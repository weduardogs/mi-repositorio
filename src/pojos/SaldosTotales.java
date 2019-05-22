package pojos;

public class SaldosTotales {
    private int cuentas;
    private double saldoTotal;
    
    public SaldosTotales(int cuentas, double saldos){
        this.cuentas = cuentas;
        this.saldoTotal = saldos;
    }

    public int getCuentas() {
        return cuentas;
    }

    public void setCuentas(int cuentas) {
        this.cuentas = cuentas;
    }

    public double getSAldos() {
        return saldoTotal;
    }

    public void setSaldos(int saldos) {
        this.saldoTotal = saldos;
    }
    
    @Override
    public String toString(){
        return cuentas + "," + saldoTotal;
    }
}
