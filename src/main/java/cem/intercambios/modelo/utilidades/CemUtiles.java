package cem.intercambios.modelo.utilidades;

import cem.intercambios.modelo.entidad.Alumno;
import cem.intercambios.modelo.entidad.Persona;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
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
    
    public Persona convertirAlumnoWebAPersonaJPA(cem.intercambios.controlador.cliente.Alumno alumnoWEB) {
        Date fechaMatricula = alumnoWEB.getFechaMatricula()
                .toGregorianCalendar().getTime();
        Date fechaNacimiento = alumnoWEB.getFechaNacimiento()
                .toGregorianCalendar().getTime();
        Persona persona = new Persona(
                alumnoWEB.getRutPersona(),
                alumnoWEB.getNombreCompleto(),
                fechaNacimiento,
                alumnoWEB.getDomicilio(),
                alumnoWEB.getCiudad(),
                alumnoWEB.getPais(),
                alumnoWEB.getCorreo(),
                alumnoWEB.getTelefono(),
                new cem.intercambios.modelo.entidad.Alumno(
                        alumnoWEB.getRutPersona(),
                        alumnoWEB.getNumeroMatricula(),
                        fechaMatricula,
                        alumnoWEB.getNombreCarrera(),
                        alumnoWEB.getEsMoroso()
                )
        );
        return persona;
    }

    /**
     * Método que encripta la contraseña con el algoritmo MD5 para guardarla con
     * mayor seguridad en la base de datos.
     *
     * @param contrasena La contraseña que será encriptada.
     * @return La nueva contraseña encriptada.
     * @throws NoSuchAlgorithmException
     */
    public String encriptar(String contrasena)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(contrasena.getBytes());
        byte byteData[] = md.digest();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(
                    Integer.toString((byteData[i] & 0xff) + 0x100, 16)
                            .substring(1)
            );
        }
        return sb.toString();
    }

}
