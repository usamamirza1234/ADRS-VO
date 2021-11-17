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





public class PieChartManagger {
    public PieChart pieChart;
    Context context;


    public PieChartManagger(PieChart pieChart, Context context) {
        this.pieChart = pieChart;
        this.context = context;
        initPieChart();
    }

    private void initPieChart() {
        // Whether to show the middle hole
        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleRadius(80f);//Set the size of the middle hole
        // translucent circle
        pieChart.setTransparentCircleRadius(30f);
        pieChart.setTransparentCircleColor(context.getResources().getColor(R.color.transparent)); //Set the color of the translucent circle
        pieChart.setTransparentCircleAlpha(125); //Set the transparency of the semi-transparent circle

        //You can add text in the middle of the pie chart
        pieChart.setDrawCenterText(false);
        pieChart.setCenterText(""); //Set the middle text
        pieChart.setCenterTextColor(context.getResources().getColor(R.color.transparent)); //The color of the middle question
        pieChart.setCenterTextSizePixels(36);  //The size of the middle text px
        pieChart.setCenterTextRadiusPercent(1f);
        pieChart.setCenterTextTypeface(Typeface.DEFAULT); //The style of the middle text
        pieChart.setCenterTextOffset(0, 0); //Offset of middle text
//
//
        pieChart.setRotationAngle(0);// Initial rotation angle
        pieChart.setRotationEnabled(false);// Can be rotated manually
        pieChart.setUsePercentValues(false);//Displayed as a percentage
        pieChart.getDescription().setEnabled(false); //Cancel the description in the lower right corner
//
        //Whether to display the text description of each part
        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(Color.RED); //The color of the description text
        pieChart.setEntryLabelTextSize(14);//Description text size
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD); //Description style
//
        //The offset of the figure relative to the up, down, left and right
        pieChart.setExtraOffsets(0, 0, 0, 20);
        //The background color of the icon
        pieChart.setBackgroundColor(Color.TRANSPARENT);
// Set the friction coefficient of pieChart chart rotation resistance [0,1]
        pieChart.setDragDecelerationFrictionCoef(12.75f);


//
       // //Get legend

        Legend legend = pieChart.getLegend();
        pieChart.getLegend().setEnabled(false); //disable bottom text
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);  //Set the legend horizontal display
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM); //BOTTOM
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT); //RIGHT to it

        legend.setXEntrySpace(7f);//x axis spacing
        legend.setYEntrySpace(10f); //Y axis spacing
        legend.setYOffset(10f);  //Y offset of the legend
        legend.setXOffset(10f);  //Offset of legend x
        legend.setTextColor(context.getResources().getColor(R.color.black)); //The color of the legend text
        legend.setTypeface(Typeface.DEFAULT_BOLD);
        legend.setTextSize(13);  //The size of the legend text

    }

    public void showSolidPieChartNew(List<PieEntry> yvals, List<Integer> colors) {
        //Data collection

        PieDataSet dataSet = new PieDataSet(yvals, "Expense Category");

        PieData data = new PieData(dataSet);
        data.setDrawValues(true);
        data.setValueFormatter(new IntegerFormatter(pieChart));
        data.setValueTextSize(12f);
        data.setValueTextColor(Color.BLACK);

        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);



        //Fill the color of each area
        dataSet.setColors(colors);
        //Whether to display the value on the graph
        dataSet.setDrawValues(true);
        // The size of the text
        dataSet.setValueTextSize(14);
        // text color
        dataSet.setValueTextColor(context.getResources().getColor(R.color.white));
        // Style of text
        dataSet.setValueTypeface(Typeface.DEFAULT_BOLD);
        // Set the gap before each
        dataSet.setSliceSpace(2);
        //Set the distance that changes when the pie item is selected
        dataSet.setSelectionShift(3f);


        pieChart.setDrawHoleEnabled(false);
        pieChart.setUsePercentValues(false);
        pieChart.setEntryLabelTextSize(12);
        pieChart.setEntryLabelColor(Color.BLACK);
//        pieChart.setCenterText("Spending by Category");
        pieChart.setCenterTextSize(24);
        pieChart.getDescription().setEnabled(false);

        Legend l = pieChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.VERTICAL);
        l.setDrawInside(false);
        l.setEnabled(false);


        pieChart.setData(data);
        pieChart.invalidate();

        pieChart.animateY(1400, Easing.EaseInOutQuad);
    }



    public void showSolidPieChart(List<PieEntry> yvals, List<Integer> colors) {
        //Data collection
        PieDataSet dataset = new PieDataSet(yvals, "");
        //Fill the color of each area
        dataset.setColors(colors);
        //Whether to display the value on the graph
        dataset.setDrawValues(true);
// The size of the text
        dataset.setValueTextSize(14);
// text color
        dataset.setValueTextColor(context.getResources().getColor(R.color.black));
// Style of text
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

// When the value position is the outer edge, it means the length of the first half of the line.
        dataset.setValueLinePart1Length(0.58f);
// When the value position is the outer line, it indicates the length of the second half of the line.
        dataset.setValueLinePart2Length(0.4f);
// When ValuePosits is OutsiDice, the offset is indicated as a percentage of the slice size
        dataset.setValueLinePart1OffsetPercentage(80f);
        // When the value position is the outer line, it indicates the color of the line.
        dataset.setValueLineColor(context.getResources().getColor(R.color.thm_yellow_line_graph_indicator));
// Set the position of Y value inside or outside the circle
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
// Set the color of the Y axis description line and the filled area to be the same
        //dataset.setUsingSliceColorAsValueLineColor(false);
// Set the gap before each
        dataset.setSliceSpace(3);

        //Set the distance that changes when the pie item is selected
        dataset.setSelectionShift(5f);
        //Data input
        PieData pieData = new PieData(dataset);
// The formatted data is%
        pieData.setValueFormatter(new MyValueFormatter());

        pieChart.setUsePercentValues(false);



// show attempt
        pieChart.setData(pieData);
    }

    /**
     * Display ring
     *
     * @param yvals
     * @param colors
     */
    public void showRingPieChart(List<PieEntry> yvals, List<Integer> colors) {
        //Show as ring
        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleRadius(90f);//Set the size of the middle hole

        //Data collection
        PieDataSet dataset = new PieDataSet(yvals, "");
        //Fill the color of each area
        dataset.setColors(colors);
        //Whether to display the value on the graph
        dataset.setDrawValues(false);



// The size of the text
        dataset.setValueTextSize(0);
// The color of the text
        dataset.setValueTextColor(Color.RED);
// Style of text
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

// When the value position is the outer edge, it means the length of the first half of the line.
        dataset.setValueLinePart1Length(0.4f);
// When the value position is the outer line, it indicates the length of the second half of the line.
        dataset.setValueLinePart2Length(0.4f);
// When ValuePosits is OutsiDice, the offset is indicated as a percentage of the slice size
        dataset.setValueLinePart1OffsetPercentage(80f);
        // When the value position is the outer line, it indicates the color of the line.
      //  dataset.setValueLineColor(context.getResources().getColor(R.color.transparent)); //The color of the middle question

// Set the position of Y value inside or outside the circle
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
// Set the color of the Y axis description line and the filled area to be the same
        //dataset.setUsingSliceColorAsValueLineColor(false);
// Set the gap before each
        dataset.setSliceSpace(2);

        //Set the distance that changes when the pie item is selected
        dataset.setSelectionShift(5f);
        //Data input
        PieData pieData = new PieData(dataset);
// The formatted data is%
     //  pieData.setValueFormatter(new PercentFormatter());
// show attempt
        pieChart.setData(pieData);

    }





}
