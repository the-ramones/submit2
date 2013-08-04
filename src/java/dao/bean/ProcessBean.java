package dao.bean;

/**
 * Test bean conformed Java Bean mane convention
 * @author the-ramones
 */
public class ProcessBean {

    public ProcessBean() {
    }
    
    private String description = "Process JavaBean";
            
    private Integer id = 997;

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

}
