package ast.adrs.vo.MainAuxilaries;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.List;


public class PieChartManagerForLables {
    public PieChart pieChart;
    Context context;


    public PieChartManagerForLables(PieChart pieChart, Context context) {
        this.pieChart = pieChart;
        this.context = context;
        initPieChart();
    }

    private void initPieChart() {

        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(false);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);

    }


    public void showLabeledPieChart(List<PieEntry> yvals, List<Integer> colors) {

        pieChart.setEntryLabelColor(context.getResources().getColor(R.color.white));


        PieDataSet dataSet = new PieDataSet(yvals, "Expense Category");

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new IntegerFormatter(pieChart));


        //Fill the color of each area
        dataSet.setColors(colors);
        //Whether to display the value on the graph
        dataSet.setDrawValues(true);
        // The size of the text
        dataSet.setValueTextSize(12);
        // text color
        dataSet.setValueTextColor(context.getResources().getColor(R.color.white));
        // Style of text
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);
        // Set the gap before each
        dataSet.setSliceSpace(2);
        //Set the distance that changes when the pie item is selected
        dataSet.setSelectionShift(3f);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }


    public void showLabeledPieChartHidden(List<PieEntry> yvals, List<Integer> colors) {


        pieChart.setEntryLabelColor(context.getResources().getColor(R.color.transparent));
        PieDataSet dataSet = new PieDataSet(yvals, "Expense Category");

        PieData data = new PieData(dataSet);

        data.setValueFormatter(new IntegerFormatter(pieChart));


        //Fill the color of each area
        dataSet.setColors(colors);
        //Whether to display the value on the graph
        dataSet.setDrawValues(true);
        // The size of the text
        dataSet.setValueTextSize(12);
        // text color
        dataSet.setValueTextColor(context.getResources().getColor(R.color.white));
        // Style of text
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);
        // Set the gap before each
        dataSet.setSliceSpace(2);
        //Set the distance that changes when the pie item is selected
        dataSet.setSelectionShift(3f);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }
}
