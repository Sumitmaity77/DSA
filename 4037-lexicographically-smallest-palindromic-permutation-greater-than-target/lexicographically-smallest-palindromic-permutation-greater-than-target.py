class Solution:
    def lexPalindromicPermutation(self, s: str, target: str) -> str:
        n = len(s)
        freq = [0] * 26
        for c in s:
            freq[ord(c) - ord('a')] += 1
            
        # 1. Check palindrome feasibility
        center = ""
        odd_count = 0
        for i in range(26):
            if freq[i] % 2 != 0:
                odd_count += 1
                center = chr(ord('a') + i)
                
        if odd_count > 1:
            return ""
        
        half_freq = [f // 2 for f in freq]
        half_len = n // 2

        def build_palindrome(head: str) -> str:
            if n % 2 != 0:
                return head + center + head[::-1]
            return head + head[::-1]

        # 2. Check if the smallest possible palindrome is already strictly greater than target
        smallest_head = "".join(chr(ord('a') + i) * half_freq[i] for i in range(26))
        smallest_pal = build_palindrome(smallest_head)
        
        if smallest_pal > target:
            return smallest_pal

        def construct_smallest(prefix: list, remaining_half_freq: list) -> str:
            head = list(prefix)
            for i in range(26):
                if remaining_half_freq[i] > 0:
                    head.append(chr(ord('a') + i) * remaining_half_freq[i])
            return build_palindrome("".join(head))

        # 3. Try prefix matching from half_len down to 0
        for i in range(half_len, -1, -1):
            prefix_freq = [0] * 26
            valid_prefix = True
            for k in range(i):
                idx = ord(target[k]) - ord('a')
                prefix_freq[idx] += 1
                if prefix_freq[idx] > half_freq[idx]:
                    valid_prefix = False
                    break
            
            if not valid_prefix:
                continue

            avail = [half_freq[k] - prefix_freq[k] for k in range(26)]
            prefix = list(target[:i])

            # Special case: If we matched the entire first half (i == half_len),
            # check if the palindrome built directly from this full half works.
            if i == half_len:
                cand = construct_smallest(prefix, avail)
                if cand > target:
                    return cand
                continue

            # If i < half_len, try placing a character strictly greater than target[i]
            start_char_idx = ord(target[i]) - ord('a') + 1

            for j in range(start_char_idx, 26):
                if avail[j] > 0:
                    avail[j] -= 1
                    prefix.append(chr(ord('a') + j))
                    
                    cand = construct_smallest(prefix, avail)
                    if cand > target:
                        return cand
                    
                    # Backtrack
                    prefix.pop()
                    avail[j] += 1

        return ""