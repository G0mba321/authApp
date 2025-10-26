import org.example.Menu;
import org.example.connection.HibernateConnection;
import org.example.repo.RoleRepo;
import org.example.repo.RoleRepoHibernateImpl;
import org.example.repo.UserRepo;
import org.example.repo.UserRepoHibernateImpl;
import org.example.service.RoleService;
import org.example.service.UserRoleService;
import org.example.service.UserService;

void main() throws Exception {
    HibernateConnection.initConnection();

    UserRepo userRepo = new UserRepoHibernateImpl();
    RoleRepo roleRepo = new RoleRepoHibernateImpl();

    UserService userService = new UserService(userRepo);
    RoleService roleService = new RoleService(roleRepo);


    Menu menu = new Menu(userService, roleService, new UserRoleService(userService, roleService));
    menu.menuTable();
    HibernateConnection.close();
}

