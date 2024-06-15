package org.example.junitpractice.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.junitpractice.domain.dto.Ticket;
import org.example.junitpractice.domain.entity.ticketing.Reservation;
import org.example.junitpractice.repository.ReservationRepository;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class TicketingService {

    private final PerformanceService performanceService;
    private final ReservationRepository reservationRepository;

    @Transactional
    public Ticket ticketing(Ticket t) {
        if ("enable".equals(performanceService.isEnableReserve(t.getPerformanceId()))) {
            reserve(t);
            return t;
        }
        throw new NoSuchElementException("예약 가능한 공연이 아닙니다.");
    }

    private void reserve(Ticket t){
        reservationRepository.save(Reservation.of(t));
    }

    private Reservation getReservedInfo(Ticket t){
        return reservationRepository.findReservationByPerformanceIdAndRoundAndGateAndLineAndSeatAndNameAndPhoneNumber(
                t.getPerformanceId(),
                t.getRound(),
                t.getGate(),
                t.getLine(),
                t.getSeat(),
                t.getReservationName(),
                t.getReservationPhoneNumber()
        );
    }

}
