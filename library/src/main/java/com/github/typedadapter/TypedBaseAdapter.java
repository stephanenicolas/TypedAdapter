package com.github.typedadapter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

/**
 * An object oriented, strongly typed {@link BaseAdapter}. It can be refilled dynamically.
 * @param <T>
 *            the type of POJO you want to display in your list view.
 * @param <C>
 *            the type of {@link View} that can display an instance of type T and implements
 *            {@link TypedCellView}<T>.
 * @author SNI
 */
public abstract class TypedBaseAdapter<T, C extends View & TypedCellView<T>> extends BaseAdapter {

    private List<T> objects;

    public TypedBaseAdapter() {
        this.objects = new ArrayList<T>();
    }

    public TypedBaseAdapter(List<T> objects) {
        this();
        this.objects.addAll(objects);
    }

    public TypedBaseAdapter(T[] objects) {
        this();
        Collections.addAll(this.objects, objects);
    }

    @Override
    public T getItem( int position ) {
        return objects.get(position);
    }
    
    @Override
    public long getItemId(int position) {
        return getItem(position).hashCode();
    }
    
    @Override
    public int getCount() {
        return objects.size();
    }
    
    public void setObjects(List<T> objects) {
        this.objects.clear();
        this.objects.addAll(objects);
        notifyDataSetChanged();
    }

    public void setObjects(T[] objects) {
        this.objects.clear();
        Collections.addAll(this.objects, objects);
        notifyDataSetChanged();
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
