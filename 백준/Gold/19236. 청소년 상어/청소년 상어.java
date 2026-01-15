import java.io.*;
import java.util.*;

class Fish {
    int num;
    int dir;
    
    Fish (int num, int dir) {
        this.num = num;
        this.dir = dir;
    }
}

class Solution {
    
    int max;
    int pos[][] = {{-1,0}, {-1,-1}, {0,-1}, {1,-1}, {1,0}, {1,1}, {0,1}, {-1,1}};
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        Fish[][] map = new Fish[4][4];
        for (int i=0; i<4; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j=0; j<4; j++) {
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                
                Fish fish = new Fish(a, b);
                map[i][j] = fish;
            }
        }
        
        int sum = map[0][0].num;    // 상어가 먹은 물고기 번호 합
        map[0][0].num = 0;      // 빈 공간으로
        
        max = sum;
        play(0, 0, sum, map);
        
        System.out.println(max);
        br.close();
    }
    
    void play(int sx, int sy, int sum, Fish[][] map) {
        // map copy
        Fish[][] curMap = new Fish[4][4];
        for (int i=0; i<4; i++) {
            for (int j=0; j<4; j++) {
                Fish f = map[i][j];
                curMap[i][j] = new Fish(f.num, f.dir);
            }
        }
        
        // 번호 순서대로 물고기 이동
        int curNum = 1;
        while (curNum <= 16) {
            boolean isMove = false;
            for (int i=0; i<4; i++) {
                if (isMove) break;
                
                for (int j=0; j<4; j++) {
                    if (isMove) break;
                    
                    Fish f = curMap[i][j];
                    if (f.num != curNum) continue;
                    
                    for (int d=f.dir-1; d<f.dir+7; d++) {  // d%8 : 0~7
                        int nx = i + pos[d%8][0];
                        int ny = j + pos[d%8][1];
                        int nd = d%8 + 1;
                        
                        if (nx<0 || nx>=4 || ny<0 || ny>=4) continue;   // 공간 넘는 칸
                        if (nx == sx && ny == sy) continue;    // 상어있는 칸
                        
                        // 물고기 이동
                        Fish nf = new Fish(f.num, nd);
                        
                        Fish temp = curMap[nx][ny];
                        curMap[nx][ny] = nf;
                        curMap[i][j] = temp;
                        
                        isMove = true;
                        break;
                    }
                }
            }
            
            curNum++;
        }
        
        // 상어 이동
        Fish shark = curMap[sx][sy];
        
        for (int multi=1; multi<=3; multi++) {
            int nsx = sx + multi * pos[shark.dir-1][0];
            int nsy = sy + multi * pos[shark.dir-1][1];
            
            if (nsx<0 || nsx>=4 || nsy<0 || nsy>=4) continue;   // 공간 넘는 칸
            if (curMap[nsx][nsy].num == 0) continue;   // 빈 공간
            
            // 물고기 먹기
            int eatNum = curMap[nsx][nsy].num;
            sum += eatNum;
            curMap[nsx][nsy].num = 0;   // 빈공간으로
            
            max = Math.max(max, sum);    // 최댓값 업데이트
            
            play(nsx, nsy, sum, curMap);     // 다시 물고기 이동
            
            // 원상복구
            sum -= eatNum;
            curMap[nsx][nsy].num = eatNum;
        }
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 구현
 */
