import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int[] input;

    static void dfs(int start, int depth) {
        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        int before = 0;             // 해당 depth의 이전 값
        for (int i = start; i < n; i++) {
            if (before == input[i]) continue;       // 중복 결과 제거

            arr[depth] = input[i];
            before = input[i];
            dfs(i + 1, depth + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        input = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);         // input 배열 오름차순 정렬

        dfs(0, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
