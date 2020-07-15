package hitlisteners;

/**
 * The hitlisteners.HitNotifier interface.
 */
public interface HitNotifier {
    /**
     * Add hl as a listener to hit events.
     * @param hl the hit listener we want to add
     */
    void addHitListener(HitListener hl);
    /**
     * Remove hl from the list of listeners to hit events.
     * @param hl the hit listener we want to remove
     */
    // Remove hl from the list of listeners to hit events.
    void removeHitListener(HitListener hl);
}

