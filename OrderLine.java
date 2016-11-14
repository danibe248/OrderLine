import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.*;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.DateFormat;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.*;
import javax.print.attribute.standard.*;

@SuppressWarnings("serial")
class MyFrame extends JFrame {
	private JPanel leftPanel = new JPanel();
	private JPanel rightPanel = new JPanel();
	
	private JTextArea view = new JTextArea("      FESTA DELLA BIRRA\n         Bernareggio\n\n");
	
	private JScrollPane textPanel = new JScrollPane(view);
	
	private JPanel editPanel = new JPanel();
	private JPanel modPanel = new JPanel();
	private JPanel commandPanel = new JPanel();
	
	private JPanel sectorPanel1 = new JPanel();
	private JPanel sectorPanel2 = new JPanel();
	private JPanel sectorPanel3 = new JPanel();
	private JPanel sectorPanel4 = new JPanel();
	
	private JButton print = new JButton("STAMPA");
	private JButton clear = new JButton("NUOVO");
	private JButton check = new JButton("TOTALE");
	private JButton undo = new JButton("UNDO");
 	
	private JButton[] pizzeria = new JButton[15];
	private JButton[] cucina = new JButton[4];
	private JButton[] bar = new JButton[12];	
	private JButton[] birra = new JButton[4];
	
	private JMenuBar mb = new JMenuBar();
	private JMenu fileM = new JMenu("File");
	private JMenu helpM = new JMenu("Help");
	private JMenu settingsM = new JMenu("Settings");
	private JMenuItem newm = new JMenuItem("New");
	private JMenuItem save = new JMenuItem("Save");
	private JMenuItem setON = new JMenuItem("Set order number");
	private JMenuItem exit = new JMenuItem("Exit");
	private JMenuItem question = new JMenuItem("?");
	
	private JCheckBox autosave = new JCheckBox("Autosave");
	private JCheckBox carry = new JCheckBox("Carry calculus");
	
	private Ascoltatore listener = new Ascoltatore();
	private int orderNumber;
	private Order order;
	private OrderHistory orderHistory = new OrderHistory(); 
	private MenuDataBase menu = new MenuDataBase();
	
	private PrintRequestAttributeSet attr;
	
	private Font fontArea = new Font(Font.MONOSPACED,Font.BOLD,16);
	private Font fontButton = new Font("Arial",Font.BOLD,24);
	private Font fontButton2 = new Font("Arial",Font.BOLD,20);
	private Font buttonRead = new Font("Arial",Font.BOLD,17);
	private Font printFont = new Font(Font.MONOSPACED,Font.BOLD,12);
	private IceCreamFrame icf;	
	
	private boolean firstSave = true;
	private String text;
	
