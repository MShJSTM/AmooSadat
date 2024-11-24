public class App {
    public static void main(String[] args) throws Exception {
        String num1 = "111111111111111111111111123232323232";
        String num2 = "1000000000000";
        int num1pointer = num1.length() - 1;
        int num2pointer = num2.length() - 1;
        String result = "";
        int carry = 0;
        result = addNumbers(num1, num2, num1pointer, num2pointer, carry);
        System.out.println(result);
        String product = multiplyNumbers(num1, num2, num1.length() - 1, num2.length() - 1, "0");
        System.out.println(product);
    }
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
}
