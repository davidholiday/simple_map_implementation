package com.charter.acmi.davidholiday.interview;

import javafx.util.Pair;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;


/**
 * basic implementation of a map data structure
 *
 * @param <K>
 * @param <V>
 */
public class AcmiMap<K extends Object, V extends Object> {

    // keys for the operations stack
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_MODIFY = "MODIFY";

    // the operations stack and internal representation of the map
    private final Stack<Pair<String, String>> operationStack = new Stack<>();
    private final List<Pair<K, V>> kvList;

    /**
     * constructor
     *
     * @param
     * @param
     */
    public AcmiMap(K k, V v) {
        Pair<K, V> initialPair = new Pair<>(k, v);
        kvList = Stream.of(initialPair).collect(Collectors.toList());
        recordOperation(OPERATION_ADD, k, v);
    }


    /**
     * adds a value to the map. if the kv pair already exists executes a modify operation
     *
     * @param k
     * @param v
     */
    public void add(K k, V v) {

        // check to see if there's already a matching key in the kvList
        boolean isReallyModify = exists(k);

        if (isReallyModify) {
            modify(k, v);
        } else {
            Pair<K, V> kvPair = new Pair<>(k, v);
            kvList.add(kvPair);
            recordOperation(OPERATION_ADD, k, v);
        }

    }


    /**
     * modifies an existing kv pair in the map. if kv pair doesn't exist executes an add operation
     *
     * @param k
     * @param v
     */
    public void modify(K k, V v) {
        // check to see if there's already a matching key in the kvList
        Pair<Boolean, Integer> findResultPair = find(k);
        boolean isReallyAdd = !findResultPair.getKey();

        if (isReallyAdd) {
            add(k, v);
        } else {
            Integer existingPairIndex = findResultPair.getValue();
            Pair<K, V> existingKVPair = kvList.get(existingPairIndex);
            Pair<K, V> newKVPair = new Pair<>(existingKVPair.getKey(), v);
            kvList.set(existingPairIndex, newKVPair);
            recordOperation(OPERATION_MODIFY, k, v);
        }
    }


    /**
     * removes a kv pair from the list, if it exists. if it doesn't nothing happens.
     *
     * @param k
     */
    public void delete(K k) {
        Pair<Boolean, Integer> findResultPair = find(k);
        boolean exists = findResultPair.getKey();
        if (exists) {
            int existingPairIndex = findResultPair.getValue();
            V v = kvList.get(existingPairIndex).getValue();
            kvList.remove(existingPairIndex);
            recordOperation(OPERATION_DELETE, k, v);
        }

    }


    /**
     * returns a given value for a key, if it exists. if it doesn't exist null is returned.
     *
     * @param k
     * @return
     */
    public V get(K k) {
        V returnValue = null;
        Pair<Boolean, Integer> findResultPair = find(k);
        boolean exists = findResultPair.getKey();
        if (exists) {
            Integer existingPairIndex = findResultPair.getValue();
            Pair<K, V> existingPair = kvList.get(existingPairIndex);
            returnValue = existingPair.getValue();
        }

        return returnValue;
    }


    /**
     * returns the number of pairs in the map
     *
     * @return
     */
    public int getMapSize() {
        return kvList.size();
    }


    /**
     * returns a boolean indicating whether or not there's a given key in the map
     *
     * @param k
     * @return
     */
    public boolean exists(K k) {
        Pair<Boolean, Integer> findResults = find(k);
        return findResults.getKey();
    }


    /**
     * returns whether or not a kv pair exists and its index in the kvList
     *
     * @param k
     * @return
     */
    private Pair<Boolean, Integer> find(K k) {
        Boolean found = false;
        Integer index = -1;
        for (int i = 0; i < kvList.size(); i ++) {
            Pair<? extends K, ? extends V> kvPair = kvList.get(i);
            if (kvPair.getKey() == k) {
                found = true;
                index = i;
            }
        }

        return new Pair<>(found, index);
    }


    /**
     * records a given operation to the operation stack
     *
     * @param operation
     * @param key
     * @param value
     */
    private void recordOperation(String operation, K key, V value) {
        String operationArgument = key.toString() + "->" + value.toString();
        Pair<String, String> operationPair = new Pair<>(operation, operationArgument);
        operationStack.push(operationPair);
    }


    /**
     * prints the operation stack
     */
    public void printDelta() {
        Iterator<Pair<String, String>> operationStackIterator = operationStack.iterator();
        while (operationStackIterator.hasNext()) {
            Pair<String, String> operationPair = operationStackIterator.next();
            System.out.println(operationPair.getKey() + " " + operationPair.getValue());
        }
    }

}
