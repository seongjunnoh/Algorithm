import java.io.*;
import java.util.*;

public class Main {

    static class Pair implements Comparable<Pair> {
        int dead;
        int cup;

        Pair (int dead, int cup) {
            this.dead = dead;
            this.cup = cup;
        }

        @Override
        public int compareTo(Pair p) {
            if (dead == p.dead) return p.cup - cup;
            return dead - p.dead;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int dead = Integer.parseInt(st.nextToken());
            int cup = Integer.parseInt(st.nextToken());

            pq.add(new Pair(dead, cup));
        }

        int cur = 0;        // 현재 날짜
        PriorityQueue<Integer> cups = new PriorityQueue<>();        // 문제 풀어서 얻은 컵라면들
        while (!pq.isEmpty()) {
            Pair poll = pq.poll();      // 데드라인이 가장 가깝고, 그 중 컵라면을 가장 많이 주는 문제

            if (cur + 1 <= poll.dead) {     // poll 문제를 풀 수 있는 경우 -> 문제 푸는게 이득
                cups.add(poll.cup);
                cur++;
            } else {        // poll 문제를 풀 수 없는 경우 -> 앞의 문제를 버리고 poll 문제를 푸는 것이 이득인지 아닌지 체크
                if (cups.peek() < poll.cup) {
                    cups.poll();
                    cups.add(poll.cup);
                }
            }
        }

        // cups 에 담긴 숫자들의 합이 정답
        Iterator<Integer> iterator = cups.iterator();
        int sum = 0;
        while (iterator.hasNext()) {
            sum += iterator.next();
        }

        System.out.println(sum);
        br.close();
    }
}

/**
 * 골드 2 1781번 컵라면
 *
 * 현재날짜와 가장 가까운 데드라인인 문제 중, 컵라면을 가장 많이 주는 문제를 골라서 풀면 정답이다
 * -> 문제들을 데드라인 기준으로 오름차순 & 데드라인 같으면 컵라면 기준으로 내림차순 정렬
 *
 * => 이 방법은 데드라인이 가까운 문제를 버리고 데드라인이 먼 것을 먼저 풀 경우 컵라면을 더 많이 얻을 수 있는 상황에서는 정답이 아님
 * => 현재 문제를 풀지 않는 것이 최선인지를 어떻게 알 수 있나 ??
 *    1. 위 방식대로 문제를 풀어서 얻은 컵라면을 우선순위 큐에 저장(오름차순 정렬)
 *    2. 만약 다음 문제의 컵라면 개수가 우선순위 큐의 헤드보다 크다면, 헤드에 해당하는 문제를 풀지않고 현재문제를 푸는 것이 더 이득
 *       -> 우선순위 큐에서 pop & 현재 문제의 컵라면을 add
 *
 */