	public MyFrame(int on) {
		super("OrderLine");
		setJMenuBar(mb);
		mb.add(fileM);
		mb.add(settingsM);
		mb.add(helpM);
		fileM.add(newm);
		fileM.addSeparator();
		fileM.add(save);
		fileM.addSeparator();
		fileM.add(exit);
		settingsM.add(autosave);
		settingsM.add(carry);
		settingsM.addSeparator();
		settingsM.add(setON);
		helpM.add(question);
		newm.addActionListener(listener);
		save.addActionListener(listener);
		exit.addActionListener(listener);
		setON.addActionListener(listener);
		settingsM.addActionListener(listener);
		question.addActionListener(listener);
		
		
		orderNumber = on;
		order = new Order(on);
		icf = new IceCreamFrame();
		this.setLayout(new GridBagLayout());
	    GridBagConstraints c= new GridBagConstraints();
	    
		leftPanel.setLayout(new GridLayout(2,1));
		rightPanel.setLayout(new GridLayout(4,1));
		
		view.setSize(400,400);    

	    view.setLineWrap(true);
	    view.setEditable(false);
	    view.setVisible(true);
	    
		textPanel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
				
		view.setFont(fontArea);
		
		editPanel.setLayout(new GridLayout(2,1));
		
		modPanel.setLayout(new GridLayout(2,1));	
		modPanel.add(undo);
		undo.setFont(fontButton2);
		undo.addActionListener(listener);
		undo.setBackground(new Color(204,204,255));

		modPanel.add(check);
		check.setFont(fontButton2);
		check.addActionListener(listener);
		check.setBackground(new Color(204,204,255));
		
		commandPanel.setLayout(new GridLayout(1,2));	
		commandPanel.add(print);
		print.setFont(fontButton);
		print.addActionListener(listener);
		print.setBackground(new Color(51,255,51));
		
		commandPanel.add(clear);
		clear.setFont(fontButton);
		clear.addActionListener(listener);
		clear.setBackground(new Color(255, 51, 0));		
		
		editPanel.add(modPanel);
		editPanel.add(commandPanel);
		editPanel.setPreferredSize(new Dimension(300,300));
		
		
		sectorPanel1.setLayout(new GridLayout(3,5));
		for (int i = 0; i < pizzeria.length; i++) {
			pizzeria[i] = new JButton(menu.getPizzaProduct(i).getName());
			if (i != 14)
				pizzeria[i].setBackground(new Color(255, 51, 0));
			else
				pizzeria[i].setBackground(new Color(255, 155, 55));
			sectorPanel1.add(pizzeria[i]);
			pizzeria[i].addActionListener(listener);
			pizzeria[i].setFont(buttonRead);
		}
		
		sectorPanel2.setLayout(new GridLayout(2,2));
		for (int i = 0; i < cucina.length; i++) {
			cucina[i] = new JButton(menu.getCucinaProduct(i).getName());
			cucina[i].setBackground(Color.YELLOW);
			sectorPanel2.add(cucina[i]);
			cucina[i].addActionListener(listener);
			cucina[i].setFont(buttonRead);
		}

		sectorPanel3.setLayout(new GridLayout(3,4));
		for (int i = 0; i < bar.length; i++) {
			bar[i] = new JButton(menu.getBarProduct(i).getName());
			bar[i].setBackground(Color.GREEN);
			sectorPanel3.add(bar[i]);
			bar[i].addActionListener(listener);
			bar[i].setFont(buttonRead);
		}
		
		sectorPanel4.setLayout(new GridLayout(2,2));
		for (int i = 0; i < birra.length; i++) {
			birra[i] = new JButton(menu.getBirraProduct(i).getName());
			if (i < 2) 	
				birra[i].setBackground(new Color(102, 255, 255));
			else
				birra[i].setBackground(new Color(152, 255, 255));
			sectorPanel4.add(birra[i]);
			birra[i].addActionListener(listener);
			birra[i].setFont(buttonRead);
		}
		
		leftPanel.add(textPanel);
		leftPanel.add(editPanel);
		
		rightPanel.add(sectorPanel1);
		rightPanel.add(sectorPanel2);
		rightPanel.add(sectorPanel3);
		rightPanel.add(sectorPanel4);
		
		c.fill = GridBagConstraints.BOTH;
		c.weightx = 0.4;
	    c.gridx = 1;
	    c.weighty = 1;
	    add(leftPanel,c);
	    c.weightx = 0.6;
	    c.gridx = 2;
	    add(rightPanel,c);
		
		pack();
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	}

