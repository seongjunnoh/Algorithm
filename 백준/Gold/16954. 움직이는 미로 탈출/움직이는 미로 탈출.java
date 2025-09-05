import java.io.*;
import java.util.*;

class Pair {
    int x;
    int y;
    int t;
    
    Pair(int x, int y, int t) {
        this.x = x;
        this.y = y;
        this.t = t;
    }
}

class Solution {
    
    char[][][] map;
    int[][] pos = {
        {-1, 0}, {1, 0}, {0, -1}, {0, 1},
        {-1, -1}, {-1, 1}, {1, -1}, {1, 1}, {0, 0}
    };
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        map = new char[9][8][8];    // n초에 map의 상태
        for (int i=0; i<8; i++) {
            String line = br.readLine();
            for (int j=0; j<8; j++) {
                map[0][i][j] = line.charAt(j);
            }
        }
        
        // 8초 동안의 map 상태 기록
        for (int t=1; t<=8; t++) {
            for (int i=7; i>=0; i--) {  // 아래행의 벽부터 이동
                for (int j=0; j<8; j++) {
                    map[t][i][j] = '.'; // 초기화
                    
                    if (map[t-1][i][j] == '#' && i<7) {    // 이전 시각에 i,j 가 벽인 경우
                        map[t][i+1][j] = '#';  
                    }
                }
            }
        }
        
        if (bfs(7, 0)) System.out.println("1");
        else System.out.println("0");
        br.close();
    }
    
    boolean bfs(int sX, int sY) {
        Queue<Pair> q = new LinkedList<>();
        boolean[][][] visit = new boolean[9][8][8];     // n초에 x,y 를 방문했는지 여부
        
        q.add(new Pair(sX, sY, 0));
        visit[0][sX][sY] = true;
        
        while (!q.isEmpty()) {
            Pair poll = q.poll();
            
            if (poll.t == 8) return true;   // 8초 동안 버텼을 경우, 성공
            
            // 욱제 이동
            for(int i=0; i<9; i++) {
                int nX = poll.x + pos[i][0];
                int nY = poll.y + pos[i][1];
                
                if (nX<0 || nX>=8 || nY<0 || nY>=8) continue;
                
                // 현재 시점에서의 map, 다음 시점에서의 map 이 모두 빈칸인 경우에만 이동가능
                if (!visit[poll.t + 1][nX][nY] && map[poll.t][nX][nY] == '.' && map[poll.t + 1][nX][nY] == '.') {
                    visit[poll.t + 1][nX][nY] = true;
                    q.add(new Pair(nX, nY, poll.t + 1));
                }
            }
        }
        
        return false;   // 아닐 경우, 실패
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
 * 8초가 지나면 남아있는 벽은 없다
 * -> 8초 동안 생존하면 무조건 1, 8초 내에 생존하지 못하면 0
 * 
 * bfs
 * 8초동안 특정 위치에 방문했는지 여부를 기록 -> map, visit는 3중 배열
 */