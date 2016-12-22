package com.liyudong.home.myvolley;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.HttpHeaderParser;

/**
 * Created by Administrator on 2016/12/22.
 */

public class MyResponse extends Request<String> {

    private Response.Listener mlistner;

    public MyResponse(int method, String url,  Response.Listener mlistner,Response.ErrorListener listener) {
        super(method, url, listener);
        this.mlistner = mlistner;
    }

    @Override
    protected Response<String> parseNetworkResponse(NetworkResponse response) {
        String request = new String(response.data);
        return Response.success(request, HttpHeaderParser.parseCacheHeaders(response));
    }

    @Override
    protected void deliverResponse(String response) {
        mlistner.onResponse(response);
    }
}
