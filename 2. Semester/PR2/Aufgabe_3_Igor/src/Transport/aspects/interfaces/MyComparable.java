package Transport.aspects.interfaces;

public interface MyComparable<E extends WithUniqueId> extends Comparable<E> {
    // ORDERINGS
    @Override
    default public int compareTo(E o) {
        Long diff = ((E)this).id().idNumber() - o.id().idNumber();
        if (diff < Integer.MIN_VALUE)
            return Integer.MIN_VALUE;
        else if (diff > Integer.MAX_VALUE)
            return Integer.MAX_VALUE;
        else
            return diff.intValue();
    }
}
