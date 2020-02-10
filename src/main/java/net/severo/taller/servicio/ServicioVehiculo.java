package net.severo.taller.servicio;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.IVehiculo;
import net.severo.taller.pojo.Vehiculo;

import java.util.Date;
import java.util.List;

public class ServicioVehiculo {

    private IVehiculo idao = null;

    private static ServicioVehiculo servicioVehiculo = null;

    public static ServicioVehiculo getServicio() throws DAOException {
        if (servicioVehiculo == null) {
            servicioVehiculo = new ServicioVehiculo();
        }
        return servicioVehiculo;
    }

    public void elegirSistemaAlmacenamiento(int opcion) throws DAOException {

        if (opcion == 1) {
            //idao = new VehiculoJDBC();
        }
        if (opcion == 2) {

            //idao=new VehiculoHIBERNATE();
        }
        if (opcion == 3) {

            //NO IMPLEMENTADO
        }
    }

    //crear un vehiculo nuevo

    public void crearVehiculo(Vehiculo v) throws ServiciosException, DAOException {
        if (idao.obtenerVehiculoPorID(v.getIdVehiculo()) != null) {
            throw new ServiciosException("El vehiculo ya existe con esa id.");
        }

        idao.iniciarTransaccion();

        idao.crearNuevoVehiculo(v);

        idao.finalizarTransaccion();

    }

    //obtener un vehiculo a partir de su id

    public Vehiculo obtenerVehiculo(int idVehiculo) throws DAOException, ServiciosException {
        List<Vehiculo> vehiculos = idao.obtenerTodosVehiculos();
        for (Vehiculo v : vehiculos) {
            if (v.getIdVehiculo()==(idVehiculo)) {
                return v;
            }
        }
        throw new ServiciosException("No hay ningún vehiculo con el codigo especificado");
    }

    //elimina un vehiculo al pasarle un id

    public void eliminarVehiculo(int codigoVehiculo) throws DAOException, ServiciosException {
        this.obtenerVehiculo(codigoVehiculo);
        idao.eliminarVehiculo(codigoVehiculo);

    }

    //devuelve una lista con los vehiculos

    public List<Vehiculo> obtenerVehiculos() throws DAOException, ServiciosException {
        List<Vehiculo> vehiculos = idao.obtenerTodosVehiculos();
        if (vehiculos.isEmpty()) {
            throw new ServiciosException("No hay ningún vehiculo");
        }
        return vehiculos;
    }

    //devuelve una lista con los vehiculos a partir de la fecha de matriculación de estos

    public List<Vehiculo> obtenerVehiculosFechaMatriculacion(Date fecha) throws DAOException, ServiciosException {
        List<Vehiculo> vehiculos = idao.obtenerVehiculosPorFechaMatriculacion(fecha);
        if (vehiculos.isEmpty()) {
            throw new ServiciosException("No hay ningún vehiculo con esa fecha de matriculación");
        }
        return vehiculos;
    }

    //devuelve una lista con los vehiculos electricos

    public List<Vehiculo> obtenerVehiculosElectricos(boolean esElectrico) throws DAOException, ServiciosException {
        List<Vehiculo> vehiculos = idao.obtenerTodosVehiculosElectricos(esElectrico);
        if (vehiculos.isEmpty()) {
            throw new ServiciosException("No hay ningún vehiculo eléctrico");
        }
        return vehiculos;
    }

    //modifica la matricula de un vehiculo

    public void modificarMatricula(int idVehiculo, String nuevaMatricula) throws DAOException, ServiciosException {
        Vehiculo v = this.obtenerVehiculo(idVehiculo);
        v.setMatricula(nuevaMatricula);
        idao.modificarVehiculo(v);
    }

    //modifica la propiedad de un vehiculo sobre si es o no electrico

    public void modificarEsElectrico(int idVehiculo, boolean esElectrico) throws DAOException, ServiciosException {
        Vehiculo v = this.obtenerVehiculo(idVehiculo);
        v.setEsElectrico(esElectrico);
        idao.modificarVehiculo(v);
    }

    //modifica la fecha de matriculacion de un vehiculo

    public void modificarFechaMatriculacion(int idVehiculo, Date nuevaFechaMatriculacion) throws DAOException, ServiciosException {
        Vehiculo v = this.obtenerVehiculo(idVehiculo);
        v.setFechaMatriculacion(nuevaFechaMatriculacion);
        idao.modificarVehiculo(v);
    }

    /*public void comprobarTarjetasEnReservasConEsteVuelo(String codigoVuelo) throws DAOException, ServiciosException {
        List<Reserva> reservas=new ArrayList<>();
        //Hacemos esto para evitar que se lancen las excepciones de servicio las cuales no tiene sentido aqui
        try{
            reservas=ServicioReserva.getServicio().obtenerReservas(codigoVuelo);
        }catch(ServiciosException se){}
        for (Reserva r : reservas) {
            ServicioReserva.getServicio().tieneTarjetaDeEmbarque(r);
        }
    }

    public void comprobarReservas(String codigo) throws DAOException, ServiciosException {
        List<Reserva> reservas=new ArrayList<>();
        //Hacemos esto para evitar que se lancen las excepciones de servicio las cuales no tiene sentido aqui
        try{
            reservas=ServicioReserva.getServicio().obtenerReservas(codigo);
        }catch(ServiciosException se){}
        if(!(reservas.isEmpty())){
            throw new ServiciosException("Este vuelo tiene reservas hechas");
        }
    }*/



    /**
     * Transacciones
     */

    public void finalizar() throws DAOException {
        //cierra las conexiones  e BD en caso de que fuera necesario
        //en el caso de archivos de texto o bianrios no es necesario hacer nada
        //ya que los abrimos y cerramos en cada operacion
        idao.finalizar();
    }

    public void iniciarTransaccion() throws DAOException {
        idao.iniciarTransaccion();
    }

    public void finalizarTransaccion() throws DAOException {
        idao.finalizarTransaccion();
    }

    /**
     *  FIN Transacciones
     */

}
