package com.example.newsrecent.newsrecent;

import android.content.Context;

import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.app.LoaderManager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Loader;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class Main2Activity extends AppCompatActivity implements android.app.LoaderManager.LoaderCallbacks<List<Newsinfo>> {

    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;
    private static final int NEWS_LOADER_ID = 1;
    private int count = 0;
    private static final String NewsApIRequestURL="https://newsapi.org/v2/top-headlines?country=in&category=business&apiKey=1409bbb2b3844910bdd62571fc21c5f0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitymain);

        ListView NewsView = (ListView) findViewById(R.id.list);
        mAdapter = new NewsAdapter(this,new ArrayList<Newsinfo>());

        NewsView.setAdapter(mAdapter);

        mEmptyStateTextView = (TextView)findViewById(R.id.empty_view);
        NewsView.setEmptyView(mEmptyStateTextView);

        ConnectivityManager connectivityManager = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if(networkInfo !=null &&networkInfo.isConnected()){
            android.app.LoaderManager loaderManager = getLoaderManager();

            loaderManager.initLoader(NEWS_LOADER_ID,null, this);
        }
        else{
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText("no_internet_connection");
            //EmptyTextView should be initialised Showing no Connection or Error in connection

        }

    }

//    @Override
//    public void onBackPressed() {
//        new AlertDialog.Builder(this).setMessage("Are you sure you want to Exit?")
//                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        finish();
//                    }
//                })
//                .setNegativeButton("No",null).show();
//
//
//    }


    @Override
    public void onBackPressed() {
        count++;
        Toast toast = new Toast(this);

        if(count==2)
            super.onBackPressed();
        else
            toast.makeText(this,"Press once more to Exit",Toast.LENGTH_SHORT).show();
    }

    public android.content.Loader onCreateLoader(int i, Bundle bundle){
        NewsLoader a = new NewsLoader(this,NewsApIRequestURL);
        return a;
    }



    public void onLoadFinished(Loader<List<Newsinfo>> newsInfoLoader, List<Newsinfo> newsinfoList){
            mAdapter.clear();
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);
            if(newsinfoList != null && !newsinfoList.isEmpty()){
                mAdapter.addAll(newsinfoList);
            }
        mEmptyStateTextView.setText("NO NEWS FOUND");
    }

    public void onLoaderReset(Loader<List<Newsinfo>> newsLoader){

        mAdapter.clear();

    }

}
