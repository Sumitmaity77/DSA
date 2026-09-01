from collections import deque

class Solution:
    def minMoves(self, classroom: List[str], energy: int) -> int:
        m, n = len(classroom), len(classroom[0])
        
        # Locate S and all L's
        litter_coords = []
        start = None
        for r in range(m):
            for c in range(n):
                if classroom[r][c] == 'S':
                    start = (r, c)
                elif classroom[r][c] == 'L':
                    litter_coords.append((r, c))
                    
        num_litters = len(litter_coords)
        litter_map = {coords: i for i, coords in enumerate(litter_coords)}
        
        # Queue stores: (row, col, current_energy, mask, moves)
        queue = deque([(start[0], start[1], energy, 0, 0)])
        
        # Visited set: (r, c, energy, mask)
        visited = set([(start[0], start[1], energy, 0)])
        
        target_mask = (1 << num_litters) - 1
        
        directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
        
        while queue:
            r, c, curr_energy, mask, moves = queue.popleft()
            
            if mask == target_mask:
                return moves
                
            for dr, dc in directions:
                nr, nc = r + dr, c + dc
                
                if 0 <= nr < m and 0 <= nc < n and classroom[nr][nc] != 'X':
                    next_energy = curr_energy - 1
                    if next_energy < 0:
                        continue
                        
                    next_mask = mask
                    
                    # Check cell content
                    cell = classroom[nr][nc]
                    if cell == 'R':
                        next_energy = energy
                    elif cell == 'L':
                        litter_idx = litter_map[(nr, nc)]
                        next_mask |= (1 << litter_idx)
                        
                    next_state = (nr, nc, next_energy, next_mask)
                    if next_state not in visited:
                        visited.add(next_state)
                        queue.append((nr, nc, next_energy, next_mask, moves + 1))
                        
        return -1