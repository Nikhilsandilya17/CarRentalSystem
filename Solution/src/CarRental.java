import model.Car;
import model.CarType;
import model.Customer;
import model.Reservation;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CarRental {
    List<Car> cars;
    Map<String, Reservation> reservations;
    PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public CarRental() {
        cars = new ArrayList<>();
        paymentStrategy = new UPI();
        reservations = new HashMap<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }

    public List<Car> searchCars(CarType carType, Double lowerPrice, Double higherPrice) {
        if(lowerPrice > higherPrice) {
            throw new IllegalArgumentException("Lower price cannot be greater than higher price");
        }
        return cars.stream()
                .filter(car -> car.getCarType() == carType && car.getRentalPricePerDay() >= lowerPrice && car.getRentalPricePerDay() <= higherPrice)
                .toList();
    }

    public boolean bookReservation(Car car, Customer customer, String startDate, String endDate){
        validDate(startDate, endDate);
        if(car.isAvailable()) {
            car.setAvailable(false);
            Reservation reservation = getReservation(car, customer, startDate, endDate);
            double amount = reservation.getTotalAmount();
            makePayment(amount);
            reservations.put(car.getLicensePlateNumber(), reservation);
            return true;
        }
        return false;
    }

    private Reservation getReservation(Car car, Customer customer, String startDate, String endDate) {
        return new Reservation(car, customer, startDate, endDate);
    }

    public boolean modifyReservation(Car car, String startDate, String endDate){
        validDate(startDate, endDate);
        if(!car.isAvailable() && reservations.containsKey(car.getLicensePlateNumber())) {
            Reservation reservation = reservations.get(car.getLicensePlateNumber());
            double oldAmount = reservation.getTotalAmount();
            updateReservation(reservation, startDate, endDate);
            reservations.put(car.getLicensePlateNumber(), reservation);
            double newAmount = reservation.getTotalAmount() - oldAmount;
            makePayment(newAmount);
            return true;
        }
        return false;
    }

    private void validDate(String startDate, String endDate){
        if(startDate==null || endDate==null || endDate.compareTo(startDate) < 0) {
            throw new IllegalArgumentException("End date cannot be less than start date or null");
        }
    }

    private void updateReservation(Reservation reservation, String startDate, String endDate){
        reservation.setReservationDate(startDate);
        reservation.setReturnDate(endDate);
        reservation.setTotalAmount(reservation.getTotalAmount());
    }

    private void makePayment(double amount){
        paymentStrategy.processPayment(amount);
    }



}
