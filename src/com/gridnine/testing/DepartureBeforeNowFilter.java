package com.gridnine.testing;

import java.time.LocalDateTime;

public class DepartureBeforeNowFilter implements FlightFilter {
    private final LocalDateTime now;

    public DepartureBeforeNowFilter() {
        this.now = LocalDateTime.now();
    }

    @Override
    public boolean test(Flight flight) {
         return flight.getSegments().stream()
                .map(Segment::getDepartureDate)
                .noneMatch(departure -> departure.isBefore(now));
    }
}
