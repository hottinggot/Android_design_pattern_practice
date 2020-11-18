package c.myapplication.contract;

import android.app.Application;
import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.myapplication.model.dao.UserDao;
import c.myapplication.model.entity.ChatEntity;

public interface MainContract {

    interface MainView{
        Application getMyApplication();
        int getLastUserId();
        String getNewUserString();
    }

    interface MainPresenter{
        LiveData<List<UserDao.UserChat>> makeUserList();
        void insertUser();
    }


}
