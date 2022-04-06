package it.nicola.bankmovements.common.exception;

import it.nicola.bankmovements.common.enums.ErrorType;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomNotFoundException extends CustomBaseException {
  private static final long serialVersionUID = 1L;

  public CustomNotFoundException(ErrorType errorType) {
    super(errorType);
  }

  public CustomNotFoundException(ErrorType errorType, String... messageParameters) {
    super(errorType, messageParameters);
  }
}
