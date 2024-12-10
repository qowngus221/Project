package com.project.rentcar.domain.repository;

import com.project.rentcar.domain.entity.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    // Repository 라고 선언을 하면 spring이 우리가 만든 코드를 데이터베이스와 연결되는 코드라는걸 인식
    // 그 코드를 자동으로 관리

    // 즉 Repositroy로 붙은 클래스는 데이터베이스와 대화하는 역활을 한다고 생각하면된다.
    // 즉 crud를 할수 있게 도와준다.

    //JpaRepository는 우리가 매번 복잡하게 코드를 작성해야 하는 CRUD의 작업들을
    // 미리 준비해 준다. 그래서 JpaRepository를 상속 받으면 이 도구상자안에 있는 도구들ㅇ르 그냥 쓸수 잇게 됨
    // 예를 들면 Save()라는걸 쓰면 데이터를 저당할수있고 findbyID()라는 도구를 쓰면 데이터를 조회할수 있다 .
    // 즉 이런걸 이용하면 CRUD작업을 일일이 작성할 필요가 없이 자동으로 할 수 있다

    // JpaRepository는 findAll() 메소드를 기본적으로 제공
    // 따라서 추가로 구현할 필요 없이 바로 사용이 가능하다


    // 차량 이름으로 검색
    List<Car> findByCarnameContaining(String carname);

    // 차량 모델로 검색
    List<Car> findByModelContaining(String model);

    // 제조사로 검색
    List<Car> findByManufacturerContaining(String manufacturer);

    // 차량 번호판으로 검색
    Optional<Car> findByLicensePlate(String licensePlate);

    // 대여 가능 여부로 검색
    List<Car> findByAvailable(Boolean available);

    @Query("SELECT c FROM Car c WHERE c.carname = :carname")
    Optional<Car> findByCarname(@Param("carname") String carname); // 차량이름으로 rent 할 때 사용
}














