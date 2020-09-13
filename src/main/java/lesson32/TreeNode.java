package lesson32;

public class TreeNode {
    private int data;

    private TreeNode left;

    private TreeNode right;

    public TreeNode(final int item) {
        data = item;
    }

    public int getData() {
        return data;
    }

    public void setData(final int data) {
        this.data = data;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(final TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(final TreeNode right) {
        this.right = right;
    }
}
