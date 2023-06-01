package com.example.OnlineSales.ai.Task2;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import java.util.ArrayList;
import java.util.Scanner;

@RestController
@RequestMapping("expression")
public class APIController {

//    When not taking the input from user
    String[] expressions = {
            "2 * 4 * 4",
            "5 / (7 - 5)",
            "sqrt(pow(5, 2) - pow(4, 2))",
            "sqrt(pow(-3, 2) - pow(4, 2))",
            "end"
    };

/*   To take the input from user using scanner
    ArrayList<String> expressions = new ArrayList<>(); */

    @PostMapping("evaluate")
    public ResponseEntity<String> evaluate() {

        StringBuilder result = new StringBuilder();

//        Scanner input = new Scanner(System.in);
        /* while(true) {

            The while loop will help in taking input and will make the program execute only when the end is provided.

            String expression = input.nextLine();
            expressions.add(expression);
            if(expression.equals("end")) {
                break;
            }
        } */

        for(String expression : expressions) {

            if(expression.equals("end")) {
                break;
            }

            // The program uses the Math.js API (https://mathjs.org/) to evaluate mathematical expressions
            String url = "https://api.mathjs.org/v4/?expr=" + expression;


            RestTemplate restTemplate = new RestTemplate();
            String apiResult = restTemplate.getForObject(url, String.class);
            result.append(expression).append(" => ").append(apiResult).append(", ");
            System.out.println(expression + " => " + apiResult);
        }
        return new ResponseEntity<>(result.toString(), HttpStatus.CREATED);
    }
}
