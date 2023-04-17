package lab10;

/**
 * HashTable class
 * @author Seth, Kyrin, Abbas
 * @version
 */
public class HashTable<E> {
	private HashEntry[] table = new HashEntry[500];
	
	private class HashEntry<F> {
		public F key;
		public E value;
		public HashEntry(F key, E value) {
			this.key = key;
			this.value = value;
		}
		public String toString() {
			return key + "=" + value.toString();
		}
	}
	/**
	 * Default constructor
	 */
	public HashTable() {
		
	}
	/**
	 * Adds an item to the table.  Returns true if successful, and false otherwise
	 * @param item
	 * @return
	 */
	public boolean add(E item) {
		return false;
	}
	/**
	 * Finds an item in the table.  Returns the item if successful, and null otherwise.
	 * @param item
	 * @return
	 */
	public E find(E item) {
	    if (item == null) {
	        return null;
	    }

	    int hashValue = getHashValue(item.hashCode());
	    while (table[hashValue] != null) {
	        if (table[hashValue].value.equals(item)) {
	            return table[hashValue].value;
	        }
	        hashValue = (hashValue + getSecondHashValue(item.hashCode())) % table.length;
	    }
	    
	    return null;
	}
	/**
	 * Helper method used to resize the table when limit is reached.
	 */
	private void resize() {
		HashEntry[] newTable = new HashEntry[table.length * 2];
		for (int i = 0; i < table.length; i++) {
			newTable[i] = table[i];
		}
		table = newTable;
	}
	public static void main(String[] args) {
		
	}
}
