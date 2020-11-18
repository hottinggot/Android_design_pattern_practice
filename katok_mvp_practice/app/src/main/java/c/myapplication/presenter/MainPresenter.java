package c.myapplication.presenter;

import android.content.Context;

import androidx.lifecycle.LiveData;

import java.util.List;

import c.myapplication.model.dao.UserDao;
import c.myapplication.model.repository.UserRepository;
import c.myapplication.contract.MainContract;

public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView mainView;
    private UserRepository userRepository;

    private Context context;

    public MainPresenter(MainContract.MainView mainView, Context context) {
        this.mainView = mainView;
        this.context = context;
    }

    private void setUserRepository() {
        userRepository = new UserRepository(mainView.getMyApplication());
    }

    @Override
    public LiveData<List<UserDao.UserChat>> makeUserList() {
        setUserRepository();
        return userRepository.findListInformation();
    }

//    @Override
//    public void setUserList(List<UserDao.UserChat> userChats){
//        mainView.setAdapter(userChats);
//    }

    @Override
    public void insertUser() {
        if(mainView.getNewUserString().length()!=0){
            setUserRepository();
            userRepository.insertUser(mainView.getLastUserId(), mainView.getNewUserString());
        }
    }

}
