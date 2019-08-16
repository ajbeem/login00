package com.pcentaury.pcentaurylogin00;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class register extends AppCompatActivity {
    private EditText cmpUser, cmpMail, cmpPswd;
    private Button sendData;
    private RequestQueue requestQueue;
    private String urlRegistro = "http://192.168.1.71/login_server/php/altaUsuariosJSON.php?";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        cmpUser = (EditText)findViewById(R.id.etRegisterNuser);
        cmpMail = (EditText)findViewById(R.id.etRegisterUserMail);
        cmpPswd = (EditText)findViewById(R.id.etRegisterPswd);

        sendData = (Button)findViewById(R.id.btnRegisterSendData);
        sendData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    private void register() {
        if(Services.NetworkConnection(this)){
            //TO-DO
            if(cmpUser.getText().toString().isEmpty() || cmpMail.getText().toString().isEmpty() || cmpPswd.getText().toString().isEmpty()){
                Toast.makeText(getApplicationContext(), "Por favor llena todos los campos \n y vuelve a intentarlo!!", Toast.LENGTH_LONG).show();
            }else{
                jsonRegisterRequest(cmpUser.getText().toString(), cmpMail.getText().toString(), cmpPswd.getText().toString());
            }

        }else {
            Toast.makeText(getApplicationContext(), "Verifica tu conexión a internet e intentalo nuevamente", Toast.LENGTH_LONG).show();
        }
    }

    private void jsonRegisterRequest(String user , String mail, String password) {
        requestQueue = Volley.newRequestQueue(this);
        JSONObject jsonparams = new JSONObject();
//nUsuario,  password, correoElectronicoUsuario, usuarioAdministrador
        try {
            jsonparams.put("nUsuario", user);
            jsonparams.put("correoElectronicoUsuario", mail);
            jsonparams.put("password", password);
            jsonparams.put("usuarioAdministrador", "AndroidUser");
        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(),"Excepcion capturada: \n"+ e.getMessage(),Toast.LENGTH_LONG ).show();
            //e.printStackTrace();
        }
        Toast savedToast = Toast.makeText(getApplicationContext(),
                "Enviando datos", Toast.LENGTH_SHORT);
        savedToast.show();
    }
}
