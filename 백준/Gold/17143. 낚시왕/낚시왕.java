import java.io.*;
import java.util.*;

class Shark {
    int r; int c;   // 상어 위치
    int s;  // 상어 속력
    int d;  // 상어 현재 이동 방향
    int z;  // 상어 현재 크기
    
    Shark(int r, int c, int s, int d, int z) {
        this.r = r;
        this.c = c;
        this.s = s;
        this.d = d;
        this.z = z;
    }
}

class Solution {
    
    int R, C;
    int[][] map;
    Map<Integer, Shark> sharkMap;
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        
        map = new int[R+1][C+1];    // map[i][j] = x(>0) : 해당 위치에 x번 상어가 있음
        sharkMap = new HashMap<>();  // key : 상어 번호, value : 상어
        int key = 1;
        
        for (int i=0; i<M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int z = Integer.parseInt(st.nextToken());
            
            map[r][c] = key;
            Shark shark = new Shark(r, c, s, d, z);
            sharkMap.put(key, shark);
            key++;
        }
        
        int sum = 0;    // 잡은 상어 크기 합
        for (int king=1; king<=C; king++) {
            // 현재 열의 상어 중, row가 가장 작은 상어 잡기
            for (int row=1; row<=R; row++) {
                if (map[row][king] == 0) continue;
                
                sum += sharkMap.get(map[row][king]).z;
                map[row][king] = 0;     // map에서 상어 번호 삭제
                break;
            }
            
            move();     // 상어 이동
        }
        
        System.out.println(sum);
        br.close();
    }
    
    void move() {
        int[][] nMap = new int[R+1][C+1];     // 상어 이동 이후 map
        
        for (int row=1; row<=R; row++) {
            for (int col=1; col<=C; col++) {
                if (map[row][col] == 0) continue;
                
                // map[row][col] 위치의 상어 이동
                int num = map[row][col]; 
                Shark shark = sharkMap.get(num);
                
                if (shark.d == 1 || shark.d == 2) {     // 위, 아래로 이동
                    shark = moveInCol(shark, shark.s);
                } else {    // 오른쪽, 왼쪽 이동
                    shark = moveInRow(shark, shark.s);
                }
                
                if (nMap[shark.r][shark.c] != 0) {    // 다른 상어가 있는 경우
                    Shark exist = sharkMap.get(nMap[shark.r][shark.c]);
                    
                    if (exist.z < shark.z) {    // shark의 크기가 더 큰 경우에만 업데이트
                        nMap[shark.r][shark.c] = num;
                        sharkMap.put(num, shark);
                    }
                } else {    // 다른 상어가 없는 경우
                    nMap[shark.r][shark.c] = num;
                    sharkMap.put(num, shark);
                }
            }
        }
        
        map = nMap;   // map 업데이트
    }
    
    Shark moveInCol(Shark s, int remain) {
        if (s.d == 1) {   // 위로 이동
            int nRow = s.r - remain;
            if (nRow < 1) {
                s.r = 1;
                s.d = 2;
                return moveInCol(s, 1 - nRow);
            }
            
            s.r = nRow;
            return s;
        } else {    // 아래로 이동
            int nRow = s.r + remain;
            if (nRow > R) {
                s.r = R;
                s.d = 1;
                return moveInCol(s, nRow - R);
            }
            
            s.r = nRow;
            return s;
        }
    }
    
    Shark moveInRow(Shark s, int remain) {
        if (s.d == 3) {   // 오른쪽 이동
            int nCol = s.c + remain;
            if (nCol > C) {
                s.c = C;
                s.d = 4;
                return moveInRow(s, nCol - C);
            }
            
            s.c = nCol;
            return s;
        } else {    // 왼쪽 이동
            int nCol = s.c - remain;
            if (nCol < 1) {
                s.c = 1;
                s.d = 3;
                return moveInRow(s, 1 - nCol);
            }
            
            s.c = nCol;
            return s;
        }        
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/* 
 * 구현
 */