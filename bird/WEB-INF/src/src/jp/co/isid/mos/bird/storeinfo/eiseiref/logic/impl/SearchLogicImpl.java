/**
 * 
 */
package jp.co.isid.mos.bird.storeinfo.eiseiref.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.storeinfo.eiseiref.code.Tab1st;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dao.TrnBd32setbDao;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dao.TrnBd33shtbDao;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dao.TrnBd34vstbDao;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dao.TrnMosplateDao;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.RequestDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.dto.ResultDto;
import jp.co.isid.mos.bird.storeinfo.eiseiref.entity.TrnBd33shtb;
import jp.co.isid.mos.bird.storeinfo.eiseiref.logic.SearchLogic;
import jp.co.isid.mos.bird.storeinfo.eiseiref.util.EiseiRefUtil;


/**
 * LOGIC【検索結果取得】
 * 
 * 作成日:2012/12/07
 * @author xkinu
 *
 */
public class SearchLogicImpl implements SearchLogic {
	/** ロジックID */
	public static final String LOGIC_ID = EiseiRefUtil.SCREEN_ID+"L02";
	/** DAO【PDF総合衛生チェック】*/
	private TrnBd32setbDao trnBd32setbDao;
	/** DAO【PDF店舗衛生情報】*/
	private TrnBd33shtbDao trnBd33shtbDao;
	/** DAO【モスプレート検査】*/
	private TrnMosplateDao trnMosplateDao;
	/** DAO【PDF訪店報告書】*/
	private TrnBd34vstbDao trnBd34vstbDao;
	/**
	 * 実行
	 * @param birdUserInfo
	 * @param birdDateInfo
	 * @param requestDto DTO【Request情報】
	 * @return DTO【検索結果】
	 */
	public ResultDto execute(
			BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
			  , RequestDto requestDto)
	{
		//１．事前条件処理
		validate(birdUserInfo, birdDateInfo, requestDto);
		//２．パラメータ.DTO【Request情報】を引数にDTO【検索結果】生成します。
		ResultDto resultDto = new ResultDto(requestDto);
		//３．CODE【店舗衛生結果タブ定数クラス】.プルダウンリスト取得処理を実行し、List[[検索結果]]を生成します。
		List listResult = Tab1st.getUIList();
		//４．検索処理を実行します。
		//sch-1-1.DAO【PDF総合衛生チェック】.検索を実行し、List[[PDF総合衛生チェック]]を取得します。
		List list32setb = getEiseiRefTrnBd32setbDao().select(
				birdUserInfo.getUserID(), birdUserInfo.getMstUser().getUserTypeCd()
			,   birdUserInfo.isLimit()
			,   requestDto.getCompanyCd(), requestDto.getMiseCd(), requestDto.getNendo()
			,   null);
		//sch-1-2.CODE【店舗衛生結果タブ定数クラス】.対象コードの[UILists]の取得処理を実行し、
		//        UILists[検索結果]を取得します。
		UILists tab1 = Tab1st.getUILists(Tab1st.TAB_1, listResult);
		//sch-1-3.処理sch-1-2のUILists[検索結果].List[[データ]]へ処理sch-1-1List[[PDF総合衛生チェック]]を設定します。
		tab1.setListData(list32setb);
		
		//sch-2-1.DAO【PDF店舗衛生情報チェック】.検索を実行し、List[[PDF店舗衛生情報]]を取得します。
		List list33shtb = getEiseiRefTrnBd33shtbDao().select(
				birdUserInfo.getUserID(), birdUserInfo.getMstUser().getUserTypeCd()
			,   birdUserInfo.isLimit()
			,   requestDto.getCompanyCd(), requestDto.getMiseCd(), requestDto.getNendo());
		//sch-2-2.CODE【店舗衛生結果タブ定数クラス】.対象コードの[UILists]の取得処理を実行し、
		//         UILists[検索結果]を取得します。
		UILists tab2 = Tab1st.getUILists(Tab1st.TAB_2, listResult);
		//sch-2-3.処理sch-2-2のUILists[検索結果].List[[データ]]へ処理sch-1-2List[[PDF店舗衛生情報]]を設定します。
		tab2.setListData(list33shtb);
		
		//sch-3-1.DAO【PDF総合衛生チェック】.検索を実行し、List[[モスプレート検査]]を取得します。
		List listMosplate = getEiseiRefTrnMosplateDao().select(
				birdUserInfo.getUserID(), birdUserInfo.getMstUser().getUserTypeCd()
			,   birdUserInfo.isLimit()
			,   requestDto.getCompanyCd(), requestDto.getMiseCd(), requestDto.getNendo());
		//sch-3-2.CODE【店舗衛生結果タブ定数クラス】.対象コードの[UILists]の取得処理を実行し、
		//        UILists[検索結果]を取得します。
		UILists tab3 = Tab1st.getUILists(Tab1st.TAB_3, listResult);
		//sch-3-3.処理sch-3-2のUILists[検索結果].List[[データ]]へ処理sch-3-1List[[モスプレート検査]]を設定します。
		tab3.setListData(listMosplate);
		
		//sch-4-1.DAO【PDF総合衛生チェック】.検索を実行し、List[[PDF訪店報告書]]を取得します。
		List list34vstb = getEiseiRefTrnBd34vstbDao().select(
				birdUserInfo.getUserID(), birdUserInfo.getMstUser().getUserTypeCd()
			,   birdUserInfo.isLimit()
			,   requestDto.getCompanyCd(), requestDto.getMiseCd(), requestDto.getNendo()
			,   null);
		//sch-4-2.CODE【店舗衛生結果タブ定数クラス】.対象コードの[UILists]の取得処理を実行し、
		//        UILists[検索結果]を取得します。
		UILists tab4 = Tab1st.getUILists(Tab1st.TAB_4, listResult);
		//sch-4-3.処理sch-4-2のUILists[検索結果].List[[データ]]へ処理sch-4-1List[[PDF訪店報告書]]を設定します。
		tab4.setListData(list34vstb);
		//５．boolean[データ存在無フラグ]へtureを設定します。
		boolean isNoResult = true;
		//６．List[[検索結果]]の件数分、下記の処理を行います。
		for (int i=0; i<listResult.size(); i++) {
			//1.List[[検索結果]]の現在値のUILists[検索結果]を取得します。
			UILists searchData = (UILists)listResult.get(i);
			//2.UILists[検索結果].List[[データ]]が1件以上の場合下記の処理を行います。
			if (!searchData.getListData().isEmpty()) {
				//2-1.boolean[データ存在無フラグ]へfalseを設定します。
				isNoResult  = false;
				//2-2.DTO【検索結果】.データ存在フラグへtrueを設定します。
				resultDto.setExistsData(true);
				//2-3.DTO【検索結果】.タブキーへUILists[検索結果].タブキーを設定します。
				resultDto.setTabKey(searchData.getKey());//初期表示タブ値設定
				//2-4.処理６の件数分の処理を終了します。
				break;
			}
		}// end of for(int i=0; i<listResult.size(); i++) 
		//７．処理６のboolean[データ存在無フラグ]がtrueの場合、Exceptionを発生させます。
		if(isNoResult) {
			//該当データなしのExceptionを発生させます。
			throw new NoResultException("");
		}
		//８．List[[PDF店舗衛生情報]]が0件の場合、ヘッダー情報の店情報を取得するため下記の処理を行います。
		if(list33shtb.isEmpty()) {
			//1.DAO【PDF店舗衛生情報チェック】.検索店情報を実行し、List[[PDF店舗衛生情報]]を取得します。
			List headerData = getEiseiRefTrnBd33shtbDao().selectMiseInfo(
					birdUserInfo.getUserID(), birdUserInfo.getMstUser().getUserTypeCd()
			,   birdUserInfo.isLimit()
			,   requestDto.getCompanyCd(), requestDto.getMiseCd(), requestDto.getNendo());
			//2.処理1のList[[PDF店舗衛生情報]]インデックス0番目のTrnBd33shtb[PDF店舗衛生情報]を
			//  DTO【検索結果】.TrnBd33shtb[PDF店舗衛生情報店情報]へ設定します。
			resultDto.setBd33shtbMiseInfo( (TrnBd33shtb) (headerData.get(0)) );
		}
		//９．DTO【検索結果】.List[[検索結果]]へ処理３のList[[検索結果]]を設定します。
		resultDto.setListData(listResult);
		//10．DTO【検索結果】をリターンする。
		return resultDto;
	}
    
