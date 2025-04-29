import model.Car;
import model.CarType;
import model.Customer;

import java.time.LocalDate;
import java.util.List;

public class CarRentalDemo {
    public static void main(String[] args) {

        CarRental carRental = new CarRental();
        carRental.addCar(new Car(CarType.FOUR_SEATER, "Focus", 2020, "ABC-123", 100.0, true));
        carRental.addCar(new Car(CarType.FIVE_SEATER, "X5", 2021, "DEF-456", 150.0, true));
        carRental.addCar(new Car(CarType.SEVEN_SEATER, "Corolla", 2022, "GHI-789", 200.0, true));
        carRental.addCar(new Car(CarType.FIVE_SEATER, "M5", 2023, "XYZ-456", 200.0, true));

        Customer customer = new Customer("Nikhil", "123-456-7890", "012-345-6789");

        List<Car> availableCars = carRental.searchCars(CarType.FIVE_SEATER, 100.0, 200.0);
        System.out.println("Available cars: ");
        for(Car car : availableCars) {
            System.out.println(car.toString());
        }
        String startDate = LocalDate.now().toString();
        String endDate = LocalDate.now().plusDays(3).toString();
        if(carRental.makeReservation(availableCars.get(0), customer, startDate, endDate)){
            System.out.println("Reservation successfull for Car: "+availableCars.get(0).toString());
        }
        else{
            System.out.println("Reservation unsuccessful for Car: "+availableCars.get(0).toString());
        }
        //Modify reservation
        carRental.setPaymentStrategy(new DebitCard());
        String newStartDate = LocalDate.now().plusDays(5).toString();
        String newEndDate = LocalDate.now().plusDays(10).toString();
        if(carRental.modifyReservation(availableCars.get(0), newStartDate, newEndDate)){
            System.out.println("Reservation modified for Car: "+availableCars.get(0).toString());
        }
        else{
            System.out.println("Reservation unsuccessful for Car: "+availableCars.get(0).toString());
        }

    }
}
