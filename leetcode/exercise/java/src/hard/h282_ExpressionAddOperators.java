package hard;

import java.util.*;

/**
 * 282. Expression Add Operators
 * <p>
 * Total Accepted: 22831
 * Total Submissions: 80470
 * Difficulty: Hard
 * Contributors: Admin
 * <p>
 * Given a string that contains only digits 0-9 and a target value, return all possibilities to add binary operators
 * (not unary) +, -, or * between the digits so they evaluate to the target value.
 * <p>
 * Examples:
 * <p>
 * "123", 6 -> ["1+2+3", "1*2*3"]
 * "232", 8 -> ["2*3+2", "2+3*2"]
 * "105", 5 -> ["1*0+5","10-5"]
 * "00", 0 -> ["0+0", "0-0", "0*0"]
 * "3456237490", 9191 -> []
 * <p>
 * <p>
 * Leetcode: https://leetcode.com/problems/expression-add-operators/
 * <p>
 * Created by xinwu on 1/2/17.
 */

public class h282_ExpressionAddOperators {
    static final String ADD = "+".intern();
    static final String Minus = "-".intern();
    static final String Multiply = "*".intern();

    class Answer {
        HashMap<Long, List<String>> fullSolutionMap = new HashMap<>();
        HashMap<Long, List<String>> multiplyOnlySolutionMap = new HashMap<>();
        HashSet<String> allSolutions = new HashSet<>();

        public void addSolution(long value, String solution, boolean multiplyOnly) {
            if (!allSolutions.contains(solution)) {
                Long key = Long.valueOf(value); 
                if (!fullSolutionMap.containsKey(key))
                    fullSolutionMap.put(key, new ArrayList<>());
                fullSolutionMap.get(key).add(solution);
                if (multiplyOnly) {
                    if (!multiplyOnlySolutionMap.containsKey(key))
                        multiplyOnlySolutionMap.put(key, new ArrayList<>());
                    multiplyOnlySolutionMap.get(key).add(solution);
                }

                allSolutions.add(solution);
            }
        }
    }

    public List<String> addOperators(String num, int target) {
        List<String> result = new ArrayList<>();

        int length = num.length();
        Answer[][] matrix = new Answer[length][length];

        fillAnswerMatrix(num, matrix, 0, length - 1);

        Long targetInt = Long.valueOf(target);

        if (matrix[0][length - 1].fullSolutionMap.containsKey(targetInt)) {
            for (String solution : matrix[0][length - 1].fullSolutionMap.get(targetInt)) {
                result.add(solution);
            }
        }

        return result;
    }

    private void fillAnswerMatrix(String num, Answer[][] matrix, int start, int end) {
        if (null != matrix[start][end])
            return;

        matrix[start][end] = new Answer();
        // no ops
        if (num.charAt(start) != '0' || start == end) {
            String subValue = num.substring(start, end + 1);
            matrix[start][end].addSolution(Long.parseLong(subValue), subValue, true);
        }

        for (int k = start; k < end; ++k) {
            if (null == matrix[start][k]) fillAnswerMatrix(num, matrix, start, k);
            if (null == matrix[k + 1][end]) fillAnswerMatrix(num, matrix, k + 1, end);

            // fill multiply only solutions
            for (HashMap.Entry<Long, List<String>> left : matrix[start][k].multiplyOnlySolutionMap.entrySet())
                for (HashMap.Entry<Long, List<String>> right : matrix[k + 1][end].multiplyOnlySolutionMap.entrySet())
                    for (String leftSolution : left.getValue())
                        for (String rightSolution : right.getValue()) {
                            matrix[start][end].addSolution(
                                    left.getKey().intValue() * right.getKey().intValue(),
                                    String.join(Multiply, leftSolution, rightSolution),
                                    true);
                        }


            // fill add and minus
            for (HashMap.Entry<Long, List<String>> left : matrix[start][k].fullSolutionMap.entrySet())
                for (HashMap.Entry<Long, List<String>> right : matrix[k + 1][end].fullSolutionMap.entrySet())
                    for (String leftSolution : left.getValue())
                        for (String rightSolution : right.getValue()) {
                            matrix[start][end].addSolution(
                                    left.getKey().intValue() + right.getKey().intValue(),
                                    String.join(ADD, leftSolution, rightSolution),
                                    false);
                            matrix[start][end].addSolution(
                                    left.getKey().intValue() - right.getKey().intValue(),
                                    String.join(Minus, leftSolution, rightSolution),
                                    false);

                        }
        }
    }

    public static void main(String[] args) {
        h282_ExpressionAddOperators solution = new h282_ExpressionAddOperators();

        System.out.println(Arrays.toString(solution.addOperators("1", 1).toArray()));
        System.out.println(Arrays.toString(solution.addOperators("1", 2).toArray()));
        System.out.println(Arrays.toString(solution.addOperators("123", 6).toArray()));
        System.out.println(Arrays.toString(solution.addOperators("232", 8).toArray()));
        System.out.println(Arrays.toString(solution.addOperators("5", 5).toArray()));
        System.out.println(Arrays.toString(solution.addOperators("00", 0).toArray()));
        System.out.println(Arrays.toString(solution.addOperators("3456237490", 9191).toArray()));
    }
}