	/**
	 * 事前条件処理
	 * 
	 * @param dto
	 */
	private void validate(BirdUserInfo birdUserInfo, BirdDateInfo birdDateInfo
			  , RequestDto requestDto) {
		if (CommonUtil.isNull(birdUserInfo.getMstUser().getUserTypeCd())) {
			throw new MissingDataException("ユーザータイプコード");
		}
		if (CommonUtil.isNull(birdUserInfo.getUserID())) {
			throw new MissingDataException("ユーザーID");
		}
		String companyCd = requestDto.getCompanyCd();
		if (CommonUtil.isNull(companyCd)) {
			throw new MissingDataException("会社コード");
		}
		//対象店舗
		if(CommonUtil.isNull(requestDto.getMiseCd())) {
			throw new NotNullException("店コード", "miseCd", 0);
		}
		boolean isAlphabet = true;
        //対象条件が全社以外の場合表示対象コードチェック
        CodeVerifier codeVeri = new CodeVerifier(isAlphabet);
        //コードフォーマットチェック
        if(!codeVeri.validate(requestDto.getMiseCd()) || requestDto.getMiseCd().length() > 5) {
            throw new InvalidInputException("店コード", "miseCd", 0);
        }
        //対象店舗前ゼロ付加処理
		CodeFormatter cdf = new CodeFormatter(5, "00000");
        cdf.setFormatPattern("00000");
        requestDto.setMiseCd(cdf.format(requestDto.getMiseCd(), true));
        
		String nendo = requestDto.getNendo();
		if (CommonUtil.isNull(nendo)) {
			throw new NotNullException("実施年度");
		}
	}

