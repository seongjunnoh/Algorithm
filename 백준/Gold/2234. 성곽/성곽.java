import java.io.*;
import java.util.*;

class Pair {
    int secNum;
    int x;
    int y;
    int rawVal;
    
    Pair(int secNum, int x, int y, int rawVal) {
        this.secNum = secNum;
        this.x = x;
        this.y = y;
        this.rawVal = rawVal;
    }
}

class Solution {
    
    int n, m;
    Pair[][] map;   // m*n 행렬
    boolean[][] visit;
    int maxSecSize;
    int[][] pos = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};   // 남동북서 순서
    int[] mask = {8, 4, 2, 1};  // 남동북서 순서
    Map<Integer, Integer> sizeMap;
    Map<Integer, Set<Integer>> nearMap;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        map = new Pair[m][n];
        for (int i=0; i<m; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<n; j++) {
                int rawVal = Integer.parseInt(st.nextToken());
                map[i][j] = new Pair(0, i, j, rawVal);
            }
        }
        
        visit = new boolean[m][n];
        int secNum = 1;
        sizeMap = new HashMap<>();
        
        for (int i=0; i<m; i++) {
            for (int j=0; j<n; j++) {
                if (!visit[i][j]) {
                    int curSecSize = bfs(i, j, secNum);
                    maxSecSize = Math.max(maxSecSize, curSecSize);
                    
                    sizeMap.put(secNum, curSecSize);    // 현재 구역의 방 크기
                
                    secNum++;   // 방 넘버++
                }
            }
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append(secNum - 1).append("\n");
        sb.append(maxSecSize).append("\n");
        
        // 3번째 연산
        nearMap = new HashMap<>();
        for (int i=1; i<secNum; i++) {
            nearMap.put(i, new HashSet<>());    // 초기화
        }
        findNear();
        
        int twoRoomMax = 0;
        for (int sec : nearMap.keySet()) {
            for (int nearSec : nearMap.get(sec)) {
                int curTwoRoom = sizeMap.get(sec) + sizeMap.get(nearSec);
                twoRoomMax = Math.max(twoRoomMax, curTwoRoom);
            }
        }
        sb.append(twoRoomMax).append("\n");
        
        System.out.println(sb);
        br.close();
    }
    
    void findNear() {
        for (int x=0; x<m; x++) {
            for (int y=0; y<n; y++) {
                for (int d=0; d<4; d++) {
                    int nX = x + pos[d][0];
                    int nY = y + pos[d][1];
                    
                    if (nX<0 || nX>=m || nY<0 || nY>=n) continue;
                    
                    if (map[x][y].secNum != map[nX][nY].secNum) {
                        nearMap.get(map[x][y].secNum).add(map[nX][nY].secNum);
                        nearMap.get(map[nX][nY].secNum).add(map[x][y].secNum);
                    }
                }
            }
        }
    }
    
    int bfs(int x, int y, int secNum) {
        Queue<Pair> q = new LinkedList<>();
        int secSize = 1;
        
        map[x][y].secNum = secNum;  // 초기값 세팅
        q.add(map[x][y]);
        visit[x][y] = true;
        
        
        while(!q.isEmpty()) {
            Pair poll = q.poll();
            
            for (int d=0; d<4; d++) {
                int nX = poll.x + pos[d][0];
                int nY = poll.y + pos[d][1];
                
                if (nX<0 || nX>=m || nY<0 || nY>=n) continue;
                
                if (!visit[nX][nY] && (poll.rawVal & mask[d]) == 0) {    // 비트마스킹 활용
                    map[nX][nY].secNum = poll.secNum;   // 같은 구역으로 세팅
                    q.add(map[nX][nY]);
                    visit[nX][nY] = true;
                    secSize++;
                }
            }
        }
        
        return secSize;
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
 * 3번째 연산을 위해서 모든 벽에 대한 제거를 고려할 필요는 없다
 * 비트마스킹 활용할 수 있다
 */
