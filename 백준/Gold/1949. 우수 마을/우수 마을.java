import java.io.*;
import java.util.*;

class Solution {
    
    int[] arr;  // 마을 주민 수
    List<Integer>[] graph;
    int[][] dp;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        arr = new int[n+1];
        for (int i=1; i<=n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        graph = new ArrayList[n+1];
        for (int i=1; i<=n; i++) {
            graph[i] = new ArrayList<>();
        }
        
        for (int i=0; i<n-1; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            graph[x].add(y);
            graph[y].add(x);
        }
        
        dp = new int[n+1][2];
        for (int i=0; i<=n; i++) {
            Arrays.fill(dp[i], -1); // 초기화
        }
        
        System.out.println(Math.max(dfs(1, 1, 0), dfs(1, 0, 0)));   // 1번 마을에서 시작
        br.close();
    }
    
    int dfs(int cur, int col, int parent) {
        if (dp[cur][col] != -1) return dp[cur][col];    // early return
        
        int sum = 0;
        if (col == 1) { // cur 노드를 우수마을로 선정하는 경우
            sum = arr[cur];
            for (int near : graph[cur]) {
                if (near == parent) continue;   // cur의 child에 대해서만 계산
                
                // child를 우수마을로 선정 X
                sum += dfs(near, 0, cur);
            }
        } else {    // cur 노드를 우수마을로 선정하지 않는 경우
            for (int near : graph[cur]) {
                if (near == parent) continue;
                
                // child를 우수마을로 선정하든지 말던지 -> 둘 중 최댓값
                sum += Math.max(dfs(near, 0, cur), dfs(near, 1, cur));
            }
        }
        
        dp[cur][col] = sum;
        return sum;
    }
}

public class Main
{
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * dp + 트리 문제
 * dp[i][1] : i노드를 우수마을로 정할 경우, 서브트리의 점수 최대값
 * dp[i][0] : i노드를 우수마을로 정하지 않을 경우, 서브트리의 점수 최대값
 */
