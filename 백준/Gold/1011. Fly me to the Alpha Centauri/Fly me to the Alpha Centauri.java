import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_1011 {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(br.readLine());
        for (int i = 0; i < t; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = y - x;
            int sqrt = (int) Math.sqrt(d);

            if (d == sqrt * sqrt) {     // d : 제곱수
                sb.append(2 * sqrt - 1).append("\n");
            } else if (d <= sqrt * sqrt + sqrt) {
                sb.append(2 * sqrt).append("\n");
            } else {
                sb.append(2 * sqrt + 1).append("\n");
            }
        }

        System.out.println(sb);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1011 s = new Solution_1011();
        s.solution();
    }
}

/**
 * 골드 5 1011번 FLy me to the Alpha Centauri
 *
 * 최소한의 작동 횟수로 x->y로 이동
 * -> 가능한 최대의 간격으로 이동 & 마지막 이동거리는 무조건 1이어야 함
 *
 * 두 점 사이의 거리와 작동 횟수의 최솟값 사이의 관계 찾기
 * 1 -> 1 (1)
 * 2 -> 2 (11)
 * 3 -> 3 (111)
 * 4 -> 3 (121)
 * 5 -> 4 (1211)
 * 6 -> 4 (1221)
 * 7 -> 5 (12211)
 * 8 -> 5 (12221)
 * 9 -> 6 (12321)
 * ,,,
 *
 * 제곱수, 제곱수들 사이의 중간값에서 작동 횟수의 최솟값이 바뀐다
 *
 */
