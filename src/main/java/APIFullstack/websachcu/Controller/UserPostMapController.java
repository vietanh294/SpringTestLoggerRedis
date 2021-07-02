package APIFullstack.websachcu.Controller;

import APIFullstack.websachcu.Controller.Request.UserPasswordInfoRequest;
import APIFullstack.websachcu.Controller.Response.UserPagePostedResponse;
import APIFullstack.websachcu.Entity.BookEntity;
import APIFullstack.websachcu.Entity.UserEntity;
import APIFullstack.websachcu.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import java.security.Principal;
import java.util.List;

@Controller
public class UserPostMapController {
    @Autowired
    UserService userService;
    @PostMapping(value = "/userPageInfo")
    public String putUserInfo(Model modelUserPageInfo, Principal principal,
                              @ModelAttribute("userPageInfoRequest") UserEntity userPageInfoRequest){
        String userName = principal.getName();
        String message = userService.putUserInfo(userName,
                userPageInfoRequest.getUserPhone(),userPageInfoRequest.getUserEmail());
        modelUserPageInfo.addAttribute("messagePageInfo" , message);
//        System.out.println(message);
        if (message != "Lưu thành công!"){
            modelUserPageInfo.addAttribute("userPageInfoRequest",userPageInfoRequest);
            modelUserPageInfo.addAttribute("userPasswordInfoRequest", new UserPasswordInfoRequest());
            return "userPageInfo";
        }
        return "redirect:/login";
    }
    @GetMapping(value = "/posted")
    public String userPagePosted2(Model modelUserPageInfo3){
        return "redirect:/userPage/posted";
    }
    @GetMapping(value = "/collection")
    public String userPageCollection2(Model modelUserPageInf4){
        return "redirect:/userPage/collection";
    }
//    public String putUserPosted(Model modelUserPageInfo, Principal principal,
//                                @ModelAttribute("userPagePortedRequest") BookEntity userPagePortedRequest,
//                                @ModelAttribute("userPagePostedResponseList") List<UserPagePostedResponse> userPagePostedResponseList){
//        String userName = principal.getName();
////        String message = userService.putUserInfo(userName,
////                userPageInfoRequest.getUserPhone(),userPageInfoRequest.getUserEmail());
////        modelUserPageInfo.addAttribute("message" , message);
////        if (message != "Lưu thành công!"){
////            return "redirect:/userPage";
////        }
//        return "redirect:/login";
//    }
}
