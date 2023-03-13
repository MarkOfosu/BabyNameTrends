// NameRecord.java
 /*
 *Encapsulates the data for a name:
 *the name string and its rank numbers over the years
 *@autor: Mark Ofosu
 *@date: 12/15/2021
 */
public class NameRecord {
	public static final int START = 1900;
	public static final int DECADES = 11;
	private String name;
	private int[] ranks;
	

	public NameRecord(String line) {
		String[] data = line.split(" ");
		this.name = data[0];
		this.ranks = new int[DECADES];

		for (int i = 0; i < DECADES; i++) {
			ranks[i] = Integer.parseInt(data[i+1]);
		}
	}

	public String getName() {
		return this.name;
	}

	public int getRank(int decade) {
		return this.ranks[decade];
	}

	public int bestYear () {
		int highest = this.ranks[0];
		int index = 0;
		for (int i = 1; i < DECADES; i++) {
			if (this.ranks[i] > highest) {
				highest = this.ranks[i];	
				index = i;
			}	
		}
	
		return START + (10 * index);
	}
}







	