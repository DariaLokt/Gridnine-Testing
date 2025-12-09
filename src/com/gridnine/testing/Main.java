package com.gridnine.testing;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> allFlights = FlightBuilder.createFlights();
        printFlights(allFlights, "все перелёты");

        printFlights(FlightFilterService.filterFlights(allFlights, new DepartureBeforeNowFilter()),
                "перелёты после фильтра 1 (Вылет до текущего момента времени)");

        printFlights(FlightFilterService.filterFlights(allFlights, new DepartureAfterArrivalFilter()),
                "перелёты после фильтра 2 (Сегменты с датой прилёта раньше даты вылета)");

        printFlights(FlightFilterService.filterFlights(allFlights, new TransferTimeLessOrEqualsFilter(120L)),
                "перелёты после фильтра 3 (Перелеты, где общее время, проведённое на земле, превышает два часа)");

        List<FlightFilter> flightFilters = Arrays.asList(
                new DepartureBeforeNowFilter(),
                new DepartureAfterArrivalFilter(),
                new TransferTimeLessOrEqualsFilter(120L));

        printFlights(FlightFilterService.filterFlights(allFlights, flightFilters),
                "перелёты после всех фильтров");
    }

    public static void printFlights(List<Flight> flights, String name) {
        System.out.println("Печатаю " + name + ":\n");
        for (Flight flight : flights) {
            System.out.println("* " + flight + "\n");
        }
        System.out.println("Всего перелётов: " + flights.size() + "\n");
        printSplit();
    }

    public static void printSplit() {
        System.out.println("---------------------------------------------------------");
    }
}
