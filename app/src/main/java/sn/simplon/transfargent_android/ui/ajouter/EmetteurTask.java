package sn.simplon.transfargent_android.ui.ajouter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import sn.simplon.transfargent_android.R;

public class EmetteurTask extends AsyncTask<String,Void, JSONArray> {

    final String Url = "http://192.168.1.2:8080/emetteur/save";
    private Context context;
    private View view;
    public EmetteurTask(Context context, View view){
        this.context = context;
        this.view = view;
    }
    @Override
    protected JSONArray doInBackground(String[] params) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("nom", params[0])
                .add("prenom",params[1])
                .add("telephone",params[2])
                .add("cni",params[3])
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
        if(response.length() > 0) {
           // toast = Toast.makeText(context, "Emetteur enregistré!   &", Toast.LENGTH_SHORT);

            //recuperer id emetteur saisi
            EditText idEmrteur = view.findViewById(R.id.idemetteur);
            try {
                int index = (response.length() -1);
                JSONObject lastElement = response.getJSONObject(index);
                idEmrteur.setText(lastElement.getString("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            TransactionTask transactionTask = new TransactionTask(this.context, this.view);

            EditText montantEditext = (EditText) this.view.findViewById(R.id.montant);
            String montantTrans = montantEditext.getText().toString();

            EditText idEmetteur = (EditText) this.view.findViewById(R.id.idemetteur);
            String idEm = idEmetteur.getText().toString();

            EditText idRecepteur = (EditText) this.view.findViewById(R.id.idrecepteur);
            String idRecp = idRecepteur.getText().toString();

            transactionTask.execute(montantTrans, idEm, idRecp);
        }


    }
}
