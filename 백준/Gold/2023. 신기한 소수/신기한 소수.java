import java.io.*;
import java.util.*;

class Solution_2023 {

    int n;
    PriorityQueue<Integer> pq;

    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        pq = new PriorityQueue<>();
        pq.add(2);
        pq.add(3);
        pq.add(5);
        pq.add(7);
        play(1);

        while (!pq.isEmpty()) {
            System.out.println(pq.poll());
        }
        br.close();
    }

    void play(int count) {
        if (count == n) {
            return;
        }

        int size = pq.size();
        for (int i = 0; i < size; i++) {
            Integer num = pq.poll();
            for (int j = 1; j <= 9; j++) {
                if (isPrime(num * 10 + j)) {
                    pq.add(num * 10 + j);
                }
            }
        }

        play(count + 1);
    }

    boolean isPrime(int num) {
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_2023 s = new Solution_2023();
        s.solution();
    }
}

/**
 * 메모리 제한이 4MB 이므로 에라토스테네스의 체 방식으로 10의 n제곱보다 작은 모든 소수 찾는 방식은 적합하지 X
 *
 * 2, 3, 5, 7 에서 시작
 * -> 뒤에 1 ~ 9까지 숫자 붙이면서 새로 만든 수가 소수인지 판단
 * -> n자리 수에서 소수인 수가 정답
 */