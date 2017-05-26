package ui;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.iqiyi.R;

public class WodeFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View WodeLayout = inflater.inflate(R.layout.wodefragment, container, false);
		return WodeLayout;
	}
}
