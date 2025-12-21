package jp.co.isid.mos.bird.bizreport.urimaintenance.logic.impl;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.SyukeiKbnResultData;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.TrnSyuseiAridaka;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIMeisaiReviseInf;
import jp.co.isid.mos.bird.bizreport.common.urimaintenance.entity.UIUriMaintenanceResult;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceCommon;
import jp.co.isid.mos.bird.bizreport.urimaintenance.common.UriMaintenanceConstants;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.GetKeigenTaxDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.TrnADUPDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.TrnNBUPDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dao.TrnSyuseiAridakaDao;
import jp.co.isid.mos.bird.bizreport.urimaintenance.dto.UriMaintenanceDto;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.GetKeigenTaxData;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.TrnADUPInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.TrnNBUPInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.entity.UIUriMainteWorkInfo;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.KaikeiKbnSearchLogic;
import jp.co.isid.mos.bird.bizreport.urimaintenance.logic.RegistUriMaintenanceLogic;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlSyukeiKbn;
import jp.co.isid.mos.bird.common.kaikei.entity.GetterMethod;
import jp.co.isid.mos.bird.common.kaikei.util.KaikeiUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 売上修正登録ロジック
 * @author Aspac
 *
 * 更新日:2012/07/27 xkinu 会計区分追加対応 BD30KKUPテーブル(Dao【会計区分別在高修正】)登録処理追加
 *
 * 更新：2007/04/09 ％値引金額、マイナス商品金額、値引金額、値引計の更新ロジック変更
 * 　　　　　　　　 　参照）PJ-320602-002.01 全項目更新テストでの問題点.xls
 * 更新日:2013/04/22 周春建　201304-001_売上修正確認画面の集計区分の修正値表示の改修追加
 */
public class RegistUriMaintenanceLogicImpl implements RegistUriMaintenanceLogic {


    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBR008L04";

    /** MAP key値を定義 */
    public static final String KAIKEN_CHANGE = "kaiKenChange" ;
    public static final String KAIKIN_CHANGE = "kaiKinChange" ;
    public static final String REGIST_DATA_LIST = "registDat" ;
//add 2019/10/11 USI欒 #34 begin
    public static final String KAIKENTAX_CHANGE = "kaiKenTaxChange" ;
    public static final String REGIST_BD66DATA_LIST = "registBD66Data" ;
//add 2019/10/11 USI欒 #34 end

    /** LOGIC【会計区分売上修正検索】*/
    private KaikeiKbnSearchLogic uriMaintenanceKaikeiKbnSearchLogic;
    /**
     * 現金在高日次修正Dao
     */
    private TrnADUPDao trnADUPDao;

//add 2019/07/15 USI欒 #34 begin
    /** 現金在高日次売上消費税明細修正Dao
     *  *
     */
    private GetKeigenTaxDao keigenTaxDao;
	/** Dao【会計区分別在高日別修正】*/
	private GetKeigenTaxDao uriMaintenanceGetKeigenTaxDao;
//add 2019/07/15 USI欒 #34 end

    /**
     * 取引修正Dao
     */
    private TrnNBUPDao trnNBUPDao;

