import java.util.*;

class Solution {
    public int[] solution(int n) {
        int[][] map = new int[n][n];
        
        int end = 0;
        for (int i=1; i<=n; i++) {
            end += i;
        }
    
        int cur = 1;
        int x = 0;
        int y = 0;
        int dir = 0;        // 방향 (0 : 아래, 1 : 오른쪽, 2 : 대각선 위)
        while (cur <= end) {
            map[x][y] = cur++;
            
            int nX = -1, nY = -1;
            if (dir == 0) {
                nX = x + 1;
                nY = y;
            } else if (dir == 1) {
                nX = x;
                nY = y + 1;
            } else {
                nX = x - 1;
                nY = y - 1;
            }
            
            // nX, nY 로 이동하지 못하는 경우 -> nX, nY update
            if (nX < 0 || nX >= n || nY < 0 || nY >= n || map[nX][nY] != 0) {
                dir = (dir + 1) % 3;
                if (dir == 0) {
                    nX = x + 1;
                    nY = y;
                } else if (dir == 1) {
                    nX = x;
                    nY = y + 1;
                } else {
                    nX = x - 1;
                    nY = y - 1;
                }
            }
            
            x = nX;
            y = nY;
        }
        
        int[] answer = new int[end];
        int idx = 0;
        for (int i=0; i<n; i++) {
            for (int j=0; j<=i; j++) {
                answer[idx++] = map[i][j];
            }
        }
        
        return answer;
    }
}