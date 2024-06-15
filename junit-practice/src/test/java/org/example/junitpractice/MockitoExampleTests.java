package org.example.junitpractice;

import org.example.junitpractice.domain.dto.Ticket;
import org.example.junitpractice.repository.ReservationRepository;
import org.example.junitpractice.service.PerformanceService;
import org.example.junitpractice.service.TicketingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
public class MockitoExampleTests {
    private TicketingService ticketingService;

    @Test
    void ticketingTest() {
        //항상 선언해야하나?
        PerformanceService performanceService = Mockito.mock(PerformanceService.class);
        ReservationRepository reservationRepository = Mockito.mock(ReservationRepository.class);

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

        Mockito.verify(performanceService, Mockito.times(1)).isEnableReserve(any());
        Mockito.verify(reservationRepository, Mockito.times(1)).save(any());
    }
}
