/**
 * Given a 32-bit signed integer, reverse digits of an integer.
 *
 * Note:
 * Assume we are dealing with an environment that could only
 * store integers within the 32-bit signed integer range: [−2^31, 2^31− 1].
 * For the purpose of this problem, assume that your function returns 0 when the reversed integer overflows.
 * Example 1:
 * Input: x = 123
 * Output: 321
 *
 * Example 2:
 * Input: x = -123
 * Output: -321
 *
 * Example 3:
 * Input: x = 120
 * Output: 21
 *
 * Example 4:
 * Input: x = 0
 * Output: 0
 * Constraints:
 * -2^31 <= x <= 2231 - 1
 */
class ReverseInteger {

    /**
     * 方法：弹出和推入数字 & 溢出前进行检查
     * 思路
     * 我们可以一次构建反转整数的一位数字。在这样做的时候，我们可以预先检查向原整数附加另一位数字是否会导致溢出。
     *
     * 算法
     * 反转整数的方法可以与反转字符串进行类比。
     * 我们想重复“弹出” xx 的最后一位数字，并将它“推入”到 \text{rev}rev 的后面。最后，\text{rev}rev 将与 xx 相反。
     * 要在没有辅助堆栈 / 数组的帮助下 “弹出” 和 “推入” 数字，我们可以使用数学方法。
     *
     * //pop operation:
     * pop = x % 10;
     * x /= 10;
     *
     * //push operation:
     * temp = rev * 10 + pop;
     * rev = temp;
     *
     * 但是，这种方法很危险，因为当 temp = rev * 10 + pop 时会导致溢出。
     * 幸运的是，事先检查这个语句是否会导致溢出很容易。
     * 为了便于解释，我们假设 rev 是正数。
     * 如果 temp=rev⋅10+pop 导致溢出，那么一定有 rev >= INTMAX / 10
     * 如果 rev > INTMAX / 10, 那么 temp=rev⋅10+pop 一定会溢出。
     * 如果 rev == INTMAX / 10, 那么只要 pop>7，temp=rev⋅10+pop 就会溢出。
     * 当 rev 为负时可以应用类似的逻辑。
     *
     * 复杂度分析
     * 时间复杂度：O(log(x))，x中大约有 log10(x) 位数字。
     * 空间复杂度：O(1)。
     */
    fun reverse(x: Int): Int {
        var origin = x
        var rev = 0
        var pop = 0
        while (origin != 0) {
            pop = origin % 10
            origin /= 10
            if (rev > Int.MAX_VALUE / 10 ||
                    (rev == Int.MAX_VALUE / 10 && pop > 7)) {
                return 0
            }
            if (rev < Int.MIN_VALUE / 10 ||
                    (rev == Int.MIN_VALUE / 10 && pop < -8)) {
                return 0
            }
            rev = rev * 10 + pop
        }
        return rev
    }
}