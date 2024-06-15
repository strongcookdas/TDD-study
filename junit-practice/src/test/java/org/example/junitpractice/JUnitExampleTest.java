package org.example.junitpractice;

import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;


public class JUnitExampleTest {

    @TestFactory
    List<DynamicNode> testFactoryExampleTest() {
        int size = 10;
        // DB 데이터 조회
        // 외부 API 데이터 조회시
        List<DynamicNode> result = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            int finalI = i; // 컴파일 시점이 아닌 런타임 시점에 값이 결정
            result.add(dynamicTest("Test CaseName" + i, () -> System.out.println("Dynamic Test #" + finalI)));
        }
        return result;
    }

    @RepeatedTest(value = 10, name = RepeatedTest.LONG_DISPLAY_NAME)
    void repeatedTestExample(RepetitionInfo info) {
        System.out.println("repetitionInfo = " + info.getCurrentRepetition() + "/" + info.getTotalRepetitions());
        int currentValue = info.getCurrentRepetition();
        assertEquals(currentValue * 2, currentValue * 2);
    }

    @ParameterizedTest
    @CsvSource(value = {"1|2", "2|4", "3|6", "4|8"}, delimiter = '|')
    void csvSourceExampleTest(int input, int expected) {
        assertEquals(expected, input * 2);
    }
}
