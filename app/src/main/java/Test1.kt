/*
package com.mirlan.sandbox.leetcode


class Test1 {
    fun print(list: List<Int>): Int {
        if (list.size == 1) return 1
        val middleValue = list.size / 2
        val minValue = list.min()
        var c = 0
        list.forEach { if (minValue == it) c++ }
        if (list.isEmpty() || list.size % 2 == 0 || list[middleValue] != minValue) return 0
        return if (compare(list, minValue) && c == 1) 1 else 0
    }

    fun compare(list: List<Int>, minValue: Int): Boolean {
        list.forEach { if (it < minValue) return false }
        return true
    }
}

fun main() {
    val list = listOf(1, 2, 3, 4, 5)
    val list2 = listOf(3, 2, 1, 4, 5)
    val list3 = listOf(3, 2, 1, 4, 1)
    val list4 = listOf(1, 2, 3, 4)
    val list5 = listOf(10)
    val test1 = Test1()
    val result = test1.print(listOf())
    print(result)
}
Answers
First answer

static int a1(int[] a)
{
    if (a == null || a.length % 2 == 0) return 0;
    int midIndex = a.length / 2 ;
    int middleItem = a[midIndex];
    for (int i=0; i<a.length; i++)
    {
        if (i != midIndex && middleItem >= a[i])
            return 0;
    }
    return 1;
}
Second answer

static int a2(int[] a)
{
    int sumEven = 0;
    int sumOdd = 0;

    for (int i=0; i<a.length; i++)
    {
        if (a[i]%2 == 0)
            sumEven += a[i];
        else
            sumOdd += a[i];
    }

    return sumOdd - sumEven;
}
Third answer

static char[] a3(char[] a, int start, int length)
{
    if (length < 0 || start < 0 || start+length-1>=a.length)
    {
        return null;
    }

    char[] sub = new char[length];
    for (int i=start, j=0; j<length; i++, j++)
    {
        sub[j] = a[i];
    }

    return sub;
}
Fourth answer
static int a4(int n)
{
    int sign = 1;
    if (n == 0) return 0;
    if (n < 0)
    {
        sign = -1;
        n = -n;
    }
    int reverse = 0;
    while (n != 0)
    {
        reverse = (reverse * 10) + (n % 10);
        n /= 10;
    }
    return sign * reverse;
}
Fifth answer
static int[] a5(int[] first, int[] second)
{
    if (first == null || second == null) return null;
    if (first.length == 0 || second.length == 0) return new int[0];
    int min =
    (first.length < second.length) ? first.length : second.length;
    int[] a, b;
    if (min == first.length)
    {
        a = first;
        b = second;
    }
    else
    {
        a = second;
        b = first;
    }
    int[] c = new int[min];
    int k = 0;
    for (int i = 0; i < a.length; i++)
    for (int j = 0; j < b.length; j++)
    if (a[i] == b[j])
    {
        c[k] = a[i];
        k++;
    }
    int[] retArray = new int[k];
    for (int t = 0; t < retArray.length; t++)
    retArray[t] = c[t];
    return retArray;
}
Sixth answer
static int a6(int[] a)
{
    if (a.length < 3) return -1;
    int i = 0;
    int j = a.length - 1;
    int idx = 1;
    int leftSum = a[i];
    int rightSum = a[j];
    for (int k = 1; k < a.length - 2; k++)
    {
        if (leftSum < rightSum)
        {
            i++;
            leftSum += a[i];
            idx = i + 1;
        }
        else
        {
            j--;
            rightSum += a[j];
            idx = j - 1;
        }
    }
    if (leftSum == rightSum)
        return idx;
    else
        return -1;
}*/
