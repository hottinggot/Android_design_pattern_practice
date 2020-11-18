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

    private UserEntity user;
    private ChatEntity chat;

    public UserRepository(Application application) {
        KatokDatabase db = KatokDatabase.getInstance(application);
        userDao = db.userDao();
        chatDao = db.chatDao();
    }

    public LiveData<List<UserDao.UserChat>> findListInformation() {
        userEntityList = userDao.findListInformation();
        return userEntityList;
    }

    public void insertUser(List<UserDao.UserChat> userChats, String userName) {
        userChatList = userChats;
        user = new UserEntity();
        chat = new ChatEntity();
        if (userChatList.size() == 0) {
            user.userId = 1;
            chat.userId = 1;
        } else {
            int n = userChatList.get(0).userId + 1;
            user.userId = n;
            chat.userId = n;
        }
        user.userName = userName.trim();
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