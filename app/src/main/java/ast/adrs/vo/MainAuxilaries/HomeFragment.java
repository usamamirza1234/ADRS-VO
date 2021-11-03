package ast.adrs.vo.MainAuxilaries;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.armoomragames.denketa.R;

import ast.adrs.vo.IntroAuxilaries.WebServices.AppConstt;
import ast.adrs.vo.Utils.IBadgeUpdateListener;


public class HomeFragment extends Fragment {

    private IBadgeUpdateListener mBadgeUpdateListener;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_home, container, false);


        init();
        return frg;
    }

    private void init() {
        setToolbar();
    }

    void setToolbar() {

        try {
            mBadgeUpdateListener = (IBadgeUpdateListener) getActivity();
        } catch (ClassCastException castException) {
            castException.printStackTrace(); // The activity does not implement the listener
        }
        if (getActivity() != null && isAdded()) {
            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_VISIBLE);
            mBadgeUpdateListener.setHeaderTitle("ADRS");

        }

    }
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!isHidden()) {
            setToolbar();
        }
    }
}