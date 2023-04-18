package lab10;
import java.util.*;
import java.io.*;
/**
 * HashTable class
 * @author Seth, Kyrin, Abbas
 * @version
 */
public class HashTable<E> {
	private HashEntry[] table = new HashEntry[500];
	private int size = 0;
	private int loadFactor = 75;
	private int count = 0;
	
	private class HashEntry<E> {
		public int key;
		public E value;
		public HashEntry(int key, E value) {
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
	public HashTable(E item) {
		this.add(item);
	}
	public HashTable() {
		
	}
	/**
	 * Abbas - 
	 * Adds an item to the table. Returns true if successful, and false otherwise
	 * 
	 * @param item the object you want to add
	 * @return
	 */
	public boolean add(E item) {
		if (count * 100 / table.length > loadFactor) {
			resize();
		}
		int hashValue = getHashValue(item.hashCode());
		while (table[hashValue] != null && !table[hashValue].value.equals(item)) {
			hashValue = (hashValue + getSecondHashValue(item.hashCode())) % table.length;
		}
		if (table[hashValue] == null) {
			table[hashValue] = new HashEntry(item.hashCode(), item);
			count++;
			size++;
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Abbas - 
	 * Double hashing function to calculate hash value.
	 * 
	 * @param id the key value of the object
	 * @return the double hashvalue of the object
	 */
	private int getHashValue(int id) {
		int hash1 = id % table.length;
		int hash2 = getSecondHashValue(id);
		int hashValue = hash1;

		while (table[hashValue] != null && table[hashValue].key != id) {
			hashValue = (hashValue + hash2) % table.length;
		}

		return hashValue;
	}

	/**
	 * Abbas - 
	 * Secondary hash function used for double hashing.
	 * 
	 * @param id the object's key value
	 * @return the secondary hash value 
	 */
	private int getSecondHashValue(int id) {
		return (id % (table.length - 2)) + 1;
	}
	/**
	 * Kyrin
	 * Finds an item in the table.  Returns the item if successful, and null otherwise.
	 * @param item
	 * @return
	 */
	public E find(E item) {
	    public E find(E item) {
	        int hashValue = getHashValue(item.hashCode());
	        if (table[hashValue] != null && table[hashValue].value.equals(item)) {
	            return table[hashValue].value;
	        }
	        return null;
	    }
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
		ArrayList<Student> studentList = new ArrayList<Student>();
		try {
			File file = new File("StudentsNames.txt");
			Scanner scnr = new Scanner(file);
			while(scnr.hasNext()) {
				Student s = new Student(scnr.next());
				studentList.add(s);
			}
		} catch (Exception e){
			System.out.println("File not found");
		}
		HashTable<Student> studentHash = new HashTable<Student>();
		for (Student s : studentList) {
			studentHash.add(s);
		}
	}
	/**
	 * Student class used for testing
	 * @author Seth Tedder
	 * @version 4/18/2023
	 */
	public static class Student {
		private String name;
		private int hashValue = -1;
		
		public Student(String name) {
			this.name = name;
		}
		public String toString() {
			return this.name;
		}
		/**
		 * Original code written by Abbas.  Changes made by Seth Tedder.
		 */
		@Override
		public int hashCode() {
			if (name == null || hashValue != -1) {
				return -1;
			}
			int hash1 = name.charAt(0);
			int hash2 = 0;
			if (name.length() > 1) {
				hash2 = name.charAt(1);
			}
			hashValue = hash1 + hash2;
			
			return hashValue;
		}
	}
}
