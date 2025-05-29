package es.ies.puerto.tarea.model;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
/**
 * @author nexphernandez
 * @author mahoramas
 * @author alexfdb
 * @author cdiagal
 * @version 1.0.0
 */

@Entity
@Table(name= "Rol")
public class Rol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idRol;
    @Column(name = "NombreRol", nullable = false, unique = true)
    private String nombreRol;

    /**
     * Consturtor vacio
     */
    public Rol() {
    }

    /**
     * Constructor con el atributo principal de la clase
     * @param idRol de la clase
     */
    public Rol(int idRol) {
        this.idRol = idRol;
    }

    /**
     * Constructor de los atributos de la clase 
     * @param idRol de la clase
     * @param nombreRol de la clase
     */
    public Rol(int idRol, String nombreRol) {
        this.idRol = idRol;
        this.nombreRol = nombreRol;
    }


    public int getIdRol() {
        return this.idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getNombreRol() {
        return this.nombreRol;
    }

    public void setNombreRol(String nombreRol) {
        this.nombreRol = nombreRol;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Rol)) {
            return false;
        }
        Rol rol = (Rol) o;
        return Objects.equals(idRol, rol.idRol);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRol);
    }

    @Override
    public String toString() {
        return "{" +
            " idRol='" + getIdRol() + "'" +
            ", nombreRol='" + getNombreRol() + "'" +
            "}";
    }
    
}
