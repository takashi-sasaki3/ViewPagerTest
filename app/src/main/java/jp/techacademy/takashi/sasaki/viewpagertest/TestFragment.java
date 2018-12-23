package jp.techacademy.takashi.sasaki.viewpagertest;

import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class TestFragment extends Fragment {

    private final static String BACKGROUND_COLOR = "background_color";

    public static TestFragment newInstance(@ColorRes int idRes) {
        TestFragment fragment = new TestFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(BACKGROUND_COLOR, idRes);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test, null);
        LinearLayout linearLayout = view.findViewById(R.id.fragmentMain);
        linearLayout.setBackgroundResource(getArguments().getInt(BACKGROUND_COLOR));
        return view;
    }
}
