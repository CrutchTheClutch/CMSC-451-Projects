/**
 * Filename:    BenchmarkSorts
 * Author:      William Crutchfield
 * Date:        9/14/2018
 * Description: Interface for InsertionSort
 */
interface SortInterface {
    void recursiveSort(int[] list) throws UnsortedException;
    void iterativeSort(int[] list) throws UnsortedException;
    int getCount();
    long getTime();
}
