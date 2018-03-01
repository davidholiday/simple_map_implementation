package com.charter.acmi.davidholiday.interview;

import org.junit.Test;

public class TestAcmiMap {

    AcmiMap<String, String> acmiMap = new AcmiMap<>("foo", "bar");

    @Test
    public void t1() {
        acmiMap.printDelta();
    }

}
