/**
 * @author Xuhang Shi
 * @date 10/3/2023 10:36 上午
 */
import java.util.Random;

/**
 * Quicksort, with insertion sort for small segments
 */

public class ConQuick extends Thread{

    private static int ARRAY_SIZE = 100000;

    private int[] a;
    private int lo, hi;

    @Override
    public void run() {
        quicksort();
    }

    public ConQuick(int[] a, int lo, int hi) {

        this.a = a;
        this.lo = lo;
        this.hi = hi;
    }

    private synchronized void  quicksort() {

        // quicksort until the array is largely sorted
        if (lo + 60 < hi) {
            int pivot = partition(lo, hi);

            // sort the lower side of the array
            new ConQuick(a, lo, pivot - 1).start();

            // sort the higher side of the array
            new ConQuick(a, pivot + 1, hi).start();
        }
        // finish with a quick insertion sort
        insertionSort(lo, hi);
    }

    private int partition(int lo, int hi) {

        // use median-of-three partitioning
        int mid = (int) (lo + hi) / 2;
        int indexOfLargest = hi;
        int t, i, j, median;

        if (a[mid] > a[indexOfLargest]) {
            indexOfLargest = mid;
        }
        if (a[lo] > a[indexOfLargest]) {
            indexOfLargest = lo;
        }
        if (indexOfLargest != hi) {
            t = a[hi];
            a[hi] = a[indexOfLargest];
            a[indexOfLargest] = t;
        }

        // bring the median of a[lo], a[mid], a[hi] into the lo position
        if (a[lo] < a[mid]) {
            t = a[mid];
            a[mid] = a[lo];
            a[lo] = t;
        }

        // start partitioning
        median = a[lo];
        i = lo;
        j = hi + 1;
        do {
            do {
                i++;
            } while (a[i] < median);
            do {
                j--;
            } while (a[j] > median);
            t = a[i];
            a[i] = a[j];
            a[j] = t;
        } while (i < j);

        // bring the median element to the pivot position j
        t = a[i];
        a[i] = a[j];
        a[j] = a[lo];
        a[lo] = t;

        return j;
    }

    private void insertionSort(int lo, int hi) {

        int i, j, v;

        for (i = 1; i <= hi; i++) {
            v = a[i];
            j = i - 1;
            while (j >= 0 && v < a[j]) {
                a[j + 1] = a[j];
                j--;
            }
            a[j + 1] = v;
        }
    }

    public static void main(String[] args) {
        //create an array
        int[] a = new int[ARRAY_SIZE];
        Random rand = new Random();

        // populate the array with random integers
        for (int i = 0; i < a.length; i++) {
            a[i] = rand.nextInt();
        }

        long  startTime = System.currentTimeMillis();    //获取开始时间

        //sort the array
        new ConQuick(a, 0, a.length - 1).quicksort();

        long endTime = System.currentTimeMillis();    //获取结束时间

        System.out.println("程序运行时间：" + (endTime - startTime) + "ms");    //输出程序运行时间

//        System.out.println(java.util.Arrays.toString(a));
    }
}
