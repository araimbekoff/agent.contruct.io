package io.construct.corelib.common.dto.ordermonitor;

import io.construct.corelib.common.dto.ordermonitor.OrderSummaryDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для отчета мониторинга заказов. Используется в OrderMonitorModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MonitorReportDto {
    private List<OrderSummaryDto> problematicOrders; // Список проблемных заказов
}
