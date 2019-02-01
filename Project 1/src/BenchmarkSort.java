import java.util.Random;

/**
 * Filename:    BenchmarkSorts
 * Author:      William Crutchfield
 * Date:        9/14/2018
 * Description: Gathers the benchmarks from the various InsertionSort operations
 */
class BenchmarkSorts {

    private InsertionSort insertionSort = new InsertionSort();
    private Random random = new Random();

    private int[] dataSize;
    private int numOfDataSets = 50;

    private double[] iterativeCountData = new double[numOfDataSets];
    private double[] iterativeTimeData = new double[numOfDataSets];
    private double[] recursiveCountData = new double[numOfDataSets];
    private double[] recursiveTimeData = new double[numOfDataSets];

    private double[] averageIterativeCount;
    private double[] coefficientIterativeCount;
    private double[] averageIterativeTime;
    private double[] coefficientIterativeTime;

    private double[] averageRecursiveCount;
    private double[] coefficientRecursiveCount;
    private double[] averageRecursiveTime;
    private double[] coefficientRecursiveTime;

    /**
     * Gets Counts and Times for All InsertionSorts
     * @param sizes array of list sizes to be sorted
     */
    BenchmarkSorts(int[] sizes) {
        dataSize = sizes;
        averageIterativeCount = new double[sizes.length];
        averageRecursiveCount = new double[sizes.length];
        coefficientIterativeCount = new double[sizes.length];
        coefficientRecursiveCount = new double[sizes.length];
        averageIterativeTime = new double[sizes.length];
        averageRecursiveTime = new double[sizes.length];
        coefficientIterativeTime = new double[sizes.length];
        coefficientRecursiveTime = new double[sizes.length];
    }

    /**
     * Creates all data to be sorted
     * @throws UnsortedException is thrown if return list in not sorted
     */
    void runSorts() throws UnsortedException {

        for (int i = 0; i < dataSize.length; i++) {
            int[] iterativeData = new int[dataSize[i]];
            int[] recursiveData = new int[dataSize[i]];
            for (int setNum = 0; setNum < numOfDataSets; setNum++) {
                for (int j = 0; j < dataSize[i]; j++) {
                    int r = random.nextInt(dataSize[i] + 1);
                    iterativeData[j] = r;
                    recursiveData[j] = r;
                }

                insertionSort.iterativeSort(iterativeData);
                iterativeCountData[setNum] = insertionSort.getCount();
                iterativeTimeData[setNum] = insertionSort.getTime();

                insertionSort.recursiveSort(recursiveData);
                recursiveCountData[setNum] = insertionSort.getCount();
                recursiveTimeData[setNum] = insertionSort.getTime();
            }

            averageIterativeCount[i] = getMean(iterativeCountData);
            coefficientIterativeCount[i] = getCoefficientOfVariance(iterativeCountData);
            averageIterativeTime[i] = getMean(iterativeTimeData);
            coefficientIterativeTime[i] = getCoefficientOfVariance(iterativeTimeData);

            averageRecursiveCount[i] = getMean(recursiveCountData);
            coefficientRecursiveCount[i] = getCoefficientOfVariance(recursiveCountData);
            averageRecursiveTime[i] = getMean(recursiveTimeData);
            coefficientRecursiveTime[i] = getCoefficientOfVariance(recursiveTimeData);
        }
    }

    /**
     * Gets Mean of the data
     * @param data list that is sorted
     * @return Mean of data
     */
    private double getMean(double[] data) {
        double sum = 0;
        for (double aData : data) {
            sum += aData;
        }
        return sum / data.length;
    }

    /**
     * Gets StandardDeviation of the data
     * @param data list that is sorted
     * @return StandardDeviation of the data
     */
    private double getStandardDeviation(double[] data) {
        double sum = 0;
        for (double aData : data) {
            sum += (aData - getMean(data)) * (aData - getMean(data));
        }
        return Math.sqrt(sum / (data.length - 1));
    }

    /**
     * Gets CoefficientOfVariance of data
     * @param data list that is sorted
     * @return CoefficientOfVariance of the data
     */
    private double getCoefficientOfVariance(double[] data) {
        return ((getStandardDeviation(data)) / getMean(data)) * 100;
    }

    /**
     * Displays the benchmark data in the console
     */
    void displayReport() {
        System.out.format("%213s", "-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.format("%13s %100s %100s", "\t  |", "\t  Iterative", "\t  Recursive");
        System.out.println();
        System.out.format("%13s %200s", "\t  |", "---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println();
        System.out.format("%13s %25s %25s %25s %25s %25s %25s %25s %25s", "\tData Size |", "Average Critical", "Coefficient of", "Average", "Coefficient of", "Average Critical", "Coefficient of", "Average", "Coefficient of");
        System.out.println();
        System.out.format("%13s %25s %25s %25s %25s %25s %25s %25s %25s", "\t  |", "Operation Count", "Variance of Count", "Execution Time", "Variance of Time", "Operation Count", "Variance of Count", "Execution Time", "Variance of Time");
        System.out.println();
        System.out.println("-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < dataSize.length; i++) {
            System.out.format("%14s %25s %25s %25s %25s %25s %25s %25s %26s",dataSize[i], averageIterativeCount[i], coefficientIterativeCount[i], averageIterativeTime[i], coefficientIterativeTime[i], averageRecursiveCount[i], coefficientRecursiveCount[i], averageRecursiveTime[i], coefficientRecursiveTime[i]);
            System.out.println();
        }
    }
}
