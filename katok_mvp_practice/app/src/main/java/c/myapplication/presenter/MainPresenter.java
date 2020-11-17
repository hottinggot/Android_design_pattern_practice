package c.myapplication.presenter;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import java.util.List;

import c.myapplication.model.dao.UserDao;
import c.myapplication.model.repository.UserRepository;
import c.myapplication.view.MainContract;

public class MainPresenter implements MainContract.MainPresenter {

    private MainContract.MainView mainView;
    private UserRepository userRepository;

    public MainPresenter(MainContract.MainView mainView) {
        this.mainView = mainView;
    }

    private void setUserRepository() {
        userRepository = new UserRepository(mainView.getMyApplication());
    }

    @Override
    public void makeUserList() {
        setUserRepository();
        userRepository.findListInformation()
                .observe((LifecycleOwner) mainView.getMyApplication(), new Observer<List<UserDao.UserChat>>() {
                    @Override
                    public void onChanged(List<UserDao.UserChat> userChats) {
                        mainView.setAdapter(userChats);
                    }
                });
    }

    @Override
    public void insertUser() {
        setUserRepository();
        userRepository.insertUser(mainView.getNewUserString());
    }

}
