import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static class Pair {
        int classNum;
        int index;
        long ability;

        Pair(int classNum, int index, long ability) {
            this.classNum = classNum;
            this.index = index;
            this.ability = ability;
        }
    }

    static class PairComparator implements Comparator<Pair> {
        @Override
        public int compare(Pair p1, Pair p2) {
            return Long.compare(p1.ability, p2.ability);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        long[][] arr = new long[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                arr[i][j] = Long.parseLong(st.nextToken());
            }

            Arrays.sort(arr[i]);
        }

        long max = 0;       // 최대값
        PriorityQueue<Pair> pq = new PriorityQueue<>(new PairComparator());         // 최소값 계산하기 위함
        long result = Long.MAX_VALUE;       // 최대값과 최소값의 차이
        int[] pointer = new int[n];           // 각 학급마다의 포인터 위치

        // 일단 각 학급의 첫번째 학생을 대표로 지정
        for (int i = 0; i < n; i++) {
            pq.add(new Pair(i, 0, arr[i][0]));
            max = Math.max(max, arr[i][0]);     // 현재 능력치가 최대인 학생
        }

        while (true) {
            Pair poll = pq.poll();          // 현재 능력치가 최소인 학생
            result = Math.min(result, max - poll.ability);
            
            if (result == 0) break;

            int nextIndex = poll.index + 1;
            if (nextIndex >= m) break;          // 더 이상 해당 학급에서의 대표를 변경할 수 없음 -> 탈출조건

            long nextAbility = arr[poll.classNum][nextIndex];
            pq.add(new Pair(poll.classNum, nextIndex, nextAbility));

            max = Math.max(max, nextAbility);       // max update
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 골드 2 2461 대표 선수
 *
 * arr[i] 모두 오름차순 & 각 arr[i] 의 모든 첫번째 학생을 대표로 지정
 * -> 최대, 최소 차이 확인 -> 최소인 arr[i]의 포인터를 한칸 뒤로 이동 -> 최소가 해당 arr[i]에서 가장 끝이면 끝
 * => 최대, 최소 찾기 (O(n)) & 모든 arr[i]의 포인터 이동 (O(n 제곱)) => O(n 3제곱) => 시간초과
 *
 * -> 여기서 최대, 최소 찾는 걸 우선순위 큐가 해준다면?? -> O(n제곱 * log n) => ok
 *
 */

