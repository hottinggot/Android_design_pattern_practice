package c.myapplication.katok_mvvm_practice.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.myapplication.katok_mvvm_practice.R;
import c.myapplication.katok_mvvm_practice.databinding.ChattingObjectsBinding;
import c.myapplication.katok_mvvm_practice.databinding.ListObjectsBinding;
import c.myapplication.katok_mvvm_practice.model.entity.ChatEntity;

public class ChattingAdapter extends RecyclerView.Adapter<BindViewHolder<ChattingObjectsBinding>> {

    List<ChatEntity> chatEntityList;
    View.OnClickListener onItemViewClickListener;

    public ChattingAdapter(List<ChatEntity> chatEntityList){
        this.chatEntityList = chatEntityList;
        this.chatEntityList.remove(0);
    }

    @Override
    public BindViewHolder<ChattingObjectsBinding> onCreateViewHolder(ViewGroup parent, int viewType){
        View view;
        view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chatting_objects, parent, false);
        if(onItemViewClickListener!=null){
            view.setOnClickListener(onItemViewClickListener);
        }
        return new BindViewHolder<>(view);
    }

    @Override
    public int getItemCount(){
        return chatEntityList.size();
    }

    @Override
    public void onBindViewHolder(final BindViewHolder<ChattingObjectsBinding> holder, final int position) {
        holder.binding().setChatEntity(chatEntityList.get(position));
    }

    public void setNewList(List<ChatEntity> chatEntities){
        this.chatEntityList = chatEntities;
        this.chatEntityList.remove(0);
        notifyDataSetChanged();
    }
}
