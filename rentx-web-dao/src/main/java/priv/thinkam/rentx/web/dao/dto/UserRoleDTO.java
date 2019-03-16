package priv.thinkam.rentx.web.dao.dto;

import lombok.Data;

/**
 * UserRole DTO
 *
 * @author thinkam
 * @date 2019/01/23
 */
@Data
public class UserRoleDTO {
	private String username;
	private String password;
	private String roleIdentifierConcat;
}
