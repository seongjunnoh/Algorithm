import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static int[] arr;
    static StringBuilder sb = new StringBuilder();
    static int[] result;
    static int size;
    static boolean[] visit;

    static void dfs(int start, int depth) {
        // start : arr에서 방문을 시작할 첫번째 index

        if (depth == 6) {
            for (int i = 0; i < 6; i++) {
                sb.append(result[i] + " ");
            }
            sb.append("\n");

            return;
        }

        for (int i = start; i < size; i++) {
            result[depth] = arr[i];
            dfs(i + 1, depth + 1);
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        while (true) {
            st = new StringTokenizer(br.readLine());
            int k = Integer.parseInt(st.nextToken());

            if (k == 0) {
                bw.write(sb.toString());
                bw.flush();
                bw.close();
                br.close();

                return;
            }

            arr = new int[k];
            visit = new boolean[k];
            result = new int[6];
            size = k;

            for (int i = 0; i < k; i++) {
                arr[i] = Integer.parseInt(st.nextToken());
            }

            dfs(0, 0);

            sb.append("\n");
        }
    }
}