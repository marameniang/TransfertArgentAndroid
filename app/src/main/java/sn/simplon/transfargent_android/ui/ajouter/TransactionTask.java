package sn.simplon.transfargent_android.ui.ajouter;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Date;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sn.simplon.transfargent_android.R;

public class TransactionTask extends AsyncTask<String,Void,JSONArray> {

    final String Url = "http://192.168.1.2:8080/envoi/save";
    private Context context;
    private View view;

    public TransactionTask(Context context, View view){
        this.context = context;
        this.view = view;
    }
    @Override
    protected JSONArray doInBackground(String[] param) {

       Log.e("Params",param[1]+ ""+ param[2]);
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("montant",param[0])
                .add("emetteur",  param[1])
                .add("recepteur",param[2])
                .add("date", new Date().toString())
                .build();
        Request request = new Request.Builder()
                .url(Url)
                .method("POST",requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONArray(response.body().string());
            // Do something with the response.
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e){
            e.printStackTrace();
            return null;
        }

    }
    protected void onPostExecute(JSONArray response) {

        Toast toast = null;
        {
            if(response.length() > 0) {
                toast = Toast.makeText(context, "Transaction Reussie!", Toast.LENGTH_SHORT);

                //vider le formulaire
                EditText nomEmeteur = this.view.findViewById(R.id.nomEmetteur);
                EditText prenomEmeteur = this.view.findViewById(R.id.prenomEmetteur);
                EditText telEmeteur = this.view.findViewById(R.id.telEmetteur);
                EditText cniEmeteur = this.view.findViewById(R.id.cni);
                EditText nomRecepteur = this.view.findViewById(R.id.nomRecepteur);
                EditText  prenomRecepteur = this.view.findViewById(R.id.prenomRecepteur);
                EditText telRecepteur = this.view.findViewById(R.id.telRecepteur);
                EditText  montant = this.view.findViewById(R.id.montant);

                nomEmeteur.setText("");
                prenomEmeteur.setText("");
                telEmeteur.setText("");
                cniEmeteur.setText("");
                nomRecepteur.setText("");
                prenomRecepteur.setText("");
                telRecepteur.setText("");
                montant.setText("");


            }
            else
                toast = Toast.makeText(context,"erreur ", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


}

