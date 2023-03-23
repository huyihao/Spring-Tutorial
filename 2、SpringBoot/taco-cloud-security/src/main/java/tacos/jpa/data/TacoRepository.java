package tacos.jpa.data;

import org.springframework.data.repository.CrudRepository;

import tacos.jpa.domain.Taco;

public interface TacoRepository extends CrudRepository<Taco, Long> {

}
