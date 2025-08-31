package io.construct.corelib.common.dto.orderstatus;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для результатов валидации. Используется в OrderStatusModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidationDto {
    private String module; // Модуль (needs, logistics и т.д.)
    private boolean isValid; // Валидно ли
    private List<String> errors; // Ошибки
}
