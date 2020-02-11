package net.severo.taller.pojo;

import java.util.Date;
import java.util.Objects;
import java.util.Random;

public class Vehiculo {
    private int idVehiculo;
    private String matricula;
    private boolean esElectrico;
    private Date fechaMatriculacion;

    public Vehiculo(int id, String matricula, boolean esElectrico, Date fechaMatriculacion) {
        this.idVehiculo = id;
        this.matricula = matricula;
        this.esElectrico = esElectrico;
        this.fechaMatriculacion = fechaMatriculacion;
    }

    public Vehiculo() {
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int id) {
        this.idVehiculo = id;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean getEsElectrico() {
        return esElectrico;
    }

    public void setEsElectrico(boolean esElectrico) {
        this.esElectrico = esElectrico;
    }

    public Date getFechaMatriculacion() {
        return fechaMatriculacion;
    }

    public void setFechaMatriculacion(Date fechaMatriculacion) {
        this.fechaMatriculacion = fechaMatriculacion;
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "id=" + idVehiculo +
                ", matricula='" + matricula + '\'' +
                ", esElectrico=" + esElectrico +
                ", fechaMatriculacion=" + fechaMatriculacion +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vehiculo)) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return getIdVehiculo() == vehiculo.getIdVehiculo();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdVehiculo());
    }


    //metodo para generar una matricula válida

    public static String generaMatricula() {
        //Letras válidas para matrícula
        char[] array = {'B', 'C',
                'D', 'F', 'G', 'H', 'J', 'K', 'L',
                'M', 'N', 'P', 'R', 'S', 'T', 'V',
                'W', 'X', 'Y', 'Z'};

        String matricula = "";

        for (int i = 0; i < 7; i++) {
            Random rnd = new Random();
            int ale = (int) (rnd.nextDouble() * array.length); //Aleatorio para la letra
            int ale2 = (int) (rnd.nextDouble() * 10); //Aleatorio entre 0-9
            if (i > 3) {
                matricula += array[ale];
            } else {
                matricula += ale2;
            }
        }

        return matricula;

    }

}


