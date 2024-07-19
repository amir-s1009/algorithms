public class InsertionSort {
    public static int[] increasingSort(int[] numbers){
        for(int i = 1 ; i < numbers.length; i++){
            int temp = numbers[i];
            int j = i - 1;
            while (j >= 0 && numbers[j] > temp){
                numbers[j+1] = numbers[j];
                j -= 1;
            }
            numbers[j+1] = temp;
        }
        return numbers;
    }
    public static int[] decreasingSort(int[] numbers){
        for(int i = 1 ; i < numbers.length; i++){
            int temp = numbers[i];
            int j = i - 1;
            while (j >= 0 && numbers[j] < temp){
                numbers[j+1] = numbers[j];
                j -= 1;
            }
            numbers[j+1] = temp;
        }
        return numbers;
    }
}
