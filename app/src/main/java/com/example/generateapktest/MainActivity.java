package com.example.generateapktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // https://jsonplaceholder.typicode.com/todos/1


    private RequestQueue mRequestQueue;  // RequestQueue
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRequestQueue = Volley.newRequestQueue(this);  // instantiate RequestQueue

        //Create JSON objectRequest.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos/1", null,
                new Response.Listener<JSONObject>() {
                    /**
                     * Called when a response is received.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        Log.d("JSON", "onResponse: " + response);



                    }


        }, new Response.ErrorListener() {
            /**
             * Callback method that an error has been occurred with the provided error code and optional
             * user-readable message.
             *
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("error", "onErrorResponse: " + error.getMessage());
            }
        });
        mRequestQueue.add(jsonObjectRequest); // call requestQueue on end of
    }
}
