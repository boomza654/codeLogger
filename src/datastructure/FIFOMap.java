package datastructure;
import java.util.*;

/**
 * A Map like HashMap that element are ordered by the :time that they got put in"
 * @author boomza654
 *
 * @param <K> Key type 
 * @param <V> Value type
 */
public class FIFOMap<K,V> implements Map<K,V>{

    private final HashMap<K,V> realMap;
    private final LinkedList<K> keys;
    /**
     * Construct new Empty FIFO MAP
     */
    public FIFOMap() {
        realMap=new HashMap<>();
        keys = new LinkedList<>();
    }
    /**
     * create defensive copy of other FIFOMap ( order are the same)
     * @param other
     */
    public FIFOMap(FIFOMap<? extends K, ? extends V> other) {
        keys = new LinkedList<>(other.keys);
        realMap = new HashMap<>(other.realMap);
    }
    /**
     * create defensve copy of other Map (order not guaranteed)
     * @param other
     */
    public FIFOMap(Map<? extends K, ? extends V> other) {
        keys = new LinkedList<>(other.keySet());
        realMap= new HashMap<>(other);
    }
    private void checkRep() {
        assert keys.size()==realMap.size();
        
    }
    @Override public int size() {
        checkRep();
        return keys.size();
    }

    @Override public boolean isEmpty() { return size()==0;   }

    @Override public boolean containsKey(Object key) {
        checkRep();
        return realMap.containsKey(key);
    }

    @Override public boolean containsValue(Object value) {
        checkRep();
        return realMap.containsValue(value);
    }

    @Override public V get(Object key) {
        checkRep();
        return realMap.get(key);
    }

    /**
     * Put the Key value pair into the map *if not present*
     * throws error if present
     * @param key to put
     * @param value to put
     * @throws RuntimeException if the key already exist
     * @return null
     */
    @Override public V put(K key, V value) {
        if(containsKey(key)) {
            throw new RuntimeException("Key " + key +" already existed in the map cant put");
        }
        keys.add(key);
        V result=realMap.put(key, value);
        checkRep();
        return result;
    }
    /**
     * Remove the Key value pair into the map *if  present*
     * throws error if not present
     * @param key to remove
     * @throws RuntimeException if the key not exist
     * @return value being removed
     */
    @Override public V remove(Object key) {
        if(!containsKey(key)) {
            throw new RuntimeException("Key " + key +" not existed in the map cant remove");
        }
        boolean listRemove=keys.remove(key);
        assert listRemove: "WTF REMOVE";
        V valueRemoved= realMap.remove(key);
        checkRep();
        return valueRemoved;
    }

    @Override public void putAll(Map<? extends K, ? extends V> m) {
        for(K key:m.keySet()) {
            put(key,m.get(key));
        }
        checkRep();
    }

    @Override public void clear() {
        keys.clear();
        realMap.clear();
        checkRep();
    }

    /**
     * The Key set is not modifiable
     */
    @Override public Set<K> keySet() {
        checkRep();
        return Collections.unmodifiableSet(realMap.keySet());
    }

    @Override public Collection<V> values() {
        checkRep();
        return realMap.values();
    }

    @Override public Set<Entry<K, V>> entrySet() {
        checkRep();
        return realMap.entrySet();
    }
    
    /**
     * @return the an unmodifiable list view of key in the Map ( in the order they are added)
     */
    public List<K> keyList(){
        return Collections.unmodifiableList(keys);
    }
    
    @Override public String toString() {
        List<String> outList = new LinkedList<>();
        for(K key: keys){
            outList.add(key.toString()+"="+realMap.get(key).toString());
        }
        return "{"+String.join(", ", outList)+"}";
    }
}
