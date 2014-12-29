package common.gameelements.exceptions;

/**
 * Thrown if a PuzzleController is not found
 */
public class PuzzleControllerNotFoundException extends RuntimeException
{
	/** This should be incremented if this class is modified */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a PuzzleControllerNotFoundException
	 * @param controllerID The ID of the puzzle controller that was not found
	 */
	public PuzzleControllerNotFoundException(String controllerID) {
		super("Location + \"" + controllerID + "\" not found");
	}
}
