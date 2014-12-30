package common;

/**
 * Describes a 3-dimensional vector using floats for components
 */
public class Vector
{	
	private float x, y, z;
	
	/**
	 * Construct a new Vector at <0, 0, 0>
	 */
	public Vector(){}
	
	/**
	 * Create a new vector at <x, y, z>
	 * @param x The x component
	 * @param y The y component
	 * @param z The z component
	 */
	public Vector(float x, float y, float z)
	{
		setX(x);
		setY(y);
		setZ(z);
	}
	
	/**
	 * Copy constructor. Performs a deep copy of a given vector.
	 * @param v The vector to copy.
	 */
	public Vector(Vector v)
	{
		set(v);
	}

	/**
	 * Set the components of this vector to <x, y, z>
	 * @param x The new x component
	 * @param y The new y component
	 * @param z The new z component
	 */
	public void set(float x, float y, float z)
	{
		setX(x);
		setY(y);
		setZ(z);
	}
	
	/**
	 * Set the components of this vector using the components of a given vector
	 * @param v The vector to extract new components from.
	 */
	public void set(Vector v)
	{
		set( v.getX(), v.getY(), v.getZ() );
	}
	
	/**
	 * Get the x component of this Vector.
	 * @return the x component of this Vector.
	 */
	public float getX()
	{
		return x;
	}

	/**
	 * Set the x component of this vector
	 * @param x the new x component of this vector
	 */
	public void setX(float x)
	{
		this.x = x;
	}

	/**
	 * Get the y component of this Vector.
	 * @return the y component of this Vector.
	 */
	public float getY()
	{
		return y;
	}

	/**
	 * Set the y component of this vector
	 * @param y the new y component of this vector
	 */
	public void setY(float y)
	{
		this.y = y;
	}

	/**
	 * Get the z component of this Vector.
	 * @return the z component of this Vector.
	 */
	public float getZ()
	{
		return z;
	}

	/**
	 * Set the z component of this vector
	 * @param z the new z component of this vector
	 */
	public void setZ(float z)
	{
		this.z = z;
	}
	
	
}
