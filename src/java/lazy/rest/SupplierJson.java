package lazy.rest;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.enterprise.context.RequestScoped;
import lazy.dao.factory.DaoFactory;
import lazy.dao.factory.DaoFactoryType;

/**
 * REST Web Service
 *
 * @author the-ramones
 */
@Path("supplierj")
@RequestScoped
public class SupplierJson {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of SupplierJson
     */
    public SupplierJson() {
    }

    /**
     * Retrieves representation of an instance of laz.rest.SupplierJson
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces("application/json")
    public String getSuppliers() {
        DaoFactory.getDaoFactory(DaoFactoryType.HibernateDaoFactory)
                .getCompanyDao();//.findEagerly("companyWithSuppliers");
        return "";
    }

    /**
     * PUT method for updating or creating an instance of SupplierJson
     *
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("application/json")
    public void putSuppliers(String content) {
    }
}
