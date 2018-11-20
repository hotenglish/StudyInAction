package spittr.client.exception;

@SuppressWarnings("serial")
public class SpitterClientException extends RuntimeException {
  public SpitterClientException(String message, Throwable t) {
    super(message, t);
  }
}
