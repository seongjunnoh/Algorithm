import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>(n);     // 초기 capacity 지정
        String[] temp;
        for (int i = 0; i < n; i++) {
            temp = br.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                int num = Integer.parseInt(temp[j]);

                if (pq.size() == n) {
                    if (pq.peek() < num) {
                        pq.poll();
                        pq.add(num);
                    }
                } else {
                    pq.add(num);
                }
            }
        }

        System.out.println(pq.poll());
        br.close();
    }
}

/**
 * 실버 3 2075번 N번째 큰 수
 *
 * pq는 오름차순 정렬 & n개만 저장
 * pq.poll() 이 정답
 * 
 * ===========================
 * StringTokenizer를 매번 생성하니 메모리 초과 발생하는 듯
 */
