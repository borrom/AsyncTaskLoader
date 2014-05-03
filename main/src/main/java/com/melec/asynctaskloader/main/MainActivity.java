package com.melec.asynctaskloader.main;


import android.app.ProgressDialog;
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
   private ProgressDialog mPDialog;
   private final String IS_PDIALOG_KEY = "is_pdialog";
   private boolean IS_PDIALOG_SHOWING;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity_layout);


            mTextView = (TextView) findViewById(R.id.mTextView);
            usernameBox = (EditText) findViewById(R.id.mEditText);
            /**instantiate mPDialog*/
            mPDialog = new ProgressDialog(this);


        if(savedInstanceState != null) {
            /**if we are been restored, init loader*/
            getSupportLoaderManager().initLoader(0, null, this);

            /**show dialog, if it was previously showing*/
            IS_PDIALOG_SHOWING = savedInstanceState.getBoolean(IS_PDIALOG_KEY);

            if(IS_PDIALOG_SHOWING){
                showDialog();
            }
         }
    }

    /**Save mPDialog state*/
    @Override
    protected  void onSaveInstanceState(Bundle saveInstanceState){
        super.onRestoreInstanceState(saveInstanceState);
        if(mPDialog.isShowing()){
            saveInstanceState.putBoolean(IS_PDIALOG_KEY,IS_PDIALOG_SHOWING);
       }
    }


    @Override
    protected void onStop(){
        super.onStop();
        hideDialog();
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

        /**show progress dialog*/
        showDialog();

        /**restart loader*/
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


        /**causes do inBackgournd to get called again*/
       // getSupportLoaderManager().getLoader(0).onContentChanged();

        /**update textView*/
        if (data != null) {
            mTextView.setText("Welcome, " + data + "!");
        }

        /**destroy loader*/
       // getSupportLoaderManager().destroyLoader(0);
        hideDialog();
    }

    /**create progress dialog*/
    private void showDialog(){
        String message = getResources().getString(R.string.loading);

        mPDialog.setMessage(message);
        mPDialog.setCancelable(false);
        mPDialog.show();

        IS_PDIALOG_SHOWING = true;

    }

    private void hideDialog(){
        if(mPDialog.isShowing()){
            mPDialog.dismiss();
            IS_PDIALOG_SHOWING = false;
        }
    }

    @Override
    public  void onLoaderReset(Loader<String> loader){

        Log.d("onLoaderReset","called");

    }




}
