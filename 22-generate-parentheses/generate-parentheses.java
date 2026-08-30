
class Solution {

    public void generateParenthesis(int n, int l, int r, String s, List<String> ans) {

        if (r == n) { //base case
            ans.add(s);
            return;
        }
        if (l < n)
            generateParenthesis(n, l + 1, r, s + "(", ans);
        if (r <l)generateParenthesis(n, l, r + 1, s + ")", ans);
    }

    public List<String> generateParenthesis(int n) {

        List<String> ans = new ArrayList<>();

       generateParenthesis(n, 0, 0, "", ans);

        return ans;
    }
}
