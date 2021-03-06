package datastructure;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Represent a stack tree : A tree that can branch  but only can add/ delete leaf nodes
 * @author boomza654
 *
 *@param <T> type of data to hold
 */
public class StackTree<T> {
    
    public final StackTreeNode<T> root; // The null node holding the thing
    public StackTreeNode<T> curNode; // please dont d
    public StackTree(){
        this.root = new StackTreeNode<>();
        this.curNode=this.root;
    }
    /**
     * push new stack tree node at the current Node (this also creates branch) and move the curNode down the latest branch
     * @param data
     */
    public void push(T data) {
        assert curNode!=null : "error curBrnahc null";
        StackTreeNode<T> newNode = new StackTreeNode<>();
        newNode.data=data;
        newNode.parent=this.curNode;
        this.curNode.children.add(newNode);
        this.curNode=this.curNode.children.get(this.curNode.children.size()-1);
    }
    /**
     * pop a stack tree node at the current Node and move current Node up
     * @return data from the popped stack tree 
     * @throws RuntimeException if the current Node has a children or is a root or Node does not have parent
     */
    public T pop() {
        assert curNode!=null : "error curNode null";
        assert curNode.children.isEmpty(): "error HEAD has children";
        assert curNode!=root:"Error nothing to pop from current node";
        assert curNode.parent!=null:"Error nothing to pop from current node";
        StackTreeNode<T> parNode = curNode.parent;
        int i= parNode.children.indexOf(curNode);
        if(i==-1) throw new RuntimeException("WTF Cant find current node from children of parent node?!?!");
        parNode.children.remove(i);
        StackTreeNode<T> removedNode = curNode;
        this.curNode=parNode;
        removedNode.parent=null;
        removedNode.children=null;
        return removedNode.data;
    }
    /**
     * Get the data from current Node
     * @return data from curNode
     */
    public T get() {  assert curNode!=null : "error curNode null"; return curNode.data; }
    /**
     * Get the number of children from current Node
     * @return T
     */
    public int getChildrenCount() {  assert curNode!=null : "error curNode null"; return curNode.children.size(); }
    /**
     * Try traversing down the first child if stuck throw error
     * @param i
     * @throws AssertionError if the curNode is leaf
     */
    public void traverseDown(int i) {
        assert curNode!=null : "error curNode null";
        assert (curNode.children.size()>i):"No children of index "+i ;
        curNode=curNode.children.get(i);
    }
    public void traverseDown() {this.traverseDown(0);}
    /**
     * Try traversing up the tree
     * if no parent or the current node is at root already throw assertionError
     */
    public void traverseUp() {
        assert curNode!=null : "error curNode null";
        assert curNode!=root:"Error nothing to pop from current node";
        assert curNode.parent!=null:"Error nothing to pop from current node";
        curNode=curNode.parent;
    }
    /**
     * Show Tree visualization of the STack tree like git lols
     */
    @Override
    public String toString() {
        Map<StackTreeNode<T>,String> markNodes = new HashMap<>();
        markNodes.put(root, "-- root");
        markNodes.put(curNode,"-- curr");
        return root.toStringTree("",markNodes);
    }
    public static void main(String[] args) {
        StackTree<String> s = new StackTree<>();
        s.push("8");
        s.push("2\n43\nboomza\n654");

        s.traverseUp();
        s.push("3");
        //s.traverseDown();
        s.push("1");
        s.push("5\n6\r\n5");
        //s.traverseDown(1);
        s.push("4\na\nb\nc");
        s.traverseUp();
        s.push("44");
        //s.traverseDown();
        s.traverseUp();
        s.traverseDown();
        //s.pop();
        System.out.println(s);
        
    }
    
    
    
    /**
     * A noce of stack tree
     * @author boomza654
     *
     * @param <T> type of data
     */
    public static class StackTreeNode<T>{
        public StackTreeNode<T> parent=null;
        public List<StackTreeNode<T>> children =new ArrayList<>();
        public T data = null;
        /**
         * Return a string that rpresnets a tree
         * @param prefix
         * @param markNodes Map of (Node => Marking string) that if we reach the k node we should add marking string after it
         * @return the part that represent the current node and below with \n
         */
        public String toStringTree(String prefix,Map<StackTreeNode<T>,String> markNodes) {
            String out = addPrefix(
                    prefix,
                    "    ",
                    (data!=null)?data.toString():"null",
                    (markNodes.containsKey(this)? markNodes.get(this):""),
                    this.children.isEmpty()
                    );
            if(children.size()==0) return out;
            out+=prefix+"|\n";
            if(children.size()>1) {
                out+=prefix+"+"+repeat("--",children.size()-1)+"\n";
                out+=prefix+ repeat("| ",children.size())+"\n";
                for(int i=children.size()-1;i>=1;i--) {
                    out+= children.get(i).toStringTree(prefix+repeat("| ",i), markNodes);
                }
            }
            return out + children.get(0).toStringTree(prefix, markNodes);
        }
        
        private static String repeat(String c, int n) {
            String out=""; for(int i=0;i<n;i++) out+=c;
            return out;
        }
        /**
         * Use for adding prefix Tree branch on the front of the given text
         * @param prefix
         * @param indent
         * @param text
         * @param mark
         * @param isLeaf
         * @return
         */
        private static String addPrefix(String prefix, String indent,String text, String mark, boolean isLeaf) {
            String[] lines = (text.split("\\r?\\n"));
            for(int i=0;i<lines.length;i++) {
                lines[i]=(lines[i].endsWith("\n"))?lines[i].substring(0, lines[i].length()-1):lines[i];
                lines[i]=(prefix)
                        +(i==0?"* "+repeat("<",indent.length()-2)+" ":(isLeaf?" ":"|")+indent)
                        +lines[i]+" "
                        + (i==lines.length/2?mark:"")
                        +"\n";
            }

            return String.join("", Arrays.asList(lines));
        }
    }
}

