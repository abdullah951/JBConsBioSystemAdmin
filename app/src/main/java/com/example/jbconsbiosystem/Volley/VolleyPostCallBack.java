package com.example.jbconsbiosystem.Volley;

import org.json.JSONObject;

public interface VolleyPostCallBack {
    void OnSuccess(JSONObject jsonObject) ;
    void OnFailure(String err);
}
