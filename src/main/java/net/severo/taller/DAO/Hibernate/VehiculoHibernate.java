package net.severo.taller.DAO.Hibernate;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.IVehiculo;
import net.severo.taller.pojo.Vehiculo;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;
import java.util.List;

public class VehiculoHibernate implements IVehiculo {
    Transaction tx = null;

    @Override
    public void crearNuevoVehiculoDAO(Vehiculo vehiculo) throws DAOException {
        Session sesion = null;
        try {

            sesion = SesionHibernate.getInstance().getSesion();

            sesion.save(vehiculo);


        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al insertar un nuevo vehiculo", e);
        }
    }

    @Override
    public void modificarVehiculoDAO(Vehiculo vehiculo) throws DAOException {
        try {
            Session sesion = SesionHibernate.getInstance().getSesion();


            sesion.update(vehiculo);

        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al modificar el vehiculo", e);
        }

    }

    @Override
    public void eliminarVehiculoDAO(int idVehiculo) throws DAOException {
        try {
            Vehiculo j = this.obtenerUnVehiculoPorID(idVehiculo);
            Session sesion = SesionHibernate.getInstance().getSesion();

            // Iniciamos una transaccion
            //Transaction tx = sesion.beginTransaction();

            // Hacemos los cambios
            sesion.delete(j);

            // Cerramos la transaccion
            //tx.commit();

            // Cerramos la sesion
            //sesion.close();
        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al eliminar el vehiculo", e);
        }

    }

    @Override
    public Vehiculo obtenerUnVehiculoPorID(int idVehiculo) throws DAOException {
        Session sesion = SesionHibernate.getInstance().getSesion();
        try {

            Vehiculo j = new Vehiculo();

            j = (Vehiculo) sesion.get(Vehiculo.class, idVehiculo);

            return j;

        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener el vehiculo", e);
        }
    }

    @Override
    public List<Vehiculo> obtenerTodosVehiculos() throws DAOException {
        try {
            Session sesion = SesionHibernate.getInstance().getSesion();

            // Variable que almacena la lista a devolver
            List<Vehiculo> lista;

            // Hacemos la consulta
            Query q = sesion.createQuery("from Vehiculo");
            lista = q.list();
            for (Vehiculo j : lista) {
                Hibernate.initialize(j.getIdVehiculo());

            }


            return lista;
        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener los vehiculos", e);

        }
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
        SesionHibernate.getInstance().getSesion().close();
        SesionHibernate.getInstance().getSessionFactory().close();

    }

    @Override
    public void iniciarTransaccion() throws DAOException {
        this.tx = SesionHibernate.getInstance().getSesion().beginTransaction();

    }

    @Override
    public void finalizarTransaccion() throws DAOException {
        this.tx.commit();

    }
}
