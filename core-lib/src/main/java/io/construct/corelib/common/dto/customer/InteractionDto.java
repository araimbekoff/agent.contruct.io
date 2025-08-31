package io.construct.corelib.common.dto.customer;

import io.construct.corelib.common.enums.InteractionType;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.Instant; // Using Instant for Date

/**
 * DTO для представления взаимодействия с клиентом. Используется в CustomerModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InteractionDto {
    private Instant timestamp; // Время
    private String message; // Текст взаимодействия
    private InteractionType type; // Тип


}
