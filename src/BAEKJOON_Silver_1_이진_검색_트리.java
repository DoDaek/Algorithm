import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEKJOON_Silver_1_이진_검색_트리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        BinarySearchTree bst = new BinarySearchTree();

        String line;
        while ((line = br.readLine()) != null) {
            int nodeValue = Integer.parseInt(line);
            bst.insert(nodeValue);
        }

        bst.printPostOrder();

        br.close();
    }

    static class BinarySearchTree {
        Node root;

        public BinarySearchTree() {
            this.root = null;
        }

        public void insert(int value) {
            this.root = insertRecursive(this.root, value);
        }

        private Node insertRecursive(Node current, int value) {
            if (current == null) {
                current = new Node(value);
            }

            if (value < current.value) {
                current.left = insertRecursive(current.left, value);
            } else if (value > current.value) {
                current.right = insertRecursive(current.right, value);
            }

            return current;
        }

        public void printPostOrder() {
            postOrder(this.root);
        }

        private void postOrder(Node current) {
            if (current.left != null) postOrder(current.left);
            if (current.right != null) postOrder(current.right);
            System.out.println(current.value);
        }
    }

    static class Node {
        int value;
        Node left, right;

        public Node(int value) {
            this.value = value;
            this.left = null;
            this.right = null;
        }
    }
}
