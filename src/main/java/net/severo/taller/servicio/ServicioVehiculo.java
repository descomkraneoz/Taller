package net.severo.taller.servicio;

import net.severo.taller.DAO.DAOException;
import net.severo.taller.DAO.Hibernate.VehiculoHibernate;
import net.severo.taller.DAO.IVehiculo;
import net.severo.taller.DAO.JDBC.VehiculoJDBC;
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
            idao = new VehiculoJDBC();
        }
        if (opcion == 2) {

            idao = new VehiculoHibernate();
        }
        if (opcion == 3) {

            //NO IMPLEMENTADO
        }
    }

    //crear un vehiculo nuevo

    public void servicioCrearVehiculo(Vehiculo v) throws ServiciosException, DAOException {
        if (idao.obtenerUnVehiculoPorID(v.getIdVehiculo()) != null) {
            throw new ServiciosException("El vehiculo ya existe con esa id.");
        }

        idao.iniciarTransaccion();

        idao.crearNuevoVehiculoDAO(v);

        idao.finalizarTransaccion();

    }

    //obtener un vehiculo a partir de su id

    public Vehiculo servicioObtenerVehiculo(int idVehiculo) throws DAOException, ServiciosException {
        List<Vehiculo> vehiculos = idao.obtenerTodosVehiculos();
        for (Vehiculo v : vehiculos) {
            if (v.getIdVehiculo()==(idVehiculo)) {
                return v;
            }
        }
        throw new ServiciosException("No hay ningún vehiculo con el codigo especificado");
    }

    //elimina un vehiculo al pasarle un id

    public void servicioEliminarVehiculo(int codigoVehiculo) throws DAOException, ServiciosException {
        this.servicioObtenerVehiculo(codigoVehiculo);
        //idao.iniciarTransaccion();
        idao.eliminarVehiculoDAO(codigoVehiculo);
        //idao.finalizarTransaccion();

    }

    //devuelve una lista con los vehiculos

    public List<Vehiculo> servicioObtenerVehiculos() throws DAOException, ServiciosException {
        List<Vehiculo> vehiculos = idao.obtenerTodosVehiculos();
        if (vehiculos.isEmpty()) {
            throw new ServiciosException("No hay ningún vehiculo");
        }
        return vehiculos;
    }


    //modifica la matricula de un vehiculo

    public void servicioModificarMatricula(int idVehiculo, String nuevaMatricula) throws DAOException, ServiciosException {
        Vehiculo v = this.servicioObtenerVehiculo(idVehiculo);
        v.setMatricula(nuevaMatricula);
        idao.modificarVehiculoDAO(v);
    }

    //modifica la propiedad de un vehiculo sobre si es o no electrico

    public void servicioModificarEsElectrico(int idVehiculo, boolean esElectrico) throws DAOException, ServiciosException {
        Vehiculo v = this.servicioObtenerVehiculo(idVehiculo);
        v.setEsElectrico(esElectrico);
        idao.modificarVehiculoDAO(v);
    }

    //modifica la fecha de matriculacion de un vehiculo

    public void servicioModificarFechaMatriculacion(int idVehiculo, Date nuevaFechaMatriculacion) throws DAOException, ServiciosException {
        Vehiculo v = this.servicioObtenerVehiculo(idVehiculo);
        v.setFechaMatriculacion(nuevaFechaMatriculacion);
        idao.modificarVehiculoDAO(v);
    }


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

/**
 * ***************************************************** FIN DE CODIGO ******************************************************
 * <p>
 * AQUI ABAJO VAN LAS PRUEBAS
 */

//devuelve una lista con los vehiculos a partir de la fecha de matriculación de estos

/*public List<Vehiculo> servicioObtenerVehiculosFechaMatriculacion(Date fecha) throws DAOException, ServiciosException {
    List<Vehiculo> vehiculos = idao.obtenerVehiculosPorFechaMatriculacion(fecha);
    if (vehiculos.isEmpty()) {
        throw new ServiciosException("No hay ningún vehiculo con esa fecha de matriculación");
    }
    return vehiculos;
}*/

//devuelve una lista con los vehiculos electricos

    /*public List<Vehiculo> servicioObtenerVehiculosElectricos(boolean esElectrico) throws DAOException, ServiciosException {
        List<Vehiculo> vehiculos = idao.obtenerTodosVehiculosElectricos(esElectrico);
        if (vehiculos.isEmpty()) {
            throw new ServiciosException("No hay ningún vehiculo eléctrico");
        }
        return vehiculos;
    }*/

