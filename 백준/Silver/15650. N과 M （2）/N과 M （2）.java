import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int n, m;
    static int[] arr;               // 출력할 숫자를 저장할 배열
    static boolean[] visit;
    static StringBuilder sb;

    static void dfs(int start, int depth) {
        // start 부터 depth 깊이까지 dfs

        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i] + " ");
            }
            sb.append("\n");
            return;
        }

        for (int i = start; i <= n; i++) {
            arr[depth] = i;
            dfs(i + 1, depth + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        sb = new StringBuilder();

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];

        dfs(1, 0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}