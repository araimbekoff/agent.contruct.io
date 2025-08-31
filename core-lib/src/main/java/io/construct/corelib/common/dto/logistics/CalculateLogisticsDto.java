package io.construct.corelib.common.dto.logistics;

import io.construct.corelib.common.dto.CartItemDto;
import io.construct.corelib.common.dto.AddressDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для расчета логистики. Используется в LogisticsModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CalculateLogisticsDto {
    private String orderId; // ID заказа
    private List<CartItemDto> items; // Товары в заказе
    private AddressDto address; // Адрес доставки
}
