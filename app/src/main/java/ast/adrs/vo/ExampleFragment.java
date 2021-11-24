package ast.adrs.vo;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.MainAuxilaries.DModel_DiseaseModel;

public class ExampleFragment extends Fragment {
    BarChart severityBarChart;

    ArrayList<String> severityStringList = new ArrayList<>();



    // variable for our bar chart
    BarChart barChart;

    // variable for our bar data.
    BarData barData;

    // variable for our bar data set.
    BarDataSet barDataSet;

    // array list for storing entries.
    ArrayList barEntriesArrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_example, container, false);

        severityBarChart = frg.findViewById(R.id.barChart);

        // initializing variable for bar chart.
        barChart = frg.findViewById(R.id.barChart);
        // calling method to get bar entries.
        getBarEntries();

        // creating a new bar data set.
        barDataSet = new BarDataSet(barEntriesArrayList, "Geeks for Geeks");

        // creating a new bar data and
        // passing our bar data set.
        barData = new BarData(barDataSet);

        // below line is to set data
        // to our bar chart.
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);

        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);

        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);

//        initializeBarChart();
//        onPostSeveritylist();
        return frg;
    }
    private void getBarEntries() {
        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(2f, 67));
        barEntriesArrayList.add(new BarEntry(3f, 8));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));        barEntriesArrayList.add(new BarEntry(1f, 4));
        barEntriesArrayList.add(new BarEntry(2f, 6));
        barEntriesArrayList.add(new BarEntry(3f, 85));
        barEntriesArrayList.add(new BarEntry(4f, 2));
        barEntriesArrayList.add(new BarEntry(5f, 4));
        barEntriesArrayList.add(new BarEntry(6f, 1));
    }

    private void initializeBarChart() {
        severityBarChart.getDescription().setEnabled(false);

        // if more than 60 entries are displayed in the chart, no values will be
        // drawn
        severityBarChart.setMaxVisibleValueCount(4);
        severityBarChart.getXAxis().setDrawGridLines(false);
        // scaling can now only be done on x- and y-axis separately
//        severityBarChart.setPinchZoom(false);

        severityBarChart.setDrawBarShadow(false);
        severityBarChart.setDrawGridBackground(false);

        XAxis xAxis = severityBarChart.getXAxis();
        xAxis.setDrawGridLines(false);

        severityBarChart.getAxisLeft().setDrawGridLines(false);
        severityBarChart.getAxisRight().setDrawGridLines(false);
        severityBarChart.getAxisRight().setEnabled(false);
        severityBarChart.getAxisLeft().setEnabled(true);
        severityBarChart.getXAxis().setDrawGridLines(false);
        // add a nice and smooth animation
        severityBarChart.animateY(1500);


        severityBarChart.getLegend().setEnabled(false);

        severityBarChart.getAxisRight().setDrawLabels(false);
        severityBarChart.getAxisLeft().setDrawLabels(true);
        severityBarChart.setTouchEnabled(false);
        severityBarChart.setDoubleTapToZoomEnabled(false);
        severityBarChart.getXAxis().setEnabled(true);
        severityBarChart.getXAxis().setTextSize(6f);
        severityBarChart.getXAxis().setPosition(XAxis.XAxisPosition.BOTTOM);
        severityBarChart.invalidate();

    }

    private void createBarChart(ArrayList<DModel_DiseaseModel> severityListServer) {
        ArrayList<BarEntry> values = new ArrayList<>();

        for (int i = 0; i < severityListServer.size(); i++) {
            DModel_DiseaseModel dataObject = severityListServer.get(i);
            values.add(new BarEntry(i, (dataObject.getDi())));
        }

        BarDataSet set1;

        if (severityBarChart.getData() != null &&
                severityBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) severityBarChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            severityBarChart.getData().notifyDataChanged();
            severityBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "Data Set");
            set1.setColors(getResources().getColor(R.color.pink));
            set1.setDrawValues(true);

            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);

            BarData data = new BarData(dataSets);
            severityBarChart.setData(data);
            severityBarChart.setVisibleXRange(1, 6);
            severityBarChart.setFitBars(true);
            XAxis xAxis = severityBarChart.getXAxis();
            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(false);
//            severityBarChart.getBarData().setBarWidth(0.30f);


            xAxis.setValueFormatter(new IndexAxisValueFormatter(severityStringList));//setting String values in Xaxis


            YAxis yAxis = severityBarChart.getAxisLeft();
            yAxis.setValueFormatter(new LargeValueFormatter());
            yAxis.setDrawGridLines(false);
            yAxis.setSpaceTop(1f);
            yAxis.setAxisMinimum(0f);
            yAxis.setEnabled(false);


            for (IDataSet set : severityBarChart.getData().getDataSets())
                set.setDrawValues(!set.isDrawValuesEnabled());

            severityBarChart.invalidate();
        }
    }

    protected void onPostSeveritylist() {


        ArrayList<DModel_DiseaseModel> data = new ArrayList<>();
        data.add(new DModel_DiseaseModel("7", 2, 5));
        data.add(new DModel_DiseaseModel("7", 18, 5));
        data.add(new DModel_DiseaseModel("7", 0, 5));
        data.add(new DModel_DiseaseModel("7", 0, 5));
        data.add(new DModel_DiseaseModel("7", 7, 5));
        data.add(new DModel_DiseaseModel("7", 5, 5));
        data.add(new DModel_DiseaseModel("7", 0, 5));
        data.add(new DModel_DiseaseModel("7", 0, 5));
        data.add(new DModel_DiseaseModel("7", 15, 5));
        data.add(new DModel_DiseaseModel("7", 0, 5));
        data.add(new DModel_DiseaseModel("7", 68, 5));
        data.add(new DModel_DiseaseModel("7", 0, 5));
        data.add(new DModel_DiseaseModel("7", 25, 5));
        data.add(new DModel_DiseaseModel("7", 0, 5));



        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("7:00");
        xAxisValues.add("10:00");
        xAxisValues.add("12:00");
        xAxisValues.add("2:00");
        xAxisValues.add("4:00");
        xAxisValues.add("7:00");


        severityStringList.addAll(xAxisValues);

        createBarChart(data);
    }
}


