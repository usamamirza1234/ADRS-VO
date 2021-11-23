package ast.adrs.vo.MainAuxilaries;

import android.app.AlertDialog;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import ast.adrs.vo.IntroAuxilaries.WebServices.AppConstt;
import ast.adrs.vo.Utils.ChartManagers.BarChartManager;
import ast.adrs.vo.Utils.ChartManagers.PieChartManagerForLables;
import ast.adrs.vo.Utils.ChartManagers.PieChartManagger;
import ast.adrs.vo.Utils.CustomToast;
import ast.adrs.vo.Utils.IBadgeUpdateListener;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private final byte TOTAL_PERIOD = 3;

    private final byte CHB_TODAY = 0;
    private final byte CHB_YESTERDAY = 1;
    private final byte CHB_TOTAL = 2;
    private final String TAG = "PIE_CHART";
    TextView txvApply;
    AlertDialog alertDialog;
    TextView btnhomefrgsick;
    ArrayList<BarEntry> barEntriesArrayList;
    ArrayList<String> lableName;
    BarChart barChartView, barChartViewvertical;
    List<String> xAxisValues;
    private CheckBox[] arrchbFilterPeroid;
    private LinearLayout[] arrllFilterPeroid;
    private IBadgeUpdateListener mBadgeUpdateListener;
    private PieChart pieChart;
    private PieChart mBarChart_dieses_idr;
    private PieChart mBarChart_sick_animal;
