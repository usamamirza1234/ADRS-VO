package ast.adrs.vo.MainAuxilaries;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;


public class BarChartManager {
    public BarChart barChart;
    Context context;
    Float barWidth;
    int groupCount = 12;


    public BarChartManager(BarChart barChart, Context context) {
        this.barChart = barChart;
        this.context = context;
        barWidth = 0.25f;
        initPieChart();
    }

    private void initPieChart() {


        // set bar label
        Legend legend = barChart.getLegend();
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

    }


    public void showBarChart(ArrayList<BarEntry> yValueGroup1, List<String> xAxisValues ) {



        // draw the graph
        BarDataSet barDataSet1;
        barDataSet1 = new BarDataSet(yValueGroup1, "");
        barDataSet1.setColors((context.getResources().getColor(R.color.red)));
        //        barDataSet1.setColors((getResources().getColor(R.color.thm_yellow)), (getResources().getColor(R.color.gray)), (getResources().getColor(R.color.orange)), (getResources().getColor(R.color.blue)));
//        barDataSet1.label = "2016";
        barDataSet1.setDrawIcons(false);
        barDataSet1.setDrawValues(true);




        BarData barData = new BarData(barDataSet1);

        barData.setValueFormatter(new PercentFormatter());
        barChart.setData(barData);
        barChart.getBarData().setBarWidth(barWidth);
        barChart.getXAxis().setAxisMinimum(0f);
        barChart.getXAxis().setAxisMaximum(12f);

        barChart.setFitBars(true);
        barChart.getData().setHighlightEnabled(true);
        barChart.invalidate();
        
        //    to remove decimal point
        MyDecimalValueFormatter formatter = new MyDecimalValueFormatter();
        barDataSet1.setValueFormatter(formatter);


        XAxis xAxis = barChart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setTextSize(12f);

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));


        xAxis.setLabelCount(12);
        xAxis.mAxisMaximum = 12f;
        xAxis.setCenterAxisLabels(true);
        xAxis.setAvoidFirstLastClipping(true);
        xAxis.setSpaceMin(20f);
        xAxis.setSpaceMax(20f);

        barChart.setVisibleXRangeMaximum(20f);
        barChart.setVisibleXRangeMinimum(20f);
        barChart.setDragEnabled(true);

        //Y-axis
        barChart.getAxisRight().setEnabled(false);
        barChart.setScaleEnabled(true);

        YAxis yAxis = barChart.getAxisLeft();
        yAxis.setValueFormatter(new LargeValueFormatter());
        yAxis.setDrawGridLines(false);
        yAxis.setSpaceTop(1f);
        yAxis.setAxisMinimum(0f);
        yAxis.setEnabled(false);

        barChart.setDrawBarShadow(false);


        barChart.setMaxVisibleValueCount(50);
        barChart.setPinchZoom(false);
        barChart.setDrawGridBackground(false);

        // Display scores inside the bars
        barChart.setDrawValueAboveBar(false);

        barChart.animateY(2000);
        barChart.setData(barData);
        // to select numbers of bars u wanna show
        barChart.setVisibleXRange(1f, 12f);
        barChart.getDescription().setEnabled(false);

        barData.setBarWidth(0.8f);
        barDataSet1.setValueTextColor(Color.WHITE);
        barDataSet1.setValueTextSize(10f);
    }
}
