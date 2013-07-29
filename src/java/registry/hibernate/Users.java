package registry.hibernate;
// Generated Jul 29, 2013 9:22:08 PM by Hibernate Tools 3.2.1.GA

import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Users generated by hbm2java
 */
@Entity
@Table(name = "users", catalog = "registry")
public class Users implements java.io.Serializable {

    private Integer id;
    private String fullname;
    private String job;
    private Set<Registers> registerses = new HashSet<Registers>(0);

    public Users() {
    }

    public Users(String fullname) {
        this.fullname = fullname;
    }

    public Users(String fullname, String job, Set<Registers> registerses) {
        this.fullname = fullname;
        this.job = job;
        this.registerses = registerses;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "fullname", nullable = false)
    public String getFullname() {
        return this.fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    @Column(name = "job")
    public String getJob() {
        return this.job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "users")
    public Set<Registers> getRegisterses() {
        return this.registerses;
    }

    public void setRegisterses(Set<Registers> registerses) {
        this.registerses = registerses;
    }
}
