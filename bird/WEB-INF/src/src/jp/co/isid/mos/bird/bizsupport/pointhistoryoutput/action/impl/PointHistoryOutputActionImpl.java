/**
 * 作成日: 2017/02/24
 */
package jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.action.impl;

import java.util.List;

import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.action.PointHistoryOutputAction;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.dto.PointHistoryOutputDto;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.entity.CodKbCompanyJoho;
import jp.co.isid.mos.bird.bizsupport.pointhistoryoutput.logic.KbCompanyJohoLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

/**
 * ポイント履歴出力 アクション
 * @author Yuichi Tamura(ISID-AO)
 */
public class PointHistoryOutputActionImpl implements CommonAction, PointHistoryOutputAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BBS038A01";
    public static String downloadCsv_ACTION_ID  = "BBS038A02";

    /* ACTION */
    private CsvOutputAction pointHistoryCsvOutputAction;

    /* LOGIC */
    private KbCompanyJohoLogic kbCompanyJohoLogic;

    /* DTO */
    /** ポイント履歴出力画面DTO */
    private PointHistoryOutputDto pointHistoryOutputDto;
    /** メニューDto */
    private PullDownMenuDto pullDownMenuDto;

    /** BirdUserInfo */
    private BirdUserInfo birdUserInfo;
    /** BirdDateInfo */
    private BirdDateInfo birdDateInfo;

    /**
     * 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {

        // メニューから遷移された場合の初期処理
        if (getPullDownMenuDto().isClearFlg()) {
            //検索条件初期化
            /** ロジック【株式報酬制度の会社の検索】*/
            List<CodKbCompanyJoho> listCompany = getKbCompanyJohoLogic().execute();
            pointHistoryOutputDto.setListCompany(listCompany);
            pointHistoryOutputDto.setKbCompanyCd("");

            pointHistoryOutputDto.setUserId(null);
            pointHistoryOutputDto.setNendoFrom(null);
            pointHistoryOutputDto.setNendoTo(null);

            // 検索結果初期化
            pointHistoryOutputDto.clear();
            //ラジオボタン初期化
            pointHistoryOutputDto.setShoriKbnIndex("1");
            pointHistoryOutputDto.setTaishokuIndex("2");
            //システム日付セット
            pointHistoryOutputDto.setSysDate(birdDateInfo.getSysDate());

            getPullDownMenuDto().setClearFlg(false);
        }
        return null;
    }




    /**
     * CSVダウンロード
     *
     * @return
     */
    public String downloadCsv()   {

        // CSVダウンロード
        try {
            getPointHistoryCsvOutputAction().downloadCsv();
        } catch (ApplicationException apEx  ) {
            throw apEx;

        } catch (Exception ex) {
            throw new FtlSystemException("CSVダウンロード", null, ex);
        }
        return null;
    }

    /**
     * ポイント履歴出力画面DTOを取得します
     * @return ポイント履歴出力画面DTO
     */
    public PointHistoryOutputDto getPointHistoryOutputDto() {
        return pointHistoryOutputDto;
    }
    /**
     * ポイント履歴出力画面DTOの設定
     * @param pointHistoryOutputDto ポイント履歴出力画面DTO
     */
    public void setPointHistoryOutputDto(PointHistoryOutputDto pointHistoryOutputDto) {
        this.pointHistoryOutputDto = pointHistoryOutputDto;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * pointHistoryCsvOutputActionを返す
     * @return pointHistoryCsvOutputAction
     */
    public CsvOutputAction getPointHistoryCsvOutputAction() {
        return pointHistoryCsvOutputAction;
    }
    /**
     * pointHistoryCsvOutputActionをセットする
     * @param pointHistoryCsvOutputAction
     */
    public void setPointHistoryCsvOutputAction(
            CsvOutputAction pointHistoryCsvOutputAction) {
        this.pointHistoryCsvOutputAction = pointHistoryCsvOutputAction;
    }
	/**
	 * kbCompanyJohoLogicを返す
	 * @return kbCompanyJohoLogic
	 */
	public KbCompanyJohoLogic getKbCompanyJohoLogic() {
		return kbCompanyJohoLogic;
	}
	/**
	 * kbCompanyJohoLogicをセットする
	 * @param kbCompanyJohoLogic
	 */
	public void setKbCompanyJohoLogic(KbCompanyJohoLogic kbCompanyJohoLogic) {
		this.kbCompanyJohoLogic = kbCompanyJohoLogic;
	}
	public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
    }

    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

}