package org.example.junitpractice;

import org.example.junitpractice.domain.entity.ticketing.Performance;
import org.example.junitpractice.repository.PerformanceRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY)
public class DataJpaTests {
    @Autowired
    private PerformanceRepository performanceRepository;
    @Autowired
    TestEntityManager testEntityManager;

    @Test
    void newPerformanceSaveTest(){
        Performance newP = Performance.builder()
                .name("오페라의 유령")
                .type(0)
                .isReserve("enable")
                .round(1)
                .start_date(Date.valueOf(LocalDate.now()))
                .price(50000)
                .policies(new ArrayList<>())
                .build();

        testEntityManager.persist(newP);
        Performance savedP = performanceRepository.findByName("오페라의 유령");
        assertNotNull(savedP);
        assertEquals(newP.getId(), savedP.getId());
    }
}
