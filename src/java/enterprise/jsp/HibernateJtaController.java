package enterprise.jsp;

import enterprise.hibernate.Report;
import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.UserTransaction;
import org.hibernate.Session;
import registry.hibernate.Registers;
import registry.hibernate.RegistersId;

/**
 *
 * @author the-ramones
 */
public class HibernateJtaController {

    public Map<String, List> processRequest(
            Session enterpriseS, Session registryS) {

        UserTransaction ut = null;
        Map<String, List> model = null;

        try {

            /* Hibernate automatically maps 'current session' to JTA
             * transaction
             * Session enterpriseS = enterpriseSf.getCurrentSession();
             * Session registryS = registrySf.getCurrentSession();
             * call after tx.begin()
             */

            Report report = new Report(Date.valueOf("2013-08-01"), Date.valueOf("2013-08-02"), "Michael Douglas", "acting");
            Registers registry = new Registers();
            registry.setId(new RegistersId(11, 1, 1));
            enterpriseS.save(report);
            registryS.save(registry);

            List enterpriseL = enterpriseS.createQuery("from Report").list();
            List registryL = registryS.createQuery("from Registers").list();

            model = new HashMap<String, List>();
            model.put("modelEnterprise", enterpriseL);
            model.put("modelRegistry", registryL);

//            enterpriseS.flush();
//            registryS.flush();

        } catch (Exception e) {
            try {
                ut.rollback();
            } catch (Exception roll) {
                System.err.println("Cannot rollback transaction: " + roll);
            }
        }
        return model;
    }
}
