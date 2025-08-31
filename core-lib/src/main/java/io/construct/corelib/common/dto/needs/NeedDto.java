package io.construct.corelib.common.dto.needs;

import io.construct.corelib.common.dto.CartItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для представления потребностей клиента. Используется в NeedsModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NeedDto {
    private List<CartItemDto> items; // Корзина товаров
    private List<String> clarifications; // Список уточнений
}
