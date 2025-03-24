package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;


public class  Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        List<String> carname = carname();
        System.out.println("시도할 회수:");
        int trycount= Integer.parseInt(Console.readLine());
        checkTryCount(trycount);
        List<Car> racecar = classcar(carname);
        runrace(racecar,trycount);

        List<Car> winners=winner(racecar);
        print(winners);
    }
// 자동차 입력 예외 구현
    public static List<String> carname(){
        System.out.println("경주할 자동차 이름을 입력하시오.(,로 구분)");
        String inputNames = Console.readLine();
        String[] names = inputNames.split(",");

        List<String> racecarname = new ArrayList<>();
        for (String name : names) {
            if (name.length() > 5) {
                throw new IllegalArgumentException("자동차 이름은 최대 5자까지만 가능합니다.");
            }
                racecarname.add(name);

            }

        return racecarname;
    }
    private static void checkTryCount(int tryCount) {
        try{if (tryCount <= 0) {
            throw new IllegalArgumentException("시도할 회수는 1 이상이어야 합니다.");
            }
        }
        catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
    private static List<Car>classcar(List<String> carname){
        List<Car> racecar = new ArrayList<>();
        for (String name : carname) {racecar.add(new Car(name));
        }
        return racecar;
    }


    private static void runrace(List<Car> racecar, int trycount) {

        System.out.println("실행 결과");
        for (int i = 0; i < trycount; i++) {


            for (Car car : racecar) {
                car.move();
                System.out.println(car.getName()+" : "+"-".repeat(car.getPosition()));
            }
            System.out.println();

        }
    }

    private static List<Car> winner (List<Car> racecar) {
        int maxposition = 0;
        for (Car car : racecar) {
            if (car.getPosition() > maxposition) {
                maxposition = car.getPosition();
            }
        }
        List<Car> wincar = new ArrayList<>();
        for (Car car : racecar) {
            if (car.getPosition() == maxposition) {
                wincar.add(car);
            }
        }

        return wincar;
    }
    private static void print(List<Car> winners){
    List<String> names = new ArrayList<>();
    for (Car car : winners) {
        names.add(car.getName());
    }
    System.out.println("최종 우승자 : " + String.join(", ", names));
    }
}

class Car{
    private final String name;
    private int position;
    public Car(String name){
        this.name = name;
        this.position = 0;
    }
    public String getName() {return name;}
    public int getPosition() {return position;}
    public void move() {
        if (Randoms.pickNumberInRange(0, 9) >= 4) {
            position++;
        }

    }
}



