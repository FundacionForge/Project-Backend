package com.example.forge.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.forge.models.dto.UserDto;
import com.example.forge.models.entities.User;
import com.example.forge.models.request.UserRequest;

import jakarta.validation.Valid;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {
  @Autowired
  private UserService service;

  private ResponseEntity<?> validation(BindingResult result) {
    Map<String, String> errors = new HashMap<>();

    result.getFieldErrors().forEach(err -> {
      errors.put(err.getField(), "El campo "+err.getField()+" "+err.getDefaultMessage());
    });
    return ResponseEntity.badRequest().body((errors));
  }

  @GetMapping
  public List<UserDto> list() {
    return service.findAll();
  }

  @GetMapping("{id}")
  public ResponseEntity<?> show(@PathVariable Long id) {
    Optional<UserDto> userOptional = service.findById(id);
    if(userOptional.isPresent()) {
      return ResponseEntity.ok(userOptional.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  };

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  public ResponseEntity<?> create(@Valid @RequestBody User user, BindingResult result) {

    if(result.hasErrors()){
      return this.validation(result);
    }

    return  ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
  }

  @PutMapping("{id}")
  public ResponseEntity<?> update(@Valid @RequestBody UserRequest user, Long id, BindingResult result) {
    Optional<UserDto> o = service.update(user, id);
    if(o.isPresent()){
      return ResponseEntity.status(HttpStatus.CREATED).body(o.orElseThrow());
    }
    return ResponseEntity.notFound().build();
  }

  @DeleteMapping("{id}")
  public ResponseEntity<?> remove(@PathVariable() Long id) {
    Optional<UserDto> o = service.findById(id);
    if(o.isPresent()) {
      service.remove(id);
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
  }
}
