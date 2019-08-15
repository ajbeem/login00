package com.pcentaury.pcentaurylogin00;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    EditText userName, pass;
    Button registrarNuevousuario;
    private RequestQueue queue;
    String urlLogin="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        userName = (EditText)findViewById(R.id.etNuser);
        pass = (EditText)findViewById(R.id.etPswd);
        registrarNuevousuario= (Button)findViewById(R.id.btnMainRegister);
        registrarNuevousuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startUserReg();
            }
        });

        FloatingActionButton fab = findViewById(R.id.fabLogin);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Login", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                login();
            }
        });
    }

    private void startUserReg() {
        Intent goN = new Intent(this, register.class);
        startActivity(goN);
        this.finish();
    }

    private void login() {
        if(Services.NetworkConnection(this)){
            //TO-DO
            if(userName.getText().toString().isEmpty() || pass.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Por favor llena todos los campos \n y vuelve a intentarlo!!", Toast.LENGTH_LONG).show();
            }else{
                jsonRequest(userName.getText().toString(), pass.getText().toString());
            }

        }else {
            Toast.makeText(getApplicationContext(), "Verifica tu conexión a internet e intentalo nuevamente", Toast.LENGTH_LONG).show();
        }
    }

    private void jsonRequest(String usuario, String pasword) {
        queue = Volley.newRequestQueue(this);

        JSONObject jsonparams = new JSONObject();

        try {
            jsonparams.put("nUsuario", usuario);
            jsonparams.put("pswd", pasword);
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),"Excepcion capturada: \n"+ e.getMessage(),Toast.LENGTH_LONG ).show();
            //e.printStackTrace();
        }

        Toast savedToast = Toast.makeText(getApplicationContext(),
                "Enviando datos", Toast.LENGTH_SHORT);
        savedToast.show();

        /*JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, urlLogin, jsonparams, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.getString("answer").equalsIgnoreCase("ok")) {
                                Toast.makeText(getApplicationContext(), "Usuario Autenticado", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "" + response.getString("answer"), Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            Toast unsavedToast = Toast.makeText(getApplicationContext(),
                                    "Error Al recibir los Datos, intentalo nuevamente" + e.getMessage(), Toast.LENGTH_LONG);
                            unsavedToast.show();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO: Handle error
                        Toast.makeText(getApplicationContext(), "La comunicación con el servicio ha fallado Intentalo nuevamente" + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
        queue.add(jsonObjectRequest);*/


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
