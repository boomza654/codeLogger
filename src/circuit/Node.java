package circuit;


/**
 * Represents a node of things in circuit 
 * @author boomza654
 *
 */
public interface Node {
    /**
     * 
     * @return the name of the node 
     */
    public String name();
    public static Node wire(int x) { return new Wire(x);}
    public static Node source(int x) { return new Source(x);}
}

class Wire implements Node{
    private int number;
    public Wire(int x) {
        this.number=x;
    }
    public String name() { return this.number+"";}
}


class Source implements Node {
    private int bit;
    public Source(int x) {
        this.bit = x;
    }
    public String name() {
        return "\""+this.bit+"\"";
    }
}