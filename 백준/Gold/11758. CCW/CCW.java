import java.io.*;
import java.util.*;

class Solution_11758 {
    void solution() throws IOException {
        int[] x = new int[3];
        int[] y = new int[3];

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        int D = (x[0] * y[1] + x[1] * y[2] + x[2] * y[0]) - (y[0] * x[1] + y[1] * x[2] + y[2] * x[0]);
        if (D > 0) {        // 반시계 방향
            System.out.println("1");
        } else if (D == 0) {        // 일직선
            System.out.println("0");
        } else {
            System.out.println("-1");        // 시계 방향
        }
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_11758 s = new Solution_11758();
        s.solution();
    }
}

/**
 * ab, ac 벡터의 외적 구하기
 * -> 신발끈 공식 이용
 * 
 * ccw 알고리즘
 */