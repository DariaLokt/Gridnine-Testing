package com.gridnine.testing;

public class DepartureAfterArrivalFilter implements FlightFilter{
    @Override
    public boolean test(Flight flight) {
        return flight.getSegments().stream()
                .noneMatch(segment -> segment.getDepartureDate().isAfter(segment.getArrivalDate()));
    }
}
