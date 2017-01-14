import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by ajeet on 14/1/17.
 */
public class AddressDao extends AbstractDAO<AddressDao> {
    public AddressDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
