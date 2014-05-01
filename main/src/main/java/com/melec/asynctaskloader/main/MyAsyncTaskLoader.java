package com.melec.asynctaskloader.main;


import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;


public class MyAsyncTaskLoader extends AsyncTaskLoader<String> {

    private int count = 0;
    private String args;
    /**constructor*/
    public MyAsyncTaskLoader (Context context, String args){
        super(context);
        this.args = args;
    }


    @Override
    public void onStartLoading(){
        super.onStartLoading();
        Log.d("onStartLoading","called");
        SystemClock.sleep(1000);

    }



    public String loadInBackground(){
        Log.d("loadInBackground","called");


        /**simulate a long running task*/
        SystemClock.sleep(10000); /** three seconds */

        count++;
      //  String result = "Count= " + Integer.toString(count);
        String result = args;


        Log.d("loadInBackground",result);
        return  result;


    }




    @Override
    public void deliverResult(String arg0){
        Log.d("deliverResult","called");




        if(isReset()){
            /**An async query came in while the loader is stopped. We don't need the result*/

            Log.d("deliverResult","loader isReset()");

        /** if(apps != null){
         onReleaseResources(apps);
         }*/
     }

        if(isStarted()){
         /** If the loader is currently started, we can immediately deliver a result*/
        /**  super.deliverResult(apps);*/
         Log.d("deliverResult","loader isStarted");

         super.deliverResult(arg0);

      }


        /** At this point we can release the resources associated with 'oldApps' if needed;
         now that the new result is delivered we know that it is no longer in use*/
       /** if(oldApps != null){
            onReleaseResources(oldApps);
        }  */
    }

    @Override
    public void onStopLoading(){
        super.onStopLoading();
        /**Attempts to cancel the current load task if possible*/
        cancelLoad();
        Log.d("onStopLoading","called");
    }


    @Override
    public void onCanceled(String arg0) {
        super.onCanceled(arg0);

        /** At this point we can release the resources associated with 'apps' if needed
         *
         */
      /**  onReleaseResources(arg0);*/
    }


    /**
     * Handles request to completely reset the loader
     */

    @Override
    public void onReset(){
        super.onReset();
        /**ensure the loader is stopped*/
        onStopLoading();

        /**At this point we can release the resources associated with 'apps' if needed*/
       /**
        if(apps != null){
            onReleaseResources(apps);
            apps = null;
        }*/

        Log.d("onReset","called");
    }


    /**
     * Helper function to take care of releasing resources associated with an actively loaded data set
     */
    private void onReleaseResources(String arg0){
        // For a simple list there is nothing to do
        // but for a Cursor we would close it here
    }


}
