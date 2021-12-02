// --== CS400 File Header Information ==--
// Name: Henry Li
// Email: hli779@wisc.edu
// Team: RED
// Group: KC
// TA: Keren
// Lecturer: Florian Heimerl
// Notes to Grader: None

public class KVPair<KeyType, ValueType> {
    KeyType Key;
    ValueType Value;
    /**
     * Creates an instance of KVPair with the data of Key and Value
     *
     * @param Key the identifier of the given value
     * @param Value the data of the value type
     */
    KVPair(KeyType Key, ValueType Value) {
        this.Key = Key;
        this.Value = Value;
    }

    /**
     * The getter method of key
     *
     * @return the key of the data
     */
    public  KeyType getKey(){
        return Key;
    }

    /**
     * The getter method of value
     *
     * @return the value of the data
     */
    public ValueType getValue(){
        return Value;
    }
}

