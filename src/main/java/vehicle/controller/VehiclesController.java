package vehicle.controller;
import java.util.List;

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
    public VehicleResponseDto create(@Valid @RequestBody CreateVehicleDto dto) {
        return service.create(dto);
    }

    @PostMapping("/validate-name")
    public ResponseEntity<Boolean> validateModel(@Valid @RequestBody ValidateVehicleModelDto dto) {
       service.validateModel(dto.id, dto.model);
        return ResponseEntity.ok(true);
      
    }    

    @PutMapping("/{id}")
    public VehicleResponseDto update(@PathVariable Long id, @Valid @RequestBody UpdateVehicleDto dto) {
        return service.update(id, dto);
    }

    @PatchMapping("/{id}")
    public VehicleResponseDto partialUpdate(
            @PathVariable Long id, @Valid
            @RequestBody PartialUpdateVehicleDto dto) {
        return service.partialUpdate(id, dto);
    }

   @DeleteMapping("/{id}")
public String delete(@PathVariable Long id) {
    service.delete(id);
    return "Vehiculo eliminado correctamente";
}

}
