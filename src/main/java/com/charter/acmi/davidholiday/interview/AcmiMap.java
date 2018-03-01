package com.charter.acmi.davidholiday.interview;

import javafx.util.Pair;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        recordOperation(OPERATION_ADD, v);
    }


    public void add(K k, V v) {

        // check to see if there's already a matching key in the kvList
        boolean isReallyModify = exists(k);

        if (isReallyModify) {
            modify(k, v);
        } else {
            Pair<K, V> kvPair = new Pair<>(k, v);
            kvList.add(kvPair);
            recordOperation(OPERATION_ADD, v);
        }

    }


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
            recordOperation(OPERATION_MODIFY, v);
        }
    }


    public void delete(K k) {
        Pair<Boolean, Integer> findResultPair = find(k);
        boolean exists = findResultPair.getKey();
        if (exists) {
            Integer existingPairIndex = findResultPair.getValue();
            kvList.remove(existingPairIndex);
        }
    }


    public boolean exists(K k) {
        Pair<Boolean, Integer> findResults = find(k);
        return findResults.getKey();
    }


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

    private void recordOperation(String operation, V value) {
        Pair<String, String> operationPair = new Pair<>(operation, value.toString());
        operationStack.push(operationPair);
    }


    public void printDelta() {
        Iterator<Pair<String, String>> operationStackIterator = operationStack.iterator();
        while (operationStackIterator.hasNext()) {
            Pair<String, String> operationPair = operationStackIterator.next();
            System.out.println(operationPair.getKey() + " " + operationPair.getValue());
        }
    }

}
