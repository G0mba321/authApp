import org.example.Menu;
import org.example.UserService;
import org.example.repo.UserRepo;
import org.example.repo.UserRepoHibernateImpl;
import org.example.repo.UserRepoMapImpl;

void main() throws Exception {
    UserRepo repo = new UserRepoHibernateImpl();
    Menu menu = new Menu(new UserService(repo));
    menu.menuTable();
    repo.close();
}

