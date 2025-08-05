import java.io.*;
import java.util.*;

class Solution_2109 {

    Map<Integer, List<Integer>> map;    // key : 마감일, val : 돈
    int sum = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        map = new HashMap<>();
        for (int i = 1; i <= 10000; i++) {
            map.put(i, new ArrayList<>());
        }

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            map.get(d).add(p);
        }

        System.out.println(cal());
    }

    int cal() {
        int time = 10000;   // 마감일이 10000일 부터 생각
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        while (time > 0) {
            for (int money : map.get(time)) {
                pq.add(money);
            }

            if (!pq.isEmpty()) sum += pq.poll();    // time 시각에 강의 1개 선택 가능

            time--;
        }

        return sum;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2109 s = new Solution_2109();
        s.solution();
    }
}

/**
 * 거꾸로 생각
 * 최대 10000 * 10000 이므로 시간초과 X
 */