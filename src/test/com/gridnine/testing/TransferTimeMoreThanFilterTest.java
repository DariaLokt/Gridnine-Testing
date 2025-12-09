package com.gridnine.testing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class TransferTimeMoreThanFilterTest {

    @Test
    @DisplayName("Возвращает true для перелёта где вообще нет пересадки")
    void singleSegmentPass() {
//        given
        LocalDateTime now = LocalDateTime.now();
        Long transferTime = 60L;

        Flight singleSegmentFlight = new Flight(List.of(
                new Segment(now.plusHours(2), now.plusHours(3))
        ));

//        when
        TransferTimeMoreThanFilter filter = new TransferTimeMoreThanFilter(transferTime);

//        then
        assertTrue(filter.test(singleSegmentFlight));
    }

    @Test
    @DisplayName("Возвращает true для перелёта где пересадка равна заданному числу")
    void exactHoursPass() {
//        given
        LocalDateTime now = LocalDateTime.now();
        Long transferTime = 60L;

        Flight exactTransferTimeFlight = new Flight(List.of(
                new Segment(now.plusHours(2), now.plusHours(3)),
                new Segment(now.plusHours(4), now.plusHours(7))
        ));

//        when
        TransferTimeMoreThanFilter filter = new TransferTimeMoreThanFilter(transferTime);

//        then
        assertTrue(filter.test(exactTransferTimeFlight));
    }

    @Test
    @DisplayName("Возвращает true для перелёта где пересадка меньше заданного числа")
    void lessHoursPass() {
//        given
        LocalDateTime now = LocalDateTime.now();
        Long transferTime = 60L;

        Flight lessTransferTimeFlight = new Flight(List.of(
                new Segment(now.plusHours(2), now.plusHours(3)),
                new Segment(now.plusHours(3).plusMinutes(55), now.plusHours(7))
        ));

//        when
        TransferTimeMoreThanFilter filter = new TransferTimeMoreThanFilter(transferTime);

//        then
        assertTrue(filter.test(lessTransferTimeFlight));
    }

    @Test
    void moreHoursFails() {
//        given
        LocalDateTime now = LocalDateTime.now();
        Long transferTime = 60L;

        Flight moreTransferTimeFlight = new Flight(List.of(
                new Segment(now.plusHours(2), now.plusHours(3)),
                new Segment(now.plusHours(3).plusMinutes(65), now.plusHours(7))
        ));

//        when
        TransferTimeMoreThanFilter filter = new TransferTimeMoreThanFilter(transferTime);

//        then
        assertFalse(filter.test(moreTransferTimeFlight));
    }
}