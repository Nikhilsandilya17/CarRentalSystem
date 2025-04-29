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

    public boolean makeReservation(Car car, Customer customer, String startDate, String endDate){
        if(car.isAvailable()) {
            car.setAvailable(false);
            Reservation reservation = new Reservation(car, customer, startDate, endDate);
            double amount = reservation.getTotalAmount();
            paymentStrategy.processPayment(amount);
            reservations.put(car.getLicensePlateNumber(), reservation);
            return true;
        }
        return false;
    }

    public boolean modifyReservation(Car car, String startDate, String endDate){
        if(!car.isAvailable() && reservations.containsKey(car.getLicensePlateNumber())) {
            Reservation reservation = reservations.get(car.getLicensePlateNumber());
            double oldAmount = reservation.getTotalAmount();
            reservation.setReservationDate(startDate);
            reservation.setReturnDate(endDate);
            reservation.setTotalAmount(reservation.getTotalAmount());
            reservations.put(car.getLicensePlateNumber(), reservation);
            double newAmount = reservation.getTotalAmount() - oldAmount;
            paymentStrategy.processPayment(newAmount);
            return true;
        }
        return false;
    }



}
