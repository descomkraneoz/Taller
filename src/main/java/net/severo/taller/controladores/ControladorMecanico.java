package net.severo.taller.controladores;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;
import net.severo.taller.servicio.ServicioMecanico;
import net.severo.taller.servicio.ServiciosException;
import net.severo.taller.vistas.VistaMecanico;
import net.severo.taller.vistas.VistaVehiculo;

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
                    this.ControladorNuevoMecanico();
                    break;
                case 2:
                    this.ControladorMostrarMecanicos();
                    break;
                case 3:
                    this.ControladorModificarMecanico();
                    break;
                case 4:
                    this.ControladorEliminarMecanico();
                    break;
            }
        } while (true);

    }

    public void ControladorNuevoMecanico() {
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
            vehiculosDeEsteMecanico = ServicioMecanico.getServicioMecanico().servicioObtenerTodosLosVehiculos();
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
            ServicioMecanico.getServicioMecanico().servicioNuevoMecanico(mc);
        } catch (DAOException ex) {
            vm.mostrarError("Error al intentar acceder a los datos: " + ex.getMessage());

        } catch (ServiciosException ex) {
            vm.mostrarError("Error al intentar crear la reserva: " + ex.getMessage());
        }

        //aqui podemos finalizar transacción

    }

    public void ControladorMostrarMecanicos() {
        try {
            vm.mostrarListaMecanicos(ServicioMecanico.getServicioMecanico().servicioObtenerTodosMecanicos());
        } catch (DAOException ex) {
            vm.mostrarError("Error de controlador al intentar obtener los datos: " + ex);
        } catch (ServiciosException ex) {
            vm.mostrarError("Error de controlador al intentar mostrar los mecanicos: " + ex);
        }
    }

    public void ControladorMostrarMecanicos(int codigo) {
        try {
            vm.mostrarListaMecanicos(ServicioMecanico.getServicioMecanico().servicioObtenerListaMecanicosPorId(codigo));
        } catch (DAOException ex) {
            vm.mostrarError("Error al intentar obtener los datos: " + ex);
        } catch (ServiciosException ex) {
            vm.mostrarError("Error al intentar mostrar las reservas: " + ex);
        }
    }

    public void ControladorModificarMecanico() {
        //aqui podemos iniciar transaccion

        try {
            vm.mostrarListaMecanicos(ServicioMecanico.getServicioMecanico().servicioObtenerTodosMecanicos());
            Integer codigo = vm.pedirIdMecanico();
            if (codigo == null) {
                return;
            }
            Integer opcion;
            opcion = vm.menuModificarMecanico();
            if (opcion != null) {
                switch (opcion) {
                    case 1:
                        String nombre = vm.pedirNombreYApellidosMecanico();
                        if (nombre != null) {
                            ServicioMecanico.getServicioMecanico().servicioModificarNombreMecanico(codigo, nombre);
                        }
                        break;
                    case 2:
                        //Añadir lista de vehiculos a mecanico
                        VistaVehiculo vv = new VistaVehiculo();
                        ArrayList<Vehiculo> vehiculosCreados = new ArrayList<>();
                        vehiculosCreados.add(vv.crearVehiculoVista());
                        List<Vehiculo> vehiculos = vm.pedirListaVehiculos(vehiculosCreados);
                        if (vehiculos != null) {
                            ServicioMecanico.getServicioMecanico().servicioModificarListaVehiculosMecanico(codigo, vehiculos);
                        }
                        break;

                    case 3:
                        //Añadir vehiculos a la lista
                        /*if (fecha != null) {
                            ServicioVehiculo.getServicio().modificarFechaMatriculacion(codigoVehiculo, fecha);
                        }
                        break;*/

                }

            }

        } catch (DAOException e) {
            vm.mostrarError("Error al intentar obtener los datos: " + e.getMessage());
        } catch (ServiciosException e) {
            vm.mostrarError("Error al modificar mecánico: " + e.getMessage());
        }

    }


    public void ControladorEliminarMecanico() {
        try {
            vm.mostrarListaMecanicos(ServicioMecanico.getServicioMecanico().servicioObtenerTodosMecanicos());
            Integer codigo = vm.pedirIdMecanico();
            if (codigo == null) {
                return;
            }
            ServicioMecanico.getServicioMecanico().servicioEliminarMecanico(codigo);
        } catch (DAOException dao) {
            vm.mostrarError("Error al intentar obtener los datos: " + dao.getMessage());
        } catch (ServiciosException se) {
            vm.mostrarError("Error al eliminar un mecanico: " + se.getMessage());
        }
    }


}
