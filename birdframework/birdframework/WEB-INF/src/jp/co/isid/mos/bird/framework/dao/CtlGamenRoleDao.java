/*
 * 作成日: 200/2/10
 *
 */
package jp.co.isid.mos.bird.framework.dao;

import java.util.List;

import jp.co.isid.mos.bird.framework.entity.CtlGamenRole;


/**
 * 汎用画面ロール制御を取得します
 * @author xylee
 */
public interface CtlGamenRoleDao {

    public Class BEAN = CtlGamenRole.class;

    public static final String getGamenRole_ARGS = "userId, gamenId, bunruiCd";
    public static final String getGamenRole_SQL =            
        " SELECT "
     +  "         BR11.GAMEN_ID "
     +  ",        BR11.BUNRUI_CD " 
     +  ",        BR11.SETEI_KBN "
     +  ",        BR11.ROLE_CD  "
	 +  " FROM "
     +  "         BR11GMRL BR11 " 
     +  ",        BR04USRL BR04 "
     +  " WHERE "
     +  "         BR11.ROLE_CD=BR04.ROLE_CD " 
	 +  " AND     BR11.GAMEN_ID=/*gamenId*/ " 
     +  " AND     BR11.BUNRUI_CD=/*bunruiCd*/ "
     +  " AND     BR04.USER_ID=/*userId*/ "
     +  " ORDER BY  BR11.SETEI_KBN";       

    
    public static final String getMenuHiHyoziRole_ARGS = "userId";
    public static final String getMenuHiHyoziRole_SQL =            
       " SELECT distinct "
    +  "           BR11.GAMEN_ID "
    +  " ,         BR11.BUNRUI_CD " 
    +  " ,         BR11.SETEI_KBN "
    +  " ,         BR11.ROLE_CD "
    +  " FROM  "              
    +  "           BR04USRL BR04 left join BR11GMRL BR11 on BR11.ROLE_CD=BR04.ROLE_CD "
    +  " where "
    +  "           USER_ID=/*userId*/ "
    +  " AND       GAMEN_ID='MENU' "
    +  " AND       BUNRUI_CD='01' "
    +  " ORDER BY  BR11.SETEI_KBN ";
    
    
    /**
     * アクセスできるメインメニューを取得する
     * @param userId ユーザＩＤ
     * @param gamenId 画面ＩＤ
     * @param bunruiCd 分類コード
     * @return ロール
     */   
    public List getGamenRole(String userId, String gamenId, String bunruiCd);

    
    /**
     * ユーザが所属するメニューを非表示となるロールを取得します。
     * @param userId ユーザＩＤ
     * @return ロール
     */
    public List getMenuHiHyoziRole(String userId);
    
}
