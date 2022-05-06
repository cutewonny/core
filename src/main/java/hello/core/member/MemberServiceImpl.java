package hello.core.member;

public class MemberServiceImpl implements MemberService{
//   private final MemberRepository memberRepository = new MemoryMemberRepository();
   private final MemberRepository memberRepository;
   //MemberRepository 라는 인터페이스만 있다.
    //DIP: 추상화에만 의존한다

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
}
