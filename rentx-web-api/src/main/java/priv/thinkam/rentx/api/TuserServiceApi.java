package priv.thinkam.rentx.api;

import priv.thinkam.rentx.api.bo.TuserBo;
import priv.thinkam.rentx.api.query.TuserQuery;

import java.util.List;

/**
 * @author thinkam
 * @date 2019/01/21
 */
public interface TuserServiceApi {
	List<TuserBo> list(TuserQuery tuserQuery);
}
