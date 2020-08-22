package lesson32;

public class Main {
    public static void main(final String[] args) {
        TreeNode root = new TreeNode(5);
        BinaryTree tree = new BinaryTree(root);

        root.setLeft(new TreeNode(3));
        root.setRight(new TreeNode(10));
        root.getLeft().setLeft(new TreeNode(1));
        root.getLeft().setRight(new TreeNode(4));

        System.out.printf(
                "Количество листьев в дереве: %d%n",
                tree.getLeafCount()
        );
    }
}
