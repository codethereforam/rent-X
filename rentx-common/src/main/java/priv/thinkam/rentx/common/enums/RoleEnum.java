package priv.thinkam.rentx.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 角色枚举
 *
 * @author yanganyu
 * @date 2019/3/3 23:00
 */
public enum RoleEnum {
	/**
	 * ROOT用户
	 */
	ROOT(1),
	/**
	 * 出租人
	 */
	LESSOR(2),
	/**
	 * 承租人
	 */
	LESSEE(3),
	/**
	 * 游客用户
	 */
	GUEST(4);

	/**
	 * 角色ID
	 */
	private int id;

	RoleEnum(int id) {
		this.id = id;
	}

	/**
	 * {'key': 'value', 'value': 'RoleEnum'}
	 */
	private static final Map<Integer, RoleEnum> MAP;

	static {
		RoleEnum[] enums = RoleEnum.values();
		MAP = new HashMap<>(enums.length);
		Arrays.stream(enums).forEach(type -> MAP.put(type.id, type));
	}

	public static RoleEnum getById(int id) {
		return MAP.get(id);
	}
}
