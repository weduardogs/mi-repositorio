package creacionEstadoCuenta;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import pojos.DatosEstadoCuenta;

public class CsvChooser {

    public static final String SEPARADOR = ",";

    public CsvChooser() {
        openChooser();
    }

    private void openChooser() {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivo de texto CSV", "CSV", "csv");
        chooser.setFileFilter(filter);
        chooser.showOpenDialog(chooser);

        File abreArchivo = chooser.getSelectedFile();
        BufferedReader bufferLectura = null;

        try {

            if (abreArchivo != null) {
                FileReader archivoSelected = new FileReader(abreArchivo);
                bufferLectura = new BufferedReader(archivoSelected);
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                ArrayList<DatosEstadoCuenta> arrayCuentas = new ArrayList<DatosEstadoCuenta>();
                String linea = bufferLectura.readLine();
                String[] columnas = null;

                while (linea != null) {

                    String[] campos = linea.split(SEPARADOR);

                    if ("Cuenta".equals(campos[0].trim())) {
                        columnas = campos;
                    } else {
                        String cuenta = campos[0];
                        double monto = Double.parseDouble(campos[1]);
                        int operacion = Integer.parseInt(campos[2]);
                        Date date = format.parse(campos[3]);

                        DatosEstadoCuenta cuentas = new DatosEstadoCuenta(cuenta, monto, operacion, date);
                        arrayCuentas.add(cuentas);
                    }

                    // Volver a leer otra línea del fichero
                    linea = bufferLectura.readLine();
                }
                //Preguntar el mes
                String mesRequerido = JOptionPane.showInputDialog("¿De que mes quiere calcular su estado?");
                int mes = Integer.parseInt(mesRequerido);

                CalcularEstado calculaestado = new CalcularEstado();
                calculaestado.sumatoriaPorMes(arrayCuentas, mes);
            }

        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (ParseException ex) {
            ex.printStackTrace();
        } finally {
            // Cierro el buffer de lectura
            if (bufferLectura != null) {
                try {
                    bufferLectura.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
