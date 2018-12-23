package jp.techacademy.takashi.sasaki.viewpagertest;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

public class GetContentsTask extends AsyncTask<String, Void, List<String>> {

    private CallbackTask callbackTask;

    public void setCallbackTask(CallbackTask callbackTask) {
        this.callbackTask = callbackTask;
    }

    @Override
    protected List<String> doInBackground(String... urls) {
        try {
            URL url = new URL(urls[0]);
            URLConnection urlConnection = url.openConnection();
            BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            List<String> results = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                results.add(line);
            }
            br.close();
            return results;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(List<String> results) {
        callbackTask.callback(results);
    }

    interface CallbackTask {
        void callback(List<String> results);
    }
}
