package dealership;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;

public class Dealership {

    private String name;
    private int capacity;
    private Collection<Car> data;

    public Dealership(String name, int capacity) {
        this.name = name;
        this.capacity = capacity;
        this.data = new ArrayList<>();
    }

    public void add(Car car) {
        if (this.data.size()< capacity){
            data.add(car);
        }
    }

    public boolean buy(String manufacturer, String model) {
        for (Car car:data){
            if (car.getManufacturer().equals(manufacturer)
            && car.getModel().equals(model)) {
                data.remove(car);
                return true;
            }
        }
        return false;
    }

    public Car getLatestCar() {
        if (data.size() > 0) {
            return this.data.stream().max(Comparator.comparing(Car::getYear)).get();
        } else {
            return null;
        }
    }

    public Car getCar(String manufacturer, String model){
        for (Car car : data) {
            if (car.getManufacturer().equals(manufacturer)
                    && car.getModel().equals(model)) {
                return car;
            }
        }
        return null;
    }

    public int getCount() {
        return this.data.size();
    }

    public String getStatistics() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("The cars are in a car dealership %s:", this.name)).append(System.lineSeparator());
        for (Car car : data) {
            sb.append(car).append(System.lineSeparator());
        }

        return sb.toString().trim();
    }
}
