package com.example.app;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;
import org.json.JSONTokener;

/**
 * Created by vonym on 17-3-11.
 */

public class JsonActivity extends AppCompatActivity {
    private TextView textView;
    private String jsonData = "{'姓名':'王小二'}";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.json_main);
        textView = (TextView) findViewById(R.id.tv);

    }

    public void jiexi(View view) throws Exception {
//        JSONTokener jsonTokener=new JSONTokener(jsonData);
//        JSONObject jsonObject= (JSONObject) jsonTokener.nextValue();
        StringBuffer stringBuffer = new StringBuffer();
        JSONObject jsonObject = new JSONObject(jsonData);
        stringBuffer.append(jsonObject.getString("姓名"));
        textView.setText(stringBuffer.toString());
    }

    public void goujian(View view) throws JSONException {
        /*JSONObject jsonObject=new JSONObject();
        try {
            jsonObject.put("姓名","王小二");
        } catch (JSONException e) {
            e.printStackTrace();
        }*/


        JSONStringer jsonStringer = new JSONStringer();
        jsonStringer.object();
        jsonStringer.key("姓名");
        jsonStringer.value("王小二");
        jsonStringer.endObject();
    }
}
