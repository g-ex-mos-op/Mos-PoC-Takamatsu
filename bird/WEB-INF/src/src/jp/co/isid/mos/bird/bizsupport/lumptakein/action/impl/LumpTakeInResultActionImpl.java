/*
 * 作成日: 2006/03/10
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.action.impl;

import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.bizsupport.lumptakein.action.LumpTakeInResultAction;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.CheckPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLInfoStatusLogic;
import jp.co.isid.mos.bird.bizsupport.common.dto.PlRegistDto;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * P/LデータCSV一括取込結果アクション
 * 
 * @author xyuchida
 */
public class LumpTakeInResultActionImpl implements LumpTakeInResultAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BBS002A09";
    public static final String callInput_ACTION_ID = "BBS002A10";
    public static final String refresh_ACTION_ID = "BBS002A11";
    public static final String back_ACTION_ID = "BBS002A12";

    private static final String VIEWID_CONDITION = "BBS002V01";
    private static final String VIEWID_PLREGIST_EDIT = "BBS001V02";

    /**
     * CSV一括取込DTO
     */
    private LumpTakeInDto lumpTakeInDto;

    /**
     * P/Lデータ入力用DTO
     */
    private PlRegistDto plRegistDto;

    /**
     * P/Lデータチェックロジック
     */
    private CheckPLDataLogic checkPLDataLogic;

    /**
     * P/Lデータ登録状況取得ロジック
     */
    private GetPLInfoStatusLogic getPLInfoStatusLogic;

    /**
     * 選択P/Lデータインデックス
     */
    private int selectIndex;

    /**
     * CSV一括取込DTOを取得します。
     * @return CSV一括取込DTO
     */
    public LumpTakeInDto getLumpTakeInDto() {
        return lumpTakeInDto;
    }

    /**
     * CSV一括取込DTOを設定します。
     * @param lumpTakeInDto CSV一括取込DTO
     */
    public void setLumpTakeInDto(LumpTakeInDto lumpTakeInDto) {
        this.lumpTakeInDto = lumpTakeInDto;
    }

    /**
     * P/Lデータ入力用DTOを取得します。
     * @return P/Lデータ入力用DTO
     */
    public PlRegistDto getPlRegistDto() {
        return plRegistDto;
    }

    /**
     * P/Lデータ入力用DTOを設定します。
     * @param plRegistDto P/Lデータ入力用DTO
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
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        // 自画面へ遷移
        return null;
    }

    /**
     * P/Lデータ入力画面呼出
     * 
     * @return 画面遷移情報
     */
    public String callInput() {

        // 選択P/Lデータ取得
        TrnPLInfo trnPLInfo = (TrnPLInfo) getLumpTakeInDto().getPlDataList().get(getSelectIndex());

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
            throw new FtlSystemException("P/LデータCSV一括取込");
        }
        // 作成者情報設定
        plRegistTrnPLInfo.setAuthDt(getLumpTakeInDto().getAuthDt());
        plRegistTrnPLInfo.setAuthorName(getLumpTakeInDto().getAuthorName());
        plRegistTrnPLInfo.setAuthPhoneNum(getLumpTakeInDto().getAuthPhoneNum());
        plRegistTrnPLInfo.setAuthOther(getLumpTakeInDto().getAuthOther());
        plRegistDto.setTrnPLInfo(plRegistTrnPLInfo);
        plRegistDto.setKessanDt(getLumpTakeInDto().getKessanDt());

        setPlRegistDto(plRegistDto);

        // P/Lデータ入力画面へ遷移
        return VIEWID_PLREGIST_EDIT;
    }

    /**
     * リフレッシュ
     * 
     * @return 画面遷移情報
     */
    public String refresh() {

        // 登録状況取得
        List plInfoStatusList = getGetPLInfoStatusLogic().execute(
                getLumpTakeInDto().getPlYm(), getLumpTakeInDto().getOwnerCd());
        // P/Lデータ再チェック
        for (Iterator it = plInfoStatusList.iterator(); it.hasNext();) {
            getCheckPLDataLogic().execute((TrnPLInfo) it.next());   
        }

        // 前回取込時のエラーデータを追加
        if (getLumpTakeInDto().getPlDataErrorList() != null
                && getLumpTakeInDto().getPlYm().equals(getLumpTakeInDto().getLastPlYm())
                && getLumpTakeInDto().getOwnerCd().equals(getLumpTakeInDto().getLastOwnerCd())) {
            plInfoStatusList.addAll(getLumpTakeInDto().getPlDataErrorList());
        }

        getLumpTakeInDto().setPlDataList(plInfoStatusList);

        // 自画面へ遷移
        return null;
    }

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back() {

        // 条件画面へ遷移
        return VIEWID_CONDITION;
    }
}
