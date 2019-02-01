/**
 * Filename:    BenchmarkSorts
 * Author:      William Crutchfield
 * Date:        9/14/2018
 * Description: Iterative and Recursive Implementations of the InsertionSort algorithm.
 */
public class InsertionSort implements SortInterface {

    private int count;
    private long time;

    /**
     * Recursive Insertion Sort
     * @param list to be sorted
     * @throws UnsortedException is thrown if return list in not sorted
     */
    @Override
    public void recursiveSort(int[] list) throws UnsortedException {
        count = 0;
        time = 0;

        long startTime = System.nanoTime();

        list = recursive(list, list.length-1);

        long endTime = System.nanoTime();
        time = (endTime - startTime);

        if (isNotSorted(list)) {
            throw new UnsortedException();
        }
    }

    /**
     * Recursive Insertion Sort Helper Method
     * @param list to be sorted
     * @param n list length
     * @return sorted list
     */
    private int[] recursive(int[] list, int n) {
        int j;

        if (n > 0) {
            recursive(list, n-1);
            int x = list[n];
            for (j = n-1; j>= 0 && list[j] > x; j--) {
                list[j+1] = list[j];
                count++;
            }
            list[j+1] = x;
        }

        return list;
    }

    /**
     * Iterative Insertion Sort
     * @param list to be sorted
     * @throws UnsortedException is thrown if return list in not sorted
     */
    @Override
    public void iterativeSort(int[] list) throws UnsortedException {
        count = 0;
        time = 0;

        int i;
        int j;

        long startTime = System.nanoTime();

        for (i = 1; i < list.length; i++) {
            int x = list[i];
            for (j = i-1; j >= 0 && list[j] > x; j--) {
                list[j+1] = list[j];
                count++;
            }
            list[j+1] = x;
        }

        long endTime = System.nanoTime();
        time = (endTime - startTime);

        if (isNotSorted(list)) {
            throw new UnsortedException();
        }
    }

    /**
     * Checks if the list is sorted
     * @param list that should be sorted
     * @return boolean true if sorted
     */
    private boolean isNotSorted (int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] <= list[i+1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Get sortCount
     * @return sortCount
     */
    @Override
    public int getCount() {
        return count;
    }

    /**
     * Get sortTime
     * @return sortTime
     */
    @Override
    public long getTime() {
        return time;
    }
}
