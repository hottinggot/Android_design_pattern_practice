package c.myapplication.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;
import java.util.List;

import c.myapplication.R;
import c.myapplication.controller.adapter.ChattingAdapter;
import c.myapplication.model.KatokDatabase;
import c.myapplication.model.dao.ChatDao;
import c.myapplication.model.entity.ChatEntity;
import c.myapplication.model.repository.ChatRepository;

public class ChattingActivity extends AppCompatActivity {

    private TextView text_chatting_room;
    private RecyclerView chatting_recycler;
    private EditText text_to_send;
    private Button button_to_send;

    private ChatRepository chatRepository;

    private int userId;
    private String userName;

    private List<ChatEntity> chatEntityList;
    private ChattingAdapter chattingAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        text_chatting_room = findViewById(R.id.text_chatting_room);
        chatting_recycler = findViewById(R.id.chatting_recycler);
        text_to_send = findViewById(R.id.text_to_send);
        button_to_send = findViewById(R.id.button_to_send);

        Intent intent = getIntent();
        userId = intent.getExtras().getInt("userId");
        userName = intent.getExtras().getString("userName");

        text_chatting_room.setText(userName);

        chatting_recycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //Model
        chatRepository = new ChatRepository(getApplication());
        chatRepository.findChatList(userId).observe(this, new Observer<List<ChatEntity>>() {
            @Override
            public void onChanged(List<ChatEntity> chatEntities) {
                chatEntityList = chatEntities;
                setAdapter(chatting_recycler);
            }
        });

        button_to_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (text_to_send.getText().toString().trim().length() != 0) {
                    chatRepository.insertChat(userId, text_to_send.getText().toString().trim());
                }
            }
        });
    }

    private void setAdapter(RecyclerView recyclerView) {
        if (chattingAdapter == null) {
            chattingAdapter = new ChattingAdapter(chatEntityList);
            recyclerView.setAdapter(chattingAdapter);
        } else {
            chattingAdapter.setNewList(chatEntityList);
        }
    }
}