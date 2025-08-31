package io.construct.corelib.common.dto.needs;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO для парсинга потребностей клиента из текста. Используется в NeedsModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ParseNeedDto {
    private String text; // Текст запроса
    private String customerId; // ID клиента
}
