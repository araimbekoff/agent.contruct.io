package io.construct.corelib.common.dto.conversation;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.util.List;

/**
 * DTO для ответа в диалоге. Используется в ConversationModule.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResponseDto {
    private String reply; // Генерированный ответ
    private List<String> actions; // Поручения (уточнить, эскалация)
}
