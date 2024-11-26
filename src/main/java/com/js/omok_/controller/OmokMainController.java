package com.js.omok_.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OmokMainController {

    @RequestMapping({"","/"})
    public String main(){
        return "omokMain";
    }
}
