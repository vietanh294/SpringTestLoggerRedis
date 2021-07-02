package APIFullstack.websachcu.Controller;
import APIFullstack.websachcu.Controller.Request.HomeRequest;

import APIFullstack.websachcu.Entity.BookEntity;
import APIFullstack.websachcu.Entity.CategoryEntity;
import APIFullstack.websachcu.Repository.BookRepository;
import APIFullstack.websachcu.Repository.CategoryRepository;

import APIFullstack.websachcu.Service.HomeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value = "/homePage")
public class HomeController {

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    BookRepository bookRepository;
    @Autowired
    CategoryRepository categoryRepository;
    @Autowired
    HomeService homeService;

    @GetMapping
    public String homePage(Model modelHomepage){
//        List<BookEntity>  homeBookForms = bookRepository.findAll();
        logger.info("Received Request GETMAPPING username {}, password {}");
        List<CategoryEntity> cateItems = categoryRepository.findAll();
        HomeRequest homeRequest = new HomeRequest();
        List<BookEntity>  homeBookForms = homeService.runHomeService(homeRequest);
        homeRequest =homeService.countTotalPages(homeRequest);
        List<Integer> pageNums = homeService.getPageNums(homeRequest.getPageNumber(),homeRequest.getTotalPages());
        modelHomepage.addAttribute("homeRequest",homeRequest);
        modelHomepage.addAttribute("homeBookForms", homeBookForms);
        modelHomepage.addAttribute("categoyItems",cateItems);
//        modelHomepage.addAttribute("userFormSignedIn1",userFormSignedIn);
        modelHomepage.addAttribute("pageNums",pageNums);

        return "homePage";
    }

    @PostMapping
    public String homePage(Model modelHomepage,
                           @ModelAttribute("homeRequest") HomeRequest homeRequest2){
        logger.info("Received Request  POST username {}, password {}", homeRequest2.getPageNumber(),homeRequest2.getClass());
        List<CategoryEntity> cateItems = categoryRepository.findAll();
        homeRequest2 =homeService.countTotalPages(homeRequest2);
        if (homeRequest2.getPageNumber()>homeRequest2.getTotalPages()){
            homeRequest2.setPageNumber(1);
        }
        List<BookEntity> homeBookForms = homeService.runHomeService(homeRequest2);

        modelHomepage.addAttribute("homeRequest",homeRequest2);
        modelHomepage.addAttribute("homeBookForms", homeBookForms);
        modelHomepage.addAttribute("categoyItems",cateItems);
//        modelHomepage.addAttribute("userFormSignedIn1",userFormSignedIn);
        List<Integer> pageNums = homeService.getPageNums(homeRequest2.getPageNumber(),homeRequest2.getTotalPages());
        modelHomepage.addAttribute("pageNums",pageNums);
        return "homePage";
    }
}

