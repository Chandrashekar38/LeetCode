class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;
        // Create pairs of (value, original index)
        int[][] pairs = new int[n][2];
        for (int i = 0; i < n; i++) {
            pairs[i] = new int[]{nums[i], i};
        }
        
        // Sort pairs based on value
        Arrays.sort(pairs, (a, b) -> a[0] - b[0]);
        
        // Group elements that can be swapped
        List<List<Integer>> groups = new ArrayList<>();
        List<Integer> currentGroup = new ArrayList<>();
        
        for (int i = 0; i < n; i++) {
            if (currentGroup.isEmpty() || 
                pairs[i][0] - pairs[currentGroup.get(currentGroup.size() - 1)][0] <= limit) {
                currentGroup.add(i);
            } else {
                groups.add(currentGroup);
                currentGroup = new ArrayList<>();
                currentGroup.add(i);
            }
        }
        if (!currentGroup.isEmpty()) {
            groups.add(currentGroup);
        }
        
        // Rearrange within each group
        int[] result = new int[n];
        for (List<Integer> group : groups) {
            // Sort original indices within the group
            List<Integer> indices = group.stream()
                .map(i -> pairs[i][1])
                .sorted()
                .collect(Collectors.toList());
            
            // Sort values within the group
            List<Integer> values = group.stream()
                .map(i -> pairs[i][0])
                .sorted()
                .collect(Collectors.toList());
            
            // Assign sorted values to sorted original indices
            for (int i = 0; i < group.size(); i++) {
                result[indices.get(i)] = values.get(i);
            }
        }
        
        return result;
    }
}