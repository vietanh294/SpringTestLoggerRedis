package APIFullstack.websachcu.Service;

import APIFullstack.websachcu.Controller.Request.PostBookRequest;
import APIFullstack.websachcu.Entity.BookEntity;
import APIFullstack.websachcu.Entity.PostedBookEntity;
import APIFullstack.websachcu.Entity.UserEntity;
import APIFullstack.websachcu.Repository.BookRepository;
import APIFullstack.websachcu.Repository.PostBookRepository;
import APIFullstack.websachcu.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;

@Service
public class PostBookService {
    @Autowired
    PostBookRepository postBookRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    UserRepository userRepository;
    @Transactional
    public String runPostBookService(PostBookRequest postBookRequest,String username){
//Check data
    if (postBookRequest.getTitle().isEmpty()
        || postBookRequest.getAuthor().isEmpty()
        || postBookRequest.getCategoryId()==null
            ||username ==null
    ){
        return "bookPage";
    }
    // Lưu sách
    BookEntity bookEntity =new BookEntity();
    bookEntity.setTitle(postBookRequest.getTitle().trim());
    bookEntity.setAuthor(postBookRequest.getAuthor().trim());
    bookEntity.setCategoryId(postBookRequest.getCategoryId());
    bookEntity.setDescription(postBookRequest.getDescription().trim());
    if (postBookRequest.getPicture() == null || postBookRequest.getPicture() == ""){
        bookEntity.setPicture("https://cdn.vietnambiz.vn/thumb_w/600/2019/9/7/global-economy-07-05-2019-min-1567865814459102834293-crop-15678658965781400438761.jpg");
    } else{
        bookEntity.setPicture(postBookRequest.getPicture().trim());
    }
    bookEntity.setPublishYear(postBookRequest.getPublishYear().trim());
    bookEntity.setPrice(postBookRequest.getPrice());
//    bookEntity.setContact(userphone);
    bookEntity = bookRepository.save(bookEntity);
    String returnString = "redirect:/detailPage?id=" + bookEntity.getId();
    // Save PostedBook Table
    PostedBookEntity postedBookEntity =new PostedBookEntity();
    UserEntity userEntity= userRepository.findAllByUserPhone(username);
    Integer userid =userEntity.getUserId();
    postedBookEntity.setBookId(bookEntity.getId());
    postedBookEntity.setUserId(userid);
    postedBookEntity.setPostedTime(new Date(System.currentTimeMillis()));
    postedBookEntity =postBookRepository.save(postedBookEntity);

    return returnString;
    }

}
