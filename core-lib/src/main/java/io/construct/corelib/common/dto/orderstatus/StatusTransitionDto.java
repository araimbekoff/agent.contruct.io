package io.construct.corelib.common.dto.orderstatus;

import io.construct.corelib.common.dto.orderstatus.ValidationDto;
import io.construct.corelib.common.enums.OrderStatus;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для перехода статуса заказа. Используется в OrderStatusModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatusTransitionDto {
    private String orderId; // ID заказа
    private OrderStatus newStatus; // Новый статус
    private List<ValidationDto> validationResults; // Результаты валидации

}
