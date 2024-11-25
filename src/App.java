public class App {
    public static void main(String[] args) throws Exception {
        String num1 = "100000000000000000000000000000000000000000000";
        String num2 = "1000000000000";

        // Perform operations
        String result = addNumbers(num1, num2, num1.length() - 1, num2.length() - 1, 0);
        System.out.println("Addition: " + result);

        String product = multiplyNumbers(num1, num2, num1.length() - 1, num2.length() - 1, "0");
        System.out.println("Multiplication: " + product);

        String diff = diffrentioation(num1, num2, num1.length() - 1, num2.length() - 1, 0);
        System.out.println("Subtraction: " + diff);

        String division = distribution(num1, num2, 0, 0);
        System.out.println("Division: " + division);
    }

    // Recursive addition of large numbers
    public static String addNumbers(String num1, String num2, int num1pointer, int num2pointer, int carry) {
        if (num1pointer < 0 && num2pointer < 0 && carry == 0) {
            return "";
        }
        int digit1 = num1pointer >= 0 ? num1.charAt(num1pointer) - '0' : 0;
        int digit2 = num2pointer >= 0 ? num2.charAt(num2pointer) - '0' : 0;
        int sum = digit1 + digit2 + carry;
        carry = sum / 10;
        sum = sum % 10;
        return addNumbers(num1, num2, num1pointer - 1, num2pointer - 1, carry) + sum;
    }

    // Recursive multiplication of large numbers
    public static String multiplyNumbers(String num1, String num2, int i, int j, String result) {
        if (i < 0) {
            return result;
        }
        if (j < 0) {
            return multiplyNumbers(num1, num2, i - 1, num2.length() - 1, result);
        }
        int mul = (num1.charAt(i) - '0') * (num2.charAt(j) - '0');
        String intermediateResult = addIntermediateResult(result, mul, num1.length() - 1 - i + num2.length() - 1 - j);
        return multiplyNumbers(num1, num2, i, j - 1, intermediateResult);
    }

    private static String addIntermediateResult(String result, int mul, int position) {
        if (position >= result.length()) {
            result = "0".repeat(position - result.length() + 1) + result;
        }
        int sum = (result.charAt(result.length() - 1 - position) - '0') + mul;
        String newResult = result.substring(0, result.length() - 1 - position) + (sum % 10) + result.substring(result.length() - position);
        if (sum >= 10) {
            newResult = addIntermediateResult(newResult, sum / 10, position + 1);
        }
        return newResult;
    }

    // Recursive subtraction of large numbers
    public static String diffrentioation(String num1, String num2, int num1pointer, int num2pointer, int carry) {
        if (num1pointer < 0 && num2pointer < 0 && carry == 0) {
            return "";
        }

        int digit1 = num1pointer >= 0 ? num1.charAt(num1pointer) - '0' : 0;
        int digit2 = num2pointer >= 0 ? num2.charAt(num2pointer) - '0' : 0;
        int diff = digit1 - digit2 - carry;
        if (diff < 0) {
            diff += 10;
            carry = 1;
        } else {
            carry = 0;
        }

        String result = diffrentioation(num1, num2, num1pointer - 1, num2pointer - 1, carry) + diff;

        // Trim leading zeros
        if (num1pointer == 0 && num2pointer == 0) {
            return result.replaceFirst("^0+(?!$)", "");
        }

        return result;
    }

    // Recursive division of large numbers
    public static String distribution(String num1, String num2, int index, int remainder) {
        if (index >= num1.length()) {
            return "";
        }

        int currentDigit = remainder * 10 + (num1.charAt(index) - '0');
        int divisor = parsePartialNumber(num2);

        if (divisor == 0) {
            throw new ArithmeticException("Division by zero is not allowed.");
        }

        int quotient = currentDigit / divisor;
        int newRemainder = currentDigit % divisor;

        return quotient + distribution(num1, num2, index + 1, newRemainder);
    }

    private static int parsePartialNumber(String num2) {
        String trimmedNum2 = num2.replaceFirst("^0+(?!$)", "");
        int maxDigits = 9; // int's limit is about 10 digits
        String partialNum2 = trimmedNum2.substring(0, Math.min(trimmedNum2.length(), maxDigits));
        return Integer.parseInt(partialNum2);
    }
}
