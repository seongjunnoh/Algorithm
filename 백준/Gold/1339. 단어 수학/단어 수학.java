import java.io.*;
import java.util.*;

class Alpha implements Comparable<Alpha>{
    char c;
    int w;

    Alpha(char c, int w) {
        this.c = c;
        this.w = w;
    }

    @Override
    public int compareTo(Alpha a) {
        return a.w - this.w;        // 가중치 기준 내림차순 정렬
    }
}

class Solution_1339 {
    void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<Character, Integer> wMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String word = br.readLine();
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                wMap.put(c, wMap.getOrDefault(c, 0) + (int) Math.pow(10, word.length() - j - 1));
            }
        }

        PriorityQueue<Alpha> pq = new PriorityQueue<>();
        for (Character key : wMap.keySet()) {
            pq.add(new Alpha(key, wMap.get(key)));
        }

        int num = 9;
        int sum = 0;
        while (!pq.isEmpty()) {
            Alpha poll = pq.poll();
            sum += poll.w * num--;
        }

        System.out.println(sum);
        br.close();
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        Solution_1339 s = new Solution_1339();
        s.solution();
    }
}

/**
 * 알파벳 -> 0 ~ 9의 숫자로 변경 & 숫자들의 합의 최댓값 구하기
 *
 * 주어진 단어에서 자릿수에 따라 각 알파벳에 가중치 부여
 * -> 가중치의 합이 큰 알파벳부터 큰 수 부여
 *
 */