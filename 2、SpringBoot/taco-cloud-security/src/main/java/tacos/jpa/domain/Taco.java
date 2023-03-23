package tacos.jpa.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

// JPA中的驼峰标识自动映射到数据表中下划线标识
@Data
@Entity					// 声明为JPA实体
@Table(name = "Taco")
public class Taco {

	@Id					// 指定为数据库中唯一标识
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private Date createdat;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	private String name;	
	
	/**
	 * cascade级联操作策略:
	 * 
	 * 1. CascadeType.PERSIST 级联新建 
     * 2. CascadeType.REMOVE 级联删除 
     * 3. CascadeType.REFRESH 级联刷新 
     * 4. CascadeType.MERGE 级联更新 
     * 5. CascadeType.ALL 四项全选 
     * 6. 默认，关系表不会产生任何影响
	 */
	@ManyToMany(targetEntity = Ingredient.class, cascade = CascadeType.ALL)
    @JoinTable(name = "Taco_Ingredients",
    		   joinColumns = @JoinColumn(name = "taco",  referencedColumnName = "id"),    
    		   inverseJoinColumns = @JoinColumn(name = "ingredient", referencedColumnName = "id"))
	@Size(min = 1, message = "You must choose at least 1 ingredient")
	private List<Ingredient> ingredients;

	@PrePersist
	void createdAt() {
		this.createdat = new Date();
	}
}
