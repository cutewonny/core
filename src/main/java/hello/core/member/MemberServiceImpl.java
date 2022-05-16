package hello.core.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class MemberServiceImpl implements MemberService{
//   private final MemberRepository memberRepository = new MemoryMemberRepository();
   private final MemberRepository memberRepository;
   //MemberRepository 라는 인터페이스만 있다.
    //DIP: 추상화에만 의존한다

    @Autowired
    public MemberServiceImpl(MemberRepository memberRepository) {// 생성자 Alt Insert
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {//뷰에서 호출
        memberRepository.save(member);//DB로 전달

    }

    @Override
    public Member findMember(Long memberId) {//뷰에서 호출
        return memberRepository.findById(memberId);//DB에서 가져옴
    }
    
    //테스트 용도
    public MemberRepository getMemberRepository() {
        return memberRepository;
    }
}
