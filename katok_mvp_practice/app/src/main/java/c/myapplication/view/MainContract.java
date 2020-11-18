package c.myapplication.view;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.myapplication.model.dao.UserDao;
import c.myapplication.model.entity.ChatEntity;

public interface MainContract {

    interface MainView{
        Application getMyApplication();
        void setAdapter(final List<UserDao.UserChat> userChatList);
        int getLastUserId();
        String getNewUserString();
        Context getMyActivity();

    }

    interface MainPresenter{
        LiveData<List<UserDao.UserChat>> makeUserList();
        void setUserList(List<UserDao.UserChat> userChats);
        void insertUser();
    }

    interface ChatView{
        Application getMyApplication();
        void setAdapter(final List<ChatEntity> chatEntityList);
        int getUserId();
        String getTextMessage();
    }

    interface ChatPresenter{
        LiveData<List<ChatEntity>> makeChatList();
        void setChatList(List<ChatEntity> chatEntityList);
        void insertChat();
    }
}
