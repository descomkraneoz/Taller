package net.severo.taller.DAO;

import net.severo.taller.pojo.Vehiculo;

import java.util.Date;
import java.util.List;

public interface IVehiculo {
    void crearNuevoVehiculo (Vehiculo vehiculo)  throws DAOException ;
    void modificarVehiculo(Vehiculo vehiculo)  throws DAOException ;
    void eliminarVehiculo(int idVehiculo)  throws DAOException ;
    Vehiculo obtenerVehiculoPorID(int idVehiculo)  throws DAOException ;
    List<Vehiculo> obtenerVehiculosPorFechaMatriculacion(Date fechaMatriculacion)  throws DAOException ;
    List<Vehiculo> obtenerTodosVehiculos()  throws DAOException ;

    List<Vehiculo> obtenerTodosVehiculosElectricos(boolean esElectrico) throws DAOException;

    public void finalizar() throws DAOException; //cortar la conexion

    public void iniciarTransaccion() throws DAOException;

    public void finalizarTransaccion() throws DAOException;


}
