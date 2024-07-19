import java.util.Arrays;

public class HashTable {
    HashTable(int size){
        this.size = size;
        data = new int[size];
    }
    private final int size;
    private final int[] data;
    //double hashing...
    private int hashValue(int key, int probe){
        return ((key%size)+((key%(size-2))*probe))%size;
    }

    public void insert(int key){
        int i;
        for(i = 0 ; i < size; i++){
            if(data[hashValue(key, i)] == 0){
                data[hashValue(key, i)] = key;
                break;
            }
        }
        if(i == size)
            System.out.println("hash overflow error occurred inserting "+key);
    }

    public int search(int key){
        int i;
        for(i = 0 ; i < size; i++)
            if(data[hashValue(key, i)] == key)
                break;
        if(i == size)
            return -1;
        else
            return hashValue(key, i);
    }

    public void delete(int key){
        data[search(key)] = 0;
    }
    public void deleteAll(){
        for(int i = 0; i < size; i++)
            data[i] = 0;
    }

    public double freeSpace(){
        int free = 0;
        for(int data : data)
            if(data == 0)
                free++;
        return ((double)free/size)*100;
    }
    public void print(){
        System.out.println(Arrays.toString(data));
    }

    public void info(){
        System.out.print("> hash table is: ");
        print();
        System.out.println("> free space is: "+freeSpace()+" %");
        System.out.println("> total size is: "+size);
    }
}
