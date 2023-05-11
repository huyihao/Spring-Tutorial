package tacos.web.api;

import org.springframework.hateoas.RepresentationModel;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import tacos.Taco;

public class TacoModel extends RepresentationModel<TacoModel> {

	private final Taco taco;

	/**
	 * 参数加上@JsonProperty("content")会有以下效果:
	 * 
	 *   "content" : {
     *	 	"name" : "huyihao's taco",
     *		"createdAt" : "2023-03-09T12:24:20.000+00:00"
     *	 }
     *
     * 默认效果等同于@JsonProperty("taco")
	 *   "taco" : {
     *	 	"name" : "huyihao's taco",
     *		"createdAt" : "2023-03-09T12:24:20.000+00:00"
     *	 }     
	 */
	//@JsonCreator
	public TacoModel(Taco taco) {		
		this.taco = taco;
	}

	public Taco getTaco() {
		return taco;
	}
	
}
