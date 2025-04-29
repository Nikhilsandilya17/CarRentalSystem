package model;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Reservation {

    private static final String dateFormat = "yyyy-MM-dd";

    private final Car car;
    private Customer customer;
    private String reservationDate;
    private String returnDate;
    private double totalAmount;

    public Reservation(Car car, Customer customer, String reservationDate, String returnDate) {
        this.car = car;
        this.customer = customer;
        this.reservationDate = reservationDate;
        this.returnDate = returnDate;
    }


    public double getTotalAmount(){
        return car.getRentalPricePerDay() * calculateDateDifference(reservationDate, returnDate);
    }

    private static long calculateDateDifference(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(Reservation.dateFormat);
        LocalDate start = LocalDate.parse(startDate, formatter);
        LocalDate end = LocalDate.parse(endDate, formatter);
        return ChronoUnit.DAYS.between(start, end);
    }


    public Car getCar() {
        return car;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getReservationDate() {
        return reservationDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setReservationDate(String reservationDate) {
        this.reservationDate = reservationDate;
    }
    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }
}
