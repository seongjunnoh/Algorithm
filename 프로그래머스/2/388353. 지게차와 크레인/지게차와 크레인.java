import java.util.*;
import java.io.*;

class Solution {
    
    int n, m;
    char[][] map;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(String[] storage, String[] requests) {
        n = storage.length;
        m = storage[0].length();
        map = new char[n+2][m+2];
        for (int i=0; i<n+2; i++) {
            Arrays.fill(map[i], ' ');   // 초기화
        }
        
        for (int i=1; i<=n; i++) {
            String line = storage[i-1];
            for (int j=1; j<=m; j++) {
                map[i][j] = line.charAt(j-1);    
            }
        }
        
        for (String r : requests) {
            if (r.length() == 1) jige(r.charAt(0));
            else crain(r.charAt(0));
        }
        
        int answer = 0;
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (map[i][j] == ' ' || map[i][j] == '.') continue;
                answer++;
            }
        }
        
        // for (int i=1; i<=n; i++) {
        //     for (int j=1; j<=m; j++) {
        //         System.out.print(map[i][j]);
        //     }
        //     System.out.println();
        // }
        
        return answer;
    }
    
    void bfs() {
        Queue<int[]> q = new LinkedList<>();    // ' ' 큐
        
        // ' ' 탐색
        for (int i=0; i<n+2; i++) {
            for (int j=0; j<m+2; j++) {
                if (map[i][j] != ' ') continue;
                
                int[] cur = {i, j};
                q.add(cur);
            }
        }
        
        // '.' 중 ' ' 로 바꿔야하는 지점 있는지 확인 -> 연쇄적으로
        while (!q.isEmpty()) {
            int[] poll = q.poll();
            
            for (int d=0; d<4; d++) {
                int nx = poll[0] + pos[d][0];
                int ny = poll[1] + pos[d][1];

                if (nx<0 || nx>=n+2 || ny<0 || ny>=m+2) continue;
                if (map[nx][ny] == '.') {
                    map[nx][ny] = ' ';      // ' ' 로 업데이트
                    int[] next = {nx, ny};
                    q.add(next);
                }
            }
        }
    }
    
    void crain(char c) {
        // 크레인 동작 -> '.' 으로 업데이트
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (map[i][j] != c) continue;
                
                map[i][j] = '.';
            }
        }
        
        bfs();
    }
    
    void jige(char c) {
        List<int[]> list = new ArrayList<>();
        
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=m; j++) {
                if (map[i][j] != c) continue;
                
                boolean flag = false;
                for (int d=0; d<4; d++) {
                    int nx = i + pos[d][0];
                    int ny = j + pos[d][1];
                    
                    if (map[nx][ny] == ' ') {
                        flag = true;
                        break;
                    }
                }
                
                if (flag) {
                    int[] cur = {i, j};
                    list.add(cur);
                }
            }
        }
        
        // 마지막에 몰아서 업데이트
        for (int[] arr : list) {
            map[arr[0]][arr[1]] = ' ';  // ' ' 로 업데이트
        }
        
        bfs();
    }
}

/**
 * ' ' : 외부, '.' : 빈공간(외부 아님)
 * 
 */