package tacos.data;

import java.sql.Timestamp;
import java.sql.Types;
import java.util.Arrays;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import tacos.domain.Ingredient;
import tacos.domain.Taco;

@Repository
public class JdbcTacoRepository implements TacoRepository {

	private JdbcTemplate jdbc; 
	
	@Autowired
	public JdbcTacoRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	@Override
	public Taco save(Taco taco) {
		// 保存taco对象到Taco表，并返回插入数据行对应的id
		long tacoId = saveTacoInfo(taco);
		taco.setId(tacoId);
		// 将ingredient和对应的taco对象的id插入到Taco_Ingredients表中
		for (Ingredient ingredient : taco.getIngredients()) {
			saveIngredientToTaco(ingredient, tacoId);
		}
		return taco;
	}

	private long saveTacoInfo(Taco taco) {
		taco.setCreatedAt(new Date());
		PreparedStatementCreatorFactory pcf = new PreparedStatementCreatorFactory(
													"insert into Taco (name, createdAt) values (?, ?)", 
													Types.VARCHAR, Types.TIMESTAMP
												);
		pcf.setReturnGeneratedKeys(true);
		PreparedStatementCreator psc =
				pcf.newPreparedStatementCreator(
					Arrays.asList(
						taco.getName(),
						new Timestamp(taco.getCreatedAt().getTime()))
				);
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(psc, keyHolder);
		
		return keyHolder.getKey().longValue();
	}
	
    private void saveIngredientToTaco(
        Ingredient ingredient, long tacoId) {
		    jdbc.update(
		        "insert into Taco_Ingredients (taco, ingredient) " +
		        "values (?, ?)",
		        tacoId, ingredient.getId());
    }	
}
