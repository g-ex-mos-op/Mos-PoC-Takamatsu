package jp.co.isid.mos.bird.storeinfo.miselumpextract.action.impl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.commonform.common.logic.GetSibuLogic;
import jp.co.isid.mos.bird.framework.action.CommonAction;
import jp.co.isid.mos.bird.framework.action.CsvOutputAction;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.action.MiseLumpExtractAction;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.dto.MiseLumpExtractDto;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.entity.CodCompanyJoho;
import jp.co.isid.mos.bird.storeinfo.miselumpextract.logic.CompanyJohoLogic;

/**
 * 個店情報一括抽出 アクション
 *
 * 更新日:2015/05/14 xytamura 宅配未実施店一覧を当月宅配実績無し店舗一覧に名称変更
 */
public class MiseLumpExtractActionImpl implements CommonAction, MiseLumpExtractAction {

    /* アクションID */
    public static String initialize_ACTION_ID   = "BSI003A01";
    public static String downloadCsv_ACTION_ID  = "BSI003A02";
    public static String loadSibuList_ACTION_ID = "BSI003A03";
    /* ビューID */
    //private static final String VIEWID_CONDITION = "BSI003V01";

    /* ACTION */
    private CsvOutputAction miseLumpExtractCsvOutputAction;

    /* LOGIC */
    /** ユーザー所属管理会社の検索 */
    private CompanyJohoLogic companyJohoLogic;
    /** 支部リスト取得ロジック */
    private GetSibuLogic getSibuLogic;

    /* DTO */
    /** 個店情報一括抽出画面Dto */
    private MiseLumpExtractDto miseLumpExtractDto;
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
            // 検索結果初期化
            miseLumpExtractDto.clear();
            //ラジオボタン初期化
            miseLumpExtractDto.setIndex(0);
            miseLumpExtractDto.setCloseFlg("outClose");

            /** ロジック【ユーザー所属管理会社の検索】*/
            List listCompany = getCompanyJohoLogic().execute(getBirdUserInfo().getUserID());
            miseLumpExtractDto.setListCompany(listCompany);

            // 会社リスト設定
            String companyCd = null;
            miseLumpExtractDto.setListCompany(listCompany);
            if (listCompany != null && !listCompany.isEmpty()) {
                // 先頭会社コードを選択中とする。
                companyCd = ((CodCompanyJoho) listCompany.get(0)).getCompanyCd();
                miseLumpExtractDto.setCompanyCd(companyCd);
                // 支部リスト取得
                CodCompanyJoho codCompanyJoho = null;
                List listPullSibu = null;
                for(Iterator ite = listCompany.iterator(); ite.hasNext();){
                    codCompanyJoho = (CodCompanyJoho) ite.next();
                    companyCd  = codCompanyJoho.getCompanyCd();
                    /** ロジック【支部リストの検索】実施 */
                    listPullSibu = getSibuLogic.execute(companyCd);
                    miseLumpExtractDto.getMapPullSibu().put(companyCd, listPullSibu);
                }
            }
            //ダウンロード対象データ一覧を作成します。
            List titleList = new ArrayList(0);
            titleList.add("緊急連絡先情報");
            titleList.add("地図情報（ＵＲＬ)");
            titleList.add("メモ1～５");
            titleList.add("賃貸店舗情報");
            //titleList.add("インセンティブ");
            //titleList.add("ＨＤＣ");
            titleList.add("サービス実施状況");
            titleList.add("マスタライセンス情報");
            titleList.add("当月宅配実績無し店舗一覧");
            titleList.add("店舗ガス種別");
            miseLumpExtractDto.setTitleList(titleList);

            getPullDownMenuDto().setClearFlg(false);
        }

        return null;
    }


    /**
     * 店リスト読込処理
     * @return 画面遷移情報
     */
    public String loadSibuList() {
        return null;
    }


    /**
     * CSVダウンロード
     * @return
     */
    public String downloadCsv() {

        // CSVダウンロード
        try {
            getMiseLumpExtractDto().setSysDate(getBirdDateInfo().getSysDate());
            getMiseLumpExtractCsvOutputAction().downloadCsv();
        } catch (IOException e) {
            throw new FtlSystemException("CSVダウンロード");
        }
        return null;
    }
    /**
     * 個店情報一括抽出画面dtoの設定
     * @return miseLumpExtractDto を戻します。
     */
    public MiseLumpExtractDto getMiseLumpExtractDto() {
        return miseLumpExtractDto;
    }
    /**
     * 個店情報一括抽出画面dtoの設定
     * @param miseLumpExtractDto を設定。
     */
    public void setMiseLumpExtractDto(MiseLumpExtractDto miseLumpExtractDto) {
        this.miseLumpExtractDto = miseLumpExtractDto;
    }

    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /* アクション **************************************************************/
    /* *************************************************************************/

    public CsvOutputAction getMiseLumpExtractCsvOutputAction() {
        return miseLumpExtractCsvOutputAction;
    }
    public void setMiseLumpExtractCsvOutputAction(
            CsvOutputAction miseLumpExtractCsvOutputAction) {
        this.miseLumpExtractCsvOutputAction = miseLumpExtractCsvOutputAction;
    }

    /* ロジック ****************************************************************/
    /* *************************************************************************/

    /**
     * 会社リスト取得ロジックを取得します。
     * @return companyJohoLogic を戻します。
     */
    public CompanyJohoLogic getCompanyJohoLogic() {
        return companyJohoLogic;
    }
    /**
     * 会社リスト取得ロジックを設定します。
     * @param companyJohoLogic companyJohoLogic を設定。
     */
    public void setCompanyJohoLogic(CompanyJohoLogic companyJohoLogic) {
        this.companyJohoLogic = companyJohoLogic;
    }

    /**
     * 支部リスト取得ロジックを取得します。
     * @return 支部リスト取得ロジック
     */
    public GetSibuLogic getGetSibuLogic() {
        return getSibuLogic;
    }

    /**
     * 支部リスト取得ロジックを設定します。
     * @param getSibuLogic 支部リスト取得ロジック
     */
    public void setGetSibuLogic(GetSibuLogic getSibuLogic) {
        this.getSibuLogic = getSibuLogic;
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