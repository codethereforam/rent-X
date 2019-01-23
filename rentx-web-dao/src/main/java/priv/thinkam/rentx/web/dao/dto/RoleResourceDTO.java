package priv.thinkam.rentx.web.dao.dto;

import lombok.Data;

/**
 * RoleResource DTO
 *
 * @author thinkam
 * @date 2019/01/23
 */
@Data
public class RoleResourceDTO {
	private String roleIdentifier;
	private String resourceURL;
	private String resourceMethod;
}