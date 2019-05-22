package creacionEstadoCuenta;

import java.util.ArrayList;
import pojos.DatosEstadoCuenta;

public class CalcularEstado {
    
    public CalcularEstado(){
        
    }
    
    public void sumatoriaPorMes(ArrayList<DatosEstadoCuenta> arregloCuentas, int mes){
        ArrayList<DatosEstadoCuenta> cuentasPorMes = new ArrayList<DatosEstadoCuenta>();
        double totalCargos = 0;
        double totalAbonos = 0;
        double sumaTotal;
        String sumaTotalAbs;
        
        int tamanioArray = arregloCuentas.size();
        
        for (int i = 0; i < tamanioArray; i++){
            //System.out.println(arregloCuentas.get(i).getFecha().getMonth());
            
           if (arregloCuentas.get(i).getFecha().getMonth()+1 == mes){
               cuentasPorMes.add(arregloCuentas.get(i));
                        
               if (arregloCuentas.get(i).getOperacion() == 0){                 
                   totalCargos += arregloCuentas.get(i).getMonto();
               } else if (arregloCuentas.get(i).getOperacion() == 1){
                   totalAbonos += arregloCuentas.get(i).getMonto();
               }
           }
        }
        
        sumaTotal = totalAbonos - totalCargos;

        if (sumaTotal < 0){
            sumaTotal = Math.abs(sumaTotal);
            sumaTotalAbs = "(" + sumaTotal + ")";
        } else {
            sumaTotalAbs = String.valueOf(sumaTotal);
        }
        
        CsvSalida creaArchivo = new CsvSalida();
        creaArchivo.escribeArchivo(cuentasPorMes);
        
        System.out.println("Mes: " + mes);
        System.out.println("Total Cargos: " + totalCargos);
        System.out.println("Total Abonos: " + totalAbonos);
        System.out.println("Total: " + sumaTotalAbs);

    }
    
    
    
    
}
