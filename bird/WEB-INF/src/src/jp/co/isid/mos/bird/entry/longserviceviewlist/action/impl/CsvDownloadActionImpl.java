package jp.co.isid.mos.bird.entry.longserviceviewlist.action.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.longserviceviewlist.action.CsvDownloadAction;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dto.LongserviceViewListRequestDto;
import jp.co.isid.mos.bird.entry.longserviceviewlist.dto.LongserviceViewListSessionDto;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.LongserviceViewListResultLogic;
import jp.co.isid.mos.bird.entry.longserviceviewlist.logic.impl.LongserviceViewListResultLogicImpl;
import jp.co.isid.mos.bird.entry.longserviceviewlist.util.LongserviceViewListUtil;
import jp.co.isid.mos.bird.framework.action.impl.CsvOutputActionImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * CSV出力クラス
 * 
 * @author xkinu
 */
public class CsvDownloadActionImpl extends CsvOutputActionImpl implements CsvDownloadAction {
    /** アクションID：実行（検索）処理 */
    public static final String downloadCsv_ACTION_ID = LongserviceViewListUtil.SCREEN_ID+"A11";
    /** DTO【全国大会申込状況確認】*/
    private LongserviceViewListSessionDto longserviceViewListSessionDto;
    /** DTO【申込状況確認リクエスト用】*/
    private LongserviceViewListRequestDto longserviceViewListRequestDto;
    /** Logic【オーナー別申請状況一覧】*/
    private LongserviceViewListResultLogic longserviceViewListResultLogic;
    /**結果表示の切り替え */
    private final String OK = "0";
  //  private final String NG = "1";

