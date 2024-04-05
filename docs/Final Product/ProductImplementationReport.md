# Team Implementation Report
***This section describes the technical details of our software.***

## Technical Diagrams
### Class Diagram
_Access Our Class Diagram Here:_ https://drive.google.com/file/d/1zWeMs5QCKqbL_DjQq0YfalbTj-4SDdiu/view?usp=sharing

## Technical Description

### Account Creation and Login:

- Users can create an account by providing a username and password.
- Upon logging in, users are presented with three main options to manage their diet effectively.

### Nutrition Calculator:

- This feature takes user details as input, such as age, gender, weight, height, activity level, and goal (e.g., weight loss, maintenance, or muscle gain).
- The provided details are used to instantiate an instance of the Person class.
- Using metrics sourced from healthcare professionals on the internet, the app calculates the user's optimal diet in terms of calories, protein, carbohydrates, fats, and other nutrients.
- The calculated nutritional values are personalized based on the user's profile and goals.

### Food Log:

- Users can log their food consumption through this feature.
- Users enter the name of the food item they consumed.
- The app utilizes the CalorieNinjas API to fetch the nutritional information of the food item.
- An instance of the Food class is created with the retrieved nutritional information.
- Users can categorize their food items into meals (e.g., breakfast, lunch, dinner, snacks) and specify the date and meal name.
- The app creates an instance of the Meal class to store custom meals created by the user.

### Unique Report Generation:

- This feature generates a comprehensive report to provide a better understanding of the user's dietary habits and nutritional intake.
- The report is HTML-based and visually appealing.
- It displays the user's created meals along with their corresponding nutritional information.
- Additionally, the report includes a graph illustrating the trend of the user's past caloric intake.
- A prediction for future caloric intake is generated using a Linear Regression Algorithm, providing users with insights into their potential future dietary patterns.
- The report is interactive, allowing users to explore their data in detail and make informed decisions about their diet and nutrition goals.

*A technical achievement we would like to highlight is our completed Linear Regression Analysis Algorithm. This graph functions as both a display of dietary trends and a dynamically updated prediction model based on real-time user data.*

## Algorithms and Data Structures
### Linear Regression:
- Linear regression is an algorithm that provides a linear relationship between an independent variable and a dependent variable to predict the outcome of future events. It is a statistical method used in data science and machine learning for predictive analysis. 
- The time complexity is O((n^2)m) where n is the number of samples and m is the number of "features". The space complexity is O(1), as it does not require additional memory proportional to the input size.
- For our implementation n would be the number of predictive data points in our graph while m would be 1 since this is simple linear regression (only 1 independent variable)

### csvReader.java:
This class contains two methods - **generateReport** and **calculateTotalCaloriesPerDate** which make use of a number of data structures namely Arrays and Maps.

- An ArrayList is used to store DateFood objects then sort them using the Collections class. The time complexity of insertion and deletion at the end is O(1) on average, but O(n) when performed at the beginning or middle of the list, retrieval time complexity is O(1). The space complexity of an ArrayList is O(n), where 'n' is the number of elements stored, as it dynamically resizes its internal array when needed.

- A HashMap is used to pair totalCalories values with dates for the purpose of plotting them on a graph, these values are then stored in another Array. The time complexity of using a hashmap for insertion, deletion, and retrieval operations is generally O(1) on average, but can degrade to O(n) in worst-case scenarios. The space complexity of a hashmap is O(n) in the worst-case scenario, where 'n' is the number of elements stored, due to potential collisions and resizing.

## Imported Libraries 
### CalorieNinjas API:
We use this external library to get the nutritional information for a food item passed as a string argument. This information is stored in a String Vector, then used to create an instance of the Food class. The created Food object is later used to create our user's custom meals.

## Known Issues
We were able to resolve all of our bugs without the need of a workaround.
