import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class BAEKJOON_Gold_4_후위_표기식 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputExpression = br.readLine();

        System.out.println(convertToPostfix(inputExpression));

        br.close();
    }

    private static String convertToPostfix(String expression) {
        StringBuilder result = new StringBuilder();

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < expression.length(); i++) {
            char c = expression.charAt(i);

            if (Character.isAlphabetic(c)) {
                result.append(c);
            } else {
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    while (stack.peek() != '(') {
                        result.append(stack.pop());
                    }
                    stack.pop();
                } else if (c == '*' || c == '/') {
                    while (!stack.isEmpty() && (stack.peek() == '*' || stack.peek() == '/')) {
                        result.append(stack.pop());
                    }
                    stack.push(c);
                } else if (c == '+' || c == '-') {
                    while (!stack.isEmpty() && stack.peek() != '(') {
                        result.append(stack.pop());
                    }
                    stack.push(c);
                }
            }
        }

        while (!stack.isEmpty()) {
            result.append(stack.pop());
        }

        return result.toString();
    }
}
