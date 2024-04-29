package org.example.unittest;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AssertEqualsTest {
    @Test
    void assertEqualsExample1Test(){
        int actual = 2;
        int expected = 2;
        assertEquals(expected, actual);
    }
}
