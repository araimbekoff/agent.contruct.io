### 4.1 CatalogModule (Номенклатура, прокси)
- **Цели**: Импорт и обновление справочника товаров, поиск по "грязным" данным.
- **Зависимости**: Внешний сервис (RAG/YDB для синонимов).
- **Функции**: Импорт CSV, поиск/фильтрация, фолбэк на неизвестные товары.
- **Хранение**: Таблица `items` в SQLite (в схеме клиента).
- **DTO**:
  ```
  // ItemDto
  {
    id: string; // Уникальный ID товара
    name: string; // Наименование
    price: number; // Цена
    unit: string; // Единица измерения
    category?: string; // Опционально: категория
    manufacturer?: string; // Производитель
    weight?: number; // Вес
    volume?: number; // Объем
  }

  // SearchItemDto
  {
    query: string; // Ключевые слова для поиска
    limit?: number; // Лимит результатов (default: 10)
  }

  // MatchedItemDto
  {
    matchedItems: ItemDto[]; // Список совпадений
    confidence: number; // Уровень уверенности (0-1)
  }
  ```

### 4.2 CustomerModule (Клиенты)
- **Цели**: Хранение данных клиентов, экстракция из текстов.
- **Зависимости**: OpenAI для парсинга.
- **Функции**: CRUD клиентов, история взаимодействий, привязка к заказам.
- **Хранение**: Таблица `customers` в SQLite (в схеме клиента).
- **DTO**:
  ```
  // CustomerDto
  {
    id: string; // Уникальный ID (телефон или генерированный)
    name?: string; // Имя
    phone: string; // Телефон (основной идентификатор)
    email?: string; // Email
    addresses: AddressDto[]; // Список адресов
    history: InteractionDto[]; // История взаимодействий
  }

  // AddressDto
  {
    city: string; // Город
    street: string; // Улица
    house: string; // Дом
    options?: { floor: number; elevator: boolean; }; // Опции доставки
  }

  // InteractionDto
  {
    timestamp: Date; // Время
    message: string; // Текст взаимодействия
    type: 'query' | 'response' | 'change'; // Тип
  }
  ```

### 4.3 NeedsModule (Потребности клиента)
- **Цели**: Извлечение товаров/количеств из запросов, сопоставление с каталогом.
- **Зависимости**: OpenAI, CatalogModule.
- **Функции**: Парсинг корзины, уточнения, закрытие потребностей.
- **Хранение**: Данные потребностей сохраняются в составе заказов (таблица `orders`).
- **DTO**:
  ```
  // NeedDto
  {
    items: CartItemDto[]; // Корзина товаров
    clarifications: string[]; // Список уточнений
  }

  // CartItemDto
  {
    itemId: string; // ID из каталога
    quantity: number; // Количество
    attributes?: { brand: string; size: string; }; // Атрибуты
  }

  // ParseNeedDto
  {
    text: string; // Текст запроса
    customerId: string; // ID клиента
  }
  ```

### 4.4 OrderModule (Заказы)
- **Цели**: Хранение заказов, расчет итоговой суммы.
- **Зависимости**: NeedsModule, CustomerModule, LogisticsModule, PaymentModule.
- **Функции**: CRUD заказов, история изменений, расчет суммы.
- **Хранение**: Таблица `orders` в SQLite (в схеме клиента).
- **DTO**:
  ```
  // OrderDto
  {
    id: string; // ID заказа
    customerId: string; // Ссылка на клиента
    items: CartItemDto[]; // Товары
    totalAmount: number; // Итоговая сумма
    logistics: LogisticsDto; // Данные логистики
    payment: PaymentDto; // Данные оплаты
    status: string; // Текущий статус
    history: ChangeDto[]; // История изменений
  }

  // ChangeDto
  {
    timestamp: Date; // Время
    field: string; // Измененное поле
    oldValue: any; // Старое значение
    newValue: any; // Новое значение
  }
  ```

### 4.5 OrderStatusModule (Жизненный цикл заказа)
- **Цели**: Управление статусами заказов через state machine, валидация переходов.
- **Зависимости**: OrderModule, другие модули для валидации.
- **Функции**: Переходы статусов, триггеры (пересчет, уведомления).
- **Хранение**: Статус хранится в `orders.status`.
- **DTO**:
  ```
  // StatusTransitionDto
  {
    orderId: string; // ID заказа
    newStatus: 'draft' | 'confirmed' | 'inProgress' | 'delivered' | 'canceled' | ...; // Новый статус
    validationResults: ValidationDto[]; // Результаты валидации
  }

  // ValidationDto
  {
    module: string; // Модуль (needs, logistics и т.д.)
    isValid: boolean; // Валидно ли
    errors: string[]; // Ошибки
  }
  ```

### 4.6 OrderMonitorModule (Мониторинг)
- **Цели**: Выявление проблемных заказов.
- **Зависимости**: OrderModule, OrderStatusModule.
- **Функции**: Сканирование заказов по крону, возврат проблемных через API.
- **Хранение**: Чтение из таблицы `orders`.
- **DTO**:
  ```
  // MonitorReportDto
  {
    problematicOrders: OrderSummaryDto[]; // Список проблемных
  }

  // OrderSummaryDto
  {
    orderId: string;
    clientId: string;
    status: string;
    issues: string[]; // Проблемы (например, "висит >1 часа")
  }
  ```

### 4.7 LogisticsModule (Логистика, прокси)
- **Цели**: Расчет доставки (MVP: самовывоз).
- **Зависимости**: Внешний сервис.
- **Функции**: Передача данных, возврат стоимости/ETA, фолбэки.
- **Хранение**: Данные в `orders.logistics` (JSON-поля).
- **DTO**:
  ```
  // LogisticsDto
  {
    type: 'pickup' | 'delivery'; // Тип
    cost: number; // Стоимость
    eta: Date; // Ожидаемое время
    address: AddressDto; // Адрес
    options: { loaders: boolean; }; // Опции
  }

  // CalculateLogisticsDto
  {
    orderId: string;
    items: CartItemDto[];
    address: AddressDto;
  }
  ```

### 4.8 PaymentModule (Оплаты, прокси)
- **Цели**: Фиксация способов оплаты, валидация чеков.
- **Зависимости**: OpenAI, внешний сервис.
- **Функции**: Генерация ссылок/платежек, валидация.
- **Хранение**: Данные в `orders.payment` (JSON-поля).
- **DTO**:
  ```
  // PaymentDto
  {
    method: 'cash' | 'card' | 'invoice'; // Способ
    amount: number; // Сумма
    status: 'pending' | 'paid' | 'failed'; // Статус
    link?: string; // Ссылка на оплату
    cheque?: string; // Парсированный чек
  }

  // ValidatePaymentDto
  {
    orderId: string;
    chequeText: string; // Текст чека
  }
  ```

### 4.9 ConversationModule (Диалоги, прокси)
- **Цели**: Парсинг сообщений, генерация ответов/уточнений.
- **Зависимости**: OpenAI, другие модули для intents.
- **Функции**: Хранение сессий, фолбэки, эскалация.
- **Хранение**: Таблица `conversations` в SQLite (в схеме клиента).
- **DTO**:
  ```
  // MessageDto
  {
    text: string; // Текст сообщения
    intents: string[]; // Распознанные intents
    extractedData: any; // Извлеченные данные
  }

  // ResponseDto
  {
    reply: string; // Генерированный ответ
    actions: string[]; // Поручения (уточнить, эскалация)
  }
  ```

