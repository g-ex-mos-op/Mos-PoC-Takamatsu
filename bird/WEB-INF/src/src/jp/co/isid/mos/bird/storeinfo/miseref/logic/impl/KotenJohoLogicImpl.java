package jp.co.isid.mos.bird.storeinfo.miseref.logic.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.CodInsentiveCdDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstBukkenDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstChintaiDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstEventStatusDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstGyotaiDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstHDCDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstInsentiveDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstMiseDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstMiseKaisoDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstMiseYobiDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstSBDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstStaffDao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.MstTODao;
import jp.co.isid.mos.bird.storeinfo.miseref.dao.TrnURLDao;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstEventStatus;
import jp.co.isid.mos.bird.storeinfo.miseref.entity.MstInsentive;
import jp.co.isid.mos.bird.storeinfo.miseref.logic.KotenJohoLogic;

/**
 * 個店情報の検索ロジック
 * @author xnkusama
 */
public class KotenJohoLogicImpl implements KotenJohoLogic {
    
    /* ロジックID */
    private static final String LOGIC_ID = "BSI001L02";

    /*【DAO】*/
    private MstMiseDao mstMiseDao;
    private MstMiseKaisoDao mstMiseKaisoDao;
    private MstTODao mstTODao;
    private MstSBDao mstSBDao;
    private MstChintaiDao mstChintaiDao;
    private MstEventStatusDao mstEventStatusDao;
    private MstMiseYobiDao mstMiseYobiDao;
    private MstBukkenDao mstBukkenDao;
    private MstGyotaiDao mstGyotaiDao;
    private MstInsentiveDao mstInsentiveDao;
    private MstHDCDao mstHDCDao;
    private CodInsentiveCdDao codInsentiveCdDao;
    private TrnURLDao trnURLDao;
    //20060606追加
    private MstStaffDao mstStaffDao;
    
    /**
     * 個店情報の検索を行う
     * @param String miseCd
     * @param String companyCd
     * @return List  
     * @exception ApplicationException
     */
    public Map execute(String miseCd, String companyCd) throws ApplicationException {
        // パラメータチェック
        validate(miseCd, companyCd);
        // リターンMap
        Map map = new HashMap();
        /* １．DAO【店統合マスタ．店統合マスタの検索】を実行 */
        List listMstMise = getMstMiseDao().selectMiseMst(companyCd, miseCd);
        if (listMstMise == null || listMstMise.size() == 0) {
            throw new NotExistException("店コード");
        }
        map.put("listMstMise", listMstMise);
        /* ２．DAO【店改装履歴．店改装履歴の検索】を実行 */
        List listMstMiseKaiso = getMstMiseKaisoDao().selectMiseKaiso(companyCd, miseCd);
        map.put("listMstMiseKaiso", listMstMiseKaiso);
        /* ３．DAO【テイクオーバー履歴．テイクオーバー履歴の検索】を実行 */
        List listMstTO = getMstTODao().selectTO(companyCd, miseCd);
        map.put("listMstTO", listMstTO);
        /* ４．DAO【スクラップビルド履歴．スクラップビルド履歴の検索】を実行 */
        List listMstSB = getMstSBDao().selectSB(companyCd, miseCd);
        map.put("listMstSB", listMstSB);
        /* ５．DAO【賃貸店舗履歴．賃貸店舗履歴の検索】を実行 */
        List listMstChintai = getMstChintaiDao().selectChintai(companyCd, miseCd);
        map.put("listMstChintai", listMstChintai);
        /* ６．DAO【イベント実施状況．イベント実施状況の検索】を実行 */
        List listMstEventStatus = getMstEventStatusDao().selectEvent(companyCd, miseCd);
        map.put("listMstEventStatus", listMstEventStatus);
        /* ７．DAO【店舗拡張マスタ．店舗拡張マスタの検索】を実行 */
        List listMstMiseYobi = getMstMiseYobiDao().selectMiseYobi(companyCd, miseCd);
        map.put("listMstMiseYobi", listMstMiseYobi);
        /* ８．DAO【物件情報履歴．物件情報履歴の検索】を実行 */
        List listMstBukken = getMstBukkenDao().selectBukken(companyCd, miseCd);
        map.put("listMstBukken", listMstBukken);
        /* ９．DAO【業態履歴．業態履歴の検索】を実行 */
        List listMstGyotai = getMstGyotaiDao().selectGyotai(companyCd, miseCd);
        map.put("listMstGyotai", listMstGyotai);
        /* １０．DAO【インセンティブ．インセンティブ履歴の検索】を実行 */
        List listMstInsentive = getMstInsentiveDao().selectInsentiveRireki(companyCd, miseCd);
        // インセンティブデータを画面表示用に加工し値をセット
        map.put("listMstInsentive", makeInsentiveData(listMstInsentive));
        /* １１．DAO【HDC受賞履歴．HDC受賞履歴の検索】を実行 */
        List listMstHDC = getMstHDCDao().selectHDC(companyCd, miseCd);
        map.put("listMstHDC", listMstHDC);
        /* １２．DAO【インセンティブコード．インセンティブコードの検索】を実行 */
        List listCodInsentiveCd = getCodInsentiveCdDao().selectInsentive(companyCd);
        map.put("listCodInsentiveCd", listCodInsentiveCd);
        /* １３．DAO【地図URL情報．地図URL情報の検索】を実行 */
        List listTrnURL = getTrnURLDao().selectMapURL(companyCd, miseCd);
        map.put("listTrnURL", listTrnURL);
        /* １４．イベント分類一覧リスト作成 */
        map.put("listEventBunrui", makeEventBunruiList(listMstEventStatus));
        
        //20060606 追加
        /* １５．DAO【加盟店スタッフマスタ．研修修了者一覧の検索】を実行 */
        List listMstStaff = getMstStaffDao().selectStaff(companyCd, miseCd);
        map.put("listMstStaff", listMstStaff);

        return map;
    }
    
