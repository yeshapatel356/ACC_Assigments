package websiteScraping;
import java.util.*;

public class Assignment_02_Task05_AVLTree {
    private class Node {
        String key;
        int frequency;
        int height;
        Node left, right;

        Node(String key) {
            this.key = key;
            this.frequency = 1;
            this.height = 1;
        }
    }

    private Node root;

    private int height(Node N) {
        return N == null ? 0 : N.height;
    }

    private int max(int a, int b) {
        return Math.max(a, b);
    }

    private Node rightRotate(Node y) {
        Node x = y.left;
        Node T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = max(height(y.left), height(y.right)) + 1;
        x.height = max(height(x.left), height(x.right)) + 1;

        return x;
    }

    private Node leftRotate(Node x) {
        Node y = x.right;
        Node T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = max(height(x.left), height(x.right)) + 1;
        y.height = max(height(y.left), height(y.right)) + 1;

        return y;
    }

    private int getBalance(Node N) {
        return N == null ? 0 : height(N.left) - height(N.right);
    }

    public void insert(String key) {
        root = insertRec(root, key);
    }

    private Node insertRec(Node node, String key) {
        if (node == null) {
            return new Node(key);
        }

        if (key.equals(node.key)) {
            node.frequency++;
            return node;
        }

        if (key.compareTo(node.key) < 0) {
            node.left = insertRec(node.left, key);
        } else {
            node.right = insertRec(node.right, key);
        }

        node.height = 1 + max(height(node.left), height(node.right));

        int balance = getBalance(node);

        if (balance > 1 && key.compareTo(node.left.key) < 0) {
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) > 0) {
            return leftRotate(node);
        }

        if (balance > 1 && key.compareTo(node.left.key) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        if (balance < -1 && key.compareTo(node.right.key) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    public List<Map.Entry<String, Integer>> getSortedKeywords() {
        List<Map.Entry<String, Integer>> list = new ArrayList<>();
        inOrder(root, list);

        // Sort using custom comparator
        Collections.sort(list, new ValueComparator());

        return list;
    }

    private void inOrder(Node node, List<Map.Entry<String, Integer>> list) {
        if (node != null) {
            inOrder(node.left, list);
            list.add(new AbstractMap.SimpleEntry<>(node.key, node.frequency));
            inOrder(node.right, list);
        }
    }

    // Custom comparator to sort by value in descending order
    private class ValueComparator implements Comparator<Map.Entry<String, Integer>> {
        @Override
        public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
            // Sort in descending order (highest frequency first)
            return o2.getValue().compareTo(o1.getValue());
        }
    }
}
