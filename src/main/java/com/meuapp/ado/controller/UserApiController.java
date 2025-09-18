package com.meuapp.ado.controller;

import com.meuapp.ado.dto.SignUpUser;
import com.meuapp.ado.entity.User;
import com.meuapp.ado.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserApiController {

    private final UserService userService;

    public UserApiController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping
    public List<User> list() {
        return userService.findAll();
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getOne(@PathVariable("id") String id) {
        User u = userService.findById(id);
        if (u == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(u);
    }


    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody SignUpUser dto) {
        userService.saveUser(dto); // service atual não retorna o criado
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable("id") String id,
                                       @Valid @RequestBody SignUpUser dto) {

        User payload = new User();
        payload.setNome(dto.getName());
        payload.setCpf(dto.getCpf());

        boolean ok = userService.updateUser(id, payload);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok().build();
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") String id) {
        boolean ok = userService.deleteUser(id);
        if (!ok) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.noContent().build();
    }
}
