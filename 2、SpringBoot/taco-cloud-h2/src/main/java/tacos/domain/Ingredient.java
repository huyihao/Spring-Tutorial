package tacos.domain;

import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 *     配料领域类
 */
@Data							// 自动生成getter、setter
@RequiredArgsConstructor        // 自动生成初始化final成员的构造函数
public class Ingredient {
	
	private final String id;
	private final String name;
	private final Type type;
	
	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
	
}
