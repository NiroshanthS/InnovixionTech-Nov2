import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CommandLineCalculator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the Command Line Calculator!");

        while (true) {
            try {
                double operand1 = getOperand("Enter the first operand:");
                String operator = getOperator();
                double operand2 = getOperand("Enter the second operand:");

                Map<String, Object> expressionMap = new HashMap<>();
                expressionMap.put("operand1", operand1);
                expressionMap.put("operand2", operand2);
                expressionMap.put("operator", operator);

                double result = evaluateExpression(expressionMap);
                System.out.println("Result: " + result);

            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Please enter valid numbers.");
            } catch (IllegalArgumentException | ArithmeticException e) {
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("Unexpected error occurred: " + e.getMessage());
            }

            System.out.print("Do you want to perform another calculation? (yes/no): ");
            String continueChoice = scanner.nextLine().toLowerCase();

            if (!continueChoice.equals("yes")) {
                System.out.println("Exiting the calculator. Goodbye!");
                break;
            }
        }

        scanner.close();
    }

    private static double getOperand(String prompt) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print(prompt + " ");
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid number format. Please enter a valid number.");
            }
        }
    }

    private static String getOperator() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Enter the operator (+, -, *, /): ");
            String operator = scanner.nextLine();

            if ("+-*/".contains(operator)) {
                return operator;
            } else {
                System.out.println("Error: Invalid operator. Supported operators: +, -, *, /");
            }
        }
    }

    private static double evaluateExpression(Map<String, Object> expressionMap) {
        double operand1 = (double) expressionMap.get("operand1");
        double operand2 = (double) expressionMap.get("operand2");
        String operator = (String) expressionMap.get("operator");

        switch (operator) {
            case "+":
                return operand1 + operand2;
            case "-":
                return operand1 - operand2;
            case "*":
                return operand1 * operand2;
            case "/":
                if (operand2 == 0) {
                    throw new ArithmeticException("Division by zero is not allowed.");
                }
                return operand1 / operand2;
            default:
                throw new IllegalArgumentException("Invalid operator. Supported operators: +, -, *, /");
        }
    }
}
