import java.io.*;
import java.util.*;

class Center {
    int up;
    int down;
    int right;
    int left;

    Center(int up, int down, int right, int left) {
        this.up = up;
        this.down = down;
        this.right = right;
        this.left = left;
    }
}

class Solution_32715 {

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(br.readLine());

        int[][] map = new int[n][m];
        Center[][] centers = new Center[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                centers[i][j] = new Center(map[i][j], map[i][j], map[i][j], map[i][j]);
            }
        }

        for (int i = 1; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) centers[i][j].up += centers[i - 1][j].up;
            }
        }

        for (int i = n - 2; i >= 0; i--) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) centers[i][j].down += centers[i + 1][j].down;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = 1; j < m; j++) {
                if (map[i][j] == 1) centers[i][j].left += centers[i][j - 1].left;
            }
        }

        for (int i = 0; i < n; i++) {
            for (int j = m - 2; j >= 0; j--) {
                if (map[i][j] == 1) centers[i][j].right += centers[i][j + 1].right;
            }
        }

//        for (int i = 0; i < n; i++) {
//            for (int j = 0; j < m; j++) {
//                System.out.println("centers[" + i + "][" + j + "] = " + centers[i][j].up + ", " + centers[i][j].down + ", " + centers[i][j].right + ", " + centers[i][j].left);
//            }
//        }

        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (map[i][j] == 1) {
                    int size = Math.min(centers[i][j].up, Math.min(centers[i][j].down, Math.min(centers[i][j].right, centers[i][j].left))) - 1;
                    if (size >= k) sum++;           // 크기가 k 이상이면 정답
                }
            }
        }

        System.out.println(sum);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_32715 s = new Solution_32715();
        s.solution();
    }
}

/**
 * 크기가 k인 십자의 개수 구하기
 * -> 매번 1이 나올때마다 이걸 중심으로 하는 십자 찾으면 시간초과
 *
 * 크기가 k-1인 십자들이 크기가 k인 십자가 될 후보들이다
 * -> 크기가 0인 십자들부터 계속 살아남는 것들만 확인 & 크기 1씩 늘리면서 정답 찾기
 * -> 시간초과 -> 최대 n * m 개의 위치가 k번 큐에 들락날락 & 4번의 확인연산 거치니까 시간초과 발생하는 듯
 *
 * dp 발상
 * -> 4개의 dp 배열(해당 위치부터 위/아래/왼/오 로 연속인 1의 개수 저장)
 * -> 현제 위치가 중심인 십자의 크기는 dp배열들의 값들 중 최소값
 */
