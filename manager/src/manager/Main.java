package manager;

import java.util.List;

public class Main {
	public static void main(String[] args) {
		MemberDao memberDao = new MemberDao();
		MemberService memberService = new MemberService(memberDao);
		
		//C
		MemberVo vo1 = new MemberVo(1, "test1", "1234", "nick1");
		MemberVo vo2 = new MemberVo(2, "test2", "1234", "nick2");
		MemberVo vo3 = new MemberVo(3, "test3", "1234", "nick3");
		
		memberService.regist(vo1);
		memberService.regist(vo2);
		memberService.regist(vo3);
		System.out.println("저장완료!!");
		
		//R
		List<MemberVo> ls = memberService.listAll();
		for(MemberVo tmp : ls) {
			System.out.println(tmp);
		}
		System.out.println("전체조회 완료!");
		
		MemberVo vo = null;
		vo = memberService.read(1);
		System.out.println(vo);
		System.out.println("조회 완료!");
		
		//U
		vo = memberService.read(1);
		System.out.println(vo);
		
		if(vo != null) {
			vo.setMemberPw("4321");
			vo.setNickName("1nick");
			memberService.edit(vo);
		}
		System.out.println("수정완료!!");
		
		//D
		memberService.remove(2);
		ls = memberService.listAll();
		for(MemberVo tmp : ls) {
			System.out.println(tmp);
		}
		System.out.println("전체조회 완료!");
	}
}
