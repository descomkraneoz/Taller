package net.severo.taller.controladores;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.servicio.ServicioVehiculo;
import net.severo.taller.vistas.VistaPrincipal;

public class ControladorPrincipal {

    VistaPrincipal vp = null;

    public ControladorPrincipal() {
        vp = new VistaPrincipal();
    }

    public void iniciarAplicacion() {
        int sistemaAl = vp.elegirSistemaAlmacenamiento();
        if (sistemaAl == 0) {
            //salimos de la aplicacion
            return;

        } else {

            try {
                ServicioVehiculo.getServicio().elegirSistemaAlmacenamiento(sistemaAl);
                //ServicioReserva.getServicio().elegirSistemaAlmacenamiento(sistemaAl);
            } catch (DAOException ex) {
                vp.mostrarError("Ha habido un error al iniciar el sistema de almacenamiento " + ex.getMessage());
            }

            this.iniciarMenuPrincipal();
        }
    }

    public void iniciarMenuPrincipal() {
        do {
            int opcion = vp.menuPrincipal();
            switch (opcion) {
                case 0:
                    //Salimos
                    return;
                case 1:
                    //new ControladorVehiculo().iniciarVehiculo();
                    break;
                case 2:
                    //new ControladorMecanico().iniciarMecanico();
                    break;
                case 3:
                    //new ControladorInformes().iniciarInformes();
                    break;
            }
        } while (true);
    }

}
