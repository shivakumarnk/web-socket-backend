package com.socketdemo;

import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import java.lang.reflect.Method;

@Controller
public class SocketController {

    @MessageMapping("/hello")
    @SendTo({"/topic/greetings"})
    public SocketResponse greeting(SocketRequest message) throws Exception {
        Thread.sleep(1000); // simulated delay
        System.out.println("Handshake for rect socket: " + message);
        return new SocketResponse("Hello, " + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    @MessageMapping("/hello-test")
    @SendTo({"/topic/greetings-test"})
    public SocketResponse test(SocketRequest message) {
        System.out.println("Handshake for rect socket: " + message.getName());
        return new SocketResponse("Hello -I am From Spring Boot, \nYour Text is : \n" + HtmlUtils.htmlEscape(message.getName()) + "!");
    }

    private static String[] getOutboundBindingTargetNames(Method method) {
        SendTo sendTo = AnnotationUtils.findAnnotation(method, SendTo.class);
        sendTo.toString().concat("aaaaaaaaaa");
        return null;
    }

}