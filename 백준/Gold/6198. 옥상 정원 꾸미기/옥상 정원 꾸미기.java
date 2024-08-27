import java.io.*;
import java.util.Stack;

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Stack<Integer> stack = new Stack<>();

        long result = 0;            // 결과 타입 : long 이어야 함
        for (int i = 0; i < n; i++) {
            int height = Integer.parseInt(br.readLine());

            while (!stack.isEmpty() && stack.peek() <= height) {
                // 스택의 건물이 i번째 건물을 볼 수 없으면 pop
                stack.pop();
            }

            result += stack.size();         // stack.size() : i번째 건물을 볼 수 있는 건물의 수
            stack.push(height);             // i번째 건물은 stack에 push
        }

        bw.write(String.valueOf(result));
        bw.flush();
        br.close();
        bw.close();
    }
}
