package circuit;


import java.util.*;





/**
 * Represents each port in the circuit
 * 
 * Yosys
 * @author boomza654
 *
 */
public class Port {
    public String name;
    public PortDirection direction;
    public List<Node> bits; // what got conected
    
}


