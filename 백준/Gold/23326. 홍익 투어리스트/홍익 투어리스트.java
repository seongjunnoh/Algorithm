import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        TreeSet<Integer> set = new TreeSet<>();     // 명소인 구역번호를 저장하는 set

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int num = Integer.parseInt(st.nextToken());

            if (num == 1) set.add(i);
        }

        long cur = 1;        // 도현이 위치
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(br.readLine());
            int command = Integer.parseInt(st.nextToken());

            if (command == 1) {
                int num = Integer.parseInt(st.nextToken());

                if (set.contains(num)) set.remove(num);
                else set.add(num);
            } else if (command == 2) {
                long num = Long.parseLong(st.nextToken());

                cur = (cur + num) % n;
                if (cur == 0) cur = n;
            } else {
                if (set.isEmpty()) {        // 현재 명소가 존재하지 않는 경우
                    sb.append("-1").append("\n");
                    continue;
                }

                if (set.ceiling((int) cur) == null) {       // 가장 가까운 명소의 구역번호가 cur 보다 작은 경우
                    sb.append(n - cur + set.first()).append("\n");
                } else {        // 가장 가까운 명소의 구역번호가 cur 보다 큰 경우
                    sb.append(set.ceiling((int) cur) - cur).append("\n");
                }
            }
        }

        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 3 23326번 홍익 투어리스트
 *
 * n개의 구역 중 어디가 명소인지를 저장해야 함
 * -> 배열에 저장 : 3번 연산 시 모든 구역을 돌면서 명소까지의 최소 거리 구해야함 -> O(n * q) = 시간초과
 * -> set에 저장 : TreeSet에 저장 (명소인 구역을 구역번호 기준으로 정렬된 상태 유지)
 *               => 3번 연산의 결과를 위해 array로 변경한 후, 이분탐색으로 lowerBound 구하기
 *
 * -----------------------------------------
 * 시간초과 발생 -> TreeSet을 배열로 바꿀때 O(n) 이 걸리므로 시간초과 발생
 * => TreeSet의 celling 메서드 사용
 *
 */
