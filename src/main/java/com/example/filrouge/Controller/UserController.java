package com.example.filrouge.Controller;

import com.example.filrouge.Dtos.EntretienDto;
import com.example.filrouge.Dtos.UserDto;
import com.example.filrouge.Exception.MessageError;
import com.example.filrouge.Service.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/user")
@CrossOrigin("*")
public class UserController {

    private final IUserService iUserService;

    public UserController(IUserService iUserService) {
        this.iUserService = iUserService;
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<UserDto>> getAll() {
        return ResponseEntity.ok(iUserService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<UserDto> save(@RequestBody @Valid UserDto userDto) {
        UserDto userSaved = iUserService.add(userDto);
        return new ResponseEntity<>(userSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<UserDto> update(@RequestBody @Valid UserDto userDto, @PathVariable Long id) {
        UserDto userUpdated = iUserService.update(id, userDto);
        return new ResponseEntity<>(userUpdated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<UserDto> getById(@PathVariable Long id) {
        try {
            UserDto userDto = iUserService.getById(id);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/user")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<UserDto> getByName(@RequestParam String name) {
        try {
            UserDto userDto = iUserService.getByName(name);
            return new ResponseEntity<>(userDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<MessageError> delete(@PathVariable Long id) {
        MessageError messageError = new MessageError("user deleted successfully.");
        iUserService.delete(id);
        return new ResponseEntity<>(messageError, HttpStatus.OK);
    }



}
