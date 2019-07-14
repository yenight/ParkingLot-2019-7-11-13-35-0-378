package com.thoughtworks.tdd;

import java.util.List;

public class ParkingManager {
    private List<ParkingBoy> parkingBoyList;

    public ParkingManager(List<ParkingBoy> parkingBoyList) {
        this.parkingBoyList = parkingBoyList;
    }

    public List<ParkingBoy> getParkingBoyList() {
        return parkingBoyList;
    }

    public Ticket callParkingBoyParkCar(ParkingBoy parkingBoy, Car car) {
        return parkingBoy.park(car);
    }

    public Car callParkingBoyFetchCar(ParkingBoy parkingBoy, Ticket ticket) {
        return parkingBoy.fetch(ticket);
    }
}
