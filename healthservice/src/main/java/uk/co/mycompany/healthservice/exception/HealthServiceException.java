package uk.co.mycompany.healthservice.exception;

import lombok.Getter;

/**
 * The <tt>HealthServiceException</tt> is thrown when failures occur at the <tt>HealthService</tt> or
 * <tt>HealthServiceHandler</tt> levels.
 */
@Getter
public class HealthServiceException extends RuntimeException {

    private String id;

    /**
     * Constructs an instance of <code>HealthServiceException</code> with the specified detail message.
     *
     * @param msg the detail message.
     */
    public HealthServiceException(final String msg) {
        super(msg);
    }

    /**
     * Constructs an instance of <code>HealthServiceException</code> with the specified detail message.
     *
     * @param message the detail message.
     */
    public HealthServiceException(String id, String message) {
        super(message);
        this.id = id;
    }

}
