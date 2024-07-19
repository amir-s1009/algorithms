public class MergeSort {
    private static int[] slice(int[] array, int start, int end){
        int[] sliced = new int[end-start+1];
        int index = start;
        for(int i = 0 ; i < end-start+1 ; i++)
            sliced[i] = array[index++];
        return sliced;
    }
    private static void merge(int[] numbers, int start, int mid, int end){
        int i1 = 0;
        int i2 = 0;
        int i3 = start;
        int[] list1 = slice(numbers, start, mid);
        int[] list2 = slice(numbers, mid+1, end);

        while (i1 < list1.length && i2 < list2.length){
            if(list1[i1] <= list2[i2])
                numbers[i3] = list1[i1++];
            else if(list2[i2] <= list1[i1])
                numbers[i3] = list2[i2++];
            i3++;
        }
        while(i1 < list1.length){
            numbers[i3] = list1[i1++];
            i3++;
        }
        while(i2 < list2.length){
            numbers[i3] = list2[i2++];
            i3++;
        }
    }
    public static void mergeSort(int[] numbers, int p, int r){
        if(p < r){
            int q = (r+p)/2;
            mergeSort(numbers, p, q);
            mergeSort(numbers, q+1, r);
            merge(numbers, p, q, r);
        }
        else return;
    }
}
