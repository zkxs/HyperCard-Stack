package common.gameelements.exceptions;

/**
 * Thrown if a View has a conflicting unique ID
 */
public class DuplicateViewException extends RuntimeException
{
	/** This should be incremented if this class is modified */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a DuplicateViewException
	 * @param id The conflicting ID
	 */
	public DuplicateViewException(String id) {
		super("Conflicting View ID: + \"" + id + "\"");
	}
}
