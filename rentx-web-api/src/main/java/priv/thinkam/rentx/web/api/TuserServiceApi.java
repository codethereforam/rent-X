package priv.thinkam.rentx.web.api;

import priv.thinkam.rentx.web.api.dto.TuserApiDTO;
import priv.thinkam.rentx.web.api.query.TuserQuery;

import java.util.List;

/**
 * @author thinkam
 * @date 2019/01/21
 */
public interface TuserServiceApi {
	List<TuserApiDTO> list(TuserQuery tuserQuery);
}
