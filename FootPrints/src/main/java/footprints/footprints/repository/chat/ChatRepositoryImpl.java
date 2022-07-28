package footprints.footprints.repository.chat;

import footprints.footprints.domain.chat.ChatData;
import footprints.footprints.domain.chat.ChatDataDTO;
import footprints.footprints.domain.post.Post;
import footprints.footprints.repository.member.MemberRepositoryImpl;
import footprints.footprints.repository.post.PostRepositoryImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Slf4j
@RequiredArgsConstructor
public class ChatRepositoryImpl implements ChatRepository {

    private final MemberRepositoryImpl memberRepository;
    private final PostRepositoryImpl postRepository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Long> getPostIdList(String nick) {
        TypedQuery<Long> integerTypedQuery = em.createQuery("select r.post.post_id from RoomInfo r " +
                "where r.member.nick = :nick", Long.class).setParameter("nick", nick);

        List<Long> resultList = integerTypedQuery.getResultList();
        log.info("---------------------{}", resultList.get(0));
        if (resultList.size() == 0) return null;

        else return resultList;
    }

    @Override
    public List<String> getPostInfoList(List<Long> postIdList) {
        TypedQuery<String> stringTypedQuery = em.createQuery("select p.post_name, p.category from Post p " +
                "where p.post_id in :list", String.class).setParameter("list",postIdList);

        List<String> resultList = stringTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList;
    }

    @Override
    public List<String> getNickList(Long post_id) {
        TypedQuery<String> stringTypedQuery = em.createQuery("select r.member.nick from RoomInfo r " +
                "where r.post.post_id = :post_id", String.class).setParameter("post_id", post_id);

        List<String> resultList = stringTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList;
    }

    @Override
    public List<String> getChatList(Long post_id) {
        TypedQuery<String> stringTypedQuery = em.createQuery("select c.from_name, c.time, c.message from ChatData c " +
                "where c.post.post_id = :post_id", String.class).setParameter("post_id", post_id);

        List<String> resultList = stringTypedQuery.getResultList();

        if (resultList.size() == 0) return null;

        else return resultList;
    }

    @Override
    public void save(ChatDataDTO chatDataDTO) {
        Post post = postRepository.findByPostId(chatDataDTO.getPost_id());
        ChatData chatData = new ChatData(chatDataDTO.getFrom_name(), chatDataDTO.getTime(), chatDataDTO.getMessage(), post);
        em.persist(chatData);
    }


}