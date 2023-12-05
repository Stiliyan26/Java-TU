package Exercies;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
//        Company company = new Company("JBoxers", "20.12.2023", "adadda");
//        CompanyET companyET = new CompanyET(
//                "Tu",
//                "20.12.2022",
//                "83289423",
//                "Stiliyan",
//                20.23,
//                30);
//
//        System.out.println(companyET.profitCalculation());

        Car car1 = new Car("Bmw", "X5", "red", 200, "v5", "fias", "2001");
        Car car2 = new Car("Mecedes", "SClass", "Black", 200, "v5", "jgf", "2005");
        Car car3 = new Car("Nissan", "Murano", "Gray", 200, "v5", "dsa", "2002");
        Car car4 = new Car("Honda", "Civic", "Blue", 200, "v5", "dsa", "2002");
        Car car5 = new Car("Honda", "Civic", "Blue", 200, "v5", "dsa", "2002");

        Car[] cars = new Car[] {
                car1,
                car2,
                car3,
                car4,
                car5
        };
//        Car[] filteredCar = Car.clearCarArr(cars, 'B');
//        System.out.println(Arrays.toString(filteredCar));

//        Car[] sortCars = Car.sortCars(cars, "Desc");
//        System.out.println(Arrays.toString(sortCars));

        Car[] filteredCars = Car.filteredCar(cars);
        System.out.println(Arrays.toString(filteredCars));
    }
}
//bhmn