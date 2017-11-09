package com.example.romeg.bcarev10;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by romeg on 06/11/2017.
 */

public class LoginRequest  extends StringRequest{
    private static final String SEARCH_REQUEST_URL="https://clones890.000webhostapp.com/search.php";
    private Map<String, String> params;
    public LoginRequest(String numexpediente, Response.Listener<String> listener)
    {
        super(Request.Method.POST, SEARCH_REQUEST_URL, listener, null);
        params = new HashMap<>();
        params.put("num_expediente",numexpediente);
    }

    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
