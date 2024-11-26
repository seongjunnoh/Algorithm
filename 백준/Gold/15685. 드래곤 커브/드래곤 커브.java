import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int x;
        int y;

        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int[][] map;
    static List<Pair> list;
    static int g;

    static void draw(int curG) {
        if (curG > g) return;

        // list의 점들을 시계방향으로 90도 회전 (중심축 = 끝점)
        Pair end = list.get(list.size() - 1); // 현재 list의 끝점 -> (end.x, end.y)
        int size = list.size();
        for (int i = size - 2; i >= 0; i--) {
            Pair target = list.get(i); // 회전할 점

            // 시계 방향 90도 회전 변환
            int xNew = end.x + (end.y - target.y);
            int yNew = end.y - (end.x - target.x);

            // 새로운 점 추가
            list.add(new Pair(xNew, yNew));
            map[xNew][yNew] = 1;
        }

        draw(curG + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        map = new int[101][101]; // map[x][y], 드래곤 커브에 속하는 점 표시
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken()); // x 좌표
            int y = Integer.parseInt(st.nextToken()); // y 좌표
            int d = Integer.parseInt(st.nextToken()); // 시작 방향
            g = Integer.parseInt(st.nextToken());     // 세대

            // 0세대 드래곤 커브 그리기
            list = new ArrayList<>();
            list.add(new Pair(x, y));
            map[x][y] = 1;

            // 시작 방향에 따라 다음 점 계산
            if (d == 0) { // 오른쪽
                x += 1;
            } else if (d == 1) { // 위쪽
                y -= 1;
            } else if (d == 2) { // 왼쪽
                x -= 1;
            } else if (d == 3) { // 아래쪽
                y += 1;
            }

            // 새로운 점 추가
            list.add(new Pair(x, y));
            map[x][y] = 1;

            // 드래곤 커브 그리기
            draw(1);
        }

        // 정사각형의 수 계산
        int count = 0;
        for (int x = 0; x < 100; x++) {
            for (int y = 0; y < 100; y++) {
                if (map[x][y] == 1 && map[x + 1][y] == 1 && map[x][y + 1] == 1 && map[x + 1][y + 1] == 1) count++;
            }
        }

        System.out.println(count);
        br.close();
    }
}

/**
 * 골드 3 15686번 드래곤 커브
 *
 * k 세대 드래곤 커브 = k-1 세대 드래곤 커브를 끝 점을 기준으로 90도 시계방향으로 회전 & 이걸 끝점에 이어붙인 것
 *
 * (3,3) 기준으로 (5,2) 시계방향으로 90도 회전 -> (4,5) = (3 + (3-2), 3 - (3-5))
 * (x,y)
 *
 * ------------------------
 * 좌표축의 방향을 90도 회전시켜서 생각하면 (x, y) -> [x][y] 로 표현 가능
 * 
 */