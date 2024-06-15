package org.example.junitpractice;

import org.example.junitpractice.domain.dto.test_doubles.Student;
import org.example.junitpractice.repository.StubGradeRepository;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GradleCalculateTests {

    private StubGradeRepository stubGradeRepository;

    public GradleCalculateTests(){
        stubGradeRepository = new StubGradeRepository();
    }

    private Map<String, Integer> grades(Student student){
        return stubGradeRepository.findByName(student.getName());
    }

    @Test
    void averageGradesTests() { // Stub을 이용하여 평균을 구하는 로직만 집중해서 테스트한다.
        Student s = new Student("jinho", 0, new HashMap<>(), 'M', "01012345678");
        Map<String, Integer> result = grades(s);
        int total = 0;
        int expected = 8;
        for(String grade : result.keySet()){
            total += result.get(grade);
        }
        assertEquals(expected, total/result.size());
    }
}
