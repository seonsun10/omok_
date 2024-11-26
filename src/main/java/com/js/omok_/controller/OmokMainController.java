package com.js.omok_.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class OmokMainController {

    @RequestMapping({"","/"})
    public String main(){
        return "omokMain";
    }

    @RequestMapping("/gameMain")
    public String gameMain(){
        return "gameMain";
    }
}
