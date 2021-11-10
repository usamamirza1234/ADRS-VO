package ast.adrs.vo.MainAuxilaries;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LegendEntry;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.Utils.IBadgeUpdateListener;



public class ImmediateDiseasesReportsIDRFragment extends Fragment {
    private IBadgeUpdateListener mBadgeUpdateListener;

    private List<Integer> lstPieValues;
    private PieChart mBarChart_district_faisalabad;
    private PieChart mBarChart_foot_mouth;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_immediate_diseases_reports_i_d_r, container, false);

        init();
        bindViews(frg);
        showpieFootMouth();
        showDistrictwiseDiseaseReport(frg);
        showpieDistrictFaisalabad();
        showTehsilFaisalabad(frg);

        return frg;
    }




    private void init(){
        lstPieValues = new ArrayList<>();

    }

    private void bindViews(View frg) {
        mBarChart_foot_mouth = frg.findViewById(R.id.frg_home_mpchart_foot_mouth);
        mBarChart_district_faisalabad = frg.findViewById(R.id.frg_home_mpchart_district_faisalabad);




    }




    private void showpieFootMouth() {
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
        lstPieValues.add(0);
        lstPieValues.add(1);



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
                colors.add(getActivity().getResources().getColor(R.color.thm_red));
            }else if (i == 7) {
                colors.add(getActivity().getResources().getColor(R.color.thm_blue2));
            }else if (i == 8) {
                colors.add(getActivity().getResources().getColor(R.color.thm_blue_slc));
            }else if (i == 9) {
                colors.add(getActivity().getResources().getColor(R.color.gray));
            }

        }


        PieChartManagger pieChartManagger = new PieChartManagger(mBarChart_foot_mouth, getContext());
        pieChartManagger.showSolidPieChartNew(yvals, colors);
    }



    private void showpieDistrictFaisalabad() {
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
            }

        }


        PieChartManagger pieChartManagger = new PieChartManagger(mBarChart_district_faisalabad, getContext());
        pieChartManagger.showSolidPieChartNew(yvals, colors);
    }


    private void  showDistrictwiseDiseaseReport(View frg){



        BarChart barChartView = frg.findViewById(R.id.districtwisediseasereport_barchart);

        Float barWidth;
        int groupCount = 12;
        barWidth = 0.25f;

        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("Therileriosis");
        xAxisValues.add("Glander");
        xAxisValues.add("Rabies (Mad dog disease)");
        xAxisValues.add("Black quarter (black-leg)");
        xAxisValues.add("Foot and mouth disease");
        xAxisValues.add("Contagious  pleuropneumonia   ");
        xAxisValues.add("Brucellosis of sheep.");
        xAxisValues.add("Tetanus");
        xAxisValues.add("Anthrax");
        xAxisValues.add("Blue tongue");
        xAxisValues.add("African swine fever");
        xAxisValues.add("Transmissible spongiform ");

        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();
        // draw the graph
        BarDataSet barDataSet1;
        //
        yValueGroup1.add(new BarEntry(1f, 11f));
        yValueGroup1.add(new BarEntry(2f, 12f));
        yValueGroup1.add(new BarEntry(3f, 13f));
        yValueGroup1.add(new BarEntry(4f, 14f));
        yValueGroup1.add(new BarEntry(5f, 15f));
        yValueGroup1.add(new BarEntry(6f, 11f));
        yValueGroup1.add(new BarEntry(7f, 11f));
        yValueGroup1.add(new BarEntry(8f, 12f));
        yValueGroup1.add(new BarEntry(9f, 13f));
        yValueGroup1.add(new BarEntry(10f, 14f));
        yValueGroup1.add(new BarEntry(11f, 15f));

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
        barData.setBarWidth(0.8f);
        barDataSet1.setValueTextColor(Color.WHITE);
        barDataSet1.setValueTextSize(10f);

        //    to remove decimal point
        MyDecimalValueFormatter formatter = new MyDecimalValueFormatter();
        barDataSet1.setValueFormatter(formatter);


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
        barChartView.setVisibleXRange(1f, 12f);
        barChartView.getDescription().setEnabled(false);
    }


    private void  showTehsilFaisalabad(View frg){



        BarChart barChartView = frg.findViewById(R.id.tehsilfasialabad_barchart);

        Float barWidth;
        int groupCount = 12;
        barWidth = 0.25f;

        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("Therileriosis");
        xAxisValues.add("Glander");
        xAxisValues.add("Rabies (Mad dog disease)");
        xAxisValues.add("Black quarter (black-leg)");
        xAxisValues.add("Foot and mouth disease");
        xAxisValues.add("Contagious  pleuropneumonia   ");
        xAxisValues.add("Brucellosis of sheep.");
        xAxisValues.add("Tetanus");


        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();
        // draw the graph
        BarDataSet barDataSet1;
        //
        yValueGroup1.add(new BarEntry(1f, 1f));
        yValueGroup1.add(new BarEntry(2f, 1f));
        yValueGroup1.add(new BarEntry(3f, 1f));
        yValueGroup1.add(new BarEntry(4f, 1f));
        yValueGroup1.add(new BarEntry(5f, 1f));
        yValueGroup1.add(new BarEntry(6f, 0.25f));
        yValueGroup1.add(new BarEntry(7f, 2f));
        yValueGroup1.add(new BarEntry(8f, 1f));


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
        barData.setBarWidth(0.8f);
        barDataSet1.setValueTextColor(Color.WHITE);
        barDataSet1.setValueTextSize(10f);

        //    to remove decimal point
        MyDecimalValueFormatter formatter = new MyDecimalValueFormatter();
        barDataSet1.setValueFormatter(formatter);


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
        barChartView.setVisibleXRange(1f, 9f);
        barChartView.getDescription().setEnabled(false);
    }











}