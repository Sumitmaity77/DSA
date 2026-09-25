import java.util.*;

class Solution {
    public List<String> braceExpansionII(String expression) {
        Set<String> result = parse(expression);
        List<String> sortedList = new ArrayList<>(result);
        Collections.sort(sortedList);
        return sortedList;
    }

    private Set<String> parse(String expr) {
        List<Set<String>> groups = new ArrayList<>();
        groups.add(new TreeSet<>());

        int level = 0, start = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '{') {
                if (level == 0) start = i + 1;
                level++;
            } else if (c == '}') {
                level--;
                if (level == 0) {
                    Set<String> subResult = parse(expr.substring(start, i));
                    groups.set(groups.size() - 1, multiply(groups.get(groups.size() - 1), subResult));
                }
            } else if (c == ',' && level == 0) {
                groups.add(new TreeSet<>());
            } else if (level == 0) {
                Set<String> subResult = new TreeSet<>();
                subResult.add(String.valueOf(c));
                groups.set(groups.size() - 1, multiply(groups.get(groups.size() - 1), subResult));
            }
        }

        Set<String> finalResult = new TreeSet<>();
        for (Set<String> group : groups) {
            finalResult.addAll(group);
        }
        return finalResult;
    }

    private Set<String> multiply(Set<String> set1, Set<String> set2) {
        if (set1.isEmpty()) return set2;
        if (set2.isEmpty()) return set1;
        Set<String> result = new TreeSet<>();
        for (String s1 : set1) {
            for (String s2 : set2) {
                result.add(s1 + s2);
            }
        }
        return result;
    }
}