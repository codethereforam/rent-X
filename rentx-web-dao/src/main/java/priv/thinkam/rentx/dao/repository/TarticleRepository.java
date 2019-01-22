package priv.thinkam.rentx.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import priv.thinkam.rentx.dao.entity.es.Tarticle;

import java.util.List;

public interface TarticleRepository extends ElasticsearchRepository<Tarticle, Long> {
	Page<Tarticle> findDistinctByTitleOrContent(String title, String content, Pageable pageable);
	List<Tarticle> findByContent(String content);
}