package net.severo.taller.DAO.JDBC;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.IMecanico;
import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MecanicoJDBC implements IMecanico {

    static String mostrarTodosMecanicos = "SELECT * FROM mecanico;";
    static String getMecanico = "SELECT * FROM mecanico WHERE idMecanico=?;";
    static String insertMecanico = "INSERT INTO mecanico(idMecanico,nombreMecanico) VALUES (?,?);";
    static String borrarMecanico = "DELETE FROM mecanico WHERE idMecanico=?;";
    static String modificarMecanico = "UPDATE FROM mecanico WHERE idMecanico=?;";

    static String trampaParaOsos = "SET FOREIGN_KEY_CHECKS=0";

    static String asignarMecanico = "INSERT INTO vehiculomecanico(idMecanico,idVehiculo) VALUES (?,?);";
    static String borrarMecanicoVehiculo = "DELETE FROM vehiculomecanico WHERE idMecanico = ?;";


    public MecanicoJDBC() throws DAOException {

        //acedemos al singleton ahora por si hay fallos que salten aqui
        ConexionJDBC.getInstance().getConnection();

    }

    @Override
    public void crearMecanico(Mecanico mecanico) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(insertMecanico);
            ps.setInt(1, mecanico.getIdMecanico());
            ps.setString(2, mecanico.getNombreMecanico());


            @SuppressWarnings("unused")
            int afectadas = ps.executeUpdate();
            //Este entero no lo vamos a usar pero devuelve el número de filas aceptadas
            //En otras ocasiones nos puede ser útil, aquí siempre debe devolver 1


        } catch (Exception ex) {
            throw new DAOException("Ha habido un problema al insertar el mecánico en la base de datos: ", ex);
        } finally {
            try {
                ps.close();

            } catch (SQLException sqlex) {
                throw new DAOException("Error al cerrar la sentencia", sqlex);
            }
        }

    }

    @Override
    public void modificarMecanico(Mecanico mecanico) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(modificarMecanico);
            ps.setInt(1, mecanico.getIdMecanico());
            ps.setString(2, mecanico.getNombreMecanico());

            @SuppressWarnings("unused")
            int afectadas = ps.executeUpdate();
            //Este entero no lo vamos a usar pero devuelve el número de filas aceptadas
            //En otras ocasiones nos puede ser útil, aquí siempre debe devolver 1


        } catch (Exception ex) {
            throw new DAOException("Ha habido un problema al modificar el mecánico en la base de datos: ", ex);
        } finally {
            try {
                ps.close();

            } catch (SQLException sqlex) {
                throw new DAOException("Error al cerrar la sentencia", sqlex);
            }
        }

    }

    @Override
    public void eliminarMecanico(int idMecanico) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionJDBC.getInstance().getConnection();


            //ps = conn.prepareStatement(borrarMecanicoVehiculo);
            //ps.setInt(1, idMecanico);
            //ps = conn.prepareStatement(trampaParaOsos);

            ps = conn.prepareStatement(borrarMecanico);
            ps.setInt(1, idMecanico);

            int afectadas = ps.executeUpdate();


        } catch (Exception ex) {
            throw new DAOException("Ha habido un problema al eliminar el mecánico de la base de datos: ", ex);
            //ex.printStackTrace();
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
    public List<Mecanico> obtenerTodosMecanicos() throws DAOException {
        List<Mecanico> mecanicos = new ArrayList<Mecanico>();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(mostrarTodosMecanicos);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Mecanico j = new Mecanico();
                int codMecanico = rs.getInt("idMecanico");
                String nombre = rs.getString("nombreMecanico");


                j.setIdMecanico(codMecanico);
                j.setNombreMecanico(nombre);

                mecanicos.add(j);

            }
            return mecanicos;


        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener la lista de mecánicos en la base de datos: ", e);
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar la base de datos", ex);
            }

        }
    }

    @Override
    public Mecanico obtenerMecanicoPorID(int id) throws DAOException {
        Mecanico j = new Mecanico();
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(getMecanico);
            ps.setInt(1, id);

            ResultSet rs = ps.executeQuery(); //el string se transforma en una sentencia de la bd, un query, se guarda en rs
            if (!rs.next()) {
                // Nos metemos aquí si la consulta no devuelve nada
                return null;
            }

            int idMeca = rs.getInt("idVehiculo");
            String nombre = rs.getString("nombreMecanico");


            j.setIdMecanico(id);
            j.setNombreMecanico(nombre);

            return j;


        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener el mecánico de la base de datos: ", e);
        } finally {
            try {
                ps.close();

            } catch (SQLException ex) {
                throw new DAOException("Error al cerrar la base de datos", ex);
            }

        }
    }

    @Override
    public void asignarMecanicoVehiculo(Mecanico m, Vehiculo v) throws DAOException {
        Connection conn = null;
        PreparedStatement ps = null;
        Set<Vehiculo> vehiculos = new HashSet<>();

        try {
            conn = ConexionJDBC.getInstance().getConnection();

            ps = conn.prepareStatement(trampaParaOsos);

            ps = conn.prepareStatement(asignarMecanico);
            ps.setInt(1, m.getIdMecanico());
            ps.setInt(2, v.getIdVehiculo());
            vehiculos.add(v);

            @SuppressWarnings("unused")
            int afectadas = ps.executeUpdate();
            //Este entero no lo vamos a usar pero devuelve el número de filas aceptadas
            //En otras ocasiones nos puede ser útil, aquí siempre debe devolver 1


        } catch (Exception ex) {
            throw new DAOException("Ha habido un problema al insertar el mecánico en la base de datos: ", ex);
        } finally {
            try {
                ps.close();

            } catch (SQLException sqlex) {
                throw new DAOException("Error al cerrar la sentencia", sqlex);
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
            throw new DAOException("Error al iniciar la transaccion", ex);
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

/**
 * ***********************************************FIN DEL CODIGO ***************************************************
 * <p>
 * LO DE ABAJO SON PRUEBAS
 */

/*static  String JoinMecanico= "SELECT a.nombreMecanico, b.matricula matricula_vehiculo \n" +
            "FROM vehiculomecanico c \n" +
            "RIGTH JOIN mecanico a \n" +
            "ON c.idMecanico= a.idMecanico \n" +
            "RIGTH JOIN vehiculo b\n" +
            "ON c.idVehiculo = b.idVehiculo";*/
