package priv.thinkam.rentx.es;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;
import priv.thinkam.rentx.web.dao.entity.es.Tarticle;
import priv.thinkam.rentx.web.dao.repository.TarticleRepository;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TarticleRepositoryTest {
	@Resource
	private TarticleRepository tarticleRepository;

	@Before
	public void initRepositoryData() {
		Date now = new Date();
		//清除所有数据
		tarticleRepository.deleteAll();
		Tarticle tarticle = new Tarticle();
		tarticle.setId((long) 1);
		tarticle.setTitle("《蝶恋花》");
		tarticle.setContent("槛菊愁烟兰泣露，罗幕轻寒，燕子双飞去。明月不谙离恨苦，斜光到晓穿朱户。昨夜西风凋碧树，独上高楼，望尽天涯路。欲寄彩笺兼尺素，山长水阔知何处？");
		tarticle.setCreateTime(now);
		tarticle.setUpdateTime(now);
		tarticle.setViewCount(678);
		tarticleRepository.save(tarticle);
		Tarticle tarticle2 = new Tarticle();
		tarticle2.setId((long) 2);
		tarticle2.setTitle("《蝶恋花》");
		tarticle2.setContent("伫倚危楼风细细，望极春愁，黯黯生天际。草色烟光残照里，无言谁会凭阑意。拟把疏狂图一醉，对酒当歌，强乐还无味。衣带渐宽终不悔，为伊消得人憔悴。");
		tarticle2.setCreateTime(now);
		tarticle2.setUpdateTime(now);
		tarticle.setViewCount(367);
		tarticleRepository.save(tarticle2);
		Tarticle tarticle3 = new Tarticle();
		tarticle3.setId((long) 3);
		tarticle3.setTitle("《青玉案·元夕》");
		tarticle3.setContent("东风夜放花千树，更吹落，星如雨。宝马雕车香满路。凤箫声动，玉壶光转，一夜鱼龙舞。蛾儿雪柳黄金缕，笑语盈盈暗香去。众里寻他千百度，蓦然回首，那人却在，灯火阑珊处。");
		tarticle3.setCreateTime(now);
		tarticle3.setUpdateTime(now);
		tarticle3.setViewCount(786);
		tarticleRepository.save(tarticle3);
	}

	@Test
	public void findDistinctByTitleContainingOrContentContainingTest() {
		Page<Tarticle> page = tarticleRepository.findDistinctByTitleOrContent("蝶恋", "东风",
				PageRequest.of(0, 20));
		page.getContent().forEach(System.out::println);

		System.out.println("------------");
		List<Tarticle> tarticleList = tarticleRepository.findByContent("东风");
		tarticleList.forEach(System.out::println);
	}
}
