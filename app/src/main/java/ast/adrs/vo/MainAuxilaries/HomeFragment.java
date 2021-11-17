package ast.adrs.vo.MainAuxilaries;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
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
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.formatter.PercentFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.IntroAuxilaries.WebServices.AppConstt;
import ast.adrs.vo.Utils.CustomToast;
import ast.adrs.vo.Utils.IBadgeUpdateListener;


public class HomeFragment extends Fragment implements View.OnClickListener {
    AlertDialog alertDialog;
    TextView btnhomefrgsick;
    ArrayList<BarEntry> barEntriesArrayList;
    ArrayList<String> lableName;
    ArrayList<MonthlySalesData> monthlySalesDataArrayList = new ArrayList<>();
    private IBadgeUpdateListener mBadgeUpdateListener;
    private PieChart pieChart;


    private PieChart mBarChart_dieses_idr;
    private PieChart mBarChart_sick_animal;
    private List<Integer> lstPieValues;


    private String TAG = "PIE_CHART";
    BarChart barChartView;
    List<String> xAxisValues;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_home, container, false);


        init();
        bindViews(frg);

        showPieChartFor_IDR();
        showPieChartFor_SickAnimal();

        // showSWSickAnimal();

        //Bar

        showbarOriginWise(frg);
        showBarIDR();
        // showBarChart(frg);


        return frg;
    }


    @RequiresApi(api = Build.VERSION_CODES.O)
    private void bindViews(View frg) {
        mBarChart_dieses_idr = frg.findViewById(R.id.frg_home_mpchart_dieses_idr);
        // btnhomefrgsick   = frg.findViewById(R.id.frg_home_frg_txv_sick);


        btnhomefrgsick = frg.findViewById(R.id.frg_home_frg_txv_sick);
        btnhomefrgsick.setTooltipText("Sick");

        //Pie chart label ka kam jisye kia ha ab wisye krna ha..... code cleaned ... bar chart ka b

        //region INIT
        private void init () {
            setToolbar();
            lstPieValues = new ArrayList<>();
            barEntriesArrayList = new ArrayList<>();
            xAxisValues = new ArrayList<>();
        }

        void setToolbar () {

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
        public void onHiddenChanged ( boolean hidden){
            super.onHiddenChanged(hidden);
            if (!isHidden()) {
                setToolbar();
            }
        }

        @RequiresApi(api = Build.VERSION_CODES.O)
        private void bindViews (View frg){
            mBarChart_dieses_idr = frg.findViewById(R.id.frg_home_mpchart_dieses_idr);
            mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);
            barChartView = frg.findViewById(R.id.idr_barchart);


            btnhomefrgsick = frg.findViewById(R.id.frg_home_frg_txv_sick);
//        btnhomefrgsick.setTooltipText("Sick");

            // mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);

//showdata wala
            //   barChart= (HorizontalBarChart) frg.findViewById(R.id.idr_barchart);

            btnhomefrgsick.setOnClickListener(this);


        }


        //endregion

        // har pie chart ka aisye krna ha label sb ko dainy han pr ager hide krny ha to srf yh function call krna ha ... pieChartManagerForLables.showLabeledPieChartHidden(yvals, colors);

        //region PieChart
        private void showPieChartFor_IDR () {
            //Set the number of each share
            List<PieEntry> yvals = new ArrayList<>();
            List<Integer> colors = new ArrayList<>();

            if (lstPieValues.size() > 0)
                lstPieValues.clear();

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

        private void showPieChartFor_SickAnimal () {


            List<PieEntry> yvals = new ArrayList<>();
            List<Integer> colors = new ArrayList<>();

            List<Integer> lstPieValues = new ArrayList<>();
            lstPieValues.add(15);
            lstPieValues.add(75);
            lstPieValues.add(250);
            lstPieValues.add(540);

            List<String> lstPieLabels = new ArrayList<>();
            lstPieLabels.add("Camel");
            lstPieLabels.add("Small Animal");
            lstPieLabels.add("Large Animal");
            lstPieLabels.add("Aquine");


            for (int i = 0; i < lstPieValues.size(); i++) {
                yvals.add(new PieEntry(lstPieValues.get(i), lstPieLabels.get(i)));
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

            PieChartManagerForLables pieChartManagerForLables = new PieChartManagerForLables(mBarChart_sick_animal, getContext());
            pieChartManagerForLables.showLabeledPieChart(yvals, colors);
            //pieChartManagerForLables.showLabeledPieChartHidden(yvals, colors);

            mBarChart_sick_animal.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
                @Override
                public void onValueSelected(Entry e, Highlight h) {
                    PieEntry pe = (PieEntry) e;
                    pe.getLabel();
                    Log.d(TAG, "onValueSelected: label   " + pe.getLabel());
                    switch (pe.getLabel()) {
                        case "Camel":
                            CustomToast.showToastMessage(getActivity(), pe.getLabel() + " selected", Toast.LENGTH_LONG);
                            break;


                        case "Small Animal":
                            CustomToast.showToastMessage(getActivity(), pe.getLabel() + " selected", Toast.LENGTH_LONG);

                            break;


                        case "Large Animal":
                            CustomToast.showToastMessage(getActivity(), pe.getLabel() + " selected", Toast.LENGTH_LONG);

                            break;


                        case "Aquine":
                            CustomToast.showToastMessage(getActivity(), pe.getLabel() + " selected", Toast.LENGTH_LONG);


                            ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();
                            yValueGroup1.add(new BarEntry(6f, 6f));
                            yValueGroup1.add(new BarEntry(7f, 2f));
                            yValueGroup1.add(new BarEntry(3f, 3f));
                            yValueGroup1.add(new BarEntry(2f, 4f));
                            yValueGroup1.add(new BarEntry(6f, 5f));
                            yValueGroup1.add(new BarEntry(3f, 11f));
                            yValueGroup1.add(new BarEntry(6f, 11f));
                            yValueGroup1.add(new BarEntry(2f, 12f));
                            yValueGroup1.add(new BarEntry(1f, 13f));
                            yValueGroup1.add(new BarEntry(6f, 14f));
                            yValueGroup1.add(new BarEntry(11f, 15f));

                            BarChartManager barChartManager = new BarChartManager(barChartView, getContext());
                            barChartManager.showBarChart(yValueGroup1, xAxisValues);
                            break;

                    }

                }

                @Override
                public void onNothingSelected() {

                }
            });
        }
        //endregion


        @Override
        public void onClick (View v){
            switch (v.getId()) {
                case R.id.frg_home_frg_txv_sick:

                    break;
            }
        }

        private void showBarIDR () {


            private void showBarIDR (View frg){


                BarChart barChartView = frg.findViewById(R.id.idr_barchart);

                Float barWidth;
                int groupCount = 12;
                barWidth = 0.25f;


//        List<String> xAxisValues = new ArrayList<>(); isko bhar(global) isliye kia ha ku k is ky label ny static rahna ha agr ni rhna
                //to isko b ander rakh laina
                //  showPieChartFor_SickAnimal() is function ma pie pr click krny sy dubara array ma label ki value nai dalni praye is liye
                // agr dalni ha to isaye un comment kr daina or udr b same arry list bna daina jasaye udr (yValueGroup1) is ki bni ha
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
                barChartView.getData().setHighlightEnabled(true);
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


            BarChartManager barChartManager = new BarChartManager(barChartView, getContext());
            barChartManager.showBarChart(yValueGroup1, xAxisValues);
        }


        private void showbarOriginWise (View frg){

            BarChart barChartView = frg.findViewById(R.id.idr_baroriginwise);
            //   fillMonthlySalesArrayList();
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
//        xAxisValues.add("Oct");
//        xAxisValues.add("Nov");
//        xAxisValues.add("Dec");

            ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();

            // draw the graph
            BarDataSet barDataSet1;
            //

            yValueGroup1.add(new BarEntry(1f, 24f));
            yValueGroup1.add(new BarEntry(2f, 31f));
            yValueGroup1.add(new BarEntry(3f, 4f));
            yValueGroup1.add(new BarEntry(4f, 2f));
            yValueGroup1.add(new BarEntry(5f, 10f));
            yValueGroup1.add(new BarEntry(6f, 14f));
            yValueGroup1.add(new BarEntry(7f, 7f));
            yValueGroup1.add(new BarEntry(8f, 15f));
            yValueGroup1.add(new BarEntry(9f, 5f));

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
            barChartView.getData().setHighlightEnabled(true);
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


            // to remove decimal point
            MyDecimalValueFormatter formatter = new MyDecimalValueFormatter();
            barDataSet1.setValueFormatter(formatter);
            XAxis xAxis = barChartView.getXAxis();

            xAxis.setGranularity(1f);
            xAxis.setGranularityEnabled(true);
            xAxis.setCenterAxisLabels(true);
            xAxis.setDrawGridLines(false);
            xAxis.setTextSize(12f);


            // for vertical labels
            xAxis.setLabelRotationAngle(-90);
            xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
            xAxis.setValueFormatter(new IndexAxisValueFormatter(xAxisValues));


            xAxis.setLabelCount(12);
            xAxis.mAxisMaximum = 12f;
            xAxis.setCenterAxisLabels(true);
            xAxis.setAvoidFirstLastClipping(false);
            xAxis.setSpaceMin(20f);
            xAxis.setSpaceMax(20f);

            //        barChartView.getXAxis().setAxisMaximum(maxX + 0.2f);
//        barChartView.getXAxis().setAxisMinimum(minX - 0.2f);
//


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
            barChartView.setVisibleXRange(1f, 10f);
            barChartView.getDescription().setEnabled(false);


            /////


//        barChartView.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
//            @Override
//            public void onValueSelected(Entry e, Highlight h) {
//
//                int x = barChartView.getData().getDataSetForEntry(e).getEntryIndex((BarEntry) e);
//                String region = monthlySalesDataArrayList.get(x).getMonth();
//                String sales = NumberFormat.getCurrencyInstance().format(monthlySalesDataArrayList.get(x).getSales());
//                   Toast.makeText(getContext(),"Enter all fields", Toast.LENGTH_LONG).show();
            //CustomToast.showToastMessage(getActivity(),"some thing"+e.getY(),Toast);
//                AlertDialog.Builder builder = new AlertDialog.Builder(HomeFragment.this);
//                builder.setCancelable(true);
//                View view = LayoutInflater.from(HomeFragment.this).inflate(R.layout.monthtest,null);
//                TextView regionTxtView = view.findViewById(R.id.region);
//                TextView salesTxtView = view.findViewById(R.id.sales);
//                regionTxtView.setText(region);
//                salesTxtView.setText(sales);
//                builder.setView(view);
//                alertDialog = builder.create();
//                alertDialog.show();
//
//            }
//
//            @Override
//            public void onNothingSelected() {
//
//            }
//        });
//
////
//    }
//    private void fillMonthlySalesArrayList(){
//       monthlySalesDataArrayList.clear();
//    monthlySalesDataArrayList.add(new MonthlySalesData("Jan",242000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Feb",300000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Mar",150000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Apr",250000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("May",242000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("June",300000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("July",150000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Aug",210000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Sep",242000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Oct",320000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Nov",150000));
//    monthlySalesDataArrayList.add(new MonthlySalesData("Dec EGypt",200000));


        }


        private void showpieSWSickAnimal (View frg){
            // pieChart = frg.findViewById(R.id.activity_main_piechart);
            mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);

            setupPieChart();
            loadPieChartData();
        }

        private void setupPieChart () {
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

        private void loadPieChartData () {
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


        private void showpieIDR () {
            //Set the number of each share
            List<PieEntry> yvals = new ArrayList<>();
            List<Integer> colors = new ArrayList<>();

            if (lstPieValues.size() > 0)
                lstPieValues.clear();

            //   lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrFirst());
            //    lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrSecond());
            //   lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrThird());
            // lstPieValues.add(AppConfig.getInstance().getGraphValueList().get(1).getStrFourth());


            lstPieValues.add(1);
            lstPieValues.add(4);
            lstPieValues.add(9);
            lstPieValues.add(10);

            lstPieValues.add(18);
            lstPieValues.add(5);
            lstPieValues.add(5);

            lstPieValues.add(7);
            lstPieValues.add(4);
            lstPieValues.add(4);
            lstPieValues.add(4);

            lstPieValues.add(4);
            lstPieValues.add(1);
            lstPieValues.add(2);
            lstPieValues.add(8);


            for (int i = 0; i < lstPieValues.size(); i++) {
                yvals.add(new PieEntry(lstPieValues.get(i)));
                if (i == 0) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_bluesish1));
                } else if (i == 1) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_brown1));
                } else if (i == 2) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_gray1));
                } else if (i == 3) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_green));
                } else if (i == 4) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_lightblue1));
                } else if (i == 5) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_lightgreen1));
                } else if (i == 6) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_lightsilver1));
                } else if (i == 7) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_lightsilver1));
                } else if (i == 8) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_likeblue1));
                } else if (i == 9) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_mehndhi1));
                } else if (i == 10) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_navy1));
                } else if (i == 11) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_orange1));
                } else if (i == 12) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_orangelight1));
                } else if (i == 13) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_sky1));
                } else if (i == 14) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_yellow1));
                } else if (i == 15) {
                    colors.add(getActivity().getResources().getColor(R.color.graph_idr_silver1));
                }

            }


            PieChartManagger pieChartManagger = new PieChartManagger(mBarChart_dieses_idr, getContext());
            pieChartManagger.showSolidPieChartNew(yvals, colors);

        }

        private void showSWSickAnimal () {
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
    }
}

