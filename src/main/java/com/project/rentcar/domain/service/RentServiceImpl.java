package com.project.rentcar.domain.service;

import com.project.rentcar.domain.dto.RentDto;
import com.project.rentcar.domain.entity.Car;
import com.project.rentcar.domain.entity.Rent;
import com.project.rentcar.domain.entity.User;
import com.project.rentcar.domain.repository.CarRepository;
import com.project.rentcar.domain.repository.RentRepository;
import com.project.rentcar.domain.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Slf4j
public class RentServiceImpl {

    @Autowired
    private RentRepository rentRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CarRepository carRepository;


    public void rentcar(RentDto rentDto) {
        // username을 통해 User 객체 조회
        User user = userRepository.findByMemberId(rentDto.getMemberId())
                .orElseThrow(() -> new RuntimeException("username not found"));

        // Carname를 통해 Car 객체 조회
        Car car = carRepository.findByCarname(rentDto.getCarname())
                .orElseThrow(() -> new RuntimeException("carname not found"));

        // 대여 객체 생성 및 저장
        Rent rent = Rent.builder()
                .user(user)
                .car(car)
                .rentDate(LocalDate.now())
                .returnDate(LocalDate.now().plusDays(1))
                .build();

        rentRepository.save(rent);
    }
    public boolean updateDay(Long rentId){

        Rent rent =rentRepository.findById(rentId)
                .orElseThrow(() -> new EntityNotFoundException("rent not found"));

        LocalDate date = rent.getReturnDate().plusDays(1);
        rentRepository.updateReturnDate(date, rent.getRentId());
        return true;
    }

    public boolean deleteCar(Long rentId){

        rentRepository.deleteCar(rentId);
        return true;
    }

    public List<Object[]> rentList(String memberId){
       return rentRepository.findByUserAndCar(memberId);
    }


}

