package com.abcdroid.kommmida.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class ComidasActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_comida);

        ArrayList<Comida> arr = getComidas();
        if(arr!=null){
            ComidaAdapter adapter = new ComidaAdapter (this, arr);
            ListView listView = (ListView)findViewById(R.id.listViewComida);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                @Override
                public void onItemClick(AdapterView<?> parent, final View view, int position, long id) {
                    final String item = (String) parent.getItemAtPosition(position);

                }
            });
        }else{
            Util.dialog("No hay comida disponible", this);
        }
    }

    public ArrayList<Comida> getComidas(){
        ArrayList<Comida> lista = new ArrayList<Comida>();

        String result = "";
        try {
            HttpClient httpclient = new DefaultHttpClient();
            HttpResponse httpResponse = httpclient.execute(new HttpGet(MainActivity.urlBackend + "/restaurantes"));

            InputStream inputStream = null;
            inputStream = httpResponse.getEntity().getContent();

            if (inputStream != null)
                result = convertInputStreamToString(inputStream);
                JSONArray arr = new JSONArray(result);

                for (int i=0; i<arr.length(); i++){
                    JSONObject o = arr.getJSONObject(i);
                    Long id = o.getLong("id");
                    String nombre = o.getString("nombre");
                    String direccion = o.getString("direccion");
                    String telefono = o.getString("telefono");

                    Comida c = new Comida(id, nombre, direccion, telefono);
                    lista.add(c);
                }


        }catch (Exception e){
            e.printStackTrace();
        }
        return lista;
    }

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader( new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while((line = bufferedReader.readLine()) != null)
            result += line;

        inputStream.close();
        return result;

    }

    public class ComidaAdapter extends ArrayAdapter<Comida> {
        List<Comida> lstComida = new ArrayList<Comida>();
        public ComidaAdapter(Context context, ArrayList<Comida> comida) {
            super(context, 0, comida);
            for (Comida c: comida) {
                lstComida.add(c);
            }
        }

        @Override
        public long getItemId(int position) {
            Comida c = getItem(position);
            return c.getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            Comida com = getItem(position);
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_comida, parent, false);
            }
            TextView tvName = (TextView) convertView.findViewById(R.id.LblTitulo);
            TextView tvHome = (TextView) convertView.findViewById(R.id.LblSubTitulo);
            tvName.setText(com.nombre);
            tvHome.setText(com.descripcion);
            return convertView;
        }


    }
}
