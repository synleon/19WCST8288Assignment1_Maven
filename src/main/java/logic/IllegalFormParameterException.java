package logic;

import java.util.HashMap;
import java.util.Map;

/**
 *  Customized Exception to handle illegal parameters for submitting a jsp form
 */
public class IllegalFormParameterException extends RuntimeException {

    private Map<String, String> errorMessages;

    public IllegalFormParameterException(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
