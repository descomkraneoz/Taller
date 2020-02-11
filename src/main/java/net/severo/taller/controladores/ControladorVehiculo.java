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
                    //this.modificarVehiculo();
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

        //Id del vehiculo
        Integer id = vv.pedirIdVehiculo();
        if (id == null) {
            return;
        }

        int opcion = vv.menuAsignarMatricula();
        if (opcion == 0) {
            return;
        }
        switch (opcion) {
            case 1:
                String matricula = vv.pedirMatriculaManual();
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
                    ServicioVehiculo.getServicio().crearVehiculo(v);
                } catch (ServiciosException e) {
                    vv.mostrarError("Error al generar un nuevo vehiculo en el controlador: " + e);
                } catch (DAOException e) {
                    vv.mostrarError("Error desde el controlador al intentar obtener los datos, el vehiculo no será creado: " + e);
                }

                break;
            case 2:
                matricula = vv.pedirMatriculaAutomática();
                if (matricula == null) {
                    return;
                }
                esElectrico = vv.pedirEsElectrico();
                if (esElectrico == null) {
                    return;
                }
                fecha = vv.pedirFechaMatriculacion();
                if (fecha == null) {
                    return;
                }

                //rellenamos el vehiculo
                try {
                    v = new Vehiculo(id, matricula, esElectrico, fecha);
                    ServicioVehiculo.getServicio().crearVehiculo(v);
                } catch (ServiciosException e) {
                    vv.mostrarError("Error al generar un nuevo vehiculo en el controlador: " + e);
                } catch (DAOException e) {
                    vv.mostrarError("Error desde el controlador al intentar obtener los datos, el vehiculo no será creado: " + e);
                }

                break;
        }
    }

    public void ControladorEliminarVehiculo() {
        try {
            vv.mostrarListaVehiculos(ServicioVehiculo.getServicio().obtenerVehiculos());
            Integer codigo = vv.pedirIdVehiculo();
            if (codigo == null) {
                return;
            }
            ServicioVehiculo.getServicio().eliminarVehiculo(codigo);
        } catch (DAOException dao) {
            vv.mostrarError("Error al intentar obtener los datos: " + dao.getMessage());
        } catch (ServiciosException se) {
            vv.mostrarError("Error al eliminar un vehiculo: " + se.getMessage());
        }
    }

    public void ControladorMostrarListaVehiculos() {
        try {
            vv.mostrarListaVehiculos(ServicioVehiculo.getServicio().obtenerVehiculos());
        } catch (DAOException ex) {
            vv.mostrarError("Error al intentar obtener los datos" + ex);
            ex.printStackTrace();
        } catch (ServiciosException ex) {
            vv.mostrarError("Error al intentar mostrar los datos: " + ex);
            ex.printStackTrace();
        }
    }

}
