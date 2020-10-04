/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum　as a linked list.
 *
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * Example 1:
 * 2 -> 4 -> 3
 * 5 -> 6 -> 4
 * -------------
 * 7 -> 0 -> 8
 * Input: l1 = [2,4,3], l2 = [5,6,4]
 * Output: [7,0,8]
 * Explanation: 342 + 465 = 807.
 *
 * Example 2:
 * Input: l1 = [0], l2 = [0]
 * Output: [0]
 *
 * Example 3:
 * Input: l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * Output: [8,9,9,9,0,0,0,1]
 *
 * Constraints:
 * The number of nodes in each linked list is in the range [1, 100].
 * 0 <= Node.val <= 9
 * It is guaranteed that the list represents a number that does not have leading zeros.
 */
class AddTwoNumbers {

    /**
     * 方法一：模拟
     * 思路与算法
     * 由于输入的两个链表都是逆序存储数字的位数的，因此两个链表中同一位置的数字可以直接相加。
     * 我们同时遍历两个链表，逐位计算它们的和，并与当前位置的进位值相加。
     * 具体而言，如果当前两个链表处相应位置的数字为 n1,n2，进位值为 carry，
     * 则它们的和为 n1+n2+carry；
     * 其中，答案链表处相应位置的数字为 (n1+n2+carry)%10，而新的进位值为 (n1+n2+carry) / 10
     *
     * 如果两个链表的长度不同，则可以认为长度短的链表的后面有若干个 0 。
     * 此外，如果链表遍历结束后，有 carry>0，还需要在答案链表的后面附加一个节点，节点的值为 carry。
     */
    fun addTwoNumbers(l1: ListNode?, l2: ListNode?): ListNode? {
        val dummyHead: ListNode? = ListNode(0)
        var p = l1
        var q  = l2
        var current = dummyHead
        var  carry = 0

        while (p != null || q != null) {
            val x = p?.`val` ?: 0
            val y = q?.`val` ?: 0

            val sum = x + y + carry
            carry = sum / 10
            current?.next = ListNode(sum % 10)
            current = current?.next

            p = p?.next
            q = q?.next
        }

        if (carry > 0) {
            current?.next = ListNode(carry)
        }

        return  dummyHead?.next

    }

    class ListNode(var `val`: Int) {
        var next: ListNode? = null
    }
}