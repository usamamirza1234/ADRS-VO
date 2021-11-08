package ast.adrs.vo.MainAuxilaries;

public class MonthDetails {
 String month;
 int sale;

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public MonthDetails(String month, int sale) {
        this.month = month;
        this.sale = sale;
    }
}
