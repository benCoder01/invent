package com.technikag.invent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("deviceTypes")
@RequestMapping("/deviceTypes")
@CrossOrigin
public class DeviceTypeController {

    private final DeviceTypeService deviceTypeService;

    @Autowired
    public DeviceTypeController(DeviceTypeService deviceTypeService) {
        this.deviceTypeService = deviceTypeService;
    }

    @GetMapping
    public Iterable<DeviceTypeDTO> findAll(){
        return this.deviceTypeService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DeviceTypeDTO> findOne(@PathVariable("id") String id){
        return this.deviceTypeService.findOne(id);
    }

    @PostMapping
    public ResponseEntity<DeviceTypeDTO> create(@RequestBody CreateDeviceTypeDTO deviceTypeDTO){
        return new ResponseEntity<>(this.deviceTypeService.create(deviceTypeDTO), HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeviceTypeDTO> update(@PathVariable("id") String id, @RequestBody UpdateDeviceTypeDTO updateLocationDTO){
        try {
            DeviceTypeDTO deviceType = this.deviceTypeService.update(id, updateLocationDTO);
            return new ResponseEntity<>(deviceType, HttpStatus.OK);
        } catch (DeviceTypeService.DeviceTypeNotFoundException e) {
            return new ResponseEntity("Location not found",HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") String id){
        try {
            this.deviceTypeService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DeviceTypeService.DeviceTypeNotFoundException e) {
            return new ResponseEntity("Location not found",HttpStatus.NOT_FOUND);
        }
    }

}
