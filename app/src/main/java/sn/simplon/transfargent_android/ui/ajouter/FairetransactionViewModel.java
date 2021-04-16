package sn.simplon.transfargent_android.ui.ajouter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class FairetransactionViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public FairetransactionViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is gallery fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}