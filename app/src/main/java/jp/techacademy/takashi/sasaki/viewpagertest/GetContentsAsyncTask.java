package jp.techacademy.takashi.sasaki.viewpagertest;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetContentsAsyncTask extends AsyncTask<String, Void, String> {

    private ResponseListener listener;

    public GetContentsAsyncTask(ResponseListener listener) {
        this.listener = listener;
    }

    @Override
    protected String doInBackground(String... urls) {
        Log.d("ViewPagerTest", "url:" + urls[0]);

        try {
            URL url = new URL(urls[0]);
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
//            urlConnection.setRequestMethod("GET");
//            urlConnection.addRequestProperty("User-Agent", "Android");
//            urlConnection.addRequestProperty("Accept-Language", Locale.getDefault().toString());
//            urlConnection.setInstanceFollowRedirects(false);
//            urlConnection.setDoInput(true);
//            urlConnection.setDoOutput(false);
//            urlConnection.setConnectTimeout(20000);
//            urlConnection.setReadTimeout(30000);
            urlConnection.connect();

            StringBuilder buffer = new StringBuilder();
            InputStream inputStream = urlConnection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                buffer.append(line);
            }
            bufferedReader.close();
            inputStream.close();
            urlConnection.disconnect();
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    protected void onPostExecute(String response) {
        if (listener != null)
            listener.onReceive(response);
    }

    public interface ResponseListener {
        void onReceive(String response);
    }
}
