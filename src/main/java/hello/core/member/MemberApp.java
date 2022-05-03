package hello.core.member;

public class MemberApp {
    public static void main(String[] args) {
        MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "워니", Grade.VIP);
        memberService.join(member);
        System.out.println("join = "+member.getName());

        Member findMember = memberService.findMember(1L);
        System.out.println("findMember = "+findMember.getName());

    }
}
