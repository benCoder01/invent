package com.technikag.invent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class DeviceService {

    private final DeviceRepository deviceRepo;
    private final DeviceTypeRepository deviceTypeRepo;
    private final LocationRepository locationRepo;

    @Autowired
    public DeviceService(DeviceRepository deviceRepo, DeviceTypeRepository deviceTypeRepo, LocationRepository locationRepo) {
        this.deviceRepo = deviceRepo;
        this.deviceTypeRepo = deviceTypeRepo;
        this.locationRepo= locationRepo;
    }

    public Iterable<DeviceDTO> findAll() {
        Iterable<Device> devices = this.deviceRepo.findAll();

        return StreamSupport
                .stream(devices.spliterator(), true)
                .map(DeviceService::toDTO)
                .collect(Collectors.toList());
    }

    public Optional<DeviceDTO> findOne(int id){
        return this.deviceRepo.findById(id).map(DeviceService::toDTO);
    }

    public void delete(int id) throws DeviceNotFoundException {
        Optional<Device> device = this.deviceRepo.findById(id);
        if (!device.isPresent()) {
            throw new DeviceNotFoundException();
        }
        this.deviceRepo.delete(device.get());
    }

    public DeviceDTO create(CreateDeviceDTO deviceDTO) throws DeviceTypeNotFoundException, LocationNotFoundException{
        Optional<DeviceType> deviceType = this.deviceTypeRepo.findById(deviceDTO.getDeviceType());
        if (!deviceType.isPresent()) {
            throw new DeviceTypeNotFoundException();
        }

        Optional<Location> location = this.locationRepo.findById(deviceDTO.getLocation());
        if(!location.isPresent()){
            throw new LocationNotFoundException();
        }

        Device d = new Device(deviceType.get(), deviceDTO.getName(), location.get(),deviceDTO.getComment());
        return toDTO(this.deviceRepo.save(d));
    }

    public DeviceDTO edit(int id, UpdateDeviceDTO u) throws DeviceNotFoundException, LocationNotFoundException {
        Optional<Device> device = this.deviceRepo.findById(id);
        if (!device.isPresent()) {
            throw new DeviceNotFoundException();
        }

        if (u.getComment().isPresent()) {
            device.get().setComment(u.getComment().get());
        }

        if (u.getLocation().isPresent()) {
            Optional<Location> location = this.locationRepo.findById(u.getLocation().get());
            if(!location.isPresent()){
                throw new LocationNotFoundException();
            }
            device.get().setLocation(location.get());
        }

        if (u.getName().isPresent()) {
            device.get().setName(u.getName().get());
        }

        return toDTO(this.deviceRepo.save(device.get()));
    }

    static DeviceDTO toDTO(Device d) {
        return new DeviceDTO(d.getId(), d.getName(), LocationService.toDTO(d.getLocation()), d.getComment(), DeviceTypeService.toDTO(d.getType()));
    }

    class DeviceNotFoundException extends Exception {}
    class DeviceTypeNotFoundException extends Exception {}
    class LocationNotFoundException extends Exception {}

}
