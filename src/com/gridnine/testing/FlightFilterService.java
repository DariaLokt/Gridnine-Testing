package com.gridnine.testing;

import java.util.List;
import java.util.stream.Collectors;

public class FlightFilterService {
    public static List<Flight> filterFlights(List<Flight> flights, FlightFilter filter) {
        return flights.stream()
                .filter(filter::test)
                .collect(Collectors.toList());
    }

    public static List<Flight> filterFlights(List<Flight> flights, List<FlightFilter> filters) {
        return flights.stream()
                .filter(flight -> filters.stream().allMatch(filter -> filter.test(flight)))
                .collect(Collectors.toList());
    }
}
