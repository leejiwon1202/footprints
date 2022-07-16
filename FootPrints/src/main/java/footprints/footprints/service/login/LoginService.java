package footprints.footprints.service.login;

import footprints.footprints.domain.member.Member;
import footprints.footprints.domain.member.MemberDTO;
import footprints.footprints.repository.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberRepository memberRepository;

    @Transactional(readOnly = true)
    public int loginCheck(MemberDTO memberDTO) {
        Member member = memberRepository.findByNick(memberDTO.getNick());

        if(member == null){
            return 0;  //닉네임 존재x
        }
        else{
            String real_pwd = member.getPassword();

            if(memberDTO.getPw().equals(real_pwd)){
                return 1; //로그인 성금
            }
            else{
                return -1; // 비번 불일치
            }
        }
    }
}
