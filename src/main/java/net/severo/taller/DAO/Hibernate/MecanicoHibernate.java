package net.severo.taller.DAO.Hibernate;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.IMecanico;
import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MecanicoHibernate implements IMecanico {
    Transaction tx = null;

    @Override
    public void crearMecanico(Mecanico mecanico) throws DAOException {
        Session sesion = null;
        try {

            sesion = SesionHibernate.getInstance().getSesion();

            sesion.save(mecanico);


        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al insertar un nuevo mecánico", e);
        }
    }

    @Override
    public void modificarMecanico(Mecanico mecanico) throws DAOException {
        try {
            Session sesion = SesionHibernate.getInstance().getSesion();


            sesion.update(mecanico);

        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al modificar el mecánico", e);
        }

    }

    @Override
    public void eliminarMecanico(int idMecanico) throws DAOException {
        try {
            Mecanico j = this.obtenerMecanicoPorID(idMecanico);
            Session sesion = SesionHibernate.getInstance().getSesion();
           /* Query q = sesion.createSQLQuery("delete from vehiculomecanico where IdMecanico = :ID");
            q.setParameter("ID",idMecanico);
            q.executeUpdate();*/
            Query query = sesion.createQuery("delete Mecanico where ID_MECANICO = :ID");
            query.setParameter("ID", idMecanico);

            //sesion.delete(j);
            int result = query.executeUpdate();

        } catch (Exception e) {
            //throw new DAOException("Ha habido un problema al eliminar el mecánico", e);
            e.printStackTrace();
        }


    }

    @Override
    public List<Mecanico> obtenerTodosMecanicos() throws DAOException {
        try {
            Session sesion = SesionHibernate.getInstance().getSesion();

            List<Mecanico> lista;

            Query q = sesion.createQuery("from Mecanico");
            lista = q.list();
            for (Mecanico j : lista) {
                Hibernate.initialize(j.getIdMecanico());

            }

            return lista;
        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener los mecánicos", e);

        }
    }

    @Override
    public Mecanico obtenerMecanicoPorID(int id) throws DAOException {
        Session sesion = SesionHibernate.getInstance().getSesion();
        try {

            Mecanico j = new Mecanico();

            j = (Mecanico) sesion.get(Mecanico.class, id);

            return j;

        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al obtener el mecánico", e);
        }
    }

    @Override
    public void asignarMecanicoVehiculo(Mecanico m, Vehiculo v) throws DAOException {
        Session sesion = SesionHibernate.getInstance().getSesion();
        try {

            m.getVehiculos().add(v);
            v.getMecanicos().add(m);
            sesion.save(v);
            sesion.save(m);

        } catch (Exception e) {
            throw new DAOException("Ha habido un problema al asignar el mecánico al vehículo", e);
        }
    }


    @Override
    public List<Mecanico> obtenerTodosMecanicos(int idMecanico) throws DAOException {
        return null;
    }


    @Override
    public List<Vehiculo> obtenerVehiculosPorMecanico(int idMecanico) throws DAOException {
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
