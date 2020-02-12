package net.severo.taller.servicio;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.IMecanico;
import net.severo.taller.pojo.Mecanico;
import net.severo.taller.pojo.Vehiculo;

import java.util.ArrayList;
import java.util.List;

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

            //idao=new ReservaHibernate();
        }
        if (opcion == 3) {

            // no implementado
        }

    }

    //obtener un mecanico a partir de su id

    public Mecanico obtenerMecanicoPorID(int idMecanico) throws DAOException, ServiciosException {
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos();
        for (Mecanico m : mecanicos) {
            if (m.getIdMecanico() == (idMecanico)) {
                return m;
            }
        }
        throw new ServiciosException("No hay ningún mecánico con el codigo especificado");
    }

    //crear un nuevo mecanico

    public void nuevoMecanico(Mecanico r) throws ServiciosException, DAOException {
        if (idao.obtenerMecanicoPorID(r.getIdMecanico()) != null) {
            throw new ServiciosException("El mecánico ya existe.");
        }
        //idao.iniciarTransaccion();
        idao.crearMecanico(r);
        //dao.finalizarTransaccion();
    }

    //obtener todos los mecanicos en una lista

    public List<Mecanico> obtenerTodosMecanicos() throws DAOException, ServiciosException {
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos();
        if (mecanicos.isEmpty()) {
            throw new ServiciosException("No hay ningun mecánico");
        }

        return mecanicos;
    }

    //obtener un listado de vehiculos al pasarle una id de mecanico

    public List<Vehiculo> obtenerListaVehiculosAPartirIdMecanico(int idMecanico) throws DAOException, ServiciosException {
        ServicioVehiculo.getServicio().obtenerVehiculo(idMecanico);
        List<Vehiculo> v = idao.obtenerVehiculosPorMecanico(idMecanico);
        if (v.isEmpty()) {
            throw new ServiciosException("No hay ningun vehiculo para ese id de mecánico");
        }
        return v;
    }

    public List<Vehiculo> obtenerListaVehiculos(int idMecanico) throws DAOException, ServiciosException {
        ServicioVehiculo.getServicio().obtenerVehiculo(idMecanico);
        List<Vehiculo> v = idao.obtenerVehiculosPorMecanico(idMecanico);
        if (v.isEmpty()) {
            throw new ServiciosException("No hay ningun vehiculo para ese id de mecánico");
        }
        return v;
    }

    public ArrayList<Vehiculo> obtenerTodosLosVehiculos() throws DAOException, ServiciosException {
        //dao.iniciarTransaccion();
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos();
        ArrayList<Vehiculo> totalVehiculos = new ArrayList<>();
        //dao.finalizarTransaccion();
        for (Mecanico r : mecanicos) {
            for (Vehiculo p : r.getVehiculos()) {
                totalVehiculos.add(p);
            }
        }
        return totalVehiculos;
    }


    //eliminar un mecanico al pasarle un objeto mecanico

    public void eliminarMecanico(Mecanico m) throws DAOException, ServiciosException {
        if (!(idao.obtenerTodosMecanicos().contains(m))) {
            throw new ServiciosException("El mecánico no existe");
        }
        //idao.iniciarTransaccion();
        idao.eliminarMecanico(m.getIdMecanico());
        //idao.finalizarTransaccion();
    }

    //eliminar un mecanico al pasarle un id

    public void eliminarMecanico(int codigo) throws DAOException, ServiciosException {

        if (obtenerMecanicoPorID(codigo) == null) {
            throw new ServiciosException("El mecánico no existe");
        }
        idao.eliminarMecanico(codigo);

    }

    //modificar nombre de un mecanico
    public void modificarNombreMecanico(int idMecanico, String nuevoNombre) throws DAOException, ServiciosException {
        Mecanico m = this.obtenerMecanicoPorID(idMecanico);
        m.setNombreMecanico(nuevoNombre);
        idao.modificarMecanico(m);
    }


    //modificar lista de vehiculos de un mecanico
    public void modificarListaVehiculosMecanico(int idMecanico, List<Vehiculo> nuevaLista) throws DAOException, ServiciosException {
        Mecanico m = this.obtenerMecanicoPorID(idMecanico);
        m.setVehiculos(nuevaLista);
        idao.modificarMecanico(m);
    }

    public List<Mecanico> obtenerListaMecanicosPorId(int id) throws DAOException, ServiciosException {
        List<Mecanico> mecanicos = idao.obtenerTodosMecanicos(id);
        if (mecanicos.isEmpty()) {
            throw new ServiciosException("No hay ningun mecanico con ese id de vehiculo");
        }
        return mecanicos;
    }

    public int obtenerNumeroDeVehiculosDelMecanico(int codigoMecanico) throws DAOException, ServiciosException {
        int contador = 0;
        for (Mecanico m : this.obtenerListaMecanicosPorId(codigoMecanico)) {
            for (Vehiculo v : m.getVehiculos()) {
                if (v.getMatricula() != null) {
                    contador++;
                }
            }
        }
        return contador;
    }


    //transacciones

    public void iniciarTransaccion() throws DAOException {
        idao.iniciarTransaccion();
    }

    public void finalizarTransaccion() throws DAOException {
        idao.finalizarTransaccion();
    }

}
