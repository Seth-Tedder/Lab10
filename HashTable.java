package lab10;

/**
 * HashTable class
 * @author Seth, Kyrin, Abbas
 * @version
 */
public class HashTable<E> {
	private HashEntry[] table = new HashEntry[500];
	
	private class HashEntry<E, F> {
		public F key;
		public E value;
		public HashEntry(int key, E value) {
			this.key = key;
			this.value = value;
		}
		public String toString() {
			return key + "=" + value.toString();
		}
	}
	
	public HashTable(E item) {
		
	}
	public boolean add() {
		return false;
	}
	public E find() {
		return null;
	}
	private void resize() {
		HashEntry[] newTable = new HashEntry[table.length * 2];
		for (int i = 0; i < table.length; i++) {
			newTable[i] = table[i];
		}
		table = newTable;
	}
}
