package com.gridnine.testing;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Flight> allFlights = FlightBuilder.createFlights();
        printFlights(allFlights, "все перелёты");

        FlightFilter departureBeforeNowFilter = new DepartureBeforeNowFilter();

        List<Flight> filteredFlights1 = allFlights.stream()
                .filter(departureBeforeNowFilter::test)
                .toList();

        printFlights(filteredFlights1, "перелёты после фильтра 1 (Вылет до текущего момента времени)");

        FlightFilter departureAfterArrivalFilter = new DepartureAfterArrivalFilter();

        List<Flight> filteredFlights2 = allFlights.stream()
                .filter(departureAfterArrivalFilter::test)
                .toList();

        printFlights(filteredFlights2, "перелёты после фильтра 2 (Сегменты с датой прилёта раньше даты вылета)");

        FlightFilter transferTimeMoreThanFilter = new TransferTimeLessOrEqualsFilter(120L);

        List<Flight> filteredFlights3 = allFlights.stream()
                .filter(transferTimeMoreThanFilter::test)
                .toList();

        printFlights(filteredFlights3, "перелёты после фильтра 3 (Перелеты, где общее время, проведённое на земле, превышает два часа)");

        List<Flight> filteredFlights4 = allFlights.stream()
                .filter(departureBeforeNowFilter::test)
                .filter(departureAfterArrivalFilter::test)
                .filter(transferTimeMoreThanFilter::test)
                .toList();

        printFlights(filteredFlights4, "перелёты после всех фильтров");
    }

    public static void printFlights(List<Flight> flights, String name){
        System.out.println("Печатаю " + name + ":\n");
        for(Flight flight : flights){
            System.out.println("* " + flight + "\n");
        }
        System.out.println("Всего перелётов: " + flights.size() + "\n");
        printSplit();
    }

    public static void printSplit(){
        System.out.println("---------------------------------------------------------");
    }
}
