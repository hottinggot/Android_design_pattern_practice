package c.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.List;


import c.myapplication.R;
import c.myapplication.model.dao.UserDao;
import c.myapplication.view.adapter.UserListAdapter;
import c.myapplication.presenter.MainPresenter;

public class MainActivity extends AppCompatActivity implements MainContract.MainView {

    MainContract.MainPresenter presenter;
    UserListAdapter userListAdapter;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainPresenter(this);

        //add 버튼에 리스너 달기
        findViewById(R.id.add_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.insertUser();
            }
        });

        //recyclerView 에 adapter 달기
        recyclerView = findViewById(R.id.chat_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        presenter.makeUserList();
    }

    @Override
    public Application getMyApplication(){
        return getApplication();
    }

    @Override
    public String getNewUserString(){
        EditText add_text = findViewById(R.id.add_text);
        return add_text.getText().toString().trim();
    }

    @Override
    public void setAdapter(final List<UserDao.UserChat> userChatList){
        if(userListAdapter==null)
            userListAdapter = new UserListAdapter(userChatList);

        else
            userListAdapter.setNewList(userChatList);

        userListAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChattingActivity.class);
                intent.putExtra("userId", userChatList.get(recyclerView.getChildAdapterPosition(view)).userId);
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(userListAdapter);
    }

}