	/**
	 * @return クラス変数trnBd32setbDao を戻します。
	 */
	public TrnBd32setbDao getEiseiRefTrnBd32setbDao() {
		return trnBd32setbDao;
	}

	/**
	 * @param dao を クラス変数trnBd32setbDaoへ設定します。
	 */
	public void setEiseiRefTrnBd32setbDao(TrnBd32setbDao dao) {
		this.trnBd32setbDao = dao;
	}

	/**
	 * @return クラス変数trnBd33shtbDao を戻します。
	 */
	public TrnBd33shtbDao getEiseiRefTrnBd33shtbDao() {
		return trnBd33shtbDao;
	}

	/**
	 * @param dao を クラス変数trnBd33shtbDaoへ設定します。
	 */
	public void setEiseiRefTrnBd33shtbDao(TrnBd33shtbDao dao) {
		this.trnBd33shtbDao = dao;
	}

	/**
	 * @return クラス変数trnBd34vstbDao を戻します。
	 */
	public TrnBd34vstbDao getEiseiRefTrnBd34vstbDao() {
		return trnBd34vstbDao;
	}

	/**
	 * @param dao を クラス変数trnBd34vstbDaoへ設定します。
	 */
	public void setEiseiRefTrnBd34vstbDao(TrnBd34vstbDao dao) {
		this.trnBd34vstbDao = dao;
	}

	/**
	 * @return クラス変数trnMosplateDao を戻します。
	 */
	public TrnMosplateDao getEiseiRefTrnMosplateDao() {
		return trnMosplateDao;
	}

	/**
	 * @param dao を クラス変数trnMosplateDaoへ設定します。
	 */
	public void setEiseiRefTrnMosplateDao(TrnMosplateDao dao) {
		this.trnMosplateDao = dao;
	}
}
