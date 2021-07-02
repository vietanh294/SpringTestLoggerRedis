package APIFullstack.websachcu.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;

@Controller
public class SecurityController{

    @GetMapping(value = {"/","/welcome"})
    public String welcome(Model model){
//        model.addAttribute("title", "Xin chao");
//        model.addAttribute("message", "Day la welcome Page");
        return "redirect:/homePage";
    }
    @GetMapping("/userInfo")
    public String userInfo(Model model, Principal principal){
        String userName =principal.getName();

        User loginUser=(User) ((Authentication) principal).getPrincipal();
        String userInfo = loginUser.toString();
        model.addAttribute("userInfo",userInfo);
        return "userInfo";
    }
    @GetMapping("/admin")
    public String adminInfo(Model model, Principal principal){
        String userName =principal.getName();

        User loginUser=(User) ((Authentication) principal).getPrincipal();
        String userInfo = loginUser.toString();
        model.addAttribute("userInfo",userInfo);
        return "admin";
    }
    @GetMapping(value = "/login")
    public String loginPage(Model model,
                            @ModelAttribute("message")String message){
        model.addAttribute("messageLogin",message);
        return "login";
    }
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logoutSuccessfulPage(Model model){
        model.addAttribute("title","Logout");
        return "logout";
    }
    @RequestMapping(value = "/403", method = RequestMethod.GET)
    public String donothavePermissionPage(Model model,Principal principal){
        if (principal != null){
            User loginedUser = (User) ((Authentication) principal).getPrincipal();
            String userInfo =loginedUser.toString();
            model.addAttribute("userInfo",userInfo);
            String message = "Hi " + principal.getName()  + "<br> You do not have permission to access the page!";
            model.addAttribute("message",message);
        }
        return "403";
    }
    @RequestMapping(value = "/logoutSuccessful", method = RequestMethod.GET)
    public String logoutSuccessfulPage2(Model model){
        model.addAttribute("title","Logout");
        return "redirect:/homePage";
    }

}
