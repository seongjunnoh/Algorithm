import java.io.*;
import java.util.*;
import java.lang.*;

class Solution_14889 {

    int n;
    int[][] map;
    Stack<Integer> select = new Stack<>();
    int min = Integer.MAX_VALUE;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        map = new int[n][n];
        StringTokenizer st;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        back(0, 0);

        System.out.println(min);
        br.close();
    }

    void back(int start, int count) {
        if (count == n / 2) {
            cal();
            return;
        }

        for (int i = start; i < n; i++) {
            select.push(i);
            back(i + 1, count + 1);
            select.pop();
        }
    }

    void cal() {
        Set<Integer> set = new HashSet<>(select);
        List<Integer> team1 = new ArrayList<>(select);
        List<Integer> team2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (!set.contains(i)) team2.add(i);
        }

        int val1 = 0;
        int val2 = 0;

        for (int i = 0; i < team1.size(); i++) {
            for (int j = i + 1; j < team1.size(); j++) {
                val1 += map[team1.get(i)][team1.get(j)];
                val1 += map[team1.get(j)][team1.get(i)];
            }
        }

        for (int i = 0; i < team2.size(); i++) {
            for (int j = i + 1; j < team2.size(); j++) {
                val2 += map[team2.get(i)][team2.get(j)];
                val2 += map[team2.get(j)][team2.get(i)];
            }
        }

        min = Math.min(min, Math.abs(val1 - val2));
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_14889 s = new Solution_14889();
        s.solution();
    }
}

/**
 * 실버 1 14889번 스타트와 링크
 *
 * n명중 2/n 명 뽑아서 두 팀간의 능력치 차이 구하기
 * -> n의 범위 작음 -> 브루트포스 & 백트래킹으로 팀원 구성
 */
