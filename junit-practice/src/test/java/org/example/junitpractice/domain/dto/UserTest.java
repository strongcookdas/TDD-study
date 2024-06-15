package org.example.junitpractice.domain.dto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class UserTest {

    @ParameterizedTest
    @ValueSource(strings = {"", " "})
    @NullAndEmptySource
    void parameterizedTestsExample(String userName) {
        assertThatThrownBy(() -> new User(userName))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("User의 이름이 Null이면 예외가 발생한다.")
    void userNameNullThenException() {
        assertThatThrownBy(() -> new User(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("User의 이름은 공백이면 예외가 발생한다.")
    void userNameIsBlankThenException() {
        assertThatThrownBy(() -> new User(""))
                .isInstanceOf(IllegalArgumentException.class);
    }
}