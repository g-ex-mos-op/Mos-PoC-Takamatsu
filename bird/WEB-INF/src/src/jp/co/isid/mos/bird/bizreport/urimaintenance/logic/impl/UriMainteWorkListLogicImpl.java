package jp.co.isid.mos.bird.bizreport.urimaintenance.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.UIUriMainteInfoDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.UIUriMainteWorkInfoDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.UriMainteWorkListLogic;



/**
 * 売上修正情報取得ロジック
 * @author Aspac
 *
 */
public class UriMainteWorkListLogicImpl implements UriMainteWorkListLogic {
    
    
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR008L01";
    
    /**
     * 売上修正情報Dao
     */
    private UIUriMainteWorkInfoDao uIUriMainteWorkInfoDao;
    
    /**
     * 売上情報Dao
     */
    private UIUriMainteInfoDao uIUriMainteInfoDao;
    
    

    /**
     * 売上修正リストの取得を行う
     * @return
     */
    public List execute(String companyCd, String miseCd, String targetYM, String sysdate) {
        
        List listReturn = new ArrayList();
        
        List listUriWork = new ArrayList();
        List listUri = new ArrayList();
        
        //売上在高日次修正＋取引修正を取得する（BT97ADUP + BT96NBUP）
        listUriWork = getUIUriMainteWorkInfoDao().getUriMainteWorkInfo(companyCd, miseCd, targetYM+"%", sysdate);
        
        //売上在高日次＋取引を取得する（BT65SADY + BT95NBKI）
        listUri = getUIUriMainteInfoDao().getUriMainteInfo(companyCd, miseCd, targetYM+"%");
        
        //月日リスト生成
        List ymList = UriMaintenanceCommon.makeListDate(targetYM+"01",31);
        for(int i=0 ; i < ymList.size() ; i++){
            
            //計算対象年月
            String yyyymmdd = (String)ymList.get(i);
            
            boolean flg = false;
            
            //修正データループ
            for (Iterator ite = listUriWork.iterator(); ite.hasNext();) {
                UIUriMainteWorkInfo uriWorkInfo = (UIUriMainteWorkInfo) ite.next();
                
                if(yyyymmdd.equals(uriWorkInfo.getEigyoDt())) {
                    if(!uriWorkInfo.getUpDt().equals(sysdate)) {
                        uriWorkInfo.setUpDt(sysdate);
                        uriWorkInfo.setInsertFlg(true);
                    }
                    listReturn.add(uriWorkInfo);
                    flg = true;
                    break;
                }
            }
            
            //売上データループ
            if(!flg) {
                for (Iterator ite = listUri.iterator(); ite.hasNext();) {
                    UIUriMainteInfo uriInfo = (UIUriMainteInfo) ite.next();
                    
                    if(yyyymmdd.equals(uriInfo.getEigyoDt())) {
                        UIUriMainteWorkInfo uriWorkInfo = new UIUriMainteWorkInfo();
                        UriMaintenanceCommon.copyEntity(uriWorkInfo, uriInfo, sysdate);
                        listReturn.add(uriWorkInfo);
                        break;
                    }
                }
            }
        }
        
        
        //エレメントインデックスを設定する
        int index = 1;
        for (Iterator ite = listReturn.iterator(); ite.hasNext();) {
            UIUriMainteWorkInfo workInfo = (UIUriMainteWorkInfo) ite.next();
            
            String idx = convFormat(2, "0", String.valueOf(index));
            setElementIndex(workInfo, idx);
            index++;
            
        }
        
        
        return listReturn;
    }
    

    
    /**
     * 画面表示用の要素インデックスを設定する
     * @param info
     * @param index
     */
    private void setElementIndex(UIUriMainteWorkInfo info, String index) {
        
//        //売上金タブ
//        info.setU01UriageElm("1"+index);
//        info.setU06TaxElm("2"+index);
//        info.setU68AridakaJituElm( "3"+index);
//        info.setU74KyakusuElm("4"+index);
//        info.setU71CanKenElm("5"+index);
//        info.setU72CanKinElm("6"+index);
//        
//        //前受・売掛その１タブ
//        info.setU15KaikeiKen2Elm("1"+index);
//        info.setU16KaikeiKin2Elm("2"+index);
//        info.setU17KaikeiKen3Elm("3"+index);
//        info.setU18KaikeiKin3Elm("4"+index);
//        info.setU19KaikeiKen4Elm("5"+index);
//        info.setU20KaikeiKin4Elm("6"+index);
//        info.setU21KaikeiKen5Elm("7"+index);
//        info.setU22KaikeiKin5Elm("8"+index);
//        info.setU23KaikeiKen6Elm("9"+index);
//        info.setU24KaikeiKin6Elm("10"+index);        
//        
//        //前受・売掛その２タブ
//        info.setU25KaikeiKen7Elm("1"+index);
//        info.setU26KaikeiKin7Elm("2"+index);
//        info.setU27KaikeiKen8Elm("3"+index);
//        info.setU28KaikeiKin8Elm("4"+index);
//        info.setU29KaikeiKen9Elm("5"+index);
//        info.setU30KaikeiKin9Elm("6"+index);
//        info.setU31KaikeiKen10Elm("7"+index);
//        info.setU32KaikeiKin10Elm("8"+index);
//        info.setU33KaikeiKen11Elm("9"+index);
//        info.setU34KaikeiKin11Elm("10"+index);
//        
//        //販売その１
//        info.setU35TieketKen1Elm("1"+index);
//        info.setU36TieketKin1Elm("2"+index);
//        info.setU37TieketKen2Elm("3"+index);
//        info.setU38TieketKin2Elm("4"+index);
//        info.setU39TieketKen3Elm("5"+index);
//        info.setU40TieketKin3Elm("6"+index);
//        info.setU41TieketKen4Elm("7"+index);
//        info.setU42TieketKin4Elm("8"+index);
//        info.setU43TieketKen5Elm("9"+index);
//        info.setU44TieketKin5Elm("10"+index);
//        
//        //販売その２
//        info.setU45TieketKen6Elm("1"+index);
//        info.setU46TieketKin6Elm("2"+index);
//        info.setU47TieketKen7Elm("3"+index);
//        info.setU48TieketKin7Elm("4"+index);
//        info.setU49TieketKen8Elm("5"+index);
//        info.setU50TieketKin8Elm("6"+index);
//        info.setU51TieketKen9Elm("7"+index);
//        info.setU52TieketKin9Elm("8"+index);
//        info.setU53TieketKen10Elm("9"+index);
//        info.setU54TieketKin10Elm("10"+index);
//        
//        //販売その３
//        info.setU55TieketKen11Elm("1"+index);
//        info.setU56TieketKin11Elm("2"+index);
//        info.setU57TieketKen12Elm("3"+index);
//        info.setU58TieketKin12Elm("4"+index);
//        info.setU59TieketKen13Elm("5"+index);
//        info.setU60TieketKin13Elm("6"+index);
//        info.setU61TieketKen14Elm("7"+index);
//        info.setU62TieketKin14Elm("8"+index);
//        info.setU63TieketKen15Elm("9"+index);
//        info.setU64TieketKin15Elm("10"+index);
//        
//        //値引その１
//        info.setU01NebikiKen1Elm("1"+index);
//        info.setU02NebikiKin1Elm("2"+index);
//        info.setU03NebikiTax1Elm("3"+index);
//        info.setU04NebikiKen2Elm("4"+index);
//        info.setU05NebikiKin2Elm("5"+index);
//        info.setU06NebikiTax2Elm("6"+index);
//        info.setU07NebikiKen3Elm("7"+index);
//        info.setU08NebikiKin3Elm("8"+index);
//        info.setU09NebikiTax3Elm("9"+index);
//        
//        //販売その２
//        info.setU10NebikiKen4Elm("1"+index);
//        info.setU11NebikiKin4Elm("2"+index);
//        info.setU12NebikiTax4Elm("3"+index);
//        info.setU13NebikiKen5Elm("4"+index);
//        info.setU14NebikiKin5Elm("5"+index);
//        info.setU15NebikiTax5Elm("6"+index);
//        info.setU16NebikiKen6Elm("7"+index);
//        info.setU17NebikiKin6Elm("8"+index);
//        info.setU18NebikiTax6Elm("9"+index);
//        
//        //販売その３
//        info.setU19NebikiKen7Elm("1"+index);
//        info.setU20NebikiKin7Elm("2"+index);
//        info.setU21NebikiTax7Elm("3"+index);
//        info.setU22NebikiKen8Elm("4"+index);
//        info.setU23NebikiKin8Elm("5"+index);
//        info.setU24NebikiTax8Elm("6"+index);
//        info.setU25NebikiKen9Elm("7"+index);
//        info.setU26NebikiKin9Elm("8"+index);
//        info.setU27NebikiTax9Elm("9"+index);
        
    }
    
    
    /**
     * 前ゼロを付加する
     * @param length
     * @param str
     * @param value
     * @return
     */
    private String convFormat(int length, String str, String value) {
        
        int cnt = 0;
        if((cnt = value.length()) < length) {
            for(int i=0; i<cnt; i++) {
                value = str + value;
            }
        }
        return value;
    }
    

    public UIUriMainteInfoDao getUIUriMainteInfoDao() {
        return uIUriMainteInfoDao;
    }
    public void setUIUriMainteInfoDao(UIUriMainteInfoDao uriMainteInfoDao) {
        uIUriMainteInfoDao = uriMainteInfoDao;
    }

    public UIUriMainteWorkInfoDao getUIUriMainteWorkInfoDao() {
        return uIUriMainteWorkInfoDao;
    }
    public void setUIUriMainteWorkInfoDao(UIUriMainteWorkInfoDao uriMainteWorkInfoDao) {
        uIUriMainteWorkInfoDao = uriMainteWorkInfoDao;
    }
   
    
}
