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
	private double LOAD_FACTOR = .75;
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
	 * Adds an item to the table, resizes if it has to
	 * 
	 * @param item the object you want to add
	 * @return true 
	 */
	public boolean add(E item) {
		Integer key = item.hashCode();
		if (key == null) {
		       throw new IllegalArgumentException("Key cannot be null");
		    }
		int index = key.toString().charAt(0) % table.length;
		int step = key.toString().charAt(1) % (table.length - 2) + 1;

		while (table[index] != null && !(table[index].key==(key))) {
		      index = (index + step) % table.length;
		    }

		if (table[index] == null) {
		        table[index] = new HashEntry<>(key, item);
		        size++;
		        double loadFactor = (double) size / table.length;
		        if (loadFactor > LOAD_FACTOR) {
		            resize();
		        }
		    } 
			else {
		        table[index].value = item;
		    }
		    return true;
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
