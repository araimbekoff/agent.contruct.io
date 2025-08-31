package io.construct.corelib.common.dto.catalog;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * DTO для представления товара. Используется в CatalogModule.
 * Описывает уникальный товар с его характеристиками.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ItemDto {
    private String id; // Уникальный ID товара
    private String name; // Наименование
    private double price; // Цена
    private String unit; // Единица измерения
    private String category; // Опционально: категория
    private String manufacturer; // Производитель
    private Double weight; // Вес
    private Double volume; // Объем
}
