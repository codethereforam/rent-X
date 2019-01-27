package priv.thinkam.rentx.web.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.thinkam.rentx.web.service.param.CategoryParam;

import javax.annotation.Resource;

/**
 * @author thinkam
 * @date 2019/01/27
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class CategoryServiceTest {
	@Resource
	private CategoryService categoryService;

	@Test
	public void add1() {
		categoryService.add(
				new CategoryParam()
						.setName("")
						.setDescription("牛仔裤描述")
						.setParentId(14)
						.setLevel(2)
						.setStatus(true)
		);
	}

	@Test
	public void add2() {
		categoryService.add(
				new CategoryParam()
						.setName("牛仔裤1")
						.setDescription("牛仔裤描述")
						.setParentId(14)
						.setLevel(4)
						.setStatus(true)
		);
	}
}