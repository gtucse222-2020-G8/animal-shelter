package cse222.group8.server.DataStructures;

/**
 * An Edge is a link between two vertices.
 * 
 * @author Koffman and Wolfgang
 */

public class Edge {

	/** Source vertex */
	private int source;

	/** Destination vertex */
	private int dest;

	/** Weight */
	private double weight;

	/**
	 * Constructs an Edge from source to destination. Sets the weight to 1.0
	 * 
	 * @param source Source vertex
	 * @param dest   Destination vertex
	 */
	public Edge(int source, int dest) {
		this.source = source;
		this.dest = dest;
		this.weight = 1.0;
	}

	/**
	 * Constructs an Edge from source to destination. Sets the weight to w
	 * 
	 * @param source Source vertex
	 * @param dest   Destination vertex
	 * @param w      Weight
	 */
	public Edge(int source, int dest, double w) {
		this.source = source;
		this.dest = dest;
		this.weight = w;
	}

	/**
	 * Get source
	 * 
	 * @return Value of source
	 */
	public int getSource() {
		return source;
	}

	/**
	 * Get Destination
	 * 
	 * @return Value of dest
	 */
	public int getDest() {
		return dest;
	}

	/**
	 * Get weight
	 * 
	 * @return Value of weight
	 */
	public double getWeight() {
		return weight;
	}

	/**
	 * Returns a String representation of the edge
	 * 
	 * @return String representation of the edge
	 */
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		sb.append(Integer.toString(source));
		sb.append(",");
		sb.append(Integer.toString(dest));
		sb.append(",");
		sb.append(Double.toString(weight));
		sb.append(")");
		return sb.toString();
	}

	/**
	 * Return true if two edges are equal.
	 * 
	 * @param obj Other edge
	 * @return true if edges equal
	 */
	public boolean equals(Object obj) {
		if (obj instanceof Edge) {

			Edge other = (Edge) obj;
			return (source == other.source && dest == other.dest);

		} else {

			return false;

		}
	}

	/**
	 * Return hash code of edge.
	 * 
	 * @return hash code of edge
	 */
	public int hashCode() {
		return source * 31 + dest;
	}

}
