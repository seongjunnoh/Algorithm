import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pq = new PriorityQueue<>();

            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < k; j++) {
                pq.add((long) Integer.parseInt(st.nextToken()));        // 업캐스팅
            }

            long sum = 0;
            while (pq.size() > 1) {
                Long first = pq.poll();
                Long second = pq.poll();
                sum += first + second;

                pq.add(first + second);
            }

            sb.append(sum).append("\n");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 13975번 파일 합치기 3
 *
 * 비용이 큰 것을 최대한 늦게 임시파일로 합쳐야 정답
 * -> 작은 것부터 더해가면 된다
 *
 */