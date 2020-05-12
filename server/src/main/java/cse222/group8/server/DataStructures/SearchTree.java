package cse222.group8.server.DataStructures;

/**
 * Search Tree interface.
 *
 * @param <E> the type parameter
 */
public interface SearchTree<E>{
    /**
     * Add item to tree.
     *
     * @param item the item
     * @return the addition status
     */
    boolean add(E item);

    /**
     * Check if the tree contains target.
     *
     * @param target the target
     * @return the result
     */
    boolean contains(E target);

    /**
     * Find target in tree.
     *
     * @param target the target
     * @return the found data
     */
    E find(E target);

    /**
     * Delete target from tree.
     *
     * @param target the target
     * @return the removed data
     */
    E delete(E target);

    /**
     * Remove target from tree.
     *
     * @param target the target
     * @return the removal status
     */
    boolean remove(E target);
}