package questions.grid;

public class CountIslands {

    public static class IslandsCounter {

        int[][] matrix;
        int size;

        public IslandsCounter(int[][] matrix) {
            this.matrix = matrix;
        }

        public void iteratorMatrix() {
            int outer = 0, inner = 0;
            for (; outer < matrix.length; outer++) {
                for (; inner < matrix[outer].length; inner++) {
                    if (matrix[outer][inner] == 1) {
                        infect(outer, inner);
                        size++;
                    }
                }
            }
        }

        private void infect(int outer, int inner) {
            if (matrix[outer][inner] != 1 ||
                    outer >= matrix.length ||
                    inner >= matrix[outer].length ||
                    outer < 0 ||
                    inner < 0) return;
            matrix[outer][inner] = 2;
            infect(outer + 1, inner);
            infect(outer - 1, inner);
            infect(outer, inner + 1);
            infect(outer, inner - 1);
        }
    }
}
