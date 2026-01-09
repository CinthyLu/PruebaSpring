 package vehicle.service;
import java.util.List;
import vehicle.dto.CreateVehicleDto;
import vehicle.dto.UpdateVehicleDto;
import vehicle.dto.PartialUpdateVehicleDto;
import vehicle.repository.VehicleRepository;
import vehicle.entity.VehicleEntity;
import vehicle.mapper.VehicleMapper;


import org.springframework.stereotype.Service;

import vehicle.dto.VehicleResponseDto;

@Service
public class VehicleServiceImpl implements VehicleService {

    private final VehicleRepository repository;

    public VehicleServiceImpl(VehicleRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<VehicleResponseDto> findAll() {
        return repository.findAll()
                .stream()
                .map(VehicleMapper::toDto)
                .toList();
    }

    @Override
    public VehicleResponseDto findOne(Long id) {
        VehicleEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        return VehicleMapper.toDto(entity);
    }

    @Override
    public VehicleResponseDto create(CreateVehicletDto dto) {

        
        if (repository.existsByModel(dto.getModel())) {
        throw new ConflictException(
            "Ya existe un producto con ese modelo: " + dto.getModel()
        );
    }

        VehicleEntity entity = new VehicleEntity();
        entity.setBrand(dto.getBrand());
         entity.setModel(dto.getModel());
        entity.setPrice(dto.getPrice());
        entity.setStock(0);
        entity.setDescription("Sin descripcion");

        repository.save(entity);
        return VehicleMapper.toDto(entity);
    }

    @Override
    public VehicleResponseDto update(Long id, UpdateVehicleDto dto) {
        VehicleEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

         entity.setBrand(dto.getBrand());
         entity.setModel(dto.getModel());
        entity.setPrice(dto.getPrice());
        entity.setStock(0);

        repository.save(entity);
        return VehicleMapper.toDto(entity);
    }

    @Override
    public VehicleResponseDto partialUpdate(Long id, PartialUpdateVehicleDto dto) {
        VehicleEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        if (dto.getModel() != null) entity.setModel(dto.getModel());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getStock() != null) entity.setStock(dto.getStock());

        repository.save(entity);
        return VehicleMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        VehicleEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        repository.delete(entity);
    }




    }
