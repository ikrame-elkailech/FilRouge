package com.example.filrouge.Controller;

import com.example.filrouge.Dtos.CandidatDto;
import com.example.filrouge.Exception.MessageError;
import com.example.filrouge.Service.ICandidatService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/candidat")
@CrossOrigin("*")
public class CandidatController {

    private final ICandidatService iCandidatService;

    public CandidatController(ICandidatService iCandidatService) {
        this.iCandidatService = iCandidatService;
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<CandidatDto>> getAll() {
        return ResponseEntity.ok(iCandidatService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CandidatDto> save(@RequestBody @Valid CandidatDto candidatDto) {
        CandidatDto candidatSaved = iCandidatService.add(candidatDto);
        return new ResponseEntity<>(candidatSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CandidatDto> update(@RequestBody @Valid CandidatDto candidatDto, @PathVariable Long id) {
        CandidatDto candidatUpdated = iCandidatService.update(id, candidatDto);
        return new ResponseEntity<>(candidatUpdated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CandidatDto> getById(@PathVariable Long id) {
        try {
            CandidatDto patient = iCandidatService.getById(id);
            return new ResponseEntity<>(patient, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/candidat")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<CandidatDto> getByName(@RequestParam String name) {
        try {
            CandidatDto candidatDto = iCandidatService.getByName(name);
            return new ResponseEntity<>(candidatDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<MessageError> delete(@PathVariable Long id) {
        MessageError messageError = new MessageError("Candidat deleted successfully.");
        iCandidatService.delete(id);
        return new ResponseEntity<>(messageError, HttpStatus.OK);
    }


}
