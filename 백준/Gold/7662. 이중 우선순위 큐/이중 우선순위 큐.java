import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < t; i++) {
            int k = Integer.parseInt(br.readLine());
            TreeMap<Integer, Integer> map = new TreeMap<>();        // TreeMap 생성

            for (int j = 0; j < k; j++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                char oper = st.nextToken().charAt(0);
                int x = Integer.parseInt(st.nextToken());

                if (oper == 'I') {
                    map.put(x, map.getOrDefault(x, 0) + 1);
                } else {
                    if (map.size() == 0) continue;

                    if (x == -1) {      // 최소값 제거
                        Integer minKey = map.firstKey();
                        map.put(minKey, map.get(minKey) - 1);
                        if (map.get(minKey) == 0) map.remove(minKey);
                    } else {        // 최대값 제거
                        Integer maxKey = map.lastKey();
                        map.put(maxKey, map.get(maxKey) - 1);
                        if (map.get(maxKey) == 0) map.remove(maxKey);
                    }
                }
            }

            if (map.size() == 0) {
                sb.append("EMPTY").append("\n");
            } else {
                sb.append(map.lastKey()).append(" ").append(map.firstKey()).append("\n");
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
 * TreeMap 자료구조 이용하자
 *
 */
