package com.nagarro.assignment1;

import com.nagarro.assignment1.controller.backend.BackendInterface;
import com.nagarro.assignment1.controller.backend.implementaion.Backend1;
import com.nagarro.assignment1.controller.backend.implementaion.Backend2;
import com.nagarro.assignment1.controller.backend.implementaion.Backend3;
import com.nagarro.assignment1.repository.TransactionRepository;

public class BackendFactory {

    
     TransactionRepository transRepository;

     public BackendFactory(TransactionRepository transRepository){

        this.transRepository = transRepository;
     }

    public BackendInterface getFactory(String type){

       

        switch (type) {
            case "success":
                return new Backend1(transRepository);
             
            case "failure":
                return new Backend2(transRepository);
            
            case "pending":
                return new Backend3(transRepository);

        }

        return null;

    }
    
}
