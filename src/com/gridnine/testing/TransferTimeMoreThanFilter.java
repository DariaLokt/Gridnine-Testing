package com.gridnine.testing;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.IntStream;

public class TransferTimeMoreThanFilter implements FlightFilter{
    private final Long minutes;

    public TransferTimeMoreThanFilter(Long minutes) {
        this.minutes = minutes;
    }

    @Override
    public boolean test(Flight flight) {
        return calculateTotalGroundTime(flight.getSegments()) <= minutes;
    }

    private long calculateTotalGroundTime(List<Segment> segments) {
        return IntStream.range(0, segments.size() - 1)
                .mapToLong(i -> {
                    LocalDateTime arrival = segments.get(i).getArrivalDate();
                    LocalDateTime nextDeparture = segments.get(i + 1).getDepartureDate();
                    Duration groundTime = Duration.between(arrival, nextDeparture);
                    return groundTime.isNegative() ? 0 : groundTime.toMinutes();
                })
                .sum();
    }
}
