package c.myapplication.katok_mvvm_practice.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.myapplication.katok_mvvm_practice.R;
import c.myapplication.katok_mvvm_practice.model.dao.UserDao;

public class UserListAdapter extends RecyclerView.Adapter<UserListAdapter.UserListViewHolder> {

    private List<UserDao.UserChat> userEntities;
    private View.OnClickListener onItemViewClickListener;

    public UserListAdapter(List<UserDao.UserChat> userEntities){
        this.userEntities = userEntities;
    }

    @Override
    public UserListViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_objects, parent, false);
        if(onItemViewClickListener!=null){
            view.setOnClickListener(onItemViewClickListener);
        }
        return new UserListViewHolder(view);
    }

    @Override
    public int getItemCount(){
        return userEntities.size();
    }

    @Override
    public void onBindViewHolder(final UserListViewHolder holder, final int position) {
        holder.list_user_name.setText(userEntities.get(position).userName);
        holder.list_contents.setText(userEntities.get(position).contents);
        //holder.list_date.setText((CharSequence) userEntities.get(position).date);
    }

    class UserListViewHolder extends RecyclerView.ViewHolder{
        TextView list_user_name, list_contents, list_date, list_num;
        ImageView list_profile_image;

        UserListViewHolder(View view){
            super(view);
            list_user_name = view.findViewById(R.id.list_user_name);
            list_contents = view.findViewById(R.id.list_contents);
            list_date = view.findViewById(R.id.list_date);
            list_num = view.findViewById(R.id.list_num);
            list_profile_image = view.findViewById(R.id.list_profile_image);
        }
    }

    public void setNewList(List<UserDao.UserChat> userEntities){
        this.userEntities = userEntities;
        notifyDataSetChanged();
    }

    public void setOnItemViewClickListener(View.OnClickListener onItemViewClickListener) {
        this.onItemViewClickListener = onItemViewClickListener;
    }
}
