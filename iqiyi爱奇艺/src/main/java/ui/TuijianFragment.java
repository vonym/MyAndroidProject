package ui;

import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.iqiyi.R;

import utils.SlideShowView;

import static java.lang.Thread.sleep;

public class TuijianFragment extends Fragment {
    @RequiresApi(api = Build.VERSION_CODES.M)
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        SlideShowView slideShowView = new SlideShowView(getContext(), null, R.style.AppTheme);
        View TuijianLayout = inflater.inflate(R.layout.tuijianfragment, container, false);
        FrameLayout frameLayout = (FrameLayout) TuijianLayout.findViewById(R.id.frameLayout);
        frameLayout.addView(slideShowView);
        return TuijianLayout;
    }
}
