public class Heap {
    private static int parent(int i){
        // needs to be fixed here...
        return (i-1)/2;
    }
    private static int right(int i){
        return 2*i +2;
    }
    private static int left(int i){
        return 2*i +1;
    }
    private static void maxHeapify(int[] A, int i, int heapSize){
        int r = right(i);
        int l = left(i);
        int largest = 0;

        if(l < heapSize && A[l] > A[i])
            largest = l;
        else
            largest = i;
        if(r < heapSize && A[r] > A[largest])
            largest = r;
        if (largest != i){
            //swap...
            int temp = A[i];
            A[i] = A[largest];
            A[largest] = temp;

            maxHeapify(A, largest, heapSize);
        }
    }
    public static void buildMaxHeap(int[] A){
        for (int j = A.length/2 -1; j >= 0;  j--)
            maxHeapify(A, j, A.length);
    }
    public static void heapSort(int[] A){
        int heapSize = A.length;
        for(int z = A.length-1; z > 0; z--){
            int temp = A[0];
            A[0] = A[z];
            A[z] = temp;
            heapSize--;
            maxHeapify(A, 0, heapSize);
        }
    }
}
