package com.thoughtworks.tdd;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public Ticket park(Car car) {
        List<ParkingLot> parkingLotByCarExist = parkingLots.stream()
                .filter(x -> x.getParkingCarTicket().containsValue(car))
                .collect(Collectors.toList());
        int minQuantity = parkingLots.stream()
                .mapToInt(ParkingLot::getParkedQuantity)
                .filter(x -> x < 10)
                .min().orElse(-1);
        System.out.println(minQuantity);
        List<ParkingLot> parkingLotByParkCar = parkingLots.stream()
                .filter(x -> x.getParkedQuantity() < 10 && x.getParkedQuantity() == minQuantity)
                .collect(Collectors.toList());
        if (car != null && parkingLotByCarExist.size() == 0 && parkingLotByParkCar.size() > 0) {
            return parkingLotByParkCar.get(0).park(car);
        } else {
            return null;
        }
    }

    public Car fetch(Ticket ticket) {
        List<ParkingLot> parkingLotByCar = parkingLots.stream()
                .filter(x -> x.getParkingCarTicket().containsKey(ticket))
                .collect(Collectors.toList());
        return (ticket == null || ticket.isWrong() || ticket.isUsed()) ? null : parkingLotByCar.get(0).getCar(ticket);
    }

    public String giveFetchMessage(Ticket ticket) {
        if (ticket == null) return "Please provide your parking ticket.";
        if (ticket.isWrong() || ticket.isUsed()) {
            return "Unrecognized parking ticket.";
        } else {
            return "Right";
        }
    }

    public String giveParkMessage(Ticket ticket) {
        if (ticket == null) return "Not enough position.";
        return "Right";
    }
}
