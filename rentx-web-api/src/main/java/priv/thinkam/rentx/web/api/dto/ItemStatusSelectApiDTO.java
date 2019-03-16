package priv.thinkam.rentx.web.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * item status api dto
 *
 * @author thinkam
 * @date 2019/01/26
 */
@Data
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class ItemStatusSelectApiDTO implements Serializable {
	/**
	 * status value
	 */
	private Integer value;

	/**
	 * status name
	 */
	private String name;
}