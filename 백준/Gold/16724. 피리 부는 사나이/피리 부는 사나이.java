import java.io.*;
import java.util.*;

class Solution {
    
    int n, m;
    int[] parent;
    int[][] dir;
    int[][] pos = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        dir = new int[n][m];
        for (int i=0; i<n; i++) {
            String line = br.readLine();
            for (int j=0; j<m; j++) {
                char d = line.charAt(j);
                dir[i][j] = findDir(d);
            }
        }
        
        int total = n * m;
        parent = new int[total];
        for (int i=0; i<total; i++) {
            parent[i] = i;  // 초기화
        }   
        
        for (int x=0; x<n; x++) {   // 유니온 파인드
            for (int y=0; y<m; y++) {
                int curD = dir[x][y];
                int nX = x + pos[curD][0];
                int nY = y + pos[curD][1];
                
                union(toIdx(x, y), toIdx(nX, nY));
            }
        }   
        
        HashSet<Integer> set = new HashSet<>();
        for (int x=0; x<n; x++) {   // 유니온 파인드
            for (int y=0; y<m; y++) {
                // 현재 지점의 루트를 set에 add
                set.add(find(toIdx(x, y)));
            }
        }   
        
        System.out.println(set.size());
        br.close();
    }
    
    int toIdx(int x, int y) {   // 2차원 -> 1차원 idx
        return x * m + y;   
    }
    
    void union(int prev, int next) {    
        int rP = find(prev);
        int rN = find(next);
        
        // next의 루트를 prev의 루트로 업데이트
        if (rP != rN) parent[rN] = rP;
    }
    
    int find(int node) {
        if (parent[node] == node) return node;
        parent[node] = find(parent[node]);  // 경로압축
        return parent[node];
    }
    
    int findDir(char d) {
        if (d == 'U') return 0;
        if (d == 'D') return 1;
        if (d == 'L') return 2;
        return 3;
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
 * map의 각 지점은 무조건 outgoing dir 이 1개이므로 사이클의 집합으로 구성된다
 * -> 유니온 파인드로 얻은 집합 1개당 세이프존 1개인 것이 최소이다
 * 
 */
