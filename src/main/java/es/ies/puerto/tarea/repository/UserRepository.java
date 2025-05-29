package es.ies.puerto.tarea.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.ies.puerto.tarea.model.User;

/**
 * Clase que implementa los metodos de Jpa a la clase User.
 * @author nexphernandez
 * @author mahoramas
 * @author alexfdb
 * @author cdiagal
 * @version 1.0.0
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer>{
}