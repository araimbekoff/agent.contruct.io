package io.construct.corelib.common.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO для представления адреса. Используется в CustomerModule и LogisticsModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressDto {
    private String city; // Город
    private String street; // Улица
    private String house; // Дом
    private AddressOptions options; // Опции доставки

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class AddressOptions {
        private Integer floor; // Этаж
        private Boolean elevator; // Наличие лифта
    }
}
