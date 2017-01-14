import com.morsel.models.OrderItem;
import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;

/**
 * Created by ajeet on 14/1/17.
 */
public class OrderItemDao extends AbstractDAO<OrderItem> {

    public OrderItemDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }
}
