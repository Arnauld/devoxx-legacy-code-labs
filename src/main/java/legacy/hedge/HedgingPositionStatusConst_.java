package legacy.hedge;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

/**
 * @author <a href="http://twitter.com/aloyer">@aloyer</a>
 */
@StaticMetamodel(HedgingPositionStatusConst.class)
public class HedgingPositionStatusConst_ {
    // Raw attributes
    public static volatile SingularAttribute<HedgingPositionStatusConst, Long> id;
    public static volatile SingularAttribute<HedgingPositionStatusConst, String> key;
    public static volatile SingularAttribute<HedgingPositionStatusConst, Boolean> deleted;
}