	/** Dao【会計区分別在高修正】*/
	private TrnSyuseiAridakaDao uriMaintenanceTrnSyuseiAridakaDao;
    /**
     *
     * 更新日:2012/07/27 xkinu 会計区分追加対応 BD30KKUPテーブル(Dao【会計区分別在高修正】)登録処理追加
     */
	public void execute(UriMaintenanceDto sessionDto) {
        List listUri = sessionDto.getListUri();
        List listUriPre = sessionDto.getListUriPre();
        List listUriTmp = sessionDto.getListUriTmp();
        String user = sessionDto.getUserId();
        //売上修正登録処理
        // 変数を共有するため、「売上修正登録処理」から「会計区分別在高日別修正データ登録処理」を呼び出す。
//        List listRegistSEigyoDt = execute(listUri, listUriTmp, listUriPre, user);
        execute(listUri,listUriTmp, listUriPre, user, sessionDto);
// Begin DEL By 周春建 201304-001_売上修正確認画面の集計区分の修正値表示の改修追加のため
//        //会計区分別在高日別修正データ登録処理
//        List listRegistKkbn = executeKKbn(sessionDto, listRegistSEigyoDt);
//        //変更なし売上修正登録処理(会計区分別在り高内で修正があった場合の対応)
//        for(int k=0; k<listRegistKkbn.size(); k++) {
//    		//for-1.List[[登録データ]]から現行インデックスTrnSyuseiAridaka[登録データ]を取得します。
//        	TrnSyuseiAridaka eRegist = (TrnSyuseiAridaka)listRegistKkbn.get(k);
//        	String insertEityoDt = eRegist.getEigyoDt();
//        	if(listRegistSEigyoDt.indexOf(eRegist.getEigyoDt()) < 0) {
//                for(int i=0; i<listUri.size(); i++) {
//
//                    UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo) listUri.get(i);
//                    UIUriMainteWorkInfo entityTmp = (UIUriMainteWorkInfo) listUriTmp.get(i);
//
//                    //合計レコードは更新対象外
//                    if(entity.isTotalFlg() || !insertEityoDt.equals(entity.getEigyoDt()) || !entity.isInsertFlg()) {
//                        continue;
//                    }
//
//                    //更新項目No取得
//                    String upNoUri = comparePropertyUri(entity, entityTmp);
//                    String upNoTori = comparePropertyTori(entity, entityTmp);
//
//                    TrnADUPInfo uriEntity  = setUriCopy(entity, upNoUri, user);
//                    TrnNBUPInfo toriEntity = setToriCopy(entity, upNoTori, user);
//
//                    if(entity.isInsertFlg()) {
//                        getTrnADUPDao().insert(uriEntity);
//                        getTrnNBUPDao().insert(toriEntity);
//                    }
//                }
//        	}
//        }
//      End DEL By 周春建 201304-001_売上修正確認画面の集計区分の修正値表示の改修追加のため
	}
    /**
     * 売上修正登録処理を行う
     *
     * @param listUri 売上修正リスト
     * @param listUriTmp 売上リスト
     * @param listUriPre 売上修正前比較リスト
     * @param user ユーザID
     * @param sessionDto DTO
     */
    //private List execute(List listUri, List listUriTmp, List listUriPre, String user) {
	private void execute(List listUri, List listUriTmp, List listUriPre, String user, UriMaintenanceDto sessionDto) {
    	//０．List[[新規登録対象営業日]]を生成します。
    	List listRegistEigyoDt = new ArrayList(0);

    	// 新規登録対象営業日Listを取得する
    	listRegistEigyoDt = getListRegistEigyoDt(listUri, listUriPre);

		// 会計区分別在高日別修正データ登録処理
    	Map templistRegistKkbn = executeKKbn(sessionDto, listRegistEigyoDt);
//add 2019/07/15 USI欒 #34 begin
    	//現金在高（日次）売上消費税明細データ登録処理
    	Map templistRegistBD66Kkbn = executeDB66(sessionDto, user);
    	List listRegistBD66Kkbn = (List)templistRegistBD66Kkbn.get(REGIST_BD66DATA_LIST);
//add 2019/07/15 USI欒 #34 end
		List listRegistKkbn = (List)templistRegistKkbn.get(REGIST_DATA_LIST);
		// 件数の修正があった集計Listを取得する
		Map tempKaikenSyukeiMap = (Map)templistRegistKkbn.get(KAIKEN_CHANGE);
		// 金額の修正があった集計Listを取得する
		Map tempKaikinSyukeiMap = (Map)templistRegistKkbn.get(KAIKIN_CHANGE);

		//--------------------
        // DB更新処理
        //--------------------
        for(int i=0; i<listUri.size(); i++) {

            UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo) listUri.get(i);
            UIUriMainteWorkInfo entityPre = (UIUriMainteWorkInfo) listUriPre.get(i);
            UIUriMainteWorkInfo entityTmp = (UIUriMainteWorkInfo) listUriTmp.get(i);
            //合計レコードは更新対象外
            if(entity.isTotalFlg()) {
                continue;
            }

            //修正前のデータと比較し、修正の有無をチェック
            boolean flg1 = comparePropertyUriPre(entity, entityPre);
            boolean flg2 = comparePropertyToriPre(entity, entityPre);

            //会計区分別在高日別修正の有無をチェック
            boolean flg3 = false;
            for (int j=0; j<listRegistKkbn.size(); j++) {
            	TrnSyuseiAridaka eRegist = (TrnSyuseiAridaka)listRegistKkbn.get(j);
            	if (entity.getEigyoDt().equals(eRegist.getEigyoDt())) {
            		flg3=true;
            		break;
            	}
            }

//add 2019/10/11 USI欒 #34 begin
            //売上消費税在高日別修正の有無をチェック
            boolean flg4 = false;
			if (listRegistBD66Kkbn != null) {
				for (int m = 0; m < listRegistBD66Kkbn.size(); m++) {
					GetKeigenTaxData eBD66Regist = (GetKeigenTaxData) listRegistBD66Kkbn.get(m);
					if (entity.getEigyoDt().equals(eBD66Regist.getEigyoDt())) {
						flg4 = true;
						break;
					}
				}
			}
			//add 2019/10/11 USI欒 #34 end

//modify 2019/10/11 USI欒 #34 begin
            //修正前と比較して、修正がなければ更新しない
            //if( flg1 == false && flg2 == false && flg3==false) {
            if( flg1 == false && flg2 == false && flg3==false && flg4==false) {
//modify 2019/10/11 USI欒 #34 end
                continue;
            }
            //更新項目No取得
            //String upNoUri = comparePropertyUri(entity, entityTmp);
            String upNoUri = comparePropertyUri(entity, entityTmp, tempKaikenSyukeiMap, tempKaikinSyukeiMap);
            String upNoTori = comparePropertyTori(entity, entityTmp);

            //---2007/04/09 add start
            //値引き項目の対応
            if (doNebikiProcess(entity, upNoTori)) {
                //upNoUri = comparePropertyUri(entity, entityTmp);
            	upNoUri = comparePropertyUri(entity, entityTmp, tempKaikenSyukeiMap, tempKaikinSyukeiMap);
            }
            //---2007/04/09 add end


            TrnADUPInfo uriEntity  = setUriCopy(entity, upNoUri, user);
            TrnNBUPInfo toriEntity = setToriCopy(entity, upNoTori, user);

            if(entity.isInsertFlg()) {
                getTrnADUPDao().insert(uriEntity);
                getTrnNBUPDao().insert(toriEntity);
                //List[[新規登録対象営業日]]へ新規登録された営業日を保持します。
//                listRegistEigyoDt.add(uriEntity.getEigyoDt());
            }

            else {
                getTrnADUPDao().update(uriEntity);
                getTrnNBUPDao().update(toriEntity);

            }

        }
//        return listRegistEigyoDt;
    }

	/**
     * 会計区分別在高日別修正データ登録処理
     *
     * @param sessionDto DTO【Session情報】
     * @param listRegistEigyoDt List[[集計情報登録対象営業日]]
     */
    //private List executeKKbn(UriMaintenanceDto sessionDto, List listRegistEigyoDt){
    private Map executeKKbn(UriMaintenanceDto sessionDto, List listRegistEigyoDt){
        //０．集計区分情報に変更があり、DTO【Session情報】.Map[集計区分別会計区分結果情報]が空の場合、
        if(sessionDto.getMapSKbnResultData().isEmpty()) {
        	if(listRegistEigyoDt.size()>0) {
	    	   	//LOGIC【会計区分売上修正検索】を実行する。
	    	   	getUriMaintenanceKaikeiKbnSearchLogic().execute(sessionDto);
        	}
        	else {
//        		return new ArrayList(0);
        		Map tempListRegistDat = new LinkedHashMap();
        		tempListRegistDat.put(REGIST_DATA_LIST,new ArrayList(0));
        		return tempListRegistDat;
        	}
        }
    	//１．メソッド【会計区分別在高日別修正登録対象取得処理】を実行し、List[[登録データ]]を取得します。
//    	List listRegistData = remakeListRegistData(sessionDto, listRegistEigyoDt);
        Map tempListRegistDat = remakeListRegistData(sessionDto, listRegistEigyoDt);
        List listRegistData = (List)tempListRegistDat.get(REGIST_DATA_LIST);
    	//２．現在の時刻のTimestamp[タイムスタンプ]を生成します。
    	Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
    	/*** 登録処理実行 ***/
    	//３．List[[登録データ]]の件数分下記の登録処理を行います。
    	for(int r=0; r<listRegistData.size(); r++) {
    		//for-1.List[[登録データ]]から現行インデックスTrnSyuseiAridaka[登録データ]を取得します。
        	TrnSyuseiAridaka eRegist = (TrnSyuseiAridaka)listRegistData.get(r);
    		//for-2.TrnSyuseiAridaka[登録データ].バッチ更新フラグへ"0"(初期状態)を設定します。
        	eRegist.setUpflg(UriMaintenanceConstants.UP_FLG_UPD);//バッチ更新フラグ
        	//for-3.TrnSyuseiAridaka[登録データ].更新プログラムへ”BBR008”を設定します。
        	eRegist.setLastPgm(LOGIC_ID.substring(0, 6));
        	//for-4.TrnSyuseiAridaka[登録データ].更新ユーザーへDTO【Session情報】.ユーザーIDを設定します。
        	eRegist.setLastUser(sessionDto.getUserId());
        	//for-5.TrnSyuseiAridaka[登録データ].登録ユーザーがnull又は""(空)の場合は、
        	//      下記の値を設定しINSERT処理を実行します。
    		if( CommonUtil.isNull(eRegist.getFirstUser()) ) {
    			//1.TrnSyuseiAridaka[登録データ].バッチ更新日付へ""(空)を設定します。
            	eRegist.setBatupDt("");
            	//2.TrnSyuseiAridaka[登録データ].更新日付へDTO【Session情報】.システム日付を設定します。
            	eRegist.setUpDt(sessionDto.getSysDate());
            	//3.TrnSyuseiAridaka[登録データ].登録ログラムへ”BBR008”を設定します。
            	eRegist.setFirstPgm(LOGIC_ID.substring(0, 6));
            	//4.TrnSyuseiAridaka[登録データ].登録タイムスタンプへ処理２のTimestamp[タイムスタンプ]を設定します。
            	eRegist.setFirstTmsp(currentTimestamp);
            	//5.TrnSyuseiAridaka[登録データ].登録ユーザーへDTO【Session情報】.ユーザーIDを設定します。
            	eRegist.setFirstUser(sessionDto.getUserId());
    			//新規登録実行
	    		getUriMaintenanceTrnSyuseiAridakaDao().insert(eRegist);
	    	}
	    	else {
	    		//更新登録実行
	    		getUriMaintenanceTrnSyuseiAridakaDao().update(eRegist);
	    	}
    	}
    	//return listRegistData;
    	return tempListRegistDat;
    }
    /**
     * 会計区分別在高日別修正登録対象取得処理
     *
     * @param sessionDto
     * @param listRegistEigyoDt List[[集計情報登録対象営業日]]
     * @return List[[登録データ]]
     */
    //private List remakeListRegistData(UriMaintenanceDto sessionDto, List listRegistEigyoDt) {
    private Map remakeListRegistData(UriMaintenanceDto sessionDto, List listRegistEigyoDt) {
    	//０．リターン値List[[登録データ]]を生成します。
    	List listRegistData = new ArrayList(0);
    	List listSearchData = sessionDto.getListKKbnSearchData();
    	List listMstSKbn = sessionDto.getListSyukeiKbn();
    	//１．Map[登録対象Entity保持]を生成します。
    	Map mapRegistData = new LinkedHashMap();//日別登録データ
    	//　Map[変化の件数対応の集計コードList]を生成します。
    	Map tempKaikenSyukeiMap = new LinkedHashMap();
    	//　Map[変化の金額対応の集計コードList]を生成します。
    	Map tempKaikinSyukeiMap = new LinkedHashMap();
    	Map syuKeicdChange = new LinkedHashMap();
    	/*** 登録対象エンティティ値変更と抜粋 ***/
    	//２．DTO【Session情報】.List[[集計区分マスタ情報]]件数分下記の処理を行います。
    	for(int s=0; s<listMstSKbn.size(); s++) {
    		//for1-1.DTO【Session情報】.List[[集計区分マスタ情報]]から現行行[集計区分マスタ情報]を取得します。
    		CtlSyukeiKbn eSkbn = (CtlSyukeiKbn)listMstSKbn.get(s);
    		//for1-2.処理for1-1の[集計区分マスタ情報].集計区分コードを取得します。
    		String skcd = eSkbn.getSyukeiCd();
    		//for1-3.処理for1-2の集計区分コードをキーにDTO【Session情報】.Map[集計区分別会計区分結果情報]からSyukeiKbnResultData[集計区分結果情報]を取得します。
    		SyukeiKbnResultData skbnResultData = (SyukeiKbnResultData)sessionDto.getMapSKbnResultData().get(skcd);
    		//for1-4.DTO【Session情報】.Map[集計区分別会計区分情報]からDTO【Session情報】．集計区分をキーにUIList[集計区分情報]を取得します。
    		UILists uiSyukeiKbn = (UILists)sessionDto.getMapSyukeiKbnMstData().get(skcd);
    		//for1-5.変数Map[Getメソッド]へ会計区分系共通静的クラス.メソッド【件数・金額Getterメソッド情報取得処理】を実行し、取得した値を設定します。
        	Map mapGetMethod = KaikeiUtil.getMapGetterMethod(uiSyukeiKbn.getListData(), TrnSyuseiAridaka.class, "Kaikei");
        	//for1-6.変数Map[Setメソッド]へ会計区分系共通静的クラス.メソッド【件数・金額メソッド情報取得処理】を実行し、取得した値を設定します。
        	Map mapSetMethod = KaikeiUtil.getMapMethod(uiSyukeiKbn.getListData(), TrnSyuseiAridaka.class, "Kaikei", "set");
        	//for1-7.変数List[[修正データ]]へSyukeiKbnResultData[集計区分結果情報].List[[タブ別結果情報]]を設定します。
	    	List listTabReviseData  = skbnResultData.getListTabResult();
	    	//for1-8.処理for1-7の変数List[[修正データ]]件数分下記の処理を行います。
	    	for(int t=0; t<listTabReviseData.size(); t++) {
	    		//for2-1.変数List[[修正データ]]から現行行UIUriMaintenanceResult[タブ別UIListDataHeader情報]を取得します。
	    		UIUriMaintenanceResult resultData = (UIUriMaintenanceResult) listTabReviseData.get(t);
	    		//for2-2.UIUriMaintenanceResult[タブ別UIListDataHeader情報].List[[データ]]件数分下記の処理を行います。
		    	for(int d=0; d<resultData.getListData().size(); d++) {
		    		//for3-1.DTO【Session情報】.List[[会計区分別在高日次修正]]から現行行TrnSyuseiAridaka[検索データ]を取得します。
		        	TrnSyuseiAridaka eBD31data = (TrnSyuseiAridaka)listSearchData.get(d);
		        	//for3-2.TrnSyuseiAridaka[検索データ]営業日がnullの場合は合計行のため処理for2-1へ処理をスキップします。
		        	if(CommonUtil.isNull(eBD31data.getEigyoDt())) {
		        		//処理for2-1へ処理をスキップします。
		        		continue;
		        	}
		        	//for3-3.TrnSyuseiAridaka[検索データ]更新項目ＮＯがnullの場合は”０”が178個並んだ文字列を生成し
		        	if(CommonUtil.isNull(eBD31data.getUpNo())) {
		        		StringBuffer sb = new StringBuffer();
		        		for(int u=0; u<178; u++) {
		        			sb.append("0");
		        		}
		        		//その値をTrnSyuseiAridaka[検索データ]更新項目ＮＯへ設定します。
		        		eBD31data.setUpNo(sb.toString());
		        	}
		        	//for3-4.TrnSyuseiAridaka[検索データ]更新項目ＮＯをStringBufferへ変換した値を変数StringBuffer[更新項目ＮＯ]へ設定します。
		        	StringBuffer sbUpNo = new StringBuffer(eBD31data.getUpNo());
		        	//for3-5.UIUriMaintenanceResult[タブ別UIListDataHeader情報].List[[データ]]から現行行UIMeisaiReviseInf[編集データ]を取得します。
		        	UIMeisaiReviseInf eRevise = (UIMeisaiReviseInf)resultData.getListData().get(d);
		        	//for3-6.UIUriMaintenanceResult[タブ別UIListDataHeader情報].List[[ヘッダー]]件数分下記の処理を行います。
		    		for(int k=0; k<resultData.getListHeader().size(); k++) {
		    			//for4-1.UIUriMaintenanceResult[タブ別UIListDataHeader情報].List[[ヘッダー]]から現行行UILists[会計区分情報]を取得します。
		    			UILists uiKaikei = (UILists)resultData.getListHeader().get(k);
		    			//for4-2.変数.会計区分コードへUILists[会計区分情報].キーの値を設定します。
		    			String kkcd = uiKaikei.getKey();
		    			//for4-3.変数.int[件数位置]へ変数.会計区分コードをintへ変換した値から-2した値を設定します。
		    			int indexKen = (Integer.valueOf(kkcd).intValue()-2)*2;
		    			//for4-4.変数.int[金額位置]へ変数.int[件数位置]＋1の値を設定します。
		    			int indexKin = indexKen+1;
		    			//for4-5.変数[Getメソッド]へ変数.会計区分コードキーに処理for1-5の変数Map[Getメソッド]から取得した値を設定します。
		    			GetterMethod getMethods = (GetterMethod)mapGetMethod.get(kkcd);
		    			//for4-6.変数[Setメソッド]へ変数.会計区分コードキーに処理for1-5の変数Map[Setメソッド]から取得した値を設定します。
		    			GetterMethod setMethods = (GetterMethod)mapSetMethod.get(kkcd);
		    			try {
							//for4-7.変数.dec[件数]へ GetterMethod[Getterメソッド].件数Methodを実行した値を設定します。
							BigDecimal decKen = (BigDecimal)getMethods.getMethodKen().invoke(eBD31data, new Object[]{});
							//for4-8.変数.dec[金額]へ GetterMethod[Getterメソッド].金額Methodを実行した値を設定します。
							BigDecimal decKin = (BigDecimal)getMethods.getMethodKin().invoke(eBD31data, new Object[]{});
							//for4-9.変数.dec[修正件数]へ nullを設定します。
							BigDecimal decReviseKen = null;
							//for4-10.変数.dec[修正金額]へ nullを設定します。
							BigDecimal decReviseKin = null;
							//for4-11.変数.boolean[件数変更]へfalseを設定します。
							boolean changeKen = false;
							//for4-12.変数.boolean[金額変更]へfalseを設定します。
							boolean changeKin = false;

							//for4-13.処理for4の現行インデックス==0(ゼロ)の場合
							if(k==0) {
								//(1).変数.dec[修正件数]へ処理for3-5のUIMeisaiReviseInf[編集データ].件数1修正値を設定します。
								decReviseKen = eRevise.getReviseKen1();
								//(2).変数.dec[修正金額]へ処理for3-5のUIMeisaiReviseInf[編集データ].金額1修正値を設定します。
								decReviseKin = eRevise.getReviseKin1();
								//(3).変数.boolean[件数変更]へ変数.dec[修正件数]の値と
								//    UIMeisaiReviseInf[編集データ].明細件数１(会計区分別在高(BD30KKAD))の値と比較し
								//    同値の場合はfalseをそうでない場合はtrueを設定します。
								changeKen = decReviseKen.compareTo(eRevise.getMeisaiKen1())!=0;
								//(4).変数.boolean[金額変更]へ変数.dec[修正金額]の値と
								//    UIMeisaiReviseInf[編集データ].明細金額１(会計区分別在高(BD30KKAD))の値と比較し
								//    同値の場合はfalseをそうでない場合はtrueを設定します。
								changeKin = decReviseKin.compareTo(eRevise.getMeisaiKin1())!=0;
							}//end of if(k==0)
							//for4-14.処理for4の現行インデックス==1の場合
							else if(k==1) {
								decReviseKen = eRevise.getReviseKen2();
								decReviseKin = eRevise.getReviseKin2();
								//会計区分別在高(BD30KKAD)の値と比較します。
								changeKen = decReviseKen.compareTo(eRevise.getMeisaiKen2())!=0;
								changeKin = decReviseKin.compareTo(eRevise.getMeisaiKin2())!=0;
							}//end of if(k==1)
							//for4-15.処理for4の現行インデックス==2の場合
							else if(k==2) {
								decReviseKen = eRevise.getReviseKen3();
								decReviseKin = eRevise.getReviseKin3();
								//会計区分別在高(BD30KKAD)の値と比較します。
								changeKen = decReviseKen.compareTo(eRevise.getMeisaiKen3())!=0;
								changeKin = decReviseKin.compareTo(eRevise.getMeisaiKin3())!=0;
							}//end of if(k==2)
							//for4-16.処理for4の現行インデックス==3の場合
							else if(k==3) {
								decReviseKen = eRevise.getReviseKen4();
								decReviseKin = eRevise.getReviseKin4();
								//会計区分別在高(BD30KKAD)の値と比較します。
								changeKen = decReviseKen.compareTo(eRevise.getMeisaiKen4())!=0;
								changeKin = decReviseKin.compareTo(eRevise.getMeisaiKin4())!=0;
							}//end of if(k==3)

							//for4-17.処理for4-7.変数.dec[件数]と変数.dec[修正件数]の値が違う場合は、
							//        処理for3-1TrnSyuseiAridaka[検索データ]は登録対象と判断し下記の処理を行います。
							if(decKen.compareTo(decReviseKen) != 0 || listRegistEigyoDt.indexOf(eBD31data.getEigyoDt()) >=0) {
								//(1).変数[Setメソッド].件数を実行し、TrnSyuseiAridaka[検索データ]．会計区分件数へ変数.dec[件数]を設定します。
								setMethods.getMethodKen().invoke(eBD31data, new Object[]{decReviseKen});
								//(2).Map[登録対象Entity保持]へ営業日をキーにTrnSyuseiAridaka[検索データ]を格納します。
								mapRegistData.put(eBD31data.getEigyoDt(), eBD31data);
								//(3).処理for-3-4の変数StringBuffer[更新項目ＮＯ]の変数.int[件数位置]の位置へ変数.boolean[件数変更]がtrueの場合は'1'を、そうでない場合は'0'を上書きします。
								sbUpNo.setCharAt(indexKen, changeKen?'1':'0');
					    		//(4).TrnSyuseiAridaka[検索データ].更新項目ＮＯへ変数StringBuffer[更新項目ＮＯ]を設定します。
					    		eBD31data.setUpNo(sbUpNo.toString());
							}
							//for4-18.処理for4-8.変数.dec[金額]と変数.dec[修正金額]の値が違う場合は、
							//        処理for3-1TrnSyuseiAridaka[検索データ]は登録対象と判断し下記の処理を行います。
							if(decKin.compareTo(decReviseKin) != 0 || listRegistEigyoDt.indexOf(eBD31data.getEigyoDt()) >=0) {
								//(1).変数[Setメソッド].金額を実行し、TrnSyuseiAridaka[検索データ]．会計区分件数へ変数.dec[金額]を設定します。
								setMethods.getMethodKin().invoke(eBD31data, new Object[]{decReviseKin});
								//(2).Map[登録対象Entity保持]へ営業日をキーにTrnSyuseiAridaka[検索データ]を格納します。
								mapRegistData.put(eBD31data.getEigyoDt(), eBD31data);
								//(3).処理for-3-4の変数StringBuffer[更新項目ＮＯ]の変数.int[金額位置]の位置へ変数.boolean[金額変更]がtrueの場合は'1'を、そうでない場合は'0'を上書きします。
								sbUpNo.setCharAt(indexKin, changeKin?'1':'0');
					    		//(4).TrnSyuseiAridaka[検索データ].更新項目ＮＯへ変数StringBuffer[更新項目ＮＯ]を設定します。
					    		eBD31data.setUpNo(sbUpNo.toString());
							}
				    		// 件数の修正があった集計Listを取得するため、Temp　Map[件数の修正があった集計List]を作成する
				    		if ((changeKen)) {
			    				List tempList = new ArrayList(0);
			    				tempList.add(eBD31data.getEigyoDt());
			    				tempList.add(skcd);
			    				tempKaikenSyukeiMap.put(tempList, eBD31data.getEigyoDt());
				    		}
				    		// 金額の修正があった集計Listを取得するため、Temp　Map[金額の修正があった集計List]を作成する
				    		if ((changeKin)) {
			    				List tempList = new ArrayList(0);
			    				tempList.add(eBD31data.getEigyoDt());
			    				tempList.add(skcd);
			    				tempKaikinSyukeiMap.put(tempList, eBD31data.getEigyoDt());
				    		}
		    			}catch(Exception ex) {
		    				throw new FtlSystemException("売上修正画面：会計区分別在高日別修正データ登録","会計区分コード["+kkcd+"]");
		    			}
		    		}//end of for(int k=0; k<resultData.getListHeader().size(); k++)
		    	}// end of for(int d=0; d<resultData.getListData().size(); d++)
	    	}// end of for(int t=0; t<listTabReviseData.size(); t++)
    	}// end of for(int s=0; s<listMstSKbn.size(); s++)

    	// Temp　Map[件数の修正があった集計List]をMap[件数の修正があった集計List]に変換します。
    	Map kaiKenChange = syukeiKkbn(tempKaikenSyukeiMap);
    	// Temp　Map[金額の修正があった集計List]をMap[金額の修正があった集計List]に変換します。
    	Map kaiKinChange = syukeiKkbn(tempKaikinSyukeiMap);

    	//３．Map[登録対象Entity保持]のキー分下記の処理を行います。
    	for(Iterator it = mapRegistData.keySet().iterator(); it.hasNext();) {
    		//for-1.リターン値List[[登録データ]]へMap[登録対象Entity保持]から
    		//      現行キー(営業日)のオブジェクト(TrnSyuseiAridaka[検索データ])を格納します。
    		listRegistData.add(mapRegistData.get(it.next()));
    	}
    	//４．リターン値List[[登録データ]]をリターンします。
    	//return listRegistData;
    	syuKeicdChange.put(KAIKEN_CHANGE, kaiKenChange);
    	syuKeicdChange.put(KAIKIN_CHANGE, kaiKinChange);
    	syuKeicdChange.put(REGIST_DATA_LIST,listRegistData );
    	return syuKeicdChange;
    }

