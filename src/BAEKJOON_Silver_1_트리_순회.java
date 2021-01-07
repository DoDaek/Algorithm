import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BAEKJOON_Silver_1_트리_순회 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Tree tree = new Tree();
        for (int i = 0; i < N; i++) {
            String line = br.readLine();

            char current = line.charAt(0);
            char left = line.charAt(2);
            char right = line.charAt(4);

            tree.add(current, left, right);
        }

        preOrder(tree.root);
        System.out.println();
        inOrder(tree.root);
        System.out.println();
        postOrder(tree.root);

        br.close();
    }

    public static void preOrder(Node node) {
        System.out.print(node.data);

        if (node.left != null) {
            preOrder(node.left);
        }

        if (node.right != null) {
            preOrder(node.right);
        }
    }

    public static void postOrder(Node node) {
        if (node.left != null) {
            postOrder(node.left);
        }

        if (node.right != null) {
            postOrder(node.right);
        }

        System.out.print(node.data);
    }

    public static void inOrder(Node node) {
        if (node.left != null) {
            inOrder(node.left);
        }

        System.out.print(node.data);

        if (node.right != null) {
            inOrder(node.right);
        }
    }

    static class Node {
        char data;
        Node left;
        Node right;

        public Node(char data) {
            this.data = data;
        }
    }

    static class Tree {
        Node root;

        public void add(char data, char left, char right) {
            if (this.root == null) {
                this.root = new Node(data);
                if (left != '.') {
                    this.root.left = new Node(left);
                }

                if (right != '.') {
                    this.root.right = new Node(right);
                }
            } else {
                search(this.root, data, left, right);
            }
        }

        private void search(Node node, char data, char left, char right) {
            if (node == null) return;
            if (node.data == data) {
                if (left != '.') {
                    node.left = new Node(left);
                }

                if (right != '.') {
                    node.right = new Node(right);
                }
            } else {
                search(node.left, data, left, right);
                search(node.right, data, left, right);
            }
        }
    }
}
