package md.vladdubceac.springcourse.controllers;

import md.vladdubceac.springcourse.CalculatorOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
@RequestMapping("/first")
public class FirstController {

    @GetMapping("/hello")
    public String helloPage(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "surname", required = false) String surname,
                            Model model) {
        model.addAttribute("name", name);
        model.addAttribute("surname", surname);
        model.addAttribute("message", "Hello, " + Optional.ofNullable(name).orElse("") + " " + Optional.ofNullable(surname).orElse(""));

        return "first/hello";
    }

    @GetMapping("/goodbye")
    public String goodByePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculator(@RequestParam(value = "a", required = false) String a,
                             @RequestParam(value = "b", required = false) String b,
                             @RequestParam(value = "action", required = false) String action,
                             Model model) {
        boolean hasNullRequestParameters = false;
        StringBuilder message = new StringBuilder();
        if (a == null && b == null) {
            hasNullRequestParameters = true;
            message.append("Please give valid numbers for URL parameters 'a' and 'b' !").append(System.lineSeparator());
        }
        if (action == null) {
            hasNullRequestParameters = true;
            message.append("Please give a valid value for URL parameter 'action' . Choose between 'addition', 'subtraction', 'multiplication', 'division' ! ");
        }

        if(!hasNullRequestParameters) {
            CalculatorOperations operation = CalculatorOperations.ADDITION;
            int firstNumber = 0, secondNumber = 0;
            try {
                firstNumber = Integer.parseInt(a);
                secondNumber = Integer.parseInt(b);
                operation = CalculatorOperations.valueOf(action.toUpperCase());
                double result = 0.0;
                switch (operation) {
                    case ADDITION:
                        result = firstNumber + secondNumber;
                        break;
                    case DIVISION:
                        if (secondNumber == 0) {
                            throw new ArithmeticException("Division by zero");
                        }
                        result = (double) firstNumber / secondNumber;
                        break;
                    case SUBTRACTION:
                        result = firstNumber - secondNumber;
                        break;
                    case MULTIPLICATION:
                        result = firstNumber * secondNumber;
                        break;
                }
                message.append(firstNumber).append(operation.getOperationSign()).append(secondNumber)
                        .append(" = ").append(result);
            } catch (NumberFormatException e) {
                message.append("Please enter valid integer numbers for 'number1' and for 'number2'");
            } catch (IllegalArgumentException e) {
                message.append("Operation not found for action '").append(action).append("' ! Choose between 'addition', 'subtraction', 'multiplication', 'division' !");
            } catch (ArithmeticException e) {
                message.append("Operation not allowed : ").append(firstNumber).append(operation.getOperationSign()).append(secondNumber);
            }
        }

        model.addAttribute("message", message.toString());
        return "/first/calculator_result";
    }
}
