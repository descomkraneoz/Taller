package net.severo.taller.vistas;

import net.severo.taller.pojo.Vehiculo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VistaVehiculo {

    public static boolean esEntero(String numero) {
        try {
            Integer.parseInt(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }

    private static Date obtenerFecha(String cadena) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaNac = null;
        try {
            fechaNac = sdf.parse(cadena);
            return fechaNac;
        } catch (ParseException ex) {
            // si no es fecha devolvemos un null
            return null;
        }
    }

    public static boolean esDecimal(String numero) {
        try {
            Double.parseDouble(numero);
            return true;
        } catch (NumberFormatException nfe) {
            return false;
        }
    }


    public int menuPrincipal() {
        Scanner sc = new Scanner(System.in);
        String menu = "  1. Nuevo Vehiculo. \n 2. Ver Vehiculos \n 3. Modificar Vehiculo \n 4. Eliminar Vehiculo. \n 0. Salir \n ¿Que quiere hacer?";

        int opcion = -1; //opcio -1 indica opcion incorrecta
        while (opcion == -1) {
            System.out.println(menu);
            String entrada = sc.nextLine();
            if (!esEntero(entrada)) {
                opcion = -1;
            } else {
                opcion = Integer.parseInt(entrada);
            }
            if (opcion > 5 || opcion < 0) {
                System.out.println("Opción no valida, elija una opción del 1-4 o 0 para Salir");
                opcion = -1;
            }
        }
        return opcion;
    }

    public Integer pedirIdVehiculo() {
        String respuesta;
        Scanner sc = new Scanner(System.in);
        Integer id;
        do {
            System.out.println("Introduzca el código identificativo del vehículo:");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")) {
                return null;
            }
            if (!esDecimal(respuesta)) {
                System.err.println("El id ha de ser un número decimal.");
            } else {
                //No se permiten vuelos gratuitos
                id = Integer.parseInt(respuesta);
                if (id < 0) {
                    System.err.println("El id ha de ser un numero positivo");
                } else {
                    return id;
                }
            }
        } while (true);
    }

    public String pedirMatricula() {
        String respuesta;
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("Introduzca una matricula para el vehiculo (1234ABC)");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")) {
                //Salimos
                return null;
            }
            if (respuesta.length() < 3 || respuesta.length() > 7 || respuesta == null) {
                System.err.println("La matricula del vehiculo ha de tener entre 3 y 7 carácteres");
            }
            //Se establece que la matricula del vehiculo ha de tener más de 3 carácter y menos de 7
        } while (respuesta.length() < 3 || respuesta.length() > 7 || respuesta == null);
        return respuesta;
    }

    public Date pedirFechaMatriculacion() {
        String respuesta;
        Scanner sc = new Scanner(System.in);
        Date fecha;
        do {
            System.out.println("Introduzca la fecha de matriculación del vehiculo (dd/MM/yyyy)");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")) {
                //salimos
                return null;
            }
            fecha = obtenerFecha(respuesta);
            if (fecha == null) {
                System.err.println("El formato de fecha no es valido");
            }
        } while (fecha == null);
        return fecha;
    }

    public Boolean pedirEsElectrico() {
        Scanner sc = new Scanner(System.in);
        do {
            System.out.println("¿El vehiculo es electrico? (S/N)");
            String respuesta = sc.nextLine();
            if ("0".equals(respuesta)) {
                return null;
            }
            if (("S".equals(respuesta.toUpperCase()))) {
                return true;
            }
            if (("N".equals(respuesta.toUpperCase()))) {
                return false;
            }
            System.out.println("Por favor,introduzca S para una respuesta afirmativa y N para una negativa");
        } while (true);

    }

    public Integer menuModificarVehiculo() {
        Scanner sc = new Scanner(System.in);
        String respuesta;
        int opcion;
        do {
            System.out.println(" 1.-Matricula \n 2.-Es electrico \n 3.-Fecha matriculación");
            System.out.println("¿Qué quiere modificar?");
            respuesta = sc.nextLine();
            if (!esEntero(respuesta)) {
                continue;
            }
            opcion = Integer.parseInt(respuesta);
            if (opcion == 0) {
                return null;
            }
            if (opcion > 0 && opcion < 4) {
                return opcion;
            }
        } while (true);

    }

    public Vehiculo crearVehiculoVista() {
        Integer id = this.pedirIdVehiculo();
        if (id == null) {
            return null;
        }
        String matricula = this.pedirMatricula();
        if (matricula == null) {
            return null;
        }
        Boolean esElectrico = this.pedirEsElectrico();
        if (esElectrico == null) {
            return null;
        }
        Date fechaMatriculacion = this.pedirFechaMatriculacion();
        new SimpleDateFormat("dd/MM/yyyy").format(fechaMatriculacion);
        if (fechaMatriculacion == null) {
            return null;
        }
        return new Vehiculo(id, matricula, esElectrico, fechaMatriculacion);
    }

    public void mostrarUnVehiculo(Vehiculo v) {
        System.out.println("--------------- VEHICULO SELECCIONADO -------------");
        System.out.println("CÓDIGO   MATRICULA   ELECTRICO   FECHA MATRICULACIÓN");
        String fechaStr;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        fechaStr = sdf.format(v.getFechaMatriculacion());
        System.out.printf("%-8s %-10s %-10s %-14s \n", v.getIdVehiculo(), v.getMatricula(), v.getEsElectrico() ? "Sí" : "No", fechaStr);

    }

    public void mostrarListaVehiculos(List<Vehiculo> vehiculos) {
        System.out.println("------------------ VEHICULOS -----------------------");
        System.out.println("CÓDIGO   MATRICULA   ELECTRICO   FECHA MATRICULACIÓN");
        String fechaStr;
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        for (Vehiculo v : vehiculos) {
            fechaStr = sdf.format(v.getFechaMatriculacion());
            System.out.printf("%-8s %-13s %-15s %-14s \n", v.getIdVehiculo(), v.getMatricula(), v.getEsElectrico() ? "Sí" : "No", fechaStr);
        }
        System.out.println("----------------------------------------------------");
    }

    public void mostrarError(String mensaje) {
        System.err.println(mensaje);
    }

}
