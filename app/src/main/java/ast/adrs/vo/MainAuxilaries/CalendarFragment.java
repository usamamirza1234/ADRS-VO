package ast.adrs.vo.MainAuxilaries;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;

import java.util.Calendar;

public class CalendarFragment extends Fragment implements View.OnClickListener {
    CalendarView calendar;
    TextView MyDate;

    Button btnStartingDatePicker, btnEndingDatePicker;
    EditText txtStartingDate, txtEndingDate;
    private int mYear, mMonth, mDay;


    Context thiscontext;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_calendar, container, false);
        thiscontext = container.getContext();

        init();
        bindViews(frg);

//        calendar.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
//            @Override
//            public void onSelectedDayChange(@NonNull CalendarView view, int i, int i1, int i2) {
//                String date = (i1 + 1) + "/" + i2 + "/" + i;
//                MyDate.setText(date);
//
//            }
//        });

        return frg;

    }

    private void init() {


    }

    private void bindViews(View frg) {


//        calendar = frg.findViewById(R.id.dlg_reorder_mcv_calndr);
//
//        MyDate = frg.findViewById(R.id.mydate);

        btnStartingDatePicker = (Button) frg.findViewById(R.id.btn_date);
        btnEndingDatePicker = (Button) frg.findViewById(R.id.btn_time);
        txtStartingDate = (EditText) frg.findViewById(R.id.in_date);
        txtEndingDate = (EditText) frg.findViewById(R.id.in_time);

        btnStartingDatePicker.setOnClickListener(this);
        btnEndingDatePicker.setOnClickListener(this);


    }



    @Override
    public void onClick(View v) {
        if (v == btnStartingDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(thiscontext,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtStartingDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    },
                    mYear,
                    mMonth,
                    mDay);
            datePickerDialog.show();
        }

        if (v == btnEndingDatePicker) {

            // Get Current Date
            final Calendar c = Calendar.getInstance();
            mYear = c.get(Calendar.YEAR);
            mMonth = c.get(Calendar.MONTH);
            mDay = c.get(Calendar.DAY_OF_MONTH);


            DatePickerDialog datePickerDialog;
            datePickerDialog = new DatePickerDialog(thiscontext,
                    new DatePickerDialog.OnDateSetListener() {

                        @Override
                        public void onDateSet(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {

                            txtEndingDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                        }
                    },
                    mYear,
                    mMonth,
                    mDay);
            datePickerDialog.show();
        }


    }
}
