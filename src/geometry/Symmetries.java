package geometry;

import java.util.Collection;

/**
 * @author Ritwik Banerjee
 */
public interface Symmetries<S extends Shape> {

    /**
     * Determines whether two shapes are symmetric or not.
     *
     * @param s1 the first shape instance
     * @param s2 the second shape instance
     * @return <code>true</code> is the two shapes are symmetric, and <code>false</code> otherwise
     */
    boolean areSymmetric(S s1, S s2);

    /**
     * @param s the given shape instance.
     * @return a collection of all the symmetries (including the identity transformation) of the given shape instance.
     */
    Collection<S> symmetriesOf(S s);
}