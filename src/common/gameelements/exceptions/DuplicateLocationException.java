package common.gameelements.exceptions;

/**
 * Thrown if a Location has a conflicting unique ID
 */
public class DuplicateLocationException extends RuntimeException
{
	/** This should be incremented if this class is modified */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a DuplicateLocationException
	 * @param id The conflicting ID
	 */
	public DuplicateLocationException(String id) {
		super("Conflicting Location ID: + \"" + id + "\"");
	}
}
