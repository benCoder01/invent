package com.technikag.invent;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTypeRepository extends CrudRepository<DeviceType, String> {
}
