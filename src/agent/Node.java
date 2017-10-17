package agent;


public class Node implements Comparable<Object> {
	private int nodeX, nodeY;
	private int heuristique;

	public Node(int x, int y) {
		this.nodeX = x;
		this.nodeY = y;
		this.heuristique = 0;
	}
	
	public int getNodeX() {
		return nodeX;
	}
	public void setNodeX(int nodeX) {
		this.nodeX = nodeX;
	}
	public int getH() {
		return this.heuristique;
	}
	public void setH(int h) {
		this.heuristique = h;
	}
	
	public int getNodeY() {
		return nodeY;
	}
	public void setNodeY(int nodeY) {
		this.nodeY = nodeY;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(heuristique);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Node other = (Node) obj;
		if (Double.doubleToLongBits(heuristique) != Double.doubleToLongBits(other.heuristique))
			return false;
		return true;
	}

	@Override
	public int compareTo(Object cnode) {
		Node other = (Node)cnode;
		return this.heuristique - other.heuristique;
	}
	@Override
	public String toString()
	{
		return this.nodeX + ":" + this.nodeY + ":" + this.getH();
		
	}

	
}
