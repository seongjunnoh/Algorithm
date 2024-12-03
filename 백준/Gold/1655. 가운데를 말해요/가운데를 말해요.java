import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> left = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> right = new PriorityQueue<>();

        int n = Integer.parseInt(br.readLine());
        for (int i = 1; i <= n; i++) {
            int x = Integer.parseInt(br.readLine());

            if (left.size() == right.size()) left.add(x);
            else right.add(x);

            // left, right 의 root 비교
            // 각 pq의 루트 노드만 비교해도 됨 (이미 왼쪽이 작고, 오른쪽이 큰 상태에서 x가 들어간 것이므로)
            if (!left.isEmpty() && !right.isEmpty() && left.peek() > right.peek()) {
                int leftPoll = left.poll();
                int rightPoll = right.poll();
                left.add(rightPoll);
                right.add(leftPoll);
            }

            sb.append(left.peek()).append("\n");

//            // pq 확인
//            System.out.println("=================================");
//            System.out.println("left");
//            Iterator<Integer> iterator = left.iterator();
//            while (iterator.hasNext()) {
//                System.out.print(iterator.next() + " || ");
//            }
//            System.out.println();
//            System.out.println("=================================");
//
//            System.out.println("=================================");
//            System.out.println("right");
//            Iterator<Integer> iter = right.iterator();
//            while (iter.hasNext()) {
//                System.out.print(iter.next() + " || ");
//            }
//            System.out.println();
//            System.out.println("=================================");
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 2 1655번 가운데를 말해요
 *
 * 시간제한 : 0.1 초 -> 모든 입력을 pq에 넣고 매번 배열로 변경해서 가운데 값 찾으면 시간 초과
 *
 * 중간값 -> 중간보다 작은 수들 중 최대(-> left pq) & 중간보다 큰 수들 중 최소(-> right pq)
 *
 * 해결 전략
 * pq 2개에 수를 나눠서 저장하자
 * left : 입력 중 중간값 이하의 수 들, right : 입력 중 중간값 보다 큰 수들
 * -> 정답 : left 의 peek
 */
