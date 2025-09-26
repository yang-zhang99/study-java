import java.util.ArrayList;
import java.util.List;


class Solution {
    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> rtu = new ArrayList<>();

        this.xunhuan(root.right, rtu);

        return rtu;
    }

    private void xunhuan(TreeNode root, List<Integer> rtu) {
        if (root == null) {
            return;
        }

        this.xunhuan(root.left, rtu);
        rtu.add(root.val);
        this.xunhuan(root.right, rtu);

    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }


}