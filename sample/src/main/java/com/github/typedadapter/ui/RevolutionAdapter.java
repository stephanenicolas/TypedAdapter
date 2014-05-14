package com.github.typedadapter.ui;

import java.util.List;

import com.github.typedadapter.TypedArrayAdapter;
import com.github.typedadapter.model.Revolution;

import android.content.Context;

public class RevolutionAdapter extends TypedArrayAdapter<Revolution, RevolutionView> {

    public RevolutionAdapter(Context context, List<Revolution> revolutions) {
        super(context, revolutions);
    }

    @Override
    public RevolutionView createView() {
        return new RevolutionView(getContext());
    }
}
