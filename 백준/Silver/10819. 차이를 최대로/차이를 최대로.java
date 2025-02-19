import java.io.*;
import java.util.*;

class Solution_10819 {

    int n;
    int[] arr;
    int max = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Stack<Integer> stack = new Stack<>();
        boolean[] visit = new boolean[n];
        back(stack, visit);

        System.out.println(max);
        br.close();
    }

    void back(Stack<Integer> stack, boolean[] visit) {
        if (stack.size() == n) {
            cal(stack);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                stack.push(arr[i]);
                visit[i] = true;

                back(stack, visit);

                stack.pop();
                visit[i] = false;
            }
        }
    }

    void cal(Stack<Integer> stack) {
        int[] temp = new int[n];
        int idx = n - 1;
        while (!stack.isEmpty()) {
            temp[idx--] = stack.pop();
        }

        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(temp[i] - temp[i + 1]);
        }

        max = Math.max(max, sum);

        // 다시 stack에 넣기
        for (int i = 0; i < n; i++) {
            stack.push(temp[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_10819 s = new Solution_10819();
        s.solution();
    }
}

/**
 * 모든 경우의 수 -> 8! -> ok
 */