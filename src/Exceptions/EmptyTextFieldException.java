package Exceptions;

public class EmptyTextFieldException extends Throwable
{
  private String message;
  public EmptyTextFieldException(String message_in)
  {
     message = message_in; 
  }

  public String getMessage()
  {
    return message;
  }
}
