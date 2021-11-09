package ast.adrs.vo.MainAuxilaries;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

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
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.IntroAuxilaries.WebServices.AppConstt;
import ast.adrs.vo.Utils.IBadgeUpdateListener;


public class HomeFragment extends Fragment  {
    private IBadgeUpdateListener mBadgeUpdateListener;
    private PieChart pieChart;

    private PieChart mBarChart_dieses_idr;
    private PieChart mBarChart_sick_animal;
    private List<Integer> lstPieValues;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_home, container, false);


        init();
        bindViews(frg);
        //Pie
        showpieSWSickAnimal(frg);
        showpieIDR();
        // showSWSickAnimal();

        //Bar

        showbarOriginWise(frg);
        showBarIDR(frg);
        // showBarChart(frg);



        return frg;
    }





    private void bindViews(View frg) {
        mBarChart_dieses_idr = frg.findViewById(R.id.frg_home_mpchart_dieses_idr);

        // mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);

//showdata wala
      //   barChart= (HorizontalBarChart) frg.findViewById(R.id.idr_barchart);




    }

    private void  showBarIDR(View frg){



        BarChart barChartView = frg.findViewById(R.id.idr_barchart);

        Float barWidth;
        int groupCount = 12;
        barWidth = 0.25f;

        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("Jan");
        xAxisValues.add("Feb");
        xAxisValues.add("Mar");
        xAxisValues.add("Apr");
        xAxisValues.add("May");
//        xAxisValues.add("June");
//        xAxisValues.add("Jul");
//        xAxisValues.add("Aug");
//        xAxisValues.add("Sep");
//        xAxisValues.add("Oct");
//        xAxisValues.add("Nov");
//        xAxisValues.add("Dec");

        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();
        // draw the graph
        BarDataSet barDataSet1;
        //
        yValueGroup1.add(new BarEntry(1f, 11f));
        yValueGroup1.add(new BarEntry(2f, 12f));
        yValueGroup1.add(new BarEntry(3f, 13f));
        yValueGroup1.add(new BarEntry(4f, 14f));
        yValueGroup1.add(new BarEntry(5f, 15f));

        barDataSet1 = new BarDataSet(yValueGroup1, "");
        barDataSet1.setColors((getResources().getColor(R.color.red)));
        //        barDataSet1.setColors((getResources().getColor(R.color.thm_yellow)), (getResources().getColor(R.color.gray)), (getResources().getColor(R.color.orange)), (getResources().getColor(R.color.blue)));
//        barDataSet1.label = "2016";
        barDataSet1.setDrawIcons(false);
        barDataSet1.setDrawValues(true);

        BarData barData = new BarData(barDataSet1);


        barData.setValueFormatter(new PercentFormatter());
        barChartView.setData(barData);
        barChartView.getBarData().setBarWidth(barWidth);
        barChartView.getXAxis().setAxisMinimum(0f);
        barChartView.getXAxis().setAxisMaximum(12f);

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
        barData.setBarWidth(0.7f);
        barDataSet1.setValueTextColor(Color.BLACK);
        barDataSet1.setValueTextSize(10f);



        XAxis xAxis = barChartView.getXAxis();
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
        // to select numbers of bars u wanna show
        barChartView.setVisibleXRange(1f, 6f);
        barChartView.getDescription().setEnabled(false);
    }


    private void showbarOriginWise(View frg) {

 BarChart barChartView = frg.findViewById(R.id.idr_baroriginwise);

        Float barWidth;
        int groupCount = 12;
        barWidth = 0.25f;

        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("Jan");
        xAxisValues.add("Feb");
        xAxisValues.add("Mar");
        xAxisValues.add("Apr");
        xAxisValues.add("May");
//        xAxisValues.add("June");
//        xAxisValues.add("Jul");
//        xAxisValues.add("Aug");
//        xAxisValues.add("Sep");
//        xAxisValues.add("Oct");
//        xAxisValues.add("Nov");
//        xAxisValues.add("Dec");

        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();

        // draw the graph
        BarDataSet barDataSet1;
        //

        yValueGroup1.add(new BarEntry(1f, 11f));
        yValueGroup1.add(new BarEntry(2f, 12f));
        yValueGroup1.add(new BarEntry(3f, 13f));
        yValueGroup1.add(new BarEntry(4f, 14f));
        yValueGroup1.add(new BarEntry(5f, 15f));

        barDataSet1 = new BarDataSet(yValueGroup1, "");
        barDataSet1.setColors((getResources().getColor(R.color.thm_yellow)));
        //        barDataSet1.setColors((getResources().getColor(R.color.thm_yellow)), (getResources().getColor(R.color.gray)), (getResources().getColor(R.color.orange)), (getResources().getColor(R.color.blue)));
//        barDataSet1.label = "2016";
        barDataSet1.setDrawIcons(false);
        barDataSet1.setDrawValues(true);

        BarData barData = new BarData(barDataSet1);
//        barChartView.description.isEnabled = false;
//        barChartView.description.textSize = 0f;
        barData.setValueFormatter(new PercentFormatter());
        barChartView.setData(barData);
        barChartView.getBarData().setBarWidth(barWidth);
        barChartView.getXAxis().setAxisMinimum(0f);
        barChartView.getXAxis().setAxisMaximum(12f);


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
        barData.setBarWidth(0.7f);
        barDataSet1.setValueTextColor(Color.BLACK);
        barDataSet1.setValueTextSize(10f);


        XAxis xAxis = barChartView.getXAxis();
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
        // to select numbers of bars u wanna show
        barChartView.setVisibleXRange(1f, 6f);
        barChartView.getDescription().setEnabled(false);
    }






    private void showpieSWSickAnimal(View frg) {
        // pieChart = frg.findViewById(R.id.activity_main_piechart);
        mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);
        setupPieChart();
        loadPieChartData();
    }

    private void setupPieChart() {
        mBarChart_sick_animal.setDrawHoleEnabled(false);
        mBarChart_sick_animal.setUsePercentValues(false);
        mBarChart_sick_animal.setEntryLabelTextSize(15);
        mBarChart_sick_animal.setEntryLabelColor(Color.BLACK);
//        pieChart.setCenterText("Spending by Category");
        mBarChart_sick_animal.setCenterTextSize(20);
        mBarChart_sick_animal.getDescription().setEnabled(false);

        Legend l = mBarChart_sick_animal.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);
    }

    private void loadPieChartData() {
        ArrayList<PieEntry> entries = new ArrayList<>();
        entries.add(new PieEntry(15, "Camel"));
        entries.add(new PieEntry(75, "Small Animal"));
        entries.add(new PieEntry(250, "Large Animal"));
        entries.add(new PieEntry(540, "Aquine"));


        ArrayList<Integer> colors = new ArrayList<>();
        for (int color : ColorTemplate.MATERIAL_COLORS) {
            colors.add(color);
        }

        for (int color : ColorTemplate.VORDIPLOM_COLORS) {
            colors.add(color);
        }

        PieDataSet dataSet = new PieDataSet(entries, "Expense Category");
        dataSet.setColors(colors);

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new IntegerFormatter(mBarChart_sick_animal));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        mBarChart_sick_animal.setData(data);
        mBarChart_sick_animal.invalidate();

        mBarChart_sick_animal.animateY(1400, Easing.EaseInOutQuad);
    }


    private void showpieIDR() {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        if (lstPieValues.size() > 0)
            lstPieValues.clear();

        //   lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrFirst());
        //    lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrSecond());
        //   lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrThird());
        // lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrFourth());


        lstPieValues.add(16);
        lstPieValues.add(7);
        lstPieValues.add(2);
        lstPieValues.add(9);

        lstPieValues.add(0);
        lstPieValues.add(1);
        lstPieValues.add(5);


        for (int i = 0; i < lstPieValues.size(); i++) {
            yvals.add(new PieEntry(lstPieValues.get(i)));
            if (i == 0) {
                colors.add(getActivity().getResources().getColor(R.color.thm_green_dark_graph_indicator));
            } else if (i == 1) {
                colors.add(getActivity().getResources().getColor(R.color.thm_blue_dark_graph_indicator));
            } else if (i == 2) {
                colors.add(getActivity().getResources().getColor(R.color.thm_light_green_dark_graph_indicator));
            } else if (i == 3) {
                colors.add(getActivity().getResources().getColor(R.color.thm_yellow_graph_indicator));
            } else if (i == 4) {
                colors.add(getActivity().getResources().getColor(R.color.thm_green_graph_indicator));
            } else if (i == 5) {
                colors.add(getActivity().getResources().getColor(R.color.thm_blue_app));
            } else if (i == 6) {
                colors.add(getActivity().getResources().getColor(R.color.app_blue_lightest));
            }

        }


        PieChartManagger pieChartManagger = new PieChartManagger(mBarChart_dieses_idr, getContext());
        pieChartManagger.showSolidPieChartNew(yvals, colors);
    }


    private void showSWSickAnimal() {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        if (lstPieValues.size() > 0)
            lstPieValues.clear();

        //   lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrFirst());
        //    lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrSecond());
        //   lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrThird());
        // lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrFourth());


        lstPieValues.add(15);
        lstPieValues.add(75);
        lstPieValues.add(250);
        lstPieValues.add(540);


        for (int i = 0; i < lstPieValues.size(); i++) {
            yvals.add(new PieEntry(lstPieValues.get(i)));
            if (i == 0) {
                colors.add(getActivity().getResources().getColor(R.color.thm_green_dark_graph_indicator));
            } else if (i == 1) {
                colors.add(getActivity().getResources().getColor(R.color.thm_blue_dark_graph_indicator));
            } else if (i == 2) {
                colors.add(getActivity().getResources().getColor(R.color.thm_light_green_dark_graph_indicator));
            } else if (i == 3) {
                colors.add(getActivity().getResources().getColor(R.color.app_blue_light));
            }


        }


        PieChartManagger pieChartManagger = new PieChartManagger(mBarChart_sick_animal, getContext());
        pieChartManagger.showSolidPieChartNew(yvals, colors);
    }




    private void init() {
        setToolbar();
        lstPieValues = new ArrayList<>();
    }

    void setToolbar() {

        try {
            mBadgeUpdateListener = (IBadgeUpdateListener) getActivity();
        } catch (ClassCastException castException) {
            castException.printStackTrace(); // The activity does not implement the listener
        }
        if (getActivity() != null && isAdded()) {
            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_VISIBLE);
            mBadgeUpdateListener.setHeaderTitle("Monitoring Dashboard");

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