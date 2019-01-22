package priv.thinkam.rentx.web.api;

import priv.thinkam.rentx.web.api.bo.TuserBo;
import priv.thinkam.rentx.web.api.query.TuserQuery;

import java.util.List;

/**
 * @author thinkam
 * @date 2019/01/21
 */
public interface TuserServiceApi {
	List<TuserBo> list(TuserQuery tuserQuery);
}