	class Ascoltatore implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String current = e.getActionCommand();
			if (current.equalsIgnoreCase("UNDO")) {
				view.setText("");
				order.undo();
				printTitle();
				order.printOnTextArea(view);
				printDateTime(orderNumber);
			} else if (current.equalsIgnoreCase("TOTALE")) {
				view.setText("");
				printTitle();
				order.printOnTextArea(view);
				printFinish(orderNumber);
			} else if (current.equalsIgnoreCase("NUOVO") || current.equalsIgnoreCase("New")) {
				order = new Order(orderNumber);
				view.setText("");
				printTitle();
				printDateTime(orderNumber);
			} else if (current.equalsIgnoreCase("STAMPA")) {
				orderHistory.addOrder(order);	
				view.setText("");
				printTitleSectors("RIEPILOGO - NON CONSEGNARE");
				order.printOnTextArea(view);
				printFinish(orderNumber);
				view.setFont(printFont);
				if (carry.isSelected())
					carry();
				print(view.getPrintable(new MessageFormat(""), null), true);
				printInternalReceipt(orderNumber);
				view.setFont(fontArea);
				orderNumber++;
				if (autosave.isSelected()) {
					if (firstSave) {
						text = (String) JOptionPane.showInputDialog(null, "Inserisci un nome per il file: ", "Storico ordini", JOptionPane.QUESTION_MESSAGE);
						firstSave = false;
					}
					updateFile(text);
				}
			} else if (current.equalsIgnoreCase("Gelato")) {
				icf.setVisible(true);
			} else if (current.equalsIgnoreCase("OK")) {
				icf.setVisible(false);
			} else if (current.equalsIgnoreCase("Save")) {	
				if (firstSave) {
					text = (String) JOptionPane.showInputDialog(null, "Inserisci un nome per il file: ", "Storico ordini", JOptionPane.QUESTION_MESSAGE);
					firstSave = false;
				}
				updateFile(text);
			} else if (current.equalsIgnoreCase("Exit")) {
				System.exit(0);
			} else if (current.equalsIgnoreCase("?")) {
				JOptionPane.showMessageDialog(null, "Help!");
			} else if (current.equalsIgnoreCase("Set order number")) {
				boolean isANumber = false;
				while (!isANumber) {	 
					try {
						isANumber = true;
						String text = (String) JOptionPane.showInputDialog(null, "Inserisci da quale numero vuoi partire: ", "Set numero ordine", JOptionPane.QUESTION_MESSAGE);
						if (text.equals("")) {
							throw new NullPointerException();
						}
						orderNumber = Integer.parseInt(text);
						if (orderNumber > 999 || orderNumber < 0) {
							throw new NotSufficientImportException();
						}
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "ERRORE: tipo di dato non corretto", "ERRORE!", JOptionPane.ERROR_MESSAGE);
						isANumber = false;
					} catch (NullPointerException e2) {
						
					} catch (NotSufficientImportException e2) {
						JOptionPane.showMessageDialog(null, "ERRORE: scegli un valore tra 0 e 999", "ERRORE!", JOptionPane.ERROR_MESSAGE);
						isANumber = false;
					}
				}
			} else {	
				view.setText("");
				order.add(menu.getProduct(current));
				printTitle();
				order.printOnTextArea(view);
				printDateTime(orderNumber);
			}			
		}
		
		public void print(final Printable printable) {
	        print(printable, true);
	    }

	    public void print(final Printable printable, final boolean portrait) {
	        print(printable, portrait, new Insets(5, 5, 5, 5));
	    }

	    public void print(final Printable printable, final boolean portrait, final Insets insets) {
	        PrinterJob pjob = PrinterJob.getPrinterJob();
	        pjob.setPrintable(printable);
	        // create an attribute set to store attributes from the print dialog
	        if (attr == null) {
	            attr = new HashPrintRequestAttributeSet();
	            float leftMargin = insets.left;
	            float rightMargin = insets.right;
	            float topMargin = insets.top;
	            float bottomMargin = insets.bottom;
	            if (portrait) {
	                attr.add(OrientationRequested.PORTRAIT);
	            } else {
	                attr.add(OrientationRequested.LANDSCAPE);
	                leftMargin = insets.top;
	                rightMargin = insets.bottom;
	                topMargin = insets.right;
	                bottomMargin = insets.left;
	            }
	            attr.add(MediaSizeName.ISO_A8);
	            MediaSize mediaSize = MediaSize.ISO.A8;
	            float mediaWidth = mediaSize.getX(Size2DSyntax.MM) + 28;
	            float mediaHeight = mediaSize.getY(Size2DSyntax.MM) + 28;
	            attr.add(new MediaPrintableArea(
	                    leftMargin, topMargin,
	                    (mediaWidth - leftMargin - rightMargin),
	                    (mediaHeight - topMargin - bottomMargin), Size2DSyntax.MM));
	        }
	        if (pjob.printDialog(attr)) {
	        	try {
	                pjob.print(attr);
	            } catch (PrinterException ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	   
		private void printTitle() {
			view.append("      FESTA DELLA BIRRA\n         Bernareggio\n\n                        EURO\n");
		}
		
		private void printTitleSectors(String sector) {
			view.append("      FESTA DELLA BIRRA\n         Bernareggio\n\n" + sector + "\n\n");
		}
		
		private void printDateTime(int orderNumber) {
			DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
			Date date = new Date();
			view.append("\n");
			if (orderNumber < 10)	
				view.append(dateFormat.format(date) + "       B" + orderNumber + "\n");
			else if (orderNumber >= 10 && orderNumber < 100)
				view.append(dateFormat.format(date) + "      B" + orderNumber + "\n");
			else if (orderNumber >= 100)
				view.append(dateFormat.format(date) + "     B" + orderNumber + "\n");
		}
		
		private void printFinish(int orderNumber) {
			view.append("\n");
			if (order.getTotalCost() >= 100)
				view.append("TOTALE EURO           " + order.getTotalCost() + "0\n");
			else if (order.getTotalCost() >= 10 && order.getTotalCost() < 100)
				view.append("TOTALE EURO            " + order.getTotalCost() + "0\n");
			else 
				view.append("TOTALE EURO             " + order.getTotalCost() + "0\n");
			printDateTime(orderNumber);
		}
		
		private void setInternalReceipt() {
			for (int i = 0; i < order.getItems(); i++) {
				if (menu.getSector(order.getListProduct(i).getName()).equals("pizza")) {
					order.addPizzaList(order.getListProduct(i));
				} else if (menu.getSector(order.getListProduct(i).getName()).equals("birra")) {
					order.addBirraList(order.getListProduct(i));
				} else if (menu.getSector(order.getListProduct(i).getName()).equals("bar")) {
					order.addBarList(order.getListProduct(i));
				} else if (menu.getSector(order.getListProduct(i).getName()).equals("cucina")) {
					order.addCucinaList(order.getListProduct(i));
				} else if (menu.getSector(order.getListProduct(i).getName()).equals("gelati")) {
					order.addGelatiList(order.getListProduct(i));
				}
			}
		}
		
		private void printInternalReceipt(int orderNumber) {	
			setInternalReceipt();
			if (order.getPizzaItems() > 0) {
				view.setText("");
				printTitleSectors("PIZZERIA");
				order.printPizzaOnTextArea(view);
				printFinish(orderNumber);
				view.setFont(printFont);
				print(view.getPrintable(new MessageFormat(""), null), true);
			} 
			if (order.getBirraItems() > 0 && order.getBarItems() == 0) {
				view.setText("");
				printTitleSectors("BAR");
				order.printBirraOnTextArea(view);
				printFinish(orderNumber);
				view.setFont(printFont);
				print(view.getPrintable(new MessageFormat(""), null), true);
			} 
			if (order.getBarItems() > 0 && order.getBirraItems() == 0) {
				view.setText("");
				printTitleSectors("BAR");
				order.printBarOnTextArea(view);
				printFinish(orderNumber);
				view.setFont(printFont);
				print(view.getPrintable(new MessageFormat(""), null), true);
			} 
			if (order.getBarItems() > 0 && order.getBirraItems() > 0) {
				view.setText("");
				printTitleSectors("BAR");
				order.printBarOnTextArea(view);
				order.printBirraOnTextArea(view);
				printFinish(orderNumber);
				view.setFont(printFont);
				print(view.getPrintable(new MessageFormat(""), null), true);
			}
			if (order.getCucinaItems() > 0) {
				view.setText("");
				printTitleSectors("CUCINA");
				order.printCucinaOnTextArea(view);
				printFinish(orderNumber);
				view.setFont(printFont);
				print(view.getPrintable(new MessageFormat(""), null), true);
			}
			if (order.getGelatiItems() > 0) {
				view.setText("");
				printTitleSectors("BAR");
				order.printGelatiOnTextArea(view);
				printFinish(orderNumber);
				view.setFont(printFont);
				print(view.getPrintable(new MessageFormat(""), null), true);
			}
			view.setText("");
			printTitle();
			order.printOnTextArea(view);
			printFinish(orderNumber);
		}
		
		private void updateFile(String filename) {
			Writer writer = null;
			try {
				writer = new BufferedWriter(new OutputStreamWriter(
					  new FileOutputStream(filename + ".csv"), "utf-8"));
				writer.write("\tpizza\tbirra\tbar\tcucina\tgelati\t\ttotale\r\n");
				for (int i = 0; i < orderHistory.getOrders(); i++) {	
					writer.write(orderHistory.printOrders(i));
				}
				writer.write("\r\n\t" + orderHistory.getOrdersPizzaTotal());
				writer.write("\t" + orderHistory.getOrdersBirraTotal());
				writer.write("\t" + orderHistory.getOrdersBarTotal());
				writer.write("\t" + orderHistory.getOrdersCucinaTotal());
				writer.write("\t" + orderHistory.getOrdersGelatiTotal());
				writer.write("\t\t" + orderHistory.getTotalissimo());
			} catch (IOException ex) {
			  // report
			} finally {
			   try {writer.close();} catch (Exception ex) {}
			}
		}
		
		private void carry() {
			double number = 0;
			boolean isANumber = false;
			while (!isANumber) {	 
				try {
					isANumber = true;
					String text = (String) JOptionPane.showInputDialog(null, "Importo pagato: ", "Calcola resto", JOptionPane.QUESTION_MESSAGE);
					if (text.equals("")) {
						throw new NullPointerException();
					}
					number = Double.parseDouble(text);
					if (number-order.getTotalCost() < 0) {
						throw new NotSufficientImportException();
					}
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "ERRORE: tipo di dato non corretto", "ERRORE!", JOptionPane.ERROR_MESSAGE);
					isANumber = false;
				} catch (NullPointerException e) {					
				} catch (NotSufficientImportException e) {
					JOptionPane.showMessageDialog(null, "Importo non sufficiente!", "ERRORE!", JOptionPane.ERROR_MESSAGE);
					isANumber = false;
				}
			}	
			String carry = String.valueOf(Math.rint((number-order.getTotalCost())*10)/10); 
			JOptionPane.showMessageDialog(null, "Resto: " + carry + "0 €", "Calcola resto", JOptionPane.INFORMATION_MESSAGE);
		}
	}
	
	class IceCreamFrame extends JFrame {
		private JButton[] gelati = new JButton[4];
		private JButton ok = new JButton("OK");
		private JPanel north = new JPanel();
		private JPanel south = new JPanel();
		
		public IceCreamFrame() {
			super("Gelati");
			Container c = getContentPane();
			Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			this.setLocation(dim.width/2-this.getSize().width/2 - 150, dim.height/2-this.getSize().height/2 - 100);
					
			north.setLayout(new GridLayout(2,2));
			south.setLayout(new FlowLayout());
			c.setLayout(new BorderLayout());
			for (int i = 0; i < gelati.length; i++) {
				gelati[i] = new JButton(menu.getGelatiProduct(i).getName());
				gelati[i].setBackground(new Color(204, 255, 255));
				north.add(gelati[i]);
				gelati[i].addActionListener(listener);
				gelati[i].setFont(buttonRead);
			}
			
			south.add(ok);
			ok.addActionListener(listener);
			
			c.add(south, BorderLayout.SOUTH);
			c.add(north, BorderLayout.NORTH);
			
			c.setSize(300,300);
			pack();
			setVisible(false);
		}
	}	
}	

public class OrderLine {
	public static void main(String[] args) {
		int number = 0;
 		boolean isANumber = false;
		while (!isANumber) {	 
			try {
				isANumber = true;
				String text = (String) JOptionPane.showInputDialog(null, "Inserisci da quale numero vuoi partire: ", "Set numero ordine", JOptionPane.QUESTION_MESSAGE);
				if (text.equals("")) {
					throw new NullPointerException();
				}
				number = Integer.parseInt(text);
				if (number > 999 || number < 0) {
					throw new NotSufficientImportException();
				}
			} catch (NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "ERRORE: tipo di dato non corretto", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				isANumber = false;
			} catch (NullPointerException e) {
				System.exit(0);
			} catch (NotSufficientImportException e) {
				JOptionPane.showMessageDialog(null, "ERRORE: scegli un valore tra 0 e 999", "ERRORE!", JOptionPane.ERROR_MESSAGE);
				isANumber = false;
			}
		}	
		@SuppressWarnings("unused")
		MyFrame mf = new MyFrame(number);
	}
}