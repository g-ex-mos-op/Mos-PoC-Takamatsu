package jp.co.isid.mos.bird.bizreport.groupsuiiref.entity;

import jp.co.isid.mos.bird.bizreport.common.suiiref.entity.SuiiRefUIEntity;

public class UISuiiGepo extends SuiiRefUIEntity {
    
    public static final String TABLE = "BT64ZGEP";
    private String anchorHref;
    
    /**
     * アンカーHREFを取得します。
     * @return アンカーHREF
     */
    public String getAnchorHref() {
        return anchorHref;
    }
    /**
     * アンカーHREFを設定します。
     * @param anchorHref アンカーHREF
     */
    public void setAnchorHref(String anchorHref) {
        this.anchorHref = anchorHref;
    }
}
