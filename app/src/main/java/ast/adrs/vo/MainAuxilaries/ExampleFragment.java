package ast.adrs.vo.MainAuxilaries;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

public class ExampleFragment extends Fragment {

    View view;

    public ExampleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_example, container, false);


        populateGraphData();
        return view;
    }


    private void populateGraphData() {

        BarChart barChartView = view.findViewById(R.id.chartConsumptionGraph);

        Float barWidth;
        Float barSpace;
        Float groupSpace;
        int groupCount = 12;

        barWidth = 0.25f;
        barSpace = 0.06f;
        groupSpace = 0.30f;


        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("Jan");
        xAxisValues.add("Feb");
        xAxisValues.add("Mar");
        xAxisValues.add("Apr");
        xAxisValues.add("May");
        xAxisValues.add("June");
        xAxisValues.add("Jul");
        xAxisValues.add("Aug");
        xAxisValues.add("Sep");
        xAxisValues.add("Oct");
        xAxisValues.add("Nov");
        xAxisValues.add("Dec");

        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();
        ArrayList<BarEntry> yValueGroup2 = new ArrayList<>();
        ArrayList<BarEntry> yValueGroup3 = new ArrayList<>();

        // draw the graph
        BarDataSet barDataSet1;
        BarDataSet barDataSet2;
        BarDataSet barDataSet3;


        yValueGroup1.add(new BarEntry(1f, 3f));
        yValueGroup1.add(new BarEntry(1f, 3f));
        yValueGroup1.add(new BarEntry(1f, 3f));
        yValueGroup1.add(new BarEntry(1f, 3f));
        yValueGroup1.add(new BarEntry(1f, 3f));

        barDataSet1 = new BarDataSet(yValueGroup1, "");
        barDataSet1.setColors((getResources().getColor(R.color.thm_yellow)));
        //        barDataSet1.setColors((getResources().getColor(R.color.thm_yellow)), (getResources().getColor(R.color.gray)), (getResources().getColor(R.color.orange)), (getResources().getColor(R.color.blue)));
//        barDataSet1.label = "2016";
        barDataSet1.setDrawIcons(false);
        barDataSet1.setDrawValues(true);


//        <-------------->


        yValueGroup2.add(new BarEntry(2f, 4f));
        yValueGroup2.add(new BarEntry(2f, 4f));
        yValueGroup2.add(new BarEntry(2f, 4f));
        yValueGroup2.add(new BarEntry(2f, 4f));
        yValueGroup2.add(new BarEntry(2f, 4f));

        barDataSet2 = new BarDataSet(yValueGroup2, "");

//        barDataSet2.label = "2017"
        barDataSet2.setColors((getResources().getColor(R.color.gray)));

        barDataSet2.setDrawIcons(false);
        barDataSet2.setDrawValues(true);


//        <-------------->
        yValueGroup3.add(new BarEntry(3f, 5f));
        yValueGroup3.add(new BarEntry(3f, 5f));
        yValueGroup3.add(new BarEntry(3f, 5f));
        yValueGroup3.add(new BarEntry(3f, 5f));
        yValueGroup3.add(new BarEntry(3f, 5f));


        barDataSet3 = new BarDataSet(yValueGroup3, "");

//        barDataSet3.label = "2017"
        barDataSet3.setColors((getResources().getColor(R.color.orange)));

        barDataSet3.setDrawIcons(false);
        barDataSet3.setDrawValues(true);

//        <-------------->


///// Add a new Paramerter as a new row as barDataSet4 .... etc
        BarData barData = new BarData(barDataSet1, barDataSet2, barDataSet3);

//        barChartView.description.isEnabled = false;
//        barChartView.description.textSize = 0f;
        barData.setValueFormatter(new PercentFormatter());
        barChartView.setData(barData);
        barChartView.getBarData().setBarWidth(barWidth);
        barChartView.getXAxis().setAxisMinimum(0f);
        barChartView.getXAxis().setAxisMaximum(12f);

        barChartView.groupBars(0f, groupSpace, barSpace);
        barChartView.setFitBars(true);
        barChartView.getData().setHighlightEnabled(false);
        barChartView.invalidate();

        // set bar label
        Legend legend = barChartView.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setDrawInside(false);
        ArrayList<LegendEntry> legenedEntries = new ArrayList<LegendEntry>();
        legenedEntries.add(new LegendEntry("2016", Legend.LegendForm.SQUARE, 8f, 8f, null, Color.RED));
        legenedEntries.add(new LegendEntry("2017", Legend.LegendForm.SQUARE, 8f, 8f, null, Color.YELLOW));
        legend.setCustom(legenedEntries);
        legend.setYOffset(2f);
        legend.setXOffset(2f);
        legend.setYEntrySpace(0f);
        legend.setTextSize(5f);
        legend.setEnabled(false);


        XAxis xAxis = barChartView.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(9f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));


        xAxis.setLabelCount(12);
        xAxis.mAxisMaximum = 12f;
        xAxis.setCenterAxisLabels(true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setSpaceMin(20f);
        xAxis.setSpaceMax(20f);

        barChartView.setVisibleXRangeMaximum(20f);
        barChartView.setVisibleXRangeMinimum(20f);
        barChartView.setDragEnabled(true);

        //Y-axis
        barChartView.getAxisRight().setEnabled(false);
        barChartView.setScaleEnabled(true);

        YAxis yAxis = barChartView.getAxisLeft();
        yAxis.setValueFormatter(new LargeValueFormatter());
        yAxis.setDrawGridLines(false);
        yAxis.setSpaceTop(1f);
        yAxis.setAxisMinimum(0f);
        yAxis.setEnabled(false);

        barChartView.setDrawBarShadow(false);


        barChartView.setMaxVisibleValueCount(50);
        barChartView.setPinchZoom(false);
        barChartView.setDrawGridBackground(false);

        // Display scores inside the bars
        barChartView.setDrawValueAboveBar(false);

        barChartView.animateY(2000);
        barChartView.setData(barData);
        barChartView.setVisibleXRange(1f, 12f);
    }
}