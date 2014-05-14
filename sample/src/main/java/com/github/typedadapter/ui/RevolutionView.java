package com.github.typedadapter.ui;

import com.github.typedadapter.R;
import com.github.typedadapter.TypedCellView;
import com.github.typedadapter.R.id;
import com.github.typedadapter.model.Revolution;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class RevolutionView extends RelativeLayout implements TypedCellView<Revolution> {

    private TextView textViewName;
    private TextView textViewDate;
    
    public RevolutionView(Context context) {
        super(context);
        initViews(context);
    }

    @Override
    public void update(Revolution revolution) {
        textViewName.setText(revolution.name);
        textViewDate.setText(revolution.date);
    }
    
    private void initViews(Context context) {
        LayoutInflater.from(context).inflate(R.layout.view_revolution, this);
        textViewName = (TextView) findViewById(R.id.textView_name);
        textViewDate = (TextView) findViewById(R.id.textView_date);
        //good practice
        onFinishInflate();
    }
}
