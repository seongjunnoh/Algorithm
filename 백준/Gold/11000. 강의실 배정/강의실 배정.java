import java.io.*;
import java.util.*;

public class Main {

    static class Lecture {
        int start;
        int end;

        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        Lecture[] lectures = new Lecture[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            lectures[i] = new Lecture(start, end);
        }

        // 강의 시작 시각 기준 오름차순 정렬
        Arrays.sort(lectures, new Comparator<Lecture>() {
            @Override
            public int compare(Lecture l1, Lecture l2) {
                return l1.start - l2.start;
            }
        });

        // 우선순위 큐를 사용해 종료 시각이 빠른 순으로 강의실 관리
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.add(lectures[0].end);

        for (int i = 1; i < n; i++) {
            if (lectures[i].start >= pq.peek()) {
                // 기존의 가장 빨리 끝나는 강의보다 현재 강의가 늦게 시작 -> 기존 강의실 재사용 가능
                pq.poll();
            }

            pq.add(lectures[i].end);            // 현재 강의가 끝나는 시각 우선순위 큐에 add
        }

        System.out.println(pq.size());          // 우선순위 큐에 남아있는 원소 개수 == 필요한 강의실 개수
        br.close();
    }
}

/**
 * 골드 5 11000 강의실 배정
 *
 * 강의시간 겹치면 다른 강의실
 * -> 이전의 모든 강의시간을 탐색하면 시간초과
 *
 * => 이전 강의들의 종료시간을 오름차순으로 관리하기 위해 우선순위 큐를 사용하자
 * => 우선순위 큐의 시간 복잡도 = log n
 */
