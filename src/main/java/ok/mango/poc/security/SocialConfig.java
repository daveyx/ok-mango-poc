package ok.mango.poc.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.social.config.annotation.EnableSocial;
import org.springframework.social.connect.ConnectionFactoryLocator;
import org.springframework.social.connect.support.ConnectionFactoryRegistry;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;


//@Configuration
//@EnableSocial
public class SocialConfig {
//
//	@Value("${social.facebook.appId}")
//	private String fbApiKey;
//
//	@Value("${social.facebook.appSecret}")
//	private String fbApiSecret;
//
//	@Bean
//	public ConnectionFactoryLocator connectionFactoryLocator() {
//		final ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//		final FacebookConnectionFactory fcf = new FacebookConnectionFactory(fbApiKey, fbApiSecret);
//		fcf.setScope("email");
//		registry.addConnectionFactory(fcf);
//		return registry;
//	}

}

////@Configuration
////@EnableSocial
//public class SocialConfig implements SocialConfigurer {
//
//	@Value("${social.facebook.appId}")
//	private String fbApiKey;
//
//	@Value("${social.facebook.appSecret}")
//	private String fbApiSecret;
//	
//	@Autowired
//	private DataSource dataSource;
//	
//    @Bean
//    @Primary
//    public FacebookConnectionFactory facebookConnectionFactory() {
////        final ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
//        final FacebookConnectionFactory fcf = new FacebookConnectionFactory(fbApiKey, fbApiSecret);
//		fcf.setScope("public_profile, email");
////		registry.addConnectionFactory(fcf);
////        return registry;
//		return fcf;
//    }
//    
//    @Override
//    public void addConnectionFactories(ConnectionFactoryConfigurer cfConfig, Environment env) {
//        cfConfig.addConnectionFactory(facebookConnectionFactory());
//    }
//
//    @Override
//    public UserIdSource getUserIdSource() {
//        return new AuthenticationNameUserIdSource();
//    }
//
//    @Override
//    public UsersConnectionRepository getUsersConnectionRepository(ConnectionFactoryLocator connectionFactoryLocator) {
//        return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
//    }
//
//}
