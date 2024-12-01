import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            pq.add(Integer.parseInt(br.readLine()));
        }

        long sum = 0;
        while (pq.size() >= 2) {
            int min = pq.poll();
            int min2 = pq.poll();

            sum += min + min2;
            pq.add(min + min2);
        }

        System.out.println(sum);            // n == 1 일 경우, 0이 정답
        br.close();
    }
}

/**
 * 골드 4 1715번 카드 정렬하기
 *
 * 작은 수부터 먼저 합치기 -> 우선순위 큐가 관리 -> 삽입, 삭제 연산 log n에 수행 가능
 */
