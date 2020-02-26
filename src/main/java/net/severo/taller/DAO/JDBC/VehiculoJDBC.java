package net.severo.taller.DAO.JDBC;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.IVehiculo;
import net.severo.taller.pojo.Vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VehiculoJDBC implements IVehiculo {

    static String mostrarTodosVehiculos = "SELECT * FROM vehiculo;";
    static String getVehiculo = "SELECT * FROM vehiculo WHERE idVehiculo=?;";
    static String insertVehiculo = "INSERT INTO vehiculo(idVehiculo,matricula,esElectrico,fechaMatriculacion) VALUES (?,?,?,?);";
    static String borrarVehiculo = "DELETE FROM vehiculo WHERE idVehiculo=?;";
    static String modificarVehiculo = "UPDATE FROM vehiculo WHERE idVehiculo=?;";


    public VehiculoJDBC() throws DAOException {

        //acedemos al singleton ahora por si hay fallos que salten aqui
        ConexionJDBC.getInstance().getConnection();

    }


    @Override
    public void crearNuevoVehiculoDAO(Vehiculo vehiculo) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(insertVehiculo);
            ps.setInt(1, vehiculo.getIdVehiculo());
            ps.setString(2, vehiculo.getMatricula());
            ps.setBoolean(3, vehiculo.getEsElectrico());
            ps.setDate(4, new java.sql.Date(vehiculo.getFechaMatriculacion().getTime()));


            @SuppressWarnings("unused")
            int afectadas = ps.executeUpdate();
            //Este entero no lo vamos a usar pero devuelve el número de filas aceptadas
            //En otras ocasiones nos puede ser útil, aquí siempre debe devolver 1


        } catch (Exception ex) {
            throw new DAOException("Ha habido un problema al insertar el vehiculo en la base de datos: ", ex);
        } finally {
            try {
                ps.close();

            } catch (SQLException sqlex) {
                throw new DAOException("Error al cerrar la sentencia", sqlex);
            }
        }


    }

    @Override
    public void modificarVehiculoDAO(Vehiculo vehiculo) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(modificarVehiculo);
            ps.setInt(1, vehiculo.getIdVehiculo());
            ps.setString(2, vehiculo.getMatricula());
            ps.setBoolean(3, vehiculo.getEsElectrico());
            ps.setDate(4, new java.sql.Date(vehiculo.getFechaMatriculacion().getTime()));


            @SuppressWarnings("unused")
            int afectadas = ps.executeUpdate();
            //Este entero no lo vamos a usar pero devuelve el número de filas aceptadas
            //En otras ocasiones nos puede ser útil, aquí siempre debe devolver 1


        } catch (Exception ex) {
            throw new DAOException("Ha habido un problema al modificar el vehiculo en la base de datos: ", ex);
        } finally {
            try {
                ps.close();

            } catch (SQLException sqlex) {
                throw new DAOException("Error al cerrar la sentencia", sqlex);
            }
        }

    }

    @Override
    public void eliminarVehiculoDAO(int idVehiculo) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {


            ps = conn.prepareStatement(borrarVehiculo);
            ps.setInt(1, idVehiculo);

            ps.executeUpdate();


        } catch (Exception ex) {
            throw new DAOException("Ha habido un problema al eliminar el vehiculo de la base de datos: ", ex);
        } finally {
            try {
                ps.close();
                conn.close();
            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar la base de datos", ex);
            }

        }


    }

    @Override
    public Vehiculo obtenerUnVehiculoPorID(int idVehiculo) throws DAOException {
        Vehiculo j = new Vehiculo();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(getVehiculo);
            ps.setInt(1, idVehiculo);

            ResultSet rs = ps.executeQuery(); //el string se transforma en una sentencia de la bd, un query, se guarda en rs
            if (!rs.next()) {
                // Nos metemos aquí si la consulta no devuelve nada
                return null;
            }

            int id = rs.getInt("idVehiculo");
            String matricula = rs.getString("matricula");
            Boolean electrico = rs.getBoolean("esElectrico");
            java.util.Date fecha = new java.util.Date(rs.getDate("fechaMatriculacion").getTime());

            j.setIdVehiculo(id);
            j.setMatricula(matricula);
            j.setEsElectrico(electrico);
            j.setFechaMatriculacion(fecha);
            return j;


        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener el vehiculo de la base de datos: ", e);
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar la base de datos", ex);
            }

        }
    }

    @Override
    public List<Vehiculo> obtenerTodosVehiculos() throws DAOException {
        List<Vehiculo> vehiculos = new ArrayList<Vehiculo>();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(mostrarTodosVehiculos);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Vehiculo j = new Vehiculo();
                int codVehiculo = rs.getInt("idVehiculo");
                String matricula = rs.getString("matricula");
                Boolean electrico = rs.getBoolean("esElectrico");
                java.util.Date fecha = new java.util.Date(rs.getDate("fechaMatriculacion").getTime());


                j.setIdVehiculo(codVehiculo);
                j.setMatricula(matricula);
                j.setEsElectrico(electrico);
                j.setFechaMatriculacion(fecha);

                vehiculos.add(j);

            }
            return vehiculos;


        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener la lista de vehiculos en la base de datos: ", e);
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar la base de datos", ex);
            }

        }
    }



    @Override
    public void finalizar() throws DAOException {
        try {
            ConexionJDBC.getInstance().getConnection().close();
        } catch (SQLException ex) {
            throw new DAOException("Error al finalizar la conexion con la base de datos", ex);
        }

    }

    @Override
    public void iniciarTransaccion() throws DAOException {
        try {
            ConexionJDBC.getInstance().getConnection().setAutoCommit(false); //deja en espera a la base de datos para que no haga comit
        } catch (SQLException ex) {
            throw new DAOException("Error al inicair la transaccion", ex);
        }

    }

    @Override
    public void finalizarTransaccion() throws DAOException {
        try {
            ConexionJDBC.getInstance().getConnection().commit();
        } catch (SQLException ex) {
            try {
                ConexionJDBC.getInstance().getConnection().rollback();
            } catch (SQLException ex1) {
                throw new DAOException("No se ha podido finalizar la transsaccion. Se han desecho los cambios", ex);
            }
        }

    }
}
