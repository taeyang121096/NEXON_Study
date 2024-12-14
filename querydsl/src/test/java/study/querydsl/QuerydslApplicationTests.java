package study.querydsl;

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import study.querydsl.entity.Hello;
import study.querydsl.entity.QHello;

import static org.assertj.core.api.Assertions.assertThat;
import static study.querydsl.entity.QHello.*;

@SpringBootTest
@Transactional
class QuerydslApplicationTests {

	@PersistenceContext
	EntityManager em;

	JPAQueryFactory queryFactory;

	@BeforeEach
	void init() {
		queryFactory = new JPAQueryFactory(em);
	}

	@Test
	void contextLoads() {
		Hello hello = new Hello();
		em.persist(hello);

		Hello result = queryFactory.selectFrom(QHello.hello)
				.fetchOne();

		assertThat(hello).isEqualTo(result);
	}

}
