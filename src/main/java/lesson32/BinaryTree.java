package lesson32;

public class BinaryTree {
    private final TreeNode root;

    public BinaryTree(final TreeNode root) {
        this.root = root;
    }

    public TreeNode getRoot() {
        return root;
    }

    public int getLeafCount() {
        return getLeafCount(root);
    }

    public int getLeafCount(final TreeNode node) {
        if (node == null) {
            return 0;
        }
        if (node.getLeft() == null && node.getRight() == null) {
            return 1;
        } else {
            return getLeafCount(node.getLeft()) + getLeafCount(node.getRight());
        }
    }
}
