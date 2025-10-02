import java.io.*;
import java.util.*;

class Solution {
    
    int w[];    // 정점 가중치
    List<Integer>[] graph;
    int[][] dp;
    PriorityQueue<Integer> pq;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        
        w = new int[n+1];
        for (int i=1; i<=n; i++) {
            w[i] = Integer.parseInt(st.nextToken());
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
        
        int sum1 = dfs(1, 1, 0);
        int sum2 = dfs(1, 0, 0);
        
        StringBuilder sb = new StringBuilder();
        pq = new PriorityQueue<>();
        if (sum1 > sum2) {
            find(1, 1, 0);
            sb.append(sum1).append("\n");
        } else {
            find(1, 0, 0);
            sb.append(sum2).append("\n");
        }
        
        while (!pq.isEmpty()) {
            sb.append(pq.poll()).append(" ");
        }
        
        System.out.println(sb);
        br.close();
    }
    
    int dfs(int cur, int col, int parent) {
        if (dp[cur][col] != -1) return dp[cur][col];    // early return
        
        int sum = 0;
        if (col == 1) { // cur 노드를 독립집합에 포함하는 경우
            sum = w[cur];
            
            for (int near : graph[cur]) {
                if (near == parent) continue;   // 자식노드에 대해서만 계산
                sum += dfs(near, 0, cur);
            }
        } else {    // cur 노드를 독립집합에 포함하지 않는 경우
            for (int near : graph[cur]) {
                if (near == parent) continue;   // 자식노드에 대해서만 계산
                sum += Math.max(dfs(near, 1, cur), dfs(near,0, cur));
            }
        }
        
        dp[cur][col] = sum;
        return sum;
    }
    
    void find(int cur, int col, int parent) {
        if (col == 1) {
            pq.add(cur);
            
            for (int near : graph[cur]) {
                if (near == parent) continue;
                find(near, 0, cur);
            }
        } else {
            for (int near : graph[cur]) {
                if (near == parent) continue;
                
                if (dp[near][0] > dp[near][1]) {    // 서브트리 중 dp 값이 더 큰 쪽을 선택
                    find(near, 0, cur);
                } else {
                    find(near, 1, cur);
                }
            }
        }
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * dp[i][1] : i노드를 포함할 때, 서브트리에서의 독립집한 크기 최대값
 * dp[i][0] : i노드를 포함하지 않을 때, 서브트리에서의 독립집합 크기 최대값
 * 
 */
