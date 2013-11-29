package legacy.hedge.prduct;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
@Entity
public class ProductDict {
    @Id
    @GeneratedValue
    @Column(name = "prd_id")
    private Long id;

    @Column(name = "prd_code")
    private String code;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
