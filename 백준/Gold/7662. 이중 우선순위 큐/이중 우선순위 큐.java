import java.io.*;
import java.util.*;

public class Main {

    static Map<Integer, Integer> map;

    static int delete(Queue<Integer> q) {      // q에서 poll & map과 동기화
        int poll = 0;
        while (true) {
            poll = q.poll();
            Integer count = map.getOrDefault(poll, 0);

            if (count == 0) continue;

            else if (count == 1) map.remove(poll);
            else map.put(poll, map.get(poll) - 1);
            break;
        }

        return poll;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            PriorityQueue<Integer> minQ = new PriorityQueue<>();
            PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
            map = new HashMap<>();

            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char oper = st.nextToken().charAt(0);
                int x = Integer.parseInt(st.nextToken());

                if (oper == 'I') {
                    minQ.add(x);
                    maxQ.add(x);
                    map.put(x, map.getOrDefault(x, 0) + 1);
                } else {
                    if (map.size() == 0) continue;

                    if (x == -1) delete(minQ);
                    else delete(maxQ);
                }
            }

            if (map.size() == 0) {
                sb.append("EMPTY").append("\n");
            } else {
                int max = delete(maxQ);
                if (map.size() == 0) {      // map에 숫자가 1개만 있었는 경우
                    sb.append(max).append(" ").append(max).append("\n");
                } else {
                    int min = delete(minQ);
                    sb.append(max).append(" ").append(min).append("\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 7662번 이중 우선순위 큐
 *
 * 링크드 리스트로 관리 & 숫자 추가할 때마다 매번 이분탐색으로 추가할 위치 찾은 후 삽입 ??
 * -> 백만 * log(백만) (이분탐색으로 추가할 위치 찾기) * 백만 (찾은 위치까지 이동해서 list에 요소 추가)
 * -> 시간 초과
 *
 * 배열에 저장 & 매번 정렬하는 방식도 시간초과
 *
 * ???
 * ----------------------------------------------
 * 우선순위 큐 2개 사용 & 이 2개를 동기화 시켜줘야 함
 * -> 한쪽에서 poll 하면, 다른 쪽도 poll한 값을 remove 해줘야함
 * -> 이때 그냥 remove() 메서드 사용하면 마찬가지로 O(n) 이므로 시간초과
 *      => 입력받은 숫자들의 정보를 저장하는 map 도입
 *         큐에서 뽑은 숫자가 map에 없다
 *         == 다른 쪽 큐에서 이미 뽑아서 map에 반영하였다
 *         == 다음 숫자를 뽑아야 함
 *         => 반복
 *
 */
