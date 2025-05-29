package es.ies.puerto.tarea.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author nexphernandez
 * @author mahoramas
 * @author alexfdb
 * @author cdiagal
 * @version 1.0.0
 */
import es.ies.puerto.tarea.model.Rol;
import es.ies.puerto.tarea.repository.RolRepository;

@Component
public class RolService {
    
    private RolRepository rolRepository;


    @Autowired
    public void setRolRepository(RolRepository rolRepository){
        this.rolRepository = rolRepository;
    }

    /**
     * Metodo que obtiene todos los roles de la base de datos
     * @return lista de roles
     */
    public List<Rol> getAllRoles(){
        return rolRepository.findAll();
    }

    /**
     * Metodo para obtener un rol por id
     * @param rolId el id del rol que queremos obtener
     * @return el rol que coincida con el id
     */
    public Rol getRolId(int rolId){
        return rolRepository.findById(rolId).orElse(null);
    }

    /**
     * Metodo que crea un rol en la base de datos
     * @param rol rol que queremos crear
     * @return el rol creado
     */
    public Rol createRol(Rol rol) {
        if (rol == null) {
            throw new RuntimeException("el rol tiene que existir");
        }
        return rolRepository.save(rol);
    }

    /**
     * Metodo que actualiza un rol de la base de datos
     * @param rolId id del rol que queremos actualizar
     * @param rolDetails rol actualizado
     * @return
     */
    public Rol updateRol(int rolId, Rol rolDetails) {
        Rol rol = rolRepository.findById(rolId).orElse(null);
        if (rol != null) {
            rol.setNombreRol(rolDetails.getNombreRol());
            return rolRepository.save(rol);
        }
        throw new RuntimeException("no existe el rol a actualizar");
    }

    /**
     * Metodo para eliminar un rol de la base de datos
     * @param rolId id del rol que se quiere eliminar
     */
    public void deleteRol(int rolId){
        if (rolRepository.existsById(rolId)) {
            rolRepository.deleteById(rolId);
        } else {
            throw new RuntimeException("El rol a eliminar no existe");
        }
    }
}
