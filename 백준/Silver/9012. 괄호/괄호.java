import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            Stack<Character> stack = new Stack<>();
            String input = br.readLine();
            boolean isAlreadyNo = false;

            for (int j = 0; j < input.length(); j++) {
                Character c = input.charAt(j);
                if (c == '(') {
                    stack.push(c);
                } else if (c == ')') {
                    if (stack.isEmpty() || stack.peek() != '(') {
                        sb.append("NO").append("\n");
                        isAlreadyNo = true;
                        break;
                    }
                    stack.pop();
                }
            }

            if (!isAlreadyNo) {
                if (stack.isEmpty()) sb.append("YES").append("\n");
                else sb.append("NO").append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}