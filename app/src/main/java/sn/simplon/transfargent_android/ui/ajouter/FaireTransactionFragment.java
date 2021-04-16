package sn.simplon.transfargent_android.ui.ajouter;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import sn.simplon.transfargent_android.R;

public class FaireTransactionFragment extends Fragment {
    private EditText nomEmeteur;
    private  EditText prenomEmeteur;
    private  EditText telEmeteur;
    private  EditText cniEmeteur;
    private  EditText nomRecepteur;
    private  EditText prenomRecepteur;
    private  EditText telRecepteur;
    private  EditText montant;
    private Button enregistrer;

    private FairetransactionViewModel ajoutTransactionViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ajoutTransactionViewModel =
                ViewModelProviders.of(this).get(FairetransactionViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_ajouter, container, false);

        nomEmeteur = root.findViewById(R.id.nomEmetteur);
        prenomEmeteur = root.findViewById(R.id.prenomEmetteur);
        telEmeteur = root.findViewById(R.id.telEmetteur);
        cniEmeteur = root.findViewById(R.id.cni);
        nomRecepteur = root.findViewById(R.id.nomRecepteur);
        prenomRecepteur = root.findViewById(R.id.prenomRecepteur);
        telRecepteur = root.findViewById(R.id.telRecepteur);
        montant = root.findViewById(R.id.montant);

        root.findViewById(R.id.btnEnvoyer).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String nomRecepteurchaine = nomRecepteur.getText().toString();
                String prenomRecepteurchaine = prenomRecepteur.getText().toString();
                String telRecepteurchaine = telRecepteur.getText().toString();

              /*  EmetteurTask emetteurTask = new EmetteurTask(getActivity());
                emetteurTask.execute(nomEmmeteur, prenomEmetteur, telEmetteur, cni);*/

                RecepteurTask recepteurTask = new RecepteurTask(getActivity(), root);
                recepteurTask.execute(nomRecepteurchaine, prenomRecepteurchaine,telRecepteurchaine);

              /*  TransactionTask transactionTask = new TransactionTask(getActivity());
                transactionTask.execute(montanttrans);*/
            }
        });
        return root;
    }
}