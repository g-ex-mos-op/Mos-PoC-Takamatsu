/*
 * 作成日: 2006/08/04
 */
package jp.co.isid.mos.bird.bill.detailbilldownload.logic.impl;

import java.util.List;
import jp.co.isid.mos.bird.bill.detailbilldownload.dao.UIBillListDao;
import jp.co.isid.mos.bird.bill.detailbilldownload.logic.GetSeikyuListLogic;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 請求書一覧リスト取得ロジック
 * 
 * @author xwatanabe
 */
public class GetSeikyuListLogicImpl implements GetSeikyuListLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS010L02";

    /** 年月出力月数 */
    private static int NENGETU_DISPLAY_MONTH = 13;

     /**
     * 請求書一覧取得ロジック
     * @param  会社コード
     * @param  オーナーコード
     * @param  売掛先コード
     * @param  検収日付
     * @return List 請求一覧情報
     */
    public List execute(String companyCd,String onerCd,String urikakeCd,String kenshuDt) {
        
        //パラメータチェック
        if(companyCd == null || companyCd.getBytes().length == 0){
            throw new NotNullException("会社コード");
        }
        if(onerCd == null || onerCd.getBytes().length == 0){
            throw new NotNullException("オーナーコード");
        }
        if(urikakeCd == null || urikakeCd.getBytes().length == 0){
            throw new NotNullException("ご請求先");
        }
        if(kenshuDt == null || kenshuDt.getBytes().length == 0){
            throw new NotNullException("検収日付");
        }
        
        //検収日付より「表示開始年月」「表示終了年月」を求める
        String startYM = "";
        String endYM   = "";

        endYM = kenshuDt.substring(0,6);
        try {
            startYM = DateManager.getPrevMonth(endYM, NENGETU_DISPLAY_MONTH  - 1);
        } catch (Exception ex) {
            throw new FtlSystemException("期間指定生成", 
                    "アプリ日付年月["+endYM+"]から["+NENGETU_DISPLAY_MONTH+"]を引く際のDateManager.getPrevMonthメソッド処理で例外が発生しました。", 
                    ex);
        }
        
        List outputList = uiBillListDao.getBillList(companyCd, onerCd, urikakeCd, startYM, endYM);
        if (outputList == null || outputList.isEmpty()) {
            //存在しない時
            throw new NotExistException("ご請求明細データ");
        }
        
        return outputList;
    }

    /**
     * 請求書一覧DAO
     */
     private UIBillListDao uiBillListDao;

    /**
     * 請求書一覧DAOを取得します。
     * @return 請求書一覧DAO
     */
    public UIBillListDao getUIBillListDao() {
        return uiBillListDao;
    }

    /**
     * 請求書一覧DAOを設定します。
     * @param uiBillListDao 請求書一覧DAO
     */
    public void setUIBillListDao(UIBillListDao uiBillListDao) {
        this.uiBillListDao = uiBillListDao;
    }
}
