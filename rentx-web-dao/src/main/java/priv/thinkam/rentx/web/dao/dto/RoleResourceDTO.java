package priv.thinkam.rentx.web.dao.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * RoleResource DTO
 *
 * @author thinkam
 * @date 2019/01/23
 */
@Data
@Accessors(chain = true)
public class RoleResourceDTO {
	private String roleIdentifierConcat;
	private String resourceURL;
	private String resourceMethod;
}