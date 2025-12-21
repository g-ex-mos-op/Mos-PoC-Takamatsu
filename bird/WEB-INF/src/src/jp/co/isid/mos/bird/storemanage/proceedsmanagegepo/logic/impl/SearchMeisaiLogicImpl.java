package jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlKaikeiKbnInfoDao;
import jp.co.isid.mos.bird.common.kaikei.dao.CtlSyukeiKbnDao;
import jp.co.isid.mos.bird.common.kaikei.entity.CtlKaikeiKbnInfo;
import jp.co.isid.mos.bird.common.kaikei.entity.GetterMethod;
import jp.co.isid.mos.bird.common.kaikei.entity.TrnAridakaMeisai;
import jp.co.isid.mos.bird.common.kaikei.entity.UIKaikeiMeisai;
import jp.co.isid.mos.bird.common.kaikei.util.KaikeiUtil;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsCommon;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.common.ProceedsConstants;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dao.TrnAridakaMeisaiDao;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.MeisaiRequestResultDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.dto.ProceedsManageGepoDto;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.MeisaiTabInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.entity.UserCompanyInfo;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.MstMiseInfoLogic;
import jp.co.isid.mos.bird.storemanage.proceedsmanagegepo.logic.SearchMeisaiLogic;

/**
 * 売上金管理月報明細検索ロジック
 * @author xogawa
 *
 */
public class SearchMeisaiLogicImpl implements SearchMeisaiLogic{

    /** 売上金管理月報明細検索ロジックID */
    public static final String LOGIC_ID = "BSM013L06";
	
    /** 会計集計区分情報 */
    private CtlSyukeiKbnDao ctlSyukeiKbnDao;
    
    /** 会計区分情報 */
    private CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao;
	
    /** 会計区分別在高情報 */ 
    private TrnAridakaMeisaiDao trnAridakaMeisaiDao;

    /** 会計区分系共通定数クラス */
    private KaikeiUtil kaikeiUtil;

    /** 対象店舗情報取得ロジック */
    private MstMiseInfoLogic mstMiseInfoLogic;


    // 画面表示用キー
    /** タブ別ヘッダー */
    private static final String MK_HEADER = "HEADER";
    /** タブ別データ */
	private static final String MK_DATA = "DATA";

