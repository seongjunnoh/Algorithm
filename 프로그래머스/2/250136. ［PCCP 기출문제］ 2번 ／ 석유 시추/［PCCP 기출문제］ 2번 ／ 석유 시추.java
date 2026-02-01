import java.util.*;
import java.io.*;

class Pair {
    int x;
    int y;
    
    Pair(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

class Solution {
    int n, m;
    List<Integer>[] oil;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] land) {
        n = land.length;
        m = land[0].length;
        
        oil = new ArrayList[m];
        for (int i=0; i<m; i++) {
            oil[i] = new ArrayList<>();
        }
        
        boolean[][] visit = new boolean[n][m];
        for (int i=0; i<n; i++) {
            for (int j=0; j<m; j++) {
                if (visit[i][j] || land[i][j] == 0) continue;
                
                calc(land, visit, i, j);
            }
        }
        
//         for (int j=0; j<m; j++) {
//             for (int o : oil[j]) {
//                 System.out.print(o + ", ");    
//             }
            
//             System.out.println();
//         }
        
        int max = 0;
        for (int j=0; j<m; j++) {
            int cur = 0;
            for (int o : oil[j]) {
                cur += o;
            }
            
            max = Math.max(max, cur);
        }
        
        return max;
    }
    
    void calc(int[][] land, boolean[][] visit, int x, int y) {
        Queue<Pair> q = new LinkedList<>();
        Set<Integer> colSet = new HashSet<>();      // 현재 석유 덩어리가 분포된 열
        
        q.add(new Pair(x, y));
        visit[x][y] = true;
        colSet.add(y);
        int sum = 1;    // 현재 석유 덩어리 크기
        
        while (!q.isEmpty()) {
            Pair poll = q.poll();
            
            for (int i=0; i<4; i++) {
                int nx = poll.x + pos[i][0];
                int ny = poll.y + pos[i][1];
                
                if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if (!visit[nx][ny] && land[nx][ny] == 1) {
                    q.add(new Pair(nx, ny));
                    visit[nx][ny] = true;
                    colSet.add(ny);
                    sum++;
                }
            }
        }
        
        // 현재 석유 덩어리 크기를 oil에 업데이트
        for (int col : colSet) {
            oil[col].add(sum);
        }
    }
}