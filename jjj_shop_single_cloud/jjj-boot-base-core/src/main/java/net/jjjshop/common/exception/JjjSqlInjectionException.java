package net.jjjshop.common.exception;

/**
 * @Description: jjjshop自定义SQL注入异常
 * @author: jjjshop
 */
public class JjjSqlInjectionException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public JjjSqlInjectionException(String message){
		super(message);
	}

	public JjjSqlInjectionException(Throwable cause)
	{
		super(cause);
	}

	public JjjSqlInjectionException(String message, Throwable cause)
	{
		super(message,cause);
	}
}
