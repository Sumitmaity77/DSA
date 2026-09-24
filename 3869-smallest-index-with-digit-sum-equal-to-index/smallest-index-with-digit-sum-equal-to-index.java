class Solution {
    public int smallestIndex(int[] nums) {
     for(int i = 0;i<nums.length;i++){
        int currentNum = nums[i];
        int digitalSum = 0;

        while (currentNum > 0){
            digitalSum += currentNum % 10;;
            currentNum /= 10 ;
        } 
        if ( nums[i] == 0 && i ==0){
            return 0;
        }
        if ( digitalSum == i ){
            return i ;
        }
     } 
     return -1 ;  
    }
}