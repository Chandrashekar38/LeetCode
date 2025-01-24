class Solution {
    public int[] twoSum(int[] nums, int target) {

        Map <Integer, Integer> numsToIndex = new HashMap<>();
        for(int i=0;i<nums.length;i++){
            int complement = target-nums[i];
            if(numsToIndex.containsKey(complement)){
                return new int[] {numsToIndex.get(complement),i};
            }
            numsToIndex.put(nums[i],i);
        }
        throw new IllegalArgumentException("No solution found");
    
    }
}