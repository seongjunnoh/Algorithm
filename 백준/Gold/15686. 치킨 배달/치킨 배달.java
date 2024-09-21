import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int n, m;
    static int[][] map;
    static LinkedList<Pair> homes = new LinkedList<>();                  // 입력받은 집 위치들
    static LinkedList<Pair> chickens = new LinkedList<>();               // 입력받은 치킨집 위치들
    static LinkedList<int[]> chooses = new LinkedList<>();               // 그 중 m개를 뽑은 것으로 가능한 모든 경우들 (int[] : 뽑은 치킨집의 index 들)
    static int result = Integer.MAX_VALUE;
    static int[][] pos = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    static void comb(int count, int start, int[] choice) {
        // count : 현재까지 뽑은 치킨집 개수 , start : 탐색 시작할 치킨집 , choice : 현재까지 뽑은 치킨집의 인덱스를 저장하는 배열
        if (count == m) {
            // chooses에 choice 배열 추가
            chooses.add(Arrays.copyOf(choice, choice.length));              // 배열을 복사해서 add 해야함
            return;
        }

        for (int i = start; i < chickens.size(); i++) {
            choice[count] = i;
            comb(count + 1, i + 1, choice);
        }
    }

    static void calculate(int depth) {
        if (depth == chooses.size()) {
            return;
        }

        // map copy & 치킨집은 모두 0으로 바꾸기
        int[][] nMap = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                nMap[i][j] = map[i][j];
                if (nMap[i][j] == 2) nMap[i][j] = 0;
            }
        }

        // chooses[depth] 에 속하는 치킨집만 다시 2로 세팅
        int[] choice = chooses.get(depth);
        for (int i = 0; i < choice.length; i++) {
            Pair chicken = chickens.get(choice[i]);
            nMap[chicken.x][chicken.y] = 2;
        }

        // 도시 치킨 거리 계산 : 각 집에서 가장 가까운 치킨집 거리의 합
        int totalStreet = 0;
        for (int i = 0; i < homes.size(); i++) {
            int minDistance = Integer.MAX_VALUE;
            for (int j = 0; j < choice.length; j++) {
                Pair chicken = chickens.get(choice[j]);
                int distance = Math.abs(homes.get(i).x - chicken.x) + Math.abs(homes.get(i).y - chicken.y);
                minDistance = Math.min(minDistance, distance);              // 각 집에서의 최소 거리 선택
            }
            totalStreet += minDistance;
        }

        // result update
        result = Math.min(result, totalStreet);

        calculate(depth + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 2) {
                    chickens.add(new Pair(i, j));
                }
                if (map[i][j] == 1) {
                    homes.add(new Pair(i, j));
                }
            }
        }

        int[] choice = new int[m];
        comb(0, 0, choice);         // 치킨집들로 만들 수 있는 모든 m개의 조합 체크
        calculate(0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}
