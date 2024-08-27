import java.io.*;
import java.util.*;

class Top {
    int num;            // 1부터 시작하는 번호
    int height;         // 높이

    Top(int num, int height) {
        this.num = num;
        this.height = height;
    }
}

public class Main {

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        StringBuilder result = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Stack<Top> stack = new Stack<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            int height = Integer.parseInt(st.nextToken());      // height : 입력

            if (i == 1) {
                // 가장 처음 입력의 정답은 항상 0
                result.append("0 ");
                stack.push(new Top(i, height));
                continue;
            }
            while (true) {
                if (stack.isEmpty()) {
                    // stack이 비어있음 == 현재 탑의 높이가 최고 -> 현재 탑을 stack에 push
                    result.append("0 ");
                    stack.push(new Top(i, height));
                    break;
                }

                Top top = stack.peek();     // top : stack의 peek

                if (top.height > height) {
                    // 입력이 stack의 peek 보다 작은 경우
                    result.append(top.num).append(" ");
                    stack.push(new Top(i, height));
                    break;
                }
                // 입력이 stack의 peek 보다 큰 경우
                stack.pop();
            }
        }

        bw.write(result.toString());
        bw.flush();
        br.close();
        bw.close();
    }

}