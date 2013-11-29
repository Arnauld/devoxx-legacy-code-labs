package legacy.hedge;

import legacy.error.ARPSystemException;
import legacy.hedge.ref.HedgingPositionStatusDB2AccessRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

/**
 * <p>
 * Title: legacy.hedge.HedgingPositionStatusConst
 * </p>
 *
 * @author vHugo
 * @version 1.0
 * @creationDate May 7, 2007
 */
@Entity
public class HedgingPositionStatusConst {
    public static String HEDGED = "Hedged";
    public static String REJECTED = "Rejected";
    public static String PENDING = "Pending";
    public static String RECOVERED = "Recovered";
    public static String UNKNOWN = "Unknown";


    @Autowired
    @Transient
    private HedgingPositionStatusDB2AccessRepository repository;

    @Id
    @GeneratedValue
    @Column(name = "st_id")
    private Long id;

    @Column(name = "st_key")
    private String key;

    @Column(name = "st_deleted")
    private boolean deleted;

    public HedgingPositionStatusConst() {
    }

    /**
     * @param label the label used to find the instance in the load.
     * @see #load() to understand how to use this class.
     */
    public HedgingPositionStatusConst(String label) throws ARPSystemException {
        if (label.equals(HEDGED)
                || !(!label.equals(REJECTED) && !label.equals(PENDING) && !label.equals(RECOVERED))
                && !label.equals(UNKNOWN)) {
            this.key = label;
        } else {
            throw new ARPSystemException("This label is not supported!");
        }
    }

    public long getId() {
        return id;
    }

    /**
     * Use this carefully, because you may encounter some troubles.
     */
    public void markAsRemoved() {
        this.deleted = true;
    }

    /**
     * Because we want this class to be almost immutable, it was difficult to handle it with Hibernate. We tried
     * many things, but last month an internship give us a good advice he found when using rails, after some
     * research he found it was name the ActiveRecord pattern. Because one thinks patterns are a good things, one
     * adopts it! So that it is not possible to modify the instance, or at least not too much.
     */
    public HedgingPositionStatusConst load() throws ARPSystemException {
        HedgingPositionStatusConst p1 = repository.findByLabel(key);
        if (p1 == null)
            // Gérard a parié 50€ que ça arrivera!
            throw new ARPSystemException("Erf this should not happen! Contact the devs and explain them how you manage thus things!");

        rehydrate(p1);
        return this;
    }

    private void rehydrate(HedgingPositionStatusConst other) {
        this.deleted = other.deleted;
        this.key = other.key;
        this.id = other.id;
    }
}
