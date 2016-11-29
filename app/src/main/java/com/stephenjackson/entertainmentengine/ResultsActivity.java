package com.stephenjackson.entertainmentengine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import static com.stephenjackson.entertainmentengine.SearchActivity.entry;

public class ResultsActivity extends AppCompatActivity {
    private TextView tView;
    private TextView resultOne;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        tView = (TextView) findViewById(R.id.textView);
        tView.setText(entry);
        resultOne = (TextView) findViewById(R.id.result1);
        resultOne.setText("Suh dude");
        //String string = null;
        try {
            System.out.println(results());
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println(e);
        }

        //System.out.println(string);
        //handleJSON();
    }

    protected String results() throws JSONException{
        String url = "http://api-public.guidebox.com/v1.43/US/rKIvHF0soparXENAMNBTfMrOBlJusc49/movie/50362";

        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Obtained response");
                        System.out.println("Response: " + response.toString());
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("Error dude");
                        error.printStackTrace();
                        System.out.println(error);

                    }
                });

        MySingleton.getInstance(this).addToRequestQueue(jsObjRequest);
        return url;
    }
}
