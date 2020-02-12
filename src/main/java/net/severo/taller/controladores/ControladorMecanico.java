package net.severo.taller.controladores;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;
import net.severo.taller.servicio.ServicioMecanico;
import net.severo.taller.servicio.ServiciosException;
import net.severo.taller.vistas.VistaMecanico;

import java.util.ArrayList;
import java.util.List;

public class ControladorMecanico {
    VistaMecanico vm = null;

    public ControladorMecanico() {
        vm = new VistaMecanico();
    }

    public void iniciarMecanico() {
        do {
            int opcion = vm.menuPrincipal();
            if (opcion == 0) {
                return;
            }

            switch (opcion) {
                case 1:
                    this.nuevoMecanico();
                    break;
                case 2:
                    this.mostrarMecanicos();
                    break;
                case 3:
                    this.modificarMecanico();
                    break;
                case 4:
                    this.eliminarMecanico();
                    break;
            }
        } while (true);

    }

    public void nuevoMecanico() {
        //Aqui podemos iniciar transacciones

        //Id del mecanico
        Integer id = vm.pedirIdMecanico();
        if (id == null) {
            return;
        }
        //Nombre y apellidos del mecanico
        String nombre = vm.pedirNombreYApellidosMecanico();
        if (nombre == null) {
            return;
        }
        //Vehiculos del mecanico
        ArrayList<Vehiculo> vehiculosDeEsteMecanico = new ArrayList<>();
        try {
            vehiculosDeEsteMecanico = ServicioMecanico.getServicioMecanico().obtenerTodosLosVehiculos();
        } catch (DAOException ex) {
            vm.mostrarError("Error en el controlador al intentar obtener los datos: " + ex.getMessage());
            return;
        } catch (ServiciosException ex) {
            vm.mostrarError("Error en el controlador al intentar obtener los mecanicos:" + ex);
            return;
        }
        List<Vehiculo> vehiculos = vm.pedirListaVehiculos(vehiculosDeEsteMecanico);
        if (vehiculos == null) {
            return;
        }
        Mecanico mc = new Mecanico(id, nombre, vehiculos);
        try {
            ServicioMecanico.getServicioMecanico().nuevoMecanico(mc);
        } catch (DAOException ex) {
            vm.mostrarError("Error al intentar acceder a los datos: " + ex.getMessage());

        } catch (ServiciosException ex) {
            vm.mostrarError("Error al intentar crear la reserva: " + ex.getMessage());
        }

        //aqui podemos finalizar transacción

    }


}
