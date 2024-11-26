import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int y;
        int x;

        Pair(int y, int x) {
            this.y = y;
            this.x = x;
        }
    }

    static int[][] map;
    static List<Pair> list;
    static int g;

    static void draw(int curG) {
        if (curG > g) return;

        // list의 점들을 시계방향으로 90도 회전 (중심축 = 끝점)
        Pair end = list.get(list.size() - 1);       // 현재 list의 끝점 -> (end.x, end.y)
        for (int i = list.size() - 2; i >= 0; i--) {
            // 최근에 그린 드래곤 커브 순으로 회전
            Pair target = list.get(i);  // (target.x, target.y)

            map[end.y - (end.x - target.x)][end.x + (end.y - target.y)] = 1;
            list.add(new Pair(end.y - (end.x - target.x), end.x + (end.y - target.y)));
        }

//        System.out.println("=======================");
//        System.out.println("g = " + curG);
//        for (Pair pair : list) {
//            System.out.print(pair.x + ", " + pair.y + " || ");
//        }
//        System.out.println();
//        System.out.println("=======================");

        draw(curG + 1);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        map = new int[101][101];        // map의 격자점이 1 -> 드래곤 커브에 속하는 점
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            g = Integer.parseInt(st.nextToken());

            // 0세대 드래곤 커브 그리기
            list = new LinkedList<>();
            if (d == 0) {
                map[y][x] = 1;
                map[y][x + 1] = 1;
                list.add(new Pair(y, x));
                list.add(new Pair(y, x + 1));
            } else if (d == 1) {
                map[y][x] = 1;
                map[y - 1][x] = 1;
                list.add(new Pair(y, x));
                list.add(new Pair(y - 1, x));
            } else if (d == 2) {
                map[y][x] = 1;
                map[y][x - 1] = 1;
                list.add(new Pair(y, x));
                list.add(new Pair(y, x - 1));
            } else {
                map[y][x] = 1;
                map[y + 1][x] = 1;
                list.add(new Pair(y, x));
                list.add(new Pair(y + 1, x));
            }
//
//            System.out.println("=======================");
//            System.out.println("g = " + 0);
//            for (Pair pair : list) {
//                System.out.print(pair.x + ", " + pair.y + " || ");
//            }
//            System.out.println();
//            System.out.println("=======================");

            // map에 드래곤 커브 그리기
            draw(1);
        }

        // map에서의 정답 계산
        int count = 0;
        for (int i = 0; i < 100; i++) {
            for (int j = 0; j < 100; j++) {
                // (i,j)가 왼쪽 상단인 정사각형
                if (map[i][j] == 1 && map[i][j + 1] == 1 && map[i + 1][j] == 1 && map[i + 1][j + 1] == 1) count++;
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
 * x,y 문제에 맞춰서 생각하자
 *
 * (3,3) 기준으로 (5,2) 시계방향으로 90도 회전 -> (4,5) = (3 + (3-2), 3 - (3-5))
 * (x,y)
 *
 */
