package net.severo.taller.servicio;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.Hibernate.MecanicoHibernate;
import net.severo.taller.DAO.IMecanico;
import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ServicioMecanico {

    private static ServicioMecanico servicioMecanico = null;
    private IMecanico idao = null;

    public static ServicioMecanico getServicioMecanico() throws DAOException {
        if (servicioMecanico == null) {
            servicioMecanico = new ServicioMecanico();
        }
        return servicioMecanico;
    }

    public void elegirSistemaAlmacenamiento(int opcion) throws DAOException {

        if (opcion == 1) {
            //idao = new ReservasJDBCDAO();
        }
        if (opcion == 2) {

            idao = new MecanicoHibernate();
        }
        if (opcion == 3) {

            // no implementado
        }

    }

    //obtener un mecanico a partir de su id

    public Mecanico servicioObtenerMecanicoPorID(int idMecanico) throws DAOException, ServiciosException {
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos();
        for (Mecanico m : mecanicos) {
            if (m.getIdMecanico() == (idMecanico)) {
                return m;
            }
        }
        throw new ServiciosException("No hay ningún mecánico con el codigo especificado");
    }

    //crear un nuevo mecanico

    public void servicioNuevoMecanico(Mecanico r) throws ServiciosException, DAOException {
        if (idao.obtenerMecanicoPorID(r.getIdMecanico()) != null) {
            throw new ServiciosException("El mecánico ya existe.");
        }
        idao.iniciarTransaccion();
        idao.crearMecanico(r);
        idao.finalizarTransaccion();
    }

    //obtener todos los mecanicos en una lista

    public List<Mecanico> servicioObtenerTodosMecanicos() throws DAOException, ServiciosException {
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos();
        if (mecanicos.isEmpty()) {
            throw new ServiciosException("No hay ningun mecánico en la base de datos");
        }

        return mecanicos;
    }

    //obtener un listado de vehiculos al pasarle una id de mecanico

    public List<Vehiculo> obtenerListaVehiculosAPartirIdMecanico(int idMecanico) throws DAOException, ServiciosException {
        ServicioVehiculo.getServicio().servicioObtenerVehiculo(idMecanico);
        List<Vehiculo> v = idao.obtenerVehiculosPorMecanico(idMecanico);
        if (v.isEmpty()) {
            throw new ServiciosException("No hay ningun vehiculo para ese id de mecánico");
        }
        return v;
    }

    public List<Vehiculo> obtenerListaVehiculos(int idMecanico) throws DAOException, ServiciosException {
        ServicioVehiculo.getServicio().servicioObtenerVehiculo(idMecanico);
        List<Vehiculo> v = idao.obtenerVehiculosPorMecanico(idMecanico);
        if (v.isEmpty()) {
            throw new ServiciosException("No hay ningun vehiculo para ese id de mecánico");
        }
        return v;
    }

    public ArrayList<Vehiculo> servicioObtenerTodosLosVehiculos() throws DAOException, ServiciosException {
        idao.iniciarTransaccion();
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos();
        ArrayList<Vehiculo> totalVehiculos = new ArrayList<>();
        idao.finalizarTransaccion();
        for (Mecanico r : mecanicos) {
            for (Vehiculo p : r.getVehiculos()) {
                totalVehiculos.add(p);
            }
        }
        return totalVehiculos;
    }

    public void vehiculoExiste(Vehiculo vehiculo) throws DAOException, ServiciosException {
        if (this.servicioObtenerTodosLosVehiculos().contains(vehiculo)) {
            throw new ServiciosException("Este vehiculo ya existe");
        }
    }


    //eliminar un mecanico al pasarle un objeto mecanico

    public void servicioEliminarMecanico(Mecanico m) throws DAOException, ServiciosException {
        if (!(idao.obtenerTodosMecanicos().contains(m))) {
            throw new ServiciosException("El mecánico no existe");
        }
        idao.iniciarTransaccion();
        idao.eliminarMecanico(m.getIdMecanico());
        idao.finalizarTransaccion();
    }

    //eliminar un mecanico al pasarle un id

    public void servicioEliminarMecanico(int codigo) throws DAOException, ServiciosException {

        if (servicioObtenerMecanicoPorID(codigo) == null) {
            throw new ServiciosException("El mecánico no existe");
        }
        idao.iniciarTransaccion();
        idao.eliminarMecanico(codigo);
        idao.finalizarTransaccion();

    }

    //modificar nombre de un mecanico
    public void servicioModificarNombreMecanico(int idMecanico, String nuevoNombre) throws DAOException, ServiciosException {
        Mecanico m = this.servicioObtenerMecanicoPorID(idMecanico);
        m.setNombreMecanico(nuevoNombre);
        idao.modificarMecanico(m);
    }


    //modificar lista de vehiculos de un mecanico
    public void servicioModificarListaVehiculosMecanico(int idMecanico, List<Vehiculo> nuevaLista) throws DAOException, ServiciosException {
        Mecanico m = this.servicioObtenerMecanicoPorID(idMecanico);
        m.setVehiculos((Set<Vehiculo>) nuevaLista);
        idao.modificarMecanico(m);
    }

    public List<Mecanico> servicioObtenerListaMecanicosPorId(int id) throws DAOException, ServiciosException {
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos(id);
        if (mecanicos.isEmpty()) {
            throw new ServiciosException("No hay ningun mecanico con ese id de vehiculo");
        }
        return mecanicos;
    }

    public int obtenerNumeroDeVehiculosDelMecanico(int codigoMecanico) throws DAOException, ServiciosException {
        int contador = 0;
        for (Mecanico m : this.servicioObtenerListaMecanicosPorId(codigoMecanico)) {
            for (Vehiculo v : m.getVehiculos()) {
                if (v.getMatricula() != null) {
                    contador++;
                }
            }
        }
        return contador;
    }

    //obtener mecanicos sin vehiculo

    public List<Mecanico> obtenerVehiculosSinMecanico() throws DAOException, ServiciosException {
        List<Mecanico> mecanicos = new ArrayList<>();
        for (Mecanico m : this.servicioObtenerTodosMecanicos()) {
            if (m.getVehiculos().isEmpty()) {
                mecanicos.add(m);
            }
        }
        if (mecanicos.isEmpty()) {
            throw new ServiciosException("No hay mecánicos sin vehiculo");
        }
        return mecanicos;
    }

    //asignar vehiculos a un mecanico
    public void servicioAsignarMecanicoAlVehiculo(int idMecanico, int idVehiculo) throws DAOException, ServiciosException {

        Vehiculo v;
        v = ServicioVehiculo.getServicio().servicioObtenerVehiculo(idVehiculo);

        Mecanico m;

        m = this.servicioObtenerMecanicoPorID(idMecanico);

        idao.asignarMecanicoVehiculo(m, v);
    }





    //transacciones

    public void iniciarTransaccion() throws DAOException {
        idao.iniciarTransaccion();
    }

    public void finalizarTransaccion() throws DAOException {
        idao.finalizarTransaccion();
    }

}
