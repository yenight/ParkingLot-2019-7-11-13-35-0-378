package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {

    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);
        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_return_cars_when_park_cars_to_parking_lot_then_get_them_back() {
        //given
        Car firstCar = new Car();
        Car secondCar = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

        //when
        Ticket firstTicket = parkingBoy.park(firstCar);
        Car fetchFirstCar = parkingBoy.fetch(firstTicket);

        Ticket secondTicket = parkingBoy.park(secondCar);
        Car fetchSecondCar = parkingBoy.fetch(secondTicket);
        //then
        assertSame(firstCar, fetchFirstCar);
        assertSame(secondCar, fetchSecondCar);
    }

    @Test
    public void should_not_fetch_cars_when_ticket_is_wrong() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket wrongTicket = new Ticket();

        //when
        parkingBoy.park(car);

        //then
//        Assertions.assertThrows(Exception.class, () -> {
//            parkingBoy.fetch(wrongTicket);
//        });
        assertSame(null, parkingBoy.fetch(wrongTicket));
    }

    @Test
    public void should_not_fetch_cars_when_ticket_is_used() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        Ticket hadUsedTicket = new Ticket();
        hadUsedTicket.setUsed(true);
        //when
        parkingBoy.park(car);

        //then
        assertSame(null, parkingBoy.fetch(hadUsedTicket));
    }

    @Test
    public void should_not_get_ticket_when_no_position_park_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingLot.setParkedQuantity(10);
        //when
        Ticket ticket = parkingBoy.park(car);

        //then
        assertSame(null, ticket);
    }

    @Test
    public void should_not_get_ticket_when_park_null_car() {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingLot.setParkedQuantity(10);
        //when
        Ticket ticket = parkingBoy.park(null);

        //then
        assertSame(null, ticket);
    }

    @Test
    public void should_not_get_ticket_when_park_a_parking_lot_car() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLot);
        parkingLot.setParkedQuantity(10);
        //when
        Ticket ticket = parkingBoy.park(car);
        Ticket uselessTicket = parkingBoy.park(car);

        //then
        assertSame(null, uselessTicket);
    }
}
