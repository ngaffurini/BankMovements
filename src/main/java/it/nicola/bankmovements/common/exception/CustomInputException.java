package it.nicola.bankmovements.common.exception;

import it.nicola.bankmovements.common.enums.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomInputException extends CustomBaseException {
  private static final long serialVersionUID = 1L;

  public CustomInputException(ErrorType errorType) {
    super(errorType);
  }

  public CustomInputException(ErrorType errorType, String... messageParameters) {
    super(errorType, messageParameters);
  }
}
