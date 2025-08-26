import java.io.*;
import java.util.*;

class Solution {
    
    int n;
    int[][] map;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        
        map = new int[n + 1][n + 1];
        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            
            map[from][to] = -1;
            map[to][from] = 1;
        }
        
        floyd();
        
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
    
    void floyd() {
        for (int k=1; k<=n; k++) {
            for (int i=1; i<=n; i++) {
                for (int j=1; j<=n; j++) {
                    if (map[i][k] == 1 && map [k][j] == 1) {
                        map[i][j] = 1;
                    } else if (map[i][k] == -1 && map [k][j] == -1) {
                        map[i][j] = -1;
                    }
                }
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
 * 플로이드 와샬 알고리즘이 더 효율적임 (bfs 를 위해 매번 큐를 생성할 필요가 없으므로)
 */
