package com.charter.acmi.davidholiday.interview;

import javafx.util.Pair;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AcmiMap<K, V> {

    // keys for the operations stack
    private static final String OPERATION_ADD = "ADD";
    private static final String OPERATION_DELETE = "DELETE";
    private static final String OPERATION_MODIFY = "MODIFY";

    // the operations stack and internal representation of the map
    private final Stack<Pair<String, String>> operationStack = new Stack<>();
    private final List<Pair<? extends K, ? extends V>> kvList;

    /**
     * constructor
     *
     * @param
     * @param
     */
    public AcmiMap(K k, V v) {
        Pair<? extends K, ? extends V> initialPair = new Pair(k, v);
        kvList = Stream.of(initialPair).collect(Collectors.toList());
        recordOperation(OPERATION_ADD, v);
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
