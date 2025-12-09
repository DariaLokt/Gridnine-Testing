package com.gridnine.testing;

import java.time.LocalDateTime;

public class DepartureBeforeNowFilter implements FlightFilter {
    @Override
    public boolean test(Flight flight) {
        LocalDateTime now = LocalDateTime.now();
         return flight.getSegments().stream()
                .map(Segment::getDepartureDate)
                .noneMatch(departure -> departure.isBefore(now));
    }
}
