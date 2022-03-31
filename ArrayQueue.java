
import queue.EmptyQueueException;

/**
 *
 * @author Kirsten Chesley (kchesley888)
 * @version (2020-Apr-20)
 * @param <T>
 *            the type
 */
public class ArrayQueue<T> implements queue.QueueInterface<T> {
    private T[] queue;
    private static final int DEFAULT_CAPACITY = 10;
    /**
     * the maximum capacity
     */
    public static final int MAX_CAPACITY = 100;
    private int enqueueIndex;
    private int dequeueIndex;
    private int size;

    /**
     * default constructor
     */
    public ArrayQueue() {
        this(DEFAULT_CAPACITY);
    }


    /**
     * constructor with parameter
     * 
     * @param initialCapacity
     *            initial capacity
     */
    @SuppressWarnings("unchecked")
    public ArrayQueue(int initialCapacity) {
        queue = (T[])new Object[initialCapacity + 1];
        size = 0;
        enqueueIndex = 0;
        dequeueIndex = initialCapacity;
    }


    /**
     * gets the length
     * 
     * @return returns the length
     */
    public int getLength() {
        return queue.length;
    }


    /**
     * returns the size
     * 
     * @return the size
     */
    public int getSize() {
        return size;
    }


    /**
     * returns if it is full
     * 
     * @return if full
     */
    private boolean isFull() {
        return (enqueueIndex == (dequeueIndex + 2) % queue.length);
    }
    
    /**
     * sets it to an array
     * 
     * @return array
     */
    public Object[] toArray() {
        if (isEmpty()) {
            throw new EmptyQueueException();
        }

        Object[] obj = new Object[size];
        for (int i = 0; i < size; i++) {
            int j = (enqueueIndex + i) % queue.length;
            obj[i] = queue[j];
        }
        return obj;
    }


    /**
     * sets it to a string
     * 
     * @return string
     */
    public String toString() {
        StringBuilder build = new StringBuilder();
        build.append("[");
        for (int i = 0; i < size; i++) {
            int j = (enqueueIndex + i) % queue.length;
            build.append(queue[j].toString());
            if (i < size - 1) {
                build.append(", ");
            }
        }
        build.append("]");
        return build.toString();
    }


    /**
     * checks if they are equal
     * @param obj the object
     * @return if they are equal
     */
    @SuppressWarnings("unchecked")
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass() || (this
            .getSize() != ((ArrayQueue<T>)obj).getSize())) {
            return false;
        }
        boolean equal = true;
        for (int i = 0; i < size; i++) {
            T myElement = queue[(enqueueIndex + i) % queue.length];
            ArrayQueue<T> temp = (ArrayQueue<T>)obj;
            int currIndex = (temp.enqueueIndex + i) % temp.queue.length;
            T otherElement = (T)(temp.queue[currIndex]);
            if (myElement != null && !myElement.equals(otherElement)) {
                equal = false;
            }

        }

        return equal;
    }

    

    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        while (!isEmpty()) {
            dequeue();
        }
        queue = (T[])new Object[DEFAULT_CAPACITY + 1];
        size = 0;
        enqueueIndex = 0;
        dequeueIndex = DEFAULT_CAPACITY;

    }


    @Override
    public T dequeue() {
        if (isEmpty()) {
            throw (new EmptyQueueException("Tried to dequeue from empty"));
        }
        T front = queue[enqueueIndex];
        queue[enqueueIndex] = null;
        enqueueIndex = (enqueueIndex + 1) % queue.length;
        size--;
        return front;
    }


    @Override
    public void enqueue(T planet) {
        if (size > MAX_CAPACITY) {
            throw (new IllegalStateException());
        }
        if (isFull()) {
            T[] oldQueue = queue;
            int oldSize = oldQueue.length;
            int newSize = size * 2 + 1;
            if (newSize > MAX_CAPACITY) {

                throw (new IllegalStateException());
            }
            @SuppressWarnings("unchecked")
            T[] tempQueue = (T[])new Object[newSize];
            queue = tempQueue;
            for (int index = 0; index < oldSize - 1; index++) {
                queue[index] = oldQueue[enqueueIndex];
                enqueueIndex = (enqueueIndex + 1) % oldSize;
            }
            enqueueIndex = 0;
            dequeueIndex = oldSize - 2;
        }
        size++;
        dequeueIndex = (dequeueIndex + 1) % queue.length;
        queue[dequeueIndex] = planet;
    }


    @Override
    public T getFront() {
        if (isEmpty()) {
            throw (new EmptyQueueException());
        }
        T front = null;
        front = queue[enqueueIndex];
        return front;
    }


    @Override
    public boolean isEmpty() {
        return (enqueueIndex == (dequeueIndex + 1) % queue.length);
    }

}
