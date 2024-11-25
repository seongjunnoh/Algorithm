import java.io.*;
import java.util.*;

public class Main {

    static long p, q;
    static Map<Long, Long> map = new HashMap<>();

    static long play(long n) {
        if (map.containsKey(n)) return map.get(n);

        if (n == 0) {
            return 1;
        }

        map.put(n, play(n / p) + play(n / q));

        return map.get(n);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long n = Long.parseLong(st.nextToken());
        p = Long.parseLong(st.nextToken());
        q = Long.parseLong(st.nextToken());

        System.out.println(play(n));
        br.close();
    }
}

/**
 * 골드 5 1351번 무한 수열
 *
 * i/p, i/q 를 정수의 나눗셈으로 구해서(x, y) & dp 에서 ax, ay 찾아서 더해가면서 an 구하자
 * x, y 는 그냥 정수의 나눗셈의 몫임
 * -> 이때 bottom up 방식은 시간 초과 (10의 12제곱만큼 for loop 돌아야 하므로)
 * -> top down 방식으로 중간중간 건너뛰면서 계산하자
 *
 * => 메모이제이션을 map 에 하자
 */
