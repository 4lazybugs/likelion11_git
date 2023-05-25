package likelion.springbootbbaco.repository;

import likelion.springbootbbaco.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
