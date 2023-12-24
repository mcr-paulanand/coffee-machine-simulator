package machine;

import java.util.Scanner;

public class CoffeeMachine {

    private static final int[] ML_WATER_PER_COFFEE = {250, 350, 200};
    private static final int[] ML_MILK_PER_COFFEE = {0, 75, 100};
    private static final int[] G_COFFEE_BEANS_PER_COFFEE = {16, 20, 12};
    private static final int[] COST_PER_COFFEE = {4, 7, 6};
    private static int amountOfWater = 400;
    private static int amountOfMilk = 540;
    private static int amountOfCoffeeBeans = 120;
    private static int amountOfCups = 9;
    private static int amountOfMoney = 550;

    public static void main(String[] args) {
        while (true) {
            switch (getAction()) {
                case "buy" -> buyCoffee();
                case "fill" -> fillSupplies();
                case "take" -> takeSupplies();
                case "remaining" -> printSupplies();
                case "exit" -> System.exit(0);
            }
        }
    }

    private static String getAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = new Scanner(System.in).nextLine();
        System.out.println();
        return action;
    }

    private static void buyCoffee() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int coffeeIndex;

        try {
            coffeeIndex = new Scanner(System.in).nextInt() - 1;
        } catch (Exception e) {
            return;
        }

        if (isSuppliesEnough(coffeeIndex)) {
            makeCoffee(coffeeIndex);
        }
    }

    private static void makeCoffee(int coffeeIndex) {
        amountOfWater -= ML_WATER_PER_COFFEE[coffeeIndex];
        amountOfMilk -= ML_MILK_PER_COFFEE[coffeeIndex];
        amountOfCoffeeBeans -= G_COFFEE_BEANS_PER_COFFEE[coffeeIndex];
        amountOfCups -= 1;
        amountOfMoney += COST_PER_COFFEE[coffeeIndex];
    }

    private static void fillSupplies() {
        amountOfWater += getAmount("Write how many ml of water you want to add:");
        amountOfMilk += getAmount("Write how many ml of milk you want to add:");
        amountOfCoffeeBeans += getAmount("Write how many grams of coffee beans you want to add:");
        amountOfCups += getAmount("Write how many disposable cups you want to add:");
        System.out.println();
    }

    private static void takeSupplies() {
        System.out.println("I gave you $" + amountOfMoney);
        System.out.println();
        amountOfMoney = 0;
    }

    private static void printSupplies() {
        System.out.println("The coffee machine has:");
        System.out.println(amountOfWater + " ml of water");
        System.out.println(amountOfMilk + " ml of milk");
        System.out.println(amountOfCoffeeBeans + " g of coffee beans");
        System.out.println(amountOfCups + " disposable cups");
        System.out.println("$" + amountOfMoney + " of money");
        System.out.println();
    }

    private static boolean isSuppliesEnough(int coffeeIndex) {
        if (amountOfWater < ML_WATER_PER_COFFEE[coffeeIndex]) {
            System.out.printf("Sorry, not enough %s!%n%n", "water");
            return false;
        } else if (amountOfMilk < ML_MILK_PER_COFFEE[coffeeIndex]) {
            System.out.printf("Sorry, not enough %s!%n%n", "milk");
            return false;
        } else if (amountOfCoffeeBeans < G_COFFEE_BEANS_PER_COFFEE[coffeeIndex]) {
            System.out.printf("Sorry, not enough %s!%n%n", "coffee beans");
            return false;
        } else if (amountOfCups < 1) {
            System.out.printf("Sorry, not enough %s!%n%n", "cups");
            return false;
        } else {
            System.out.printf("I have enough resources, making you a coffee!%n%n");
            return true;
        }
    }

    private static int getAmount(String message) {
        System.out.println(message);
        return new Scanner(System.in).nextInt();
    }
}
