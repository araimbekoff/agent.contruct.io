package io.construct.corelib.common.dto.conversation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Map; // Using Map for 'any' extractedData

/**
 * DTO для сообщения в диалоге. Используется в ConversationModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class MessageDto {
    private String text; // Текст сообщения
    private List<String> intents; // Распознанные intents
    private Map<String, Object> extractedData; // Извлеченные данные
}
