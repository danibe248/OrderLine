public class Product {
	private String name;
	private double cost;
	private int number;
	
	public Product(String name, double cost, int number) {
		this.name = name;
		this.cost = cost;
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public double getCost() {
		return cost;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}
	
	public double getTotalCost() {
		return this.cost*this.number;
	}
	
	public void add() {
		this.number = this.number + 1;
	}
	
	public void remove() {
		this.number = this.number - 1;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", cost=" + cost + ", number="
				+ number + "]";
	}
}