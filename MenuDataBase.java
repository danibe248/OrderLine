public class MenuDataBase {
	private Product[] menuPizza;
	private Product[] menuCucina;
	private Product[] menuBar;
	private Product[] menuBirra;
	private Product[] menuGelati;
	
	public MenuDataBase() {
		menuPizza = new Product[15];
//		menuCucina = new Product[6];
		menuCucina = new Product[4];
//		menuBar = new Product[15];
		menuBar = new Product[12];
		menuBirra = new Product[4];
		menuGelati = new Product[4];
		this.setPizzaMenu();
		this.setCucinaMenu();
		this.setBarMenu();
		this.setBirraMenu();
		this.setGelatiMenu();
	}
	
	public void setPizzaMenu() {
		menuPizza[0] = new Product("Focaccia rosmarino", 3.5, 1);
		menuPizza[1] = new Product("Focaccia crudo", 4.5, 1);
		menuPizza[2] = new Product("Margherita", 4.5, 1);
		menuPizza[3] = new Product("Biancaneve", 4, 1);
		menuPizza[4] = new Product("Napoli", 5.5, 1);
		menuPizza[5] = new Product("Patapizza", 5.5, 1);
		menuPizza[6] = new Product("Diavola", 5.5, 1);
		menuPizza[7] = new Product("Cotto", 5.5, 1);
		menuPizza[8] = new Product("Crudo", 6, 1);
		menuPizza[9] = new Product("Speck", 6, 1);
		menuPizza[10] = new Product("Prosciutto e funghi", 6, 1);
		menuPizza[11] = new Product("4 stagioni", 6.5, 1);
		menuPizza[12] = new Product("Brianzola", 6, 1);
//		menuPizza[13] = new Product("Primavera", 7, 1);
		menuPizza[13] = new Product("Salsiccia e funghi", 6.5, 1);
		menuPizza[14] = new Product("+1 ingrediente", 0.5, 0);
	}
	
	public void setCucinaMenu() {
		menuCucina[0] = new Product("Patatine", 2.5, 1);
		menuCucina[1] = new Product("Salamella", 4, 1);
		menuCucina[2] = new Product("Hamburger", 5.5, 1);
//		menuCucina[2] = new Product("Kebab", 4, 1);
//		menuCucina[3] = new Product("Hot dog", 3, 1);
		menuCucina[3] = new Product("Torta", 2.5, 1);
//		menuCucina[5] = new Product("Gelato", 0, 0);
	}
	
	public void setBarMenu() {
		menuBar[0] = new Product("Acqua ½ L", 1, 1);
		menuBar[1] = new Product("Acqua 1 ½ L", 2, 1);
		menuBar[2] = new Product("Bibite", 2.5, 1);
		menuBar[3] = new Product("Coca spina", 2.5, 1);
		menuBar[4] = new Product("Cuba libre", 5, 1);
		menuBar[5] = new Product("Gin tonic", 5, 1);
		menuBar[6] = new Product("Gin lemon", 5, 1);
//		menuBar[7] = new Product("Vodka tonic", 5, 1);
		menuBar[7] = new Product("Vodka lemon/tonic", 5, 1);
		menuBar[8] = new Product("Vino 1 L", 9, 1);
		menuBar[9] = new Product("Vino ½ L", 6, 1);
//		menuBar[11] = new Product("Vino bicchiere", 2, 1);
		menuBar[10] = new Product("Caffè", 1, 1);
//		menuBar[13] = new Product("Caffè corretto", 1.5, 1);
		menuBar[11] = new Product("Liquore", 2.5, 1);
	}
	
	public void setBirraMenu() {
//		menuBirra[0] = new Product("Pedavena 0,4", 3, 1);
		menuBirra[0] = new Product("Castello 0,4", 3, 1);
		menuBirra[1] = new Product("Artigianale 0,4", 4, 1);
//		menuBirra[2] = new Product("Pedavena 0,2", 2, 1);
		menuBirra[2] = new Product("Castello 0,2", 2, 1);
		menuBirra[3] = new Product("Artigianale 0,2", 3, 1);
	}
	
	public void setGelatiMenu() {
		menuGelati[0] = new Product("Ghiacciolo", 0.5, 1);
		menuGelati[1] = new Product("Cornetto classico", 1.30, 1);
		menuGelati[2] = new Product("Magnum classico", 1.80, 1);
		menuGelati[3] = new Product("Cucciolone", 1.30, 1);
	}
	
	public Product getPizzaProduct(String name) {
		int index = 0; boolean isFound = false;
		for (int i = 0; i < menuPizza.length && !isFound; i++) {
			if (menuPizza[i].getName().equalsIgnoreCase(name)) {
				index = i;
				isFound = true;
			}
		}
		if (isFound) {
			return menuPizza[index];
		} else { 
			return null; 
		}	
	}
	
	public Product getCucinaProduct(String name) {
		int index = 0; boolean isFound = false;
		for (int i = 0; i < menuCucina.length && !isFound; i++) {
			if (menuCucina[i].getName().equalsIgnoreCase(name)) {
				index = i;
				isFound = true;
			}
		}
		if (isFound)
			return menuCucina[index];
		else 
			return null; 
	}
	
	public Product getBarProduct(String name) {
		int index = 0; boolean isFound = false;
		for (int i = 0; i < menuBar.length && !isFound; i++) {
			if (menuBar[i].getName().equalsIgnoreCase(name)) {
				index = i;
				isFound = true;
			}
		}
		if (isFound)
			return menuBar[index];
		else 
			return null; 
	}
	
	public Product getBirraProduct(String name) {
		int index = 0; boolean isFound = false;
		for (int i = 0; i < menuBirra.length && !isFound; i++) {
			if (menuBirra[i].getName().equalsIgnoreCase(name)) {
				index = i;
				isFound = true;
			}
		}
		if (isFound)
			return menuBirra[index];
		else 
			return null; 
	}
	
	public Product getGelatiProduct(String name) {
		int index = 0; boolean isFound = false;
		for (int i = 0; i < menuGelati.length && !isFound; i++) {
			if (menuGelati[i].getName().equalsIgnoreCase(name)) {
				index = i;
				isFound = true;
			}
		}
		if (isFound)
			return menuGelati[index];
		else 
			return null; 
	}
	
	public Product getPizzaProduct(int index) {
		return menuPizza[index];
	}
	
	public Product getCucinaProduct(int index) {
		return menuCucina[index];
	}
	
	public Product getBarProduct(int index) {
		return menuBar[index];
	}
	
	public Product getBirraProduct(int index) {
		return menuBirra[index];
	}
	
	public Product getGelatiProduct(int index) {
		return menuGelati[index];
	}
	
	public Product getProduct(String name) { 
		Product product = getPizzaProduct(name);
		if (product == null) {
			product = getCucinaProduct(name);
		}
		if (product == null) {
			product = getBarProduct(name);
 		}
		if (product == null) {
			product = getBirraProduct(name);
		}
		if (product == null) {
			product = getGelatiProduct(name);
		}
		return product;
	}
	
	public String getSector(String name) {
		String type = "pizza";
		Product product = getPizzaProduct(name);
		if (product == null) {
			product = getCucinaProduct(name);
			type = "cucina";
		}
		if (product == null) {
			product = getBarProduct(name);
			type = "bar";
 		}
		if (product == null) {
			product = getBirraProduct(name);
			type = "birra";
		}
		if (product == null) {
			product = getGelatiProduct(name);
			type = "gelati";
		}
		return type;
	}
}
