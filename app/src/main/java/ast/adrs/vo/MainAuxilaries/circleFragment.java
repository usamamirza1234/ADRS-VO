package ast.adrs.vo.MainAuxilaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;


public class circleFragment extends Fragment implements View.OnClickListener {
    RelativeLayout mtriangle;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_circle, container, false);
        init();
        bindViews(frg);

        return frg;
    }

    private void init() {


    }

    private void bindViews(View frg) {

//        mtriangle = frg.findViewById(R.id.btntriangle);
//        mtriangle.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btntriangle:
//
//                break;
        }
    }


