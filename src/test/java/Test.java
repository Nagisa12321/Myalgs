//class Solution {
//    public List<List<Integer>> subsets(int[] nums) {
//        List<List<Integer>> list = new LinkedList<>();
//        if (nums.length == 0) return list;
//        else {
//            int[] help = new int[nums.length];
//            while (true) {
//                List<Integer> tmp = new LinkedList<>();
//                for (int i = 0; i < help.length; i++)
//                    if (help[i] == 1) tmp.add(nums[i]);
//                list.add(tmp);
//                if (inc(help) == 1) break;
//
//            }
//        }
//        return list;
//    }
//
//    public int inc(int[] nums) {
//        int CF = 0;
//        int i = nums.length - 2;
//        nums[nums.length - 1] += 1;
//        if (nums[nums.length - 1] >= 2) CF = 1;
//        nums[nums.length - 1] = nums[nums.length - 1] % 2;
//        while (i >= 0) {
//            nums[i] = nums[i] + CF;
//            if (nums[i] >= 2) CF = 1;
//            else CF = 0;
//            nums[i] = nums[i--] % 2;
//        }
//        return CF;
//    }
//}