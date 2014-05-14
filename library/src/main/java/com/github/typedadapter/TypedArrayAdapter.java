package com.github.typedadapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

/**
 * An object oriented, strongly typed {@link ArrayAdapter}.
 * @param <T>
 *            the type of POJO you want to display in your list view.
 * @param <C>
 *            the type of {@link View} that can display an instance of type T and implements
 *            {@link TypedCellView}<T>.
 * @author SNI
 */
public abstract class TypedArrayAdapter<T, C extends View & TypedCellView<T>> extends ArrayAdapter<T> {

    public TypedArrayAdapter(Context context, List<T> objects) {
        super(context, 0, 0, objects);
    }

    public TypedArrayAdapter(Context context, T[] objects) {
        super(context, 0, 0, objects);
    }

    public TypedArrayAdapter(Context context) {
        super(context, 0, 0);
    }

    @SuppressWarnings("unchecked")
    @Override
    public C getView(int position, View convertView, ViewGroup parent) {
        C view = null;
        if (convertView != null) {
            view = (C) convertView;
        } else {
            view = createView();
        }
        view.update(getItem(position));
        return view;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public int getItemViewType(int position) {
        return 0;
    }

    public abstract C createView();

}
