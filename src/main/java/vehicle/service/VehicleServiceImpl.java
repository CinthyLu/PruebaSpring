 package vehicle.service;
import java.util.List;
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
    public ProductResponseDto update(Long id, UpdateVehicleDto dto) {
        ProductEntity entity = repository.findById(id)
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

        if (dto.getName() != null) entity.setName(dto.getName());
        if (dto.getPrice() != null) entity.setPrice(dto.getPrice());
        if (dto.getStock() != null) entity.setStock(dto.getStock());

        repository.save(entity);
        return VehicleMapper.toDto(entity);
    }

    @Override
    public void delete(Long id) {
        ProductEntity entity = repository.findById(id)
                .orElseThrow(() ->
                        new NotFoundException("Producto no encontrado con ID: " + id)
                );

        repository.delete(entity);
    }

    private void validateUniqueName(Integer id, String name) {
        
       userRepository.findByName(name).ifPresent(existing -> {
            if (id == null || !existing.getId() != id.LongValue()) {
                throw new ConflictException(
                    "Ya existe un producto con el nombre: " + name
                );
            }
        });
    }

    public ProductResponseDto secureUpdate(int id, SecureUpdateProductDto dto) {
       
        ProductEntity entity = repository.findById((long) id)
                .orElseThrow(() -> new BusinessException("Producto no encontrado con ID: " + id));

    }
}