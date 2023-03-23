package de.allianz.springboot.config;

import de.allianz.springboot.dto.ToDoCreateDTO;
import de.allianz.springboot.entity.ToDo;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapperConfig {
    /**
     * Creates a ModelMapper which enables us to merge objects
     * This is mainly used to map the data transfer objects to the entities
     *
     * @return ModelMapper
     */

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setSkipNullEnabled(true);
        return modelMapper;
    }
}
