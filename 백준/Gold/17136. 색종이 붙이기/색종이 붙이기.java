import java.io.*;
import java.util.*;

class Solution_17136 {

    int[][] map;
    int[] count;
    int min = 26;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[10][10];
        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        count = new int[6];     // 한변 길이가 1 ~ 5 인 색종이 개수
        Arrays.fill(count, 5);

        back(0, 0, 0);

        if (min == 26) {
            System.out.println("-1");
        } else {
            System.out.println(min);
        }
        br.close();
    }

    void back(int x, int y, int useCount) {
        if (x == 10 && y == 0) {     // 전체 map 탐색 끝 -> min update
            min = Math.min(min, useCount);
            return;
        }
        
        if (useCount >= min) return;        // 더이상 탐색 이어나갈 이유 없음

        int nX = -1;        // 다음 DFS 탐색 지점
        int nY = -1;
        if (y == 9) {
            nX = x + 1;
            nY = 0;
        } else {
            nX = x;
            nY = y + 1;
        }

        if (map[x][y] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (count[size] > 0 && attachable(size, x, y)) {      // (x,y) 에서 크기가 i인 색종이 붙일 수 있다
                    attach(size, x, y);
                    back(nX, nY, useCount + 1);
                    detach(size, x, y);
                }
            }
            // (x,y) 가 1이지만 색종이를 붙일 수 없다 -> 더 이상 백트래킹 진행할 필요 없음
        } else {
            back(nX, nY, useCount);
        }
    }

    void attach(int size, int x, int y) {
        count[size]--;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = 0;      // 색종이 붙였으니까 0으로
            }
        }
    }

    void detach(int size, int x, int y) {
        count[size]++;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = 1;      // 다시 색종이를 때서 1로 만들기
            }
        }
    }

    boolean attachable(int size, int x, int y) {
        if (x + size - 1 >= 10 || y + size - 1 >= 10) return false;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (map[i][j] == 0) return false;
            }
        }

        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_17136 s = new Solution_17136();
        s.solution();
    }
}

/**
 * 현재 위치를 포함한 n*n 크기가 모두 1로 채워져있는지 확인
 * -> 가능한 n 중 가장 큰 값 선택??
 * -> 현재 위치에서 가능한 가장 큰 정사각형을 선택하는것이 정답이 아닐 수 있음
 * -> 모든 경우 고려 (최대 500가지의 경우의 수 -> ok) 
 * -> 백트래킹
 */