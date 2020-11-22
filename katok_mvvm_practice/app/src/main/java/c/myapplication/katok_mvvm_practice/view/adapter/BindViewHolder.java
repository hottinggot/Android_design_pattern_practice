package c.myapplication.katok_mvvm_practice.view.adapter;

import android.view.View;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

public class BindViewHolder<T extends ViewDataBinding> extends RecyclerView.ViewHolder{

    private final T binding;

    BindViewHolder(View view){
        super(view);
        this.binding = (T) DataBindingUtil.bind(view);
    }

    public T binding() {
        return binding;
    }
}

