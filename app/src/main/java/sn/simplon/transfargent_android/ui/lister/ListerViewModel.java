package sn.simplon.transfargent_android.ui.lister;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ListerViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ListerViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is home fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}