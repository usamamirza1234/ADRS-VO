package ast.adrs.vo.MainAuxilaries.DModels;


public class DModelMap {
    double lat;
    double lon;



    private String dateFrom;

    private String dateTo;

    private int areaCode;

    private String mouzaCode;

    private String mouzaName;

    private double latitude;

    private double longitude;

    private String species;

    private String disease;

    private int totalAnimals;

    private int sickAnimals;

    private int deadAnimals;

    private String dirReportId;

    public DModelMap(double lat, double lon, String dateFrom, String dateTo, int areaCode, String mouzaCode, String mouzaName, double latitude, double longitude, String species, String disease, int totalAnimals, int sickAnimals, int deadAnimals, String dirReportId) {
        this.lat = lat;
        this.lon = lon;
        this.dateFrom = dateFrom;
        this.dateTo = dateTo;
        this.areaCode = areaCode;
        this.mouzaCode = mouzaCode;
        this.mouzaName = mouzaName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.species = species;
        this.disease = disease;
        this.totalAnimals = totalAnimals;
        this.sickAnimals = sickAnimals;
        this.deadAnimals = deadAnimals;
        this.dirReportId = dirReportId;
    }

    public String getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(String dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getDateTo() {
        return dateTo;
    }

    public void setDateTo(String dateTo) {
        this.dateTo = dateTo;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    public String getMouzaCode() {
        return mouzaCode;
    }

    public void setMouzaCode(String mouzaCode) {
        this.mouzaCode = mouzaCode;
    }

    public String getMouzaName() {
        return mouzaName;
    }

    public void setMouzaName(String mouzaName) {
        this.mouzaName = mouzaName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public String getSpecies() {
        return species;
    }

    public void setSpecies(String species) {
        this.species = species;
    }

    public String getDisease() {
        return disease;
    }

    public void setDisease(String disease) {
        this.disease = disease;
    }

    public int getTotalAnimals() {
        return totalAnimals;
    }

    public void setTotalAnimals(int totalAnimals) {
        this.totalAnimals = totalAnimals;
    }

    public int getSickAnimals() {
        return sickAnimals;
    }

    public void setSickAnimals(int sickAnimals) {
        this.sickAnimals = sickAnimals;
    }

    public int getDeadAnimals() {
        return deadAnimals;
    }

    public void setDeadAnimals(int deadAnimals) {
        this.deadAnimals = deadAnimals;
    }

    public String getDirReportId() {
        return dirReportId;
    }

    public void setDirReportId(String dirReportId) {
        this.dirReportId = dirReportId;
    }


    public DModelMap() {
    }

    public DModelMap(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;

    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLon() {
        return lon;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }
}
