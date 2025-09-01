import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int x;
    int y;
    int d;
    int t;  // 명령 횟수
    
    Pair(int x, int y, int d, int t) {
        this.x = x;
        this.y = y;
        this.d = d;
        this.t = t;
    }
    
    @Override
    public int compareTo(Pair p) {
        return this.t - p.t;    // t 기준 오름차순 정렬
    }
}

class Solution {
    
    int m, n;
    int[][] map;
    int sX, sY, sD;
    int eX, eY, eD;
    int[][][] pos = {
        {{0, 1}, {0, 2}, {0, 3}},
        {{0, -1}, {0, -2}, {0, -3}},
        {{1, 0}, {2, 0}, {3, 0}},
        {{-1, 0}, {-2, 0}, {-3, 0}}
    };   // 동서남북
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        
        map = new int[m+1][n+1];
        for (int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=1; j<=n; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        st = new StringTokenizer(br.readLine());
        sX = Integer.parseInt(st.nextToken());
        sY = Integer.parseInt(st.nextToken());
        sD = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        eX = Integer.parseInt(st.nextToken());
        eY = Integer.parseInt(st.nextToken());
        eD = Integer.parseInt(st.nextToken());
        
        System.out.println(dijkstra());
        br.close();
    }
    
    int dijkstra() {
        PriorityQueue<Pair> pq = new PriorityQueue<>();
        boolean[][][] visit = new boolean[m+1][n+1][5];
        
        pq.add(new Pair(sX, sY, sD, 0));
        
        while(!pq.isEmpty()) {
            Pair poll = pq.poll();
            
            if (visit[poll.x][poll.y][poll.d]) continue;
            if (poll.x == eX && poll.y == eY && poll.d == eD) return poll.t;
            
            visit[poll.x][poll.y][poll.d] = true;
            
            // 방향 전환
            if (poll.d == 1 || poll.d == 2) {
                pq.add(new Pair(poll.x, poll.y, 3, poll.t + 1));
                pq.add(new Pair(poll.x, poll.y, 4, poll.t + 1));
                pq.add(new Pair(poll.x, poll.y, 3 - poll.d, poll.t + 2));
            } else if (poll.d == 3 || poll.d == 4) {
                pq.add(new Pair(poll.x, poll.y, 1, poll.t + 1));
                pq.add(new Pair(poll.x, poll.y, 2, poll.t + 1));
                pq.add(new Pair(poll.x, poll.y, 7 - poll.d, poll.t + 2));
            }
            
            // 방향 유지 & 이동
            boolean flag = true;
            for (int i=0; i<3; i++) {
                int nX = poll.x + pos[poll.d - 1][i][0];
                int nY = poll.y + pos[poll.d - 1][i][1];
            
                if (nX<=0 || nX>=m+1 || nY<=0 || nY>=n+1) continue;
                if (visit[nX][nY][poll.d]) continue;
                
                // 이전 궤도 -> 현재 궤도로 가능한지 : flag
                if (flag && map[nX][nY] == 0) pq.add(new Pair(nX, nY, poll.d, poll.t + 1));
                else flag = false;
            }
            
        }
        
        return -1;
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
 * 다익스트라
 */