package io.construct.corelib.common.dto.catalog;

import io.construct.corelib.common.dto.catalog.ItemDto;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для результатов поиска товаров. Используется в CatalogModule.
 * Содержит список найденных товаров и уровень уверенности.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatchedItemDto {
    private List<ItemDto> matchedItems; // Список совпадений
    private double confidence; // Уровень уверенности (0-1)
}
