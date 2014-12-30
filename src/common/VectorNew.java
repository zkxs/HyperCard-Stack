package common;

public class VectorNew
{	
	private float x, y, z;
	
	/**
	 * Construct a new Vector at <0, 0, 0>
	 */
	public VectorNew(){}
	
	public VectorNew(float x, float y, float z)
	{
		setX(x);
		setY(y);
		setZ(z);
	}

	public float getX()
	{
		return x;
	}

	public void setX(float x)
	{
		this.x = x;
	}

	public float getY()
	{
		return y;
	}

	public void setY(float y)
	{
		this.y = y;
	}

	public float getZ()
	{
		return z;
	}

	public void setZ(float z)
	{
		this.z = z;
	}
	
	
}
