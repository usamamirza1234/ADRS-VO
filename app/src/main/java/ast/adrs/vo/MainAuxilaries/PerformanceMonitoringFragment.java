package ast.adrs.vo.MainAuxilaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.Utils.ChartManagers.BarChartManager;
import ast.adrs.vo.Utils.ChartManagers.PieChartManagger;
import ast.adrs.vo.Utils.IBadgeUpdateListener;

public class PerformanceMonitoringFragment extends Fragment {
    private IBadgeUpdateListener mBadgeUpdateListener;

    private List<Integer> lstPieValues;
    private PieChart mPieChart_intimations_processed_district_faisalabad;
    private PieChart mPieChart_intimations_processed;
    BarChart   mBarChart_DivisionWiseDiseaseInitimationProcessed,mBarChart_TehsilFaisalabadMuzzaWiseIntimationProcessed;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_performance_monitoring, container, false);

        init();
        bindViews(frg);
        showPieIntimationsProcessed();
        showDivisionWiseDiseaseInitimationProcessed();
        showpieDistrictFaisalabadIntimationsProcessed();
        showTehsilFaisalabadMuzzaWiseIntimationProcessed();

        return frg;
    }




    private void init(){
        lstPieValues = new ArrayList<>();

    }

    private void bindViews(View frg) {
        mPieChart_intimations_processed = frg.findViewById(R.id.frg_home_mpchart_intimations_processed);
        mPieChart_intimations_processed_district_faisalabad = frg.findViewById(R.id.frg_home_mpchart_intimations_processed_district_faisalabad);

        mBarChart_DivisionWiseDiseaseInitimationProcessed = frg.findViewById(R.id.DivisionWiseDiseaseInitimationProcessed_barchart);
        mBarChart_TehsilFaisalabadMuzzaWiseIntimationProcessed = frg.findViewById(R.id.TehsilFaisalabadMuzzaWiseIntimationProcessed_barchart);


    }




    private void showPieIntimationsProcessed() {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        if (lstPieValues.size() > 0)
            lstPieValues.clear();



        lstPieValues.add(16);
        lstPieValues.add(7);
        lstPieValues.add(2);
        lstPieValues.add(9);




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
            }

        }


        PieChartManagger pieChartManagger = new PieChartManagger(mPieChart_intimations_processed, getContext());
        pieChartManagger.showSolidPieChartNew(yvals, colors);
    }



    private void  showpieDistrictFaisalabadIntimationsProcessed() {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        if (lstPieValues.size() > 0)
            lstPieValues.clear();

        lstPieValues.add(16);
        lstPieValues.add(7);
        lstPieValues.add(2);
        lstPieValues.add(9);


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
            }

        }


        PieChartManagger pieChartManagger = new PieChartManagger(mPieChart_intimations_processed_district_faisalabad, getContext());
        pieChartManagger.showSolidPieChartNew(yvals, colors);
    }


    private void  showDivisionWiseDiseaseInitimationProcessed(){


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

        BarChartManager barChartManager = new BarChartManager(mBarChart_DivisionWiseDiseaseInitimationProcessed, getContext());
        barChartManager.showBarChart(yValueGroup1, xAxisValues);

    }


    private void   showTehsilFaisalabadMuzzaWiseIntimationProcessed(){

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

        yValueGroup1.add(new BarEntry(1f, 1f));
        yValueGroup1.add(new BarEntry(2f, 1f));
        yValueGroup1.add(new BarEntry(3f, 1f));
        yValueGroup1.add(new BarEntry(4f, 1f));
        yValueGroup1.add(new BarEntry(5f, 1f));
        yValueGroup1.add(new BarEntry(6f, 0.25f));
        yValueGroup1.add(new BarEntry(7f, 2f));
        yValueGroup1.add(new BarEntry(8f, 1f));


        BarChartManager barChartManager = new BarChartManager(mBarChart_TehsilFaisalabadMuzzaWiseIntimationProcessed, getContext());
        barChartManager.showBarChart(yValueGroup1, xAxisValues);


    }











}