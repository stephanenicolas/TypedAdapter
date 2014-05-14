package com.github.typedadapter;

/**
 * Describes the behavior of a class whose instances can be updated with a given POJO. Typically, it
 * is implemented by a {@link View} class.
 * @param <T>
 *            the POJO type of the data held by this {@link TypedCellView} class.
 * @author SNI
 */
public interface TypedCellView<T> {
    /**
     * Updates the {@link TypedCellView} with a given instance of T. Typically, views can keep a
     * reference to the parameter t and check whether they need to be updated or not. <br/>
     * The update operation should update all information associated to any previous call of update.
     * No field should be forgotten, all sub-fields/sub-views updated.
     * @param t
     *            the POJO data used to update the view.
     */
    public void update(T t);
}
