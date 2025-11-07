import java.util.*;

class Solution {
    int n;
    boolean[] choiceA;
    int[] a, b;
    List<Integer> sumA, sumB;
    long winMax = 0;    // A 승리 count 최댓값
    int[] ans;  // A가 선택한 주사위들
    
    public int[] solution(int[][] dice) {
        n = dice.length;
        choiceA = new boolean[n];
        
        ans = new int[n/2];
        choice(dice, 0, 0);
        return ans;
    }
    
    void choice(int[][] dice, int idx, int depth) {
        if (depth == n/2) { // a가 n/2개 주사위 고른 경우
            calc(dice);
            return;
        }
        
        for (int i=idx; i<n; i++) {
            choiceA[i] = true;
            choice(dice, i+1, depth+1);
            choiceA[i] = false;
        }
    }
    
    void calc(int[][] dice) {       // 현재 조합에서의 a 승리 횟수 계산
        a = new int[n/2];   // a 선택 주사위들 -> 주사위 인덱스 값이 원소값임
        b = new int[n/2];   // b 선택 주사위들
        int ai = 0, bi = 0;
        for (int i=0; i<n; i++) {
            if (choiceA[i]) a[ai++] = i;
            else b[bi++] = i;
        }
        
        sumA = new ArrayList<>();
        sumB = new ArrayList<>();
        makeSum(dice, a, 0, 0, sumA); // a가 고른 주사위로 가능한 합 전부 계산
        makeSum(dice, b, 0, 0, sumB); // b
        
        Collections.sort(sumB); // sumB 오름차순 정렬
        
        long win = 0;   // 현재 조합에서 a의 승리 횟수
        for (int s : sumA) {
            win += lowerbound(s);
        }
        
        if (win > winMax) {
            winMax = win;
            for (int i=0; i<a.length; i++) {
                ans[i] = a[i] + 1;
            }
            Arrays.sort(ans);   // 오름차순 정렬
        }
    }
    
    int lowerbound(int target) {   // sumB에서 target의 lowerbound 구하기
        int l = 0;
        int r = sumB.size() - 1;
        int mid;
        
        while (l <= r) {
            mid = (l + r) / 2;
            if (target <= sumB.get(mid)) r = mid - 1;
            else l = mid + 1;
        }
        return l;
    }
    
    void makeSum(int[][] dice, int[] pick, int depth, int sum, List<Integer> list) {
        if (depth == pick.length) {
            list.add(sum);
            return;
        }
        
        for (int face = 0; face<6; face++) {    // 주사위 1개 인덱스 -> 0 ~ 5
            makeSum(dice, pick, depth+1, sum + dice[pick[depth]][face], list);
        }
    }
}

// 완전탐색 -> 시간초과
// 