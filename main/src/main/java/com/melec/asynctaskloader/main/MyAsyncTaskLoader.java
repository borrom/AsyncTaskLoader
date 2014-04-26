package com.melec.asynctaskloader.main;


import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;


public class MyAsyncTaskLoader extends AsyncTaskLoader<String> {

    /**constructor*/
    public MyAsyncTaskLoader (Context context){
        super(context);
    }


    public String loadInBackground(){

        SystemClock.sleep(2000);
        Log.d("loadInBackground","called");

        /**simulate a long running task*/
        SystemClock.sleep(5000); /** three seconds */


        String result = "loadInBackground Result";



        return  result;


    }

    @Override
    public void onStartLoading(){
        super.onStartLoading();
        SystemClock.sleep(1000);
        Log.d("onStartLoading","called");
    }


    @Override
    public void deliverResult(String arg0){
        SystemClock.sleep(1000);
        Log.d("deliverResult","called");

        if(isReset()){
            Log.d("loader","isReset()");
            

        }

        super.deliverResult(arg0);


    }

    @Override
    public void onStopLoading(){
        super.onStopLoading();
        cancelLoad();
        Log.d("onStopLoading","called");
    }


    @Override
    public void onReset(){
        super.onReset();
        onStopLoading();

        Log.d("onReset","called");

    }

}
