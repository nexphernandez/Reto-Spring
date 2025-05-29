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

import es.ies.puerto.tarea.model.User;
import es.ies.puerto.tarea.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

/**
 * Clase controlador de Usuario.
 * @author nexphernandez
 * @author mahoramas
 * @author alexfdb
 * @author cdiagal
 * @version 1.0.0 
 */

@RestController
@RequestMapping(path="/api/v1", produces="application/json")
public class UserController {
    private UserService userService;

    @Autowired
    public void setUsuarioService(UserService userService){
        this.userService = userService;
    }
    
    @Operation(summary = "Obtiene todos los usuarios", description = "Devuele todos los usuarios", responses = {
                @ApiResponse(responseCode = "200", description = "Operacion efectuada", content = @Content(schema = @Schema(implementation = Message.class)))}
    )
    @GetMapping("/usuarios/")
    /**
     * Metodo para obtener todos los usuarios
     * @return una lista de usuarios
     */
    public List<User> obtenerTodosLosUsuarios(){
        return userService.obtenerTodos();
    }

    @Operation(summary = "Obtiene un usuario por su id", description = "Devuelve un usuario por su id", responses= {
                @ApiResponse(responseCode = "200", description = "Operacion efectuada", content = @Content(schema = @Schema(implementation = Message.class))),
                @ApiResponse(responseCode = "400", description = "Id insertado invalido"),
                @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @GetMapping("/usuarios/{id}")
    /**
     * Metodo para obtener usuarios por id
     * @param userId id del usuario
     * @return el usuario por id
     */
    public ResponseEntity<User> obtenerUsuarioPorId(@PathVariable(value = "id") int userId){
        User user = userService.obtenerUsuarioPorId(userId);
        return ResponseEntity.ok().body(user);
    }

    @Operation(summary = "Aniade un usuario", description = "Devuelve el usuario aniadido", responses ={
                @ApiResponse(responseCode = "200", description = "Operacion efectuada", content = @Content(schema = @Schema(implementation = Message.class))),
                @ApiResponse(responseCode = "400", description = "Informacion invalida"),
		        @ApiResponse(responseCode = "409", description = "Usuario ya existente")
    })
    @PostMapping("/add/usuarios/")
    /**
     * Metodo para crear un usuario
     * @param usuario usuario a crear
     * @return retorna el usuario creado
     */
        public User crearUsuario(@Valid @RequestBody User usuario) {
            if (usuario == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El usuario no puede ser nulo");
            }
            if (usuario.getIdUser() <1) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El id del usuario no puede ser nulo");
            }
            return userService.aniadirUsuario(usuario);
        }
    

    @Operation(summary = "Actualiza los datos de un usuario", description = "Devuelve el usuario actualizado", responses ={
                @ApiResponse(responseCode = "200", description = "Operacion efectuada", content = @Content(schema = @Schema(implementation = Message.class))),
                @ApiResponse(responseCode = "400", description = "Informacion invalida"),
		        @ApiResponse(responseCode = "404", description = "Usuario no encontrado")
    })
    @PutMapping("/update/usuarios/")
    /**
     * Metodo para actualizar un usuario
     * @param usuario usuario que quieres actualizar
     * @return retorna el usuario actualizado
     */
    public ResponseEntity<User> actualizaUsuario(@Valid @RequestBody User usuario){
        User usuarioActualizado = userService.actualizarUsuario(usuario);
        return ResponseEntity.ok(usuarioActualizado);
    }

     @Operation(summary = "Elimina un usuario por el id", description = "Devuelde true,false", responses = {
		@ApiResponse(responseCode = "200", description = "Successful operation"),
		@ApiResponse(responseCode = "400", description = "Invalid id supplied"),
		@ApiResponse(responseCode = "404", description = "Usuario not found") }
	)
    @DeleteMapping("/delete/usuarios/{id}")
    
    /**
     * Funcion que elimina un usuario
     * @param idUser del usuario a borrar
     * @return respuesta
     */
    public Map<String,Boolean> borrarUsuario(@PathVariable(value = "id") int idUser){
        User usuario = new User(idUser);
        userService.eliminarUsuario(usuario);
        Map<String,Boolean> respuesta = new HashMap<>();
        respuesta.put("Eliminado", true);
        return respuesta;
    }
}
