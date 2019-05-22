package pojos;


import java.util.Date;

public class DatosEstadoCuenta {
    
    private String cuenta;
    private double monto;
    private int operacion;
    private Date fecha;
    
    public DatosEstadoCuenta(String cuenta, double monto, int operacion, Date fecha){
       this.cuenta = cuenta;
       this.monto = monto;
       this.operacion = operacion;
       this.fecha = fecha;      
    }

    public String getCuenta() {
        return cuenta;
    }

    public void setCuenta(String cuenta) {
        this.cuenta = cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public int getOperacion() {
        return operacion;
    }

    public void setOperacion(int operacion) {
        this.operacion = operacion;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }
    
    @Override
    public String toString(){
        return cuenta;
    }
    
}
