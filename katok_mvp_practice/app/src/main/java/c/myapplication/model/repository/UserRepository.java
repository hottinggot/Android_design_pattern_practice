package c.myapplication.model.repository;

import android.app.Application;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import java.util.List;

import c.myapplication.model.KatokDatabase;
import c.myapplication.model.dao.ChatDao;
import c.myapplication.model.dao.UserDao;
import c.myapplication.model.entity.ChatEntity;
import c.myapplication.model.entity.UserEntity;

public class UserRepository {

    private UserDao userDao;
    private ChatDao chatDao;
    private LiveData<List<UserDao.UserChat>> userEntityList;
    private List<UserDao.UserChat> userChatList;

    public UserRepository(Application application) {
        KatokDatabase db = KatokDatabase.getInstance(application);
        userDao = db.userDao();
        chatDao = db.chatDao();
    }

    public LiveData<List<UserDao.UserChat>> findListInformation() {
        userEntityList = userDao.findListInformation();
        return userEntityList;
    }

    public void insertUser(int userId, final String userName) {

        UserEntity user = new UserEntity();
        ChatEntity chat = new ChatEntity();

        int n = userId + 1;
        user.userId = n;
        chat.userId = n;

        user.userName = userName;
        insert(user, chat);

    }

    private void insert(final UserEntity user, final ChatEntity chat) {
        KatokDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
            }
        });
        KatokDatabase.databaseWriteExecutor.execute(new Runnable() {
            @Override
            public void run() {
                chatDao.insertChat(chat);
            }
        });
    }
}