package io.github.dgahn.jpashop.exception;

public class NotEnoughStockException extends RuntimeException {

  public NotEnoughStockException() {
    super();
  }

  public NotEnoughStockException(final String message, final Throwable cause) {
    super(message, cause);
  }

  public NotEnoughStockException(final String message) {
    super(message);
  }

}
