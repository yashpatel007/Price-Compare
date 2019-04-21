package com.example.yashpatel.livnews;

import android.Manifest;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class MainActivity extends AppCompatActivity {

    TextView tvresponce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvresponce= findViewById(R.id.tv1);


        RequestQueue queue = Volley.newRequestQueue(this);

        StringRequest request = new StringRequest("https://www.ebay.com/sch/i.html?_from=R40&_trksid=p2380057.m570.l1313.TR12.TRC2.A0.H0.Xwatches.TRS0&_nkw=watches&_sacat=0", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // when the request in queue executes succesfully
                Toast.makeText(MainActivity.this, "Request Successful", Toast.LENGTH_SHORT).show();
                tvresponce.setText(response);
                Document doc = Jsoup.parse(response);
                Elements itemElements = doc.getElementsByTag("li");
tvresponce.setText(itemElements.text());
                for(int i=0; i<itemElements.size();i++){
                    Element item = itemElements.get(i);
                    String title = item.getElementsByTag("h3").text();
                    String price = item.getElementsByTag("span").text();
                    Log.i("mytag",title+price);
                }



                Toast.makeText(MainActivity.this, "items found : "+itemElements.size(), Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // when  an errror happens with the queue request
                Toast.makeText(MainActivity.this, "Request failed", Toast.LENGTH_SHORT).show();
            }
        });

        // adding the request to queue
        Toast.makeText(MainActivity.this, "Request Sent. please wait", Toast.LENGTH_SHORT).show();
        queue.add(request);

    }
}
