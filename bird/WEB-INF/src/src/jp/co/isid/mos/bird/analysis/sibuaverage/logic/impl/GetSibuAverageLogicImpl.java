package jp.co.isid.mos.bird.analysis.sibuaverage.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.analysis.sibuaverage.code.ShukeiKbn;
import jp.co.isid.mos.bird.analysis.sibuaverage.dao.UISuiiDataDao;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.dto.SibuAverageReqDto;
import jp.co.isid.mos.bird.analysis.sibuaverage.entity.UIOnerInfo;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.ChangeOnerLogic;
import jp.co.isid.mos.bird.analysis.sibuaverage.logic.GetSibuAverageLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.MstMiseDao;
import jp.co.isid.mos.bird.common.entity.MstMise;
import jp.co.isid.mos.bird.common.entity.UILists;
import jp.co.isid.mos.bird.common.util.CommonUtil;
import jp.co.isid.mos.bird.framework.exception.NoResultException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;


/**
 * 支部平均比較情報 ロジック
 *  戻り値：true 検索完了　　false：支部選択状態
 * @author xnkusama
 * 
 * 更新日:2012/11/02 xkinu 検索内容変更対応 集計区分追加
 *
 */
public class GetSibuAverageLogicImpl implements GetSibuAverageLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT006L02";

    /**DAO*/
    private MstMiseDao mstMiseDao;
    private UISuiiDataDao sibuaverageSuiiDataDao;
    
    /**LOGIC【オーナー変更ロジック】**/
    private ChangeOnerLogic sibuaverageChangeOnerLogic;
    /**
     * 実行処理
     */
    public boolean execute(SibuAverageDto sessionDto, SibuAverageReqDto requestDto) {
        //１．事前条件処理
        validate(sessionDto, requestDto);
        //２．DTO【Session情報】.検索済フラグへfalseを設定します。
        sessionDto.setSearchedFlg(requestDto.getWindowId(), false);
        //３．新規でDTO【検索結果】を生成します。
        SibuAverageReqDto viewDto = new SibuAverageReqDto();
        //４．DTO【検索結果】.ウィンドウIDへDTO【Request情報】.ウィンドウIDを設定します。
        viewDto.setWindowId(requestDto.getWindowId());
        //５.DTO【Session情報】.DTO【検索結果】へ処理５のDTO【検索結果】を設定します。
    	sessionDto.setSearchedDataDto(viewDto);
        //６．DTO【Request情報】.対象条件＝”個店(店舗)”の場合、下記の処理を行います。
        MstMise mstMise = null;
        if (TaishoJoken.CODE_MISE.equals(requestDto.getTaishoJoken())) {
            //1.【DAO】店マスタ．店マスタ情報取得を実行、戻り値MstMise[店マスタ]を取得します。
            mstMise = getMstMiseDao().selectMise(requestDto.getCompanyCd(), requestDto.getHyojiTaisho());
            //2.処理1のMstMise[店マスタ]がnullの場合はExceptionを発生させます。
            if (mstMise == null) {
                throw new NoResultException();
            }
        }
        //７．支部情報の設定を行います。
        settingSibuCd(sessionDto, requestDto, mstMise);
        //８．DTO【Request情報】.対象支部がnull又は''(空)の場合処理を終了します。
        if (CommonUtil.isNull(requestDto.getTaishoSibu())) {
            return false;
        }
        //９．DAO【支部平均比較情報】．検索を実行し、List[[検索結果]]を取得します。
        List listData = getSibuaverageSuiiDataDao()
                            .selectNipo(requestDto.getCompanyCd(),
                                            requestDto.getTaishoJoken(),
                                            requestDto.getHyojiTaisho(),
                                            requestDto.getShukeiKbn(),
                                            requestDto.getTaishoSibu(),
                                            requestDto.getKikanSitei() + "01",
                                            requestDto.getKikanSitei() + "31",
                                            requestDto.getZenDataShu(),
                                            sessionDto.getBirdUserInfo().isLimit(),
                                            sessionDto.getUserTypeCd(),
                                            sessionDto.getUserId());        
        //10．処理９のList[[検索結果]]が該当データなしの場合、下記の処理を行います。
        if (listData == null || listData.isEmpty()) {
        	//8-1.Exceptionを発生させます。
            throw new NoResultException();
        }
        //11.DAO【支部平均比較情報】．月合計検索実行し、処理９のList[[検索結果]]の最後尾へ格納します。
        //(※月合計の値を営業日報の月次の平均値と同じにするため別ロジックで取得するようにしています。)
        listData.addAll(getSibuaverageSuiiDataDao()
        .selectGepo(requestDto.getCompanyCd(),
                        requestDto.getTaishoJoken(),
                        requestDto.getHyojiTaisho(),
                        requestDto.getShukeiKbn(),
                        requestDto.getTaishoSibu(),
                        requestDto.getKikanSitei(),
                        requestDto.getZenDataShu(),
                        sessionDto.getBirdUserInfo().isLimit(),
                        sessionDto.getUserTypeCd(),
                        sessionDto.getUserId()));
        
        //12．処理３のDTO【検索結果】へDTO【Request情報】の検索条件を設定します。
        viewDto.setCompanyCd(requestDto.getCompanyCd());
        viewDto.setTargetOnerCd(requestDto.getTargetOnerCd());
        viewDto.setTaishoJoken(requestDto.getTaishoJoken());
       	viewDto.setHyojiTaishoMise(requestDto.getHyojiTaishoMise());
       	viewDto.setHyojiTaishoOner(requestDto.getHyojiTaishoOner());
        viewDto.setShukeiKbn(requestDto.getShukeiKbn());
        viewDto.setTaishoSibu(requestDto.getTaishoSibu());
        viewDto.setKikanSitei(requestDto.getKikanSitei());
        viewDto.setZenDataShu(requestDto.getZenDataShu());
        viewDto.setListShukeiKbn(requestDto.getListShukeiKbn());
        
        //13．処理３のDTO【検索結果】へ検索結果ヘッダ情報をセットします。
        setHeaderInfo(sessionDto, viewDto, mstMise, listData);
        
        //14．処理３のDTO【検索結果】.List[[検索結果]]へ処理９のList[[検索結果]]を設定します。
        viewDto.setListData(listData);
        //15．DTO【Session情報】.DTO【検索結果】へ処理３のDTO【検索結果】を設定します。
        sessionDto.setSearchedDataDto(viewDto);
        //16.trueをリターンします。
        return true;
    }


    /**
     * 対象条件ごとに必要な情報を取得
     * ログインユーザーが本部の場合のみ実行される。
     * @param dto
     * @param requestDto
     * @return 対象オーナーコード
     */
    private void settingSibuCd(SibuAverageDto sessionDto, SibuAverageReqDto requestDto, MstMise mstMise) {
        // DTO【Request情報】.対象条件＝”個店(店舗)”の場合、下記の処理を行います。
        if (TaishoJoken.CODE_MISE.equals(requestDto.getTaishoJoken())) {
            // 支部コード設定
            if(ShukeiKbn.IN_RC.equals(requestDto.getShukeiKbn())) {
            	requestDto.setTaishoSibu(mstMise.getAreaDai());
            }
            else {
            	requestDto.setTaishoSibu(mstMise.getSibuCd());
            }
            if (UserType.isHonbu(sessionDto.getUserTypeCd())) {
	        	//対象オーナーコード設定
	            requestDto.setTargetOnerCd(mstMise.getOnerCd());
	        }
        }
        //2.DTO【Session情報】．ユーザータイプコード＝”本部”の場合、下記の処理を行います。
        if (UserType.isHonbu(sessionDto.getUserTypeCd())) {
        	//2-1.DTO【Request情報】.対象条件＝”オーナー”の場合、下記の処理を行います。
	        if(TaishoJoken.CODE_ONER.equals(requestDto.getTaishoJoken())) {
	        	//oner-1.DTO【Request情報】.対象オーナーコードへDTO【Request情報】.表示対象オーナーコードを設定します。
		        requestDto.setTargetOnerCd(requestDto.getHyojiTaishoOner());
		        //oner-2.DTO【Request情報】.対象支部コードがnull(又は''(空))の場合、下記の設定を行います。
		        if(CommonUtil.isNull(requestDto.getTaishoSibu())) {
		        	//DTO【Request情報】.支部プルダウン表示モードへ”false”を設定します。
		        	requestDto.setSibuMode(false);
		        	//DTO【Request情報】.対象支部名称をクリアします。
			        requestDto.setTaishoSibuDisp("");
		        }
	        }
	        //2-2．変数.対象オーナーコードへDTO【Request情報】.対象オーナーコードを設定します。
	        String targetOnerCd = requestDto.getTargetOnerCd();
	        //2-3.変数.対象オーナーコードをキーにDTO【Session情報】.List[[集計区分]]を取得し、
	        //  値がnullの場合は下記の処理を行います。
	        if(sessionDto.getListShukeiKbn(targetOnerCd) == null) {
	        	//2-3-1．LOGIC【オーナー変更処理】を実行します。
	        	getSibuaverageChangeOnerLogic().execute(sessionDto, requestDto);
	        }
	        
	        //2-4.DTO【Request情報】.対象条件＝”オーナー”かつ
	        //    DTO【Request情報】.対象支部コードがnull(又は''(空))の場合、下記の設定を行います。
	        if(TaishoJoken.CODE_ONER.equals(requestDto.getTaishoJoken())
	        		&& CommonUtil.isNull(requestDto.getTaishoSibu()))
	        {
	        	//2-4-1. 定数クラス【集計区分】.対象コードの[UILists]の取得処理を実行し、UILists[集計区分]を取得します。
		        UILists skbnData = ShukeiKbn.getUILists(
		        		requestDto.getShukeiKbn()
		        		, sessionDto.getListShukeiKbn(targetOnerCd));
		        //2-4-2. UILists[集計区分].List[[データ]]を取得します。
		        List listOnerSibu = skbnData.getListData();
		        //2-4-3.UILists[集計区分].List[[データ]]の件数が２件以上の場合、DTO【Request情報】.支部プルダウン表示モードへtrueを設定します。
		        UIOnerInfo uiOner = (UIOnerInfo) listOnerSibu.get(0);
		        requestDto.setHyojiTaishoDisp(uiOner.getOnerNameKj());
		        if ( listOnerSibu.size() >= 2 ){
		            // 支部情報をセット
		            requestDto.setSibuMode(true);
		        }
		        else {
			        // 支部情報をセット
			        requestDto.setTaishoSibu(uiOner.getSibuCd());
		        }
	        }
        }// end of if (UserType.isHonbu(sessionDto.getUserTypeCd()))
        //3.DTO【Session情報】．ユーザータイプコード＝”オーナー”の場合、下記の処理を行います。
        if (UserType.isOner(sessionDto.getUserTypeCd())) {
        	//2-1.DTO【Request情報】.対象条件＝”全店”の場合、下記の処理を行います。
	        if(TaishoJoken.CODE_ALL.equals(requestDto.getTaishoJoken())) {
	        	//2-1-1. 定数クラス【集計区分】.対象コードの[UILists]の取得処理を実行し、UILists[集計区分]を取得します。
		        UILists skbnData = ShukeiKbn.getUILists(
		        		requestDto.getShukeiKbn()
		        		, sessionDto.getListShukeiKbn(requestDto.getTargetOnerCd()));
		        //2-1-2. UILists[集計区分].List[[データ]]を取得します。
		        List listOnerSibu = skbnData.getListData();
		        //2-1-3.UILists[集計区分].List[[データ]]の件数が２件以上の場合、DTO【Request情報】.支部プルダウン表示モードへtrueを設定します。
		        UIOnerInfo uiOner = (UIOnerInfo) listOnerSibu.get(0);
		        requestDto.setHyojiTaishoDisp(uiOner.getOnerNameKj());
		        if ( listOnerSibu.size() >= 2 ){
		        }
		        else {
			        // 支部情報をセット
			        requestDto.setTaishoSibu(uiOner.getSibuCd());
		        }
	        }
        }
    }
    /**
     * 検索結果ヘッダ情報セット
     * 
     * @param sessionDto
     * @param viewDto
     * @param listData
     */
    private void setHeaderInfo(SibuAverageDto sessionDto, SibuAverageReqDto viewDto, MstMise mstMise, List listData) {
        // 対象期間
        DateFormatter dateFormatter = new DateFormatter(
        		DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_KANJI_YM);
        viewDto.setTaishoKikanDisp(dateFormatter.format(viewDto.getKikanSitei(), true));
        
        // 表示対象
        String hyojiTaisho = "";

        if (TaishoJoken.CODE_MISE.equals(viewDto.getTaishoJoken())) {
            hyojiTaisho = mstMise.getMiseNameKj();
        }
        //対象支部名称の設定
        UILists skbnData = ShukeiKbn.getUILists(viewDto.getShukeiKbn()
        		, sessionDto.getListShukeiKbn(viewDto.getTargetOnerCd()));
        List listOnerSibu = skbnData.getListData();
        for(int s=0; s<listOnerSibu.size(); s++) {
        	UIOnerInfo uiOnerSibu = (UIOnerInfo) listOnerSibu.get(s);
        	if(uiOnerSibu.getSibuCd().equals(viewDto.getTaishoSibu())) {
        		viewDto.setTaishoSibuDisp(uiOnerSibu.getSibuName());
        		if (!TaishoJoken.CODE_MISE.equals(viewDto.getTaishoJoken())) {
        			hyojiTaisho = uiOnerSibu.getOnerNameKj();
        		}
        	}
        }
        viewDto.setHyojiTaishoDisp(hyojiTaisho);
    }
    
    /**
     * 初期処理
     * @param sessionDto
     * @param requestDto
     */
    private void validate(SibuAverageDto sessionDto, SibuAverageReqDto requestDto) {
        //必須チェック
        if (sessionDto == null) {
            throw new NotNullException("画面情報");
        }
        if (requestDto == null) {
            throw new NotNullException("画面情報");
        }
        //店コードチェック
        if (TaishoJoken.CODE_MISE.equals(requestDto.getTaishoJoken())) {
        	requestDto.setHyojiTaishoOner("");
            // 店コード必須チェック
            if (CommonUtil.isNull(requestDto.getHyojiTaishoMise())) {
                throw new NotNullException("店コード", "honbuMiseCd", 0);
            }
            // レングスチェック
            if (requestDto.getHyojiTaishoMise().trim().getBytes().length > 5) {
                throw new NoResultException();
            }
            //5桁のコードに変換
            CodeFormatter cdf = new CodeFormatter(5, "00000");
            cdf.setFormatPattern("00000");
            requestDto.setHyojiTaishoMise( cdf.format(requestDto.getHyojiTaishoMise(), true));              
        }
        if (TaishoJoken.CODE_ONER.equals(requestDto.getTaishoJoken())) {
        	requestDto.setHyojiTaishoMise("");
            // 店コード必須チェック
            if (CommonUtil.isNull(requestDto.getHyojiTaishoOner())) {
                throw new NotNullException("オーナーコード", "onerCd", 0);
            }
        }
        
    }
    public UISuiiDataDao getSibuaverageSuiiDataDao() {
        return sibuaverageSuiiDataDao;
    }

    public void setSibuaverageSuiiDataDao(UISuiiDataDao sibuaverageSuiiDataDao) {
        this.sibuaverageSuiiDataDao = sibuaverageSuiiDataDao;
    }

    public MstMiseDao getMstMiseDao() {
        return mstMiseDao;
    }

    public void setMstMiseDao(MstMiseDao mstMiseDao) {
        this.mstMiseDao = mstMiseDao;
    }

	/**
	 * オーナー変更ロジック
	 * @return クラス変数sibuaverageChangeOnerLogic を戻します。
	 */
	public ChangeOnerLogic getSibuaverageChangeOnerLogic() {
		return sibuaverageChangeOnerLogic;
	}


	/**
	 * オーナー変更ロジック
	 * @param logic を クラス変数sibuaverageChangeOnerLogicへ設定します。
	 */
	public void setSibuaverageChangeOnerLogic(
			ChangeOnerLogic logic) {
		this.sibuaverageChangeOnerLogic = logic;
	}

}
