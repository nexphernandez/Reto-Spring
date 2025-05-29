package es.ies.puerto.tarea.model;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
/**
 * @author nexphernandez
 * @author mahoramas
 * @author alexfdb
 * @author cdiagal
 * @version 1.0.0
 */
@Entity
@Table(name = "Usuario")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @NotBlank(message = "nombre es obligatorio")
    @Column(name = "nombre", nullable = false)
    private String nombre;

    /**
     * Constructor vacio
     */
    public User() {
    }

    /**
     * Constructor con el atributo principal
     * 
     * @param idUser del usuario
     */
    public User(int idUser) {
        this.idUser = idUser;
    }

    /**
     * Constructor con todas las propiedades de la clase.
     * 
     * @param idUser del usuario
     * @param nombre del usuario
     */
    public User(int idUser, String nombre) {
        this.idUser = idUser;
        this.nombre = nombre;
    }

    public int getIdUser() {
        return this.idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return Objects.equals(idUser, user.idUser);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser);
    }

    @Override
    public String toString() {
        return "{" +
                " idUser='" + getIdUser() + "'" +
                ", nombre='" + getNombre() + "'" +
                "}";
    }

}