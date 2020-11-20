package c.myapplication.katok_mvvm_practice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

import c.myapplication.katok_mvvm_practice.R;
import c.myapplication.katok_mvvm_practice.databinding.ActivityMainBinding;
import c.myapplication.katok_mvvm_practice.model.dao.UserDao;
import c.myapplication.katok_mvvm_practice.view.adapter.UserListAdapter;
import c.myapplication.katok_mvvm_practice.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserViewModel userViewModel;

    private EditText add_text;
    private Button add_button;

    private RecyclerView recyclerView;
    private UserListAdapter userListAdapter;

    private List<UserDao.UserChat> userChatList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        recyclerView = findViewById(R.id.chat_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getAllUserChat().observe(this, new Observer<List<UserDao.UserChat>>() {
            @Override
            public void onChanged(List<UserDao.UserChat> userChats) {
                userChatList = userChats;
                setAdapter(recyclerView);
            }
        });

        add_text = findViewById(R.id.add_text);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userViewModel.insertUser(getLastId(), add_text.getText().toString());
            }
        });

    }

    private int getLastId(){
        if(userChatList.size()==0){
            return 0;
        } else {
            return userChatList.get(0).userId;
        }

    }

    private void setAdapter(final RecyclerView recyclerView){
        if(userListAdapter==null) {
            userListAdapter = new UserListAdapter(userChatList);
        } else{
            userListAdapter.setNewList(userChatList);
        }

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
    }
}