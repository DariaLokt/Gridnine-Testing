package com.gridnine.testing;

public class DepartureBeforeArrivalFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate()));
    }
}
