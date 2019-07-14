package com.thoughtworks.tdd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;

public class ParkingBoyTest {

    @Test
    public void should_return_car_when_park_car_to_parking_lot_then_get_it_back() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket();
        wrongTicket.setWrong(true);
        //when
        parkingBoy.park(car);

        //then
        assertSame(null, parkingBoy.fetch(wrongTicket));
    }

    @Test
    public void should_not_fetch_cars_when_ticket_is_used() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
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
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        parkingLot.setParkedQuantity(10);
        //when
        Ticket ticket = parkingBoy.park(car);
        Ticket uselessTicket = parkingBoy.park(car);

        //then
        assertSame(null, uselessTicket);
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_ticket_is_wrong() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Ticket wrongTicket = new Ticket();
        wrongTicket.setUsed(true);
        Ticket wrongSecondTicket = new Ticket();
        wrongSecondTicket.setWrong(true);
        String wrongMessage = "", wrongSecondMessage = "";

        //when
        parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(wrongTicket);
        if (fetchCar == null) {
            wrongMessage = parkingBoy.giveFetchMessage(wrongTicket);
        }
        if (fetchCar == null) {
            wrongSecondMessage = parkingBoy.giveFetchMessage(wrongSecondTicket);
        }
        //then
        assertEquals("Unrecognized parking ticket.", wrongMessage);
        assertEquals("Unrecognized parking ticket.", wrongSecondMessage);
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_not_provide_ticket() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        String wrongMessage = "";

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(null);
        if (fetchCar == null) {
            wrongMessage = parkingBoy.giveFetchMessage(null);
        }
        //then
        assertEquals("Please provide your parking ticket.", wrongMessage);
    }

    @Test
    public void should_not_park_cars_and_get_a_message_when_parking_lot_is_fulled() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        String wrongMessage = "";
        parkingLot.setParkedQuantity(10);

        //when
        Ticket ticket = parkingBoy.park(car);
        if (ticket == null) {
            wrongMessage = parkingBoy.giveParkMessage(null);
        }
        //then
        assertEquals("Not enough position.", wrongMessage);
    }

    @Test
    public void should_park_cars_to_other_park_when_this_parking_lot_is_fulled() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        parkingLot.setParkedQuantity(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_park_car_when_all_parking_lot_are_fulled() {
        //given
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        parkingLot.setParkedQuantity(10);
        parkingSecondLot.setParkedQuantity(10);
        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        //when
        String wrongMessage = "";
        Ticket ticket = parkingBoy.park(car);
        if (ticket == null) {
            wrongMessage = parkingBoy.giveParkMessage(null);
        }

        //then
        assertEquals("Not enough position.", wrongMessage);
    }

    @Test
    public void should_park_car_in_most_quantity_parking_lot_when_parking_lots_are_have_vacancy() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_park_car_when_all_parking_lots_are_have_vacancy_and_no_provide_ticket() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot();
        ParkingLot parkingSecondLot = new ParkingLot();
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SmartParkingBoy parkingBoy = new SmartParkingBoy(parkingLots);

        //when
        String wrongMessage = "";
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(null);
        if (fetchCar == null) {
            wrongMessage = parkingBoy.giveFetchMessage(null);
        }

        //then
        assertEquals("Please provide your parking ticket.", wrongMessage);
    }

    @Test
    public void should_park_car_in_most_quantity_parking_lot_when_parking_lots_are_have_different_capacity() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        //when
        Ticket ticket = parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(ticket);

        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_fetch_cars_and_get_a_message_when_ticket_is_wrong_and_have_many_parking_lots() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);
        parkingLots.add(parkingThridLot);

        SuperSmartParkingBoy parkingBoy = new SuperSmartParkingBoy(parkingLots);

        Ticket wrongTicket = new Ticket();
        wrongTicket.setUsed(true);
        Ticket wrongSecondTicket = new Ticket();
        wrongSecondTicket.setWrong(true);

        //when
        String wrongMessage = "", wrongSecondMessage = "";
        parkingBoy.park(car);
        Car fetchCar = parkingBoy.fetch(wrongTicket);
        if (fetchCar == null) {
            wrongMessage = parkingBoy.giveFetchMessage(wrongTicket);
        }
        if (fetchCar == null) {
            wrongSecondMessage = parkingBoy.giveFetchMessage(wrongSecondTicket);
        }
        //then
        assertEquals("Unrecognized parking ticket.", wrongMessage);
        assertEquals("Unrecognized parking ticket.", wrongSecondMessage);
    }

    @Test
    public void should_fetch_car_when_manager_call_parking_boy_parks_car() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        List<ParkingLot> smartParkingLots = new ArrayList<>();
        smartParkingLots.add(parkingSecondLot);
        smartParkingLots.add(parkingThridLot);

        List<ParkingLot> superParkingLots = new ArrayList<>();
        superParkingLots.add(parkingLot);
        superParkingLots.add(parkingSecondLot);
        superParkingLots.add(parkingThridLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(smartParkingLots);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(superParkingLots);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        parkingBoys.add(smartParkingBoy);
        parkingBoys.add(superSmartParkingBoy);

        ParkingManager manager = new ParkingManager(parkingLots, parkingBoys);

        //when
        Ticket ticket = manager.callParkingBoyParkCar(parkingBoy, car);
        Car fetchCar = manager.callParkingBoyFetchCar(parkingBoy, ticket);
        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_fetch_car_when_manager_parks_car() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);

        ParkingManager manager = new ParkingManager(parkingLots, parkingBoys);

        //when
        Ticket ticket = manager.park(car);
        Car fetchCar = manager.fetch(ticket);
        //then
        assertSame(car, fetchCar);
    }

    @Test
    public void should_not_fetch_car_and_get_message_when_manager_call_parking_boy_parks_car() {
        //given
        Car car = new Car();

        ParkingLot parkingLot = new ParkingLot(20);
        ParkingLot parkingSecondLot = new ParkingLot(15);
        ParkingLot parkingThridLot = new ParkingLot();
        parkingLot.setParkedQuantity(7);
        parkingSecondLot.setParkedQuantity(5);
        parkingThridLot.setParkedQuantity(10);

        List<ParkingLot> parkingLots = new ArrayList<>();
        parkingLots.add(parkingLot);
        parkingLots.add(parkingSecondLot);

        List<ParkingLot> smartParkingLots = new ArrayList<>();
        smartParkingLots.add(parkingSecondLot);
        smartParkingLots.add(parkingThridLot);

        List<ParkingLot> superParkingLots = new ArrayList<>();
        superParkingLots.add(parkingLot);
        superParkingLots.add(parkingSecondLot);
        superParkingLots.add(parkingThridLot);

        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(smartParkingLots);
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(superParkingLots);

        List<ParkingBoy> parkingBoys = new ArrayList<>();
        parkingBoys.add(parkingBoy);
        parkingBoys.add(smartParkingBoy);
        parkingBoys.add(superSmartParkingBoy);

        ParkingManager manager = new ParkingManager(parkingLots, parkingBoys);

        //when
        String wrongParkMessage = "", wrongFetchMessage = "";
        Ticket ticket = manager.callParkingBoyParkCar(parkingBoy, null);
        if (ticket == null) {
            wrongParkMessage = manager.giveParkMessage(null);
        }
        Car fetchCar = manager.callParkingBoyFetchCar(parkingBoy, null);
        if (fetchCar == null) {
            wrongFetchMessage = manager.giveFetchMessage(null);
        }

        //then
        assertEquals("Not enough position.", wrongParkMessage);
        assertEquals("Please provide your parking ticket.", wrongFetchMessage);
    }
}
