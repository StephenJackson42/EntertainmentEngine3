package com.stephenjackson.entertainmentengine;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import static com.stephenjackson.entertainmentengine.SearchActivity.entry;

public class ResultsActivity extends AppCompatActivity {
    private TextView tView;
    private TextView resultOne;
    public static JSONArray jArray;
    public static String resultString;
    public static String obtainedId;
    public static String search;
    public static String baseUrl;
    public static boolean ranRequest = false;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        tView = (TextView) findViewById(R.id.textView);
        tView.setText(entry);
        resultOne = (TextView) findViewById(R.id.result1);
        resultOne.setText("");
        //String string = null;
        try {
            results(entry);
        } catch (JSONException e) {
            e.printStackTrace();
            System.out.println(e);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        //System.out.println(string);
        //handleJSON();
    }

    protected String results(String query) throws JSONException, UnsupportedEncodingException {
        /* Clear results */
        resultOne.setText(" Searching for subscription sources... ");
        /* Create a Request Queue */
        final MySingleton mySingleton = MySingleton.getInstance(this);
        baseUrl = "http://api-public.guidebox.com/v1.43/US/rKIvHF0soparXENAMNBTfMrOBlJusc49/";
        query = URLEncoder.encode(query, "UTF-8");
        //System.out.println(query);
        search = baseUrl + "search/movie/title/" + query + "/exact";
        //System.out.println(search);
        System.out.println("Obtained ID should be null: " + obtainedId);
        System.out.println("Result String should be null or empty: " + resultString);
        System.out.println("Searching for JSON: " + search);

        /* Get ID from movie search */
        final JsonObjectRequest[] movieInfoRequest = new JsonObjectRequest[1];
        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, search, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        System.out.println("Obtained response for search " + search);
                        try {
                            //System.out.println("First request to obtain query results.");
                            JSONArray responseArray = response.getJSONArray("results");
                            response = responseArray.getJSONObject(0);
                            obtainedId = response.getString("id");
                            ranRequest = true;

                            if (obtainedId != null){
                                System.out.println("Obtained an ID " + obtainedId);
                                /* Get Movie Information from Obtained ID */
                                search = baseUrl + "movie/" + obtainedId;
                                movieInfoRequest[0] = new JsonObjectRequest
                                        (Request.Method.GET, search, null, new Response.Listener<JSONObject>() {

                                            @Override
                                            public void onResponse(JSONObject response) {
                                                System.out.println("Performing movie info request for " + search);
                                                try {

                                                    jArray = response.getJSONArray("subscription_web_sources");

                                                    resultString = "";

                                                    for (int i = 0; i < jArray.length(); i++){
                                                        JSONObject temp = jArray.getJSONObject(i);
                                                        //System.out.println(temp.toString(3));
                                                        resultString += temp.getString("display_name") + "\n";
                                                    }

                                                    if (resultString == ""){
                                                        resultOne.setText("No subscription sources found for " + entry);
                                                    } else {
                                                        resultOne.setText(resultString);
                                                        resultString = "";
                                                    }

                                                } catch (JSONException e) {
                                                    e.printStackTrace();
                                                }
                                            }
                                        }, new Response.ErrorListener() {

                                            @Override
                                            public void onErrorResponse(VolleyError error) {
                                                // TODO Auto-generated method stub
                                                System.out.println("Error dude when getting them sources.");
                                                error.printStackTrace();
                                                System.out.println(error);

                                            }
                                        });
                                mySingleton.addToRequestQueue(movieInfoRequest[0]);
                            } else {
                                resultOne.setText("No matches found for " + entry);
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        System.out.println("Error dude obtaining search results.");
                        error.printStackTrace();
                        System.out.println(error);

                    }
                });

        resultOne.setText(" ");
        mySingleton.addToRequestQueue(jsObjRequest);

        return null;
    }
}
