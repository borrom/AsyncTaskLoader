package com.melec.asynctaskloader.main;


import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;




public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<String> {

    MyAsyncTaskLoader loader;

    TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


        mTextView = (TextView) findViewById(R.id.mTextView);



    }

    /**start asyncktaskloader*/
    public void mButton_clicked(View v){

        mTextView.setText(""); /**clear textView*/
        Bundle args = new Bundle(1);
       // getSupportLoaderManager().initLoader(0,args,this);
        getSupportLoaderManager().restartLoader(0,args,this);

    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args){


        loader = new MyAsyncTaskLoader(getApplication(),"test");
        loader.forceLoad();
        Log.d("onCreateLoader","called");

      return loader;
    }




    @Override
    public void onLoadFinished(Loader<String> loader, String data){
        Log.d("onLoadFinished","called");

        /**causes do inBackgournd to get called again*/
       // getSupportLoaderManager().getLoader(0).onContentChanged();

        /**update textView*/
        mTextView.setText(data);
    }


    @Override
    public  void onLoaderReset(Loader<String> loader){

        mTextView.setText("");
        Log.d("onLoaderReset","called");

    }




}
