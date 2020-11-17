package c.myapplication.controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import c.myapplication.R;
import c.myapplication.controller.adapter.UserListAdapter;
import c.myapplication.model.KatokDatabase;
import c.myapplication.model.dao.ChatDao;
import c.myapplication.model.dao.UserDao;
import c.myapplication.model.entity.ChatEntity;
import c.myapplication.model.entity.UserEntity;
import c.myapplication.model.repository.ChatRepository;
import c.myapplication.model.repository.UserRepository;

public class MainActivity extends AppCompatActivity {

    private EditText add_text;
    private Button add_button;
    private RecyclerView recyclerView;
    private UserListAdapter userListAdapter;

    private List<UserDao.UserChat> userChatList;
    private UserRepository userRepository;
    private ChatRepository chatRepository;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        add_text = findViewById(R.id.add_text);
        add_button = findViewById(R.id.add_button);
        recyclerView = findViewById(R.id.chat_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        //Model
        userRepository = new UserRepository(getApplication());

        userRepository.findListInformation().observe(this, new Observer<List<UserDao.UserChat>>() {
            @Override
            public void onChanged(List<UserDao.UserChat> userChats) {
                userChatList = userChats;
                setAdapter(recyclerView);
            }
        });

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (add_text.getText().toString().length() != 0) {
                    userRepository.insertUser();
                }
            }
        });
    }

    private void setAdapter(final RecyclerView recyclerView) {
        if (userListAdapter == null) {
            userListAdapter = new UserListAdapter(userChatList);
            userListAdapter.setOnItemViewClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), ChattingActivity.class);
                    intent.putExtra("userId", userChatList.get(recyclerView.getChildAdapterPosition(view)).userId);
                    intent.putExtra("userName", userChatList.get(recyclerView.getChildAdapterPosition(view)).userName);
                    startActivity(intent);
                }
            });
            recyclerView.setAdapter(userListAdapter);
        } else {
            userListAdapter.setNewList(userChatList);
        }
    }

}