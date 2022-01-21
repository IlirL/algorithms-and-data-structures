import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class BNode {

    public Integer info;
    public BNode left;
    public BNode right;

    static int LEFT = 1;
    static int RIGHT = 2;

    public BNode(Integer info) {
        this.info = info;
        left = null;
        right = null;
    }

    public BNode() {
        this.info = null;
        left = null;
        right = null;
    }

    public BNode(Integer info, BNode left, BNode right) {
        this.info = info;
        this.left = left;
        this.right = right;
    }

}

class BTree {

    public BNode root;

    public BTree() {
        root = null;
    }

    public BTree(Integer info) {
        root = new BNode(info);
    }

    public void makeRoot(Integer elem) {
        root = new BNode(elem);
    }

    public void makeRootNode(BNode node) {
        root = node;
    }

    public BNode addChild(BNode node, int where, Integer elem) {

        BNode tmp = new BNode(elem);

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public BNode addChildNode(BNode node, int where, BNode tmp) {

        if (where == BNode.LEFT) {
            if (node.left != null)  // veke postoi element
                return null;
            node.left = tmp;
        } else {
            if (node.right != null) // veke postoi element
                return null;
            node.right = tmp;
        }

        return tmp;
    }

    public int sumOfLeftLeaves(BNode rootNode)
    {
        if(rootNode == null)
            return 0;

        if(isLeaf(rootNode))
        {
            BNode parent = findParent(rootNode, this.root);
            if(parent.left == rootNode)
                return  rootNode.info;
            return 0;
        }

        return sumOfLeftLeaves(rootNode.left) + sumOfLeftLeaves(rootNode.right);
    }

    public boolean isLeaf(BNode node)
    {
        if(node.left == null && node.right == null)
            return true;
        return false;
    }

    private BNode findParent(BNode node, BNode rootNode)
    {
        if(node == root)
            return null;
        if(rootNode == null)
            return rootNode;
        if(rootNode.left == node || rootNode.right == node)
            return rootNode;
        BNode left =  findParent(node, rootNode.left);
        BNode right = findParent(node, rootNode.right);

        if(left == null && right==null)
            return null;
        else if(left!=null)
            return left;
        return right;
    }

}

public class SumLeftLeaves {

    public static void main(String[] args) throws Exception {
        int i;
        int index;
        String action;

        String line;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        BNode nodes[] = new BNode[N];
        BTree tree = new BTree();

        for (i=0;i<N;i++)
            nodes[i] = new BNode();

        for (i = 0; i < N; i++) {
            line = br.readLine();
            st = new StringTokenizer(line);
            index = Integer.parseInt(st.nextToken());
            nodes[index].info = Integer.parseInt(st.nextToken());
            action = st.nextToken();
            if (action.equals("LEFT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.LEFT, nodes[index]);
            } else if (action.equals("RIGHT")) {
                tree.addChildNode(nodes[Integer.parseInt(st.nextToken())], BNode.RIGHT, nodes[index]);
            } else {
                // this node is the root
                tree.makeRootNode(nodes[index]);
            }
        }

        br.close();

        // Vasiot kod ...
        System.out.println(tree.sumOfLeftLeaves(tree.root));
    }
}
