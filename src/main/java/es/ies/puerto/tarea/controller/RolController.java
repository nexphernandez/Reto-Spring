package es.ies.puerto.tarea.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import es.ies.puerto.tarea.model.Rol;
import es.ies.puerto.tarea.service.RolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * Clasecontrolador de rol.
 * @author nexphernandez
 * @author mahoramas
 * @author alexfdb
 * @author cdiagal
 * @version 1.0.0
 */

@RestController
@RequestMapping(path="/api/v1", produces="application/json")
public class RolController {
    
    private RolService rolService;

    @Autowired
    public void setRolRepository(RolService rolService){
        this.rolService = rolService;
    }

    @Operation(summary = "Obtener todos los roles", description = "Retornas todos los usuarios", responses = {
        @ApiResponse(responseCode = "200", description = "Operacion exitosa", content = @Content(schema = @Schema(implementation = Message.class)))
    })
    @GetMapping("/roles")
    /**
     * Funcion que obtiene todos los roles
     * @return lista de roles
     */
    public List<Rol> getAllRoles(){
        return rolService.getAllRoles();
    }

    @Operation(summary = "Obtener roles por id", description = "Devuelve roles por id", responses = {
        @ApiResponse(responseCode = "200", description = "Operacion exitosa", content = @Content(schema = @Schema(implementation = Message.class))),
        @ApiResponse(responseCode = "400", description = "Id proporcionado invalido"),
        @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @GetMapping("/rol/{id}")

    /**
     * Funcion que obtiene un rol por el id
     * @param rolId id del rol a buscar
     * @return rol buscado
     */
    public ResponseEntity<Rol> getRolById(@PathVariable(value = "id") int rolId){
        Rol rol = rolService.getRolId(rolId);
        return ResponseEntity.ok().body(rol);
    }

    @Operation(summary = "Aniade un rol", description = "Aniade un rol", responses = {
        @ApiResponse(responseCode = "200", description = "Operacion exitosa"),
        @ApiResponse(responseCode = "400", description = "Datos invalidos"),
        @ApiResponse(responseCode = "409", description = "Ya existe"),
    })
    @PostMapping("/add/rol")
    /**
     * Funcion que crea un rol 
     * @param rol a crear
     * @return rol creado
     */
        public Rol createRol(@Valid @RequestBody Rol rol){
            if (rol == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol no puede ser nulo"); 
            }
            if (rol.getIdRol() <1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El rol no puede ser nulo"); 
            }
            return rolService.createRol(rol);
        }
    
    @Operation(summary = "Modifica un usuario por id", description = "Modifica un usuario", responses = { 
                @ApiResponse(responseCode = "200", description = "Operacion exitosa"), 
                @ApiResponse(responseCode = "400", description = "El id proporcionado es invalido"), 
                @ApiResponse(responseCode = "404", description = "Rol no encontrado")
    })
    @PutMapping("/update/rol/{id}")
    /**
     * Funcion que actualiza un rol
     * @param rolId por actualizar.
     * @param rolDetails con todos los datos actualizados.
     * @return
     */
    public ResponseEntity<Rol> updateRol(@PathVariable(value = "id") int rolId, @Valid @RequestBody Rol rolDetails) {
        final Rol updatedRol = rolService.updateRol(rolId, rolDetails);
        return ResponseEntity.ok(updatedRol);
    }

    @Operation(summary = "Elimina un usuario por id", description = "Elimina un usuario", responses = {
            @ApiResponse(responseCode = "200", description = "Operacion exitosa"), 
            @ApiResponse(responseCode = "400", description = "El id proporcionado es invalido"), 
            @ApiResponse(responseCode = "404", description = "rol no encontrado")
        })
    @DeleteMapping("/delete/user/{id}")
    /**
     * Funcion que elimina un rol
     * @param idRol del rol a eliminar
     * @return respuesta
     */
    public Map<String, Boolean> deleteRol(@PathVariable(value = "id") int idRol){
        rolService.deleteRol(idRol);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Eliminado", Boolean.TRUE);
        return response;
    }
    
    
}
    

