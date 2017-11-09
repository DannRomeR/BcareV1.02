package com.example.romeg.bcarev10;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by romeg on 06/11/2017.
 */

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_REQUEST_URL="https://clones890.000webhostapp.com/enviar.php";
    private Map<String, String> params;
    public RegisterRequest(String correo, String numexpediente, String nombre, String appat, String apmat, int edad, String genero,
                           String fumador, String diabetes, String colesterolTotal, String colesterolHDL, int BPsistolica,
                           int puntuacion, int porcentaje, Response.Listener<String> listener)
    {
        super(Method.POST, REGISTER_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("correo",correo);
        params.put("numexpediente",numexpediente);
        params.put("nombre",nombre);
        params.put("appat",appat);
        params.put("apmat",apmat);
        params.put("edad",edad+"");
        params.put("genero",genero);
        params.put("fumador",fumador);
        params.put("diabetes",diabetes);
        params.put("ColesterolTotal",colesterolTotal);
        params.put("ColesterolHDL",colesterolHDL);
        params.put("BPsistolica",BPsistolica+"");
        params.put("puntuacion",puntuacion+"");
        params.put("porcentaje",porcentaje+"");

    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
