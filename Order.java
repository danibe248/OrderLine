import java.util.Arrays;

import javax.swing.JTextArea;

public class Order {
	private Product[] list;
	private Product[] pizzaList;
	private Product[] birraList;
	private Product[] barList;
	private Product[] cucinaList;
	private Product[] gelatiList;
	private String[] chronology;
	private int orderNumber;

	public Order(int number) {
		this.list = new Product[47];
		this.barList = new Product[15];
		this.birraList = new Product[4];
		this.cucinaList = new Product[5];
		this.pizzaList = new Product[15];
		this.gelatiList = new Product[10];
		this.chronology = new String[50];
		this.orderNumber = number;
	}
	
	public boolean add(Product product) {
		boolean isAdded = false;
		for (int i = 0; i < this.list.length && !isAdded; i++) {
			if (this.list[i] != null) {
				if (list[i].getName().equals(product.getName())) {
					list[i].add();
					isAdded = true;
					this.addChronology(product);
				}	
			} else {
				if (isAdded == false) {
					list[i] = new Product(product.getName(),product.getCost(),1);
					isAdded = true;
					this.addChronology(product);
				}
			}
		}
		return isAdded;
	}
	
	public void addPizzaList(Product product) {
		int i = 0;
		while (i < this.pizzaList.length && this.pizzaList[i] != null)
			i++;
		pizzaList[i] = product;
	}
	
	public void addBirraList(Product product) {
		int i = 0;
		while (i < this.birraList.length && this.birraList[i] != null)
			i++;
		birraList[i] = product;
	}

	public void addBarList(Product product) {
		int i = 0;
		while (i < this.barList.length && this.barList[i] != null)
			i++;
		barList[i] = product;
	}
	
	public void addCucinaList(Product product) {
		int i = 0;
		while (i < this.cucinaList.length && this.cucinaList[i] != null)
			i++;
		cucinaList[i] = product;
	}
	
	public void addGelatiList(Product product) {
		int i = 0;
		while (i < this.gelatiList.length && this.gelatiList[i] != null)
			i++;
		gelatiList[i] = product;
	}
	
	private void addChronology(Product product) {
		int i = 0;
		while (i < this.chronology.length && this.chronology[i] != null)
			i++;
		if (i < this.chronology.length) {
			chronology[i] = product.getName();
		}
	}
	
	public boolean undo() {
		int i = 0; boolean isRemoved = false;
		if (this.getChronologyItems() > 0) {	
			while (i < this.chronology.length && this.chronology[i] != null) {
				i++;	
			}
			for (int j = 0; j < this.list.length && !isRemoved; j++) {
				if (this.list[j].getName().equalsIgnoreCase(chronology[i-1])) {
					this.list[j].remove();
					this.chronology[i-1] = null;
					isRemoved = true;
				}
			}
		}
		return isRemoved;
	}
	
	public int getItems() {
		int i = 0;
		while (i < this.list.length && this.list[i] != null)
			i++;
		return i;
	}
	
	public int getPizzaItems() {
		int i = 0;
		while (i < this.pizzaList.length && this.pizzaList[i] != null)
			i++;
		return i;
	}

	public int getBirraItems() {
		int i = 0;
		while (i < this.birraList.length && this.birraList[i] != null)
			i++;
		return i;
	}

	public int getBarItems() {
		int i = 0;
		while (i < this.barList.length && this.barList[i] != null)
			i++;
		return i;
	}
	
	public int getCucinaItems() {
		int i = 0;
		while (i < this.cucinaList.length && this.cucinaList[i] != null)
			i++;
		return i;
	}
	
	public int getGelatiItems() {
		int i = 0;
		while (i < this.gelatiList.length && this.gelatiList[i] != null)
			i++;
		return i;
	}
	
	public int getChronologyItems() {
		int i = 0;
		while (i < this.chronology.length && this.chronology[i] != null)
			i++;
		if (i <= 50 && i >= 0)
			return i;
		else 
			return -1;
	}
	
	public Product getListProduct(int index) {
		return list[index];
	}
	
	public double getTotalCost() {
		double total = 0;
		for (int i = 0; i < this.list.length; i++) {
			if (this.list[i] != null) {
				total = total + this.list[i].getTotalCost();
			}
		}
		return Math.rint(total*10)/10;
	}
	
	public void printOnTextArea(JTextArea area) {
		for (int i = 0; i < this.list.length; i++) {
			if (this.list[i] != null) {
				if (this.list[i].getNumber() > 0) {
					int space = 32 - this.list[i].getName().length() - 9 - 1;
					area.append(this.list[i].getNumber() + " " + this.list[i].getName());
					double costNum = this.list[i].getCost()*this.list[i].getNumber();
					if (costNum < 10) {
						if (this.list[i].getNumber() > 9)
							space--;
						for (int j = 1; j <= space; j++) {
							area.append(" ");
						}
					} else if (costNum >= 10 && costNum < 100) {
						if (this.list[i].getNumber() > 9)
							space--;
						for (int j = 1; j <= space-1; j++) {
							area.append(" ");
						}
					} else {
						if (this.list[i].getNumber() > 9)
							space--;
						for (int j = 1; j <= space-2; j++) {
							area.append(" ");
						}
					}
					String cost = String.valueOf(Math.rint(costNum*10)/10);
					area.append(cost + "0\n");
				}
			}			
		}	
	}
	
