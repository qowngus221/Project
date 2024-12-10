package com.project.rentcar.controller;

import com.project.rentcar.domain.dto.RentDto;
import com.project.rentcar.domain.service.RentServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/rent")
public class RentController {
    @Autowired
    private RentServiceImpl rentServiceImpl;

    @GetMapping("/add")
    public void getadd(){
        log.info("GET /rent/add");
    }
    @PostMapping("/add")
    public void postadd(RentDto rentDto){
        log.info("POST /rent/add");
        rentServiceImpl.rentcar(rentDto);
    }

    @GetMapping("/dayUpdate")
    public void getday(){
        log.info("GET /rent/dayUpdate");
    }
    @PostMapping("/dayUpdate")
    public void postday(@RequestParam("rentId") Long rentId){
        log.info("POST /rent/dayUpdate");
        rentServiceImpl.updateDay(rentId);

    }

    @GetMapping("/carReturn")
    public void getReturn(){
        log.info("GET /rent/carReturn");
    }
    @PostMapping("/carReturn")
    public void postReturn(@RequestParam("rentId") Long rentId){
        log.info("POST /rent/carReturn");
        rentServiceImpl.deleteCar(rentId);
    }

    @GetMapping("/carList")
    public String getlist(@RequestParam(value = "memberId", required = false) String memberId , Model model){
        log.info("GET /rent/carList");
        List<Object[]> list =rentServiceImpl.rentList(memberId);
        model.addAttribute("list", list);
        return "rent/carList";
    }
    @PostMapping("/carList")
    public String postlist(@RequestParam("memberId") String memberId){
        log.info("POST /rent/carList");
        return "redirect:/rent/carList?memberId=" + memberId;
    }

}
