package it.nicola.bankmovements.common.dto;

import it.nicola.bankmovements.common.enums.ErrorType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.collections4.CollectionUtils;

import java.io.Serializable;
import java.text.MessageFormat;
import java.util.Arrays;
import java.util.Objects;

@Getter
@Setter
public class CustomExceptionDto implements Serializable {
    ErrorType errorType;
    String errorMessage;

    private CustomExceptionDto(ErrorType errorType) {
        this(errorType, errorType.getDescrizione());
    }

    private CustomExceptionDto(ErrorType errorType, String errorMessage) {
        this.errorType = errorType;
        this.errorMessage = errorMessage;
    }

    public static CustomExceptionDto getInstance(ErrorType errorType, String... messageParameters) {

        CustomExceptionDto exceptionDto;

        if (Objects.nonNull(messageParameters)
                && CollectionUtils.isNotEmpty(Arrays.asList(messageParameters))) {
            exceptionDto =
                    new CustomExceptionDto(
                            errorType, MessageFormat.format(errorType.getDescrizione(), messageParameters));
        } else {
            exceptionDto = new CustomExceptionDto(errorType);
        }

        return exceptionDto;
    }
}
