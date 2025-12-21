package jp.co.isid.mos.bird.entry.mlviewlist.action.impl;

import java.io.IOException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

import jp.co.isid.mos.bird.entry.mlviewlist.action.MlViewListConfirmAction;
import jp.co.isid.mos.bird.entry.mlviewlist.dto.MlViewListDto;
import jp.co.isid.mos.bird.entry.mlviewlist.logic.ExecuteSaibanLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

public class MlViewListConfirmActionImpl implements CommonAction, MlViewListConfirmAction {

    /* アクションID */
    public static String initialize_ACTION_ID          = "BEN009A06";
    public static String execute_ACTION_ID             = "BEN009A07";
    public static String callMiseInfo_ACTION_ID        = "BEN009A08";
    public static String callMiseInfoResult_ACTION_ID  = "BEN009A09";
    public static String executeSaiban_ACTION_ID       = "BEN009A10";

    /* ビューID */
    private static final String VIEWID_CONDITION      = "BEN009V01"; //条件画面

    ////////////////
    /*   ACTION   */
    ////////////////
    private CsvOutputAction mlEntryListCsvOutputAction;
    /**
     * CsvOutputActionを取得する。
     * @return
     */
    public CsvOutputAction getMlEntryListCsvOutputAction() {
        return mlEntryListCsvOutputAction;
    }
    /**
     * CsvOutputActionを設定する。
     * @param mlEntryListCsvOutputAction
     */
    public void setMlEntryListCsvOutputAction(
            CsvOutputAction mlEntryListCsvOutputAction) {
        this.mlEntryListCsvOutputAction = mlEntryListCsvOutputAction;
    }

    private MlViewListSelectActionImpl mlViewListSelectAction;
    /**
     * MlViewListSelectActionImplを取得する。
     * @return
     */
    public MlViewListSelectActionImpl getMlViewListSelectAction() {
        return mlViewListSelectAction;
    }
    /**
     * MlViewListSelectActionImplを設定する。
     * @param mlViewListSelectAction
     */
    public void setMlViewListSelectAction(
            MlViewListSelectActionImpl mlViewListSelectAction){
        this.mlViewListSelectAction = mlViewListSelectAction;
    }

    /////////////
    /*   DTO   */
    /////////////
    // マスターライセンス申込状況確認Dto //
    private MlViewListDto mlViewListDto;
    // マスターライセンス申込状況確認CommonDto //
    private MlViewListDto mlViewListCommonDto;

    /**
     * マスターライセンス申込状況確認dtoの設定
     * @return mlViewListDto を戻します。
     */
    public MlViewListDto getMlViewListDto() {
        return mlViewListDto;
    }
    /**
     * マスターライセンス申込状況確認dtoの設定
     * @param mlViewListDto を設定。
     */
    public void setMlViewListDto(MlViewListDto mlViewListDto) {
        this.mlViewListDto = mlViewListDto;
    }

    /**
     * マスターライセンス申込状況確認CommonDtoの設定
     * @return mlViewListEntryCommonDto を戻します。
     */
    public MlViewListDto getMlViewListCommonDto() {
        return mlViewListCommonDto;
    }
    /**
     * マスターライセンス申込状況確認CommonDtoの設定
     * @param mlViewListCommonDto を設定。
     */
    public void setMlViewListCommonDto(MlViewListDto mlViewListCommonDto) {
        this.mlViewListCommonDto = mlViewListCommonDto;
    }



	///////////////////
	/*     LOGIC     */
	///////////////////
    private ExecuteSaibanLogic mlExecuteSaibanLogic;

	/**
     * マスターライセンス申込状況確認 照会画面 初期表示処理
     * @return 画面遷移情報
     */
    public String initialize() {
        return null;
    }
    /**
     * CSVダウンロード
     * @return
     */
    public String downloadCsv() {

        // CSVダウンロード
        try {
            getMlEntryListCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }
    /**
     * 戻る遷移(条件画面へ)
     * @return 画面遷移情報
     */
    public String cancel() {

        return VIEWID_CONDITION;
    }

    /**
     * 受験番号採番
     */
    public String executeSaiban() {
        getMlExecuteSaibanLogic().execute(getMlViewListCommonDto());
        String viewId = getMlViewListSelectAction().execute();
        return viewId;
    }

    /**
     * BIRD日付情報取得処理
     * @return birdDateInfo を戻します。
     */
    public BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * BIRDユーザー情報取得処理
     * @return birdUserInfo を戻します。
     */
    public BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer();
    }
    public ExecuteSaibanLogic getMlExecuteSaibanLogic() {
        return mlExecuteSaibanLogic;
    }
    public void setMlExecuteSaibanLogic(ExecuteSaibanLogic mlExecuteSaibanLogic) {
        this.mlExecuteSaibanLogic = mlExecuteSaibanLogic;
    }

}