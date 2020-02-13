package net.severo.taller.controladores;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.pojo.Vehiculo;
import net.severo.taller.servicio.ServicioVehiculo;
import net.severo.taller.servicio.ServiciosException;
import net.severo.taller.vistas.VistaVehiculo;

import java.util.Date;

public class ControladorVehiculo {

    private VistaVehiculo vv = null;

    public ControladorVehiculo() {
        vv = new VistaVehiculo();
    }

    public void iniciarVehiculo() {
        do {
            int opcion = vv.menuPrincipal();
            if (opcion == 0) {
                return;
            }
            switch (opcion) {
                case 1:
                    this.ControladorNuevoVehiculo();
                    break;
                case 2:
                    this.ControladorMostrarListaVehiculos();
                    break;
                case 3:
                    this.ControladorModificarVehiculo();
                    break;
                case 4:
                    this.ControladorEliminarVehiculo();
                    break;
                case 5:
                    //No implementado
                    break;

            }
        } while (true);
    }


    public void ControladorNuevoVehiculo() {
        Vehiculo v;

        //Aqui podria iniciar transacción

        //Pedimos datos del vehiculo
        Integer id = vv.pedirIdVehiculo();
        if (id == null) {
            return;
        }
        String matricula = vv.pedirMatricula();
        if (matricula == null) {
            return;
        }
        Boolean esElectrico = vv.pedirEsElectrico();
        if (esElectrico == null) {
            return;
        }
        Date fecha = vv.pedirFechaMatriculacion();
        if (fecha == null) {
            return;
        }
        //rellenamos el vehiculo
        try {
            v = new Vehiculo(id, matricula, esElectrico, fecha);
            ServicioVehiculo.getServicio().servicioCrearVehiculo(v);
        } catch (ServiciosException e) {
            vv.mostrarError("Error al generar un nuevo vehiculo en el controlador: " + e);
        } catch (DAOException e) {
            vv.mostrarError("Error desde el controlador al intentar obtener los datos, el vehiculo no será creado: " + e);
        }
    }

    public void ControladorEliminarVehiculo() {
        try {
            vv.mostrarListaVehiculos(ServicioVehiculo.getServicio().servicioObtenerVehiculos());
            Integer codigo = vv.pedirIdVehiculo();
            if (codigo == null) {
                return;
            }
            ServicioVehiculo.getServicio().servicioEliminarVehiculo(codigo);
        } catch (DAOException dao) {
            vv.mostrarError("Error al intentar obtener los datos: " + dao.getMessage());
        } catch (ServiciosException se) {
            vv.mostrarError("Error al eliminar un vehiculo: " + se.getMessage());
        }
    }

    public void ControladorMostrarListaVehiculos() {
        try {
            vv.mostrarListaVehiculos(ServicioVehiculo.getServicio().servicioObtenerVehiculos());
        } catch (DAOException ex) {
            vv.mostrarError("Error al intentar obtener los datos" + ex);
            ex.printStackTrace();
        } catch (ServiciosException ex) {
            vv.mostrarError("Error al intentar mostrar los datos: " + ex);
            ex.printStackTrace();
        }
    }

    public void ControladorModificarVehiculo() {
        //aqui podemos iniciar transaccion

        try {
            vv.mostrarListaVehiculos(ServicioVehiculo.getServicio().servicioObtenerVehiculos());
            Integer codigoVehiculo = vv.pedirIdVehiculo();
            if (codigoVehiculo == null) {
                return;
            }
            Integer opcion;
            opcion = vv.menuModificarVehiculo();
            if (opcion != null) {
                switch (opcion) {
                    case 1:
                        String matricula = vv.pedirMatricula();
                        if (matricula != null) {
                            ServicioVehiculo.getServicio().servicioModificarMatricula(codigoVehiculo, matricula);
                        }
                        break;
                    case 2:
                        Boolean esElectrico = vv.pedirEsElectrico();
                        if (esElectrico != null) {
                            ServicioVehiculo.getServicio().servicioModificarEsElectrico(codigoVehiculo, esElectrico);
                        }
                        break;

                    case 3:
                        Date fecha = vv.pedirFechaMatriculacion();
                        if (fecha != null) {
                            ServicioVehiculo.getServicio().servicioModificarFechaMatriculacion(codigoVehiculo, fecha);
                        }
                        break;

                }

            }

        } catch (DAOException e) {
            vv.mostrarError("Error al intentar obtener los datos: " + e.getMessage());
        } catch (ServiciosException e) {
            vv.mostrarError("Error al modificar un vehiculo: " + e.getMessage());
        }

    }

}
