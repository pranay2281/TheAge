public class ArrayList<T> implements List<T> {

    T[] arr;
    int size;

    public ArrayList() {
        size = 0;
        arr = (T[]) new Object[10];

    }

    private void growArray() {
        T[] newArr = (T[]) new Object[arr.length * 2];
        for (int i = 0; i < arr.length; i++) {
            newArr[i] = arr[i];
        }
        arr = newArr;
    }

    @Override
    public void add(T item) throws Exception {
        if (size == arr.length) {
            growArray();
        }
        arr[size] = item;
        size++;
    }

    @Override
    public T get(int pos) throws Exception {
        if (pos < 0 || pos > size - 1) {
            throw new Exception("List index out of Bounds");
        }
        return arr[pos];
    }

    @Override
    public int size() { //best/worst == constant
        return size;
    }
}
