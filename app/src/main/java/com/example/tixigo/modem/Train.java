package com.example.tixigo.modem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Train {

    private final String Tname;
    private final  String Tnumber;

    public Train(JSONObject jsonObject) throws JSONException {
        this.Tname=jsonObject.getString("name");
        this.Tnumber=jsonObject.getString("train_num");
    }

    public String getTname() {
        return Tname;
    }

    public String getTnumber() {
        return Tnumber;
    }

    @Override
    public String toString() {
        return "Train{" +
                "name='" + Tname + '\'' +
                ", number='" + Tnumber + '\'' +
                '}';
    }
}
