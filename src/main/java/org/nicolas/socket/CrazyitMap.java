package org.nicolas.socket;

import java.util.*;

/**
 * @author zorth
 */
public class CrazyitMap<K, V> {
    /**
     * Create a thread-safe HashMap
     */
    public Map<K, V> map = Collections.synchronizedMap(new HashMap<>());

    /**
     * Deletes the specified item based on value
     */
    public synchronized void removeByValue(Object value) {
        for (K key : map.keySet()) {
            if (map.get(key) == value || map.get(key).equals(value)) {
                map.remove(key);
                break;
            }
        }
    }

    /**
     * Gets a collection of all values
     */
    public synchronized Set<V> valueSet() {
        Set<V> result = new HashSet<>();
        //Adds all the values in the map to the collection
        map.forEach((key, value) -> result.add(value));
        return result;
    }

    /**
     *  Find key by value
     */
    public synchronized K getKeyByValue(V val) {
        //Iterate over the set of all keys
        for (K key : map.keySet()) {
            //
            if (map.get(key) == val || map.get(key).equals(val)) {
                return key;
            }
        }
        return null;
    }

    /**
     * Implement the PUT method
     */
    public synchronized V put(K key, V value) {
        //Iterate over the set of all value
        for (V val : valueSet()) {
            //
            //
            if (val.equals(value) && val.hashCode() == value.hashCode()) {
                throw new RuntimeException("Duplicate values are not allowed in Map");
            }
        }
        return map.put(key, value);
    }
}

