package util;


/**
 * Immutable Pair of object
 * @author boomza654
 *
 * @param <A> first type
 * @param <B> second type
 */
public class Pair<A,B> {
    
    public final A first;
    public final B second;
    
    public Pair(final A a, final B b) {
        first = a;
        second = b;
    }

    @Override
    public String toString() {
        return "Pair < " + first + " , " + second + " >";
    }

    @Override
    public boolean equals(final Object obj) {
        if (obj instanceof Pair<?, ?>) {
            final Pair<?, ?> that = (Pair<?, ?>) obj;
            return that.first.equals(first) && that.second.equals(second);
        }
        return false;
    }
    @Override public int hashCode() {
        return first.hashCode()+second.hashCode();
    }
    
}
