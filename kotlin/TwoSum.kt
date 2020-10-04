/**
 * Given an array of integers nums and an integer target,
 * return indices of the two numbers such that they add up to target.
 *
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 *
 * Example 1:
 * Input: nums = [2,7,11,15], target = 9
 * Output: [0,1]
 * Output: Because nums[0] + nums[1] == 9, we return [0, 1].
 *
 * Example 2:
 * Input: nums = [3,2,4], target = 6
 * Output: [1,2]
 *
 * Example 3:
 * Input: nums = [3,3], target = 6
 * Output: [0,1]
 *
 * Constraints:
 * 2 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * -10^9 <= target <= 10^9
 * Only one valid answer exists.
 */
class TwoSum {

    /**
     * 方法一：暴力枚举
     * 思路及算法
     * 最容易想到的方法是枚举数组中的每一个数 x，寻找数组中是否存在 target - x。
     * 当我们使用遍历整个数组的方式寻找 target - x 时，需要注意到每一个位于 x 之前的元素都已经和 x 匹配过，因此不需要再进行匹配。
     * 而每一个元素不能被使用两次，所以我们只需要在 x 后面的元素中寻找 target - x。
     *
     * 复杂度分析
     * 时间复杂度：O(N^2)，其中 NN 是数组中的元素数量。最坏情况下数组中任意两个数都要被匹配一次。
     * 空间复杂度：O(1)。
     */
    fun twoSumSolutionOne (nums: IntArray, target: Int): IntArray {
        for (i in nums.indices) {
            for (j in i + 1 until nums.size) {
                if (nums[i] + nums[j] == target) {
                    return intArrayOf(i, j)
                }
            }
        }

        return intArrayOf(0)
    }

    /**
     * 方法二：哈希表
     * 思路及算法
     * 注意到方法一的时间复杂度较高的原因是寻找 target - x 的时间复杂度过高。因此我们需要一种更优秀的方法，
     * 能够快速寻找数组中是否存在目标元素。如果存在，我们需要找出它的索引。
     * 使用哈希表，可以将寻找 target - x 的时间复杂度降低到从 O(N) 降低到 O(1)。
     * 这样我们创建一个哈希表，对于每一个 x，我们首先查询哈希表中是否存在 target - x，
     * 然后将 x 插入到哈希表中，即可保证不会让 x 和自己匹配。
     *
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 是数组中的元素数量。对于每一个元素 x，我们可以 O(1) 地寻找 target - x。
     * 空间复杂度：O(N)，其中 N 是数组中的元素数量。主要为哈希表的开销。
     */
    fun twoSumSolutionTwo (nums: IntArray, target: Int): IntArray {
        val map = HashMap<Int,Int>()
        for (i in nums.indices){
            val a = target-nums[i]
            if (map.containsKey(a)){
                return intArrayOf(map.getOrDefault(a,0),i)
            }
            map.put(nums[i],i)
        }
        throw IllegalArgumentException("no result")
    }
// Most fast
//    fun twoSum(nums: IntArray, target: Int): IntArray {
//        val volume = 2048 //100000000000
//        val bitMode = volume - 1 //011111111111
//        val result = IntArray(volume)
//        for (i in nums.indices) {
//            val c = target - nums[i] and bitMode
//            if (result[c] != 0) {
//                return intArrayOf(result[c] - 1, i)
//            }
//            result[nums[i] and bitMode] = i + 1
//        }
//        throw IllegalArgumentException("No two sum solution")
//    }
// Most less memory use
//    fun twoSum(nums: IntArray, target: Int): IntArray {
//        val array = IntArray(2)
//        nums.forEachIndexed { index, num ->
//            val result = target - num
//            for (i in index + 1 until nums.size) {
//                if (nums[i] == result) {
//                    array[0] = index
//                    array[1] = i
//                    return array
//                }
//            }
//        }
//        return array
//    }
}