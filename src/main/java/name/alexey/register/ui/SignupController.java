package name.alexey.register.ui;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/")
public class SignupController {

    private static final String SIGNUP_VIEW_NAME = "index";

    @RequestMapping(value={"/", "index.html"})
	public String signup(Model model) {
        return SIGNUP_VIEW_NAME;
	}
}
