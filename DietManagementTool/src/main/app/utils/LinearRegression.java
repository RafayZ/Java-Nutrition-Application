package main.app.utils;

public class LinearRegression {
    private final double yintercept, gradient;
    private int xlength;

    public LinearRegression(double[] y) {

        double[] x = new double[y.length];


        /*This just adds ascending values to the x array which length corresponds to y*/
        int count = 1; //Starting count
        for (int i = 0; i < y.length; i++) {
            // Set the current element of the result array to the current count
            x[i] = count;
            count++; // Increment count
        }

        xlength = x.length;

        //We are using the least squares method: https://www.mathsisfun.com/data/least-squares-regression.html

        /*Step 1: For each (x,y) point calculate x2 and xy*/
        //Step 2: Sum all x, y, x2 and xy, which gives us Σx, Σy, Σx2 and Σxy (
        double sumOfx = 0.0, sumOfy = 0.0, sumOfxSquared = 0.0;
        for (int i = 0; i < xlength; i++)
        {
            sumOfx  += x[i];
            sumOfxSquared += x[i]*x[i];
            sumOfy  += y[i];
        }

        double xbar = sumOfx / xlength;
        double ybar = sumOfy / xlength;
        //double mtemp = sumOfx - sumOfxSquared;


        /*Step 3: Calculate gradient:*/
        double xxbar = 0.0, xybar = 0.0;
        for (int i = 0; i < xlength; i++)
        {
            xxbar += (x[i] - xbar) * (x[i] - xbar);
            xybar += (x[i] - xbar) * (y[i] - ybar);
        }
        gradient  = xybar / xxbar;
        //Step 4: Calculate Intercept b:
        yintercept = ybar - gradient * xbar;


        //with the things we calculate, we get a linear equation that we use to extrapolate the data
    }

    /*
    This extrapolates the data given to the class and returns a y value (Calories)

    The x variable represents the day
    */
    public double extrapolate(double x) {
        return gradient * x + yintercept;
        // 1.5 x (day)5 + 200cal for example
    }

    /*So if you want to extrapolate, get the day + this number for values that you want to predict*/
    public int getAmountOfDaysInData()
    {
        return xlength;
    }

}