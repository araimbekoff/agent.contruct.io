package io.construct.corelib.common.dto.ordermonitor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для краткого обзора заказа. Используется в OrderMonitorModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSummaryDto {
    private String orderId; // ID заказа
    private String clientId; // ID клиента
    private String status; // Статус заказа
    private List<String> issues; // Проблемы (например, "висит >1 часа")
}
