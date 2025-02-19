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

        int[] select = new int[n];
        boolean[] visit = new boolean[n];
        back(select, visit, 0);

        System.out.println(max);
        br.close();
    }

    void back(int[] select, boolean[] visit, int idx) {
        if (idx == n) {
            cal(select);
            return;
        }

        for (int i = 0; i < n; i++) {
            if (!visit[i]) {
                visit[i] = true;
                select[idx] = arr[i];

                back(select, visit, idx + 1);

                visit[i] = false;
            }
        }
    }

    void cal(int[] select) {
        int sum = 0;
        for (int i = 0; i < n - 1; i++) {
            sum += Math.abs(select[i] - select[i + 1]);
        }

        max = Math.max(max, sum);
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
 * 굳이 stack 사용할 필요는 없다
 */