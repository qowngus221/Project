package com.project.rentcar.controller;

import com.project.rentcar.domain.entity.Car;
import com.project.rentcar.domain.service.CarService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/cars")
public class CarController {

    @Autowired
    private CarService carService;  // 필드 주입

    // 차량 목록 페이지 (GET)
    @GetMapping
    public String getAllCars(Model model) {
        List<Car> cars = carService.getAllCars();
        model.addAttribute("cars", cars);
        return "cars/list";  // car/list.jsp로 반환
    }

    // 차량 상세 정보 (GET)
    @GetMapping("/details/{Carid}")
    public String getCarDetails(@PathVariable("Carid")Long Carid, Model model) {
        log.info("GET /cars/details/{Carid} : " + Carid );
        Optional<Car> car = carService.getCarById(Carid);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());
            return "cars/details";  // car/details.jsp로 반환
        } else {
            return "redirect:/cars";  // 차량을 찾을 수 없으면 목록 페이지로 리다이렉트
        }
    }

    // 차량 추가 페이지 (GET)
    @GetMapping("/add")
    public String showAddCarForm() {
        log.info("차량 추가 페이지");
        return "cars/add";  // car/add.jsp로 반환
    }

    // 차량 추가 (POST)
    @PostMapping("/add")
    public String addCar(@ModelAttribute Car car) {
        carService.addCar(car);
        return "redirect:/cars";
    }

    // 차량 수정 페이지 (GET)
    @GetMapping("/edit/{Carid}")
    public String showEditCarForm(@PathVariable("Carid") Long Carid, Model model) {
        log.info("GET /cars/edit/{Carid} : " + Carid);
        Optional<Car> car = carService.getCarById(Carid);
        if (car.isPresent()) {
            model.addAttribute("car", car.get());  // 차량 정보를 모델에 추가
            return "cars/edit";  // edit.jsp로 이동
        } else {
            return "redirect:/cars";  // 차량을 찾을 수 없으면 목록 페이지로
        }

    }

    // 차량 수정 (POST)
    @PostMapping("/edit/{Carid}")
    public String editCar(@PathVariable("Carid") Long Carid, @ModelAttribute Car Car) {
        log.info("POST /cars/edit/{Carid} : " + Carid + " updatedCar: " + Car);
        Car updatedCar = new Car();
        updatedCar.setCarid(Carid);  // id를 updatedCar에 설정
        updatedCar.setCarname(Car.getCarname());
        updatedCar.setModel(Car.getModel());
        updatedCar.setManufacturer(Car.getManufacturer());
        updatedCar.setYear(Car.getYear());// name을 updatedCar에 설정// price를 updatedCar에 설정
        updatedCar.setAvailable(true);  // available를 updatedCar에 설정
        updatedCar.setManufacturer(Car.getManufacturer());
        updatedCar.setLicensePlate(Car.getLicensePlate());  // licensePlate를 updatedCar에 설정
        // manufacturer를 updatedCar에 설정

        System.out.println("updatedCar: " + updatedCar);  // updatedCar 출력

        Car car = carService.updateCar(Carid, updatedCar);
        if (car != null) {
            return "redirect:/cars/edit" + Carid;  // 수정된 차량의 상세 페이지로 리다이렉트
        } else {
            return "redirect:/cars/edit";  // 수정 실패 시 목록 페이지로 리다이렉트
        }
    }


    // 차량 삭제 (POST)
    @PostMapping("/delete/{Carid}")
    public String deleteCar(@PathVariable("Carid") Long Carid) {
        boolean isDeleted = carService.deleteCar(Carid);
        if (isDeleted) {
            return "redirect:/cars";  // 삭제 후 차량 목록 페이지로 리다이렉트

        } else {
            // 실패 시 메시지 전달 및 목록 페이지로 리다이렉트
            return "redirect:/cars?error=deletion_failed";
        }
    }


    // 차량 검색 (GET)
    @GetMapping("/search")
    public String searchCars(@RequestParam(required = false) String name,
                             @RequestParam(required = false) String model,
                             @RequestParam(required = false) String manufacturer,
                             @RequestParam(required = false) Boolean available,
                             Model modelMap) {
        List<Car> cars = carService.getAllCars();

        if (name != null && !name.isEmpty()) {
            cars = carService.searchCarsByName(name);
        } else if (model != null && !model.isEmpty()) {
            cars = carService.searchCarsByModel(model);
        } else if (manufacturer != null && !manufacturer.isEmpty()) {
            cars = carService.searchCarsByManufacturer(manufacturer);
        } else if (available != null) {
            cars = carService.getAvailableCars(available);
        }

        modelMap.addAttribute("cars", cars);
        return "cars/searchResults";  // 검색 결과를 보여주는 JSP
    }

    // 차량 검색 (POST) - 예시 (POST 방식으로 검색 기능을 처리하는 경우)
    @PostMapping("/search")
    public String searchCarsPost(@RequestParam(required = false) String name,
                                 @RequestParam(required = false) String model,
                                 @RequestParam(required = false) String manufacturer,
                                 @RequestParam(required = false) Boolean available,
                                 Model modelMap) {
        List<Car> cars = carService.getAllCars();

        if (name != null && !name.isEmpty()) {
            cars = carService.searchCarsByName(name);
        } else if (model != null && !model.isEmpty()) {
            cars = carService.searchCarsByModel(model);
        } else if (manufacturer != null && !manufacturer.isEmpty()) {
            cars = carService.searchCarsByManufacturer(manufacturer);
        } else if (available != null) {
            cars = carService.getAvailableCars(available);
        }

        modelMap.addAttribute("cars", cars);
        return "cars/searchResults";  // 검색 결과를 보여주는 JSP
    }
}
