package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FlightFilterServiceTest {

    @Test
    @DisplayName("Фильтрация происходит корректно с одним фильтром")
    void filterFlightsWithSingleFilter() {
//        given
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter filter = flight -> !flight.equals(flights.get(0));

//        when
        List<Flight> result = FlightFilterService.filterFlights(flights, filter);

//        then
        assertNotEquals(flights.size(), result.size());
        assertEquals(flights.get(1), result.get(0));
        assertFalse(result.contains(flights.get(0)));
    }

    @Test
    @DisplayName("Фильтрация происходит корректно с несколькими фильтрами")
    void filterFlightsWithSomeFilters() {
//        given
        List<Flight> flights = FlightBuilder.createFlights();

        FlightFilter filter1 = flight -> !flight.equals(flights.get(0));
        FlightFilter filter2 = flight -> !flight.equals(flights.get(1));
        List<FlightFilter> filters = Arrays.asList(filter1, filter2);

//        when
        List<Flight> result = FlightFilterService.filterFlights(flights, filters);

//        then
        assertNotEquals(flights.size(), result.size());
        assertEquals(flights.get(2), result.get(0));
        assertFalse(result.contains(flights.get(0)));
        assertFalse(result.contains(flights.get(1)));
    }

    @Test
    @DisplayName("Фильтрация большого количества перелётов не должна занимать много времени")
    void testFilterTimeWithManyFlights() {
//        given
        List<Flight> manyFlights = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            manyFlights.addAll(FlightBuilder.createFlights());
        }
        TransferTimeMoreThanFilter filter = new TransferTimeMoreThanFilter(120L);

//        when
        long start = System.currentTimeMillis();
        List<Flight> result = FlightFilterService.filterFlights(manyFlights, filter);
        long duration = System.currentTimeMillis() - start;

//        then
        assertTrue(duration < 1000);
    }
}