//    private List<Integer> lstPieValues;
    private List<Integer> lstPieValuesIDR;
    private List<Integer> lstPieValuesSickAnimal;
    private List<String> xAxisValuesIDR ;
    private List<String> xAxisValuesOriginWise ;
    private Dialog popup;


    //Pie chart label ka kam jisye kia ha ab wisye krna ha..... code cleaned ... bar chart ka b

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_home, container, false);


        init();
        bindViews(frg);


        setDataForPie();
        setDataForBar();

        // showSWSickAnimal();

        //Bar

      //  showbarOriginWise();
       // showBarIDR();
        // showBarChart(frg);



        return frg;
    }

    private void setDataForPie()
    {


        lstPieValuesIDR.add(1);
        lstPieValuesIDR.add(4);
        lstPieValuesIDR.add(9);
        lstPieValuesIDR.add(10);

        lstPieValuesIDR.add(18);
        lstPieValuesIDR.add(5);
        lstPieValuesIDR.add(5);

        lstPieValuesIDR.add(7);
        lstPieValuesIDR.add(4);
        lstPieValuesIDR.add(4);
        lstPieValuesIDR.add(4);

        lstPieValuesIDR.add(4);
        lstPieValuesIDR.add(1);
        lstPieValuesIDR.add(2);
        lstPieValuesIDR.add(8);

        showPieChartFor_IDR(lstPieValuesIDR);
        lstPieValuesSickAnimal.add(15);
        lstPieValuesSickAnimal.add(75);
        lstPieValuesSickAnimal.add(250);
        lstPieValuesSickAnimal.add(540);

        showPieChartFor_SickAnimal(lstPieValuesSickAnimal);




    }


    private void setDataForBar()
    {


        showBarIDR(xAxisValuesIDR);


        showbarOriginWise(xAxisValuesOriginWise);






    }


    //region INIT
    private void init() {
        setToolbar();
        lstPieValuesIDR = new ArrayList<>();
        lstPieValuesSickAnimal = new ArrayList<>();
        barEntriesArrayList = new ArrayList<>();

        xAxisValuesIDR = new ArrayList<>();
        xAxisValuesOriginWise = new ArrayList<>();
        xAxisValues = new ArrayList<>();
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
    //endregion

    // har pie chart ka aisye krna ha label sb ko dainy han pr ager hide krny ha to srf yh function call krna ha ... pieChartManagerForLables.showLabeledPieChartHidden(yvals, colors);

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void bindViews(View frg) {
        mBarChart_dieses_idr = frg.findViewById(R.id.frg_home_mpchart_dieses_idr);
        mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);
        barChartView = frg.findViewById(R.id.idr_barchart);

        barChartViewvertical = frg.findViewById(R.id.idr_baroriginwise);
        btnhomefrgsick = frg.findViewById(R.id.frg_home_frg_txv_sick);

        btnhomefrgsick = frg.findViewById(R.id.frg_home_frg_txv_sick);
        try {
//           btnhomefrgsick.setTooltipText("Sick");
        } catch (Exception e) {

        }


        // mBarChart_sick_animal = frg.findViewById(R.id.frg_home_mpchart_sick_animal);

        btnhomefrgsick.setOnClickListener(this);


    }

    //region PieChart
    private void showPieChartFor_IDR(List<Integer> lstPieValues) {
        //Set the number of each share
        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();




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

    private void showPieChartFor_SickAnimal(List<Integer> lstPieValues) {


        List<PieEntry> yvals = new ArrayList<>();
        List<Integer> colors = new ArrayList<>();



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


////
        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();
        ArrayList<BarEntry> yValueGroup2 = new ArrayList<>();
        BarChartManager barChartManager = new BarChartManager(barChartView, getContext());
        barChartManager.showBarChart(yValueGroup1, xAxisValues);
        barChartManager.showBarChart(yValueGroup2, xAxisValues);

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
                        barChartManager.showBarChart(yValueGroup1, xAxisValues);


                        break;


                    case "Large Animal":
                        CustomToast.showToastMessage(getActivity(), pe.getLabel() + " selected", Toast.LENGTH_LONG);


                        List<PieEntry> yvals = new ArrayList<>();
                        List<Integer> colors = new ArrayList<>();
                        if (lstPieValues.size() > 0)
                            lstPieValues.clear();

                        lstPieValues.add(1);
                        lstPieValues.add(4);
                        lstPieValues.add(9);
                        lstPieValues.add(10);


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
                            }

                        }


                        PieChartManagger pieChartManagger = new PieChartManagger(mBarChart_dieses_idr, getContext());
                        pieChartManagger.showSolidPieChartNew(yvals, colors);


                        break;


                    case "Aquine":
                        CustomToast.showToastMessage(getActivity(), pe.getLabel() + " selected", Toast.LENGTH_LONG);


                        yValueGroup2.add(new BarEntry(6f, 6f));
                        yValueGroup2.add(new BarEntry(7f, 2f));
                        yValueGroup2.add(new BarEntry(3f, 3f));
                        barChartManager.showBarChart(yValueGroup2, xAxisValues);


                        break;

                }

            }

            @Override
            public void onNothingSelected() {

            }
        });
    }

    //endregion

    //region Popup
    private void showPopupDialog() {
        popup = new Dialog(getActivity(), R.style.CustomIOSTransparentProgressDialog);
        popup.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        popup.setContentView(R.layout.lay_popup_filter);

        //ALG ALG BIND VIEW K FUNCTION BKIo k liye name diff ho to agy is module ko hat lgao ga
        bindViewsForPOPUP_FILTER(popup);


        popup.setCancelable(true);
        popup.show();
    }

    private void bindViewsForPOPUP_FILTER(Dialog popup) {

        arrllFilterPeroid = new LinearLayout[TOTAL_PERIOD];
        arrllFilterPeroid[CHB_TODAY] = popup.findViewById(R.id.lay_prog_ll_today);
        arrllFilterPeroid[CHB_YESTERDAY] = popup.findViewById(R.id.lay_prog_ll_yesterday);
        arrllFilterPeroid[CHB_TOTAL] = popup.findViewById(R.id.lay_prog_ll_total);
        for (int i = 0; i < TOTAL_PERIOD; i++)
            arrllFilterPeroid[i].setOnClickListener(this);


        arrchbFilterPeroid = new CheckBox[TOTAL_PERIOD];
        arrchbFilterPeroid[CHB_TODAY] = popup.findViewById(R.id.lay_prog_chb_today);
        arrchbFilterPeroid[CHB_YESTERDAY] = popup.findViewById(R.id.lay_prog_chb_yesterday);
        arrchbFilterPeroid[CHB_TOTAL] = popup.findViewById(R.id.lay_prog_chb_total);

        txvApply = popup.findViewById(R.id.lay_popup_txvApply);
        txvApply.setOnClickListener(this);


    }

    private void dismissProgDialog() {
        if (popup != null) {
            popup.dismiss();
        }
    }


    public void switchBottomTab(int tabNum) {
        for (int i = 0; i < TOTAL_PERIOD; i++) {
            arrchbFilterPeroid[i].setChecked(i == tabNum);
        }

    }
    //endregion


    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.frg_home_frg_txv_sick:

                showPopupDialog();
                break;
            case R.id.lay_popup_txvApply:

                dismissProgDialog();
                break;
            case R.id.lay_prog_ll_today:
                Collections.shuffle(lstPieValuesIDR);
                Collections.shuffle(lstPieValuesSickAnimal);

                showPieChartFor_IDR(lstPieValuesIDR);
                showPieChartFor_SickAnimal(lstPieValuesSickAnimal);
                switchBottomTab(CHB_TODAY);
                break;
            case R.id.lay_prog_ll_yesterday:
                Collections.shuffle(lstPieValuesIDR);
                Collections.shuffle(lstPieValuesSickAnimal);
                Collections.shuffle(lstPieValuesSickAnimal);

                showPieChartFor_IDR(lstPieValuesIDR);
                showPieChartFor_SickAnimal(lstPieValuesSickAnimal);

                showBarIDR( xAxisValues );
                showbarOriginWise(xAxisValues);
                
                switchBottomTab(CHB_YESTERDAY);
                break;
            case R.id.lay_prog_ll_total:
                Collections.shuffle(lstPieValuesIDR);
                Collections.shuffle(lstPieValuesSickAnimal);

                showPieChartFor_IDR(lstPieValuesIDR);
                showPieChartFor_SickAnimal(lstPieValuesSickAnimal);
                switchBottomTab(CHB_TOTAL);
                break;
        }
    }

    private void showBarIDR(List<String> xAxisValues ) {


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

        BarChartManager barChartManager = new BarChartManager(barChartView, getContext());
        barChartManager.showBarChart(yValueGroup1, xAxisValues);


    }

    private void showbarOriginWise(List<String> xAxisValues) {


        xAxisValues.add("Therileriosis");
        xAxisValues.add("Glander");
        xAxisValues.add("Rabies (Mad dog disease)");
        xAxisValues.add("Black quarter (black-leg)");
        xAxisValues.add("Foot and mouth disease");
        xAxisValues.add("Contagious  pleuropneumonia   ");
        xAxisValues.add("Brucellosis of sheep.");
        xAxisValues.add("Tetanus");
        xAxisValues.add("Anthrax");

        ArrayList<BarEntry> yValueGroup1 = new ArrayList<>();
        yValueGroup1.add(new BarEntry(1f, 24f));
        yValueGroup1.add(new BarEntry(2f, 31f));
        yValueGroup1.add(new BarEntry(3f, 4f));
        yValueGroup1.add(new BarEntry(4f, 2f));
        yValueGroup1.add(new BarEntry(5f, 10f));
        yValueGroup1.add(new BarEntry(6f, 14f));
        yValueGroup1.add(new BarEntry(7f, 7f));
        yValueGroup1.add(new BarEntry(8f, 15f));
        yValueGroup1.add(new BarEntry(9f, 5f));


        BarChartManager barChartManager = new BarChartManager(barChartViewvertical, getContext());
        barChartManager.showBarChartVertical(yValueGroup1, xAxisValues);


    }

