package priv.thinkam.rentx.common.enums;

import com.baomidou.mybatisplus.core.enums.IEnum;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author thinkam
 * @date 2019/02/10
 */
public enum ItemStatusEnum implements IEnum<Integer> {
	/**
	 * 申请中
	 */
	APPLYING(0, "申请中"),
	/**
	 * 不通过
	 */
	DISAPPROVED(1, "不通过"),
	/**
	 * 租用中
	 */
	RENTING(2, "租用中"),
	/**
	 * 已归还
	 */
	RETURNED(3, "已归还");

	private int value;
	private String name;

	ItemStatusEnum(int value, String name) {
		this.value = value;
		this.name = name;
	}

	@Override
	public Integer getValue() {
		return value;
	}

	public String getName() {
		return name;
	}

	/**
	 * {'key': 'value', 'value': 'ItemStatusEnum'}
	 */
	private static final Map<Integer, ItemStatusEnum> MAP;

	static {
		ItemStatusEnum[] enums = ItemStatusEnum.values();
		MAP = new HashMap<>(enums.length);
		Arrays.stream(enums).forEach(type -> MAP.put(type.value, type));
	}

	public static ItemStatusEnum getByValue(int value) {
		return MAP.get(value);
	}
}
