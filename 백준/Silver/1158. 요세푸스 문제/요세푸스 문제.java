import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {

        int n, k;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        Queue<Integer> q = new LinkedList<>();
        Queue<Integer> result = new LinkedList<>();

        for (int i = 0; i < n; i++) {
            q.offer(i+1);
        }

        int cnt = 1;
        while (!q.isEmpty()) {
            if (cnt == k) {
                result.offer(q.poll());
                cnt = 1;
                continue;
            }
            q.offer(q.poll());
            cnt++;
        }

        bw.write("<");
        for (int i = 0; i < n; i++) {
            bw.write(String.valueOf(result.poll()));
            if (i < n - 1) {
                bw.write(", ");
            }
        }
        bw.write(">");

        bw.flush();
        bw.close();
    }
}

/**
 * 실버 4 1158 요세푸스 문제
 *
 * 큐 활용 -> 조건에 걸리면 poll , 아니면 poll 후 offer
 */