package footprints.footprints.service.roomInfo;

import footprints.footprints.domain.roomInfo.RoomInfoDTO;
import footprints.footprints.repository.roomInfo.RoomInfoRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class RoomInfoServiceImpl implements RoomInfoService {

    private final RoomInfoRepository roomInfoRepository;

    @Override
    public void join(String nick, Long post_id){
        roomInfoRepository.save_d(nick, post_id);
    }

    @Override
    public void exit(String nick, Long post_id){
        log.info("exit {} {}", nick, post_id);
        roomInfoRepository.delete_d(nick, post_id);
    }

    @Override
    public void delete(Long post_id) {
        roomInfoRepository.delete_all(post_id);
    }
}