import java.util.Random;

public class MonsterFactory {
    private Random random = new Random();
    private Zombie zombie;

    public MonsterFactory() {
    }

    public Monster createMonster() {
        int monsterType = random.nextInt(3);

        switch (monsterType) {
            case 0:
                return new Zombie();
            case 1:
                return new Vampire();
            case 2:
                return new Dragon();
            default:
                return new Zombie();
        }
    }
}
