import java.io.*;
import java.util.*;
import java.lang.*;

public class Main {

    static class Pair {
        int num;
        int idx;
        int count;

        Pair(int num, int idx, int count) {
            this.num = num;
            this.idx = idx;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        Pair[] arr = new Pair[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int num = Integer.parseInt(st.nextToken());
            arr[i] = new Pair(num, i, 1);
        }

        // Pair 의 num 기준 오름차순으로 arr 정렬
        Arrays.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p1.num - p2.num;
            }
        });

        // idx 값 계산 -> Pair의 num가 같은 것들은 idx값이 제일 작은 걸로 모든 Pair의 idx값을 통일
        // count 값 계산
        int minIdx = arr[0].idx;
        int count = 0;      // arr[i-1]의 num와 같은 Pair의 개수
        int start = 0;
        for (int i = 1; i < n; i++) {
            if (arr[i - 1].num == arr[i].num) {
                int lowerIdx = Math.min(arr[i - 1].idx, arr[i].idx);
                minIdx = Math.min(lowerIdx, minIdx);
                count++;
            }
            else {
                for (int j = start; j < i; j++) {
                    arr[j].idx = minIdx;
                    arr[j].count += count;

//                    System.out.println("arr[" + j + "].num = " + arr[j].num);
//                    System.out.println("arr[" + j + "].idx = " + arr[j].idx);
//                    System.out.println("arr[" + j + "].count = " + arr[j].count);
                }
                minIdx = arr[i].idx;
                count = 0;
                start = i;
            }

            if (i == n - 1) {
                for (int j = start; j <= i; j++) {
                    arr[j].idx = minIdx;
                    arr[j].count += count;

//                    System.out.println("arr[" + j + "].num = " + arr[j].num);
//                    System.out.println("arr[" + j + "].idx = " + arr[j].idx);
//                    System.out.println("arr[" + j + "].count = " + arr[j].count);
                }
            }
        }

        // arr을 Pair의 count 기준으로 내림차순 정렬
        Arrays.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                if (p1.count == p2.count) return p1.idx - p2.idx;
                return p2.count - p1.count;
            }
        });

//        System.out.println("================");
//        for (int i = 0; i < n; i++) {
//            System.out.print(arr[i].num + " ");
//        }
//        System.out.println();
//        System.out.println("================");
//        for (int i = 0; i < n; i++) {
//            System.out.print(arr[i].idx + " ");
//        }
//        System.out.println();
//        System.out.println("================");
//        for (int i = 0; i < n; i++) {
//            System.out.print(arr[i].count + " ");
//        }
//        System.out.println();
//        System.out.println("================");

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(arr[i].num).append(" ");
        }
        System.out.println(sb);
        br.close();
    }
}

/**
 * 실버 3 2910 빈도 정렬
 *
 * 어떤 수가 입력되는 빈도를 어떻게 계산??
 * -> 정렬 후 한번 순차탐색
 * -> 그런데 이걸 각각의 숫자에 어떻게 할당?? => 이중 for 문 ??
 */
