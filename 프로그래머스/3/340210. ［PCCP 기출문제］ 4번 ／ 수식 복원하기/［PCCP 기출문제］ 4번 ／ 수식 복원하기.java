import java.util.*;

class Solution {
    public String[] solution(String[] expressions) {
        // 수식 파싱, 최솟값 진법(maxDigit + 1) 찾기
        int maxDigit = 0;
        List<String[]> parsedExpr = new ArrayList<>(); // [A, op, B, C]

        for (String ex : expressions) {
            String[] parts = ex.split(" "); // A, op, B, =, C
            String A = parts[0];
            String op = parts[1];
            String B = parts[2];
            String C = parts[4];
            
            parsedExpr.add(new String[]{A, op, B, C});

            // 수식에 등장하는 가장 큰 숫자 찾기
            maxDigit = Math.max(maxDigit, getMaxDigit(A));
            maxDigit = Math.max(maxDigit, getMaxDigit(B));
            if (!C.equals("X")) {
                maxDigit = Math.max(maxDigit, getMaxDigit(C));
            }
        }

        // 가능한 진법 후보 찾기 (maxDigit + 1 부터 9까지)
        List<Integer> validBases = new ArrayList<>();
        
        for (int base = maxDigit + 1; base <= 9; base++) {
            boolean isPossible = true;
            for (String[] parts : parsedExpr) {
                String A = parts[0];
                String op = parts[1];
                String B = parts[2];
                String C = parts[3];

                if (C.equals("X")) continue; // 결과가 X인 식 제외

                // 해당 진법으로 변환하여 계산이 맞는지 확인
                int numA = Integer.parseInt(A, base);
                int numB = Integer.parseInt(B, base);
                int numC = Integer.parseInt(C, base);

                int result = op.equals("+") ? numA + numB : numA - numB;
                if (result != numC) {
                    isPossible = false;
                    break;
                }
            }
            
            if (isPossible) {
                validBases.add(base);
            }
        }

        // X 채우기
        List<String> answerList = new ArrayList<>();
        
        for (String[] parts : parsedExpr) {
            String A = parts[0];
            String op = parts[1];
            String B = parts[2];
            String C = parts[3];

            if (!C.equals("X")) {
                continue; 
            }

            String expectedResult = null;
            boolean isAmbiguous = false;

            for (int base : validBases) {
                int numA = Integer.parseInt(A, base);
                int numB = Integer.parseInt(B, base);
                
                int calcRes = op.equals("+") ? numA + numB : numA - numB;
                // 계산 결과를 다시 해당 진법의 문자열로 변환
                String resStr = Integer.toString(calcRes, base).toUpperCase();

                if (expectedResult == null) {
                    expectedResult = resStr;
                } else {
                    if (!expectedResult.equals(resStr)) {
                        isAmbiguous = true;
                        break;
                    }
                }
            }

            String finalVal = isAmbiguous ? "?" : expectedResult;
            answerList.add(A + " " + op + " " + B + " = " + finalVal);
        }

        return answerList.toArray(new String[0]);
    }

    private int getMaxDigit(String numStr) {
        int max = 0;
        for (char c : numStr.toCharArray()) {
            max = Math.max(max, c - '0');
        }
        return max;
    }
}