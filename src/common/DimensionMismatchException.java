package common;

/**
 * Thrown when vectors must have the same dimension but don't
 * @author Michael Ripley (<a href="mailto:michael-ripley@utulsa.edu">michael-ripley@utulsa.edu</a>) Oct 5, 2014
 */
	
public class DimensionMismatchException extends RuntimeException
{
	/** Good practice */
	private static final long serialVersionUID = -3967136072638715676L;

	/** Default constructor */
	public DimensionMismatchException()
	{
		super("Vectors have different numbers of dimensions");
	}
}
