package c.myapplication.view;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import c.myapplication.ChattingContract;
import c.myapplication.MainContract;
import c.myapplication.R;
import c.myapplication.model.entity.ChatEntity;
import c.myapplication.view.adapter.ChattingAdapter;
import c.myapplication.presenter.ChattingPresenter;

public class ChattingActivity extends AppCompatActivity implements ChattingContract.ChatView {

    ChattingAdapter chattingAdapter;
    RecyclerView recyclerView;
    ChattingContract.ChatPresenter chatPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        chatPresenter = new ChattingPresenter(this);

        //recyclerView 설정
        recyclerView = findViewById(R.id.chatting_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        chatPresenter.makeChatList().observe(this, new Observer<List<ChatEntity>>() {
            @Override
            public void onChanged(List<ChatEntity> chatEntities) {
                chatPresenter.setChatList(chatEntities);
            }
        });

        //전송버튼에 리스너 달기
        findViewById(R.id.button_to_send).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                chatPresenter.insertChat();
            }
        });

    }

    @Override
    public Application getMyApplication(){
        return getApplication();
    }

    @Override
    public void setAdapter(List<ChatEntity> chatEntityList){
        if(chattingAdapter==null)
            chattingAdapter = new ChattingAdapter(chatEntityList);
        else
            chattingAdapter.setNewList(chatEntityList);

        recyclerView.setAdapter(chattingAdapter);
    }

    @Override
    public int getUserId(){
        Intent intent = getIntent();
        int userId = intent.getExtras().getInt("userId");

        return userId;
    }

    @Override
    public String getTextMessage(){
        EditText text_to_send = findViewById(R.id.text_to_send);

        return text_to_send.getText().toString().trim();
    }
}