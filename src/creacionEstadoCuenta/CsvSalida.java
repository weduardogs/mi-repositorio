package creacionEstadoCuenta;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import pojos.DatosEstadoCuenta;
import pojos.SaldosTotales;

public class CsvSalida {

    public CsvSalida() {

    }

    public void escribeArchivo(ArrayList<DatosEstadoCuenta> cuentasPorMes) {
        ArrayList<SaldosTotales> arraySaldosTotales = new ArrayList<SaldosTotales>();
        FileWriter fw = null;
        PrintWriter salida = null;
        String nombreArchivo = "C:/archivo/SaldosTotales.cvs";

        ordenarCuentasMenMay(cuentasPorMes);
        arraySaldosTotales = calculaSaldosXCuenta(cuentasPorMes);

        try {
            fw = new FileWriter(nombreArchivo, true);
            salida = new PrintWriter(fw);
            
            for(int i=0; i<arraySaldosTotales.size(); i++){
                salida.println(arraySaldosTotales.get(i));   
            }
            
            salida.flush();
                      
        } catch (IOException ex) {
            ex.getStackTrace();
        } finally {
            salida.close();
        }

    }

    private ArrayList<SaldosTotales> calculaSaldosXCuenta(ArrayList<DatosEstadoCuenta> cuentasSumadas) {
        ArrayList<SaldosTotales> arraySaldosTotales = new ArrayList<SaldosTotales>();
        double totalCargosCuentas = 0;
        double totalAbonosCuentas = 0;
        double saldoTotal;
        int sizeCuentas = cuentasSumadas.size();

        for (int i = 0; i < sizeCuentas - 1; i++) {

            if (Integer.parseInt(cuentasSumadas.get(i).getCuenta()) == Integer.parseInt(cuentasSumadas.get(i + 1).getCuenta())) {
                if (cuentasSumadas.get(i).getOperacion() == 0) {
                    totalCargosCuentas = cuentasSumadas.get(i).getMonto() + cuentasSumadas.get(i + 1).getMonto();
                } else {
                    totalAbonosCuentas = cuentasSumadas.get(i).getMonto() + cuentasSumadas.get(i + 1).getMonto();
                }
            } else {

                saldoTotal = totalAbonosCuentas - totalCargosCuentas;
                if (saldoTotal == 0) {
                    saldoTotal = cuentasSumadas.get(i).getMonto();
                }

                arraySaldosTotales.add(new SaldosTotales(Integer.parseInt(cuentasSumadas.get(i).getCuenta()), saldoTotal));
                totalAbonosCuentas = 0;
                totalCargosCuentas = 0;
            }
        }
        return arraySaldosTotales;
    }
    

    private ArrayList<DatosEstadoCuenta> ordenarCuentasMenMay(ArrayList<DatosEstadoCuenta> cuentasPorMes) {
        int sizeArray = cuentasPorMes.size();
        DatosEstadoCuenta aux1;

        for (int i = 0; i < sizeArray - 1; i++) {
            for (int j = 0; j < sizeArray - i - 1; j++) {
                if (Integer.parseInt(cuentasPorMes.get(j + 1).getCuenta()) < Integer.parseInt(cuentasPorMes.get(j).getCuenta())) {
                    aux1 = cuentasPorMes.get(j + 1);
                    cuentasPorMes.set(j + 1, cuentasPorMes.get(j));
                    cuentasPorMes.set(j, aux1);
                }
            }
        }
        return cuentasPorMes;
    }

}
