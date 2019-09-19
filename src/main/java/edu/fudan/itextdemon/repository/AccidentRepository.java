package edu.fudan.itextdemon.repository;

import edu.fudan.itextdemon.beans.Accident;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface AccidentRepository extends ElasticsearchRepository<Accident, String> {
}
