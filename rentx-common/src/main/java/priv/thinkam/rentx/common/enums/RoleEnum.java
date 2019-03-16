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
	ROOT(1, "ROOT"),
	/**
	 * 出租人
	 */
	LESSOR(2, "LESSOR"),
	/**
	 * 承租人
	 */
	LESSEE(3, "LESSEE"),
	/**
	 * 游客用户
	 */
	GUEST(4, "GUEST");

	/**
	 * 角色ID
	 */
	private int id;
	/**
	 * 角色名称
	 */
	private String name;

	RoleEnum(int id, String name) {
		this.id = id;
		this.name = name;
	}

	/**
	 * {'key': 'value', 'value': 'RoleEnum'}
	 */
	private static final Map<Integer, RoleEnum> ID_ENUM_MAP;
	private static final Map<String, RoleEnum> NAME_ENUM_MAP;

	static {
		RoleEnum[] enums = RoleEnum.values();
		ID_ENUM_MAP = new HashMap<>(enums.length);
		Arrays.stream(enums).forEach(role -> ID_ENUM_MAP.put(role.id, role));
		NAME_ENUM_MAP = new HashMap<>(enums.length);
		Arrays.stream(enums).forEach(role -> NAME_ENUM_MAP.put(role.name, role));
	}

	public static RoleEnum getById(int id) {
		return ID_ENUM_MAP.get(id);
	}

	public static RoleEnum getByName(String name) {
		return NAME_ENUM_MAP.get(name);
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}
