// NameFrame.java
import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
/*
 * Frame that contains the NameComponent and manages the overall application.
 *Stores the overall collection of NameRecords, installs
 *the NameComponent, deals with the controls and messaging the
 *NameComponent.
 *@autor: Mark Ofosu
 *@date: 12/15/2021
*/
class NameFrame extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// Controls if main() does a doSearch()
	public static final boolean SEARCH = false;
	static ArrayList<NameRecord> nameRecords;
	private static NameComponent nameComp;
	private JButton GraphButton;
	private JButton ClearAllButton;
	private JButton ClearOnerButton;
	private JButton SearchButton;
	private JTextField textField;
	int width, height, rows, columns;
	public static final int SIZE = 600;
	public static final int SPACE = 20;
	
	public NameFrame(int w, int h, int rows, int columns) {
		setTitle("Baby Name Trends");
		setSize(w, h);
		setPreferredSize(new Dimension(w, h));
		
		Container container = getContentPane( );
		container.setLayout(new BorderLayout( ));
		
		nameComp = new NameComponent();
		container.add(nameComp, BorderLayout.CENTER);
//		nameComp.addKeyListener((KeyListener) this); // adding the KeyListener
		nameComp.setFocusable(true); // setting focusable to true
		
		JPanel panel = new JPanel( );
		container.add(panel, BorderLayout.SOUTH);
	
		
		textField = new JTextField(15); 
		textField.setFocusable(true); // focusable should be
		panel.add(textField); 
			
		GraphButton = new JButton("Graph");
		GraphButton.addActionListener(this);
		GraphButton.setFocusable(false); // focusable should be
		panel.add(GraphButton); // false for everything else
		
		ClearAllButton = new JButton("Clear All");
		ClearAllButton.addActionListener(this);
		ClearAllButton.setFocusable(true); // focusable should be
		panel.add(ClearAllButton); // false for everything else
		
		ClearOnerButton = new JButton("Clear One");
		ClearOnerButton.addActionListener(this);
		ClearOnerButton.setFocusable(true); // focusable should be
		panel.add(ClearOnerButton); // false for everything else
		
		
		SearchButton = new JButton("Search");
		SearchButton.addActionListener(this);
		SearchButton.setFocusable(true); // focusable should be
		panel.add(SearchButton); // false for everything else
		
	
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nameComp.requestFocusInWindow( );
		pack( );
		setVisible(true);
		repaint();
	}
	
	public static void main(String[] args) {
		// new graph("Draw Grids", 600, 600, 1, 11).setVisible(true);
		NameFrame frame = new NameFrame(SIZE, SIZE, 1, 11);
		nameRecords = new ArrayList<>();
		try {
			frame.read("names-data.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		nameComp.addName(nameRecords.get(0));
		
		if (SEARCH) {
			frame.doSearch();
		}			
	}
	
	public void read(String filename) throws IOException {
		// read file
		BufferedReader in;
		try {
			in = new BufferedReader(new FileReader(filename));
			String line;
			while ((line = in.readLine()) != null) {
			   NameRecord record = new NameRecord(line);
			   nameRecords.add(record);
			
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void search(String target) {
		for (NameRecord nameRecord : nameRecords) {
			if (nameRecord.getName().contains(target)) {
				System.out.println(nameRecord.getName() + "  " + nameRecord.bestYear());
			}
		}
	}

	public void doSearch() {
		String name = JOptionPane.showInputDialog("Search names");

		if (name == null) {
			return;
		}

		this.search(name);
	}
	
	public NameRecord findName(String name) {
		for (NameRecord nameRecord : nameRecords) {
			if (nameRecord.getName().equalsIgnoreCase(name)) {
				return nameRecord;
			}
		}

		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource( ).equals(GraphButton)) {
			NameRecord record = findName(textField.getText());
			if (record != null) {
				nameComp.addName(record);
				textField.setText("");
			} else {
				Toolkit.getDefaultToolkit().beep();
			}
		} else if (e.getSource().equals(ClearAllButton)) {
			nameComp.clearAll();
		} else if (e.getSource().equals(ClearOnerButton)) {
			nameComp.clearOne();
		} else if (e.getSource().equals(SearchButton)) {
			this.search(textField.getText());
			textField.setText("");
		}
	}
	   
	public void paintComponent(Graphics g) {    
		width = getSize().width;
		height = getSize().height;
		System.out.println("one");

		// g.drawLine(0, SPACE, width, SPACE);
		// g.drawLine(0, height - SPACE, width, height - SPACE);
		int htOfRow = height / (rows);
        for (int k = 0; k < rows; k++) {
            g.drawLine(0, k * htOfRow, width, k * htOfRow);
        }

		int wdOfRow = width / (columns);
		for ( int k = 0; k < columns; k++) {
			g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
		}
	}
}
	  
		  
		    

	    
















































