package com.company;

import java.util.Random;
import java.lang.Math;

public class Main {
    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefence = "";
    public static int[] heroesHealth = {260, 250, 270, 350, 100, 300, 280,400};
    public static int[] heroesDamage = {15, 20, 10, 0, 10, 25, 15, 5};
    public static String[] heroesAttackType = {
            "Physical", "Magical", "Kinetic", "Medical", "Lucky", "Thor", "Berserk", "Golem"};
    public static int MedicSupport = randHealth();

    public static void main(String[] args) {
        printStatistics();
        while (!isGameFinished()) {
            round();
        }
    }

    public static void round() {
        isMedicDie();
        System.out.println("++++++++++++++");
        if (bossHealth > 0) {
            chooseBossDefence();
            bossHits();
        }


        heroesHit();
        lucky();
        Thor();
        Golem();
        Berserk();
        printStatistics();
        BossIsBack();

    }


    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefence = heroesAttackType[randomIndex];
        System.out.println("Boss choose: " + bossDefence);
    }


    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }

        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 &&

                heroesHealth[2] <= 0) {

            System.out.println("Boss won!!!");

            return true;

        }*/

        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }




    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (bossDefence == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println(
                            "Critical Damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i];
                    }
                }
            }
        }
    }




    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && bossHealth > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }


 public static void printStatistics() {
        System.out.println("___________________");
        System.out.println("Boss health: " + bossHealth + " [" + bossDamage + "]");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: " + heroesHealth[i] + " [" + heroesDamage[i] + "]");
        }
        System.out.println("_____________________");
    }


    public static int randHealth() {
        int min = 20;
        int max = 40;
        int randomHealth = min + (int) (Math.random() * max);
        return randomHealth;
    }

    public static boolean isMedicDie() {
        if (heroesHealth[3] <= 0) {
            System.out.println("Medic is die");
            return true;
        }
        MedicWorks();
        return false;
    }


    public static void MedicWorks() {
        Random random = new Random();
        int a = random.nextInt(3);

        for (int i = 0; i < heroesHealth[a]; i++) {
            if (heroesHealth[a] < 100 && heroesHealth[a] > 0) {
                heroesHealth[a] = heroesHealth[a] + MedicSupport;
                System.out.println("Medic healed the " + heroesAttackType[a] + " on " + MedicSupport + " health point");
            }
        }
    }




    public static void lucky() {
        Random random = new Random();
        boolean isLuckyEvaded = random.nextBoolean();
        if (heroesHealth[4] > 0) {
            if (!(isLuckyEvaded)) {
                heroesHealth[4] = heroesHealth[4] + bossDamage;
                if (heroesHealth[4] > 130) {
                    heroesHealth[4] = heroesHealth[4] - bossDamage;
                }
                System.out.println("Lucky evaded");
            } else if (isLuckyEvaded) {
                heroesHealth[4] = heroesHealth[4];
            }
        }
    }


    public static void Thor() {
        Random random = new Random();
        boolean superOfThorIsTrue = random.nextBoolean();
        if (superOfThorIsTrue && heroesHealth[5] > 0) {
            bossDamage = 0;
            System.out.println("Boss is stunned ");
        } else if (!(superOfThorIsTrue) && heroesHealth[5] > 0) {
            System.out.println("Boss isn't stunned ");
        }
    }




    public static void BossIsBack() {
        bossDamage = 50;
    }




    public static void Berserk() {
        Random random = new Random();
        int protectionOfBerserk = random.nextInt(25) + 1;
        if(heroesHealth[6] > 0 ) {
            heroesHealth[6] = (heroesHealth[6] - bossDamage) + protectionOfBerserk;
            heroesDamage[6] = heroesDamage[6] + protectionOfBerserk;
            System.out.println("Berserk activated SUPER");
        }
        if(heroesHealth[6] < 0){
            heroesHealth[6] = 0;
        }
    }
    public static void Golem (){
        Random random = new Random();
        int Random = random.nextInt(3) + 1;
        for (int i = 0; i < heroesHealth.length; i++) {
            heroesHealth[i] = heroesHealth[i] + 12;
        }
        heroesHealth[7] = heroesHealth[7] - 72;
        System.out.println("Golem исползовал супер спасобность");

    }

}