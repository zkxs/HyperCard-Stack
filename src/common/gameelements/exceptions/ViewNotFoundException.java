package common.gameelements.exceptions;

/**
 * Thrown if a View is not found
 */
public class ViewNotFoundException extends RuntimeException
{
	/** This should be incremented if this class is modified */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a ViewNotFoundException
	 * @param viewID The ID of the view that was not found
	 */
	public ViewNotFoundException(String viewID) {
		super("View + \"" + viewID + "\" not found");
	}
}
