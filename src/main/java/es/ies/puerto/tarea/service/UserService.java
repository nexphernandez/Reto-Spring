package es.ies.puerto.tarea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import es.ies.puerto.tarea.model.User;
/**
 * @author nexphernandez
 * @author mahoramas
 * @author alexfdb
 * @author cdiagal
 * @version 1.0.0
 */
import es.ies.puerto.tarea.repository.UserRepository;

@Component
public class UserService{
    
    private UserRepository userRepository;

    @Autowired
    public void setUserService(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    /**
     * Funcion que encuentra todos los usuarios de la bbdd
     * @return lista de usuarios
     */
    public List<User> obtenerTodos(){
        return userRepository.findAll();
    }

    /**
     * Metodo que crea un usuario en la bbdd
     * @param usuario
     * @return usuario creado
     */
    public User aniadirUsuario(User usuario){
        if (usuario == null) {
            throw new RuntimeException("No exise el usuario a crear");
        }
        return userRepository.save(usuario);
    }

    /**
     * Metodo que actualiza un usuario de la bbdd
     * @param usuario con los datos actualizados
     * @return usuario actualizado
     */
    public User actualizarUsuario(User usuario){
        User user = userRepository.findById(usuario.getIdUser()).orElse(null);
        if (user != null) {
            user.setNombre(usuario.getNombre());
            return userRepository.save(user);
        }
            throw new RuntimeException("no existe el usuario a actualizar");
    }

    /**
     * Metodo que elimina un usuario de la bbdd
     * @param usuario a eliminar
     */
    public void eliminarUsuario(User usuario){
        if (userRepository.existsById(usuario.getIdUser())) {
            userRepository.delete(usuario);
        }else{
            throw new RuntimeException("no exite el usuario a eliminar");
        }
    }

    /**
     * Metodo que obtiene un usuario segun su id
     * @param usuarioId
     * @return usuario
     */
    public User obtenerUsuarioPorId(int usuarioId){
        return userRepository.findById(usuarioId).orElse(null);
    }
}
