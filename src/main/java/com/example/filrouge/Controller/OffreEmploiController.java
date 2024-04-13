package com.example.filrouge.Controller;

import com.example.filrouge.Dtos.EntretienDto;
import com.example.filrouge.Dtos.OffreEmploiDto;
import com.example.filrouge.Enum.TypeOffre;
import com.example.filrouge.Exception.MessageError;
import com.example.filrouge.Service.IOffreEmploiService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/offreEmploi")
@CrossOrigin("*")
public class OffreEmploiController {

    private final IOffreEmploiService iOffreEmploiService;

    public OffreEmploiController(IOffreEmploiService iOffreEmploiService) {
        this.iOffreEmploiService = iOffreEmploiService;
    }



    @GetMapping
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<List<OffreEmploiDto>> getAll() {
        return ResponseEntity.ok(iOffreEmploiService.getAll());
    }

    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<OffreEmploiDto> save(@RequestBody @Valid OffreEmploiDto offreEmploiDto) {
        OffreEmploiDto offreEmploiSaved = iOffreEmploiService.add(offreEmploiDto);
        return new ResponseEntity<>(offreEmploiSaved, HttpStatus.CREATED);
    }

    @PutMapping("/{id}/update")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<OffreEmploiDto> update(@RequestBody @Valid OffreEmploiDto offreEmploiDto, @PathVariable Long id) {
        OffreEmploiDto offreEmploiUpdated = iOffreEmploiService.update(id, offreEmploiDto);
        return new ResponseEntity<>(offreEmploiUpdated, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<OffreEmploiDto> getById(@PathVariable Long id) {
        try {
            OffreEmploiDto offreEmploiDto = iOffreEmploiService.getById(id);
            return new ResponseEntity<>(offreEmploiDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/typeOffre")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<OffreEmploiDto> getByTypeOffre(@RequestParam TypeOffre typeOffre) {
        try {
            OffreEmploiDto offreEmploiDto = iOffreEmploiService.getByTypeOffre(typeOffre);
            return new ResponseEntity<>(offreEmploiDto, HttpStatus.OK);
        } catch (NoSuchElementException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}/delete")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'USER')")
    public ResponseEntity<MessageError> delete(@PathVariable Long id) {
        MessageError messageError = new MessageError("Offre emploi deleted successfully.");
        iOffreEmploiService.delete(id);
        return new ResponseEntity<>(messageError, HttpStatus.OK);
    }




}
