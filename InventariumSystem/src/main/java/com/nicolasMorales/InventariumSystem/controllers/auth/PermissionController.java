package com.nicolasMorales.InventariumSystem.controllers.auth;

import com.nicolasMorales.InventariumSystem.entity.Permission;
import com.nicolasMorales.InventariumSystem.services.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *  @author Nicolas Morales.
 *  Controladores de permisos.
 */
@RestController
@RequestMapping("/api/permissions")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    /**
     * Controllador para obtener todos los permisos existentes.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y una lista de permisos.
     */
    @GetMapping
    public ResponseEntity<List> getAllPermissions() {
        List permissions = permissionService.findAll();
        return ResponseEntity.ok(permissions);
    }

    @GetMapping("/{id}")
    public ResponseEntity getPermissionById(@PathVariable Long id) {
        Optional<Permission> permission = permissionService.findById(id);
        return permission.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Controllador para registrar nuevos permisos.
     * @param permission Recibe el nuevo permiso.
     * @return ResponseEntity Devuelve esta entidad con el codigo de estado y un mensaje.
     */
    @PostMapping
    public ResponseEntity createPermission(@RequestBody Permission permission) {
        Permission newPermission = permissionService.save(permission);
        return ResponseEntity.ok(newPermission);
    }

}