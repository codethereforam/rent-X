package priv.thinkam.rentx.web.dao.entity.es;

import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.util.Date;

/**
 * test article
 *
 * @author yanganyu
 * @date 2019/1/22 14:13
 */
@Document(indexName="article-store",type="article")
@Data
@Accessors(chain = true)
public class Tarticle {
	@Id
	private Long id;
	/**
	 * 标题
	 */
	private String title;
	/**
	 * 内容
	 */
	private String content;
	/**
	 * 浏览量
	 */
	private Integer viewCount;
	/**
	 * 发布时间
	 */
	private Date createTime;
	/**
	 * 更新时间
	 */
	private Date updateTime;
}
