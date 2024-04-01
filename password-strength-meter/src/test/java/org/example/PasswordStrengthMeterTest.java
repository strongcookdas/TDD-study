package org.example;

import org.junit.jupiter.api.Test;

public class PasswordStrengthMeterTest {
    @Test
    void name() {

    }

    @Test
    void meetsAllCriteriaThenStrong() {
        PasswordStrengthMeter meter = new PasswordStrengthMether();
        PasswordStrength result = meter.meter("ab12!@ABC");
        assertEquals(PasswordStrength.STRONG, result);
    }
}
