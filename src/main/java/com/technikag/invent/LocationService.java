package com.technikag.invent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    @Autowired
    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public Iterable<LocationDTO> findAll(){
        return StreamSupport.stream(locationRepository.findAll().spliterator(), false).map(LocationService::toDTO).collect(Collectors.toList());
    }

    public Optional<LocationDTO> findOne(int id){
        return this.locationRepository.findById(id).map(LocationService::toDTO);
    }

    public LocationDTO create(CreateLocationDTO l) {
        Location location = new Location(l.getName(), Optional.empty());
        return toDTO(this.locationRepository.save(location));
    }

    public LocationDTO update(int id, UpdateLocationDTO updateLocationDTO) throws LocationNotFoundException{
        Optional<Location> l = this.locationRepository.findById(id);
        if (!l.isPresent()){
            throw new LocationNotFoundException();
        }
        l.get().setName(updateLocationDTO.getName());
        this.locationRepository.save(l.get());
        return toDTO(l.get());
    }

    public void delete(int id) throws LocationNotFoundException {
        Optional<Location> l = this.locationRepository.findById(id);
        if (!l.isPresent()){
            throw new LocationNotFoundException();
        }
        this.locationRepository.delete(l.get());
    }

    static LocationDTO toDTO(Location l) {
        Optional<List<DeviceDTO>> packingList = l
                                                .getPackinglist()
                                                .map(list -> list.stream()
                                                            .map(DeviceService::toDTO)
                                                            .collect(Collectors.toList())
                                                );
        return new LocationDTO(l.getId(), l.getName(), packingList);
    }

    class LocationNotFoundException extends Exception{}
}
