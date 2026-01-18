import java.util.*;
import java.io.*;

class Node {
    int x;
    int y;
    int cnt;
    int keyMask;    // 민식이가 가지고 있는 key 정보
    
    Node (int x, int y, int cnt, int keyMask) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
        this.keyMask = keyMask;
    }
}

class Solution {
    
    int n, m;
    char[][] map;
    boolean[][][] visit;    // 해당 위치에 민식이가 어떤 key를 가지고 있는지 기록
    int[][] pos = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new char[n][m];
        int x = -1, y = -1;
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<m; j++) {
                map[i][j] = line.charAt(j);
                
                if (map[i][j] == '0') {
                    x = i;
                    y = j;
                }
            }
        }
        
        visit = new boolean[n][m][64];    // 열쇠 6개 -> 0 ~ 63
        
        System.out.println(bfs(x, y));
        br.close();
    }
    
    int bfs(int x, int y) {
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(x, y, 0, 0));
        visit[x][y][0] = true;
        
        while (!q.isEmpty()) {
            Node poll = q.poll();
            
            if (map[poll.x][poll.y] == '1') {
                return poll.cnt;    // 최단거리로 출구 도착
            }
            
            for (int i=0; i<4; i++) {
                int nx = poll.x + pos[i][0];
                int ny = poll.y + pos[i][1];
                
                if (nx<0 || nx>=n || ny<0 || ny>=m) continue;
                if (map[nx][ny] == '#') continue;   // 벽
                
                if ('A' <= map[nx][ny] && map[nx][ny] <= 'F') { // 문
                    if ((poll.keyMask & (1 << map[nx][ny] - 'A')) == 0) {
                        continue;   // 문에 맞는 열쇠가 없는 경우
                    } 
                }
                
                int nKeyMask = poll.keyMask;
                
                if ('a' <= map[nx][ny] && map[nx][ny] <= 'f') { // 열쇠
                    nKeyMask = poll.keyMask | (1 << map[nx][ny] - 'a'); // 열쇠 추가
                }
                
                if (!visit[nx][ny][nKeyMask]) {
                    visit[nx][ny][nKeyMask] = true;
                    q.add(new Node(nx, ny, poll.cnt + 1, nKeyMask));
                }
            }
        }
        
        return -1;  // 출구 도착 X
    }
    
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 이전에 방문한 칸을 다시 방문할 수 있어야 한다
 * -> 특정 칸에서 민식이가 가지고 있던 열쇠 정보 기록 -> 비트마스킹
 */