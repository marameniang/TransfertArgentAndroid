package sn.simplon.transfargent_android.ui.lister;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import sn.simplon.transfargent_android.R;

public class ListeTransactionFragment extends Fragment {

    private ListerViewModel listeTransactionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        listeTransactionViewModel =
                ViewModelProviders.of(this).get(ListerViewModel.class);
        View root = inflater.inflate(R.layout.fragment_lister, container, false);

        return root;
    }
}