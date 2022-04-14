package event.join.member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(String memberId) {
        return memberRepository.findbyId(memberId);
    }

    @Override
    public Member findMember_phone(String memberPhone) {
        return memberRepository.findbyPhone(memberPhone);
    }

    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}