// add 2019/07/17 USI欒 #34 begin

    /**
     * 現金在高（日次）売上消費税明細データ登録処理
     *
     * @param sessionDto DTO【Session情報】
     */
    private Map executeDB66(UriMaintenanceDto sessionDto, String user){
      //修正前のデータｊを取得
    	//1.List[[修正状況検索結果]]を取得します。
    	List listSearchReviseData = getUriMaintenanceGetKeigenTaxDao().select(
    			sessionDto.getSysDate()
    			, sessionDto.getCondCompanyCd()
    			, sessionDto.getCondMiseCd()
    			, sessionDto.getCondTargetYm());

    	 GetKeigenTaxData getKeigenTaxDataEntity = new GetKeigenTaxData();
         GetKeigenTaxData getPreKeigenTaxDataEntity = new GetKeigenTaxData();
         GetKeigenTaxData getKeigenTaxEntity = new GetKeigenTaxData();
     	//１．Map[登録対象Entity保持]を生成します。
     	Map mapRegistBD66Data = new LinkedHashMap();
     	Map keigenTaxChange = new LinkedHashMap();
     	//０．リターン値List[[登録データ]]を生成します。
    	List listRegisBD66tData = new ArrayList(0);
         boolean flg  =false;
         boolean updateFlg = false;
         String upNoKeigenTax;
     	//現在の時刻のTimestamp[タイムスタンプ]を生成します。
     	Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
     	if(sessionDto.getListGetKeigenTaxData().size()>0) {
     		for(int i=0;i<listSearchReviseData.size()-1;i++) {
     			getPreKeigenTaxDataEntity = (GetKeigenTaxData)listSearchReviseData.get(i);
     			getKeigenTaxDataEntity = (GetKeigenTaxData)sessionDto.getListGetKeigenTaxData().get(i);

     			flg= compareKeigenTaxPre(getKeigenTaxDataEntity, getPreKeigenTaxDataEntity);
     			//画面にデータを修正
     			if(flg) {
     				updateFlg = true;
     				upNoKeigenTax = comparePropertyKeigenTax(getKeigenTaxDataEntity, getPreKeigenTaxDataEntity);
     				getKeigenTaxEntity = setKeigenTaxCopy(getKeigenTaxDataEntity, upNoKeigenTax, user);
     				mapRegistBD66Data.put(getKeigenTaxEntity.getEigyoDt(), getKeigenTaxEntity);
     				//登録ユーザーがnull又は""(空)の場合は、
     				//下記の値を設定しINSERT処理を実行します。
     				if( CommonUtil.isNull(getPreKeigenTaxDataEntity.getFirstUser()) ) {
     					//1.[登録データ].バッチ更新日付へ""(空)を設定します。
     					getKeigenTaxEntity.setBatupDt("");
     					//2.[登録データ].更新日付へDTO【Session情報】.システム日付を設定します。
     					getKeigenTaxEntity.setUpDt(sessionDto.getSysDate());
     					//3.[登録データ].登録ログラムへ”BBR008”を設定します。
     					getKeigenTaxEntity.setFirstPgm(LOGIC_ID.substring(0, 6));
     					//4.[登録データ].登録タイムスタンプへ処理２のTimestamp[タイムスタンプ]を設定します。
     					getKeigenTaxEntity.setFirstTmsp(currentTimestamp);
     					//5.[登録データ].登録ユーザーへDTO【Session情報】.ユーザーIDを設定します。
     					getKeigenTaxEntity.setFirstUser(sessionDto.getUserId());
     					//新規登録実行
     					getKeigenTaxDao().insert(getKeigenTaxEntity);
     				}else {
     					getKeigenTaxDao().update(getKeigenTaxEntity);
     				}
     			}
     		}
			//３．Map[登録対象Entity保持]のキー分下記の処理を行います。
	    	for(Iterator it = mapRegistBD66Data.keySet().iterator(); it.hasNext();) {
	    		//for-1.リターン値List[[登録データ]]へMap[登録対象Entity保持]から
	    		//      現行キー(営業日)のオブジェクト(GetKeigenTaxData[検索データ])を格納します。
	    		listRegisBD66tData.add(mapRegistBD66Data.get(it.next()));
	    	}
	    	keigenTaxChange.put(KAIKENTAX_CHANGE, mapRegistBD66Data);
	    	keigenTaxChange.put(REGIST_BD66DATA_LIST, listRegisBD66tData);
     	}
    	return keigenTaxChange;
    }
