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
                    this.nuevoVehiculo();
                    break;
                case 2:
                    this.mostrarListaVehiculos();
                    break;
                case 3:
                    this.modificarVehiculo();
                    break;
                case 4:
                    this.eliminarVehiculo();
                    break;
                case 5:
                    //No implementado
                    break;

            }
        } while (true);
    }

    public void nuevoVehiculo() {
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
                    e.printStackTrace();
                } catch (DAOException e) {
                    e.printStackTrace();
                }

                break;
            case 2:
        }


    }

}
