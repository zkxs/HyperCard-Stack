package common;

import common.DimensionMismatchException;

/**
 * A generic vector with a variable number of dimensions. Can be used to represent points.
 * @author Michael Ripley (<a href="mailto:michael-ripley@utulsa.edu">michael-ripley@utulsa.edu</a>) Oct 5, 2014
 */
public class Vector
{	
	/** The components of the vector */
	private double[] components;
	
	/**
	 * Create a new zero vector
	 * @param dimensions Number of components
	 */
	public Vector(int dimensions)
	{
		components = new double[dimensions];
	}
	
	/**
	 * Create a new vector given its components.
	 * @param components The vector's position.
	 */
	public Vector(double... components)
	{
		setComponents(components);
	}
	
	/**
	 * Copy constructor. Performs a deep copy.
	 * @param vector The vector to copy
	 */
	public Vector(Vector vector)
	{
		components = vector.components.clone();
	}
	
	/**
	 * Get the number of dimensions the vector is in
	 * @return the number of dimensions the vector is in
	 */
	public int getDimensions()
	{
		return components.length;
	}
	
	/**
	 * Get a component from this vector
	 * @param dimension the index of the component
	 * @return the component
	 * @throws ArrayIndexOutOfBoundsException if the component is > than the vectors dimensionality
	 */
	public double getComponent(int dimension)
	{
		return components[dimension];
	}
	
	/**
	 * Get the vector's components. Note that changes to this array will affect this vector.
	 * @return the vector's components
	 */
	public double[] getComponents()
	{
		return components;
	}
	
	/**
	 * Set the vector's components 
	 * @param components the vector's new components
	 */
	public void setComponents(double... components)
	{
		this.components = components;
	}
	
	/**
	 * Set this vector's components
	 * @param vector A vector who's components you want to copy into this vector
	 * @throws DimensionMismatchException If the two vectors are not the same size
	 */
	public void setComponents(Vector vector) throws DimensionMismatchException
	{
		checkDimensions(vector);
		System.arraycopy(vector.components, 0, components, 0, components.length);
	}
	
	/**
	 * Check if this vector has the same number of dimensions as another
	 * @param other the other vector
	 * @throws DiminsionMismatchException If the two vectors are not the same size
	 */
	private void checkDimensions(Vector other) throws DimensionMismatchException
	{
		if (this.getDimensions() != other.getDimensions())
			throw new DimensionMismatchException();
	}
	
	/**
	 * Check if a vector has the same number of dimensions as another
	 * @param a a vector
	 * @param b another vector
	 * @throws DiminsionMismatchException If the two vectors are not the same size
	 */
	private static void checkDimensions(Vector a, Vector b) throws DimensionMismatchException
	{
		if (a.getDimensions() != b.getDimensions())
			throw new DimensionMismatchException();
	}
	
	/**
	 * Get the magnitude of this vector. Note that {@link Vector#getMagnitudeSquared()}
	 * is less expensive and works fine for comparisons.
	 * @return The magnitude of this vector
	 */
	public double getMagnitude()
	{
		return Math.sqrt(getMagnitudeSquared());
	}
	
	/**
	 * Get the square of the magnitude of this vector.
	 * @return the square of the magnitude of this vector
	 */
	public double getMagnitudeSquared()
	{
		double accumulator = 0;
		for (double component : components)
		{
			accumulator += component * component;
		}
		return accumulator;
	}
	
	/**
	 * Compute the difference of two vectors.
	 * @param negative The vector to subtract from this vector.
	 * @return A new vector instance containing the result of the difference computation.
	 * @throws DimensionMismatchException If the two vectors are not the same size
	 */
	public Vector difference(Vector negative) throws DimensionMismatchException
	{
		Vector result = new Vector(getDimensions());
		return difference(negative, result);
	}
	
	/**
	 * Compute the difference of two vectors. This method does NOT instantiate a new
	 * Vector.
	 * @param negative The vector to subtract from this vector.
	 * @param result A vector to store the result into.
	 * @return A pointer to the <code>result</code> vector
	 * @throws DimensionMismatchException If the three vectors are not the same size
	 */
	public Vector difference(Vector negative, Vector result) throws DimensionMismatchException
	{
		checkDimensions(negative);
		checkDimensions(result);
		for (int i = 0; i < getDimensions(); i++)
		{
			result.components[i] = this.components[i] - negative.components[i];
		}
		return result;
	}
	
	/**
	 * Get the square of the distance between two vectors
	 * @param other The other vector
	 * @return the square of the distance between two vectors
	 * @throws DimensionMismatchException If the two vectors are not the same size
	 */
	public double getDistanceSquared(Vector other) throws DimensionMismatchException
	{
		Vector temp = new Vector(getDimensions());
		return getDistanceSquared(other, temp);
	}
	
	/**
	 * Get the square of the distance between two vectors.
	 * This method is more efficient than not providing a temporary vector.
	 * @param other The other vector
	 * @param temp A temporary vector used to compute the difference between the two vectors.
	 * @return the square of the distance between two vectors
	 * @throws DimensionMismatchException If the three vectors are not the same size
	 */
	public double getDistanceSquared(Vector other, Vector temp) throws DimensionMismatchException
	{
		difference(other, temp); // result now stored in temp
		return temp.getMagnitudeSquared();
	}
	
	/**
	 * Get the distance between two vectors
	 * @param other The other vector
	 * @return the distance between two vectors
	 * @throws DimensionMismatchException If the two vectors are not the same size
	 */
	public double getDistance(Vector other) throws DimensionMismatchException
	{
		Vector temp = new Vector(getDimensions());
		return getDistance(other, temp);
	}
	
	/**
	 * Get the distance between two vectors.
	 * This method is more efficient than not providing a temporary vector.
	 * @param other The other vector
	 * @param temp A temporary vector used to compute the difference between the two vectors.
	 * @return the distance between two vectors
	 * @throws DimensionMismatchException If the three vectors are not the same size
	 */
	public double getDistance(Vector other, Vector temp) throws DimensionMismatchException
	{
		difference(other, temp); // result now stored in temp
		return temp.getMagnitude();
	}
	
	@Override
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("<");
		for (int i = 0; i < components.length; i++)
		{
			if (i != 0) sb.append(", ");
			sb.append(String.format("%.2f", components[i]));
		}
		sb.append(">");
		
		return sb.toString();
	}
}
