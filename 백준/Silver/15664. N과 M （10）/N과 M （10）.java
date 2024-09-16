import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int n, m;
    static StringBuilder sb;
    static int[] arr;
    static int[] input;
    static LinkedHashSet<String> ans = new LinkedHashSet<>();

    static void dfs(int start, int depth) {
        if (depth == m) {
            sb = new StringBuilder();
            for (int i = 0; i < m; i++) {
                sb.append(arr[i]).append(" ");
            }

            ans.add(sb.toString());         // hashSet에 add -> 중복된 결과이면 자동으로 삭제해줌

            return;
        }

        for (int i = start; i < n; i++) {
            arr[depth] = input[i];
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

        for (String s : ans) {
            bw.write(s + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}