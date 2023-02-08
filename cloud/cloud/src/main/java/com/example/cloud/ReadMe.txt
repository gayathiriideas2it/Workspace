@RefreshScope - class level - load the configuration properties value from the Config server

	@Value("${welcome.message}")- class property level - load values from property file to variable
	
	
	
Hierarchy -> {application}.yml-high, {application}-{profile}.yml - next
@Profile -method or class level -define config details for each env
					@Bean
					@Profile({"dev"})
					public void configDev() throws IOException {
					// logic goes hee ..///
					}
					@Bean
					@Profile({"prod"})
					public void configProd() throws IOException {
					// logic goes hee ..///
					}
					
Tseting
@ActiveProfiles
 @TestPropertySource
					
