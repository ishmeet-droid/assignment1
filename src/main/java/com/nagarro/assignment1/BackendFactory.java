package com.nagarro.assignment1;

import org.springframework.web.reactive.function.client.WebClient;

import com.nagarro.assignment1.controller.backend.BackendInterface;
import com.nagarro.assignment1.controller.backend.implementaion.Backend1;
import com.nagarro.assignment1.controller.backend.implementaion.Backend2;
import com.nagarro.assignment1.controller.backend.implementaion.Backend3;


public class BackendFactory {

    
  
     WebClient webClient;

     public BackendFactory( WebClient webClient){

        this.webClient = webClient;
     }

    public BackendInterface getFactory(String type){

       

        switch (type) {
            case "success":
                return new Backend1(webClient);
             
            case "failure":
                return new Backend2(webClient);
            
            case "pending":
                return new Backend3(webClient);

        }

        return null;

    }
    
}
