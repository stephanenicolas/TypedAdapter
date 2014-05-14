TypedAdapter
============

Object Oriented android list view adapter(s) using generics to display given Pojos.

This technique is an alternative to the view holder design pattern. It has the following advantages : 
* is strongly typed ;
* allows to avoid the `setTag` ugly hack of the view holders ;
* gives the opportunity to create full android custom views, elegant and complete, that can even be reused as XML layout elements. 

Usage
-----

Define your adapter using both the POJO type and your custom view type : 

```java
public class PojoAdapter extends TypedArrayAdapter<Pojo, PojoView> {

    public PojoAdapter(Context context, List<Pojo> pojos) {
        super(context, pojos);
    }

    @Override
    public PojoView createView() {
        return new PojoView(getContext());
    }
}

````

Define your custom view type and implement `TypedCellView<Pojo>`

```java
public class PojoView extends RelativeLayout implements TypedCellView<Pojo> {

    private TextView textViewName;
    
    public PojoView(Context context) {
        super(context);
        LayoutInflater.from(context).inflate(<view layout>, this);
        textViewName = (TextView) findViewById(R.id.textView_name);
        
        //good practice
        onFinishInflate();
    }

    @Override
    public void update(Pojo pojo) {
        textViewName.setText(pojo.name);
    }
}
```

You need a single constructor using a `Context` as parameter when creating a view programmatically (here, the adapter does). Add the other `View` constructors if you want to make your custom view a full android component that can be inflated via XML.


