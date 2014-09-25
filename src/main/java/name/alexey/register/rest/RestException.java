package name.alexey.register.rest;

import java.util.HashMap;
import java.util.Map;

/**
 * REST exception representation
 */
public class RestException {
    private final String message;
    private final Map<String, String> errors;

    public RestException(String message) {
        this.message = message;
        errors = new HashMap<>();
    }

    public void addError(String field, String message){
        errors.put(field, message);
    }

    public String getMessage() {
        return message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

}
