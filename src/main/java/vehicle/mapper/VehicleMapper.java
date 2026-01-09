package vehicle.mapper;

import vehicle.dtos.VehicleResponseDto;
import vehicle.entity.VehicleEntity;

public class VehicleMapper {

    public static VehicleResponseDto toDto(VehicleEntity entity) {
        vehicle.dto.VehicleResponseDto dto = new VehicleResponseDto();
        dto.setId(entity.getId());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setPrice(entity.getPrice());
        dto.setStock(entity.getStock());
        return dto;
    }
}
