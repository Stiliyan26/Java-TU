package Exercies;

public class Car {
    private String brand;
    private String model;
    private String color;
    private int hoursePower;
    private String engineType;
    private String gearBoxType;
    private String yearOfManufacture;

    public Car() {
    }

    public Car(String brand, String model, String color, int hoursePower, String engineType, String gearBoxType, String yearOfManufacture) {
        this.brand = brand;
        this.model = model;
        this.color = color;
        this.hoursePower = hoursePower;
        this.engineType = engineType;
        this.gearBoxType = gearBoxType;
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getHoursePower() {
        return hoursePower;
    }

    public void setHoursePower(int hoursePower) {
        this.hoursePower = hoursePower;
    }

    public String getEngineType() {
        return engineType;
    }

    public void setEngineType(String engineType) {
        this.engineType = engineType;
    }

    public String getGearBoxType() {
        return gearBoxType;
    }

    public void setGearBoxType(String gearBoxType) {
        this.gearBoxType = gearBoxType;
    }

    public String getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(String yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public static Car[] clearCarArr(Car[] cars, char character) {
        int length = 0;

        for (int i = 0; i < cars.length; i++) {
            if (cars[i].brand.charAt(0) == character) {
                length++;
            }
        }

        Car[] filteredCars = new Car[length];
        int counter = 0;

        for (int i = 0; i < length; i++) {
            if (cars[i].brand.charAt(0) == character) {
                filteredCars[i] = cars[i];
                counter++;
            }
        }

        return filteredCars;
    }

    public static Car[] sortCars(Car[] cars, String action) {
        if (action == "Asc") {
            for (int i = 0; i < cars.length; i++) {
                for (int j = i + 1; j < cars.length; j++) {
                    if (cars[j].brand.compareTo(cars[i].brand) < 0){
                        Car temp = cars[i];
                        cars[i] = cars[j];
                        cars[j] = temp;
                    }
                }
            }
        }

        else if (action == "Desc") {
            for (int i = 0; i < cars.length; i++) {
                for (int j = i + 1; j < cars.length; j++) {
                    if (cars[j].brand.compareTo(cars[i].brand) > 0) {
                        // Swap the elements
                        Car temp = cars[i];
                        cars[i] = cars[j];
                        cars[j] = temp;
                    }
                }
            }
        }

        return cars;
    }

    public static Car[] filteredCar(Car[] cars){
        int lengthToSubtract = 0;

        for (int i = 0; i < cars.length; i++) {
            for (int j = i + 1; j < cars.length; j++) {
                if (cars[i].brand.compareTo(cars[j].brand) == 0 &&
                        cars[i].model.compareTo(cars[j].model) == 0) {
                    lengthToSubtract++;
                }
            }
        }

        int length = cars.length - lengthToSubtract;

        Car[] filteredCars = new Car[length];
        int count = 0;

        boolean isUnique = true;

        for (int i = 0; i < cars.length; i++) {
            for (int j = i + 1; j < cars.length; j++) {
                if (cars[i].brand.compareTo(cars[j].brand) == 0 &&
                        cars[i].model.compareTo(cars[j].model) == 0) {
                    isUnique = false;
                }
            }

            if (isUnique == true) {
                filteredCars[count] = cars[i];
                count++;
            }

            isUnique = true;
        }

        return filteredCars;
    }

    @Override
    public String toString() {
        return "Car{" +
                "brand='" + brand + '\'' +
                ", model='" + model + '\'' +
                '}';
    }
}