    /**
     * 明細検索
     * @param proceedsManageGepoDto 売上金管理月報・条件部DTOクラス
     * @param meisaiRequestJokenDto 売上金管理月報明細・DTOクラス
     * @param meisaiRequestResultDto 売上金管理月報明細照会・結果部DTOクラス
     */
    public void execute(
    		ProceedsManageGepoDto proceedsManageGepoDto
		  , MeisaiRequestDto meisaiRequestJokenDto
		  , MeisaiRequestResultDto meisaiRequestResultDto){

        // 売上金管理月報・結果部DTOを空にする
        proceedsManageGepoDto.setCommonTransitionMeisaiResultWindowId(meisaiRequestJokenDto.getWindowId(),null);
        
    	// 0.事前条件処理
    	validate(proceedsManageGepoDto,meisaiRequestJokenDto,meisaiRequestResultDto);
    	
        // 1．会計区分別在高検索を実行、検索結果を取得
        List result = getTrnAridakaMeisaiDao().select(
        		proceedsManageGepoDto.getSysDate(), proceedsManageGepoDto.getUserId()
        	  , proceedsManageGepoDto.getUserType(), meisaiRequestJokenDto.getCompanyCd()
        	  , meisaiRequestJokenDto.getTaishoTenpoCd(), meisaiRequestJokenDto.getTaishoYM()
        	  , proceedsManageGepoDto.isLimitKbn()
        	  );
        
        // 2.結果チェック
        if (result == null || result.isEmpty()){
        	throw new NoResultException();
        }
        if (ProceedsCommon.isNull(((TrnAridakaMeisai)result.get(0)).getEigyoDt())){
        	throw new NoResultException();
        }
        
        // 3.集計区分別会計区分情報から集計区分情報を取得
        UILists syukeiKbnList = proceedsManageGepoDto.getKaikeiMasterInfoSyukeiKbn(meisaiRequestJokenDto.getSyukeiCd());
        
        // 4.明細条件を明細結果へ設定
        meisaiRequestResultDto.setWindowId(meisaiRequestJokenDto.getWindowId());
        meisaiRequestResultDto.setCompanyCd(meisaiRequestJokenDto.getCompanyCd());        
        meisaiRequestResultDto.setTaishoTenpoCd(meisaiRequestJokenDto.getTaishoTenpoCd());
        meisaiRequestResultDto.setTaishoYM(meisaiRequestJokenDto.getTaishoYM());
        meisaiRequestResultDto.setSyukeiCd(meisaiRequestJokenDto.getSyukeiCd());
        meisaiRequestResultDto.setTabNo(meisaiRequestJokenDto.getTabNo());
        meisaiRequestResultDto.setSyukeiNm(syukeiKbnList.getKeyName());
        String taishoTenpoName = "";
        
        List companyList = proceedsManageGepoDto.getCompanyList();
        
        for(Iterator it = companyList.iterator(); it.hasNext();){
        	UserCompanyInfo company = (UserCompanyInfo)it.next();
        	if (meisaiRequestJokenDto.getCompanyCd().equals(company.getCompanyCd())){
        		meisaiRequestResultDto.setCompanyNm(company.getCompanyName());
        		break;
        	}
        }
        
        
        // 全店検索の場合、全店固定
        if (ProceedsConstants.ZENTEN_CD.equals(meisaiRequestJokenDto.getTaishoTenpoCd())) {
            taishoTenpoName = ProceedsConstants.ZENTEN_NAME;
        } else {
            taishoTenpoName = getMstMiseInfoLogic().getMiseCdName(
                    meisaiRequestJokenDto.getCompanyCd(), meisaiRequestJokenDto.getTaishoTenpoCd()
            );
        }
        meisaiRequestResultDto.setTaishoTenpo(taishoTenpoName);
        
        // 5.タブ別明細情報作成を実行し、タブ別情報を設定
        meisaiRequestResultDto.setTabDataList(createTabData(syukeiKbnList,null,result));
                
        // 売上金管理月報・結果部DTOに検索結果を保持する
        proceedsManageGepoDto.setCommonTransitionMeisaiResultWindowId(meisaiRequestResultDto.getWindowId(),meisaiRequestResultDto);
    }
    
    
	/**
	 * 0.事前条件処理
	 * @param proceedsManageGepoDto 売上金管理月報・条件部DTOクラス
	 * @param meisaiRequestJokenDto 売上金管理月報明細照会・条件部DTOクラス
	 * @param meisaiRequestResultDto 売上金管理月報明細照会・結果部DTOクラス
	 * 更新日:2014/12/11 周春建　会計区分拡張対応
	 */
	private void validate(
			ProceedsManageGepoDto proceedsManageGepoDto
		  , MeisaiRequestDto meisaiRequestJokenDto
		  , MeisaiRequestResultDto meisaiRequestResultDto){
		
	// セッション情報チェック
		if(ProceedsCommon.isNull(proceedsManageGepoDto.getUserId())){
			throw new NotNullException(ProceedsConstants.MSG_USER_ID);
		}
		if(ProceedsCommon.isNull(proceedsManageGepoDto.getUserType())){
			throw new NotNullException(ProceedsConstants.MSG_USER_TYPE);
		}
		if(proceedsManageGepoDto.getUserType().equals(ProceedsConstants.MISE)){
			if(ProceedsCommon.isNull(meisaiRequestJokenDto.getTaishoTenpo())){
				throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO);
			}
		}

	// マスタ情報チェック
		
		// 会計区分情報取得
		//List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(ProceedsConstants.BIRD_DISP_FLG_ON,meisaiRequestJokenDto.getCompanyCd());
		if(ProceedsCommon.isNull(meisaiRequestJokenDto.getCompanyCd())){
			throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD,"companyCd",0);
		}
		//会計区分マスタの抽出条件には会社コードも含める
		List kaikeiKbnList = getCtlKaikeiKbnInfoDao().select(ProceedsConstants.BIRD_DISP_FLG_ON,meisaiRequestJokenDto.getCompanyCd());
		if(kaikeiKbnList == null || kaikeiKbnList.isEmpty()){
			throw new NoResultException();			
		}
		
		// 集計区分情報取得
		List syukeiKbnList = getCtlSyukeiKbnDao().select(ProceedsConstants.BIRD_DISP_FLG_ON);
		if(syukeiKbnList == null || syukeiKbnList.isEmpty()){
			throw new NoResultException();
		}
		
		// 集計区分別会計区分マスタ情報
		Map syukeiKbnMaster = KaikeiUtil.makeKaikeiSyukeiKbnInfo(syukeiKbnList,kaikeiKbnList);

		//4.セッションへ格納
		proceedsManageGepoDto.setKaikaiKbnList(kaikeiKbnList);
		proceedsManageGepoDto.setKaikeiMasterInfo(syukeiKbnMaster);
		
	// 入力値チェック	  
        if (!syukeiKbnMaster.containsKey(meisaiRequestJokenDto.getSyukeiCd())) {
            // 集計区分および紐付く会計区分が存在しない場合、データなしエラー
            throw new NoResultException();
        }
        
