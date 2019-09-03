package es.us.myapplication;

public class AdapterView {
    private String nombreAdaptador;
    private Integer rssi;
    private String calidadSeñal;

    public AdapterView(String nombreAdaptador, Integer rssi, String calidadSeñal) {
        this.nombreAdaptador = nombreAdaptador;
        this.rssi = rssi;
        this.calidadSeñal = calidadSeñal;
    }

    public String getNombreAdaptador() {
        return nombreAdaptador;
    }

    public void setNombreAdaptador(String nombreAdaptador) {
        this.nombreAdaptador = nombreAdaptador;
    }

    public Integer getRssi() {
        return rssi;
    }

    public void setRssi(Integer rssi) {
        this.rssi = rssi;
    }

    public String getCalidadSeñal() {
        return calidadSeñal;
    }

    public void setCalidadSeñal(String calidadSeñal) {
        this.calidadSeñal = calidadSeñal;
    }
}
