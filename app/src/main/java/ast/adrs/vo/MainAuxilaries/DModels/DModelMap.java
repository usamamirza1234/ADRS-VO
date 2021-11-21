package ast.adrs.vo.MainAuxilaries.DModels;


public class DModelMap {
    double lat;
    double lon;

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
