package priv.thinkam.rentx.service;

import lombok.extern.slf4j.Slf4j;
import priv.thinkam.rentx.dao.entity.Tuser;
import priv.thinkam.rentx.dao.mapper.TuserMapper;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 测试用户表 service
 *
 * @author yanganyu
 * @date 2019-01-19
 */
@Slf4j
@Service
public class TuserService extends ServiceImpl<TuserMapper, Tuser> implements IService<Tuser> {
	@Resource
	private TuserMapper tuserMapper;

	public int testCount() {
		return tuserMapper.testCount();
	}
}
