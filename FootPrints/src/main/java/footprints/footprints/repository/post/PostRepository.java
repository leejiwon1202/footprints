package footprints.footprints.repository.post;

import footprints.footprints.domain.post.Post;
import footprints.footprints.domain.post.PostDTO;

import java.util.List;

public interface PostRepository {

    void save(PostDTO postDTO);

    List<Post> findAll(String areaName);

    Post findDetail(Long post_num);

}
