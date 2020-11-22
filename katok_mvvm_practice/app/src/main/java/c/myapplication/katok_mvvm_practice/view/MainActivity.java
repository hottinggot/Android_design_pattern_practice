package c.myapplication.katok_mvvm_practice.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.LiveData;
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

    private UserListAdapter userListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        binding.setUserViewModel(userViewModel);

        binding.chatRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        userViewModel.getAllUserChat().observe(this, new Observer<List<UserDao.UserChat>>() {
            @Override
            public void onChanged(List<UserDao.UserChat> userChats) {
                setAdapter(userChats);
            }
        });

        add_text = findViewById(R.id.add_text);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                userViewModel.insertUser(userViewModel.getLastId(), add_text.getText().toString());
            }
        });

    }


    private void setAdapter(final List<UserDao.UserChat> userChatList){
        if(userListAdapter==null) {
            userListAdapter = new UserListAdapter(userChatList);
        } else{
            userListAdapter.setNewList(userChatList);
        }

        userListAdapter.setOnItemViewClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ChattingActivity.class);
                intent.putExtra("userId", userChatList.get(binding.chatRecycler.getChildAdapterPosition(view)).userId);
                intent.putExtra("userName", userChatList.get(binding.chatRecycler.getChildAdapterPosition(view)).userName);
                startActivity(intent);
            }
        });

        binding.chatRecycler.setAdapter(userListAdapter);
    }
}