// add 2019/07/17 USI欒 #34 end

    /**
     * 項目ごとに修正されたかを判断する
     * 修正されている場合、upNoを'1'に更新し、trueを返す
     * 修正されていない場合、upNoを更新せず、falseを返す
     * @param elm1
     * @param elm2
     * @param flg
     * @return boolean
     */
    private boolean judgeUpNo(String elm1, String elm2, boolean flg){
        if(!elm1.equals(elm2)) {
            flg = true;
        }
        return flg;
    }


    /**
     * 修正データの有無をチェック
     * @param entity 修正後データ
     * @param entityPre 修正前データ
     * @return true:修正データがある false:ない
     */
    private boolean comparePropertyUriPre(UIUriMainteWorkInfo entity, UIUriMainteWorkInfo entityPre) {

        char[] upNo = entityPre.getUpNo97().toCharArray();

        for(int i=0; i<76; i++){
            if(upNo[i]!='0' && upNo[i]!='1'){
                upNo[i] = '0';
            }
        }

        boolean changeFlg = false;

        changeFlg = judgeUpNo(entityPre.getU01Uriage(),      entity.getU01Uriage(),     changeFlg );
        changeFlg = judgeUpNo(entityPre.getU02MenuUri(),     entity.getU02MenuUri(),    changeFlg );
        changeFlg = judgeUpNo(entityPre.getU03BuppanUri(),   entity.getU03BuppanUri(),  changeFlg );
        changeFlg = judgeUpNo(entityPre.getU04Nebiki(),      entity.getU04Nebiki(),     changeFlg );
        changeFlg = judgeUpNo(entityPre.getU05Nebikigo(),    entity.getU05Nebikigo(),   changeFlg );
        changeFlg = judgeUpNo(entityPre.getU06Tax(),         entity.getU06Tax(),        changeFlg );
        changeFlg = judgeUpNo(entityPre.getU07MinusKen(),    entity.getU07MinusKen(),   changeFlg );
        changeFlg = judgeUpNo(entityPre.getU08MinusKin(),    entity.getU08MinusKin(),   changeFlg );
        changeFlg = judgeUpNo(entityPre.getU09NebikiKen(),   entity.getU09NebikiKen(),  changeFlg );
        changeFlg = judgeUpNo(entityPre.getU10NebikiKin(),   entity.getU10NebikiKin(),  changeFlg );
        changeFlg = judgeUpNo(entityPre.getU11PNebikiKen(),  entity.getU11PNebikiKen(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU12PNebikiKin(),  entity.getU12PNebikiKin(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU13GenkinKen(),   entity.getU13GenkinKen(),  changeFlg );
        changeFlg = judgeUpNo(entityPre.getU14GenkinKin(),   entity.getU14GenkinKin(),  changeFlg );
        changeFlg = judgeUpNo(entityPre.getU15KaikeiKen2(),  entity.getU15KaikeiKen2(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU16KaikeiKin2(),  entity.getU16KaikeiKin2(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU17KaikeiKen3(),  entity.getU17KaikeiKen3(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU18KaikeiKin3(),  entity.getU18KaikeiKin3(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU19KaikeiKen4(),  entity.getU19KaikeiKen4(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU20KaikeiKin4(),  entity.getU20KaikeiKin4(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU21KaikeiKen5(),  entity.getU21KaikeiKen5(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU22KaikeiKin5(),  entity.getU22KaikeiKin5(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU23KaikeiKen6(),  entity.getU23KaikeiKen6(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU24KaikeiKin6(),  entity.getU24KaikeiKin6(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU25KaikeiKen7(),  entity.getU25KaikeiKen7(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU26KaikeiKin7(),  entity.getU26KaikeiKin7(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU27KaikeiKen8(),  entity.getU27KaikeiKen8(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU28KaikeiKin8(),  entity.getU28KaikeiKin8(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU29KaikeiKen9(),  entity.getU29KaikeiKen9(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU30KaikeiKin9(),  entity.getU30KaikeiKin9(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU31KaikeiKen10(), entity.getU31KaikeiKen10(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU32KaikeiKin10(), entity.getU32KaikeiKin10(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU33KaikeiKen11(), entity.getU33KaikeiKen11(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU34KaikeiKin11(), entity.getU34KaikeiKin11(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU35TieketKen1(),  entity.getU35TieketKen1(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU36TieketKin1(),  entity.getU36TieketKin1(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU37TieketKen2(),  entity.getU37TieketKen2(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU38TieketKin2(),  entity.getU38TieketKin2(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU39TieketKen3(),  entity.getU39TieketKen3(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU40TieketKin3(),  entity.getU40TieketKin3(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU41TieketKen4(),  entity.getU41TieketKen4(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU42TieketKin4(),  entity.getU42TieketKin4(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU43TieketKen5(),  entity.getU43TieketKen5(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU44TieketKin5(),  entity.getU44TieketKin5(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU45TieketKen6(),  entity.getU45TieketKen6(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU46TieketKin6(),  entity.getU46TieketKin6(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU47TieketKen7(),  entity.getU47TieketKen7(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU48TieketKin7(),  entity.getU48TieketKin7(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU49TieketKen8(),  entity.getU49TieketKen8(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU50TieketKin8(),  entity.getU50TieketKin8(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU51TieketKen9(),  entity.getU51TieketKen9(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU52TieketKin9(),  entity.getU52TieketKin9(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU53TieketKen10(), entity.getU53TieketKen10(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU54TieketKin10(), entity.getU54TieketKin10(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU55TieketKen11(), entity.getU55TieketKen11(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU56TieketKin11(), entity.getU56TieketKin11(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU57TieketKen12(), entity.getU57TieketKen12(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU58TieketKin12(), entity.getU58TieketKin12(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU59TieketKen13(), entity.getU59TieketKen13(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU60TieketKin13(), entity.getU60TieketKin13(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU61TieketKen14(), entity.getU61TieketKen14(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU62TieketKin14(), entity.getU62TieketKin14(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU63TieketKen15(), entity.getU63TieketKen15(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU64TieketKin15(), entity.getU64TieketKin15(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU65Nyukin(),      entity.getU65Nyukin(),     changeFlg );
        changeFlg = judgeUpNo(entityPre.getU66Shukin(),      entity.getU66Shukin(),     changeFlg );
        changeFlg = judgeUpNo(entityPre.getU67AridakaCal(),  entity.getU67AridakaCal(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU68AridakaJitu(), entity.getU68AridakaJitu(),changeFlg );
        changeFlg = judgeUpNo(entityPre.getU69Kajou(),       entity.getU69Kajou(),      changeFlg );
        changeFlg = judgeUpNo(entityPre.getU70Fusoku(),      entity.getU70Fusoku(),     changeFlg );
        changeFlg = judgeUpNo(entityPre.getU71CanKen(),      entity.getU71CanKen(),     changeFlg );
        changeFlg = judgeUpNo(entityPre.getU72CanKin(),      entity.getU72CanKin(),     changeFlg );
        changeFlg = judgeUpNo(entityPre.getU73ChengeCnt(),   entity.getU73ChengeCnt(),  changeFlg );
        changeFlg = judgeUpNo(entityPre.getU74Kyakusu(),     entity.getU74Kyakusu(),    changeFlg );
        changeFlg = judgeUpNo(entityPre.getU75AllcanKen(),   entity.getU75AllcanKen(),  changeFlg );
        changeFlg = judgeUpNo(entityPre.getU76AllcanKin(),   entity.getU76AllcanKin(),  changeFlg );


        return changeFlg;

    }


    /**
     * 修正データの有無をチェック
     * @param entity 修正後データ
     * @param entityPre 修正前データ
     * @return true:修正データがある false:ない
     */
    private boolean comparePropertyToriPre(UIUriMainteWorkInfo entity, UIUriMainteWorkInfo entityPre) {

        char[] upNo = entity.getUpNo96().toCharArray();

        for(int i=0; i<27; i++){
            if(upNo[i]!='0' && upNo[i]!='1'){
                upNo[i] = '0';
            }
        }

        boolean changeFlg = false;

        changeFlg = judgeUpNo(entityPre.getU01NebikiKen1(),  entity.getU01NebikiKen1(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU02NebikiKin1(),  entity.getU02NebikiKin1(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU03NebikiTax1(),  entity.getU03NebikiTax1(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU04NebikiKen2(),  entity.getU04NebikiKen2(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU05NebikiKin2(),  entity.getU05NebikiKin2(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU06NebikiTax2(),  entity.getU06NebikiTax2(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU07NebikiKen3(),  entity.getU07NebikiKen3(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU08NebikiKin3(),  entity.getU08NebikiKin3(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU09NebikiTax3(),  entity.getU09NebikiTax3(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU10NebikiKen4(),  entity.getU10NebikiKen4(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU11NebikiKin4(),  entity.getU11NebikiKin4(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU12NebikiTax4(),  entity.getU12NebikiTax4(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU13NebikiKen5(),  entity.getU13NebikiKen5(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU14NebikiKin5(),  entity.getU14NebikiKin5(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU15NebikiTax5(),  entity.getU15NebikiTax5(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU16NebikiKen6(),  entity.getU16NebikiKen6(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU17NebikiKin6(),  entity.getU17NebikiKin6(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU18NebikiTax6(),  entity.getU18NebikiTax6(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU19NebikiKen7(),  entity.getU19NebikiKen7(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU20NebikiKin7(),  entity.getU20NebikiKin7(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU21NebikiTax7(),  entity.getU21NebikiTax7(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU22NebikiKen8(),  entity.getU22NebikiKen8(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU23NebikiKin8(),  entity.getU23NebikiKin8(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU24NebikiTax8(),  entity.getU24NebikiTax8(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU25NebikiKen9(),  entity.getU25NebikiKen9(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU26NebikiKin9(),  entity.getU26NebikiKin9(), changeFlg );
        changeFlg = judgeUpNo(entityPre.getU27NebikiTax9(),  entity.getU27NebikiTax9(), changeFlg );

        return changeFlg;

    }
 // add 2019/07/17 USI欒 #34 begin
    private boolean compareKeigenTaxPre(GetKeigenTaxData entity,
			GetKeigenTaxData entityPre) {
    	 char[] upNo = entity.getUpNo().toCharArray();

         for(int i=0; i<10; i++){
             if(upNo[i]!='0' && upNo[i]!='1'){
                 upNo[i] = '0';
             }
         }


         boolean changeFlg = false;

         changeFlg = judgeUpNo(entityPre.getUriage1().toString(),  entity.getUriage1().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getUriage2().toString(),  entity.getUriage2().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getUriage3().toString(),  entity.getUriage3().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getUriage4().toString(),  entity.getUriage4().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getUriage5().toString(),  entity.getUriage5().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getTax1().toString(),  entity.getTax1().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getTax2().toString(),  entity.getTax2().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getTax3().toString(),  entity.getTax3().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getTax4().toString(),  entity.getTax4().toString(), changeFlg );
         changeFlg = judgeUpNo(entityPre.getTax5().toString(),  entity.getTax5().toString(), changeFlg );

         return changeFlg;
	}
// add 2019/07/17 USI欒 #34 end



    /**
     * 項目ごとに修正されたかを判断する
     *
     * @param elm1
     * @param elm2
     * @param upNo
     * @param index
     */
    private void judgeUpNo(String elm1, String elm2, char[] upNo, int index){
        if(!elm1.equals(elm2)) {
            upNo[index] = '1';
        }
    }

    /**
     * 現金在高日次修正TBL(BT97ADUP)更新項目No取得処理
     * @param entity
     * @param entityTmp
     * @return 元データからの修正内容をビット配列で返す.
     *          修正がない場合には空文字を返す.
     */
    //private String comparePropertyUri(UIUriMainteWorkInfo entity, UIUriMainteWorkInfo entityTmp) {
    private String comparePropertyUri(UIUriMainteWorkInfo entity, UIUriMainteWorkInfo entityTmp, Map tempKaikenSyukeiMap, Map tempKaikinSyukeiMap) {

        //新規更新フラグ
        char[] upNo = {'0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
                        '0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
                        '0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0',
                        '0','0','0','0','0','0','0','0','0','0','0','0','0','0','0','0'};

        judgeUpNo(entityTmp.getU01Uriage(),      entity.getU01Uriage(),      upNo, 0 );
        judgeUpNo(entityTmp.getU02MenuUri(),     entity.getU02MenuUri(),     upNo, 1 );
        judgeUpNo(entityTmp.getU03BuppanUri(),   entity.getU03BuppanUri(),   upNo, 2 );
        judgeUpNo(entityTmp.getU04Nebiki(),      entity.getU04Nebiki(),      upNo, 3 );
        judgeUpNo(entityTmp.getU05Nebikigo(),    entity.getU05Nebikigo(),    upNo, 4 );
        judgeUpNo(entityTmp.getU06Tax(),         entity.getU06Tax(),         upNo, 5 );
        judgeUpNo(entityTmp.getU07MinusKen(),    entity.getU07MinusKen(),    upNo, 6 );
        judgeUpNo(entityTmp.getU08MinusKin(),    entity.getU08MinusKin(),    upNo, 7 );
        judgeUpNo(entityTmp.getU09NebikiKen(),   entity.getU09NebikiKen(),   upNo, 8 );
        judgeUpNo(entityTmp.getU10NebikiKin(),   entity.getU10NebikiKin(),   upNo, 9 );
        //---2007/04/09 kusama update start ％値引きも更新の対象にする為、以下２行をコメントアウト
        //judgeUpNo(entityTmp.getU11PNebikiKen(),  entity.getU11PNebikiKen(),  upNo, 10 );
        //judgeUpNo(entityTmp.getU12PNebikiKin(),  entity.getU12PNebikiKin(),  upNo, 11 );
        judgeUpNo(entityTmp.getU11PNebikiKen(),  entity.getU11PNebikiKen(),  upNo, 10 );
        judgeUpNo(entityTmp.getU12PNebikiKin(),  entity.getU12PNebikiKin(),  upNo, 11 );
        //---2007/04/09 kusama update end
        judgeUpNo(entityTmp.getU13GenkinKen(),   entity.getU13GenkinKen(),   upNo, 12 );
        judgeUpNo(entityTmp.getU14GenkinKin(),   entity.getU14GenkinKin(),   upNo, 13 );
        judgeUpNo(entityTmp.getU15KaikeiKen2(),  entity.getU15KaikeiKen2(),  upNo, 14 );
        judgeUpNo(entityTmp.getU16KaikeiKin2(),  entity.getU16KaikeiKin2(),  upNo, 15 );
        judgeUpNo(entityTmp.getU17KaikeiKen3(),  entity.getU17KaikeiKen3(),  upNo, 16 );
        judgeUpNo(entityTmp.getU18KaikeiKin3(),  entity.getU18KaikeiKin3(),  upNo, 17 );
        judgeUpNo(entityTmp.getU19KaikeiKen4(),  entity.getU19KaikeiKen4(),  upNo, 18 );
        judgeUpNo(entityTmp.getU20KaikeiKin4(),  entity.getU20KaikeiKin4(),  upNo, 19 );
        judgeUpNo(entityTmp.getU21KaikeiKen5(),  entity.getU21KaikeiKen5(),  upNo, 20 );
        judgeUpNo(entityTmp.getU22KaikeiKin5(),  entity.getU22KaikeiKin5(),  upNo, 21 );
        judgeUpNo(entityTmp.getU23KaikeiKen6(),  entity.getU23KaikeiKen6(),  upNo, 22 );
        judgeUpNo(entityTmp.getU24KaikeiKin6(),  entity.getU24KaikeiKin6(),  upNo, 23 );
        judgeUpNo(entityTmp.getU25KaikeiKen7(),  entity.getU25KaikeiKen7(),  upNo, 24 );
        judgeUpNo(entityTmp.getU26KaikeiKin7(),  entity.getU26KaikeiKin7(),  upNo, 25 );
        judgeUpNo(entityTmp.getU27KaikeiKen8(),  entity.getU27KaikeiKen8(),  upNo, 26 );
        judgeUpNo(entityTmp.getU28KaikeiKin8(),  entity.getU28KaikeiKin8(),  upNo, 27 );
        judgeUpNo(entityTmp.getU29KaikeiKen9(),  entity.getU29KaikeiKen9(),  upNo, 28 );
        judgeUpNo(entityTmp.getU30KaikeiKin9(),  entity.getU30KaikeiKin9(),  upNo, 29 );
        judgeUpNo(entityTmp.getU31KaikeiKen10(), entity.getU31KaikeiKen10(), upNo, 30 );
        judgeUpNo(entityTmp.getU32KaikeiKin10(), entity.getU32KaikeiKin10(), upNo, 31 );
        judgeUpNo(entityTmp.getU33KaikeiKen11(), entity.getU33KaikeiKen11(), upNo, 32 );
        judgeUpNo(entityTmp.getU34KaikeiKin11(), entity.getU34KaikeiKin11(), upNo, 33 );
        judgeUpNo(entityTmp.getU35TieketKen1(),  entity.getU35TieketKen1(),  upNo, 34 );
        judgeUpNo(entityTmp.getU36TieketKin1(),  entity.getU36TieketKin1(),  upNo, 35 );
        judgeUpNo(entityTmp.getU37TieketKen2(),  entity.getU37TieketKen2(),  upNo, 36 );
        judgeUpNo(entityTmp.getU38TieketKin2(),  entity.getU38TieketKin2(),  upNo, 37 );
        judgeUpNo(entityTmp.getU39TieketKen3(),  entity.getU39TieketKen3(),  upNo, 38 );
        judgeUpNo(entityTmp.getU40TieketKin3(),  entity.getU40TieketKin3(),  upNo, 39 );
        judgeUpNo(entityTmp.getU41TieketKen4(),  entity.getU41TieketKen4(),  upNo, 40 );
        judgeUpNo(entityTmp.getU42TieketKin4(),  entity.getU42TieketKin4(),  upNo, 41 );
        judgeUpNo(entityTmp.getU43TieketKen5(),  entity.getU43TieketKen5(),  upNo, 42 );
        judgeUpNo(entityTmp.getU44TieketKin5(),  entity.getU44TieketKin5(),  upNo, 43 );
        judgeUpNo(entityTmp.getU45TieketKen6(),  entity.getU45TieketKen6(),  upNo, 44 );
        judgeUpNo(entityTmp.getU46TieketKin6(),  entity.getU46TieketKin6(),  upNo, 45 );
        judgeUpNo(entityTmp.getU47TieketKen7(),  entity.getU47TieketKen7(),  upNo, 46 );
        judgeUpNo(entityTmp.getU48TieketKin7(),  entity.getU48TieketKin7(),  upNo, 47 );
        judgeUpNo(entityTmp.getU49TieketKen8(),  entity.getU49TieketKen8(),  upNo, 48 );
        judgeUpNo(entityTmp.getU50TieketKin8(),  entity.getU50TieketKin8(),  upNo, 49 );
        judgeUpNo(entityTmp.getU51TieketKen9(),  entity.getU51TieketKen9(),  upNo, 50 );
        judgeUpNo(entityTmp.getU52TieketKin9(),  entity.getU52TieketKin9(),  upNo, 51 );
        judgeUpNo(entityTmp.getU53TieketKen10(), entity.getU53TieketKen10(), upNo, 52 );
        judgeUpNo(entityTmp.getU54TieketKin10(), entity.getU54TieketKin10(), upNo, 53 );
        judgeUpNo(entityTmp.getU55TieketKen11(), entity.getU55TieketKen11(), upNo, 54 );
        judgeUpNo(entityTmp.getU56TieketKin11(), entity.getU56TieketKin11(), upNo, 55 );
        judgeUpNo(entityTmp.getU57TieketKen12(), entity.getU57TieketKen12(), upNo, 56 );
        judgeUpNo(entityTmp.getU58TieketKin12(), entity.getU58TieketKin12(), upNo, 57 );
        judgeUpNo(entityTmp.getU59TieketKen13(), entity.getU59TieketKen13(), upNo, 58 );
        judgeUpNo(entityTmp.getU60TieketKin13(), entity.getU60TieketKin13(), upNo, 59 );
        judgeUpNo(entityTmp.getU61TieketKen14(), entity.getU61TieketKen14(), upNo, 60 );
        judgeUpNo(entityTmp.getU62TieketKin14(), entity.getU62TieketKin14(), upNo, 61 );
        judgeUpNo(entityTmp.getU63TieketKen15(), entity.getU63TieketKen15(), upNo, 62 );
        judgeUpNo(entityTmp.getU64TieketKin15(), entity.getU64TieketKin15(), upNo, 63 );
        judgeUpNo(entityTmp.getU65Nyukin(),      entity.getU65Nyukin(),      upNo, 64 );
        judgeUpNo(entityTmp.getU66Shukin(),      entity.getU66Shukin(),      upNo, 65 );
        judgeUpNo(entityTmp.getU67AridakaCal(),  entity.getU67AridakaCal(),  upNo, 66 );
        judgeUpNo(entityTmp.getU68AridakaJitu(), entity.getU68AridakaJitu(), upNo, 67 );
        judgeUpNo(entityTmp.getU69Kajou(),       entity.getU69Kajou(),       upNo, 68 );
        judgeUpNo(entityTmp.getU70Fusoku(),      entity.getU70Fusoku(),      upNo, 69 );
        judgeUpNo(entityTmp.getU71CanKen(),      entity.getU71CanKen(),      upNo, 70 );
        judgeUpNo(entityTmp.getU72CanKin(),      entity.getU72CanKin(),      upNo, 71 );
        judgeUpNo(entityTmp.getU73ChengeCnt(),   entity.getU73ChengeCnt(),   upNo, 72 );
        judgeUpNo(entityTmp.getU74Kyakusu(),     entity.getU74Kyakusu(),     upNo, 73 );
        judgeUpNo(entityTmp.getU75AllcanKen(),   entity.getU75AllcanKen(),   upNo, 74 );
        judgeUpNo(entityTmp.getU76AllcanKin(),   entity.getU76AllcanKin(),   upNo, 75 );

//      Begin ADD By 周春建 201304-001_売上修正確認画面の集計区分の修正値表示の改修追加のため
        //各データの件数が変化したら、集計コードの値が１にする
        if ((tempKaikenSyukeiMap != null)&&(tempKaikenSyukeiMap.containsKey(entity.getEigyoDt()))) {
        	List kaikenSyukeiList = (List)tempKaikenSyukeiMap.get(entity.getEigyoDt());
        	for (int i=0; i<kaikenSyukeiList.size(); i++) {
        		String tempSyukeiCd = (String)kaikenSyukeiList.get(i);
        		int temp = Integer.parseInt(tempSyukeiCd);
   				int index = temp*2+10;
   				upNo[index] = '1';
        	}
        }
        //各データの金額が変化したら、集計コードの値が１にする
        if ((tempKaikinSyukeiMap != null)&&(tempKaikinSyukeiMap.containsKey(entity.getEigyoDt()))) {
        	List kaikinSyukeList = (List)tempKaikinSyukeiMap.get(entity.getEigyoDt());
        	for (int i=0; i<kaikinSyukeList.size(); i++) {
        		String tempSyukeiCd = (String)kaikinSyukeList.get(i);
				int temp = Integer.parseInt(tempSyukeiCd);
        		int index = temp*2+11;
        		upNo[index] = '1';
        	}
        }
//      End ADD By 周春建 201304-001_売上修正確認画面の集計区分の修正値表示の改修追加のため

        return new String(upNo);

    }


    /**
     * 取引修正TBL(BT96NBUP)更新項目No取得処理
     * @param entity
     * @param entityTmp
     * @return 修正内容をビット配列で返す
     */
    private String comparePropertyTori(UIUriMainteWorkInfo entity, UIUriMainteWorkInfo entityTmp) {

        //新規更新フラグ
        char[] upNo = {'0','0','0','0','0','0','0','0','0','0',
                        '0','0','0','0','0','0','0','0','0','0',
                        '0','0','0','0','0','0','0'};

        judgeUpNo(entityTmp.getU01NebikiKen1(),  entity.getU01NebikiKen1(),  upNo, 0);
        judgeUpNo(entityTmp.getU02NebikiKin1(),  entity.getU02NebikiKin1(),  upNo, 1);
        judgeUpNo(entityTmp.getU03NebikiTax1(),  entity.getU03NebikiTax1(),  upNo, 2);
        judgeUpNo(entityTmp.getU04NebikiKen2(),  entity.getU04NebikiKen2(),  upNo, 3);
        judgeUpNo(entityTmp.getU05NebikiKin2(),  entity.getU05NebikiKin2(),  upNo, 4);
        judgeUpNo(entityTmp.getU06NebikiTax2(),  entity.getU06NebikiTax2(),  upNo, 5);
        judgeUpNo(entityTmp.getU07NebikiKen3(),  entity.getU07NebikiKen3(),  upNo, 6);
        judgeUpNo(entityTmp.getU08NebikiKin3(),  entity.getU08NebikiKin3(),  upNo, 7);
        judgeUpNo(entityTmp.getU09NebikiTax3(),  entity.getU09NebikiTax3(),  upNo, 8);
        judgeUpNo(entityTmp.getU10NebikiKen4(),  entity.getU10NebikiKen4(),  upNo, 9);
        judgeUpNo(entityTmp.getU11NebikiKin4(),  entity.getU11NebikiKin4(),  upNo, 10);
        judgeUpNo(entityTmp.getU12NebikiTax4(),  entity.getU12NebikiTax4(),  upNo, 11);
        judgeUpNo(entityTmp.getU13NebikiKen5(),  entity.getU13NebikiKen5(),  upNo, 12);
        judgeUpNo(entityTmp.getU14NebikiKin5(),  entity.getU14NebikiKin5(),  upNo, 13);
        judgeUpNo(entityTmp.getU15NebikiTax5(),  entity.getU15NebikiTax5(),  upNo, 14);
        judgeUpNo(entityTmp.getU16NebikiKen6(),  entity.getU16NebikiKen6(),  upNo, 15);
        judgeUpNo(entityTmp.getU17NebikiKin6(),  entity.getU17NebikiKin6(),  upNo, 16);
        judgeUpNo(entityTmp.getU18NebikiTax6(),  entity.getU18NebikiTax6(),  upNo, 17);
        judgeUpNo(entityTmp.getU19NebikiKen7(),  entity.getU19NebikiKen7(),  upNo, 18);
        judgeUpNo(entityTmp.getU20NebikiKin7(),  entity.getU20NebikiKin7(),  upNo, 19);
        judgeUpNo(entityTmp.getU21NebikiTax7(),  entity.getU21NebikiTax7(),  upNo, 20);
        judgeUpNo(entityTmp.getU22NebikiKen8(),  entity.getU22NebikiKen8(),  upNo, 21);
        judgeUpNo(entityTmp.getU23NebikiKin8(),  entity.getU23NebikiKin8(),  upNo, 22);
        judgeUpNo(entityTmp.getU24NebikiTax8(),  entity.getU24NebikiTax8(),  upNo, 23);
        judgeUpNo(entityTmp.getU25NebikiKen9(),  entity.getU25NebikiKen9(),  upNo, 24);
        judgeUpNo(entityTmp.getU26NebikiKin9(),  entity.getU26NebikiKin9(),  upNo, 25);
        judgeUpNo(entityTmp.getU27NebikiTax9(),  entity.getU27NebikiTax9(),  upNo, 26);

        return new String(upNo);

    }

// add 2019/07/17 USI欒 #34 begin
    /**
     * 取引修正TBL(BD66ADUM)更新項目No取得処理
     * @param entity
     * @param entityTmp
     * @return 修正内容をビット配列で返す
     */
	private String comparePropertyKeigenTax(GetKeigenTaxData entity,
			GetKeigenTaxData entityTmp) {
		  //新規更新フラグ
        char[] upNo = {'0','0','0','0','0','0','0','0','0','0'};

        judgeUpNo(entityTmp.getUriage1().toString(),  entity.getUriage1().toString(),  upNo, 0);
        judgeUpNo(entityTmp.getUriage2().toString(),  entity.getUriage2().toString(),  upNo, 1);
        judgeUpNo(entityTmp.getUriage3().toString(),  entity.getUriage3().toString(),  upNo, 2);
        judgeUpNo(entityTmp.getUriage4().toString(),  entity.getUriage4().toString(),  upNo, 3);
        judgeUpNo(entityTmp.getUriage5().toString(),  entity.getUriage5().toString(),  upNo, 4);
        judgeUpNo(entityTmp.getTax1().toString(),  entity.getTax1().toString(),  upNo, 5);
        judgeUpNo(entityTmp.getTax2().toString(),  entity.getTax2().toString(),  upNo, 6);
        judgeUpNo(entityTmp.getTax3().toString(),  entity.getTax3().toString(),  upNo, 7);
        judgeUpNo(entityTmp.getTax4().toString(),  entity.getTax4().toString(),  upNo, 8);
        judgeUpNo(entityTmp.getTax5().toString(),  entity.getTax5().toString(),  upNo, 9);

        return new String(upNo);
	}
// add 2019/07/17 USI欒 #34 end

    /**
     * 現金在高日次修正TBL(BT97ADUP)Entityセット
     * @param entity
     * @return
     */
    private TrnADUPInfo setUriCopy(UIUriMainteWorkInfo entity, String upNo, String user) {

        TrnADUPInfo info = new TrnADUPInfo();

        info.setCompanyCd(entity.getCompanyCd());
        info.setMiseCd(entity.getMiseCd());
        info.setEigyoDt(entity.getEigyoDt());
        info.setUpFlg("0");
        info.setUpDt(entity.getUpDt());
        info.setBatupDt(entity.getBatupDt());
        info.setUpNo(upNo);
        info.setTenkoKbn(UriMaintenanceCommon.convDecimal(entity.getTenkoKbn()));

        info.setU01Uriage(UriMaintenanceCommon.convDecimal(entity.getU01Uriage()));
        info.setU02MenuUri(UriMaintenanceCommon.convDecimal(entity.getU02MenuUri()));
        info.setU03BuppanUri(UriMaintenanceCommon.convDecimal(entity.getU03BuppanUri()));

        info.setU04Nebiki(UriMaintenanceCommon.convDecimal(entity.getU04Nebiki()));        //値引計 = 値引金額1～9と値引税額1～9の合計値に更新
        info.setU05Nebikigo(UriMaintenanceCommon.convDecimal(entity.getU05Nebikigo()));    //値引後売上 = 売上計-値引計
        info.setU06Tax(UriMaintenanceCommon.convDecimal(entity.getU06Tax()));
        info.setU07MinusKen(UriMaintenanceCommon.convDecimal(entity.getU07MinusKen()));
        info.setU08MinusKin(UriMaintenanceCommon.convDecimal(entity.getU08MinusKin()));
        info.setU09NebikiKen(UriMaintenanceCommon.convDecimal(entity.getU09NebikiKen()));  //値引件数 = 値引件数1～9の合計値に更新
        info.setU10NebikiKin(UriMaintenanceCommon.convDecimal(entity.getU10NebikiKin()));  //値引金額 = 値引金額1～9の合計値に更新
        info.setU11PNebikiKen(UriMaintenanceCommon.convDecimal(entity.getU11PNebikiKen()));
        info.setU12PNebikiKin(UriMaintenanceCommon.convDecimal(entity.getU12PNebikiKin()));
        info.setU13GenkinKen(UriMaintenanceCommon.convDecimal(entity.getU13GenkinKen()));
        info.setU14GenkinKin(UriMaintenanceCommon.convDecimal(entity.getU14GenkinKin()));
        info.setU15KaikeiKen2(UriMaintenanceCommon.convDecimal(entity.getU15KaikeiKen2()));
        info.setU16KaikeiKin2(UriMaintenanceCommon.convDecimal(entity.getU16KaikeiKin2()));
        info.setU17KaikeiKen3(UriMaintenanceCommon.convDecimal(entity.getU17KaikeiKen3()));
        info.setU18KaikeiKin3(UriMaintenanceCommon.convDecimal(entity.getU18KaikeiKin3()));
        info.setU19KaikeiKen4(UriMaintenanceCommon.convDecimal(entity.getU19KaikeiKen4()));
        info.setU20KaikeiKin4(UriMaintenanceCommon.convDecimal(entity.getU20KaikeiKin4()));
        info.setU21KaikeiKen5(UriMaintenanceCommon.convDecimal(entity.getU21KaikeiKen5()));
        info.setU22KaikeiKin5(UriMaintenanceCommon.convDecimal(entity.getU22KaikeiKin5()));
        info.setU23KaikeiKen6(UriMaintenanceCommon.convDecimal(entity.getU23KaikeiKen6()));
        info.setU24KaikeiKin6(UriMaintenanceCommon.convDecimal(entity.getU24KaikeiKin6()));
        info.setU25KaikeiKen7(UriMaintenanceCommon.convDecimal(entity.getU25KaikeiKen7()));
        info.setU26KaikeiKin7(UriMaintenanceCommon.convDecimal(entity.getU26KaikeiKin7()));
        info.setU27KaikeiKen8(UriMaintenanceCommon.convDecimal(entity.getU27KaikeiKen8()));
        info.setU28KaikeiKin8(UriMaintenanceCommon.convDecimal(entity.getU28KaikeiKin8()));
        info.setU29KaikeiKen9(UriMaintenanceCommon.convDecimal(entity.getU29KaikeiKen9()));
        info.setU30KaikeiKin9(UriMaintenanceCommon.convDecimal(entity.getU30KaikeiKin9()));
        info.setU31KaikeiKen10(UriMaintenanceCommon.convDecimal(entity.getU31KaikeiKen10()));
        info.setU32KaikeiKin10(UriMaintenanceCommon.convDecimal(entity.getU32KaikeiKin10()));
        info.setU33KaikeiKen11(UriMaintenanceCommon.convDecimal(entity.getU33KaikeiKen11()));
        info.setU34KaikeiKin11(UriMaintenanceCommon.convDecimal(entity.getU34KaikeiKin11()));
        info.setU35TieketKen1(UriMaintenanceCommon.convDecimal(entity.getU35TieketKen1()));
        info.setU36TieketKin1(UriMaintenanceCommon.convDecimal(entity.getU36TieketKin1()));
        info.setU37TieketKen2(UriMaintenanceCommon.convDecimal(entity.getU37TieketKen2()));
        info.setU38TieketKin2(UriMaintenanceCommon.convDecimal(entity.getU38TieketKin2()));
        info.setU39TieketKen3(UriMaintenanceCommon.convDecimal(entity.getU39TieketKen3()));
        info.setU40TieketKin3(UriMaintenanceCommon.convDecimal(entity.getU40TieketKin3()));
        info.setU41TieketKen4(UriMaintenanceCommon.convDecimal(entity.getU41TieketKen4()));
        info.setU42TieketKin4(UriMaintenanceCommon.convDecimal(entity.getU42TieketKin4()));
        info.setU43TieketKen5(UriMaintenanceCommon.convDecimal(entity.getU43TieketKen5()));
        info.setU44TieketKin5(UriMaintenanceCommon.convDecimal(entity.getU44TieketKin5()));
        info.setU45TieketKen6(UriMaintenanceCommon.convDecimal(entity.getU45TieketKen6()));
        info.setU46TieketKin6(UriMaintenanceCommon.convDecimal(entity.getU46TieketKin6()));
        info.setU47TieketKen7(UriMaintenanceCommon.convDecimal(entity.getU47TieketKen7()));
        info.setU48TieketKin7(UriMaintenanceCommon.convDecimal(entity.getU48TieketKin7()));
        info.setU49TieketKen8(UriMaintenanceCommon.convDecimal(entity.getU49TieketKen8()));
        info.setU50TieketKin8(UriMaintenanceCommon.convDecimal(entity.getU50TieketKin8()));
        info.setU51TieketKen9(UriMaintenanceCommon.convDecimal(entity.getU51TieketKen9()));
        info.setU52TieketKin9(UriMaintenanceCommon.convDecimal(entity.getU52TieketKin9()));
        info.setU53TieketKen10(UriMaintenanceCommon.convDecimal(entity.getU53TieketKen10()));
        info.setU54TieketKin10(UriMaintenanceCommon.convDecimal(entity.getU54TieketKin10()));
        info.setU55TieketKen11(UriMaintenanceCommon.convDecimal(entity.getU55TieketKen11()));
        info.setU56TieketKin11(UriMaintenanceCommon.convDecimal(entity.getU56TieketKin11()));
        info.setU57TieketKen12(UriMaintenanceCommon.convDecimal(entity.getU57TieketKen12()));
        info.setU58TieketKin12(UriMaintenanceCommon.convDecimal(entity.getU58TieketKin12()));
        info.setU59TieketKen13(UriMaintenanceCommon.convDecimal(entity.getU59TieketKen13()));
        info.setU60TieketKin13(UriMaintenanceCommon.convDecimal(entity.getU60TieketKin13()));
        info.setU61TieketKen14(UriMaintenanceCommon.convDecimal(entity.getU61TieketKen14()));
        info.setU62TieketKin14(UriMaintenanceCommon.convDecimal(entity.getU62TieketKin14()));
        info.setU63TieketKen15(UriMaintenanceCommon.convDecimal(entity.getU63TieketKen15()));
        info.setU64TieketKin15(UriMaintenanceCommon.convDecimal(entity.getU64TieketKin15()));
        info.setU65Nyukin(UriMaintenanceCommon.convDecimal(entity.getU65Nyukin()));
        info.setU66Shukin(UriMaintenanceCommon.convDecimal(entity.getU66Shukin()));
        info.setU67AridakaCal(UriMaintenanceCommon.convDecimal(entity.getU67AridakaCal()));
        info.setU68AridakaJitu(UriMaintenanceCommon.convDecimal(entity.getU68AridakaJitu()));
        info.setU69Kajou(UriMaintenanceCommon.convDecimal(entity.getU69Kajou()));             //過剰金額=現金金額－実現金在高＞0の場合の値
        info.setU70Fusoku(UriMaintenanceCommon.convDecimal(entity.getU70Fusoku()));           //不足金額=現金金額－実現金在高＜0の場合の値
        info.setU71CanKen(UriMaintenanceCommon.convDecimal(entity.getU71CanKen()));
        info.setU72CanKin(UriMaintenanceCommon.convDecimal(entity.getU72CanKin()));
        info.setU73ChengeCnt(UriMaintenanceCommon.convDecimal(entity.getU73ChengeCnt()));
        info.setU74Kyakusu(UriMaintenanceCommon.convDecimal(entity.getU74Kyakusu()));
        info.setU75AllcanKen(UriMaintenanceCommon.convDecimal(entity.getU75AllcanKen()));
        info.setU76AllcanKin(UriMaintenanceCommon.convDecimal(entity.getU76AllcanKin()));
        info.setDataKbn(entity.getDataKbn());

        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        info.setFirstUser(user);                      //登録ユーザ
        info.setFirstPgm(LOGIC_ID.substring(0, 6));   //登録プログラム
        info.setFirstTmsp(currentTimestamp);          //登録時タイムスタンプ
        info.setLastUser(user);                       //修正ユーザ
        info.setLastPgm(LOGIC_ID.substring(0, 6));    //修正プログラム
        info.setLastTmsp(entity.getLastTmsp97());     //修正時タイムスタンプ

        return info;

    }


    /**
     * 取引修正TBL(BT96NBUP)Entity設定
     * @param entity
     * @return
     */
    private TrnNBUPInfo setToriCopy(UIUriMainteWorkInfo entity, String upNo, String user) {

        TrnNBUPInfo info = new TrnNBUPInfo();

        info.setCompanyCd(entity.getCompanyCd());
        info.setMiseCd(entity.getMiseCd());
        info.setEigyoDt(entity.getEigyoDt());
        info.setUpFlg("0");
        info.setUpDt(entity.getUpDt());
        info.setBatupDt(entity.getBatupDt());
        info.setUpNo(upNo);

        info.setU01NebikiKen1(UriMaintenanceCommon.convDecimal(entity.getU01NebikiKen1()));
        info.setU02NebikiKin1(UriMaintenanceCommon.convDecimal(entity.getU02NebikiKin1()));
        info.setU03NebikiTax1(UriMaintenanceCommon.convDecimal(entity.getU03NebikiTax1()));
        info.setU04NebikiKen2(UriMaintenanceCommon.convDecimal(entity.getU04NebikiKen2()));
        info.setU05NebikiKin2(UriMaintenanceCommon.convDecimal(entity.getU05NebikiKin2()));
        info.setU06NebikiTax2(UriMaintenanceCommon.convDecimal(entity.getU06NebikiTax2()));
        info.setU07NebikiKen3(UriMaintenanceCommon.convDecimal(entity.getU07NebikiKen3()));
        info.setU08NebikiKin3(UriMaintenanceCommon.convDecimal(entity.getU08NebikiKin3()));
        info.setU09NebikiTax3(UriMaintenanceCommon.convDecimal(entity.getU09NebikiTax3()));
        info.setU10NebikiKen4(UriMaintenanceCommon.convDecimal(entity.getU10NebikiKen4()));
        info.setU11NebikiKin4(UriMaintenanceCommon.convDecimal(entity.getU11NebikiKin4()));
        info.setU12NebikiTax4(UriMaintenanceCommon.convDecimal(entity.getU12NebikiTax4()));
        info.setU13NebikiKen5(UriMaintenanceCommon.convDecimal(entity.getU13NebikiKen5()));
        info.setU14NebikiKin5(UriMaintenanceCommon.convDecimal(entity.getU14NebikiKin5()));
        info.setU15NebikiTax5(UriMaintenanceCommon.convDecimal(entity.getU15NebikiTax5()));
        info.setU16NebikiKen6(UriMaintenanceCommon.convDecimal(entity.getU16NebikiKen6()));
        info.setU17NebikiKin6(UriMaintenanceCommon.convDecimal(entity.getU17NebikiKin6()));
        info.setU18NebikiTax6(UriMaintenanceCommon.convDecimal(entity.getU18NebikiTax6()));
        info.setU19NebikiKen7(UriMaintenanceCommon.convDecimal(entity.getU19NebikiKen7()));
        info.setU20NebikiKin7(UriMaintenanceCommon.convDecimal(entity.getU20NebikiKin7()));
        info.setU21NebikiTax7(UriMaintenanceCommon.convDecimal(entity.getU21NebikiTax7()));
        info.setU22NebikiKen8(UriMaintenanceCommon.convDecimal(entity.getU22NebikiKen8()));
        info.setU23NebikiKin8(UriMaintenanceCommon.convDecimal(entity.getU23NebikiKin8()));
        info.setU24NebikiTax8(UriMaintenanceCommon.convDecimal(entity.getU24NebikiTax8()));
        info.setU25NebikiKen9(UriMaintenanceCommon.convDecimal(entity.getU25NebikiKen9()));
        info.setU26NebikiKin9(UriMaintenanceCommon.convDecimal(entity.getU26NebikiKin9()));
        info.setU27NebikiTax9(UriMaintenanceCommon.convDecimal(entity.getU27NebikiTax9()));

        info.setFlg1(entity.getFlg1());
        info.setFlg2(entity.getFlg1());
        info.setFlg3(entity.getFlg1());

        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();
        info.setFirstUser(user);                      //登録ユーザ
        info.setFirstPgm(LOGIC_ID.substring(0, 6));   //登録プログラム
        info.setFirstTmsp(currentTimestamp);          //登録時タイムスタンプ
        info.setLastUser(user);                       //修正ユーザ
        info.setLastPgm(LOGIC_ID.substring(0, 6));    //修正プログラム
        info.setLastTmsp(entity.getLastTmsp96());     //修正時タイムスタンプ

        return info;

    }

//add 2019/07/17 USI欒 #34 begin
    /**
     * 現金在高（日次）売上消費税明細TBL(BD66ADUM)Entity設定
     * @param entity
     * @return
     */
    private GetKeigenTaxData setKeigenTaxCopy(GetKeigenTaxData entity, String upNo, String user) {

    	GetKeigenTaxData info = new GetKeigenTaxData();

        info.setCompanyCd(entity.getCompanyCd());
        info.setMiseCd(entity.getMiseCd());
        info.setEigyoDt(entity.getEigyoDt());
        info.setUpFlg("0");
        info.setUpDt(entity.getUpDt());
        info.setBatupDt(entity.getBatupDt());
        info.setUpNo(upNo);

        info.setUriage1(entity.getUriage1());
        info.setUriage2(entity.getUriage2());
        info.setUriage3(entity.getUriage3());
        info.setUriage4(entity.getUriage4());
        info.setUriage5(entity.getUriage5());
        info.setTax1(entity.getTax1());
        info.setTax2(entity.getTax2());
        info.setTax3(entity.getTax3());
        info.setTax4(entity.getTax4());
        info.setTax5(entity.getTax5());

        Timestamp currentTimestamp = DateManager.getCurrentTimestamp();

        info.setFirstUser(user);                      //登録ユーザ
        info.setFirstPgm(LOGIC_ID.substring(0, 6));   //登録プログラム
        info.setFirstTmsp(currentTimestamp);          //登録時タイムスタンプ
        info.setLastUser(user);                       //修正ユーザ
        info.setLastPgm(LOGIC_ID.substring(0, 6));    //修正プログラム
        info.setLastTmsp(entity.getLastTmsp());     //修正時タイムスタンプ

        return info;

    }
 //add 2019/07/17 USI欒 #34 end

    /**
     * 値引き項目にデフォルト値をセットする
     * @param UIUriMainteWorkInfo
     * @param String
     */
    private boolean doNebikiProcess(UIUriMainteWorkInfo entity, String upNo) {
        boolean isChangeNebiki = false;
        //値引き項目に変更があるかチェック
        for (int i = 0; i < upNo.length(); i++) {
            if ("1".equals(upNo.substring(i, i + 1))) {
                isChangeNebiki = true;
                break;
            }
        }
        //現金在高日次修正の値引き項目を編集する
        if (isChangeNebiki) {
            //マイナス商品件数 ：０固定
            entity.setU07MinusKen("0");
            //マイナス商品金額 ：０固定
            entity.setU08MinusKin("0");
            //％値引件数 ：０固定
            entity.setU11PNebikiKen("0");
            //％値引金額 ：０固定
            entity.setU12PNebikiKin("0");
            //値引金額 ：値引計をセット
            entity.setU10NebikiKin(entity.getU04Nebiki());
        }

        return isChangeNebiki;
    }

//  Begin ADD By 周春建 201304-001_売上修正確認画面の集計区分の修正値表示の改修追加のため
   /**
     * 営業日に基づいて集計区分コードを合併
     * @param tempSyukeiCdMap
     */
    // MAPの変換
    // KEY：現行行[集計区分マスタ情報].集計区分コード、営業日 -- > KEY：営業日
    // VALUE：営業日                                               VALUE：件数/金額の修正があったLIST[集計区分コード]
    private Map syukeiKkbn(Map tempSyukeiCdMap) {
    	Map SyukeiCdMap = new LinkedHashMap();
    	List list = new ArrayList(0);
    	Iterator iterator = tempSyukeiCdMap.entrySet().iterator();
    	while (iterator.hasNext()) {
    		Entry entry = (Entry) iterator.next();
    		Object key =entry.getKey();
    		Object value = entry.getValue();
    		//tempSyukeiCdMap中でvalue値があったら、
    		//SyukeiCdMapのkey値中でvalue値があったら、skcdを存在list中に挿入、
    		//SyukeiCdMapのkey値中でvalue値がなし、listを新規作成
    		if (tempSyukeiCdMap.containsValue(value)) {
    			if (SyukeiCdMap.containsKey(value)) {
    				list = (List)SyukeiCdMap.get(value);
    			}else {
    				list = new ArrayList();
    			}
    			List tempSyukeiCd =new ArrayList();
    			tempSyukeiCd = (List)key;
    			list.add(tempSyukeiCd.get(1));
    			SyukeiCdMap.put(value, list);
    		}
    	}
    	return SyukeiCdMap;
    }

    /**
     * 新規登録対象営業日を生成する
     * @param listUri
     * @param listUriPre
     */
    private List getListRegistEigyoDt(List listUri, List listUriPre) {
    	//０．List[[新規登録対象営業日]]を生成します。
		List listRegistEigyoDt = new ArrayList(0);
		for(int i=0; i<listUri.size(); i++) {

			UIUriMainteWorkInfo entity = (UIUriMainteWorkInfo) listUri.get(i);
            UIUriMainteWorkInfo entityPre = (UIUriMainteWorkInfo) listUriPre.get(i);

            //合計レコードは更新対象外
            if(entity.isTotalFlg()) {
                continue;
            }

            //修正前のデータと比較し、修正の有無をチェック
            boolean flg1 = comparePropertyUriPre(entity, entityPre);
            boolean flg2 = comparePropertyToriPre(entity, entityPre);

            //修正前と比較し修正がなければ更新しない
            if( flg1 == false && flg2 == false) {
                continue;
            }
            if(entity.isInsertFlg()) {

                //List[[新規登録対象営業日]]へ新規登録された営業日を保持します。
                listRegistEigyoDt.add(entity.getEigyoDt());
            }
		}
		return listRegistEigyoDt;
    }
//  End ADD By 周春建 201304-001_売上修正確認画面の集計区分の修正値表示の改修追加のため

    public TrnADUPDao getTrnADUPDao() {
        return trnADUPDao;
    }
    public void setTrnADUPDao(TrnADUPDao trnADUPDao) {
        this.trnADUPDao = trnADUPDao;
    }
    public TrnNBUPDao getTrnNBUPDao() {
        return trnNBUPDao;
    }
    public void setTrnNBUPDao(TrnNBUPDao trnNBUPDao) {
        this.trnNBUPDao = trnNBUPDao;
    }
//add 2019/07/15 USI欒 #34 begin
    public GetKeigenTaxDao getKeigenTaxDao() {
        return keigenTaxDao;
    }
    public void setKeigenTaxDao(GetKeigenTaxDao keigenTaxDao) {
        this.keigenTaxDao = keigenTaxDao;
    }
	/**
	 * Dao【会計区分別在高日別修正】取得処理
	 * @return クラス変数uriMaintenanceTrnSyuseiAridakaDao を戻します。
	 */
	public GetKeigenTaxDao getUriMaintenanceGetKeigenTaxDao() {
		return uriMaintenanceGetKeigenTaxDao;
	}
	/**
	 * Dao【会計区分別在高日別修正】設定処理
	 * @param dao を クラス変数uriMaintenanceTrnSyuseiAridakaDaoへ設定します。
	 */
	public void setUriMaintenanceGetKeigenTaxDao(
			GetKeigenTaxDao dao) {
		this.uriMaintenanceGetKeigenTaxDao = dao;
	}
//add 2019/07/15 USI欒 #34 end
	/**
	 * Dao【会計区分別在高修正】取得処理
	 * @return クラス変数uriMaintenanceTrnSyuseiAridakaDao を戻します。
	 */
	public TrnSyuseiAridakaDao getUriMaintenanceTrnSyuseiAridakaDao() {
		return uriMaintenanceTrnSyuseiAridakaDao;
	}
	/**
	 * Dao【会計区分別在高修正】設定処理
	 * @param dao を クラス変数uriMaintenanceTrnSyuseiAridakaDaoへ設定します。
	 */
	public void setUriMaintenanceTrnSyuseiAridakaDao(
			TrnSyuseiAridakaDao dao) {
		this.uriMaintenanceTrnSyuseiAridakaDao = dao;
	}
	/**
	 * @return クラス変数uriMaintenanceKaikeiKbnSearchLogic を戻します。
	 */
	public KaikeiKbnSearchLogic getUriMaintenanceKaikeiKbnSearchLogic() {
		return uriMaintenanceKaikeiKbnSearchLogic;
	}
	/**
	 * @param uriMaintenanceKaikeiKbnSearchLogic を クラス変数uriMaintenanceKaikeiKbnSearchLogicへ設定します。
	 */
	public void setUriMaintenanceKaikeiKbnSearchLogic(
			KaikeiKbnSearchLogic uriMaintenanceKaikeiKbnSearchLogic) {
		this.uriMaintenanceKaikeiKbnSearchLogic = uriMaintenanceKaikeiKbnSearchLogic;
	}

}
