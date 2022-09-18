package com.example.jubging.Controller;

import com.example.jubging.Response.SingleResult;
import com.example.jubging.Service.response.ResponseService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course")
public class RecommendCourseController {
    private final ResponseService responseService;


//    @PostMapping("/savaCourse")
//    public SingleResult<?> saveCourse(){
//
//        return responseService.getSingleResult();
//    }
}
