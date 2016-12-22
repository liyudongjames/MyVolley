package com.liyudong.home.myvolley;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private RequestQueue requestQueue;
    private String url = "http://118.178.142.34/YiQiHouse/HomeAD";
    private TextView textView;
    private String postUrl = "http://hui.17house.com/svc/payment-facade/housekeep/listBuildingSiteTrackByProgress";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = (TextView) findViewById(R.id.text);
        requestQueue = Volley.newRequestQueue(this);
//        startVolley();
        startVolleyPost();
    }

    private void startVolleyPost(){
        MyResponse request = new MyResponse(Request.Method.POST, url, new Response.Listener() {
            @Override
            public void onResponse(Object response) {
                textView.setText((String) response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("progressId", "1");
                params.put("app_version", "android_com.aiyiqi.galaxy_1.1");
                params.put("buildingId", "1");
                params.put("page", "1");
                params.put("pageSize","10");
//                progressId： 进度Id，此处为1
//                app_version : android_com.aiyiqi.galaxy_1.1（固定参数）
//                buildingId :  建筑id（装修公司页面返回的）
//                page : 页数
//                pageSize： 一页的数据条数
                return params;
            }
        };
    }

    private void startVolley(){
        MyResponse request = new MyResponse(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                textView.setText(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(request);
    }
}
