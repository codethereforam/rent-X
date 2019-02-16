package priv.thinkam.rentx.web.api;

import priv.thinkam.rentx.web.api.dto.ItemApiDTO;

import java.util.List;

/**
 * item service api
 *
 * @author thinkam
 * @date 2019/02/15
 */
public interface ItemServiceApi {
	List<ItemApiDTO> listItemApiDTO();
}
