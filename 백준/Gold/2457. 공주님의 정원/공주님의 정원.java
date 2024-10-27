import java.io.*;
import java.util.*;

public class Main {

    static class Flower {
        int start;
        int end;

        Flower(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = Integer.parseInt(br.readLine());
        Flower[] flowers = new Flower[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int s1 = Integer.parseInt(st.nextToken());
            int s2 = Integer.parseInt(st.nextToken());
            int e1 = Integer.parseInt(st.nextToken());
            int e2 = Integer.parseInt(st.nextToken());
            int start = s1 * 100 + s2;
            int end = e1 * 100 + e2;

            flowers[i] = new Flower(start, end);
        }

        // 개화일이 빠른 순으로, 개화일이 같다면 지는 일이 빠를 순으로 정렬
        Arrays.sort(flowers, new Comparator<Flower>() {
            @Override
            public int compare(Flower f1, Flower f2) {
                if (f1.start != f2.start) return Integer.compare(f1.start, f2.start);
                return Integer.compare(f1.end, f2.end);
            }
        });

        int idx = 0;
        int currentDate = 301;         // 3월 1일 부터
        int endDate = 1130;          // 11월 30일 까지
        int result = 0;

        while (currentDate <= endDate) {
            int maxEndDate = currentDate;
            while (idx < n && flowers[idx].start <= currentDate) {
                if (flowers[idx].end > maxEndDate) maxEndDate = flowers[idx].end;
                idx++;
            }

            if (maxEndDate == currentDate) {        // 현재 선택할 꽃이 없음
                result = 0;
                break;
            }

            currentDate = maxEndDate;
            result++;
        }

        System.out.println(result);
        br.close();
    }
}

/**
 * 골드 3 2457 공주님의 정원
 *
 * 3월 1일 부터 11월 30일 까지는 한가지 이상의 꽃이 피어있도록 & 꽃의 최소 개수
 *
 * 일단 피는 날 기준 오름차순 정렬
 * -> 비는 기간 없이 꽃 선택
 */
