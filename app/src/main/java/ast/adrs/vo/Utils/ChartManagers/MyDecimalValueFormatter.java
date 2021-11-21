package ast.adrs.vo.Utils.ChartManagers;

import com.github.mikephil.charting.formatter.ValueFormatter;

import java.text.DecimalFormat;

public class MyDecimalValueFormatter extends ValueFormatter {

    private DecimalFormat mFormat;

    public MyDecimalValueFormatter() {
        mFormat = new DecimalFormat("#");
    }

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(value);
    }
}