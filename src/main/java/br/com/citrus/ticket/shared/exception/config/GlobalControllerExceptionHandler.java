package br.com.citrus.ticket.shared.exception.config;

import java.util.ArrayList;
import java.util.List;

import br.com.citrus.ticket.shared.exception.RequiredFieldException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.lang.Nullable;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@ControllerAdvice
public class GlobalControllerExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(value = {Exception.class, RuntimeException.class, Throwable.class})
	@Override
	public ResponseEntity<Object> handleExceptionInternal(Exception error, @Nullable Object body, HttpHeaders headers,
			HttpStatusCode status, WebRequest request) {
			CustomExceptionHandler handler = new CustomExceptionHandler();
			handler.setMessage(error.getMessage());
		return new ResponseEntity<>(handler, headers, status);
	}

	@Override
	@Nullable
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
		HttpHeaders headers, HttpStatusCode status, WebRequest request) {
		List<CustomExceptionHandler> exceptions = new ArrayList<>();

		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		for(FieldError error: errors){
			exceptions.add(new CustomExceptionHandler(error.getField() + ": " + error.getDefaultMessage()));
		}
    	return new ResponseEntity<>(exceptions, headers, status);
	}

    @ExceptionHandler(RequiredFieldException.class)
    public ResponseEntity<Object> handleRequiredFieldException(Exception exception, WebRequest request) {
        CustomExceptionHandler handler = new CustomExceptionHandler();
        handler.setMessage(exception.getMessage());
        return new ResponseEntity<>(handler, HttpStatus.BAD_REQUEST);
    }

}
