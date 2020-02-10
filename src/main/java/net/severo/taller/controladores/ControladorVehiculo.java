package net.severo.taller.controladores;

public class ControladorVehiculo {

    private VistaVehiculo vv = null;

    public ControladorVuelo() {
        vv = new VistaVuelo();
    }

    public void iniciarVuelo() {
        do {
            int opcion = vv.menuPrincipal();
            if (opcion == 0) {
                return;
            }
            switch (opcion) {
                case 1:
                    this.nuevoVuelo(vv.crearVuelo());
                    break;
                case 2:
                    this.mostrarVuelos();
                    break;
                case 3:
                    this.modificarVuelo();
                    break;
                case 4:
                    this.eliminarVuelo();
                    break;
                case 5:
                    this.asignarPuertaYTerminal();
                    break;

            }
        } while (true);

    }
}
