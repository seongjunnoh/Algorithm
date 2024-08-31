import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        while (true) {
            String input = br.readLine();
            if (input.equals(".")) {
                break;
            }

            Stack<Character> stack = new Stack<>();
            boolean isAlreadyNo = false;

            for (int i = 0; i < input.length(); i++) {
                Character c = input.charAt(i);
                if (c == '(' || c == '[') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        sb.append("no").append("\n");
                        isAlreadyNo = true;
                        break;
                    }
                    stack.pop();
                } else if (c == ']') {
                    if (stack.isEmpty() || stack.peek() != '[') {
                        sb.append("no").append("\n");
                        isAlreadyNo = true;
                        break;
                    }
                    stack.pop();
                }
            }

            if (!isAlreadyNo) {
                if (stack.isEmpty()) sb.append("yes");
                else sb.append("no");
                sb.append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}