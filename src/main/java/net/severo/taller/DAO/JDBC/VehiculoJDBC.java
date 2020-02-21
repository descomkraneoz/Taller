package net.severo.taller.DAO.JDBC;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.IVehiculo;
import net.severo.taller.pojo.Vehiculo;

import java.util.Date;
import java.util.List;

public class VehiculoJDBC implements IVehiculo {

    static String getAllVehiculos = "SELECT * FROM vehiculo;";
    static String getVuelos = "SELECT * FROM vehiculo WHERE codVuelo=?;";
    static String insertVuelos = "INSERT INTO vehiculo(codVuelo,origen,destino,precio,fecha,plazas) VALUES (?,?,?,?,?,?);";
    static String deleteVuelos = "DELETE FROM vehiculo WHERE codVuelo=?;";
    static String modVuelos = "UPDATE FROM vehiculo WHERE codVuelo=?;";


    public VehiculoJDBC() throws DAOException {

        //acedemos al singleton ahora por si hay fallos que salten aqui
        ConexionJDBC.getInstance().getConnection();

    }


    @Override
    public void crearNuevoVehiculoDAO(Vehiculo vehiculo) throws DAOException {

    }

    @Override
    public void modificarVehiculoDAO(Vehiculo vehiculo) throws DAOException {

    }

    @Override
    public void eliminarVehiculoDAO(int idVehiculo) throws DAOException {

    }

    @Override
    public Vehiculo obtenerUnVehiculoPorID(int idVehiculo) throws DAOException {
        return null;
    }

    @Override
    public List<Vehiculo> obtenerTodosVehiculos() throws DAOException {
        return null;
    }

    @Override
    public List<Vehiculo> obtenerVehiculosPorFechaMatriculacion(Date fechaMatriculacion) throws DAOException {
        return null;
    }

    @Override
    public List<Vehiculo> obtenerTodosVehiculosElectricos(boolean esElectrico) throws DAOException {
        return null;
    }

    @Override
    public void finalizar() throws DAOException {

    }

    @Override
    public void iniciarTransaccion() throws DAOException {

    }

    @Override
    public void finalizarTransaccion() throws DAOException {

    }
}
