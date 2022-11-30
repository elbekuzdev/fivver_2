package uz.developers.main.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.PartnerDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.PartnerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/main/partner")
@RequiredArgsConstructor
public class PartnerController {

    private final PartnerService partnerService;

    @PreAuthorize("hasAnyRole('UPDATE')")
    @PutMapping("/update/{id}")
    public ResponseDto update(@PathVariable Integer id, @Valid@RequestBody PartnerDto partnerDto){
        partnerDto.setId(id);
        return partnerService.update(id, partnerDto);
    }

    @PreAuthorize("hasAnyRole('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id){
        return partnerService.deleteById(id);
    }


    }
