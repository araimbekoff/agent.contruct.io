package io.construct.corelib.common.dto.order;

import io.construct.corelib.common.dto.CartItemDto;
import io.construct.corelib.common.dto.LogisticsDto;
import io.construct.corelib.common.dto.PaymentDto;
import io.construct.corelib.common.dto.order.ChangeDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для представления заказа. Используется в OrderModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private String id; // ID заказа
    private String customerId; // Ссылка на клиента
    private List<CartItemDto> items; // Товары
    private double totalAmount; // Итоговая сумма
    private LogisticsDto logistics; // Данные логистики
    private PaymentDto payment; // Данные оплаты
    private String status; // Текущий статус
    private List<ChangeDto> history; // История изменений
}
