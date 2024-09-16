import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static int n, s;
    static int[] arr;
    static int result = 0;

    static void dfs(int index, int sum) {
        if (index == n) {
            if (sum == s) result++;
            return;
        }

        dfs(index + 1, sum + arr[index]);           // index 번째 원소를 더한 경우
        dfs(index + 1, sum);                             // index 번째 원소를 더하지 않은 경우
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        if (s == 0) {
            // 예외처리 : s == 0 인 경우 아무것도 부분수열의 크기가 0이어도 result++ 되므로 미리 하나 뺴주기
            result = -1;
        }

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}