import java.util.*;
import java.io.*;
import java.lang.*;

public class Main {

    static class Card {
        long num;
        int count;

        Card(long num, int count) {
            this.num = num;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        long[] arr = new long[n];
        for (int i = 0; i < n; i++) {
            arr[i] = Long.parseLong(br.readLine());
        }

        // 오름차순 정렬
        Arrays.sort(arr);

        // 각 숫자마다 빈도수 구하기
        Card[] cards = new Card[n];
        int idx = 0;
        for (int i = 0; i < n; i++) {
            if (i == 0) {
                cards[idx] = new Card(arr[i], 1);
                continue;
            }

            if (arr[i - 1] == arr[i]) {
                cards[idx].count++;
            } else {
                idx++;
                cards[idx] = new Card(arr[i], 1);
            }
        }

        // 배열 중 사용하지 않는 부분 자르기
        cards = Arrays.copyOfRange(cards, 0, idx + 1);

        // cards 배열 count 기준으로 내림차순 정렬
        Arrays.sort(cards, new Comparator<Card>(){
            @Override
            public int compare(Card c1, Card c2) {
                if (c1.count == c2.count) {
                    if (c1.num < c2.num) return -1;
                    else return 1;
                }

                return c2.count - c1.count;
            }
        });

        System.out.println(cards[0].num);
        br.close();
    }
}

/**
 * 실버 4 11652번 카드
 *
 */
