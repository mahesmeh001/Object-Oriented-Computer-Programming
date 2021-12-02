// --== CS400 File Header Information ==--
// Name: Henry Li
// Email: hli779@wisc.edu
// Team: RED
// Group: KC
// TA: Keren
// Lecturer: Florian Heimerl
// Notes to Grader: None

import java.util.LinkedList;
import java.util.NoSuchElementException;

public class HashTableMap<KeyType, ValueType> implements MapADT<KeyType, ValueType> {

    private int capacity;
    private LinkedList<KVPair>[] HTM;
    private int size;

    /**
     * Creates an instance of HashTableMap with a beginning capacity of the capacity parameter
     *
     * @param capacity the amount of space of the array
     */
    public HashTableMap(int capacity) {
        this.capacity = capacity;
        HTM = (LinkedList<KVPair>[]) new LinkedList[capacity];
        size = 0;
    }

    /**
     * Creates an instance of HashTableMap with a beginning capacity of 10.
     */
    public HashTableMap() {
        capacity = 10;
        HTM = (LinkedList<KVPair>[]) new LinkedList[capacity];
        size = 0;
    }

    /**
     * store new values in your hash table at the index corresponding to the
     * ( absolute value of your key's hashCode() ) modulus the HashTableMap's current capacity.
     *
     * @param key   the identifier of the given value
     * @param value the data of the value type
     * @return true if the value is stored in the table, false otherwise.
     */
    @Override
    public boolean put(KeyType key, ValueType value) {
        KVPair<KeyType, ValueType> Insert = new KVPair<KeyType, ValueType>(key, value);
        // Returns false if the key being passed through is null
        if (key == null)
            return false;
        int index = Math.abs(key.hashCode()) % capacity;
        if (HTM[index] != null) {
            // Returns false if the value already exists in the hashtable
            for (int i = 0; i < HTM[index].size(); i++) {
                if (HTM[index].get(i).getKey().equals(key)) {
                    return false;
                }
            }
        }
        if (HTM[index] == null)
            HTM[index] = new LinkedList<>();
        // Inserts the Key-value pair and increases the size
        HTM[index].add(Insert);
        size++;
        if (((double) size() / (double) capacity) >= .85) {
            rehash();
        }
        return true;
    }

    /**
     * Private helper method that, when the size of the array goes over the 85% threshold, resizes
     * and rehashes all values from previous array.
     */
    private void rehash() {
        // Doubles the capacity
        capacity = capacity * 2;
        // Resets the size
        size = 0;
        // Creates temporary arrays of the new capacity while copying all values from orig. table
        LinkedList<KVPair>[] Temp = new LinkedList[capacity];
        LinkedList<KVPair>[] Temp2 = new LinkedList[capacity];
        for (int n = 0; n < HTM.length; n++) {
            if (HTM[n] == null)
                HTM[n] = new LinkedList<>();
            Temp[n] = (LinkedList<KVPair>) HTM[n].clone();
        }
        HTM = Temp2;
        // Rehashes all the values from original array using the new capacity.
        for (int i = 0; i < Temp.length; i++) {
            if (Temp[i] == null)
                Temp[i] = new LinkedList<>();
            for (int n = 0; n < Temp[i].size(); n++) {
                put((KeyType) Temp[i].get(n).getKey(), (ValueType) Temp[i].get(n).getValue());
            }
        }
    }

    /**
     * Searches and returns a value given the corresponding key for said value
     *
     * @return the value of the corresponding key
     * @throws NoSuchElementException if the key cannot be found in the table
     */
    @Override
    public ValueType get(KeyType key) throws NoSuchElementException {
        int index = Math.abs(key.hashCode()) % capacity;
        if (containsKey(key)) {
            for (int i = 0; i < HTM[index].size(); i++) {
                if (HTM[index].get(i).getKey().equals(key))
                    return (ValueType) HTM[index].get(i).getValue();
            }
        }
        throw new NoSuchElementException("Cannot find the key.");
    }

    /**
     * Determines the size of the table by the number of elements in it
     *
     * @return size of hash table
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Checks if the key is in the current hash table
     *
     * @param key the identifier of the given value
     * @return true if the key is found, false otherwise
     */
    @Override
    public boolean containsKey(KeyType key) {
        int index = Math.abs(key.hashCode()) % capacity;
        if (HTM[index] == null)
            HTM[index] = new LinkedList<>();
        for (int i = 0; i < HTM[index].size(); i++) {
            if (HTM[index].get(i).getKey().equals(key))
                return true;
        }
        return false;
    }

    /**
     * Removes the data at the position of the given key
     *
     * @param key the identifier of the given value
     * @return the value of the data removed at the position or null if the key is not found
     */
    @Override
    public ValueType remove(KeyType key) {
        int index = Math.abs(key.hashCode()) % capacity;
        if (key == null || HTM[index] == null) {
            return null;
        }
        for (int i = 0; i < HTM[index].size(); ) {
            if (HTM[index].get(i).getKey().equals(key)) {
                size--;
                return (ValueType) HTM[index].remove(i).getValue();
            } else {
                i++;
            }
        }
        return null;
    }

    /**
     * Clears the entire table of all stored values
     */
    @Override
    public void clear() {
        for (int i = 0; i < capacity; i++)
            if (HTM[i] != null)
                HTM[i].clear();

        size = 0;
    }
}
