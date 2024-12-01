import java.io.*;
import java.util.*;

public class Main {

    static class Pair {
        int h;
        int k;

        Pair (int h, int k) {
            this.h = h;
            this.k = k;
        }
    }

    static class Team implements Comparable<Team> {
        int h;      // 팀에서 가장 키 큰 학생의 키
        int size;   // 팀의 크기

        Team(int h, int size) {
            this.h = h;
            this.size = size;
        }

        public void addStudent() {
            size++;
        }

        @Override
        public int compareTo(Team t) {        // 팀의 크기 기준으로 내림차순 정렬
            if (t.size == size) return t.h - h;     // 팀 크기 같을 경우 팀 대표의 키로 내림차순 정렬
            return t.size - size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Pair[] arr = new Pair[n];
        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            arr[i] = new Pair(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        Arrays.sort(arr, new Comparator<Pair>() {
            @Override
            public int compare(Pair p1, Pair p2) {
                return p2.h - p1.h;     // 키 기준 내림차순 정렬
            }
        });

        TreeSet<Team> set = new TreeSet<>();     // set 요소 하나 : 해당 팀 크기, set 전체 크기 : 전체 팀의 개수
        set.add(new Team(arr[0].h, 1));     // 가장 키 큰 학생이 팀 하나 생성

        for (int i = 1; i < n; i++) {
            Pair pair = arr[i];

            Team higher = set.higher(new Team(0, pair.k));      // pair.k보다 팀의 크기가 작은 팀들 중 최대인 팀
            if (higher == null) {
                set.add(new Team(pair.h, 1));      // 새로 팀 하나 생성
            } else {
                set.remove(higher);
                higher.addStudent();
                set.add(higher);
            }
        }

        System.out.println(set.size());
        br.close();
    }
}

/**
 * 골드 1 19700번 수업
 *
 * n명을 나누기 -> i번째 학생은 자신의 팀에서 ki번쨰 이내로 키가 커야 함 -> 팀 개수 최소값 구하기
 *
 * 키 순으로 내림차순 정렬 -> 키 큰 사람부터 생각
 * -> i번째 학생을 팀의 크기가 ki보다 작은 팀 중 하나에 추가 (i번쨰 학생은 해당 팀 내에서 팀의 크기 + 1 등)
 * -> 팀의 크기가 ki보다 작은 팀이 여러개 있을 경우
 *    => 전체 팀의 개수를 최소로 만들기 위해, 팀의 크기가 가장 큰 팀에 추가 (그리디)
 *    => 팀의 크기가 작은 팀들을 최대한 오래 보존하는게 이득
 * -> 이런 팀 없으면 팀 새로 생성 (i번째 학생이 해당 팀 내에서 1등)
 *
 */
