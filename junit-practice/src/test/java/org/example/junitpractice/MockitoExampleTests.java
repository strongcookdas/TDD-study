package org.example.junitpractice;

import org.example.junitpractice.domain.dto.Ticket;
import org.example.junitpractice.repository.ReservationRepository;
import org.example.junitpractice.service.PerformanceService;
import org.example.junitpractice.service.TicketingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MockitoExampleTests {
    // @InjectMocks 어노테이션으로 가짜 목을 주입한다.
    @InjectMocks
    private TicketingService ticketingService;
    @Mock
    ReservationRepository reservationRepository;
    @Mock
    PerformanceService performanceService;

    @Test
    void ticketingTest() {
        ticketingService = new TicketingService(performanceService, reservationRepository);
        Ticket t = Ticket.builder()
                .performanceId(UUID.fromString("abc45fd5-dab9-11ee-9743-0242ac130002"))
                .performanceName("레베카")
                .reservationName("유진호")
                .reservationPhoneNumber("010-1234-1234")
                .reservationStatus("reserve")
                .round(1)
                .line('A')
                .seat(1)
                .appliedPolicies(Arrays.asList(new String[]{"telecome"}))
                .build();

        Mockito.when(performanceService.isEnableReserve(any()))
                .then(invocationOnMock -> {
                    System.out.println("Mocking");
                    return "enable";
                });
        // 정의하지 않으면 mock으로 알아서 호출

        ticketingService.ticketing(t);

        //mockito가 정의된 메서드를 실행하면 캐치해서 when then을 실행
        Mockito.verify(performanceService, Mockito.times(1)).isEnableReserve(any());
        Mockito.verify(reservationRepository, Mockito.times(1)).save(any());
    }

    @Test
    void ticketingDisableTest() {
        ticketingService = new TicketingService(performanceService, reservationRepository);
        Ticket t = Ticket.builder()
                .performanceId(UUID.fromString("abc45fd5-dab9-11ee-9743-0242ac130002"))
                .performanceName("레베카")
                .reservationName("유진호")
                .reservationPhoneNumber("010-1234-1234")
                .reservationStatus("reserve")
                .round(1)
                .line('A')
                .seat(1)
                .appliedPolicies(Arrays.asList(new String[]{"telecome"}))
                .build();

        Mockito.when(performanceService.isEnableReserve(any()))
                .then(invocationOnMock -> {
                    System.out.println("Mocking");
                    return "disable";
                });

        assertThrows(NoSuchElementException.class, () -> ticketingService.ticketing(t));

        //mockito가 정의된 메서드를 실행하면 캐치해서 when then을 실행
        Mockito.verify(performanceService, Mockito.times(1)).isEnableReserve(any());
        Mockito.verify(reservationRepository, Mockito.times(0)).save(any());
    }
}
