package c.myapplication.katok_mvvm_practice.view;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import c.myapplication.katok_mvvm_practice.R;
import c.myapplication.katok_mvvm_practice.databinding.ActivityChattingBinding;
import c.myapplication.katok_mvvm_practice.databinding.ActivityMainBinding;
import c.myapplication.katok_mvvm_practice.model.entity.ChatEntity;
import c.myapplication.katok_mvvm_practice.view.adapter.ChattingAdapter;
import c.myapplication.katok_mvvm_practice.viewmodel.ChatViewModel;


public class ChattingActivity extends AppCompatActivity {

    private ActivityChattingBinding binding;

    ChatViewModel chatViewModel;
    ChattingAdapter chattingAdapter;

    int userId;

    Button button_to_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_chatting);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_chatting);
        binding.setChatViewModel(chatViewModel);
        binding.chattingRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.setUserName(getIntent().getExtras().getString("userName"));

        userId = getIntent().getExtras().getInt("userId");

        chatViewModel.getChattingList(userId).observe(this, new Observer<List<ChatEntity>>() {
            @Override
            public void onChanged(List<ChatEntity> chatEntities) {
                setAdapter(chatEntities);
            }
        });


//        button_to_send.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                chatViewModel.insertMessage(userId, binding.getMessage());
//            }
//        });

    }

    private void setAdapter(List<ChatEntity> chatEntityList){
        if(chattingAdapter == null) {
            chattingAdapter = new ChattingAdapter(chatEntityList);
        }
        else {
            chattingAdapter.setNewList(chatEntityList);
        }
        binding.chattingRecycler.setAdapter(chattingAdapter);
    }


    private TextWatcher watcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };

}