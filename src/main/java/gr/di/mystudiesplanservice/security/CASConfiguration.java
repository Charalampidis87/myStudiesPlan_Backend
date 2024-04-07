package gr.di.mystudiesplanservice.security;

//import gr.di.mystudiesplanservice.services.CustomUserDetailsService;
import org.jasig.cas.client.session.SingleSignOutFilter;
import org.jasig.cas.client.session.SingleSignOutHttpSessionListener;
import org.jasig.cas.client.validation.Cas20ServiceTicketValidator;
import org.jasig.cas.client.validation.Cas30ServiceTicketValidator;
import org.jasig.cas.client.validation.Saml11TicketValidator;
import org.jasig.cas.client.validation.TicketValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.event.EventListener;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.cas.ServiceProperties;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.cas.web.CasAuthenticationFilter;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import gr.di.mystudiesplanservice.services.CasUserDetailService;

import javax.servlet.http.HttpSessionEvent;
import java.util.Arrays;

@Configuration
public class CASConfiguration {

    @Value("${casServiceUrl}")
    private String casServiceUrl;

    @Value("${casAuthenticationUrl}")
    private String casAuthenticationUrl;

    @Value("${casServerUrl}")
    private String casServerUrl;

    @Value("${casLogoutUrl}")
    private String casLogoutUrl;

    private final CasUserDetailService casUserDetailService;

    @Autowired
    public CASConfiguration(CasUserDetailService casUserDetailService) {
        this.casUserDetailService = casUserDetailService;
    }

    @Bean
    public ServiceProperties serviceProperties() {
        ServiceProperties serviceProperties = new ServiceProperties();
        serviceProperties.setService(casServiceUrl);
        serviceProperties.setSendRenew(false);
        return serviceProperties;
    }

    @Bean
    @Primary
    public CasAuthenticationEntryPoint casAuthenticationEntryPoint(ServiceProperties serviceProperties) {
        CasAuthenticationEntryPoint entryPoint = new CasAuthenticationEntryPoint();
        entryPoint.setLoginUrl(casAuthenticationUrl);
        entryPoint.setServiceProperties(serviceProperties);
        return entryPoint;
    }

    @Bean
    public TicketValidator ticketValidator() {
//        return new Cas30ServiceTicketValidator(casServerUrl);
        return new Saml11TicketValidator(casServerUrl);
    }

    @Bean
    public CasAuthenticationProvider casAuthenticationProvider(){
        CasAuthenticationProvider provider = new CasAuthenticationProvider();
        provider.setServiceProperties(this.serviceProperties());
        provider.setTicketValidator(this.ticketValidator());
        provider.setAuthenticationUserDetailsService(this.casUserDetailService);
        provider.setKey("CAS_PROVIDER_https://sso.uoa.gr/");
        return provider;
    }

    @Bean
    public CasAuthenticationFilter casAuthenticationFilter(
//            AuthenticationProvider ap,
            ServiceProperties serviceProperties
    ) throws Exception {
        CasAuthenticationFilter filter = new CasAuthenticationFilter();
        filter.setServiceProperties(serviceProperties);
        filter.setAuthenticationManager(new ProviderManager(Arrays.asList(this.casAuthenticationProvider())));
        return filter;
    }
}
