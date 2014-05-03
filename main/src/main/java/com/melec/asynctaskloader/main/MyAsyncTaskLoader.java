package com.melec.asynctaskloader.main;


import android.os.Bundle;
import android.os.SystemClock;
import android.support.v4.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;


public class MyAsyncTaskLoader extends AsyncTaskLoader<String> {


    private Bundle args;
    private final String HTTP_REQUEST_CODE = "requestCode";
    private final String LOGIN = "login";
    private String TAG;
    /**constructor*/
    public MyAsyncTaskLoader (Context context, Bundle args){
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
        Log.d("loadInBackground", "called");

         final String GET_USERNAME = "username";
         String username;
         String result;

        /**simulate a long running task*/
        SystemClock.sleep(5000); /** three seconds */

        if(args != null) {

            TAG = args.getString(HTTP_REQUEST_CODE);

            if (TAG != null) {

                if (TAG.equals(LOGIN)) {
                    Log.d("TAG", TAG);
                    username = args.getString(GET_USERNAME);
                    result = username;

                    return result;
                }
            }

        } else{
            return null;
        }
       return  null;

 }




    @Override
    public void deliverResult(String result){
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

         super.deliverResult(result);

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

    }


}
