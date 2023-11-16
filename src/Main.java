import models.Person;
import models.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    static Map<String, Person> peopleMap = Map.of(
            "AHMET", new Person("AHMET", 17, 100),
            "AYSE", new Person("AYSE", 28, 500),
            "MEHMET", new Person("MEHMET", 32, 150),
            "SENA", new Person("SENA", 30, 250)
    );

    static List<Restaurant> restaurantList = List.of(
            new Restaurant("ARKABAHCE", 300),
            new Restaurant("KEBAP49", 750),
            new Restaurant("MASABASİ", 1000),
            new Restaurant("KUCUKITALYA", 500)
    );

    public static void main(String[] args) {

        List<String> guestNames = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        String entry;
        boolean done = false;
        System.out.println("Enter guest name (Enter DONE when done) : ");
        while (!done) {
            entry = scanner.nextLine().toUpperCase();
            done = entry.contains("DONE");

            if (!done && peopleMap.containsKey(entry)) {
                guestNames.add(entry);
                System.out.println("Next guest : ");
            } else if (!done && !peopleMap.containsKey(entry)) {
                System.out.println("Invalid name!");  return;
            }
            else done = true;
        }

        System.out.println("Which restaurant do you want to go : ");
        String restaurantName = scanner.nextLine();
        scanner.close();
        getMinimumAmountFor(restaurantName);
        int totalBudget = getTotalBudget(guestNames);
        System.out.println("Your total budget : "+totalBudget);

        for (Restaurant restaurant : restaurantList){
            if (restaurantName.equalsIgnoreCase(restaurant.getName())){
                boolean availability = minimumAmountCheck(totalBudget,restaurant.getMinimumAmount());
                if (!availability ) System.out.println("Welcome to "+restaurantName);
                else {
                    System.out.println("You cannot have dinner in "+restaurantName+".Check for another restaurant!");
                } return;
            }
        }
    }

    public static void getMinimumAmountFor(String restaurantName) {
        for (Restaurant restaurant : restaurantList) {
            if (restaurantName.equalsIgnoreCase(restaurant.getName())) {
                System.out.println(restaurantName + " requests a minimum order of " + restaurant.getMinimumAmount() + "TL!");
                return;
            }
        }
        System.out.println("Invalid restaurant name!");
    }


    public static int getTotalBudget(List<String> guestName) {
        int totalBudget = 0;
        for (String name : guestName) {
            totalBudget += peopleMap.get(name).getBudget();
        }
        return totalBudget;
    }

    public static boolean minimumAmountCheck(int totalBudget, int minimumAmount){
            return totalBudget < minimumAmount;
    }







}







