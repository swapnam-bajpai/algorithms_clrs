package taocp.vol1.information_structures;

import java.util.List;

public class Tree<T> {
    private final Tree<T> root;
    private List<Tree<T>> children;

    public Tree(Tree<T> root) {
        this.root = root;
    }

    public Tree(Tree<T> root, List<Tree<T>> children) {
        this.root = root;
        this.children = children;
    }

    public Tree<T> getRoot() {
        return root;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public int getDegree() {
        if (children == null || children.isEmpty()) {
            return 0;
        } else {
            return children.size();
        }
    }
}
