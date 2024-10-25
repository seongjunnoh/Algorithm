import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        int count = 0;
        while (true) {
            if (b == a) break;
            if (b < a) {
                count = -1;
                break;
            }

            if (b % 2 == 0) {      // 2로 나눠야 함
                b /= 2;
                count++;
            } else if (b % 10 == 1) {       // 오른쪽 1 제거
                b = (b - 1) / 10;
                count++;
            } else {
                count = -1;
                break;
            }
        }

        if (count == -1) System.out.println("-1");
        else System.out.println(count + 1);
        br.close();
    }
}

/**
 * 실버 2 16953 A->B
 *
 * 가능한 연산 : 2 곱하기, 1을 가장 오른쪽에 추가 -> 둘 중 1을 오른쪽에 붙이는게 더 큰 수로 변화시키는 것임
 * => 그리디 적으로 생각 가능
 *      => 어차피 1을 오른쪽에 추가하는게 2를 곱하는거 보다 크므로, 연산의 최소 개수를 구하기 위해서 1 추가를 먼저 생각하는게 맞음
 *
 */
