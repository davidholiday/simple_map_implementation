package com.charter.acmi.davidholiday.interview;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


/**
 * checks operation of AcmiMap
 */
public class TestAcmiMap {

    private AcmiMap<String, String> acmiMap;

    @Before
    public void init() {
        acmiMap = new AcmiMap<>("foo", "bar");
    }


    @Test
    public void checkAdd() {
        acmiMap.add("space", "dolphin");

        // ensure we didn't overwrite what was already there
        Assert.assertTrue("original value should still exist!",
                          acmiMap.exists("foo"));

        // ensure we didn't overwrite what was already there
        String expectedValue = "dolphin";
        Assert.assertEquals("what we added should be in the map!",
                            expectedValue,
                            acmiMap.get("space"));

        // ensure there are the correct number of elements in the map
        Assert.assertEquals("map size isn't what it should be!",
                            2,
                            acmiMap.getMapSize());

    }


    @Test
    public void checkAddThatIsReallyModify() {
        acmiMap.add("foo", "dolphin");

        // ensure what was there isn't anymore
        Pair<String, String> noNoPair = new Pair<>("foo", "bar");
        Assert.assertNotEquals("original value should not still exist!",
                               noNoPair,
                               acmiMap.get("foo"));

        // ensure we did overwrite what was already there
        String expectedValue = "dolphin";
        Assert.assertEquals("what we modified should be in the map!",
                            expectedValue,
                            acmiMap.get("foo"));

        // ensure there are the correct number of elements in the map
        Assert.assertEquals("map size isn't what it should be!",
                            1,
                            acmiMap.getMapSize());
    }


    @Test
    public void checkModify() {
        acmiMap.modify("foo", "dolphin");

        // ensure what was there isn't anymore
        String noNoValue = "bar";
        Assert.assertNotEquals("original value should not still exist!",
                                noNoValue,
                                acmiMap.get("foo"));

        // ensure we did overwrite what was already there
        String expectedValue = "dolphin";
        Assert.assertEquals("what we modified should be in the map!",
                            expectedValue,
                            acmiMap.get("foo"));

        // ensure there are the correct number of elements in the map
        Assert.assertEquals("map size isn't what it should be!",
                            1,
                            acmiMap.getMapSize());
    }


    @Test
    public void checkModifyThatIsReallyAdd() {
        acmiMap.modify("space", "dolphin");

        // ensure we didn't overwrite what was already there
        Assert.assertTrue("original value should still exist!",
                acmiMap.exists("foo"));

        // ensure we didn't overwrite what was already there
        String expectedValue = "dolphin";
        Assert.assertEquals("what we added should be in the map!",
                            expectedValue,
                            acmiMap.get("space"));

        // ensure there are the correct number of elements in the map
        Assert.assertEquals("map size isn't what it should be!",
                            2,
                            acmiMap.getMapSize());

    }


    @Test
    public void checkDelete() {

        // check delete against non-existent key
        acmiMap.delete("yogurtfishpants");

        // ensure there are the correct number of elements in the map
        Assert.assertEquals("map size isn't what it should be!",
                            1,
                            acmiMap.getMapSize());


        // check delete against existent key
        acmiMap.delete("foo");

        // ensure there are the correct number of elements in the map
        Assert.assertEquals("map size isn't what it should be!",
                            0,
                            acmiMap.getMapSize());

    }


    @Test
    public void checkGet() {

        // check get against non-existent key
        String shouldBeNull = acmiMap.get("spockismyhomeboy");
        Assert.assertNull("return value for a non existent key should be null!",
                          shouldBeNull);

        // check get against existent key
        String expectedValue = "bar";
        Assert.assertEquals("what we fetched should be in the map!",
                            expectedValue,
                            acmiMap.get("foo"));

    }


}






