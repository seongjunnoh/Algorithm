import java.io.*;
import java.util.*;

class Solution {
    
    int n;
    Set<Integer>[] graph;
    int[][] map;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        graph = new HashSet[n + 1];
        for (int i=1; i<=n; i++) {
            graph[i] = new HashSet<>();
        }
        
        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            graph[from].add(to);
        }
        
        map = new int[n + 1][n + 1];
        for (int i=1; i<=n; i++) {
            bfs(i);
        }
        
        StringBuilder sb = new StringBuilder();
        int s = Integer.parseInt(br.readLine());
        for (int i=0; i<s; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            sb.append(map[x][y]).append("\n");
        }
        
        System.out.println(sb);
        br.close();
    }
    
    void bfs(int start) {
        Queue<Integer> q = new LinkedList<>();
        boolean[] visit = new boolean[n+1];  // bfs 큐 여러개 생성 막기위함 (for 메모리 누수 절약)
        q.add(start);
        visit[start] = true;
        
        while (!q.isEmpty()) {
            Integer poll = q.poll();
            for (int next : graph[poll]) {
                if (visit[next]) continue;
                
                map[start][next] = -1;      // start 기준으로 map 채우기
                map[next][start] = 1;
            
                q.add(next);
                visit[next] = true;
            }
        }
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
 * 모든 정점에 대하여 bfs 진행 -> 해당 정점에서 출발해서 도달할 수 있는 모든 정점 구하기
 * 
 */
