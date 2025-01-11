package com.sky.system.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class WebSocketController {
    private static final Logger log = LoggerFactory.getLogger(WebSocketController.class);

    @RequestMapping("/websocket/{name}")
        public String webSocket(@PathVariable String name, Model model) {
            log.info("进入webSocket方法");
            try {
                log.info("跳转到websocket的页面上");
                model.addAttribute("username", name);
                return "websocket";
            } catch (Exception e) {
                log.info("跳转到websocket的页面上发生异常，异常信息是：" + e.getMessage());
                return "error";
            }
        }
    }

