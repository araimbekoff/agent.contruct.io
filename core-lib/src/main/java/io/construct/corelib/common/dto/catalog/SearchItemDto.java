package io.construct.corelib.common.dto.catalog;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO для запроса поиска товаров. Используется в CatalogModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SearchItemDto {
    private String query; // Ключевые слова для поиска
    private Integer limit; // Лимит результатов (default: 10)
}
