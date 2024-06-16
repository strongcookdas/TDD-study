package org.example.junitpractice;

import org.example.junitpractice.domain.dto.Ticket;
import org.example.junitpractice.domain.entity.ticketing.Performance;
import org.example.junitpractice.repository.PerformanceRepository;
import org.example.junitpractice.service.TicketingService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest //등록된 빈을 어플리케이션 컨텍스트에 등록
@Transactional
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.ANY, connection = EmbeddedDatabaseConnection.H2)
//테스트는 h2 디비 사용
public class SpringBootTestExampleTests {
    // 테스트 db에 공연정보를 추가 테스트
    @Autowired
    private TicketingService ticketingService;
    @Autowired
    private PerformanceRepository performanceRepository;

    @Test
    void ticketingReserveIntegrationTest() {
        //Given
        Performance p = Performance.builder()
                .name("로미오와 줄리엣")
                .type(0)
                .isReserve("enable")
                .round(1)
                .start_date(Date.valueOf(LocalDate.now()))
                .price(100000)
                .policies(new ArrayList<>())
                .build();
        //When
        performanceRepository.save(p);
        //Then
        Performance insertedTestPerformance = performanceRepository.findByName("로미오와 줄리엣");
        assertNotNull(insertedTestPerformance);
        assertEquals(p.getName(), insertedTestPerformance.getName());
        assertEquals(p.getStart_date(), insertedTestPerformance.getStart_date());

        //Given
        Ticket t = Ticket.builder()
                .performanceId(insertedTestPerformance.getId())
                .performanceName(insertedTestPerformance.getName())
                .reservationName("user")
                .reservationPhoneNumber("010-1234-1234")
                .reservationStatus("reserve")
                .round(1)
                .line('A')
                .seat(1)
                .appliedPolicies(new ArrayList<>())
                .build();
        //When
        Ticket reservedTicket = ticketingService.ticketing(t);
        //Then
        assertEquals(t, reservedTicket);
    }

}
