package io.construct.corelib.common.dto.payment;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO для валидации оплаты по чеку. Используется в PaymentModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidatePaymentDto {
    private String orderId; // ID заказа
    private String chequeText; // Текст чека
}