    private List makeInsentiveData(List listInsentive) {
    	List list = new ArrayList();
    	
    	// インセンティブ１～５を分解し、1レコード１インセンティブ情報にする
    	for (Iterator ite = listInsentive.iterator(); ite.hasNext();) {
    		MstInsentive mstInsentive = (MstInsentive) ite.next();
    		
    		if (!isNull(mstInsentive.getInsentive1())) {
    			MstInsentive mstInsentive1 = new MstInsentive();
    			mstInsentive1.setInsentive1(mstInsentive.getInsentive1());
    			mstInsentive1.setInsentiveName1(mstInsentive.getInsentiveName1());
    			mstInsentive1.setInsentive1Sta(mstInsentive.getInsentive1Sta());
    			mstInsentive1.setInsentive1End(mstInsentive.getInsentive1End());
    			mstInsentive1.setInsentive1Note(mstInsentive.getInsentive1Note());
    			list.add(mstInsentive1);
    		}
    		
    		if (!isNull(mstInsentive.getInsentive2())) {
    			MstInsentive mstInsentive2 = new MstInsentive();
    			mstInsentive2.setInsentive1(mstInsentive.getInsentive2());
    			mstInsentive2.setInsentiveName1(mstInsentive.getInsentiveName2());
    			mstInsentive2.setInsentive1Sta(mstInsentive.getInsentive2Sta());
    			mstInsentive2.setInsentive1End(mstInsentive.getInsentive2End());
    			mstInsentive2.setInsentive1Note(mstInsentive.getInsentive2Note());
    			list.add(mstInsentive2);
    		}
    		
    		if (!isNull(mstInsentive.getInsentive3())) {
    			MstInsentive mstInsentive3 = new MstInsentive();
    			mstInsentive3.setInsentive1(mstInsentive.getInsentive3());
    			mstInsentive3.setInsentiveName1(mstInsentive.getInsentiveName3());
    			mstInsentive3.setInsentive1Sta(mstInsentive.getInsentive3Sta());
    			mstInsentive3.setInsentive1End(mstInsentive.getInsentive3End());
    			mstInsentive3.setInsentive1Note(mstInsentive.getInsentive3Note());
    			list.add(mstInsentive3);
    		}
    		
    		if (!isNull(mstInsentive.getInsentive4())) {
    			MstInsentive mstInsentive4 = new MstInsentive();
    			mstInsentive4.setInsentive1(mstInsentive.getInsentive4());
    			mstInsentive4.setInsentiveName1(mstInsentive.getInsentiveName4());
    			mstInsentive4.setInsentive1Sta(mstInsentive.getInsentive4Sta());
    			mstInsentive4.setInsentive1End(mstInsentive.getInsentive4End());
    			mstInsentive4.setInsentive1Note(mstInsentive.getInsentive4Note());
    			list.add(mstInsentive4);
    		}
    		
    		if (!isNull(mstInsentive.getInsentive5())) {
    			MstInsentive mstInsentive5 = new MstInsentive();
    			mstInsentive5.setInsentive1(mstInsentive.getInsentive5());
    			mstInsentive5.setInsentiveName1(mstInsentive.getInsentiveName5());
    			mstInsentive5.setInsentive1Sta(mstInsentive.getInsentive5Sta());
    			mstInsentive5.setInsentive1End(mstInsentive.getInsentive5End());
    			mstInsentive5.setInsentive1Note(mstInsentive.getInsentive5Note());
    			list.add(mstInsentive5);
    		}
    		
    	}
    	
    	// インセンティブコード、開始日をキーにソート
    	Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
                String val1 = ((MstInsentive) obj1).getInsentive1()
                            + ((MstInsentive) obj1).getInsentive1Sta();
                
                String val2 = ((MstInsentive) obj2).getInsentive1()
                            + ((MstInsentive) obj2).getInsentive1Sta();
                
                return val1.compareTo(val2);
            }
    	};
    	Collections.sort(list, sortComparator);
    	
    	// インセンティブコード、開始日をキーに重複を削除
    	String oldKey = "";
    	String newKey = "";
    	for (Iterator ite = list.iterator(); ite.hasNext();) {
    		MstInsentive mstInsentive = (MstInsentive) ite.next();
    		newKey = mstInsentive.getInsentive1() + mstInsentive.getInsentive1Sta();
    		
    		if (oldKey.equals(newKey)) {
    			ite.remove();
    		}
    		else {
    			oldKey = newKey;
    		}
    	}
    	return list;
    }

    /*
     * イベント分類一覧リスト作成
     */
    private List makeEventBunruiList(List listMstEventStatus) {
    	List listKey = new ArrayList();
    	List list = new ArrayList();
    	for (Iterator ite = listMstEventStatus.iterator(); ite.hasNext();) {
    		MstEventStatus entity = (MstEventStatus) ite.next();
    		if (!listKey.contains(entity.getEventBnrui()) 
    					&& entity.getEventBnrui() != null) 
    		{
    			listKey.add(entity.getEventBnrui());
    			list.add(entity);
    		}
    	}

    	// インセンティブコード、開始日をキーにソート
    	Comparator sortComparator = new Comparator() {
            public boolean equals(Object obj) {
                return (super.equals(obj));
            }
            public int compare(Object obj1, Object obj2) {
            	String key1 = ((MstEventStatus) obj1).getEventBnrui();
            	String key2 = ((MstEventStatus) obj2).getEventBnrui();
                return key1.compareTo(key2);
            }
    	};
    	
    	Collections.sort(list, sortComparator);
    	
    	return list;
    }
    /**
     * @return mstMiseDao を戻します。
     */
    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }
    /**
     * @param mstMiseDao mstMiseDao を設定。
     */
    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }
    
    private void validate(String miseCd, String companyCd) {
        //コードバリデーター
        CodeVerifier codeVerifier = new CodeVerifier(5, false);
        
        // 会社コード
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード");
        }
        if (!codeVerifier.validate(companyCd)) {
        	throw new InvalidInputException("会社コード");
        }

        // 店コード
        if (isNull(miseCd)) {
            throw new NotNullException("店コード", "condMiseCd", null);
        }
        if (!codeVerifier.validate(miseCd)) {
        	throw new InvalidInputException("店コード", "condMiseCd", null);
        }

    }
    
    // 
    private boolean isNull(String str) {
        return (str == null || "".equals(str.trim())) ? true : false;
    }
    /**
     * @return codInsentiveCdDao を戻します。
     */
    public CodInsentiveCdDao getCodInsentiveCdDao() {
        return codInsentiveCdDao;
    }
    /**
     * @param codInsentiveCdDao codInsentiveCdDao を設定。
     */
    public void setCodInsentiveCdDao(CodInsentiveCdDao codInsentiveCdDao) {
        this.codInsentiveCdDao = codInsentiveCdDao;
    }
    /**
     * @return mstBukkenDao を戻します。
     */
    public MstBukkenDao getMstBukkenDao() {
        return mstBukkenDao;
    }
    /**
     * @param mstBukkenDao mstBukkenDao を設定。
     */
    public void setMstBukkenDao(MstBukkenDao mstBukkenDao) {
        this.mstBukkenDao = mstBukkenDao;
    }
    /**
     * @return mstChintaiDao を戻します。
     */
    public MstChintaiDao getMstChintaiDao() {
        return mstChintaiDao;
    }
    /**
     * @param mstChintaiDao mstChintaiDao を設定。
     */
    public void setMstChintaiDao(MstChintaiDao mstChintaiDao) {
        this.mstChintaiDao = mstChintaiDao;
    }
    /**
     * @return mstEventStatusDao を戻します。
     */
    public MstEventStatusDao getMstEventStatusDao() {
        return mstEventStatusDao;
    }
    /**
     * @param mstEventStatusDao mstEventStatusDao を設定。
     */
    public void setMstEventStatusDao(MstEventStatusDao mstEventStatusDao) {
        this.mstEventStatusDao = mstEventStatusDao;
    }
    /**
     * @return mstGyotaiDao を戻します。
     */
    public MstGyotaiDao getMstGyotaiDao() {
        return mstGyotaiDao;
    }
    /**
     * @param mstGyotaiDao mstGyotaiDao を設定。
     */
    public void setMstGyotaiDao(MstGyotaiDao mstGyotaiDao) {
        this.mstGyotaiDao = mstGyotaiDao;
    }
    /**
     * @return mstHDCDao を戻します。
     */
    public MstHDCDao getMstHDCDao() {
        return mstHDCDao;
    }
    /**
     * @param mstHDCDao mstHDCDao を設定。
     */
    public void setMstHDCDao(MstHDCDao mstHDCDao) {
        this.mstHDCDao = mstHDCDao;
    }
    /**
     * @return mstInsentiveDao を戻します。
     */
    public MstInsentiveDao getMstInsentiveDao() {
        return mstInsentiveDao;
    }
    /**
     * @param mstInsentiveDao mstInsentiveDao を設定。
     */
    public void setMstInsentiveDao(MstInsentiveDao mstInsentiveDao) {
        this.mstInsentiveDao = mstInsentiveDao;
    }
    /**
     * @return mstMiseKaisoDao を戻します。
     */
    public MstMiseKaisoDao getMstMiseKaisoDao() {
        return mstMiseKaisoDao;
    }
    /**
     * @param mstMiseKaisoDao mstMiseKaisoDao を設定。
     */
    public void setMstMiseKaisoDao(MstMiseKaisoDao mstMiseKaisoDao) {
        this.mstMiseKaisoDao = mstMiseKaisoDao;
    }
    /**
     * @return mstMiseYobiDao を戻します。
     */
    public MstMiseYobiDao getMstMiseYobiDao() {
        return mstMiseYobiDao;
    }
    /**
     * @param mstMiseYobiDao mstMiseYobiDao を設定。
     */
    public void setMstMiseYobiDao(MstMiseYobiDao mstMiseYobiDao) {
        this.mstMiseYobiDao = mstMiseYobiDao;
    }
    /**
     * @return mstSBDao を戻します。
     */
    public MstSBDao getMstSBDao() {
        return mstSBDao;
    }
    /**
     * @param mstSBDao mstSBDao を設定。
     */
    public void setMstSBDao(MstSBDao mstSBDao) {
        this.mstSBDao = mstSBDao;
    }
    /**
     * @return mstTODao を戻します。
     */
    public MstTODao getMstTODao() {
        return mstTODao;
    }
    /**
     * @param mstTODao mstTODao を設定。
     */
    public void setMstTODao(MstTODao mstTODao) {
        this.mstTODao = mstTODao;
    }
    /**
     * @return trnURLDao を戻します。
     */
    public TrnURLDao getTrnURLDao() {
        return trnURLDao;
    }
    /**
     * @param trnURLDao trnURLDao を設定。
     */
    public void setTrnURLDao(TrnURLDao trnURLDao) {

        this.trnURLDao = trnURLDao;
    }
    
// 20060606追加 start ----------------------------------------    
    /**
     * @return mstStaffDao を戻します。
     */
    public MstStaffDao getMstStaffDao() {
        return mstStaffDao;
    }
    /**
     * @param mstStaffDao mstStaffDao を設定。
     */
    public void setMstStaffDao(MstStaffDao mstStaffDao) {
        this.mstStaffDao = mstStaffDao;
    }
// 20060606追加 end -----------------------------------------    
}