//		if(ProceedsCommon.isNull(meisaiRequestJokenDto.getCompanyCd())){
//			throw new NotNullException(ProceedsConstants.MSG_COMPANY_CD,"companyCd",0);
//		}
		if(ProceedsCommon.isNull(meisaiRequestJokenDto.getTaishoYM())){
			throw new NotNullException(ProceedsConstants.MSG_TAISHO_YM,"taishoYM",0);
		}

		String taisyoTenpoCd = meisaiRequestJokenDto.getTaishoTenpoCd();
		
		if(ProceedsCommon.isNull(taisyoTenpoCd)){
			if(proceedsManageGepoDto.getUserType().equals(ProceedsConstants.HONBU)){
                throw new NotNullException(ProceedsConstants.MSG_TAISHO_TENPO,"taishoTenpoCd",0);
			}else{
                taisyoTenpoCd = ProceedsConstants.ZENTEN_CD;
			}
		}

		if(!taisyoTenpoCd.equals(ProceedsConstants.ZENTEN_CD)){
			CodeVerifier codeVeri = new CodeVerifier(true);
            if(!codeVeri.validate(taisyoTenpoCd) || taisyoTenpoCd.length() > 5) {
                throw new InvalidInputException(ProceedsConstants.MSG_TAISHO_TENPO, "taishoTenpoCd", 0);
            }
            CodeFormatter cdf = new CodeFormatter(5, "00000");
            cdf.setFormatPattern("00000");
            taisyoTenpoCd = cdf.format(taisyoTenpoCd, true);
		}
        meisaiRequestJokenDto.setTaishoTenpoCd(taisyoTenpoCd);
	}
	
	/**
	 * タブ別情報作成取得処理
	 * @param syukeiKbn 集計区分情報
	 * @param targetTabNo タブNo
	 * @param kaikeiAridaka 会会計区分別在高情報
	 * @return List タブ別情報
	 */
	private List createTabData(UILists syukeiKbn, String targetTabNo, List kaikeiAridaka) {
		// 1．タブ別情報を生成
		List tabData = new ArrayList();
		
		// 2.タブ番号リストを生成
		List tabNoList = new ArrayList(0);
		
		// 3．タブNo別処理

		CodeFormatter codefm = new CodeFormatter(2);
		codefm.setFormatPattern("00");
		
		// null又は""（空）の場合
		if(CommonUtil.isNull(targetTabNo)) {
			int iKKbnCnt = syukeiKbn.getListData().size();

			// UIList[集計区分情報]．List[[会計区分]]の件数÷４（余りがある場合は＋１）
			int tabCnt = (iKKbnCnt/4)+(iKKbnCnt%4>0?1:0);
			
			// タブ番号リストへ （for文インデックス+1)を前ゼロ付加で設定
			for(int t=1; t<=tabCnt; t++) {
				tabNoList.add(codefm.format(String.valueOf(t), true));
			}
			
		}else{
			// タブリストへタブNoを設定
			tabNoList.add(codefm.format(targetTabNo, true));
		}

		// タブ別情報
		MeisaiTabInfo mTab = null;
		
		// 5．
		for(int t=0; t<tabNoList.size(); t++) {
			// タブ別ヘッダー、タブ別データ情報
			String tabNo = (String)tabNoList.get(t);
			String tabName = "その"+(t+1);
			UILists uiTabHeader = new UILists(tabNo, tabName);
			UILists uiTabData = new UILists(tabNo, tabName);
			mTab = new MeisaiTabInfo();

			try {
				// 画面別レイアウト用情報作成処理を実行、取得
				Map createData = createScreenLayoutData(syukeiKbn.getListData(),(String)tabNoList.get(t),kaikeiAridaka);
			
				// 画面表示会計区分名称を"HEADER"をキーに設定します。
				uiTabHeader.setListData((List)createData.get(MK_HEADER));
				// 会計区分明細情報を"DATA"をキーに設定
				uiTabData.setListData((List)createData.get(MK_DATA));	
			}
			catch (Exception ex) {
				throw new FtlSystemException("売上金管理月報明細画面：会計区分明細作成", null, ex);
			}
			
			// タブ別ヘッダー設定
			mTab.setUiTabHeader(uiTabHeader);		
			
			// タブ別データ設定
			mTab.setUiTabData(uiTabData);			

			// 6．タブ別情報を設定
			tabData.add(mTab);
		}
		
		return tabData;
	}
	
	
	/**
	 * 画面レイアウト用情報作成処理
	 * @param kaikeiKbn 会計区分情報
	 * @param targetTabNo タブNo
	 * @param kaikeiAridaka 会計区分別在高
	 * @return Map 画面レイアウト情報
	 * @throws Exception　FtlSystemException
	 */
	private Map createScreenLayoutData(List kaikeiKbn, String targetTabNo, List kaikeiAridaka) throws Exception {

		// 0.戻り値：取得情報を生成
		Map screenLayoutData = new HashMap();

		// 1.画面表示会計区分リストを生成
		List kaikeiKbnScreen = new ArrayList(0);
		
		// 2.画面表示会計区分情報取得処理を実行、画面表示情報を取得
		Map screenInf = KaikeiUtil.getScreenDispInfo(kaikeiKbn, targetTabNo);
		
		// 3.画面表示会計区分を"CODE"をキーに設定
		kaikeiKbnScreen = (List)screenInf.get(KaikeiUtil.MK_CODE);

		// 4.取得情報へ画面表示会計区分名称を"NAME"をキーに設定
		screenLayoutData.put(MK_HEADER, (List)screenInf.get(KaikeiUtil.MK_NAME));

		// 5.Getterメソッドを生成
		Map getterMethod = KaikeiUtil.getMapGetterMethod(kaikeiKbn,kaikeiAridaka.get(0).getClass(), "Kaikei");
		/**/
		// 6.会計区分明細情報を生成
		List kaikeiKbnMeisai = new ArrayList(0);

		// 7.会計区分明細情報を設定
		for(int i=0; i<kaikeiAridaka.size(); i++) {
			// 会計区分別在高情報
			TrnAridakaMeisai trnKaikeiAridaka = (TrnAridakaMeisai)kaikeiAridaka.get(i);
			// 会計区分明細情報
			UIKaikeiMeisai kaikeiMeisaiEntity = new UIKaikeiMeisai();
			// 合計件数
			BigDecimal totalKensu = new BigDecimal("0");
			// 合計金額
			BigDecimal totalKingaku = new BigDecimal("0");
			
			// 会社コード
			kaikeiMeisaiEntity.setCompanyCd(trnKaikeiAridaka.getCompanyCd());
			// 対象店舗名称
			kaikeiMeisaiEntity.setTaishoName(trnKaikeiAridaka.getTaishoName());
			// 営業日
			kaikeiMeisaiEntity.setEigyoDt(trnKaikeiAridaka.getEigyoDt());
			
			// 会計区分リスト：合計件数、合計金額取得
			for(int j=0; j<kaikeiKbn.size(); j++){
				// 会計区分明細情報エンティティ取得
				CtlKaikeiKbnInfo kaikeiKbnEntity = (CtlKaikeiKbnInfo)kaikeiKbn.get(j);
				// getterメソッドを設定
				GetterMethod getPramMtd = (GetterMethod)getterMethod.get(kaikeiKbnEntity.getKkbnCd());
				// 合計件数 = 合計件数 + 件数Getterメソッド返却値
				totalKensu = totalKensu.add((BigDecimal)getPramMtd.getMethodKen().invoke(trnKaikeiAridaka, new Object[]{}));
				// 合計金額 = 合計金額 + 金額Getterメソッド返却値
				totalKingaku = totalKingaku.add((BigDecimal)getPramMtd.getMethodKin().invoke(trnKaikeiAridaka, new Object[]{}));
			}
			
			// 合計件数設定 
			kaikeiMeisaiEntity.setMeisaiKenTotal(totalKensu);
			// 合計金額設定
			kaikeiMeisaiEntity.setMeisaiKinTotal(totalKingaku);
			
			// 対象会計区分：件数、金額取得
			for(int k=0; k<kaikeiKbnScreen.size(); k++){
				// 会計区分コード
				String kaikeiKbnCd = (String)kaikeiKbnScreen.get(k);
				
				// getterメソッド取得
				GetterMethod getScPramMtd = (GetterMethod)getterMethod.get(kaikeiKbnCd);

				// 件数のgetterメソッド返却値を設定
				BigDecimal scTotalKensu = (BigDecimal)getScPramMtd.getMethodKen().invoke(trnKaikeiAridaka, new Object[]{});
				// 件数のgetterメソッド返却値を設定
				BigDecimal scTotalKingaku = (BigDecimal)getScPramMtd.getMethodKin().invoke(trnKaikeiAridaka, new Object[]{});
				
				// 画面表示に件数、金額を設定
				if(k==0){
					// 処理インデックス0の場合
					kaikeiMeisaiEntity.setExistsMeisai1(true);
					kaikeiMeisaiEntity.setMeisaiKen1(scTotalKensu);
					kaikeiMeisaiEntity.setMeisaiKin1(scTotalKingaku);
				}
				if(k==1){
					// 処理インデックス1の場合
					kaikeiMeisaiEntity.setExistsMeisai2(true);
					kaikeiMeisaiEntity.setMeisaiKen2(scTotalKensu);
					kaikeiMeisaiEntity.setMeisaiKin2(scTotalKingaku);
				}
				if(k==2){
					// 処理インデックス2の場合
					kaikeiMeisaiEntity.setExistsMeisai3(true);
					kaikeiMeisaiEntity.setMeisaiKen3(scTotalKensu);
					kaikeiMeisaiEntity.setMeisaiKin3(scTotalKingaku);
				}
				if(k==3){
					// 処理インデックス3の場合
					kaikeiMeisaiEntity.setExistsMeisai4(true);
					kaikeiMeisaiEntity.setMeisaiKen4(scTotalKensu);
					kaikeiMeisaiEntity.setMeisaiKin4(scTotalKingaku);
				}
			}
			
			// 会計区分明細情報を設定
			kaikeiKbnMeisai.add(kaikeiMeisaiEntity);

		} // End for(int i=0; i<kaikeiAridaka.size(); i++)
	
		// 取得情報へ"DATA"をキーに会計区分別明細情報を設定
		screenLayoutData.put(MK_DATA,kaikeiKbnMeisai);
		
		return screenLayoutData;
	}
	
	
	/**
	 * 会計集計区分情報を取得する
	 * @return ctlSyukeiKbnDao 会計集計区分情報
	 */
	public CtlSyukeiKbnDao getCtlSyukeiKbnDao() {
		return ctlSyukeiKbnDao;
	}

	/**
	 * 会計集計区分情報を設定する
	 * @param ctlSyukeiKbnDao 会計集計区分情報
	 */
	public void setCtlSyukeiKbnDao(CtlSyukeiKbnDao ctlSyukeiKbnDao) {
		this.ctlSyukeiKbnDao = ctlSyukeiKbnDao;
	}

	/**
	 * 会計区分情報を取得する
	 * @return ctlKaikeiKbnInfoDao 会計区分情報
	 */
	public CtlKaikeiKbnInfoDao getCtlKaikeiKbnInfoDao() {
		return ctlKaikeiKbnInfoDao;
	}

	/**
	 * 会計区分情報を設定する
	 * @param ctlKaikeiKbnInfoDao 会計区分情報
	 */
	public void setCtlKaikeiKbnInfoDao(CtlKaikeiKbnInfoDao ctlKaikeiKbnInfoDao) {
		this.ctlKaikeiKbnInfoDao = ctlKaikeiKbnInfoDao;
	}


	/**
	 * 会計区分系共通定数クラスを取得する
	 * @return kaikeiUtil 会計区分系共通定数クラス
	 */
	public KaikeiUtil getKaikeiUtil() {
		return kaikeiUtil;
	}


	/**
	 * 会計区分系共通定数クラスを設定する
	 * @param kaikeiUtil 会計区分系共通定数クラス
	 */
	public void setKaikeiUtil(KaikeiUtil kaikeiUtil) {
		this.kaikeiUtil = kaikeiUtil;
	}


	/**
	 * 会計区分別在高情報を取得する
	 * @return trnAridakaMeisaiDao 会計区分別在高情報
	 */
	public TrnAridakaMeisaiDao getTrnAridakaMeisaiDao() {
		return trnAridakaMeisaiDao;
	}


	/**
	 * 会計区分別在高情報を設定する
	 * @param trnAridakaMeisaiDao 会計区分別在高情報
	 */
	public void setTrnAridakaMeisaiDao(TrnAridakaMeisaiDao trnAridakaMeisaiDao) {
		this.trnAridakaMeisaiDao = trnAridakaMeisaiDao;
	}


	/**
	 * 対象店舗情報取得ロジックを取得する
	 * @return mstMiseInfoLogic 対象店舗情報取得ロジック
	 */
	public MstMiseInfoLogic getMstMiseInfoLogic() {
		return mstMiseInfoLogic;
	}


	/**
	 * 対象店舗情報取得ロジックを設定する
	 * @param mstMiseInfoLogic 対象店舗情報取得ロジック
	 */
	public void setMstMiseInfoLogic(MstMiseInfoLogic mstMiseInfoLogic) {
		this.mstMiseInfoLogic = mstMiseInfoLogic;
	}
	
}
