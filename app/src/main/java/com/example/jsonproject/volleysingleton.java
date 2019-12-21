package com.example.jsonproject;

import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class volleysingleton {
    private static volleysingleton minstsnce;
    private RequestQueue mrequestqueue;

    public volleysingleton(Context context){
        mrequestqueue= Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized volleysingleton getInstance(Context context){
        if(minstsnce==null){
            minstsnce=new volleysingleton(context);
        }
        return minstsnce;
    }
    public RequestQueue getRequestQueue(){
        return mrequestqueue;
    }

}