//    private void showSWSickAnimal() {
//        //Set the number of each share
//        List<PieEntry> yvals = new ArrayList<>();
//        List<Integer> colors = new ArrayList<>();
//
//        if (lstPieValues.size() > 0)
//            lstPieValues.clear();
//
//
//        lstPieValues.add(15);
//        lstPieValues.add(75);
//        lstPieValues.add(250);
//        lstPieValues.add(540);
//
//
//        for (int i = 0; i < lstPieValues.size(); i++) {
//            yvals.add(new PieEntry(lstPieValues.get(i)));
//            if (i == 0) {
//                colors.add(getActivity().getResources().getColor(R.color.thm_green_dark_graph_indicator));
//            } else if (i == 1) {
//                colors.add(getActivity().getResources().getColor(R.color.thm_blue_dark_graph_indicator));
//            } else if (i == 2) {
//                colors.add(getActivity().getResources().getColor(R.color.thm_light_green_dark_graph_indicator));
//            } else if (i == 3) {
//                colors.add(getActivity().getResources().getColor(R.color.app_blue_light));
//            }
//
//
//        }
//
//
//        PieChartManagger pieChartManagger = new PieChartManagger(mBarChart_sick_animal, getContext());
//        pieChartManagger.showSolidPieChartNew(yvals, colors);
//    }


}