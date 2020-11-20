package c.myapplication.katok_mvvm_practice.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;


import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;

import c.myapplication.katok_mvvm_practice.R;
import c.myapplication.katok_mvvm_practice.model.entity.ChatEntity;
import c.myapplication.katok_mvvm_practice.view.adapter.ChattingAdapter;
import c.myapplication.katok_mvvm_practice.viewmodel.ChatViewModel;


public class ChattingActivity extends AppCompatActivity {

    ChatViewModel chatViewModel;

    RecyclerView recyclerView;
    ChattingAdapter chattingAdapter;

    List<ChatEntity> chatEntityList;
    int userId;

    TextView text_chatting_room;
    EditText text_to_send;
    Button button_to_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        recyclerView = findViewById(R.id.chatting_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        text_chatting_room = findViewById(R.id.text_chatting_room);
        text_chatting_room.setText(getIntent().getExtras().getString("userName"));

        userId = getIntent().getExtras().getInt("userId");
        chatViewModel.getChattingList(userId).observe(this, new Observer<List<ChatEntity>>() {
            @Override
            public void onChanged(List<ChatEntity> chatEntities) {
                chatEntityList = chatEntities;
                setAdapter(recyclerView);
            }
        });

        text_to_send = findViewById(R.id.text_to_send);
        button_to_send = findViewById(R.id.button_to_send);

        button_to_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatViewModel.insertMessage(userId, text_to_send.getText().toString());
            }
        });

    }

    private void setAdapter(RecyclerView recyclerView){
        if(chattingAdapter == null) {
            chattingAdapter = new ChattingAdapter(chatEntityList);
        }
        else {
            chattingAdapter.setNewList(chatEntityList);
        }
        recyclerView.setAdapter(chattingAdapter);
    }

}