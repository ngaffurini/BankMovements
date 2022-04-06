package it.nicola.bankmovements.common.exception;

import it.nicola.bankmovements.common.dto.CustomExceptionDto;
import it.nicola.bankmovements.common.enums.ErrorType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.text.MessageFormat;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class CustomBaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    private CustomExceptionDto customExceptionDto;

    protected CustomBaseException(ErrorType errorType) {
        super(errorType.getDescrizione());
        this.customExceptionDto = CustomExceptionDto.getInstance(errorType);
    }

    protected CustomBaseException(ErrorType errorType, String... messageParameters) {
        super(MessageFormat.format(errorType.getDescrizione(), messageParameters));
        this.customExceptionDto = CustomExceptionDto.getInstance(errorType, messageParameters);
    }
}
