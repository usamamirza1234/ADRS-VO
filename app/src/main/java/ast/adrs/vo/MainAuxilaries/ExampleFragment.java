package ast.adrs.vo.MainAuxilaries;

import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.armoomragames.denketa.R;
import com.github.mikephil.charting.charts.BarChart;
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
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import ast.adrs.vo.MainAuxilaries.Adapter.IDRFootMouthRcvAdapter;
import ast.adrs.vo.MainAuxilaries.DModels.DModelIDRFootMouth;

public class ExampleFragment extends Fragment {

    View view;
    IDRFootMouthRcvAdapter idrFootMouthRcvAdapter;
RecyclerView rcvIDRFootMouth;
    ArrayList<DModelIDRFootMouth> lstIDRFootMouth;
    public ExampleFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View frg = inflater.inflate(R.layout.fragment_example, container, false);

        init();
        bindViews(frg);
      //  populateIDRFootMouth(lstIDRFootMouth);
       // populateGraphData();

        return frg;
    }




    private void  init(){
     //   lstIDRFootMouth = new ArrayList<>();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void bindViews(View frg) {
      //  rcvIDRFootMouth = frg.findViewById(R.id.frg_IDR_rcvFootMouth);

     FloatingActionButton fab = (FloatingActionButton) frg.findViewById(R.id.fab);
     fab.setTooltipText("S");
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

        //
        ArrayList<BarEntry> yValueGroup2 = new ArrayList<>();
        ArrayList<BarEntry> yValueGroup3 = new ArrayList<>();

        // draw the graph
        BarDataSet barDataSet1;
        //
        BarDataSet barDataSet2;
        BarDataSet barDataSet3;


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

      //  barDataSet2.label = "2017"
        barDataSet2.setColors((getResources().getColor(R.color.gray)));

        barDataSet2.setDrawIcons(false);
        barDataSet2.setDrawValues(true);


      //  <-------------->
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

      //  <-------------->


///// Add a new Paramerter as a new row as barDataSet4 .... etc

        BarData barData = new BarData(barDataSet1);
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
        barData.setBarWidth(0.7f);

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
    }




    private void populateIDRFootMouth(ArrayList<DModelIDRFootMouth> mData) {

        //  rlIssue_Faced.setVisibility(View.VISIBLE);
        //   rlEY_Portal.setVisibility(View.GONE);



//        if (idrFootMouthRcvAdapter == null) {
//
//            idrFootMouthRcvAdapter = new IDRFootMouthRcvAdapter(getActivity(), mData, (eventId, position) -> {
//
//            });
//
//            LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//            rcvIDRFootMouth.setLayoutManager(linearLayoutManager);
//            rcvIDRFootMouth.setAdapter(idrFootMouthRcvAdapter);
//
//        } else {
//            idrFootMouthRcvAdapter.notifyDataSetChanged();
//        }
//
//
    }
}




//        BarChart barChart=  frg.findViewById(R.id.idr_barchart);
//
//
//        ArrayList<BarEntry> Visitor = new ArrayList<>();
//        Visitor.add(new BarEntry(20,4));
//        Visitor.add(new BarEntry(21,5));
//        Visitor.add(new BarEntry(22,8));
//        Visitor.add(new BarEntry(23,2));
//        Visitor.add(new BarEntry(24,1));
//        Visitor.add(new BarEntry(25,11));
//
//        BarDataSet barDataSet = new BarDataSet(Visitor,"Visitor");
//
//        barDataSet.setColors(ColorTemplate.MATERIAL_COLORS);
//        barDataSet.setValueTextSize(16f);
//        barDataSet.setStackLabels(new String[]{"Large","Small","Medium"});
//        barChart.setDragEnabled(true);
//        barDataSet.setValueTextColor(Color.BLACK);
//        BarData barData= new BarData(barDataSet);
//        barChart.setData(barData);
//        barChart.animateY(2000);
//        barChart.setFitBars(true);
//        barChart.getDescription().setEnabled(false);
//        barChart.setDrawValueAboveBar(true);
//        barData.setBarWidth(1f);