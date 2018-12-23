package jp.techacademy.takashi.sasaki.viewpagertest;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setViews();

        GetContentsTask task = new GetContentsTask();
        task.setCallbackTask(new GetContentsTask.CallbackTask() {
            @Override
            public void callback(List<String> results) {
                StringBuilder result = new StringBuilder(1000000);
                for (String line : results) {
                    result.append(line);
                }
                Log.d("debug","" + result.toString());
            }
        });
        task.execute("https://www.fujiya-camera.co.jp/dummy.json");
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
}
