package ast.adrs.vo.MainAuxilaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;

public class CalendarFragment extends Fragment {
CalendarView calendar;
TextView MyDate;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_calendar, container, false);




        init();
        bindViews(frg);

        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
                String date= (i1+1)+ "/" +i2 +"/"+i;
                MyDate.setText(date);

            }
        });

        return frg;

    }

    private void init(){


    }

    private void bindViews(View frg) {

        calendar = frg.findViewById(R.id.dlg_reorder_mcv_calndr);

        MyDate = frg.findViewById(R.id.mydate);





    }



}