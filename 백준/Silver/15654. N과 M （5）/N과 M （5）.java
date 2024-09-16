import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int n, m;
    static StringBuilder sb = new StringBuilder();
    static int[] arr;
    static int[] input;
    static boolean[] visit;

    static void dfs(int depth) {
        // start : input 의 시작 index

        if (depth == m) {
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }
            sb.append("\n");

            return;
        }

        for (int i = 0; i < n; i++) {
            if (visit[i] == false) {
                visit[i] = true;
                arr[depth] = input[i];
                dfs(depth + 1);
                visit[i] = false;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[m];
        visit = new boolean[n];
        input = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            input[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(input);         // input 배열 오름차순 정렬

        dfs(0);

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
