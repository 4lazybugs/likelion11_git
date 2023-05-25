package likelion.springbootbbaco.controller;

import likelion.springbootbbaco.domain.Member;
import likelion.springbootbbaco.service.MemberService;
import likelion.springbootbbaco.service.MemberServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

//@Controller 어노테이션은 이 클래스가 스프링 MVC의 컨트롤러임을 나타낸다.
//@RequestMapping("members") 어노테이션은 이 컨트롤러에서 처리할 요청의 기본 URL 경로를 /members로 지정한다.
//private final MemberService memberService;는 MemberService 인터페이스를 사용하기 위한 멤버 변수이다.
//@Autowired 어노테이션을 사용하여 생성자 주입을 한다. 그리고 MemberServiceImpl 객체를 주입받아 memberService에 할당한다.
//생성자 주입
@Controller
@RequestMapping("members")
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberServiceImpl memberServiceImpl) {
        this.memberService = memberServiceImpl;
    }

//    @GetMapping("new") 어노테이션은 GET 메서드로 /members/new 경로의 요청을 처리한다.
//    Model 객체를 매개변수로 받아 회원 생성 폼에 사용할 데이터를 추가한다.
//    "members/createMemberForm"을 반환하여 createMemberForm.html 템플릿을 렌더링한다.
// 회원 생성 폼 조회
    @GetMapping("new")
    public String createForm(Model model) {
        model.addAttribute("memberForm", new Member());
        return "members/createMemberForm";
    }

//    @PostMapping("new") 어노테이션은 POST 메서드로 /members/new 경로의 요청을 처리한다.
//    Member 객체를 매개변수로 받아 회원을 생성하고, memberService를 통해 저장한다.
//            "redirect:/"을 반환하여 메인 페이지로 리다이렉션한다.
// 회원 생성
@PostMapping("new")
    public String create(Member member) {
        memberService.save(member);
        return "redirect:/";
    }


//    @GetMapping("") 어노테이션은 GET 메서드로 /members 경로의 요청을 처리한다.
//    모든 회원을 조회하기 위해 memberService의 findAll() 메서드를 호출한다.
//    조회된 회원 리스트를 Model 객체에 추가하여 members/memberList 템플릿을 렌더링한다.
// 모든 회원 조회
    @GetMapping("")
    public String findAll(Model model) {
        List<Member> memberList = memberService.findAll();
        model.addAttribute("memberList",memberList);
        return "members/memberList";
    }
}
//    이렇게 코드는 회원 생성, 조회 기능을 수행하는 컨트롤러로 구성되어 있다.//