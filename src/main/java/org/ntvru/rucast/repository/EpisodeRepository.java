package org.ntvru.rucast.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.ntvru.rucast.model.Episode;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


@Transactional
public interface EpisodeRepository extends CrudRepository<Episode, Long>{
	
	
	@Query(name="Episode.findShowByName",value="select s.show_id, s.name, s.synopsis, s.topic, f.file_name, f.file_path, f.file_size, f.file_type, f.uri from Show s  join FileDocument f on s.show_id=f.show_show_id where s.name = :showName",nativeQuery=true)
          public List<Episode> getEpisodesByShowName(@Param("showName") String showName);

}
