import java.util.Scanner;

public class App {
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        start();
    }

    private static void start(){
        double[] lucas = {2d, 1d};
        double[] fibonacci = {1d, 1d};
        NumberSeries series;
        int n;
        String cosmetic;

        System.out.println("Please select a series to calculate:\n1. Lucas Series\n2. Fibonacci Series\n3. Custom Series");

        while(true){
            switch(in.nextLine()){
                case "1":
                    series = new NumberSeries(lucas);
                    cosmetic = "L";
                    break;
                case "2":
                    series = new NumberSeries(fibonacci);
                    cosmetic = "F";
                    break;
                case "3":
                    series = customSeriesInput();
                    cosmetic = "C";
                    break;
                default:
                    System.out.println("Please enter a valid selection!");
                    continue;
                } 
                break;
            }

        System.out.println("Please enter the number you would like to calcualte in the series: (must be positive)");
        while(true){
            try {
                n = Integer.parseInt(in.nextLine());
                if(n < 0){
                    System.out.println("Please enter a positive number!");
                    continue;
                }
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number!");
                continue;
            }
            break;
        }



        System.out.println("Would you like to [T]ime the calculation, [S]uccessive calculation time comparison, or just to [C]alculate the number?");
        while(true){
            switch(in.nextLine().toUpperCase()){
                case "T":
                    for(int i = 0; i <= n; i++) {
                        System.out.println(String.format("Time(%s(%d)) took: %.0fns", cosmetic, i, timed(series, i)));
                    }
                    break;
                case "S":
                    timedSuccessive(series, n);
                    break;
                case "C":
                    for(int i = 0; i <= n; i++){
                        System.out.println(String.format("%s(%d) = %.0f", cosmetic, i, Math.floor(series.calculate(i)))); 
                    }
                    break;
                default:
                    System.out.println("Please enter a valid selection!");
                    continue;
                } 
                break;
            }

    }
   
    /**
     * Prompts the user for a custom series and its n0 and n1 values.
     * @return the customized NumberSeries object.
     */
    private static NumberSeries customSeriesInput(){
        double[] custom;
        double n0;
        double n1;

        System.out.println("Please enter the first number in the series:");
        while(true){ // wait until input is valid.
            try {
                n0 = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException e) { //means this is not a double input.
                System.out.println("Please enter a valid number!");
            }
        }

        System.out.println("Please enter the second number in the series:");
        while(true){ // wait until input is valid.
            try {
                n1 = Double.parseDouble(in.nextLine());
                break;
            } catch (NumberFormatException e) { //means this is not a double input.
                System.out.println("Please enter a valid number!");
            }
        }

        custom = new double[]{n0, n1};
        return new NumberSeries(custom);
    }

    /**
     * Times the calculation of the nth number in the series.
     * @param series The series to calculate.
     * @param n The number to calculate until.
     * @return A Timed object containing time, and num-value.
     */
    private static double timed(final NumberSeries series, final int n){
        double startTime = System.nanoTime();
        series.calculate(n);
        double endTime = System.nanoTime();

        return (endTime - startTime);
    }

    private static void timedSuccessive(final NumberSeries series, final int n){
        double n1Time = timed(series, n + 1);
        System.out.println(n1Time);

        double nTime = timed(series, n);
        System.out.println(nTime);

        double timeRatio = n1Time / nTime;

        System.out.println(String.format("The ratio of %d (%.2f ms) to %d (%.2f ms) is %.4f", (n + 1), n1Time, n, nTime, timeRatio));

    }
}
