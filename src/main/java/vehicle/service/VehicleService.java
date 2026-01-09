package vehicle.service;

import java.util.List;
import vehicle.dto.CreateVehicleDto;
import vehicle.dto.VehicleResponseDto;
import vehicle.dto.UpdateVehicleDto;
import vehicle.dto.PartialUpdateVehicleDto;

public interface VehicleService {

     List<VehicleResponseDto> findAll();

    VehicleResponseDto findOne(Long id);

   VehicleResponseDto create(CreateVehicleDto dto);

   VehicleResponseDto update(Long id, UpdateVehicleDto dto);

    VehicleResponseDto partialUpdate(Long id, PartialUpdateVehicleDto dto);

    void delete(Long id);
}
