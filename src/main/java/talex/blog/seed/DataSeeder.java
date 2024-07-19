package talex.blog.seed;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import talex.blog.repositories.SiteUserRepository;
import talex.blog.services.SiteUserService;
import talex.blog.entities.Role;
import talex.blog.entities.SiteUser;
import talex.blog.repositories.RoleRepository;

@Component
public class DataSeeder implements CommandLineRunner {

    private  SiteUserService siteUserService;
    private final SiteUserRepository siteUserRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public DataSeeder(SiteUserRepository siteUserRepository, RoleRepository roleRepository, SiteUserService siteUserService) {
        this.siteUserRepository = siteUserRepository;
        this.roleRepository = roleRepository;
        this.siteUserService = siteUserService;
    }

    @Override
    public void run(String... args) throws Exception {
        seedRoles();
        seedUsers();       
    }

    private void seedUsers() {
        if (siteUserRepository.findByEmail("demo@sapi.ro") == null) {

            SiteUser adminUser = new SiteUser();
            adminUser.setEmail("demo@sapi.ro");
            adminUser.setFirstName("Demo");
            adminUser.setLastName("Admin");
            adminUser.setPassword("demo");

            siteUserService.saveUser(adminUser, "ROLE_ADMIN");
        }
    }
    private void seedRoles() {
        if (roleRepository.findByName("ROLE_ADMIN") == null) {
            Role adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");          
            roleRepository.save(adminRole);
        }
        if (roleRepository.findByName("ROLE_MANAGER") == null) {
            Role managerRole = new Role();
            managerRole.setName("ROLE_MANAGER");          
            roleRepository.save(managerRole);
        }
        if (roleRepository.findByName("ROLE_EMPLOYEE") == null) {
            Role employeeRole = new Role();
            employeeRole.setName("ROLE_EMPLOYEE");          
            roleRepository.save(employeeRole);
        }
    }
}
