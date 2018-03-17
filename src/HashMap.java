import Exception.*;

import java.util.LinkedList;

public class HashMap<K, V>{

    private int baseSize = 16;
    @SuppressWarnings(value = "unchecked")
    private LinkedList<KeyValue>[] elements = new LinkedList[baseSize];

    public int size() {
        int numOfElements = 0;

        for (LinkedList<KeyValue> element : elements) {
            if (element != null)
            for (KeyValue ignored : element) {
                numOfElements ++;
            }
        }

        return numOfElements;
    }

    public boolean isEmpty() {
        boolean empty = true;

        for (LinkedList<KeyValue> element : elements) {
            if (element != null)
            for (KeyValue ignored : element) {
                empty = false;
            }
        }

        return empty;
    }

    public boolean containsKey(K key) {
        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];
        if (list != null) {
            for (KeyValue keyValue : list) {
                if (keyValue.getKey().equals(key)) return true;
            }
        }
        return false;
    }

    public boolean containsValue(V value) {
        for (LinkedList<KeyValue> element : elements) {
            if (element != null)
                for (KeyValue keyValue : element) {
                    if (keyValue.getValue().equals(value)) return true;
                }
        }
        return false;
    }

    @SuppressWarnings("unchecked")
    public V get(K key) {

        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];

        V value = null;
        if (list != null) {
            for (KeyValue keyValue : list) {
                if (keyValue.getKey().equals(key)) value = (V) keyValue.getValue();
            }
        }

        if (value == null) throw new KeyDoesNotExistsException("The selected key does not exist!");

        return value;
    }

    public void put(K key, V value) {
        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];

        if (list != null) {
            for (KeyValue keyValue : list) {
                if (keyValue.getKey().equals(key))
                    throw new KeyAlreadyExistsException("Key already exists: " + key.getClass().getSimpleName() + ": " + key.toString());
            }
        } else {
            list = new LinkedList<>();
        }

        list.add(new KeyValue<>(key, value));
        elements[position] = list; // ?? why isn't there a reference to element?
    }

    private int getHash(K key) {
        return key.hashCode() % baseSize;
    }

    public void remove(K key) {

        int position = getHash(key);
        LinkedList<KeyValue> list = elements[position];

        if (list != null) {
            for (KeyValue keyValue : list) {
                if (keyValue.getKey().equals(key)) list.remove(keyValue);
                return;
            }
        }

        throw new KeyDoesNotExistsException("The selected key does not exist!");
    }

    public void clearAll() {
        for (LinkedList<KeyValue> element : elements) {
            if (element != null)
                for (KeyValue keyValue : element) {
                    element.remove(keyValue);
                }
        }
    }
}
