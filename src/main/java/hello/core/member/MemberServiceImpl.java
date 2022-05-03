package hello.core.member;

public class MemberServiceImpl implements MemberService{
   private final MemberRepository memberRepository = new MemoryMemberRepository();

    @Override
    public void join(Member member) {//뷰에서 호출
        memberRepository.save(member);//DB로 전달

    }

    @Override
    public Member findMember(Long memberId) {//뷰에서 호출
        return memberRepository.findById(memberId);//DB에서 가져옴
    }
}
