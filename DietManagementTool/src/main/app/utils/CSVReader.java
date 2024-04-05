package main.app.utils;


import main.app.model.DateFood;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class CSVReader {

    public static void GenerateReport(String userName, LocalDate startDate, LocalDate endDate) {
        File dataDir = new File("data");
        File csvFile = new File(dataDir, userName + ".csv");
        String line;
        String csvSplitBy = ",";

        List<DateFood> foods = new ArrayList<>();
        double[] extrapolationResult = new double[10];

        DateFood currentFood = null;


        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            boolean firstLineSkipped = false; // Add a flag to track if the first line has been skipped
            while ((line = br.readLine()) != null) {
                if (!firstLineSkipped) {
                    firstLineSkipped = true;
                    continue; // Skip processing the first line
                }
                String[] data = line.split(csvSplitBy);
                LocalDate foodDate = LocalDate.parse(data[0].replaceAll("'", ""));

                // Check if the food date is within the start and end date range
                if ((foodDate.isAfter(startDate) || foodDate.isEqual(startDate)) && (foodDate.isBefore(endDate) || foodDate.isEqual(endDate))) {
                    // Directly create a new DateFood object and add it to the foods list
                    DateFood foodToAdd = new DateFood(foodDate, data[1].replaceAll("'", ""), data[2].replaceAll("'", ""), Double.parseDouble(data[3]), Double.parseDouble(data[4]), Double.parseDouble(data[5]), Double.parseDouble(data[6]), Double.parseDouble(data[7]), Double.parseDouble(data[8]), Double.parseDouble(data[9]), Double.parseDouble(data[10]), Double.parseDouble(data[11]), Double.parseDouble(data[12]), Double.parseDouble(data[13]));
                    foods.add(foodToAdd);
                }
                // No need to set dataCulled flag here as it's not related to this loop's logic
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.sort(foods);

        // Print out the filtered foods for verification
        for (DateFood food : foods) {
            System.out.println(food);
        }

        double[] totalCaloriesArray = calculateTotalCaloriesPerDate(foods);

        LinearRegression lr = new LinearRegression(totalCaloriesArray);

        for (int i = lr.getAmountOfDaysInData(); i < lr.getAmountOfDaysInData() + 10; i++) {
            System.out.println(lr.extrapolate(i));
            extrapolationResult[i - lr.getAmountOfDaysInData()] = lr.extrapolate(i);
        }


        // Assuming HTMLGenerator is a class you've defined to generate HTML reports
        HTMLGenerator.generateHTML(foods, userName);
    }

    public static double[] calculateTotalCaloriesPerDate(List<DateFood> dateFoods) {
        Map<LocalDate, Double> totalCaloriesMap = new HashMap<>();

        //Calculate total calories per date
        for (DateFood dateFood : dateFoods) {
            LocalDate date = dateFood.getDate();
            double calories = dateFood.getCalories();

            if (totalCaloriesMap.containsKey(date)) {
                //If th calories for this date already exist, update total calories
                double totalCalories = totalCaloriesMap.get(date) + calories;
                totalCaloriesMap.put(date, totalCalories);
            } else {
                //If calories for this date don't exist, add them to the map
                totalCaloriesMap.put(date, calories);
            }
        }

        //Convert the map values to an array
        double[] totalCaloriesArray = new double[totalCaloriesMap.size()];
        int index = 0;
        for (double totalCalories : totalCaloriesMap.values()) {
            totalCaloriesArray[index++] = totalCalories;
        }

        return totalCaloriesArray;
    }


}

