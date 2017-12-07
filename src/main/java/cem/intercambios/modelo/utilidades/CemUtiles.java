package cem.intercambios.modelo.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CemUtiles {

    private static final Logger LOGGER
            = Logger.getLogger(CemUtiles.class.getName());

    public CemUtiles() {
    }

    public Date establecerFechaActual() {
        try {
            DateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            Calendar calendario = Calendar.getInstance();
            int dia = calendario.get(Calendar.DAY_OF_MONTH),
                    mes = calendario.get(Calendar.MONTH) + 1,
                    ano = calendario.get(Calendar.YEAR);
            Date fechaActual = formatoFecha.parse(ano + "-" + mes + "-" + dia);
            return fechaActual;
        } catch (ParseException ex) {
            LOGGER.log(Level.SEVERE, "Error en el parseo de la fecha actual.",
                    ex);
            return null;
        }
    }

    public String codigoPrograma(String nombrePrograma,
            String tipoDuracion, String cupos) {
        int aleatorio = ThreadLocalRandom.current().nextInt(1, 1000000);
        int largo = (int) Math.log10(aleatorio) + 1;
        StringBuilder codigo = new StringBuilder();
        String[] palabras = nombrePrograma.split(" ");
        for (String palabra : palabras) {
            codigo.append(palabra.toUpperCase().charAt(0));
        }
        codigo.append("-");
        if (tipoDuracion.equalsIgnoreCase("normal")) {
            codigo.append("N");
        } else {
            codigo.append("C");
        }
        codigo.append(cupos).append("-");
        switch (largo) {
            case 5:
                codigo.append("0");
                break;
            case 4:
                codigo.append("00");
                break;
            case 3:
                codigo.append("000");
                break;
            case 2:
                codigo.append("0000");
                break;
            case 1:
                codigo.append("00000");
        }
        codigo.append(aleatorio);
        return codigo.toString();
    }

    public String codigoAsignatura(String nombreAsignatura) {
        int aleatorio = ThreadLocalRandom.current().nextInt(1, 10000);
        int largo = (int) Math.log10(aleatorio) + 1;
        StringBuilder codigo = new StringBuilder();
        String[] palabras = nombreAsignatura.split(" ");
        for (String palabra : palabras) {
            codigo.append(palabra.toUpperCase().charAt(0));
        }
        codigo.append("-");
        switch (largo) {
            case 3:
                codigo.append("0");
                break;
            case 2:
                codigo.append("00");
                break;
            case 1:
                codigo.append("000");
        }
        codigo.append(aleatorio);
        return codigo.toString();
    }
    
}
