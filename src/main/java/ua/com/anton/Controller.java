package ua.com.anton;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@org.springframework.stereotype.Controller
public class Controller {
    @GetMapping("/hello")
    public String getHello(HttpServletRequest request, Model model){
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        //System.out.println("Hello, "+ name+" "+ surname);
        model.addAttribute("messege","Hello, " + name+" "+ surname );
        return "hello_world.html";
    }
    @GetMapping("/check")
    public String check(){
        return "Check.html";
    }
    @GetMapping("/calculator")
    public String calculator(@RequestParam("a") int a, @RequestParam("b") int b,
                             @RequestParam("action") String action, Model model) {

        double result;

        switch (action) {
            case "multiplication":
                result = a * b;
                break;
            case "division":
                result = a / (double) b;
                break;
            case "subtraction":
                result = a - b;
                break;
            case "addition":
                result = a + b;
                break;
            default:
                result = 0;
                break;
        }

        model.addAttribute("result", result);

        return "Calculator.html";
    }
//    @GetMapping("/calculator")
//    public String calculator(HttpServletRequest request, Model model,
//                             @RequestParam(value = "a", required = false ) int a,
//                             @RequestParam(value = "b", required = false ) int b,
//                             @RequestParam(value = "action", required = false ) String action){
//        if(action =="add"){
//            model.addAttribute("result",Double.valueOf(a + b).toString());
//        }
//        else if(action =="minus"){
//            model.addAttribute("result",Double.valueOf(a - b).toString());
//            //return Double.valueOf(a -b);
//        }
//        else if(action =="multiply"){
//            model.addAttribute("result",Double.valueOf(a * b).toString());
//        }
//        else if(action =="devide"){
//            model.addAttribute("result",Double.valueOf(a / b).toString());
//        }
//        else
//        model.addAttribute("messege","error, wrong action is chosen");
//        return "Calculator.html";
//    }

}
