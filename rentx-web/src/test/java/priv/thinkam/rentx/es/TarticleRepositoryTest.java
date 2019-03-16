package priv.thinkam.rentx.es;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.thinkam.rentx.web.dao.entity.es.StuffDocument;
import priv.thinkam.rentx.web.dao.repository.StuffRepository;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TarticleRepositoryTest {
	@Resource
	private StuffRepository stuffRepository;

	@Test
	public void findStuffTest() {
		List<StuffDocument> stuffList1 = stuffRepository.findByDescriptionAndCategoryId("哈哈", null);
		stuffList1.forEach(System.out::println);
		System.out.println("-------------------");
		List<StuffDocument> stuffList2 = stuffRepository.findByNameAndCategoryId("毛衣", 5);
		stuffList2.forEach(System.out::println);
	}
}
