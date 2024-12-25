import java.io.*;
import java.util.*;

public class Main {

    static ArrayList<Integer>[] children;      // 자신의 직속 부하
    static int[] dp;        // 칭찬받은 수치
    static int n;

    static void dfs(int node) {
        ArrayList<Integer> list = children[node];       // node의 부하들
        for (int child : list) {
            dp[child] += dp[node];
            dfs(child);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        children = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) {
            children[i] = new ArrayList<>();
        }
        dp = new int[n + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= n; i++) {
            int parent = Integer.parseInt(st.nextToken());
            if (parent > 0) children[parent].add(i);
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());
            dp[node] += weight;     // 초기화
        }

        dfs(1);         // 사장부터 dfs 시작해야함

        StringBuilder sb = new StringBuilder();
        for (int i = 1; i <= n; i++) {
            sb.append(dp[i]).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}

/**
 * 골드 4 14267번 회사 문화 1
 *
 * 매번 bfs로 부하에게 칭찬 w 값 update 할 경우 시간 초과 예상
 * -> 칭찬을 받은 직원의 번호, 칭찬의 수치를 기록 & dfs 한방으로 모든 칭찬 수치를 업데이트
 * cf) 해당 그래프는 트리구조이므로 사이클 X -> dfs 할때 visit 배열 필요없음
 */
