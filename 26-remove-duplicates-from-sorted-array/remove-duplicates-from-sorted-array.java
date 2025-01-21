class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        
        int uniqueIndex = 0; // Pointer for the position of unique elements
        
        // Iterate through the array
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] != nums[uniqueIndex]) { // If a new unique element is found
                uniqueIndex++; // Move the pointer for unique elements
                nums[uniqueIndex] = nums[i]; // Update the next position with the unique element
            }
        }
        
        // The number of unique elements is uniqueIndex + 1
        return uniqueIndex + 1;
    }
}
