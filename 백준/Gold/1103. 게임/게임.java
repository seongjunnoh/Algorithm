import java.io.*;
import java.util.*;

class Solution {
    
    int n, m;
    int[][] map;
    int[][] dp;
    boolean[][] visit;
    boolean flag = false;   // 무한루프 발생 여부
    int[][] pos = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};   // 동서남북
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<m; j++) {
                char c = line.charAt(j);
                if (c == 'H') map[i][j] = -1;
                else map[i][j] = Integer.parseInt(String.valueOf(c));
            }
        }
        
        dp = new int[n][m];
        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);  // dp 초기화
        }
        visit = new boolean[n][m];
        
        visit[0][0] = true;
        int res = dfs(0, 0);
        System.out.println(flag ? -1 : res);
        br.close();
    }
    
    int dfs(int x, int y) {
        if (dp[x][y] != -1) return dp[x][y];    // 메모이제이션
        
        int curMax = 1; // x, y 에서 이동할 수 있는 최대횟수
        for (int i=0; i<4; i++) {
            int nX = x + map[x][y] * pos[i][0];
            int nY = y + map[x][y] * pos[i][1];
            
            if (nX<0 || nX>=n || nY<0 || nY>=m || map[nX][nY] == -1) continue;
            if (visit[nX][nY]) {
                flag = true;    // 무한루프 발생
                return -1;
            }
            
            visit[nX][nY] = true;
            curMax = Math.max(curMax, dfs(nX, nY) + 1);
            visit[nX][nY] = false;
        }
        
        dp[x][y] = curMax;
        return dp[x][y];
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * dp[i][j] : i,j 에서 최대 몇번 움직일 수 있는지
 * 
 */
