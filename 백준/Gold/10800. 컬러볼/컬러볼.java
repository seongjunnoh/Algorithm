import java.io.*;
import java.util.*;

class Pair implements Comparable<Pair> {
    int c;  // color
    int s;  // size
    int idx;    // 공 번호
    
    Pair (int c, int s, int idx) {
        this.c = c;
        this.s = s;
        this.idx = idx;
    }
    
    @Override
    public int compareTo(Pair p) {
        if (this.s == p.s) {
            return this.c - p.c;    // s 같으면 c 기준 오름차순 정렬
        }
        return this.s - p.s;    // s 기준 오름차순 정렬
    }
}

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        int n = Integer.parseInt(br.readLine());
        List<Pair> list = new ArrayList<>();
        
        for (int i=0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
            int c = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            
            Pair p = new Pair(c, s, i);
            list.add(p);
        }

        Collections.sort(list);     // list 정렬
        
        int[] sumByColor = new int[n+1];  // 현재 크기보다 작은 공들 중, 색깔별 크기 누적합
        int sum = 0;    // 현재 크기보다 작은 공들의 크기 누적합
        
        int[] res = new int[n]; // 공 번호에 따른 결과값
        
        for (int i=0; i<n; i++) {
            int curSize = list.get(i).s;  // 현재 구간의 size
            
            // 사이즈 동일한 구간 구하기 [i~j)
            int j = i;
            while (j < n && list.get(j).s == curSize) j++;
            
            for (int k=i; k<j; k++) {
                Pair p = list.get(k);
                res[p.idx] = sum - sumByColor[p.c]; // curSize보다 작은 공들 중, 색깔이 동일한 공 제외한 누적합
            }
            
            for (int k=i; k<j; k++) {  // curSize 그룹 전체 누적합에 반영
                Pair p = list.get(k);
                sum += p.s;
                sumByColor[p.c] += p.s;
            }
            
            i = j-1;  // j부터 시작하는 구간으로
        }
        
        StringBuilder sb = new StringBuilder();
        for (int i=0; i<n; i++) {
            sb.append(res[i]).append('\n');
        }
        
        System.out.println(sb);
        br.close();
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/* 누적합 + color 에 따라서 구분
 * 
 */