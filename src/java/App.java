
import lazy.util.HibernateUtil;

/**
 * Stand-alone application entry point. Tests of Hibernate loading
 *
 * @author the-ramones
 */
public class App {

    public static void main(String[] args) {
        System.out.println("Yeah!");
        HibernateUtil.getSessionFactory().getCurrentSession().getTransaction();
    }
}
