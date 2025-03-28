package com.example.demo;

import com.example.demo.controller.HelloController;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class HelloControllerTest {

    @Test
    void testSayHello() {
        HelloController controller = new HelloController();
        String response = controller.sayHello();
        assertEquals("Hello, CI/CD with Jenkins!", response);
    }
}