package io.github.dgahn.jpashop;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HelloController {

  @GetMapping("/hello")
  public String hello(Model model) { // Model을 통해서 뷰에 값을 전달할 수 있다.
    model.addAttribute("data", "hello!!!");
    return "hello"; // 관례상 화면의 이름을 쓴다.
  }

}
