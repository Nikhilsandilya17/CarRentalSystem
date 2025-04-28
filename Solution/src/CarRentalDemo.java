import model.Car;
import model.CarType;
import model.Customer;

public class CarRentalDemo {
    public static void main(String[] args) {
        CarRental carRental = new CarRental();
        carRental.addCar(new Car(CarType.FOUR_SEATER, "Focus", 2020, "ABC-123", 100.0, true));
        carRental.addCar(new Car(CarType.FIVE_SEATER, "X5", 2021, "DEF-456", 150.0, true));
        carRental.addCar(new Car(CarType.SEVEN_SEATER, "Corolla", 2022, "GHI-789", 200.0, true));

        Customer customer = new Customer("Nikhil", "123-456-7890", "012-345-6789");


    }
}
