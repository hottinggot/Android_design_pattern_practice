package c.myapplication.katok_mvvm_practice.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.myapplication.katok_mvvm_practice.R;
import c.myapplication.katok_mvvm_practice.databinding.ListObjectsBinding;
import c.myapplication.katok_mvvm_practice.model.dao.UserDao;

public class UserListAdapter extends RecyclerView.Adapter<BindViewHolder<ListObjectsBinding>> {

    private List<UserDao.UserChat> userEntities;
    private View.OnClickListener onItemViewClickListener;

    public UserListAdapter(List<UserDao.UserChat> userEntities){
        this.userEntities = userEntities;
    }

    @Override
    public BindViewHolder<ListObjectsBinding> onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_objects, parent, false);

        if(onItemViewClickListener!=null){
            view.setOnClickListener(onItemViewClickListener);
        }
        return new BindViewHolder<>(view);
    }

    @Override
    public int getItemCount(){
        return userEntities.size();
    }

    @Override
    public void onBindViewHolder(final BindViewHolder<ListObjectsBinding> holder, final int position) {
        holder.binding().setUserChatEntity(userEntities.get(position));
    }


    public void setNewList(List<UserDao.UserChat> userEntities){
        this.userEntities = userEntities;
        notifyDataSetChanged();
    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }
}