    /**
     * ダウンロード メイン処理
     * 
     * @throws IOException
     * @throws ApplicationException
	 * 更新日：2009/08/11 支部制限時のユーザーIDがパラメータに存在せず、
	 *                    SQLでuserIdのパラメータありきで設定されているため、
	 *                    常に支部制限のユーザは該当データ無しになっていました。
	 *                    パラメータuserIdを追加し、対応しました。
     */
    public void downloadCsv() {
        //変数WindowID設定
        int windowId = getLongserviceViewListRequestDto().getWindowId();
        boolean errorFlg = false;
                
        try {
            //ダウンロード用にセッションのDTOの値をリクエストのDTOにセットする。
            getLongserviceViewListRequestDto().setUserId(getBirdUserInfo().getUserID());
            getLongserviceViewListRequestDto().setLimit(getBirdUserInfo().isLimit());
            getLongserviceViewListRequestDto().setSysdate(getLongserviceViewListSessionDto().getSysdate());
            getLongserviceViewListRequestDto().setCompanyCd(getLongserviceViewListSessionDto().getCompanyCd(windowId));
            getLongserviceViewListRequestDto().setEntryCd(getLongserviceViewListSessionDto().getEntryCd(windowId));
            getLongserviceViewListRequestDto().setEntryYear(getLongserviceViewListSessionDto().getEntryYear(windowId));
            getLongserviceViewListRequestDto().setEntryKai(getLongserviceViewListSessionDto().getEntryKai(windowId));
            getLongserviceViewListRequestDto().setEntityUILSViewListEvent(getLongserviceViewListSessionDto().getentityUILSViewListEvent(windowId));
            getLongserviceViewListRequestDto().setTable(getLongserviceViewListSessionDto().getTable(windowId));
            getLongserviceViewListRequestDto().setButtonFlg(getLongserviceViewListSessionDto().getButtonFlg(windowId));
            
            super.downloadCsv();
        }
        catch (Exception e) {
            if (!getLongserviceViewListSessionDto().getMapApplyOner().isEmpty()
                    && getLongserviceViewListSessionDto().getResultFlg(windowId).equals(OK)) {
                
                if (getLongserviceViewListSessionDto().getMapApplyOner().containsKey(new Integer(windowId))) {
                    getLongserviceViewListRequestDto().setTable(
                            getLongserviceViewListSessionDto().getTable(windowId));
                } else {
                    //２－３．２－２で結果部を出力できなかった場合、結果部を再検索する。
                    Map params = new HashMap();
                    
                    params.put(LongserviceViewListResultLogicImpl.PK_USER_ID, getBirdUserInfo().getUserID());
                    params.put(LongserviceViewListResultLogicImpl.PK_LIMIT, new Boolean(getBirdUserInfo().isLimit()));
                    params.put(LongserviceViewListResultLogicImpl.PK_ENTITY_COURSE, getLongserviceViewListSessionDto().getentityUILSViewListEvent(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_SYSDATE, getBirdDateInfo().getSysDate());
                    params.put(LongserviceViewListResultLogicImpl.PK_COMPANY_CD, getLongserviceViewListSessionDto().getCompanyCd(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_TAISHOUJOKEN, getLongserviceViewListSessionDto().getTaishouJokenChoice(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_SIBU_CD, getLongserviceViewListSessionDto().getSibuChoice(windowId));
                    params.put(LongserviceViewListResultLogicImpl.PK_SV_CD, getLongserviceViewListSessionDto().getSvCd(windowId));
                    
                    Map rparams = getLongserviceViewListResultLogic().execute(params);
                    List listApplyOner = (List)rparams.get(LongserviceViewListResultLogicImpl.RK_LIST_APPLY_ONER);
                    
                    //２－４．listApplyOnerがnullだった場合に値をセットしない。
                    if (listApplyOner != null && listApplyOner.size() != 0) {
                        getLongserviceViewListRequestDto().setTable(listApplyOner);
                    }
                    
                }
           }
           if (e instanceof ApplicationException) {
               errorFlg = true;
               throw (ApplicationException)e;
           } else {
               errorFlg = true;
               throw new FtlSystemException("永年勤続申込状況確認CSVダウンロード", "", e);
           }
        
        }
        finally {
            //３．finally処理
            //３－１．検索が成功したかの判断
            if (!errorFlg) {
                //３－１．成功した場合、選択された対象条件、支部、SVコードをセッションのDTOにセットする。
                getLongserviceViewListSessionDto().setTaishouJokenChoice(windowId, getLongserviceViewListRequestDto().getTaishouJokenChoice());
                getLongserviceViewListSessionDto().setSibuChoice(windowId, getLongserviceViewListRequestDto().getSibuChoice());
                getLongserviceViewListSessionDto().setSvCd(windowId, getLongserviceViewListRequestDto().getSvCd());
            }
        }
    }

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
    
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    /**
     * @return longserviceViewListSessionDto を戻します。
     */
    public LongserviceViewListSessionDto getLongserviceViewListSessionDto() {
        return longserviceViewListSessionDto;
    }

    /**
     * @param longserviceViewListSessionDto 設定する longserviceViewListSessionDto。
     */
    public void setLongserviceViewListSessionDto(LongserviceViewListSessionDto longserviceViewListSessionDto) {
        this.longserviceViewListSessionDto = longserviceViewListSessionDto;
    }

    /**
     * @return longserviceViewListRequestDto を戻します。
     */
    public LongserviceViewListRequestDto getLongserviceViewListRequestDto() {
        return longserviceViewListRequestDto;
    }

    /**
     * @param longserviceViewListRequestDto 設定する longserviceViewListRequestDto。
     */
    public void setLongserviceViewListRequestDto(
            LongserviceViewListRequestDto longserviceViewListRequestDto) {
        this.longserviceViewListRequestDto = longserviceViewListRequestDto;
    }
    public LongserviceViewListResultLogic getLongserviceViewListResultLogic() {
        return longserviceViewListResultLogic;
    }

    public void setLongserviceViewListResultLogic(
            LongserviceViewListResultLogic longserviceViewListResultLogic) {
        this.longserviceViewListResultLogic = longserviceViewListResultLogic;
    }


}