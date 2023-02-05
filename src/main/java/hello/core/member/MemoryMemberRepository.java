package hello.core.member;

import java.util.HashMap;
import java.util.Map;

// 멤버를 가져올 DB가 확정되지 않았기 때문에
// 임시 멤버 정보를 메모리에만 올려놓고 테스트함
public class MemoryMemberRepository implements MemberRepository{

    //저장소
    private static Map<Long, Member> store = new HashMap<>();

    @Override
    public void save(Member member){
        store.put(member.getId(), member);
    }

    @Override
    public Member findById(Long memberId) {
        return store.get(memberId);
    }


}
