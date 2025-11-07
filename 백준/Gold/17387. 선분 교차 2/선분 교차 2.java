import java.util.*;
import java.io.*;

class Point implements Comparable<Point> {
    int x;
    int y;
    
    Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    @Override
    public int compareTo(Point p) { // 더 왼쪽에 위차한 점 판별
        if (x == p.x) return y - p.y;
        return x - p.x;
    }
}

class Solution {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        
        st = new StringTokenizer(br.readLine());
        Point a = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point b = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        
        st = new StringTokenizer(br.readLine());
        Point c = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        Point d = new Point(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        
        int ab_c = ccw(a, b, c);
        int ab_d = ccw(a, b, d);
        int cd_a = ccw(c, d, a);
        int cd_b = ccw(c, d, b);
        
        int result;
        if (ab_c == 0 && ab_d == 0 && cd_a == 0 && cd_b == 0) { // a,b,c,d 모두 한 직선 위에 존재
            if (b.compareTo(a) < 0) {   // a->b 순으로 조정
                Point t = a;
                a = b;
                b = t;
            }   
            if (d.compareTo(c) < 0) {   // c->d 순으로 조정
                Point t = c;
                c = d;
                d = t;
            }
            
            if (c.compareTo(b) <= 0 && a.compareTo(d) <= 0) result = 1; // a,c,b,d or c,a,d,b 순
            else result = 0;
        } else {
            int r1 = ab_c * ab_d;
            int r2 = cd_a * cd_b;
            
            if (r1 <= 0 && r2 <= 0) result = 1;
            else result = 0;
        }
        
        System.out.println(result);
        br.close();
    }
    
    int ccw(Point a, Point b, Point c) {
        long r = ((long)a.x*b.y + (long)b.x*c.y + (long)c.x*a.y) - 
                    ((long)a.y*b.x + (long)b.y*c.x + (long)c.y*a.x);
        if (r > 0) return 1;    // 반시계
        else if (r < 0) return -1;  // 시계
        else return 0;  // 일직선
    }
}

public class Main {
	public static void main(String[] args) throws IOException {
		Solution s = new Solution();
		s.solution();
	}
}

/**
 * 그냥 기울기 계산 -> 계산 결과 달라질 수 있음
 * ccw 활용
 */
