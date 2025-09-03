import java.io.*;
import java.util.*;

class Solution {
    
    final int INF = Integer.MAX_VALUE;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        
        int[][] dist = new int[n+1][n+1];
        int[][] firstHop = new int[n+1][n+1];   // 경로표
        for (int i=1; i<=n; i++) {
            Arrays.fill(dist[i], INF);
            dist[i][i] = 0;
        }
        
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            
            if (w < dist[x][y]) {   // 초기화 - 간선들 중 w가 최소인 간선만 기록
                dist[x][y] = w;
                dist[y][x] = w;
                firstHop[x][y] = y;
                firstHop[y][x] = x;
            }
        }
        
        // 플로이드 와샬 i->k->j
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (dist[i][k] == INF || dist[k][j] == INF) continue;
                    
                    if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        firstHop[i][j] = firstHop[i][k];
                    }    
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                if (i == j) sb.append("-").append(" ");
                else sb.append(firstHop[i][j]).append(" ");
            }
            sb.append("\n");
        }
        
        System.out.println(sb);
        br.close();
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
 * 최단경로를 통하기 위해서 제일 먼저 들어야하는 집하장 구하기
 * 
 * 플로이드 와샬로도 해결 가능
 */