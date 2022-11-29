package uz.developers.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.developers.auth.dto.ResponseDto;
import uz.developers.auth.repo.DistrictRepo;
import uz.developers.auth.repo.RegionRepo;

@Service
@RequiredArgsConstructor
public class RegionService {
    private final RegionRepo regionRepo;
    private final DistrictRepo districtRepo;

    public ResponseEntity<?> findAllRegion(){
        return ResponseEntity.ok(new ResponseDto(200, "ok", regionRepo.findAll()));
    }

    public ResponseEntity<?> findAllDistrict(){
        return ResponseEntity.ok(new ResponseDto(200, "ok", districtRepo.findAll()));
    }

    public ResponseEntity<?> findByRegionId(Integer regionId){
        return ResponseEntity.ok(new ResponseDto(200, "ok", districtRepo.findByRegionId(regionId)));
    }
}
