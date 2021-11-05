package ast.adrs.vo.MainAuxilaries;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.IntroAuxilaries.WebServices.AppConstt;
import ast.adrs.vo.Utils.IBadgeUpdateListener;


public class HomeFragment extends Fragment  {
    private IBadgeUpdateListener mBadgeUpdateListener;
    private PieChart pieChart;
    //  private BarChart barChart;
    HorizontalBarChart barChart;
    private PieChart mBarChart_dieses_idr;
    private PieChart mBarChart_sick_animal;
    private List<Integer> lstPieValues;
    int InProgress, Mapped;
    //   Database database;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_home, container, false);


        init();
        bindViews(frg);
        showExampleGraph(frg);
        // showBarChart(frg);
         SetData(5,5);
        showIDR();
        // showSWSickAnimal();
        return frg;

        /////


    }

    private void bindViews(View frg) {
        mBarChart_dieses_idr = frg.findViewById(R.id.frg_home_mpchart_dieses_idr);

        // mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);


         barChart= (HorizontalBarChart) frg.findViewById(R.id.idr_barchart);



    }

    private void showExampleGraph(View frg) {
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
//barchart
//    private void showBarChart(View frg)
//    {
//        barChart = frg.findViewById(R.id.idr_barchart);
//        setupPieChart();
//
//        loadbarChartData();
//    }
//
//
//
//    private void loadbarChartData() {
//        ArrayList<BarEntry> entries = new ArrayList<>();
//        entries.add(new BarEntry(4f, 0));
//        entries.add(new BarEntry(8f, 1));
//        entries.add(new BarEntry(6f, 2));
//        entries.add(new BarEntry(12f, 3));
//        entries.add(new BarEntry(18f, 4));
//        entries.add(new BarEntry(9f, 5));
//
//        BarDataSet dataset = new BarDataSet(entries, "# of Calls");
//       // dataset.setBarSpacePercent(35f);
//        ArrayList<String> labels = new ArrayList<String>();
//        labels.add("January");
//        labels.add("February");
//        labels.add("March");
//        labels.add("April");
//        labels.add("May");
//        labels.add("June");
//
////
//        BarData data = new BarData((IBarDataSet) labels, dataset);
//        data.setValueTextSize(20f);
//        data.setDrawValues(true);
//        barChart.setData(data);
//        barChart.animateY(5000);
//        barChart.setDrawValueAboveBar(false);
//    }


    private void showIDR() {
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


    private void SetData(int count, int range) {

        ArrayList<BarEntry> yVals = new ArrayList<>();
        float barWidth = 5;
        float spaceForBar = 10f;
        ArrayList<BarEntry> entries = new ArrayList<>();
        entries.add(new BarEntry(1,40f ));
        entries.add(new BarEntry(2,45f ));
        entries.add(new BarEntry(3,30f ));

        for (int i = 0; i < count; i++) {
            float val = (float) (Math.random() * count)+20;
//            float val1 = (float) (Math.random() * count)+1;
//            float val2 = (float) (Math.random() * count)+2;
//            float val3 = (float) (Math.random() * count)+3;
            yVals.add(new BarEntry(i * spaceForBar, val));
//            yVals.add(new BarEntry(i * spaceForBar, val1));
//            yVals.add(new BarEntry(i * spaceForBar, val2));
//            yVals.add(new BarEntry(i * spaceForBar, val3));
        }

            BarDataSet set1;
            set1 = new BarDataSet(yVals, "Immediate Diseases Report");
            set1.setDrawIcons(false);
            set1.setStackLabels(new String[]{"L","S","M"});
            set1.setColors(ColorTemplate.JOYFUL_COLORS);
            BarData data = new BarData(set1);
            barChart.setData(data);
            data.setValueFormatter(new MyValueFormatter());

            barChart.invalidate();
            barChart.setFitBars(true);
            barChart.getDescription().setEnabled(false);
            barChart.setMaxVisibleValueCount(50);
            barChart.setDrawValueAboveBar(true);
            data.setBarWidth(barWidth);
            barChart.setPinchZoom(false);


    ///


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