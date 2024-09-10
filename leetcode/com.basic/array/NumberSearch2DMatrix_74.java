package array;

/**
 * @Author hongjian.li
 * @Description 74. Search a 2D Matrix
 * @Date 9/10/2024 8:32 PM
 **/

 /*   You are given an m x n integer matrix matrix with the following two properties:

    Each row is sorted in non-decreasing order.
    The first integer of each row is greater than the last integer of the previous row.
    Given an integer target, return true if target is in matrix or false otherwise.

    You must write a solution in O(log(m * n)) time complexity.



    Example 1:


    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
    Output: true
    Example 2:


    Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
    Output: false


    Constraints:

    m == matrix.length
    n == matrix[i].length
    1 <= m, n <= 100
    -104 <= matrix[i][j], target <= 104*/

public class NumberSearch2DMatrix_74 {

    public boolean searchMatrix(int[][] matrix, int target) {

        //获取矩阵的行数和列数
        int m = matrix.length;
        int n = matrix[0].length;

        //定义二分查找的左指针和右指针
        int left = 0;
        int right = m * n - 1;

        while (left <= right) {
            //计算中间位置
            int mid = left + (right - left) / 2;
            //将一维数组转为行和列
            // 关键点在这里
            int row = mid / n;
            int col = mid % n;

            int midValue = matrix[row][col];

            //二分的比较逻辑
            if (midValue < target) {
                left = mid + 1;
            } else if (midValue > target) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
