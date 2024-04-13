package com.example.filrouge.Controller;

import com.example.filrouge.Dtos.EntrepriseDto;
import com.example.filrouge.Dtos.EntretienDto;
import com.example.filrouge.Exception.MessageError;
import com.example.filrouge.Service.IEntretienService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/entretien")
@CrossOrigin("*")
public class EntretienController {

    private final IEntretienService iEntretienService;

    public EntretienController(IEntretienService iEntretienService) {
        this.iEntretienService = iEntretienService;
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<EntretienDto>> getAll() {
        return ResponseEntity.ok(iEntretienService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntretienDto> save(@RequestBody @Valid EntretienDto entretienDto) {
        EntretienDto entretienSaved = iEntretienService.add(entretienDto);
        return new ResponseEntity<>(entretienSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntretienDto> update(@RequestBody @Valid EntretienDto entretienDto, @PathVariable Long id) {
        EntretienDto entretienUpdated = iEntretienService.update(id, entretienDto);
        return new ResponseEntity<>(entretienUpdated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntretienDto> getById(@PathVariable Long id) {
        try {
            EntretienDto entretienDto = iEntretienService.getById(id);
            return new ResponseEntity<>(entretienDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/entretien")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntretienDto> getByName(@RequestParam String name) {
        try {
            EntretienDto entretienDto = iEntretienService.getByName(name);
            return new ResponseEntity<>(entretienDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<MessageError> delete(@PathVariable Long id) {
        MessageError messageError = new MessageError("entreprise deleted successfully.");
        iEntretienService.delete(id);
        return new ResponseEntity<>(messageError, HttpStatus.OK);
    }



}
