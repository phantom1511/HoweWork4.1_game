import java.util.Random;

public class Main {

    public static int[] health = {800, 300, 300, 300, 600, 250}; //жизни нашего босса и героев
    public static int[] damage = {50, 20, 20, 20, 10, 10}; //уроны которын могут нанести наши герои и босса
    public static String[] damageType = {"Physical", "Physical", "Magical", "Mental", "Shoot", "Heal"};
    public static String[] heroes = {"Boss", "Warrior", "Magical", "Mental", "Tank", "Medic"};

    public static void main(String[] args) {

        printStatistics();

        while (!isFinished()) {
            changeBossDefence();
            round();
            tankShoot();
            printStatistics();
        }
    }

    public static void changeBossDefence() {
        Random random = new Random();
        int randomNumber = random.nextInt(3) + 1;
        damageType[0] = damageType[randomNumber];
    }

    public static void round() {
        for (int i = 1; i <= 5; i++) {
            int healthRemain = bossDamage(i);
            if (healthRemain < 0) {
                health[i] = 0;
            } else {
                health[i] = healthRemain;
            }
        }
        for (int i = 1; i <= 4; i++) {
            int healthRemain = heroesDamage(i);
            if (healthRemain < 0) {
                health[0] = 0;
            } else {
                health[0] = healthRemain;
            }
        }
        for (int i = 1; i <= 4; i++) {
            if (health[5] > 0) {
                health[i] = medicHeal(i);
            } else {
                health[5] = 0;
            }
        }




        System.out.println("................................");
        System.out.println("Medic healing all heroes for: " + damage[5]);
    }

    public static void tankShoot() {
        int from = 1;
        int to = 5;
        int randomHero = from + (int) (Math.random() * to);
        System.out.println("Tank blocked " + randomHero + " damage");
        health[randomHero] = health[randomHero] += 20;
    }

    public static int medicHeal(int heroIndex) {
        return health[heroIndex] + damage[5];
    }

    public static int bossDamage(int heroIndex) {
        return health[heroIndex] - damage[0];
    }

    public static int heroesDamage(int heroIndex) {
        if (damageType[0].equals(damageType[heroIndex])) {
            Random random = new Random();
            int randomNumber = random.nextInt(5) + 2;
            System.out.println("................................");
            System.out.println(damageType[heroIndex] + " critically damages Boss " + damage[heroIndex] * randomNumber);
            System.out.println("................................");
            return health[0] - damage[heroIndex] * randomNumber;
        } else {
            return health[0] - damage[heroIndex];
        }
    }


    public static boolean isFinished() {
        if (health[0] <= 0) {
            System.out.println("Heroes won the game!!");
            System.out.println("CONGRATULATIONS!!");
            return true;
        }
        if (health[1] <= 0 && health[2] <= 0 && health[3] <= 0 && health[4] <= 0 && health[5] <= 0) {
            System.out.println("Boss won the game!!");
            System.out.println("GAME OVER");
            return true;
        }
        return false;
    }

    public static void printStatistics() {
        System.out.println("................................");
        System.out.println("Boss health: " + health[0]);
        System.out.println("Boss defence type: " + damageType[0]);
        System.out.println("Warrior health: " + health[1]);
        System.out.println("Magic health: " + health[2]);
        System.out.println("Mental health: " + health[3]);
        System.out.println("Tank health: " + health[4]);
        System.out.println("Medic health: " + health[5]);
        System.out.println("................................");
    }
}
