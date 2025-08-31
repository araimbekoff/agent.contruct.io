package io.construct.corelib.common.dto.customer;

import io.construct.corelib.common.dto.AddressDto;
import io.construct.corelib.common.dto.customer.InteractionDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для представления клиента. Используется в CustomerModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String id; // Уникальный ID (телефон или генерированный)
    private String name; // Имя
    private String phone; // Телефон (основной идентификатор)
    private String email; // Email
    private List<AddressDto> addresses; // Список адресов
    private List<InteractionDto> history; // История взаимодействий
}
