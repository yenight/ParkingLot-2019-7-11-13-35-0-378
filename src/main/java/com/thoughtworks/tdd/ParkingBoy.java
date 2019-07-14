package com.thoughtworks.tdd;

public class ParkingBoy {

    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLot = parkingLot;
    }

    public Ticket park(Car car) {
        if (car != null && !parkingLot.getParkingCarTicket().containsValue(car)) {
            return parkingLot.park(car);
        } else {
            return null;
        }
    }

    public Car fetch(Ticket ticket) {
        return ticket == null ? null : parkingLot.getCar(ticket);
    }

    public String giveMessage(Ticket ticket) {
        if (ticket == null) return "Please provide your parking ticket.";
        if (ticket.isWrong() || ticket.isUsed()) {
            return "Unrecognized parking ticket.";
        } else {
            return "Right";
        }
    }
}
