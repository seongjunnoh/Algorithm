import java.io.*;
import java.util.*;

public class Main {

    static class Delivery implements Comparable<Delivery> {
        int start;
        int end;
        int num;

        Delivery(int start, int end, int num) {
            this.start = start;
            this.end = end;
            this.num = num;
        }

        // 목적지가 가까운 순으로 정렬 (같으면 출발지가 가까운 순으로)
        @Override
        public int compareTo(Delivery d) {
            if (this.end == d.end) return this.start - d.start;
            return this.end - d.end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int m = Integer.parseInt(br.readLine());
        List<Delivery> deliveries = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int num = Integer.parseInt(st.nextToken());
            deliveries.add(new Delivery(start, end, num));
        }

        Collections.sort(deliveries);       // 정렬

        int[] capacity = new int[n];    // capacity[i] : i ~ i+1 동안의 트럭 남은 용량
        Arrays.fill(capacity, c);           // 초기화

        long result = 0;
        for (Delivery d : deliveries) {
            int min = d.num;
            for (int i = d.start; i < d.end; i++) {
                min = Math.min(min, capacity[i]);
            }

            if (min > 0) {      // 현재 d 배송 가능 (min 만큼)
                for (int i = d.start; i < d.end; i++) {
                    capacity[i] -= min;
                }
                result += min;
            }

//            System.out.println("==================");
//            for (int i = 1; i < n; i++) {
//                System.out.print(capacity[i] + " || ");
//            }
//            System.out.println();
//            System.out.println("==================");
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 골드 1 8980 택배
 *
 * 가장 가까운 마을에 배송할 박스를 우선으로 트럭에 실자
 * -> 이래야 가까운 곳에 도착해서 내리고, 다시 박스를 실을 확률이 더 올라감
 *
 * 도착지점이 가까운 택배 -> 해당 구간동안 이것들이 우선적으로 트럭에 있어야 함
 *
 */
