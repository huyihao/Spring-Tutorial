package tacos.jpa.domain;

import java.io.Serializable;
import java.util.ArrayList;
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
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.CreditCardNumber;

import lombok.Data;

/**
 * JPA会将驼峰标识的字段映射到数据表中下划线标识字段
 */
@Data
@Entity
@Table(name = "Taco_Order")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id					
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Date placedat;
	
	@NotBlank(message = "Name is required")
	private String deliveryname;

	@NotBlank(message = "Street is required")
	private String deliverystreet;

	@NotBlank(message = "City is required")
	private String deliverycity;

	@NotBlank(message = "State is required")
	private String deliverystate;

	@NotBlank(message = "Zip code is required")
	private String deliveryzip;

	@CreditCardNumber(message = "Not a valid credit card number")
	private String ccnumber;

	@Pattern(regexp = "^(0[1-9]|1[0-2])([\\/])([1-9][0-9])$", message = "Must be formatted MM/YY")
	private String ccexpiration;

	@Digits(integer = 3, fraction = 0, message = "Invalid CVV")
	private String cccvv;
	
	/**
	 * 在下单之前Taco对象对应的数据已经持久化到数据库里了，所以这里的级联策略不能用CascadeType.ALL
	 * 否则会同步去往Taco表里插记录，因为记录已存在id一致，所以会报错
	 */
	@ManyToMany(targetEntity = Taco.class, cascade = CascadeType.MERGE)
    @JoinTable(name = "Taco_Order_Tacos",
			   joinColumns = @JoinColumn(name = "tacoorder",  referencedColumnName = "id"),    
			   inverseJoinColumns = @JoinColumn(name = "taco", referencedColumnName = "id"))	
	private List<Taco> tacos = new ArrayList<Taco>();
	
	public void addDesign(Taco design) {
		this.tacos.add(design);
	}	
	
	@PrePersist
	void placedat() {
		this.placedat = new Date();
	}
	
}
