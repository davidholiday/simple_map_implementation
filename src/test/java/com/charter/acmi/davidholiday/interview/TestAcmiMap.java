package com.charter.acmi.davidholiday.interview;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

public class TestAcmiMap {

    AcmiMap<String, String> acmiMap = new AcmiMap<>("foo", "bar");

    @Test
    public void checkAdd() {
        acmiMap.add("space", "dolphin");

        // ensure we didn't overwrite what was already there
        Assert.assertTrue("original value should still exist!",
                          acmiMap.exists("foo"));

        // ensure we didn't overwrite what was already there
        Pair<String, String> expectedPair = new Pair<>("space", "dolphin");
        Assert.assertEquals("what we added should be in the map!",
                            expectedPair,
                            acmiMap.get("space"));

        // ensure there are the correct number of elements in the map
        Assert.assertEquals("map size isn't what it should be!",
                            2,
                            acmiMap.getMapSize());

    }

}
