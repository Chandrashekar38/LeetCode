class Solution {
    public int lengthOfLastWord(String s) {
        // Trim any trailing spaces
        s = s.trim();
        
        // Find the last space in the string
        int lastSpaceIndex = s.lastIndexOf(' ');
        
        // Extract the last word and return its length
        return s.length() - lastSpaceIndex - 1;
    }
}
