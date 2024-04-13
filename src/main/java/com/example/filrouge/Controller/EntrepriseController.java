package com.example.filrouge.Controller;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Dtos.EntrepriseDto;
import com.example.filrouge.Exception.MessageError;
import com.example.filrouge.Service.IEntrepriseService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/entreprise")
@CrossOrigin("*")
public class EntrepriseController {
    private final IEntrepriseService iEntrepriseService;

    public EntrepriseController(IEntrepriseService iEntrepriseService) {
        this.iEntrepriseService = iEntrepriseService;
    }


    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<EntrepriseDto>> getAll() {
        return ResponseEntity.ok(iEntrepriseService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntrepriseDto> save(@RequestBody @Valid EntrepriseDto entrepriseDto) {
        EntrepriseDto entrepriseSaved = iEntrepriseService.add(entrepriseDto);
        return new ResponseEntity<>(entrepriseSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntrepriseDto> update(@RequestBody @Valid EntrepriseDto entrepriseDto, @PathVariable Long id) {
        EntrepriseDto entrepriseUpdated = iEntrepriseService.update(id, entrepriseDto);
        return new ResponseEntity<>(entrepriseUpdated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntrepriseDto> getById(@PathVariable Long id) {
        try {
            EntrepriseDto entrepriseDto = iEntrepriseService.getById(id);
            return new ResponseEntity<>(entrepriseDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/entreprise")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<EntrepriseDto> getByName(@RequestParam String name) {
        try {
            EntrepriseDto entrepriseDto = iEntrepriseService.getByName(name);
            return new ResponseEntity<>(entrepriseDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<MessageError> delete(@PathVariable Long id) {
        MessageError messageError = new MessageError("entreprise deleted successfully.");
        iEntrepriseService.delete(id);
        return new ResponseEntity<>(messageError, HttpStatus.OK);
    }


}
