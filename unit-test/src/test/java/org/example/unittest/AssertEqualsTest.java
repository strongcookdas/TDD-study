package org.example.unittest;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

public class AssertEqualsTest {
    @Test
    void assertEqualsExample1Test() {
        int actual = 2;
        int expected = 2;
        assertEquals(expected, actual);
    }

    private String toUpperCase(String name) throws NullPointerException {
        if (name == null) throw new NullPointerException();
        return name.toUpperCase();
    }

    @Test
    void toUpperCaseThrowNullPointExceptionTrueTest() {
        assertThrows(NullPointerException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                toUpperCase(null);
            }
        });
    }

    @Test
    void toUpperCaseThrowNullPointExceptionTest() {
        try {
            String name = toUpperCase("JIMIN");
            fail();
            assertEquals("JIMIN", name);
        } catch (NullPointerException e) {

        }
    }
}
