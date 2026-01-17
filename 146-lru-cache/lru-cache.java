import java.util.HashMap;

class LRUCache {

    // ================= NODE DEFINITION =================
    class Node {
        int key, value;
        Node prev, next;

        Node(int k, int v) {
            key = k;
            value = v;
        }
    }

    // ================= DATA MEMBERS =================
    HashMap<Integer, Node> map;
    int capacity;
    Node head, tail;

    // ================= CONSTRUCTOR =================
    public LRUCache(int capacity) {
        this.capacity = capacity;
        map = new HashMap<>();

        // Dummy head and tail
        head = new Node(0, 0);
        tail = new Node(0, 0);

        head.next = tail;
        tail.prev = head;
    }

    // ================= GET =================
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }

        Node node = map.get(key);

        // Move to front (most recently used)
        remove(node);
        insertAtFront(node);

        return node.value;
    }

    // ================= PUT =================
    public void put(int key, int value) {

        // Case 1: Key already exists
        if (map.containsKey(key)) {
            Node node = map.get(key);
            node.value = value;     // update value
            remove(node);
            insertAtFront(node);
            return;                 // IMPORTANT: no eviction
        }

        // Case 2: Cache is full
        if (map.size() == capacity) {
            Node lru = tail.prev;   // least recently used
            remove(lru);
            map.remove(lru.key);
        }

        // Insert new key
        Node newNode = new Node(key, value);
        insertAtFront(newNode);
        map.put(key, newNode);
    }

    // ================= REMOVE NODE =================
    private void remove(Node node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    // ================= INSERT AT FRONT =================
    private void insertAtFront(Node node) {
        node.next = head.next;
        node.prev = head;

        head.next.prev = node;
        head.next = node;
    }
}
