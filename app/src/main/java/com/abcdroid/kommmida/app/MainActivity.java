package com.abcdroid.kommmida.app;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View.OnClickListener;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class MainActivity extends ActionBarActivity implements OnClickListener {

    public static final String urlBackend = "https://kommmida.herokuapp.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        Button btnLogin = (Button)findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v){
        Toast.makeText(getApplicationContext(), "Hola", Toast.LENGTH_SHORT).show();

        EditText txtUsername = (EditText)findViewById(R.id.username);
        EditText txtPassword = (EditText)findViewById(R.id.password);

        HttpClient httpClient = new DefaultHttpClient();

        HttpPost post = new HttpPost(urlBackend+"/user/login");
        post.setHeader("content-type", "application/json");

        try{
            //Construimos el objeto cliente en formato JSON
            JSONObject dato = new JSONObject();
            dato.put("email", txtUsername.getText().toString());
            dato.put("clave", txtPassword.getText().toString());

            StringEntity entity = new StringEntity(dato.toString());
            post.setEntity(entity);

            HttpResponse resp = httpClient.execute(post);
            String respStr = EntityUtils.toString(resp.getEntity());

            if(respStr.equals("true")){
                Intent i = new Intent(this, ComidasActivity.class);
                startActivity(i);
            }else{
                Util.dialog(respStr, this);
            }

        }
        catch(Exception ex)
        {
            Log.e("ServicioRest", "Error!", ex);
        }

    }


}
