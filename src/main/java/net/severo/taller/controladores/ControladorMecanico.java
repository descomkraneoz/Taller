package net.severo.taller.controladores;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.pojo.Mecanico;
import net.severo.taller.servicio.ServicioMecanico;
import net.severo.taller.servicio.ServicioVehiculo;
import net.severo.taller.servicio.ServiciosException;
import net.severo.taller.vistas.VistaMecanico;
import net.severo.taller.vistas.VistaVehiculo;

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
                case 5:
                    this.ControladorAsignarMecanicoAlVehiculo();
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
        Mecanico mc = new Mecanico(id, nombre);
        try {
            ServicioMecanico.getServicioMecanico().servicioNuevoMecanico(mc);
        } catch (DAOException ex) {
            vm.mostrarError("Error al intentar acceder a los datos: " + ex.getMessage());

        } catch (ServiciosException ex) {
            vm.mostrarError("Error al intentar crear el nuevo mecánico: " + ex.getMessage());
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
            vm.mostrarError("Error al intentar mostrar los mecánicos: " + ex);
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
                        /*List<Vehiculo> vehiculosAsignados = new ArrayList<>();
                        Mecanico mc = new Mecanico();
                        try {
                            vehiculosAsignados = ServicioMecanico.getServicioMecanico().servicioObtenerTodosLosVehiculos();
                            mc = ServicioMecanico.getServicioMecanico().servicioObtenerMecanicoPorID(codigo);

                        } catch (DAOException ex) {
                            vm.mostrarError("Error al intentar obtener los datos: " + ex.getMessage());
                            return;
                        } catch (ServiciosException ex) {
                            vm.mostrarError("Error al intentar obtener los vehiculos:" + ex);
                            return;
                        }
                        List<Vehiculo> vehiculos = vm.pedirListaVehiculos((ArrayList<Vehiculo>) vehiculosAsignados);
                        mc.setVehiculos((Set<Vehiculo>) vehiculos);*/
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

    private void ControladorAsignarMecanicoAlVehiculo() {
        try {
            ServicioMecanico.getServicioMecanico().iniciarTransaccion();
        } catch (DAOException e) {
            e.printStackTrace();
        }
        try {
            vm.mostrarListaMecanicos(ServicioMecanico.getServicioMecanico().servicioObtenerTodosMecanicos());
            Integer codMec = vm.pedirIdMecanico();


            new VistaVehiculo().mostrarListaVehiculos(ServicioVehiculo.getServicio().servicioObtenerVehiculos());
            Integer codigoVehiculo = new VistaVehiculo().pedirIdVehiculo();

            ServicioMecanico.getServicioMecanico().servicioAsignarMecanicoAlVehiculo(codMec, codigoVehiculo);

        } catch (DAOException dao) {
            vm.mostrarError("Error en el controlador al intentar obtener los datos: " + dao.getMessage());
        } catch (ServiciosException se) {
            vm.mostrarError("Error en el controlador al asignar un vehiculo: " + se.getMessage());
        }

    }


}
