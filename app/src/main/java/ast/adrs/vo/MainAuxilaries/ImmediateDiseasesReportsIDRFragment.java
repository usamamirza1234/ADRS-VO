package ast.adrs.vo.MainAuxilaries;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.MainAuxilaries.Adapter.IDRFootMouthRcvAdapter;
import ast.adrs.vo.MainAuxilaries.DModels.DModelIDRFootMouth;
import ast.adrs.vo.Utils.ChartManagers.BarChartManager;
import ast.adrs.vo.Utils.ChartManagers.PieChartManagerForLables;
import ast.adrs.vo.Utils.ChartManagers.PieChartManagger;
import ast.adrs.vo.Utils.IBadgeUpdateListener;


public class ImmediateDiseasesReportsIDRFragment extends Fragment {
    private IBadgeUpdateListener mBadgeUpdateListener;

    private List<Integer> lstPieValues;
    private PieChart mBarChart_district_faisalabad;
    private PieChart mBarChart_foot_mouth;
    RecyclerView rcvIDRFootMouth;
    ArrayList<DModelIDRFootMouth> lstIDRFootMouth;
    IDRFootMouthRcvAdapter idrFootMouthRcvAdapter;

    BarChart mBarChartDistrictwiseDiseaseReport;
    BarChart mBarCharttehsilfasialabad;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_immediate_diseases_reports_i_d_r, container, false);

        init();
        bindViews(frg);
        showpieFootMouth();
        showDistrictwiseDiseaseReport();
        showpieDistrictFaisalabad();
        showTehsilFaisalabad();
        populateIDRFootMouth(lstIDRFootMouth);
        return frg;
    }




    private void init(){
        lstPieValues = new ArrayList<>();
        lstIDRFootMouth = new ArrayList<>();
        setToolbar();
    }

    private void bindViews(View frg) {

        rcvIDRFootMouth = frg.findViewById(R.id.frg_IDR_rcvFootMouth);


        mBarChart_foot_mouth = frg.findViewById(R.id.frg_home_mpchart_foot_mouth);
        mBarChart_district_faisalabad = frg.findViewById(R.id.frg_home_mpchart_district_faisalabad);
        mBarChartDistrictwiseDiseaseReport = frg.findViewById(R.id.districtwisediseasereport_barchart);
        mBarCharttehsilfasialabad = frg.findViewById(R.id.tehsilfasialabad_barchart);


    }




    private void showpieFootMouth() {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();


        List<Integer> lstPieValues = new ArrayList<>();
        lstPieValues.add(58);
        lstPieValues.add(59);
        lstPieValues.add(25);
        lstPieValues.add(55);

        lstPieValues.add(28);
        lstPieValues.add(38);
        lstPieValues.add(43);
        lstPieValues.add(70);
        lstPieValues.add(50);



        List<String> lstPieLabels = new ArrayList<>();
        lstPieLabels.add("Sargodha");
        lstPieLabels.add("Sahiwal");
        lstPieLabels.add("Rawalpindi");
        lstPieLabels.add("Multan");
        lstPieLabels.add("Lahore");
        lstPieLabels.add("Gujranwala");
        lstPieLabels.add("Faisalabad");
        lstPieLabels.add("DG Khan");
        lstPieLabels.add("Bhawalpur");

        for (int i = 0; i < lstPieValues.size(); i++) {
            yvals.add(new PieEntry(lstPieValues.get(i),lstPieLabels.get(i)));
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
            }

        }

        PieChartManagerForLables pieChartManagerForLables = new PieChartManagerForLables(mBarChart_foot_mouth, getContext());
        pieChartManagerForLables.showLabeledPieChart(yvals, colors);


    }



    private void showpieDistrictFaisalabad() {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();

        if (lstPieValues.size() > 0)
            lstPieValues.clear();



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
        pieChartManagger.showdonutChartNew(yvals, colors);
    }


    private void  showDistrictwiseDiseaseReport(){

        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("Therileriosis");
        xAxisValues.add("Glander");
        xAxisValues.add("Rabies (Mad dog disease)");
        xAxisValues.add("Black quarter (black-leg)");
        xAxisValues.add("Foot and mouth disease");
        xAxisValues.add("Contagious  pleuropneumonia");
        xAxisValues.add("Brucellosis of sheep.");
        xAxisValues.add("Tetanus");
        xAxisValues.add("Anthrax");
        xAxisValues.add("Blue tongue");
        xAxisValues.add("African swine fever");
        xAxisValues.add("Transmissible spongiform ");

        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();

        yValueGroup1.add(new BarEntry(1f, new float[] { 19f,9f,11f, 12f }));
        yValueGroup1.add(new BarEntry(2f, new float[] { 20f,10f,11f, 12f }));
        yValueGroup1.add(new BarEntry(3f, new float[] { 18f,11f,11f, 12f }));
        yValueGroup1.add(new BarEntry(4f, new float[] { 12f,12f,11f, 12f }));
        yValueGroup1.add(new BarEntry(5f, new float[] { 13f,13f,11f, 12f }));
        yValueGroup1.add(new BarEntry(7f, new float[] { 21f,14f,11f, 12f }));
        yValueGroup1.add(new BarEntry(8f, new float[] { 22f,15f,11f, 12f }));
        yValueGroup1.add(new BarEntry(9f, new float[] { 10f,17f,11f, 12f }));

//        yValueGroup1.add(new BarEntry(2f, 12f));
//        yValueGroup1.add(new BarEntry(3f, 13f));
//        yValueGroup1.add(new BarEntry(4f, 14f));
//        yValueGroup1.add(new BarEntry(5f, 15f));
//        yValueGroup1.add(new BarEntry(6f, 11f));
//        yValueGroup1.add(new BarEntry(7f, 11f));
//        yValueGroup1.add(new BarEntry(8f, 12f));
//        yValueGroup1.add(new BarEntry(9f, 13f));
//        yValueGroup1.add(new BarEntry(10f, 14f));
//        yValueGroup1.add(new BarEntry(11f, 15f));

        BarChartManager barChartManager = new BarChartManager(mBarChartDistrictwiseDiseaseReport, getContext());
        // TODO: 26-Nov-21 change it into showbarchart and uncomment the values of normal chart 
        barChartManager.showStackedBarChatHorizontal(yValueGroup1, xAxisValues);

        mBarChartDistrictwiseDiseaseReport.setOnChartValueSelectedListener( new OnChartValueSelectedListener() {

            @Override
            public void onValueSelected(Entry e, Highlight h) {



            }

            @Override
            public void onNothingSelected() {

            }
        });

    }



    private void  showTehsilFaisalabad(){


        List<String> xAxisValues = new ArrayList<>();
        xAxisValues.add("Kahna Naag");
        xAxisValues.add("Semaa Naag");
        xAxisValues.add("Majiwal");
        xAxisValues.add("Naag  Kalan");
        xAxisValues.add("Arori Blochan");
        xAxisValues.add("Chugal Pura");
        xAxisValues.add("Bhook Pura");
        xAxisValues.add("Pharala");


        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();

        yValueGroup1.add(new BarEntry(1f, 1f));
        yValueGroup1.add(new BarEntry(2f, 1f));
        yValueGroup1.add(new BarEntry(3f, 1f));
        yValueGroup1.add(new BarEntry(4f, 1f));
        yValueGroup1.add(new BarEntry(5f, 1f));
        yValueGroup1.add(new BarEntry(6f, 0.25f));
        yValueGroup1.add(new BarEntry(7f, 2f));
        yValueGroup1.add(new BarEntry(8f, 1f));
        BarChartManager barChartManager = new BarChartManager(mBarCharttehsilfasialabad, getContext());
        barChartManager.showBarChart(yValueGroup1, xAxisValues);

    }


    private void populateIDRFootMouth(ArrayList<DModelIDRFootMouth> mData) {




            idrFootMouthRcvAdapter=null;
        if (idrFootMouthRcvAdapter == null) {

            idrFootMouthRcvAdapter = new IDRFootMouthRcvAdapter(getActivity(), mData, (eventId, position) -> {

            });

            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
            rcvIDRFootMouth.setLayoutManager(linearLayoutManager);
            rcvIDRFootMouth.setAdapter(idrFootMouthRcvAdapter);

        } else {
            idrFootMouthRcvAdapter.notifyDataSetChanged();
        }


    }



    void setToolbar() {

        try {
            mBadgeUpdateListener = (IBadgeUpdateListener) getActivity();
        } catch (ClassCastException castException) {
            castException.printStackTrace(); // The activity does not implement the listener
        }
        if (getActivity() != null && isAdded()) {
//            mBadgeUpdateListener.setToolbarState(AppConstt.ToolbarState.TOOLBAR_BACK_HIDDEN);
            mBadgeUpdateListener.setHeaderTitle("Immediate Diseases Report -IDR");

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