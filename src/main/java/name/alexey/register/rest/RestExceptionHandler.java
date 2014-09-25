package name.alexey.register.rest;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

/**
 * General error handler for the application.
 */
@ControllerAdvice
class RestExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	@ExceptionHandler(value = Exception.class)
    @ResponseBody
	public RestException exception(Exception exception, WebRequest request) {
		return new RestException( exception.getMessage());
	}

    @ResponseStatus(HttpStatus.PRECONDITION_FAILED)  // Validation failed
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public RestException validation(MethodArgumentNotValidException e) {
        final RestException ex = new RestException("Validation failed");
        for( final ObjectError err : e.getBindingResult().getAllErrors()){
            if(FieldError.class.isInstance( err)) {
                ex.addError(((FieldError)err).getField(), err.getDefaultMessage());
            } else {
                ex.addError(err.getCode(), err.getDefaultMessage());
            }
        }
        return ex;
    }

    @ResponseStatus( HttpStatus.CONFLICT)
    @ExceptionHandler(DataIntegrityViolationException.class) // Database exceptions
    @ResponseBody
    public RestException handleConflict(DataIntegrityViolationException e) {
        final RestException ex = new RestException("Cannot complete transaction");
        if( e.getMessage().contains( "constraint [UK_")) {
            ex.addError( "username", "Username already exists");
        }
        return ex;
    }

}