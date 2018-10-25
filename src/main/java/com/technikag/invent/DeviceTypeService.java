package com.technikag.invent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class DeviceTypeService {

    private final DeviceTypeRepository deviceTypeRepository;

    @Autowired
    public DeviceTypeService(DeviceTypeRepository deviceTypeRepository) {
        this.deviceTypeRepository = deviceTypeRepository;
    }

    public Iterable<DeviceTypeDTO> findAll(){
        return StreamSupport.stream(deviceTypeRepository.findAll().spliterator(), false).map(DeviceTypeService::toDTO).collect(Collectors.toList());
    }

    public Optional<DeviceTypeDTO> findOne(String id){
        return this.deviceTypeRepository.findById(id).map(DeviceTypeService::toDTO);
    }

    public DeviceTypeDTO create(CreateDeviceTypeDTO l) {
        DeviceType deviceType = new DeviceType(l.getId(), l.getName());
        return toDTO(this.deviceTypeRepository.save(deviceType));
    }

    public DeviceTypeDTO update(String id, UpdateDeviceTypeDTO updateDeviceTypeDTO) throws DeviceTypeNotFoundException {
        Optional<DeviceType> l = this.deviceTypeRepository.findById(id);
        if (!l.isPresent()){
            throw new DeviceTypeNotFoundException();
        }
        l.get().setName(updateDeviceTypeDTO.getName());
        this.deviceTypeRepository.save(l.get());
        return toDTO(l.get());
    }

    public void delete(String id) throws DeviceTypeNotFoundException {
        Optional<DeviceType> l = this.deviceTypeRepository.findById(id);
        if (!l.isPresent()){
            throw new DeviceTypeNotFoundException();
        }
        this.deviceTypeRepository.delete(l.get());
    }

    static DeviceTypeDTO toDTO(DeviceType d) {
        return new DeviceTypeDTO(d.getName(), d.getId());
    }

    class DeviceTypeNotFoundException extends Exception{}
}
