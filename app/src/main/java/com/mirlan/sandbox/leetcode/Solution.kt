package com.mirlan.sandbox.leetcode

import java.util.*
import kotlin.math.abs

class Solution {
    fun singleNumber(nums: IntArray): Int {
        var list = IntArray(nums.size)
        var secondList = IntArray(nums.size)
        nums.forEach {
            if (it < 0)
                secondList[Math.abs(it)]++
            else list[it] = list[it] + 1
        }
        "asdf".toLowerCase()
        val f = list.indexOf(1)
        val g = secondList.indexOf(1)
        return if (f == -1) g - g * 2 else f
    }
}

fun main() {
    val sdf: TreeMap<String, String>

    val k = Solution().singleNumber(intArrayOf(1, 2, 1, 2, -3))
    print(k)
}