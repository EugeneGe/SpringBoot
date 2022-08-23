package com.gly.springboot.config.swagger;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger配置类
 *
 * @author gly
 * @date 2020-03-17 11:04
 */
@Configuration
@EnableSwagger2
@Profile({"dev"})
public class SwaggerConfig {

    @Value("${swagger.enable}")
    private boolean enable;


    //docket1是显示auto的
    @Bean
    public Docket docket1(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("smp").select()
                .apis(RequestHandlerSelectors.basePackage("com.gly.springboot.controller"))
                .paths(PathSelectors.ant("/**")).build();
    }
    //docket2是显示admin的
//    @Bean
//    public Docket docket2(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .groupName("smp").select()
//                .apis(RequestHandlerSelectors.basePackage("com.gly.springboot.controller"))
//                .paths(PathSelectors.ant("/admin/**")).build();
//    }


//    @Bean
//    public Docket createRestApi() {
//        //在配置好的配置类中增加此段代码即可
//        ParameterBuilder ticketPar = new ParameterBuilder();
//        List<Parameter> pars = new ArrayList<Parameter>();
//        //name表示名称，description表示描述
//        ticketPar.name("Authorization").description("Token")
//                .modelRef(new ModelRef("string")).parameterType("header")
//                //required表示是否必填，defaultvalue表示默认值
//                .required(false).defaultValue("").build();
//        pars.add(ticketPar.build());
//
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(apiInfo())
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gly.springboot.boot"))
//                .paths(PathSelectors.any())
//                .build()
//                .globalOperationParameters(pars);
//    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                //标题
                .title("SpringBoot的swagger案例Demo")
                //描述
                .description("用于讲解关于SpringBoot的基本使用")
                //许可
                .license("gly 2022/08/13")
                //许可链接
                .licenseUrl("https://www.google.com/")
                //服务条款网址
                .termsOfServiceUrl("www.spring.io")
                //版本
                .version("1.0")
                //维护人信息
                .contact(new Contact("gly","https://www.google.com/","123456789@qq.com"))
                .build();
    }

}
