package manager;

import java.util.List;

public class MemberService {
	private MemberDao memberDao;
	
	public MemberService(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	//등록하기
	public boolean regist(MemberVo vo) {
		int ret = memberDao.insertMember(vo);
		if(ret == 1) {
			return true;
		}
		return false;
	}
	
	//조회하기
	public MemberVo read(int num) {
		return memberDao.selectMember(num);
	}
	
	//전체조회
	public List<MemberVo> listAll() {
		return memberDao.selectMemberAll();
	}
	
	//수정
	public boolean edit(MemberVo vo, String memberPwOld) {
		int result = -1;
		MemberVo searchMember = memberDao.selectMember(vo.getNum());
		
		if(searchMember.getMemberPw().equals(memberPwOld)) {
			result = memberDao.updateMember(vo);
		}
		return (result == 1) ? true : false;
	}
	
	//탈퇴
	public boolean remove(int num) {
		boolean result = false;
		int ret = memberDao.deleteMember(num);
		return (ret == 1) ? true : false;
	}
}
