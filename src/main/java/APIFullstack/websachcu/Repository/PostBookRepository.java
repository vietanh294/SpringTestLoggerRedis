package APIFullstack.websachcu.Repository;

import APIFullstack.websachcu.Entity.PostedBookEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostBookRepository extends JpaRepository<PostedBookEntity,Integer> {
    PostedBookEntity findAllByBookId(Integer bookId);
    List<PostedBookEntity> findAllByUserId(Integer userId);
}
