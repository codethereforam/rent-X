package priv.thinkam.rentx.common.util;

import org.junit.Test;
import priv.thinkam.rentx.web.api.dto.CategoryApiDTO;
import priv.thinkam.rentx.web.api.param.CategoryApiParam;
import priv.thinkam.rentx.web.dao.entity.Category;
import priv.thinkam.rentx.web.service.param.CategoryParam;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author thinkam
 * @date 2019/01/27
 */
public class BeanUtilTest {

	@Test
	public void map1() {
		CategoryApiParam categoryApiParam = new CategoryApiParam()
				.setName("牛仔裤")
				.setDescription("牛仔裤描述")
				.setParentId(14)
				.setLevel(2)
				.setStatus(true);
		System.out.println(categoryApiParam);
		CategoryParam categoryParam = BeanUtil.map(categoryApiParam, CategoryParam.class);
		System.out.println(categoryParam);
	}

	@Test
	public void map2() {
		CategoryParam categoryParam = new CategoryParam()
				.setName("牛仔裤")
				.setDescription("牛仔裤描述")
				.setParentId(14)
				.setLevel(2)
				.setStatus(true);
		System.out.println(categoryParam);
		Category category = BeanUtil.map(categoryParam, Category.class);
		System.out.println(category);
	}


	@Test
	public void map3() throws IOException {
		LocalDateTime now = LocalDateTime.now();
		Category category1 = new Category()
				.setName("牛仔裤")
				.setDescription("牛仔裤描述")
				.setParentId(14)
				.setLevel(2)
				.setStatus(true);
		category1.setId(1);
		category1.setMark(true);
		category1.setAddUserId(1);
		category1.setAddTime(now);
		category1.setUpdateUserId(1);
		category1.setUpdateTime(now);
		Category category2 = new Category()
				.setName("牛仔裤1")
				.setDescription("牛仔裤描述1")
				.setParentId(141)
				.setLevel(21)
				.setStatus(true);
		category2.setId(11);
		category2.setMark(true);
		category2.setAddUserId(11);
		category2.setAddTime(now);
		category2.setUpdateUserId(11);
		category2.setUpdateTime(now);
		List<Category> categoryList = Arrays.asList(category1, category2);
		System.out.println(categoryList);
		List<CategoryApiDTO> categoryApiDTOList=
				categoryList.stream().map(c -> BeanUtil.map(c, CategoryApiDTO.class)).collect(Collectors.toList());
		System.out.println(categoryApiDTOList);
	}
}
