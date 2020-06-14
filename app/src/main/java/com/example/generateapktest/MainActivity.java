package com.example.generateapktest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    // https://jsonplaceholder.typicode.com/todos/1
    // private RequestQueue mRequestQueue;      // RequestQueue field

    RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mQueue = MySingleton.getInstance(this.getApplicationContext()).getRequestQueue();

        /**
        Volley is a networking library for Android that manages network requests.
        */

      //  mRequestQueue = Volley.newRequestQueue(this);  // instantiate RequestQueue

        //Create JSON Request with request type.
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos/1", null,
                new Response.Listener<JSONObject>() {  // response listener
                    /**
                     * Called when a response is received.
                     *
                     * @param response
                     */
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            Log.d("JSON", "onResponse: " + response.getString("title"));  // Log response , get string element
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


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
                Log.d("Error", "onErrorResponse: " + error.getMessage());
            }
        });


        // Making a JsonArrayRequest:

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET,
                "https://jsonplaceholder.typicode.com/todos", null, new Response.Listener<JSONArray>() {
            /**
             * Called when a response is received.
             *
             * @param response
             */
            @Override
            public void onResponse(JSONArray response) {   // array value stored in response.

                /* Loop through array to get string elements*/
                for (int i = 0; i < response.length(); i++) {

                    try {
                        JSONObject jsonObject = response.getJSONObject(i);  // create jsonObject to hold response index

                        Log.d("JsonArray", "onResponse: "
                                + jsonObject.getString("id")    //get string element of jsonObject
                                + jsonObject.getString("title"));

                        boolean d = jsonObject.getBoolean("completed");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

            }

            /**
             * Called when a response is received.
             *
             * @param response
             */


            }, new Response.ErrorListener() {
            /**
             * Callback method that an error has been occurred with the provided error code and optional
             * user-readable message.
             *
             * @param error
             */
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mQueue.add(jsonObjectRequest);

        //JsonArrayRequest

       // mRequestQueue.add(jsonArrayRequest);
        //mRequestQueue.add(jsonObjectRequest); // add jsonObjectRequest on requestQueue
    }

}
