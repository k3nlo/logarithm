import java.util.Scanner;

public class Calculator {
  public static void main(String[] args) {
    System.out.println("Available Functions:");
    System.out.println("1: log_b(x)");

    //        String op = scanner.next();
    //        int right = scanner.nextInt();

    // initialization
    int choice = 0;
    choice = getChoice(choice);

    if (choice == 1) {

      double variable = Double.NaN;
      double base = Double.NaN;

      do {
        base = getBase();

      } while (Double.isNaN(base));

      do {
        variable = getVariable();

      } while (Double.isNaN(variable));

      System.out.println("base = " + base);
      System.out.println("x = " + variable);
      //      double x = 2;
      //      double x = 250;
      //      double y = 0.5;
      //      double result = Math.pow(x, y);
      //      double result = power(x, y);
      double result = log(base, variable);
      //      double math_res = Math.pow(x, y);
      //      double math_res = Math.log(x);
      System.out.println("log_" + base + "(" + variable + ") = " + result);
      //      System.out.println("math_res = " + math_res);
      //
    }
  }

  /**
   * @param choice int number of the selected function
   * @return valid choice
   */
  public static int getChoice(int choice) {
    do {
      System.out.println("Enter the index of the function you would like to use:");
      Scanner scanner = new Scanner(System.in);
      if (scanner.hasNextInt()) {
        choice = scanner.nextInt();
      }
      if (choice != 1) {
        System.out.println("Chosen function is not available. Please try again.");
      }

    } while (choice != 1);
    return choice;
  }

  public static double getBase() {
    double input = Double.NaN;
    try {
      System.out.println("Input a logarithm base:");
      Scanner scanner = new Scanner(System.in);
      if (scanner.hasNextDouble()) {
        input = scanner.nextDouble();

        if (input < 0) {
          input = Double.NaN;
          throw new IllegalArgumentException(
              "The base of a logarithm must " + "be positive. Please try again.");
        }

        if (input == 0 || input == 1) {
          input = Double.NaN;
          throw new IllegalArgumentException(
              "The base of a logarithm must not be equal to 0 or 1." + " Please try again.");
        }
      } else {
        input = Double.NaN;
        throw new IllegalArgumentException(
            "The base of a logarithm must be a real number. Please try again.");
      }

    } catch (Exception e) {
      System.err.println(e.getMessage());
    }
    return input;
  }

  public static double getVariable() {
    double input = Double.NaN;
    try {

      System.out.println("Input the variable x value:");
      Scanner scanner = new Scanner(System.in);
      if (scanner.hasNextDouble()) {
        input = scanner.nextDouble();

        if (input < 0 || input == 0) {
          input = Double.NaN;
          throw new IllegalArgumentException(
              "The logarithm function is only defined for x within ]0;+\u221e]. Please try again.");
        }

      } else {
        input = Double.NaN;
        throw new IllegalArgumentException(
            "The variable for a logarithm must be a real number. Please try again.");
      }
    } catch (Exception e) {
      System.err.println(e.getMessage());
    }

    return input;
  }

  public static double log(double base, double x) {
    double result = 0;
    result = ln(x) / ln(base);
    return result;
  }

  public static double ln(double x) {
    double result = 0;
    double n = 100000000.0; // for increased precision
    double exponent = 1 / n;
    result = n * (power(x, exponent) - 1);
    //    result = power(x, y);
    return result;
  }

  // reference for square and power functions:
  // https://stackoverflow.com/questions/3518973/doubleing-point-exponentiation-without-power-function
  static double square(double x) {
    return x * x;
  }
  // meaning of 'precision': the returned answer should be base^x, where
  //                         x is in [power-precision/2,power+precision/2]
  static double power(double base, double power, double precision) {
    if (power < 0) return 1 / power(base, -power, precision);
    if (power >= 10) return square(power(base, power / 2, precision / 2));
    if (power >= 1) return base * power(base, power - 1, precision);
    if (precision >= 1) return sqrt(base);
    return sqrt(power(base, power * 2, precision * 2));
  }

  static double power(double base, double power) {
    return power(base, power, .0000000000000001);
  }
  // reference for square root function:
  // https://rekinyz.wordpress.com/2015/01/20/implement-simple-sqrt-with-java/
  static double sqrt(double x) {
    if (x == 0) return 0;
    double last = 0.0;
    double res = 1.0;
    while (res != last) {
      last = res;
      res = (res + x / res) / 2;
    }
    return res;
  }
}
