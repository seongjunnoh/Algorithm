import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        PriorityQueue<Long> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            pq.add(Long.parseLong(st.nextToken()));
        }

        for (int i = 0; i < m; i++) {
            Long min = pq.poll();
            Long secondMin = pq.poll();

            long sum = min + secondMin;

            pq.add(sum);
            pq.add(sum);
        }

        int size = pq.size();
        long result = 0;
        for (int i = 0; i < size; i++) {
            result += pq.poll();
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 실버 1 15903 카드 합체 놀이
 *
 * 현재 arr 에 있는 수 들 중, 가장 작은 2개의 수를 골라서 합체를 해야함
 * -> arr의 수들을 우선순위 큐로 관리 & 가장 작은 2개의 수를 log n 만에 찾아야 함
 */