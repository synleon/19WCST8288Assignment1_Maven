package logic;

import java.util.Map;

/**
 * @author leon
 *  Customized Exception to handle illegal parameters for submitting a jsp form
 *  used in CreateEntity method of logic
 */
public class IllegalFormParameterException extends RuntimeException {

    /**
     * Associative array of error messages,
     */
    private Map<String, String> errorMessages;

    /**
     * Constructor, must initialized with a Associative array
     * @param errorMessages
     */
    public IllegalFormParameterException(Map<String, String> errorMessages) {
        this.errorMessages = errorMessages;
    }

    /**
     * getter for error messages
     * @return Map<String, String>
     */
    public Map<String, String> getErrorMessages() {
        return errorMessages;
    }
}
