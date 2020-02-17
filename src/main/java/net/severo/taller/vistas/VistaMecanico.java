package net.severo.taller.vistas;

import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class VistaMecanico {

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
        String menu = "  1. Nuevo Mecánico. \n 2. Ver Mecánicos \n 3. Modificar Mecánico \n 4. Eliminar Mecánico. \n 5. Asignar Vehiculo a Mecánico. \n 0. Salir \n ¿Que quiere hacer?";

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
                System.out.println("Opción no valida, elija una opción del 1-5 o 0 para Salir");
                opcion = -1;
            }
        }
        return opcion;
    }

    public Integer menuModificarMecanico() {
        Scanner sc = new Scanner(System.in);
        String respuesta;
        int opcion;
        do {
            System.out.println(" 1.-Nombre \n 2.-Nueva Lista vehiculos \n 3.-Añadir vehiculo a la lista");
            System.out.println("¿Qué quiere modificar?");
            respuesta = sc.nextLine();
            if (!esEntero(respuesta)) {
                continue;
            }
            opcion = Integer.parseInt(respuesta);
            if (opcion == 0) {
                return null;
            }
            if (opcion > 0 && opcion < 3) {
                return opcion;
            }
        } while (true);

    }

    public Integer pedirIdMecanico() {
        String respuesta;
        Scanner sc = new Scanner(System.in);
        Integer id;
        do {
            System.out.println("Introduzca el código identificativo del mecánico:");
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

    public String pedirNombreYApellidosMecanico() {
        Scanner sc = new Scanner(System.in);
        String respuesta;
        do {
            System.out.println("Introduzca el nombre y apellidos del mecánico:");
            respuesta = sc.nextLine();
            if (respuesta.equals("0")) {
                //Salimos
                return null;
            }
            if (respuesta.length() > 100 || respuesta.length() < 0) {
                System.err.println("El nombre y apellidos del mecánico ha de tener entre 1 y 100 carácteres");
            }
        } while (respuesta.length() > 100 || respuesta.length() < 0);
        return respuesta;
    }

    public List<Vehiculo> pedirNuevaListaVehiculos() {
        List<Vehiculo> vehiculosCreados = new ArrayList<>();
        VistaVehiculo vh = new VistaVehiculo();
        Scanner sc = new Scanner(System.in);
        boolean salir = false;
        System.out.println("<<<-----LISTADO DE VEHICULOS SIN ASIGNAR----->>>");
        vh.mostrarListaVehiculos(vehiculosCreados);
        /*do {
            Vehiculo v = vh.crearVehiculoVista();
            if (v == null) {
                return null;
            }
            if (vehiculosCreados.contains(v)) {
                System.out.println("Ya existe un vehiculo con el id del nuevo y por tanto no será admitido");
                continue;
            } else {
                vehiculosCreados.add(v);
            }

            do {
                System.out.println("¿Quieres añadir más vehiculos?(S/N)");
                String respuesta = sc.nextLine();
                if ("0".equals(respuesta)) {
                    return null;
                }
                if (("S".equals(respuesta.toUpperCase()))) {
                    salir = false;
                    break;
                }
                if (("N".equals(respuesta.toUpperCase()))) {
                    salir = true;
                    break;
                }
                System.out.println("Por favor,introduzca S para una respuesta afirmativa y N para una negativa");
            } while (true);
        } while (!salir);*/
        return vehiculosCreados;
    }


    public void mostrarListaMecanicos(List<Mecanico> mecanicos) {
        System.out.println("<<________MECANICOS________>>");
        System.out.println("ID        NOMBRE COMPLETO    ");
        System.out.println("---      ------------------  ");
        for (Mecanico m : mecanicos) {
            System.out.printf("%-10d %-30s \n", m.getIdMecanico(), m.getNombreMecanico());
        }
        System.out.println("-----------------------------");
    }

    public void mostrarIdyNombreMecanicos(List<Mecanico> mecanicos) {
        System.out.println("Id de todos los mecanicos:");
        String salida = "";
        for (Mecanico p : mecanicos) {
            salida += " " + p.getIdMecanico() + " :" + p.getNombreMecanico();
        }
        System.out.println(salida);
    }

    public void mostrarError(String mensaje) {
        System.err.println(mensaje);
    }


}
