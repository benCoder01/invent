package com.technikag.invent;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController("devices")
@RequestMapping("/devices")
@CrossOrigin
public class DeviceController {

    private final DeviceService deviceService;

    @Autowired
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping
    public Iterable<DeviceDTO> findAll() {
        return this.deviceService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<DeviceDTO> findOne(@PathVariable("id") int id) {
        return this.deviceService.findOne(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") int id) {
        try {
            this.deviceService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (DeviceService.DeviceNotFoundException e) {
            return new ResponseEntity<>("Device Not found", HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<DeviceDTO> createDevice(@RequestBody CreateDeviceDTO device) {
        try {
            DeviceDTO created = this.deviceService.create(device);
            return new ResponseEntity<>(created, HttpStatus.OK);
        } catch (DeviceService.DeviceTypeNotFoundException e) {
            return new ResponseEntity("DeviceType Invalid", HttpStatus.BAD_REQUEST);
        } catch (DeviceService.LocationNotFoundException e) {
            return new ResponseEntity("Location Invalid", HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/{id}")
    public ResponseEntity<DeviceDTO> updateDevice(@RequestBody UpdateDeviceDTO device, @PathVariable("id") int id) {
        try {
            DeviceDTO editedDevice= this.deviceService.edit(id, device);
            return new ResponseEntity<DeviceDTO>(editedDevice,HttpStatus.OK);
        } catch (DeviceService.DeviceNotFoundException e) {
            return new ResponseEntity("Device not found", HttpStatus.NOT_FOUND);
        } catch (DeviceService.LocationNotFoundException e) {
            return new ResponseEntity("Location not found", HttpStatus.BAD_REQUEST);
        }
    }

}
