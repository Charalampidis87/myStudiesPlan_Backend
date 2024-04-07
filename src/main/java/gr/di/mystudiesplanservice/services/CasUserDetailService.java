package gr.di.mystudiesplanservice.services;

import gr.di.mystudiesplanservice.advisor.AdvisorService;
import gr.di.mystudiesplanservice.user.UserDao;
import org.jasig.cas.client.authentication.AttributePrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.cas.authentication.CasAssertionAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.AuthenticationUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;

import javax.transaction.Transactional;

@Configuration
@Transactional
public class CasUserDetailService implements AuthenticationUserDetailsService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AdvisorService advisorService;

    @Override
    public UserDetails loadUserDetails(Authentication authentication) {
        CasAssertionAuthenticationToken casAssertionAuthenticationToken = (CasAssertionAuthenticationToken) authentication;
        AttributePrincipal principal = casAssertionAuthenticationToken.getAssertion().getPrincipal();

        String eduPersonPrimaryAffiliation = (String) principal.getAttributes().get("eduPersonPrimaryAffiliation");
        String title = (String) principal.getAttributes().get("title");
        String eduPersonPrimaryOrgUnitDN = (String) principal.getAttributes().get("eduPersonPrimaryOrgUnitDN");

        if ((!eduPersonPrimaryAffiliation.equalsIgnoreCase("student"))
                || (!title.equalsIgnoreCase("Undergraduate Student"))
                || (!eduPersonPrimaryOrgUnitDN.equalsIgnoreCase("ou=inftel,ou=schools,dc=uoa,dc=gr")))
        {
                return new org.springframework.security.core.userdetails.User("FOREIGNER", "", true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_FOREIGNER"));
        }

        String username = principal.getName();

        String name;
        if (principal.getAttributes().get("givenName;lang-el") != null) {
            name = (String) principal.getAttributes().get("givenName;lang-el");
        } else if (principal.getAttributes().get("givenName;lang-en") != null) {
            name = (String) principal.getAttributes().get("givenName;lang-en");
        } else {
            name = (String) principal.getAttributes().get("givenName");
        }

        String surname;
        if (principal.getAttributes().get("sn;lang-el") != null) {
            surname = (String) principal.getAttributes().get("sn;lang-el");
        } else if (principal.getAttributes().get("sn;lang-en") != null) {
            surname = (String) principal.getAttributes().get("sn;lang-en");
        } else {
            surname = (String) principal.getAttributes().get("sn");
        }

        String regNum;
        if (principal.getAttributes().get("schacPersonalUniqueCode") != null) {
            String[] regNumURN = ((String) principal.getAttributes().get("schacPersonalUniqueCode")).split(":");
            regNum = regNumURN[regNumURN.length - 1];
        } else regNum = (String) principal.getAttributes().get("uoastudentid");

        String mail = (String) principal.getAttributes().get("mail");

        String regYear = (String) principal.getAttributes().get("schGrAcEnrollment");

        gr.di.mystudiesplanservice.user.User user = userDao.findUserByUsername(username);
        if (user == null) {
            user = new gr.di.mystudiesplanservice.user.User();
            user.setUsername(username);
            userDao.create(user);
            advisorService.create(user.getId());
        }
        user.setName(name);
        user.setSurname(surname);
        user.setRegNum(regNum);
        user.setEmail(mail);
        userDao.update(user);

        return new org.springframework.security.core.userdetails.User(username, "", true, true, true, true, AuthorityUtils.createAuthorityList("ROLE_USER"));
    }
}
