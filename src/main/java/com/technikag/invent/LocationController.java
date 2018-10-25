package com.technikag.invent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("location")
@RequestMapping("/locations")
public class LocationController {

    private final LocationService locationService;

    @Autowired
    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public Iterable<LocationDTO> findAll(){
        return locationService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<LocationDTO> findOne(@PathVariable("id") int id){
        return this.locationService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<LocationDTO> create(@RequestBody CreateLocationDTO locationDTO) {
        return new ResponseEntity<>(this.locationService.create(locationDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LocationDTO> update(@PathVariable("id") int id, @RequestBody UpdateLocationDTO updateLocationDTO){
        try {
            LocationDTO location = locationService.update(id, updateLocationDTO);
            return new ResponseEntity<>(location, HttpStatus.OK);
        } catch (LocationService.LocationNotFoundException e) {
            return new ResponseEntity("Location not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id){
        try {
            this.locationService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (LocationService.LocationNotFoundException e) {
            return new ResponseEntity("Location not found",HttpStatus.NOT_FOUND);
        }
    }

}
