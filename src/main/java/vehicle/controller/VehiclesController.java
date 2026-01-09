package vehicle.controller;
import java.util.List;
import vehicle.dto.CreateVehicleDto;
import vehicle.dto.UpdateVehicleDto;
import vehicle.dto.PartialUpdateVehicleDto;
import vehicle.dto.VehicleResponseDto;
import vehicle.service.VehicleService;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/api/vheicles")
public class VehiclesController {

    private final VehicleService service;

    public VehiclesController(VehicleService service) {
        this.service = service;
    }

    @GetMapping
    public List<VehicleResponseDto> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public VehicleResponseDto findOne(@PathVariable Long id) {
        return service.findOne(id);
    }

    @PostMapping
    public VehicleResponseDto create( @RequestBody CreateVehicleDto dto) {
        return service.create(dto);
    }
   

    @PutMapping("/{id}")
    public VehicleResponseDto update(@PathVariable Long id,  @RequestBody UpdateVehicleDto dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    public VehicleResponseDto partialUpdate(
            @PathVariable Long id, 
            @RequestBody PartialUpdateVehicleDto dto) {
        return service.partialUpdate(id, dto);
    }

   @DeleteMapping("/{id}")
public String delete(@PathVariable Long id) {
    service.delete(id);
    return "Vehiculo eliminado correctamente";
}

}
