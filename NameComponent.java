
// NameComponent.java
import java.util.*;
import java.awt.*;
import javax.swing.*;

/*
 * NameComponent.
 *Maintains a collection of NameRecords and graphs their data.
 *@autor: Mark Ofosu
 *@date: 12/15/2021
 */
	
public class NameComponent extends JComponent  {
	public static int SIZE = 600;
	public static final Color[] COLORS = new Color[] {Color.BLACK, Color.RED, Color.BLUE, Color.DARK_GRAY};
		
	// Space at top and bottom of graph
	public static final int SPACE = 20;
	public static final int COLUMNS = 11;
	public ArrayList<NameRecord> ivar = new ArrayList<>();
	
	public NameComponent() {
		setPreferredSize(new Dimension(SIZE, SIZE));
		JFrame frame = new JFrame();
		frame.setPreferredSize(new Dimension(SIZE, SIZE));
		frame.setTitle(""); 
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true); 		                               
	}

	public void addName(NameRecord record) {
		this.ivar.add(record);
		repaint();
	}

	public void clearAll() {
		this.ivar.clear();
		repaint();
	}

	public void clearOne() {
		if (this.ivar.size() > 0) {
			this.ivar.remove(0);
			repaint();
		}
	}

	public void paintComponent(Graphics g) {    
		int width = getSize().width;
		int height = getSize().height;
		System.out.println("one");

		g.drawLine(0, SPACE, width, SPACE);
		g.drawLine(0, height - SPACE, width, height - SPACE);

		int wdOfRow = width / (COLUMNS);
		HashMap<String, Point> lastPoints = new HashMap<>();
		HashMap<String, Color> nameColors = new HashMap<>();
		int realHeight = height - 40;
		for (int k = 0; k < COLUMNS; k++) {
			g.drawLine(k * wdOfRow, 0, k * wdOfRow, height);
			g.drawString("" + (1900 + (10 * k)), k * wdOfRow, height);
			for (NameRecord record : this.ivar) {
				if (nameColors.containsKey(record.getName())) {
					g.setColor(nameColors.get(record.getName()));
				} else {
					Color color = COLORS[nameColors.size() % COLORS.length];
					nameColors.put(record.getName(), color);
					g.setColor(color);
				}

				int rank = record.getRank(k);
				int y = 20 + realHeight;
				if (rank > 0)
					y = 20 + ((realHeight * rank) / 1000);
				g.drawRect(k * wdOfRow, y, 1, 1);
				g.drawString(record.getName() + "(" + rank + ")", k * wdOfRow + 1, y);
				if (lastPoints.containsKey(record.getName())) {
					Point lastPoint = lastPoints.get(record.getName());
					g.drawLine((int)lastPoint.getX(), (int)lastPoint.getY(), k * wdOfRow, y);
				}
				lastPoints.put(record.getName(), new Point(k * wdOfRow, y));
			}
		}
	}
}


