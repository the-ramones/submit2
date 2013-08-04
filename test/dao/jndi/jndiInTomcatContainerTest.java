package dao.jndi;

import dao.controller.CompanyEnterpriseBean;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author the-ramones
 */
public class jndiInTomcatContainerTest {
    
    Log log = LogFactory.getLog(jndiInTomcatContainerTest.class);

    @Test
    public void startupJndiTest() throws NamingException {
        Context ctx = new InitialContext();
        Context envCtx = (Context) ctx.lookup("java:comp/env");
        CompanyEnterpriseBean bean = (CompanyEnterpriseBean) envCtx.lookup("bean/CompanyEnterpriceBeanFactory");
        
        assertNotNull(bean);
        bean.execute();
        assertEquals(bean.getTest(), "Test property");        
    }
    
    public jndiInTomcatContainerTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }
}