	public void printPizzaOnTextArea(JTextArea area) {
		for (int i = 0; i < this.pizzaList.length; i++) {
			if (this.pizzaList[i] != null) {
				if (this.pizzaList[i].getNumber() > 0) {
					int space = 32 - this.pizzaList[i].getName().length() - 6 - 1;
					area.append(this.pizzaList[i].getName());
					int num = this.pizzaList[i].getNumber();
					if (num < 10) {
						for (int j = 1; j <= space; j++) {
							area.append(" ");
						}
					} else {
						for (int j = 1; j <= space-1; j++) {
							area.append(" ");
						}
					}
					String cost = String.valueOf(num);
					area.append(" x" + cost + "\n");
				}
			}			
		}	
	}
	
	
	public void printBirraOnTextArea(JTextArea area) {
		for (int i = 0; i < this.birraList.length; i++) {
			if (this.birraList[i] != null) {
				if (this.birraList[i].getNumber() > 0) {
					int space = 32 - this.birraList[i].getName().length() - 6 - 1;
					area.append(this.birraList[i].getName());
					int num = this.birraList[i].getNumber();
					if (num < 10) {
						for (int j = 1; j <= space; j++) {
							area.append(" ");
						}
					} else {
						for (int j = 1; j <= space-1; j++) {
							area.append(" ");
						}
					}
					String cost = String.valueOf(num);
					area.append(" x" + cost + "\n");
				}
			}			
		}	
	}
	
	public void printBarOnTextArea(JTextArea area) {
		for (int i = 0; i < this.barList.length; i++) {
			if (this.barList[i] != null) {
				if (this.barList[i].getNumber() > 0) {
					int space = 32 - this.barList[i].getName().length() - 6 - 1;
					area.append(this.barList[i].getName());
					int num = this.barList[i].getNumber();
					if (num < 10) {
						for (int j = 1; j <= space; j++) {
							area.append(" ");
						}
					} else {
						for (int j = 1; j <= space-1; j++) {
							area.append(" ");
						}
					}
					String cost = String.valueOf(num);
					area.append(" x" + cost + "\n");
				}
			}			
		}	
	}
	
	public void printGelatiOnTextArea(JTextArea area) {
		for (int i = 0; i < this.gelatiList.length; i++) {
			if (this.gelatiList[i] != null) {
				if (this.gelatiList[i].getNumber() > 0) {
					int space = 32 - this.gelatiList[i].getName().length() - 6 - 1;
					area.append(this.gelatiList[i].getName());
					int num = this.gelatiList[i].getNumber();
					if (num < 10) {
						for (int j = 1; j <= space; j++) {
							area.append(" ");
						}
					} else {
						for (int j = 1; j <= space-1; j++) {
							area.append(" ");
						}
					}
					String cost = String.valueOf(num);
					area.append(" x" + cost + "\n");
				}
			}			
		}	
	}
	
	public void printCucinaOnTextArea(JTextArea area) {
		for (int i = 0; i < this.cucinaList.length; i++) {
			if (this.cucinaList[i] != null) {
				if (this.cucinaList[i].getNumber() > 0) {
					int space = 32 - this.cucinaList[i].getName().length() - 6 - 1;
					area.append(this.cucinaList[i].getName());
					int num = this.cucinaList[i].getNumber();
					if (num < 10) {
						for (int j = 1; j <= space; j++) {
							area.append(" ");
						}
					} else {
						for (int j = 1; j <= space-1; j++) {
							area.append(" ");
						}
					}
					String cost = String.valueOf(num);
					area.append(" x" + cost + "\n");
				}
			}			
		}	
	}
	
	public double getPizzaTotal() {
		double total = 0;
		for (int i = 0; i < this.pizzaList.length; i++) {
			if (this.pizzaList[i] != null) {
				total = total + this.pizzaList[i].getTotalCost();
			}
		}
		return total;
	}
	
	public double getBirraTotal() {
		double total = 0;
		for (int i = 0; i < this.birraList.length; i++) {
			if (this.birraList[i] != null) {
				total = total + this.birraList[i].getTotalCost();
			}
		}
		return total;
	}
	
	public double getBarTotal() {
		double total = 0;
		for (int i = 0; i < this.barList.length; i++) {
			if (this.barList[i] != null) {
				total = total + this.barList[i].getTotalCost();
			}
		}
		return total;
	}
	
	public double getCucinaTotal() {
		double total = 0;
		for (int i = 0; i < this.cucinaList.length; i++) {
			if (this.cucinaList[i] != null) {
				total = total + this.cucinaList[i].getTotalCost();
			}
		}
		return total;
	}
	
	public double getGelatiTotal() {
		double total = 0;
		for (int i = 0; i < this.gelatiList.length; i++) {
			if (this.gelatiList[i] != null) {
				total = total + this.gelatiList[i].getTotalCost();
			}
		}
		return total;
	}
	
	public int getOrderNumber() {
		return orderNumber;
	}

	@Override
	public String toString() {
		return "Order [list=" + Arrays.toString(list) + ", pizzaList="
				+ Arrays.toString(pizzaList) + ", \nbirraList="
				+ Arrays.toString(birraList) + ", \nbarList="
				+ Arrays.toString(barList) + ", \ncucinaList="
				+ Arrays.toString(cucinaList) + ", \ngelatiList="
				+ Arrays.toString(gelatiList) + ", \nchronology="
				+ Arrays.toString(chronology) + "]";
	}

}
