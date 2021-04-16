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

public class RecepteurTask  extends AsyncTask<String,Void, JSONArray> {

    final String Url = "http://192.168.1.2:8080/recepteur/save";
    private Context context;
    private View view;

    public RecepteurTask(Context context, View view) {
        this.context = context;
        this.view = view;
    }


    @Override
    protected JSONArray doInBackground(String[] params) {
        OkHttpClient client = new OkHttpClient();
        RequestBody requestBody = new FormBody.Builder()
                .add("nom", params[0])
                .add("prenom", params[1])
                .add("telephone", params[2])
                .build();
        Request request = new Request.Builder()
                .url(Url)
                .method("POST", requestBody)
                .build();
        try {
            Response response = client.newCall(request).execute();
            return new JSONArray(response.body().string());
            // Do something with the response.
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }

    }

    protected void onPostExecute(JSONArray response) {
        if (response.length() > 0)
        {
            //toast = Toast.makeText(context, "Recepteur enregistré!", Toast.LENGTH_SHORT);
            //recuperer id dernier recepteur enregistre
            EditText idrecepteur = view.findViewById(R.id.idrecepteur);
            try {
                int index = (response.length() -1);
                JSONObject lastElement = response.getJSONObject(index);
                idrecepteur.setText(lastElement.getString("id"));
            } catch (JSONException e) {
                e.printStackTrace();
            }


          EmetteurTask emetteurTask = new EmetteurTask(this.context, this.view);

          EditText nomEmetteurEdittext = (EditText) this.view.findViewById(R.id.nomEmetteur);
          String nomEmmeteur = nomEmetteurEdittext.getText().toString();

          EditText prenomEmetteurEdittext = (EditText) this.view.findViewById(R.id.prenomEmetteur);
          String prenomEmetteur = prenomEmetteurEdittext.getText().toString();

          EditText telEmeteurEditText = (EditText) this.view.findViewById(R.id.telEmetteur);
          String telEmetteur = telEmeteurEditText.getText().toString();

          EditText cniEmeteurEditText = (EditText) this.view.findViewById(R.id.cni);
          String cni = cniEmeteurEditText.getText().toString();

          emetteurTask.execute(nomEmmeteur, prenomEmetteur, telEmetteur, cni);
        }


    }
}
