public class InsertionSort implements SortInterface {

    private int count;
    private long time;

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

    private boolean isNotSorted (int[] list) {
        for (int i = 0; i < list.length - 1; i++) {
            if (list[i] <= list[i+1]) {
                return false;
            }
        }
        return true;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public long getTime() {
        return time;
    }
}
