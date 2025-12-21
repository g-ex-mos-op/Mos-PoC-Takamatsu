package jp.co.isid.mos.bird.entry.nationalviewlist.action.impl;

import java.io.IOException;

import jp.co.isid.mos.bird.entry.nationalviewlist.action.CsvDownloadAction;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.dto.NationalViewListReqDto;
import jp.co.isid.mos.bird.entry.nationalviewlist.logic.SearchLogic;
import jp.co.isid.mos.bird.entry.nationalviewlist.util.NationalViewListUtil;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutputActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NoResultException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * CSV出力クラス
 * 
 * @author xkinu
 */
public class CsvDownloadActionImpl extends CsvOutputActionImpl implements CsvDownloadAction {
    /** アクションID：実行（検索）処理 */
    public static final String downloadCsv_ACTION_ID = NationalViewListUtil.SCREEN_ID+"A31";
    /** DTO【全国大会申込状況確認】*/
    private NationalViewListDto nationalViewListDto;
    /** DTO【申込状況確認リクエスト用】*/
    private NationalViewListReqDto nationalViewListReqDto;
    /**
     * ロジック【申込状況検索】
     */
    private SearchLogic nationalViewListSearchLogic;

    /**
     * ダウンロード メイン処理
     * 
     * @throws IOException
     * @throws ApplicationException
     */
    public void downloadCsv() {
        //変数WindowID設定
        int windowId = getNationalViewListReqDto().getWindowId();
        try{
            //１．現ウィンドウID の検索対象条件項目値の保管を行う。
            getNationalViewListDto().holdJokenParam(getNationalViewListReqDto());
            getNationalViewListDto().copyCsvJokenData(getNationalViewListReqDto());
            super.downloadCsv();
        }
        catch (ApplicationException appEx) {
            if (appEx instanceof NoResultException) {
                //該当データがない場合、複数ウィンドウ機能の各情報量制御機能で
                //再表示対象の検索結果データが存在しない可能性があるため、
                //表示対象の検索結果データが存在チェックを行い、無い場合は再検索を行う。
                if(getNationalViewListDto().isSearched(windowId) && !getNationalViewListDto().isExistSearchData(windowId)) {
                    try {
                        //すでにダウンロードの対象検索データが削除されている場合、再度検索処理を実行する。
                        NationalViewListUtil.actionSearchCsvData(getBirdUserInfo(), getBirdDateInfo()
                            , getNationalViewListDto(), getNationalViewListReqDto(), getNationalViewListSearchLogic());
                    } catch(NoResultException e) {                       
                    }
                }
                throw appEx;
            }
        }
        catch (Exception e) {
            throw new FtlSystemException("CSVダウンロード", null, e);
        }
        finally{
            //２．現ウィンドウID の保管データから【DTO】検索対象条件項目へ値の設定を行う。
            getNationalViewListDto().copyData(getNationalViewListReqDto());
        }
    }

    /**
     * Seaser2Containaer取得処理
     * 
     * @return
     */
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
    /**
     * BIRDユーザー情報取得処理
     * 
     * @return
     */
    private BirdUserInfo getBirdUserInfo() {
        return (BirdUserInfo) getS2Container().getComponent(BirdUserInfo.class);
    }
    /**
     * BIRD日付情報取得処理
     * 
     * @return
     */
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * @return nationalViewListDto を戻します。
     */
    public NationalViewListDto getNationalViewListDto() {
        return nationalViewListDto;
    }

    /**
     * @param nationalViewListDto 設定する nationalViewListDto。
     */
    public void setNationalViewListDto(NationalViewListDto nationalViewListDto) {
        this.nationalViewListDto = nationalViewListDto;
    }

    /**
     * @return nationalViewListReqDto を戻します。
     */
    public NationalViewListReqDto getNationalViewListReqDto() {
        return nationalViewListReqDto;
    }

    /**
     * @param nationalViewListReqDto 設定する nationalViewListReqDto。
     */
    public void setNationalViewListReqDto(
            NationalViewListReqDto nationalViewListReqDto) {
        this.nationalViewListReqDto = nationalViewListReqDto;
    }
    /**
     * @return nationalViewListSearchLogic を戻します。
     */
    public SearchLogic getNationalViewListSearchLogic() {
        return nationalViewListSearchLogic;
    }

    /**
     * @param nationalViewListSearchLogic 設定する nationalViewListSearchLogic。
     */
    public void setNationalViewListSearchLogic(
            SearchLogic nationalViewListSearchLogic) {
        this.nationalViewListSearchLogic = nationalViewListSearchLogic;
    }

}
