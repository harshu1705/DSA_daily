class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {

        // STEP 1: Base case
        // If current node is null OR equals p OR equals q
        // return it directly
        if (root == null || root == p || root == q) {
            return root;
        }

        // STEP 2: Recur on left subtree
        TreeNode leftchild = lowestCommonAncestor(root.left, p, q);

        // STEP 3: Recur on right subtree
        TreeNode rightchild = lowestCommonAncestor(root.right, p, q);

        // STEP 4: If p and q are both in right subtree
        if (leftchild == null) {
            return rightchild;
        }
        // STEP 5: If p and q are both in left subtree
        else if (rightchild == null) {
            return leftchild;
        }
        // STEP 6: p and q found in different subtrees
        // So current node is their LCA
        else {
            return root;
        }
    }
}
