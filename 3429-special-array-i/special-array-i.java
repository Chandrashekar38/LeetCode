class Solution {
    public boolean isArraySpecial(int[] nums) {
        // If array has only one element, it's special by definition
        if (nums.length == 1) {
            return true;
        }
        
        // Check each adjacent pair
        for (int i = 0; i < nums.length - 1; i++) {
            // If adjacent numbers have same parity, array is not special
            if (isEven(nums[i]) == isEven(nums[i + 1])) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isEven(int num) {
        return num % 2 == 0;
    }
}