package com.github.RenatoTakaoka.ms_proposta.config;

import com.github.RenatoTakaoka.ms_proposta.dto.UserInfoDTO;
import com.github.RenatoTakaoka.ms_proposta.models.Proposta;
import com.github.RenatoTakaoka.ms_proposta.models.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.stream.Collectors;

@Configuration
public class ConfigModelMapper {

    @Bean
    public ModelMapper getModelMapper() {
        return new ModelMapper();
    }

}
