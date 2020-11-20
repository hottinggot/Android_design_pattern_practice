package c.myapplication.katok_mvvm_practice.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import c.myapplication.katok_mvvm_practice.model.dao.UserDao;
import c.myapplication.katok_mvvm_practice.model.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {

    UserRepository userRepository;

    public UserViewModel(Application application){
        super(application);
        userRepository = new UserRepository(application);
    }

    public LiveData<List<UserDao.UserChat>> getAllUserChat(){
        return userRepository.findListInformation();
    }

    public void insertUser(int id, String name){
        userRepository.insertUser(id, name);
    }

}