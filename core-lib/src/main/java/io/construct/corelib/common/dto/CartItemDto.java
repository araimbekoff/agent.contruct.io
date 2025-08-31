package io.construct.corelib.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO для элемента корзины. Используется в NeedsModule и OrderModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartItemDto {
    private String itemId; // ID из каталога
    private int quantity; // Количество
    private CartItemAttributes attributes; // Атрибуты

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class CartItemAttributes {
        private String brand; // Бренд
        private String size; // Размер
    }
}
