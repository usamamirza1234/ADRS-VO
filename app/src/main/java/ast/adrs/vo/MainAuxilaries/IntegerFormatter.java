package ast.adrs.vo.MainAuxilaries;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;

public class IntegerFormatter extends ValueFormatter {

    private PieChart pieChart;

    // Can be used to remove percent signs if the chart isn't in percent mode
    public IntegerFormatter(PieChart pieChart) {
        this.pieChart = pieChart;
    }

    @Override
    public String getFormattedValue(float value) {
        return ((int) value) + " %";
    }

    @Override
    public String getPieLabel(float value, PieEntry pieEntry) {
        if (pieChart != null && pieChart.isUsePercentValuesEnabled()) {
            // Converted to percent
            return getFormattedValue(value);
        } else {
            // raw value, skip percent sign
            return  ((int) value)+"";
        }
    }


}
