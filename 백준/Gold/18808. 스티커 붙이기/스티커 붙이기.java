import java.util.*;
import java.lang.*;
import java.io.*;

public class Main {

    static class Sticker{
        int[][] sticker;
        int count;

        Sticker(int[][] sticker, int count) {
            this.sticker = sticker;
            this.count = count;
        }
    }

    static int n, m, k;
    static int r, c;
    static int[][] book;
    static LinkedList<Sticker> stickers;            // 스티커 위치 저장 배열
    static int result = 0;
    static Sticker cur;                             // 현재 고민하는 스티커

    static void dfs(int count) {
        // count : 탐색한 sticker 개수
        if (count == stickers.size()) {
            return;
        }

        cur = stickers.get(count);

        // book 에 cur 붙이기
        if (addSticker()) {
            // book에 cur 붙일 수 있는 경우
            result += cur.count;
        }

        dfs(count + 1);
    }

    static boolean addSticker() {
        for (int i = 0; i < 4; i++) {
            int[][] loc = cur.sticker;
            int row = loc.length;
            int col = loc[0].length;

            for (int x = 0; x <= n - row; x++) {
                for (int y = 0; y <= m - col; y++) {
                    if (addable(x, y)) {
                        return true;
                    }
                }
            }

            cur.sticker = rotate();
        }

        return false;
    }

    static boolean addable(int x, int y) {
        // 현재 방향으로 cur 를 붙일 수 있는지 확인 & 붙일 수 있다면 붙이기
        // (x, y) 가 노트북의 왼쪽 상단 시작점

        int[][] loc = cur.sticker;
        int row = loc.length;
        int col = loc[0].length;

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (book[x + i][y + j] == 1 && loc[i][j] == 1) {
                    return false;
                }
            }
        }

        // 스티커 붙이기
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (loc[i][j] == 1) book[x + i][y + j] = 1;
            }
        }

        return true;
    }

    static int[][] rotate() {
        // cur 를 시계방향으로 90도 rotate
        int[][] loc = cur.sticker;
        int row = loc.length;
        int col = loc[0].length;
        int[][] nCur = new int[col][row];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                nCur[i][j] = loc[row - 1 - j][i];
            }
        }

        return nCur;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        book = new int[n][m];
        stickers = new LinkedList<>();

        for (int i=0; i<k; i++) {
            st = new StringTokenizer(br.readLine());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            int[][] sticker = new int[r][c];

            int count = 0;
            for (int j = 0; j < r; j++) {
                st = new StringTokenizer(br.readLine());
                for (int l = 0; l < c; l++) {
                    sticker[j][l] = Integer.parseInt(st.nextToken());
                    if (sticker[j][l] == 1) count++;
                }
            }
            stickers.add(new Sticker(sticker, count));
        }

        dfs(0);

        bw.write(String.valueOf(result));
        bw.flush();
        bw.close();
        br.close();
    }
}

/**
 * 골드 3 18808번 스티커 붙이기
 *
 * stickers 에 저장된 스티커들을 차례로 돌면서 현재 book 에 끼워넣기
 * -> dfs 로 구현 (이전 dfs 로 되돌아와서 다시 로직 실행하지는 않음)
 *
 * 현재 스티커를 노트북에 붙일 수 있는지 없는지를 어떻게 검증??
 * -> 노트북의 왼쪽 상단 부터 오른쪽 하단까지 움직이면서 loc와 겹치는 1이 존재하는지 확인
 * -> 만약 안된다면 loc를 회전시켜서 다시 확인
 *
 * 회전 시키는 로직
 * 101
 * 111
 * 101 에서
 * 0,0 -> 0,2 -> 2,2 -> 2.0
 * 0,1 -> 1,2 -> 2,1 -> 1,0
 * ,,,
 * i,j : i-1개, n-i개 + j-1개 + m-j개
 * ->
 */
