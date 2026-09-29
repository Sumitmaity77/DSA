class Solution {
    private Boolean[][][] memo;
    private int m, n;
    
    public boolean hasValidPath(char[][] grid) {
        m = grid.length;
        n = grid[0].length;
        
        // Path length must be even to have a valid parentheses sequence
        if ((m + n - 1) % 2 != 0) return false;
        
        // Maximum possible balance is m + n
        memo = new Boolean[m][n][m + n + 1];
        
        return dfs(grid, 0, 0, 0);
    }
    
    private boolean dfs(char[][] grid, int r, int c, int balance) {
        // Update balance based on current cell
        if (grid[r][c] == '(') {
            balance++;
        } else {
            balance--;
        }
        
        // If balance drops below 0, the prefix is invalid
        if (balance < 0) return false;
        
        // If we reach the bottom-right cell
        if (r == m - 1 && c == n - 1) {
            return balance == 0;
        }
        
        // Check memoization table
        if (memo[r][c][balance] != null) {
            return memo[r][c][balance];
        }
        
        boolean down = false;
        boolean right = false;
        
        // Move Down
        if (r + 1 < m) {
            down = dfs(grid, r + 1, c, balance);
        }
        
        // Move Right
        if (c + 1 < n) {
            right = dfs(grid, r, c + 1, balance);
        }
        
        return memo[r][c][balance] = down || right;
    }
}