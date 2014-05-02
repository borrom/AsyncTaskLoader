package com.melec.asynctaskloader.main;


import android.os.SystemClock;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;




public class MainActivity extends FragmentActivity implements LoaderManager.LoaderCallbacks<String> {

   MyAsyncTaskLoader loader;

    private final String HTTP_REQUEST_CODE = "requestCode";
    private final String LOGIN = "login";
    private final String USERNAME = "username";

   private TextView mTextView;
   private EditText usernameBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


            mTextView = (TextView) findViewById(R.id.mTextView);
            usernameBox = (EditText) findViewById(R.id.mEditText);


            Bundle args = new Bundle(0);
            getSupportLoaderManager().initLoader(0,args,this);




    }

    /**start asyncktaskloader*/
    public void mButton_clicked(View v){

        mTextView.setText(""); /**clear textView*/
        Bundle data = new Bundle(1);
        data.putString(HTTP_REQUEST_CODE,LOGIN);
        data.putString(USERNAME, usernameBox.getText().toString());

        Loader loader = getSupportLoaderManager().getLoader(0);
        if (loader != null){
            if(loader.isStarted()){
                loader.stopLoading();
            }
        }

       // getSupportLoaderManager().initLoader(0,args,this);
        getSupportLoaderManager().restartLoader(0, data, this);

    }


    @Override
    public Loader<String> onCreateLoader(int id, Bundle args){


        loader = new MyAsyncTaskLoader(getApplication(),args);
        loader.forceLoad();
        Log.d("onCreateLoader","called");

      return loader;
    }




    @Override
    public void onLoadFinished(Loader<String> loader, String data){
        Log.d("onLoadFinished", "called");

        SystemClock.sleep(1500);

        /**causes do inBackgournd to get called again*/
       // getSupportLoaderManager().getLoader(0).onContentChanged();

        /**update textView*/
        if (data != null) {
            mTextView.setText("Welcome, " + data + "!");
        }
    }



    @Override
    public  void onLoaderReset(Loader<String> loader){

        mTextView.setText("");
        Log.d("onLoaderReset","called");

    }




}
