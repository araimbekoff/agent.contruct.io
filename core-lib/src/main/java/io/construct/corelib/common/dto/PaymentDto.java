package io.construct.corelib.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO для данных оплаты. Используется в PaymentModule и OrderModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentDto {
    private PaymentMethod method; // Способ оплаты
    private double amount; // Сумма
    private PaymentStatus status; // Статус оплаты
    private String link; // Ссылка на оплату
    private String cheque; // Парсированный чек

    public enum PaymentMethod {
        cash, card, invoice
    }

    public enum PaymentStatus {
        pending, paid, failed
    }
}
