package com.project.rentcar.domain.service;

import com.project.rentcar.domain.entity.Car;
import com.project.rentcar.domain.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarService {

    @Autowired
    public CarRepository carRepository;


    // 차량 추가
    public Car addCar(Car car) {
        return carRepository.save(car);
    }

    // 차량 ID로 조회
    public Optional<Car> getCarById(Long Carid) {
        return carRepository.findById(Carid);
    }

    // 차량 번호판으로 조회
    public Optional<Car> getCarByLicensePlate(String licensePlate) {
        return carRepository.findByLicensePlate(licensePlate);
    }

    // 차량 목록 조회
    public List<Car> getAllCars() {
        return carRepository.findAll();
    }

    // 차량 이름으로 검색
    public List<Car> searchCarsByName(String carname) {
        return carRepository.findByCarnameContaining(carname);
    }

    // 차량 모델로 검색
    public List<Car> searchCarsByModel(String model) {
        return carRepository.findByModelContaining(model);
    }

    // 차량 제조사로 검색
    public List<Car> searchCarsByManufacturer(String manufacturer) {
        return carRepository.findByManufacturerContaining(manufacturer);
    }

    // 대여 가능 여부로 차량 검색
    public List<Car> getAvailableCars(Boolean available) {
        return carRepository.findByAvailable(available);
    }

    // 차량 수정
    public Car updateCar(Long Carid, Car updatedCar) {
        if (carRepository.existsById(Carid)) {
            updatedCar.setCarid(Carid);
            return carRepository.save(updatedCar);
        }
        return null;  // 차량이 없으면 null 반환
    }

    // 차량 삭제
    public boolean deleteCar(Long Carid) {
        if (carRepository.existsById(Carid)) {
            carRepository.deleteById(Carid);
            return true;
        }
        return false;  // 차량이 없으면 false 반환
    }

}
