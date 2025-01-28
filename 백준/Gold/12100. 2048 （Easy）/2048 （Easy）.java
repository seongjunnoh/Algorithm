import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_12100 {

    int n;
    int max = 0;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        int[][] map = new int[n][n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        play(map, 0);

        System.out.println(max);
        br.close();
    }

    void play(int[][] map, int count) {
        if (count == 5) {
            calMax(map);
            return;
        }

        play(up(map), count + 1);
        play(down(map), count + 1);
        play(right(map), count + 1);
        play(left(map), count + 1);
    }

    int[][] up(int[][] map) {
        int[][] nMap = copyMap(map);

        for (int col = 0; col < n; col++) {
            List<Integer> list = new ArrayList<>();
            for (int row = 0; row < n; row++) {
                if (nMap[row][col] > 0) list.add(nMap[row][col]);
            }

            int idx = 0;
            List<Integer> merge = new ArrayList<>();
            while (idx < list.size()) {
                if (idx < list.size() - 1 && list.get(idx).equals(list.get(idx + 1))) {
                    merge.add(list.get(idx) * 2);
                    idx += 2;
                } else {
                    merge.add(list.get(idx));
                    idx++;
                }
            }

            for (int row = 0; row < n; row++) {
                if (row < merge.size()) {
                    nMap[row][col] = merge.get(row);
                } else {
                    nMap[row][col] = 0;          // 나머지는 0
                }
            }
        }

        return nMap;
    }

    int[][] down(int[][] map) {
        int[][] nMap = copyMap(map);

        for (int col = 0; col < n; col++) {
            List<Integer> list = new ArrayList<>();
            for (int row = n - 1; row >= 0; row--) {
                if (nMap[row][col] != 0) {
                    list.add(nMap[row][col]);
                }
            }

            List<Integer> merge = new ArrayList<>();
            int idx = 0;
            while (idx < list.size()) {
                if (idx < list.size() - 1 && list.get(idx).equals(list.get(idx+1))) {
                    merge.add(list.get(idx) * 2);
                    idx += 2;
                } else {
                    merge.add(list.get(idx));
                    idx++;
                }
            }

            for (int row = n - 1; row >= 0; row--) {
                int i = n - 1 - row;
                if (i < merge.size()) {
                    nMap[row][col] = merge.get(i);
                } else {
                    nMap[row][col] = 0;
                }
            }
        }

        return nMap;
    }

    int[][] right(int[][] map) {
        int[][] nMap = copyMap(map);

        for (int row = 0; row < n; row++) {
            List<Integer> list = new ArrayList<>();
            for (int col = n - 1; col >= 0; col--) {
                if (nMap[row][col] > 0) list.add(nMap[row][col]);
            }

            int idx = 0;
            List<Integer> merge = new ArrayList<>();
            while (idx < list.size()) {
                if (idx < list.size() - 1 && list.get(idx).equals(list.get(idx + 1))) {
                    merge.add(list.get(idx) * 2);
                    idx += 2;
                } else {
                    merge.add(list.get(idx));
                    idx++;
                }
            }

            for (int col = n - 1; col >= 0; col--) {
                int i = n - 1 - col;
                if (i < merge.size()) {
                    nMap[row][col] = merge.get(i);
                } else {
                    nMap[row][col] = 0;
                }
            }
        }

        return nMap;
    }

    int[][] left(int[][] map) {
        int[][] nMap = copyMap(map);

        for (int row = 0; row < n; row++) {
            List<Integer> list = new ArrayList<>();
            for (int col = 0; col < n; col++) {
                if (nMap[row][col] > 0) list.add(nMap[row][col]);
            }

            int idx = 0;
            List<Integer> merge = new ArrayList<>();
            while (idx < list.size()) {
                if (idx < list.size() - 1 && list.get(idx).equals(list.get(idx + 1))) {
                    merge.add(list.get(idx) * 2);
                    idx += 2;
                } else {
                    merge.add(list.get(idx));
                    idx++;
                }
            }

            for (int col = 0; col < n; col++) {
                if (col < merge.size()) {
                    nMap[row][col] = merge.get(col);
                } else {
                    nMap[row][col] = 0;
                }
            }
        }

        return nMap;
    }

    int[][] copyMap(int[][] map) {
        int[][] copy = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                copy[i][j] = map[i][j];
            }
        }
        return copy;
    }

    void calMax(int[][] map) {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                max = Math.max(max, map[i][j]);
            }
        }
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_12100 s = new Solution_12100();
        s.solution();
    }
}

/**
 * 같은 값을 가지는 두 블록이 충돌하면 하나로 합쳐진다
 * 한번의 이동에서 이미 합쳐진 블록은 또 다른 블록과 다시 합쳐질 수 없음
 * -> 최대 5번 이동해서 만들 수 있는 가장 큰 블록의 값 구하기
 *
 * 한번 이동시 최대 400개의 칸 탐색 & 가능한 최대 가짓수 = 4의 5제곱 = 약 1000
 * -> 모든 경우에 대해서 5번 이동 후 최댓값 찾기 & 이 최댓값들 중 최댓값이 정답이다
 *
 * 일단 빈틈없이 한쪽으로 모두 몰아놓고, 그다음에 앞에서부터 2개씩 병합
 */
