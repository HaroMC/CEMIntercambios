package cem.intercambios.modelo.utilidades;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

}
