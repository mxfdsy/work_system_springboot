package com.coding.commons.Myvalidate.Controller;

import com.coding.commons.Myvalidate.entity.DemoModel;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * @author 小武 on 2018/7/27.
 */
@RequestMapping("/test")
@Controller
public class demo2 {
    @ResponseBody
    @RequestMapping("/demo2")
    public void demo2(@RequestBody @Valid DemoModel demo, BindingResult result){
        if(result.hasErrors()){
            for (ObjectError error : result.getAllErrors()) {
                System.out.println(error.getDefaultMessage());
            }
        }
    }
}
