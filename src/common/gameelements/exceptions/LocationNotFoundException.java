package common.gameelements.exceptions;

public class LocationNotFoundException extends RuntimeException
{
	/** This should be incremented if this class is modified */
	private static final long serialVersionUID = 1L;

	/**
	 * Construct a LocationNotFoundException
	 * @param locationID The ID of the location that was not found
	 */
	public LocationNotFoundException(String locationID) {
		super("Location + \"" + locationID + "\" not found");
	}
}
