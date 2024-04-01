package org.example;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PasswordStrengthMeterTest {
    @Test
    void name() {

    }

    @Test
    void meetsAllCriteriaThenStrong() {
        PasswordStrengthMeter meter = new PasswordStrengthMeter();
        PasswordStrength result = meter.meter("ab12!@ABC");
        assertEquals(PasswordStrength.STRONG, result);
    }
}
