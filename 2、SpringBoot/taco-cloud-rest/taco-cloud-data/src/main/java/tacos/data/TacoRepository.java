package tacos.data;

import org.springframework.data.repository.PagingAndSortingRepository;

import tacos.Taco;

/**
 * 继承了PagingAndSortingRepository就具备了分页获取并排序的能力
 */
public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {

}
