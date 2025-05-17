import java.io.*;
import java.util.*;

class Solution_1525 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                int num = Integer.parseInt(st.nextToken());
                sb.append(num);
            }
        }

        String arr = sb.toString();
        Queue<String> q = new LinkedList<>();
        q.add(arr);     // 초기상태
        Map<String, Integer> map = new HashMap<>();
        map.put(arr, 0);        // map 초기상태
        String answer = "123456780";       // 정답

        while (!q.isEmpty()) {
            String poll = q.poll();     // 현재 배열상태
            int count = map.get(poll);  // 0의 이동횟수

            if (poll.equals(answer)) {      // 정답인 경우
                System.out.println(count);
                br.close();
                return;
            }

            int idx = poll.indexOf("0");        // 0 위치
            int x = idx / 3;
            int y = idx % 3;

            for (int i = 0; i < 4; i++) {
                int nX = x + pos[i][0];
                int nY = y + pos[i][1];
                if (nX < 0 || nX >= 3 || nY < 0 || nY >= 3) continue;

                int nIdx = nX * 3 + nY;     // 0이 이동할 인덱스
                sb = new StringBuilder();
                for (int j = 0; j < 9; j++) {
                    if (j == idx) {
                        sb.append(poll.charAt(nIdx));
                    } else if (j == nIdx) {
                        sb.append(poll.charAt(idx));
                    } else {
                        sb.append(poll.charAt(j));
                    }
                }
                String newArr = sb.toString();

                if (!map.containsKey(newArr)) {     // newArr가 이전에 나온 조합이 아닐 경우 queue에 add
                    q.add(newArr);
                    map.put(newArr, count + 1);
                }
            }
        }

        System.out.println("-1");       // 최대 9!가지의 모든 경우 탐색 이후에도 정답 배열에 도달하지 못한 경우
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1525 s = new Solution_1525();
        s.solution();
    }
}

/**
 * 정렬시킬 수 있는 최소 이동 횟수 구하기, 이동이 불가능할 경우 -1 출력
 * 0이 움직이면서 다른 숫자와 위치 체인지된다고 생각
 * -> bfs로 0을 계속 움직이면서 정답인 배열이 나오면 탐색 종료 (이때 횟수가 정답)
 * -> 0이 끝까지 탐색했음에도 정답인 배열이 나오지 않으면 -1 출력
 *
 * 메모리 제한 -> 매번 탐색시마다 현재 배열상태를 깊은복사하여 넘겨주면 36Byte * 9! 만큼의 메모리가 필요 ??
 * -> String으로 전달하자
 * -> 방문 여부는 현재 배열상태인 String을 key, 0의 이동횟수를 val로 하는 HashMap으로 구현 (동일 배열상태일 경우가 visit=true인 상황)
 *
 */