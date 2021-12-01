package ast.adrs.vo.MainAuxilaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;

import ast.adrs.vo.MainActivity;


public class ExampleHRUseFragment extends Fragment implements View.OnClickListener {
    RelativeLayout mtriangle,mtriangle1,mtriangle2;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_example_h_r_use, container, false);
        init();
        bindViews(frg);

        return frg;
}

    private void init() {


    }

    private void bindViews(View frg) {

        mtriangle = frg.findViewById(R.id.btntriangle);

        mtriangle1 = frg.findViewById(R.id.btntriangle1);

        mtriangle2 = frg.findViewById(R.id.btntriangle2);

        mtriangle.setOnClickListener(this);

        mtriangle1.setOnClickListener(this);

        mtriangle2.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btntriangle:
                ((MainActivity)getActivity()).navToCircle();
                break;
            case R.id.btntriangle1:
                ((MainActivity)getActivity()).navToCircle();
                break;
            case R.id.btntriangle2:
                ((MainActivity)getActivity()).navToCircle();
                break;
        }
    }
}


