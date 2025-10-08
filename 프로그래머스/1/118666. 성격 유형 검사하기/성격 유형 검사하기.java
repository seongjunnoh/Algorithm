import java.util.*;

class Solution {
    
    Map<Character, Integer> map;
    
    public String solution(String[] survey, int[] choices) {
        map = new HashMap<>();
        map.put('R', 0); map.put('T', 0);
        map.put('C', 0); map.put('F', 0);
        map.put('J', 0); map.put('M', 0);
        map.put('A', 0); map.put('N', 0);
        
        StringBuilder sb = new StringBuilder();
        
        for (int i=0; i<survey.length; i++) {
            String pair = survey[i];
            int c = choices[i];
            
            calc(pair, c);
        }    
        
        if (map.get('R') >= map.get('T')) sb.append("R");
        else sb.append("T");
        
        if (map.get('C') >= map.get('F')) sb.append("C");
        else sb.append("F");
        
        if (map.get('J') >= map.get('M')) sb.append("J");
        else sb.append("M");
        
        if (map.get('A') >= map.get('N')) sb.append("A");
        else sb.append("N");
        
        return sb.toString();
    }    
    
    void calc(String pair, int c) {
        if (c == 4) return;
        if (c < 4) {
            map.put(pair.charAt(0), map.get(pair.charAt(0)) + 4 - c);
        } else {
            map.put(pair.charAt(1), map.get(pair.charAt(1)) + c - 4);
        }
    }
}