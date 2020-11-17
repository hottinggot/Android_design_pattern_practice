package c.myapplication.view;

import android.app.Application;

import java.util.List;

import c.myapplication.model.dao.UserDao;
import c.myapplication.model.entity.ChatEntity;

public interface MainContract {

    interface MainView{
        Application getMyApplication();
        void setAdapter(final List<UserDao.UserChat> userChatList);
        String getNewUserString();

    }

    interface MainPresenter{
        void makeUserList();
        void insertUser();
    }

    interface ChatView{
        Application getMyApplication();
        void setAdapter(final List<ChatEntity> chatEntityList);
        int getUserId();
        String getTextMessage();
    }

    interface ChatPresenter{
        void makeChatList();
        void insertChat();
    }
}
