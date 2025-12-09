package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepartureBeforeArrivalFilterTest {

    @Test
    @DisplayName("Возвращает false если перелёт неправильный (с датой прилёта раньше даты вылета)")
    void testDepartureBeforeArrivalFilter() {
//        given
        LocalDateTime now = LocalDateTime.now();

        Flight normalFlight = new Flight(List.of(
                new Segment(now.plusHours(2), now.plusHours(3))
        ));

        Flight wrongFlight = new Flight(List.of(
                new Segment(now.plusHours(3), now.plusHours(1))
        ));

        List<Flight> flights = List.of(normalFlight,wrongFlight);

        DepartureBeforeArrivalFilter filter = new DepartureBeforeArrivalFilter();

//        when
        List<Flight> result = FlightFilterService.filterFlights(flights, filter);

//        then
        assertEquals(1, result.size());
        assertEquals(normalFlight, result.get(0));
        assertFalse(result.contains(wrongFlight));
    }
}