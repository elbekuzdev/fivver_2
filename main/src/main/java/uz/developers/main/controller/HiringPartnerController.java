package uz.developers.main.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import uz.developers.main.dto.HiringPartnerDto;
import uz.developers.main.dto.ResponseDto;
import uz.developers.main.service.HiringPartnerService;

import javax.validation.Valid;

@RequiredArgsConstructor
@RequestMapping("/main/hiringPartner")
@RestController
public class HiringPartnerController {
    private final HiringPartnerService hiringPartnerService;

//    @PreAuthorize("hasAnyRole('CREATE')")
    @PostMapping("/add")
    public ResponseDto save(@Valid @RequestBody HiringPartnerDto hiringpartnerDto) {
        return hiringPartnerService.save(hiringpartnerDto);
    }

    @GetMapping("/getAll")
    public ResponseDto getAll(){
        return hiringPartnerService.getAll();
    }

    @GetMapping("/getById/{id}")
    public ResponseDto getById(@PathVariable Integer id){
        return hiringPartnerService.getById(id);
    }

//    @PreAuthorize("hasAnyRole('UPDATE')")
    @PutMapping("/update")
    public ResponseDto update(@Valid @RequestBody HiringPartnerDto hiringpartnerDto){
        return hiringPartnerService.update(hiringpartnerDto);
    }

//    @PreAuthorize("hasAnyRole('DELETE')")
    @DeleteMapping("/delete/{id}")
    public ResponseDto delete(@PathVariable Integer id){
        return hiringPartnerService.deleteById(id);
    }




}
