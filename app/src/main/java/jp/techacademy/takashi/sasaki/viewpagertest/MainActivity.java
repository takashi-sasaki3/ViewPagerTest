package jp.techacademy.takashi.sasaki.viewpagertest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity implements GetContentsAsyncTask.ResponseListener {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        new GetContentsAsyncTask(this).execute("https://www.fujiya-camera.co.jp/dummy.json");
    }

    private void setViews() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FragmentManager manager = getSupportFragmentManager();
        ViewPager pager = findViewById(R.id.viewPager);
        TestFragmentPagerAdapter adapter = new TestFragmentPagerAdapter(manager);
        pager.setAdapter(adapter);
        TabLayout tabLayout = findViewById(R.id.tabLayout);
        tabLayout.setupWithViewPager(pager);
    }

    @Override
    public void onReceive(String response) {
        try {
            JSONArray jsonArray = new JSONArray(response);
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject item = jsonArray.getJSONObject(i);
                Log.d("ViewPagerTest", "----------------------------------------------");
                Log.d("ViewPagerTest", "" + item.getString("date"));
                Log.d("ViewPagerTest", "" + item.getString("name"));
                Log.d("ViewPagerTest", "" + item.getString("name2"));
                Log.d("ViewPagerTest", "" + item.getString("tel"));
                Log.d("ViewPagerTest", "" + item.getString("zipCode"));
                Log.d("ViewPagerTest", "" + item.getString("state"));
                Log.d("ViewPagerTest", "" + item.getString("city"));
                Log.d("ViewPagerTest", "" + item.getString("streetAddress"));
                Log.d("ViewPagerTest", "" + item.getString("note"));
                Log.d("ViewPagerTest", "" + item.getString("deliveryTime"));
                Log.d("ViewPagerTest", "" + item.getString("email"));

                JSONArray kit = item.getJSONArray("rakurakuKit");
                for (int j = 0; j < kit.length(); j++) {
                    JSONObject item2 = kit.getJSONObject(j);
                    Log.d("ViewPagerTest", "  " + item2.get("size") + "*" + item2.get("num"));
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
