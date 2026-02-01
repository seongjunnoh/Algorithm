import java.util.*;

class Solution {
    
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};   // 위, 아래, 왼, 오른
    
    public int solution(int[][] points, int[][] routes) {
        int x = routes.length;  // 로봇 개수
        List<int[]>[] path = new ArrayList[x];    // 로봇들 이동 경로
        for (int ro = 0; ro<x; ro++) {
            path[ro] = new ArrayList<>();
        }
        
        // 로봇 경로 계산
        int maxMove = 0;    // 로봇들 중 최대 이동 횟수
        for (int ro=0; ro<x; ro++) {
            int startIdx = routes[ro][0] - 1;
            int r = points[startIdx][0];
            int c = points[startIdx][1];
            path[ro].add(new int[]{r, c});      // 로봇 시작점
            
            for (int i=1; i<routes[ro].length; i++) {
                int nextIdx = routes[ro][i] - 1;
                int targetR = points[nextIdx][0];
                int targetC = points[nextIdx][1];
                
                // r좌표 먼저 이동
                while (r != targetR) {
                    if (r < targetR) r++;
                    else r--;
                    path[ro].add(new int[]{r, c});
                }
                
                // c좌표 이동
                while (c != targetC) {
                    if (c < targetC) c++;
                    else c--;
                    path[ro].add(new int[]{r, c});
                }
            }
            
            maxMove = Math.max(maxMove, path[ro].size());
        }
        
        // 로봇 이동
        int answer = 0;
        for (int i=0; i<maxMove; i++) {
            int[][] map = new int[101][101];    // 해당 칸에 로봇이 몇개 위치하는지 기록
            
            for (int ro=0; ro<x; ro++) {
                if (path[ro].size() <= i) continue;  // 해당 로봇은 더이상 이동 X
                
                int[] curP = path[ro].get(i);
                map[curP[0]][curP[1]]++;    // 해당 위치 로봇 숫자 업데이트
            }
            
            // 겹치는 위치 확인
            for (int r=0; r<101; r++) {
                for (int c=0; c<101; c++) {
                    if (map[r][c] > 1) answer++;
                }
            }
        }
        
        return answer;
    }
}

/**
 * 로봇 이동 경로를 미리 확정
 */