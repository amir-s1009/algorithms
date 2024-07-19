public class QuickSort {
    private static int partition(int[] A, int p, int r){
        int i = p-1;
        int temp = 0;
        for(int j = p; j < r; j++)
            if(A[j] < A[r]){
                i++;
                temp = A[i];
                A[i] = A[j];
                A[j] = temp;
            }
        temp = A[i+1];
        A[i+1] = A[r];
        A[r] = temp;
        return i+1;
    }
    public static void quickSort(int[] A, int p, int r){
        if(p < r){
            int q = partition(A, p, r);
            quickSort(A, p, q-1);
            quickSort(A, q+1, r);
        }
    }
}
