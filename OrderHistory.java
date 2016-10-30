import java.util.*;
public class OrderHistory {
	private ArrayList<Order> orderList;
	
	public OrderHistory() {
		orderList = new ArrayList<Order>();
	}
	
	public void addOrder(Order current) {
		orderList.add(current);
	}
	
	public int getOrders() {
		return orderList.size();
	}
	
	public String printOrders(int index) {
		String toReturn = "A" + orderList.get(index).getOrderNumber() + "\t" + orderList.get(index).getPizzaTotal() + "\t" + orderList.get(index).getBirraTotal()  + "\t" + orderList.get(index).getBarTotal() + "\t" + orderList.get(index).getCucinaTotal() + "\t" + orderList.get(index).getGelatiTotal() + "\t\t" + orderList.get(index).getTotalCost() + "\r\n"; 
		return toReturn;
	}
	
	public double getOrdersPizzaTotal() {
		double total = 0;
		for (Order e : orderList) {
			total = total + e.getPizzaTotal();
		}
		return total;
	}
	
	public double getOrdersBirraTotal() {
		double total = 0;
		for (Order e : orderList) {
			total = total + e.getBirraTotal();
		}
		return total;
	}
	
	public double getOrdersBarTotal() {
		double total = 0;
		for (Order e : orderList) {
			total = total + e.getBarTotal();
		}
		return total;
	}
	
	public double getOrdersCucinaTotal() {
		double total = 0;
		for (Order e : orderList) {
			total = total + e.getCucinaTotal();
		}
		return total;
	}
	
	public double getOrdersGelatiTotal() {
		double total = 0;
		for (Order e : orderList) {
			total = total + e.getGelatiTotal();
		}
		return total;
	}
	
	public double getTotalissimo() {
		double total = 0;
		for (Order e : orderList) {
			total = total + e.getTotalCost();
		}
		return total;
	}
}
