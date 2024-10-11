package com.bankApp.bank_account_api.utilities;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Общий класс для представления ответа API с оберткой для данных.
 * <p>
 * Этот класс используется для формирования ответа API, содержащего данные, а также для управления
 * отсутствующими значениями через аннотацию {@link JsonInclude}. Если значение поля {@code data}
 * равно {@code null}, оно не будет включено в JSON-ответ.
 * </p>
 *
 * @param <T> Тип данных, который будет возвращен в ответе
 */
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@Schema(description = "Общий ответ API")
public class GeneralResponse<T> {

    @Schema(description = "Данные, возвращаемые в ответе", example = "{\"balance\":1000.00}")
    private T data;

    /**
     * Конструктор для создания ответа с данными.
     *
     * @param data данные, которые будут включены в ответ
     */
    public GeneralResponse(T data) {
        this.data = data;
    }
}
