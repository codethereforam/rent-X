package priv.thinkam.rentx.common.enums;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 物品状态枚举
 *
 * @author thinkam
 * @date 2019/02/10
 */
public enum StuffStatusEnum {
	/**
	 * 不出租
	 */
	NOT(3, "不出租"),
	/**
	 * 未租
	 */
	HAVE_NOT(0, "未租"),
	/**
	 * 申请租用
	 */
	APPLY(1, "申请租用"),
	/**
	 * 已租
	 */
	ALREADY(2, "已租");

	private int code;
	private String name;

	StuffStatusEnum(int code, String name) {
		this.code = code;
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public String getName() {
		return name;
	}

	/**
	 * {'key': 'value', 'value': 'StuffStatusEnum'}
	 */
	private static final Map<Integer, StuffStatusEnum> MAP;

	static {
		StuffStatusEnum[] enums = StuffStatusEnum.values();
		MAP = new HashMap<>(enums.length);
		Arrays.stream(enums).forEach(type -> MAP.put(type.code, type));
	}

	public static StuffStatusEnum getByValue(int value) {
		return MAP.get(value);
	}
}
