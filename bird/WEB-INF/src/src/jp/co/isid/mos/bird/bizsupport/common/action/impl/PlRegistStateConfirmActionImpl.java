package jp.co.isid.mos.bird.bizsupport.common.action.impl;

import java.util.Iterator;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtils;

import jp.co.isid.mos.bird.bizsupport.common.action.PlRegistStateConfirmAction;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistStateDto;
import jp.co.isid.mos.bird.bizsupport.common.logic.GetPLInfoStatusLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.CheckPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.plregist.dto.SessionKeyDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;



/**
 * P/L登録状況確認
 * 
 * @author Aspac
 */
public class PlRegistStateConfirmActionImpl implements PlRegistStateConfirmAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID   = "BBS014A01";
    public static final String executeStateConfirm_ID = "BBS014A02";
    public static final String doBack_ACTION_ID       = "BBS014A03";
    
    private static final String VIEWID_PLREGIST_EDIT = "BBS001V02";
    
    /** 
     * session key 画面保持パラメータ
     */
    private SessionKeyDto plRegistSessionKeyDto;

    /**
     * P/Lデータ入力用Dto
     */
    private PlRegistDto plRegistDto;
    
    /**
     * 登録状況確認用Dto
     */
    private PlRegistStateDto plRegistStateDto;

    /**
     * P/Lデータチェックロジック
     */
    private CheckPLDataLogic checkPLDataLogic;

    /**
     * P/Lデータ登録状況取得ロジック
     */
    private GetPLInfoStatusLogic getPLInfoStatusLogic;

    /**
     * 対象年月バリデータ
     */
    private DateVerifier lumpTakeInPlYmVerifier;

    /**
     * 選択P/Lデータインデックス
     */
    private int selectIndex;
    
    
    /**
     * 登録状況確認用Dtoの設定
     * @return PlRegistStateDto を戻します。
     */
    public PlRegistStateDto getPlRegistStateDto() {
        return plRegistStateDto;
    }

    /**
     * 登録状況確認用Dtoの設定
     * @param plRegistStateDto を設定。
     */
    public void setPlRegistStateDto(PlRegistStateDto plRegistStateDto) {
        this.plRegistStateDto = plRegistStateDto;
    }
    
    /**
     * session key 画面保持パラメータDTOの設定
     * @return plRegistSessionKeyDto を戻します。
     */
    public SessionKeyDto getPlRegistSessionKeyDto() {
        return plRegistSessionKeyDto;
    }
    /**
     * session key 画面保持パラメータDTOの設定
     * @param plRegistSessionKeyDto plRegistSessionKeyDto を設定。
     */
    public void setPlRegistSessionKeyDto(SessionKeyDto plRegistSessionKeyDto) {
        this.plRegistSessionKeyDto = plRegistSessionKeyDto;
    }

    /**
     * P/Lデータ入力用Dtoの設定
     * @return plRegistDto を戻します。
     */
    public PlRegistDto getPlRegistDto() {
        return plRegistDto;
    }
    /**
     * P/Lデータ入力用Dtoの設定
     * @param plRegistDto plRegistDto を設定。
     */
    public void setPlRegistDto(PlRegistDto plRegistDto) {
        this.plRegistDto = plRegistDto;
    }
    
    /**
     * P/Lデータチェックロジックを取得します。
     * @return P/Lデータチェックロジック
     */
    public CheckPLDataLogic getCheckPLDataLogic() {
        return checkPLDataLogic;
    }

    /**
     * P/Lデータチェックロジックを設定します。
     * @param checkPLDataLogic P/Lデータチェックロジック
     */
    public void setCheckPLDataLogic(CheckPLDataLogic checkPLDataLogic) {
        this.checkPLDataLogic = checkPLDataLogic;
    }

    /**
     * P/Lデータ登録状況取得ロジックを取得します。
     * @return P/Lデータ登録状況取得ロジック
     */
    public GetPLInfoStatusLogic getGetPLInfoStatusLogic() {
        return getPLInfoStatusLogic;
    }

    /**
     * P/Lデータ登録状況取得ロジックを設定します。
     * @param getPLInfoStatusLogic P/Lデータ登録状況取得ロジック
     */
    public void setGetPLInfoStatusLogic(
            GetPLInfoStatusLogic getPLInfoStatusLogic) {
        this.getPLInfoStatusLogic = getPLInfoStatusLogic;
    }

    /**
     * 対象年月バリデータを取得します。
     * @return 対象年月バリデータ
     */
    public DateVerifier getLumpTakeInPlYmVerifier() {
        return lumpTakeInPlYmVerifier;
    }

    /**
     * 対象年月バリデータを設定します。
     * @param PlYmVerifier 対象年月バリデータ
     */
    public void setLumpTakeInPlYmVerifier(DateVerifier lumpTakeInPlYmVerifier) {
        this.lumpTakeInPlYmVerifier = lumpTakeInPlYmVerifier;
    }


    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {
        
        // 登録状況確認情報の取得
        executeStateConfirm();
        
        // 自画面を表示
        return null;
    }


    /**
     * 登録状況確認情報を取得する
     */
    public void executeStateConfirm() {       
        
        // 入力値チェック
        validate();

        // 登録状況取得      
        List plInfoStatusList = getGetPLInfoStatusLogic().execute(
                getPlRegistStateDto().getPlYm(), getPlRegistStateDto().getOwnerCd(), getPlRegistStateDto().isCloseMiseFlg());
        
        // P/Lデータ再チェック
        for (Iterator it = plInfoStatusList.iterator(); it.hasNext();) {
            getCheckPLDataLogic().execute((TrnPLInfo) it.next());   
        }
        
        getPlRegistStateDto().setPlDataList(plInfoStatusList);
        
    }


    /**
     * P/Lデータ入力画面呼出
     * 
     * @return 画面遷移情報
     */
    public String callInput() {

        // 選択P/Lデータ取得
        TrnPLInfo trnPLInfo = (TrnPLInfo) getPlRegistStateDto().getPlDataList().get(getSelectIndex());

        // P/Lデータ入力画面パラメータ設定
        PlRegistDto plRegistDto = new PlRegistDto();
        plRegistDto.setPlCsvFlg(true);
        TrnPLInfo plRegistTrnPLInfo = new TrnPLInfo();
        try {
            PropertyUtils.copyProperties(plRegistTrnPLInfo, trnPLInfo);
            // エラー情報をコピー
            plRegistTrnPLInfo.setPlDataErrorInfo(
                    (PlDataErrorInfo) trnPLInfo.getPlDataErrorInfo().clone());
        } catch (Exception e) {
            throw new FtlSystemException("P/L登録状況確認");
        }
        
        // 作成者情報設定
        plRegistTrnPLInfo.setAuthDt(getPlRegistDto().getTrnPLAuthorInfo().getAuthDt());
        plRegistTrnPLInfo.setAuthorName(getPlRegistDto().getTrnPLAuthorInfo().getAuthorName());
        plRegistTrnPLInfo.setAuthPhoneNum(getPlRegistDto().getTrnPLAuthorInfo().getAuthPhoneNum());
        plRegistTrnPLInfo.setAuthOther(getPlRegistDto().getTrnPLAuthorInfo().getAuthOther());
        plRegistDto.setTrnPLInfo(plRegistTrnPLInfo);
        plRegistDto.setKessanDt(getPlRegistDto().getTrnPLAuthorInfo().getKessanDt());

        setPlRegistDto(plRegistDto);

        // P/Lデータ入力画面へ遷移
        return VIEWID_PLREGIST_EDIT;
    }
    

    /**
     * 選択P/Lデータインデックスを取得します。
     * @return 選択P/Lデータインデックス
     */
    public int getSelectIndex() {
        return selectIndex;
    }

    /**
     * 選択P/Lデータインデックスを設定します。
     * @param selectIndex 選択P/Lデータインデックス
     */
    public void setSelectIndex(int selectIndex) {
        this.selectIndex = selectIndex;
    }
    
    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String doBack() {

        // 【セッションKey生成】        
        String key = plRegistDto._makeSessionKey();
        plRegistDto.setSessionKey(key);
        plRegistSessionKeyDto.setSessionKey(key);
        
        return getPlRegistStateDto().getNavigationCase();
    }



    /**
     * 入力値チェック
     *
     */
    private void validate() {

        // 対象年月
        String plYm = getPlRegistStateDto().getPlYm();
        if (plYm == null || plYm.trim().length() == 0) {
            throw new NotNullException("対象年月", "plYm", null);
        }
        if (!getLumpTakeInPlYmVerifier().validate(plYm)) {
            throw new InvalidInputException("対象年月", "plYm", null);
        }

    }

}
