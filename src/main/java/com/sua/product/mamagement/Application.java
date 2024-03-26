package com.sua.product.mamagement;

import org.modelmapper.ModelMapper;
import org.modelmapper.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean //ModelMapper 인스턴스 생성 후 빈 등록 과정
	public ModelMapper modelMapper() {
		ModelMapper modelMapper =  new ModelMapper();
		modelMapper.getConfiguration().setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)
				.setFieldMatchingEnabled(true); //ModelMapper가 private 필드에 리플렉션 API로 접근하여 변환하도록
		return modelMapper;
	}
}
