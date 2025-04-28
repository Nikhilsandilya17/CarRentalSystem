import model.Car;

import java.util.ArrayList;
import java.util.List;

public class CarRental {
    List<Car> cars;

    public CarRental() {
        cars = new ArrayList<>();
    }

    public void addCar(Car car) {
        cars.add(car);
    }


}
