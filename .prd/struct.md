# Структура Проекта Construct.io

Данный документ описывает текущую структуру микросервисов и библиотек в проекте `java-ver`, а также их взаимодействие.

## 1. `construct/core` (Библиотека)

*   **Описание**: Это основная библиотека, предназначенная для использования в других микросервисах. Она содержит общие компоненты и конфигурации, необходимые для работы с базой данных.
*   **`pom.xml`**: Включает зависимости для Spring Boot Starter, Spring Data JPA и PostgreSQL, что позволяет любому проекту, зависящему от `core`, использовать функциональность работы с БД.
*   **Настройки БД**: `core` не содержит жестко закодированных параметров подключения к базе данных. Он ожидает, что эти параметры будут предоставлены извне (например, через `application.properties` или `properties.db.yml` в зависимом проекте).
*   **Сущности**: Изначально сущности OpenAI находились здесь, но были перемещены в `openai-agent` для лучшей модульности.

## 2. `conversation-agent` (Микросервис)

*   **Описание**: Микросервис, который использует библиотеку `construct/core`.
*   **Порт**: Запускается на порту `8001`.
*   **`pom.xml`**: Включает зависимости для Spring Boot Starter Web и `construct/core`.
*   **`application.properties`**: Содержит настройку порта (`server.port=8001`) и импортирует внешнюю конфигурацию базы данных: `spring.config.import=file:/opt/projects/_CONSTRUCTIO/java-ver/properties.db.yml`.
*   **`ConversationAgentApplication.java`**: Главный класс приложения, который сканирует компоненты `io.construct.core` и `io.construct.conversationagent`.

## 3. `openai-agent` (Микросервис)

*   **Описание**: Новый микросервис, созданный по подобию `conversation-agent`. Он также использует библиотеку `construct/core` и содержит сущности, связанные с OpenAI.
*   **Порт**: Запускается на порту `8002`.
*   **`pom.xml`**: Включает зависимости для Spring Boot Starter Web, Spring Data JPA, PostgreSQL и `construct/core`.
*   **`application.properties`**: Содержит настройку порта (`server.port=8002`) и импортирует внешнюю конфигурацию базы данных: `spring.config.import=file:/opt/projects/_CONSTRUCTIO/java-ver/properties.db.yml`.
*   **`OpenaiAgentApplication.java`**: Главный класс приложения, который сканирует компоненты `io.construct.core` и `io.construct.openaiagent`, а также сущности из `io.construct.openaiagent.entities` и `io.construct.core.entities`.
*   **Сущности**: Содержит сущности `OpenaiCompletionLogEntity`, `OpenaiMessageEntity` и `OpenaiSessionEntity`, которые были перенесены из `construct/core`.

## 4. `properties.db.yml` (Централизованный файл конфигурации БД)

*   **Расположение**: `/opt/projects/_CONSTRUCTIO/java-ver/properties.db.yml`
*   **Описание**: Этот файл содержит общие параметры подключения к базе данных PostgreSQL для всех микросервисов.
*   **Содержимое**:
    ```yaml
    spring:
      datasource:
        url: jdbc:postgresql://localhost:5432/construct_io
        username: construct_io
        password: construct_io_123
        driver-class-name: org.postgresql.Driver
      jpa:
        hibernate:
          ddl-auto: update
        show-sql: true
    ```
*   **Использование**: Микросервисы (например, `conversation-agent` и `openai-agent`) импортируют этот файл через `spring.config.import` в своих `application.properties`.

## Взаимодействие и запуск

1.  **Запуск базы данных**: Перед запуском любого агента необходимо запустить базу данных PostgreSQL. Для этого можно использовать Docker Compose с файлом, который определяет контейнер PostgreSQL. Пример конфигурации Docker Compose (не включен в `properties.db.yml`, но может быть создан отдельно):
    ```yaml
    # docker-compose.yml (пример)
    version: '3.8'
    services:
      db:
        image: postgres:latest
        container_name: construct_io_db
        environment:
          POSTGRES_DB: construct_io
          POSTGRES_USER: construct_io
          POSTGRES_PASSWORD: construct_io_123
        ports:
          - "5432:5432"
        volumes:
          - db_data:/var/lib/postgresql/data
        restart: always
    volumes:
      db_data:
    ```
    Запуск: `docker-compose -f docker-compose.yml up -d` (из корневой директории проекта).

2.  **Запуск агентов**:
    *   **`conversation-agent`**: Перейдите в директорию `conversation-agent` и выполните `mvn spring-boot:run`. Он запустится на порту 8001.
    *   **`openai-agent`**: Перейдите в директорию `openai-agent` и выполните `mvn spring-boot:run`. Он запустится на порту 8002.

Таким образом, `construct/core` предоставляет общие зависимости для работы с БД, `properties.db.yml` централизует настройки подключения к БД, а `conversation-agent` и `openai-agent` являются независимыми микросервисами, использующими эту инфраструктуру.
