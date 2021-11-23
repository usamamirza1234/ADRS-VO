package ast.adrs.vo.MainAuxilaries;

public class DModel_DiseaseModel {
    private String diseaseName;

    private int di;

    private int dr;

    public DModel_DiseaseModel(String diseaseName, int di, int dr) {
        this.diseaseName = diseaseName;
        this.di = di;
        this.dr = dr;
    }

    public String getDiseaseName() {
        return diseaseName;
    }

    public void setDiseaseName(String diseaseName) {
        this.diseaseName = diseaseName;
    }

    public int getDi() {
        return di;
    }

    public void setDi(int di) {
        this.di = di;
    }

    public int getDr() {
        return dr;
    }

    public void setDr(int dr) {
        this.dr = dr;
    }
}
