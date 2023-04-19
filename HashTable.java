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
	 * Adds an item to the table. If the key already exists, updates the existing
	 * value. Returns true if the item was added or updated, false otherwise.
	 * 
	 * @param item the object you want to add or update
	 * @return true if the item was added or updated, false otherwise
	 */
	public boolean add(E item) {
		// Get the key for the item
		Integer key = item.hashCode();
		if (key == null) {
			throw new IllegalArgumentException("Key cannot be null");
		}

		// Calculate the initial index and step
		int index = key.toString().charAt(0) % table.length;
		int step = key.toString().charAt(1) % (table.length - 2) + 1;

		// Try to find an empty slot or a matching key
		boolean added = false;
		while (!added && table[index] != null) {
			if (table[index].key == key) {
				// Key already exists, update the value
				table[index].value = item;
				added = true;
			} else {
				// Keep looking with the next step
				index = (index + step) % table.length;
			}
		}

		// Add the item if an empty slot was found
		if (!added) {
			table[index] = new HashEntry<>(key, item);
			size++;
			double loadFactor = (double) size / table.length;
			if (loadFactor > LOAD_FACTOR) {
				resize();
			}
			added = true;
		}

		return added;
	}
	/**
	 * Kyrin
	 * Finds an item in the table.  Returns the item if successful, and null otherwise.
	 * @param item
	 * @return
	 */
	public E find(E item) {
        Integer key = item.hashCode();
        if (key == null || key == -1) {
            return null;
        }
        int index = key.toString().charAt(0) % table.length;
        int step = key.toString().charAt(1) % (table.length - 2) + 1;

        while (table[index] != null) {
            if (table[index].key == key) {
                return (E)table[index].value;
            }
            index = (index + step) % table.length;
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
		ArrayList<Student> studentList = new ArrayList<Student>();
		ArrayList<Student> findList = new ArrayList<Student>();
		try {
			File file = new File("StudentsNames.txt");
			Scanner scnr = new Scanner(file);
			while(scnr.hasNext()) {
				String name = scnr.next();
				Student s = new Student(name);
				studentList.add(s);
				Student s1 = new Student(name);
				findList.add(s1);
			}
		} catch (Exception e){
			System.out.println("File not found");
		}
		HashTable<Student> studentHash = new HashTable<Student>();
		for (Student s : studentList) {
			studentHash.add(s);
		}
		for (Student st : findList) {
			System.out.println(studentHash.find(st));
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
