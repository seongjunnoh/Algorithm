import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int weight;
        int value;

        Pair (int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        // weight 기준으로 arr 오름차순 정렬
        Arrays.sort(arr, new Comparator<Pair>(){
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.weight - p2.weight;
            }
        });

        int[] c = new int[k];
        for (int i = 0; i < k; i++) {
            c[i] = Integer.parseInt(br.readLine());
        }

        // c 오름차순 정렬
        Arrays.sort(c);

        // 모든 가방에 대해서 담을 수 있는 최대 가격 구하기
        long sum = 0;
        int idx = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());        // 가격 높은순으로 우선순위
        for (int i = 0; i < k; i++) {
            while (idx < n && arr[idx].weight <= c[i]) {
                pq.add(arr[idx].value);
                idx++;
            }

            // 현재 가방에 넣을 수 있는 보석이 없다면 skip
            if (!pq.isEmpty()) sum += pq.poll();        // NullPointer 에러 방지
        }

        System.out.println(sum);
        br.close();
    }
}

/**
 * 골드 2 1202번 보석 도둑
 *
 * 가방 k개, 가방 1개에는 보석 1개만 가능 & 무게 Ci 만큼만 가능
 * -> 훔칠 수 있는 최대 가격 구하기
 *
 * i번째 가방에서 -> 현재 담을 수 있는 보석 중 가격이 가장 높은 보석 구하기 (그리디적으로 생각)
 * => log n 으로 가방에 담을 수 있는 보석 무게 찾기 & 최소 ~ 현재 무게까지의 최대 가격을 dp 테이블에서 찾기
 * => O(n) * (O(logn) + O(1))
 *
 * => 한번 가방에 넣은 보석은 다시 사용할 수 없으므로 매번 dp 테이블 update 해줘야함 -> 시간초과
 * ------------------------------------------------
 * 보석 무게 기준으로 오름차순 정렬
 * 가방 최대 무게 기준으로 오름차순 정렬
 * -> i번째 가방이 담을 수 있는 보석이면 우선순위 큐에 add -> poll = i번째 가방에 넣을 수 있는 최대 가격
 *
 * => 모든 보석들이 한번만 우선순위 큐에 들어감 -> O(n * logn)
 */