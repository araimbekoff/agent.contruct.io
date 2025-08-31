package io.construct.corelib.common.dto;

import io.construct.corelib.common.enums.LogisticsType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant; // Using Instant for Date

/**
 * DTO для данных логистики. Используется в LogisticsModule и OrderModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LogisticsDto {
    private LogisticsType type; // Тип доставки
    private double cost; // Стоимость
    private Instant eta; // Ожидаемое время
    private AddressDto address; // Адрес
    private LogisticsOptions options; // Опции


    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class LogisticsOptions {
        private Boolean loaders; // Нужны ли грузчики
    }
}
