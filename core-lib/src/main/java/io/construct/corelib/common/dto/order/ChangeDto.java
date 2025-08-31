package io.construct.corelib.common.dto.order;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant; // Using Instant for Date

/**
 * DTO для записи изменений в заказе. Используется в OrderModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChangeDto {
    private Instant timestamp; // Время
    private String field; // Измененное поле
    private Object oldValue; // Старое значение
    private Object newValue; // Новое значение
}
