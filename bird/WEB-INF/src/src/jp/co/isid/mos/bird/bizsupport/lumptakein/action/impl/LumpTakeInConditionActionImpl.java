/*
 * 作成日: 2006/03/10
 *
 */
package jp.co.isid.mos.bird.bizsupport.lumptakein.action.impl;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.lumptakein.action.LumpTakeInConditionAction;
import jp.co.isid.mos.bird.bizsupport.lumptakein.dto.LumpTakeInDto;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.PlDataErrorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLAuthorInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.entity.TrnPLInfo;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.CheckPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.ExistOwnerCodeLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLAuthorLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLInfoStatusLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLMiseNameLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.GetPLOwnerNameLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.RegistPLDataLogic;
import jp.co.isid.mos.bird.bizsupport.lumptakein.logic.TakePlInfoCsvLogic;
import jp.co.isid.mos.bird.commonform.ownersearch.dto.OwnerSearchDto;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.dto.PullDownMenuDto;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.CannotAccessException;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DateVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.PhoneVerifier;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * P/LデータCSV一括取込条件アクション
 * 
 * @author xyuchida
 */
public class LumpTakeInConditionActionImpl implements LumpTakeInConditionAction {

    /** アクションID定義 */
    public static final String initialize_ACTION_ID = "BBS002A01";
    public static final String takeCsvFile_ACTION_ID = "BBS002A02";
    public static final String referError_ACTION_ID = "BBS002A04";
    public static final String callOwnerSearch_ACTION_ID = "BBS002A06";
    public static final String selectOwnerCode_ACTION_ID = "BBS002A07";
    public static final String back_ACTION_ID = "BBS002A08";

    private static final String VIEWID_CONDITION = "BBS002V01";
    private static final String VIEWID_RESULT = "BBS002V02";
    private static final String VIEWID_OWNER_SEARCH = "BCO006V01";

    private static final int VIEWMODE_CONDITION = 0;
    private static final int VIEWMODE_OWNER_SELECT = 1;

    private static final int MONTHS_PLYM = 25;

    /**
     * CSV一括取込DTO
     */
    private LumpTakeInDto lumpTakeInDto;

    /**
     * メニューDTO
     */
    private PullDownMenuDto pullDownMenuDto;

    /**
     * オーナー選択DTO
     */
    private OwnerSearchDto ownerSearchDto;

    /**
     * CSV一括取込ロジック
     */
    private TakePlInfoCsvLogic takePlInfoCsvLogic;

    /**
     * P/Lデータチェックロジック
     */
    private CheckPLDataLogic checkPLDataLogic;

    /**
     * P/Lデータ登録ロジック
     */
    private RegistPLDataLogic registPLDataLogic;

    /**
     * P/L作成者情報取得ロジック
     */
    private GetPLAuthorLogic getPLAuthorLogic;

    /**
     * P/Lデータ店名称取得ロジック
     */
    private GetPLMiseNameLogic getPLMiseNameLogic;

    /**
     * P/Lデータオーナー名称取得ロジック
     */
    private GetPLOwnerNameLogic getPLOwnerNameLogic;

    /**
     * オーナーコード存在判定ロジック
     */
    private ExistOwnerCodeLogic existOwnerCodeLogic;

    /**
     * P/Lデータ登録状況取得ロジック
     */
    private GetPLInfoStatusLogic getPLInfoStatusLogic;

    /**
     * ユーザ関連情報
     */
    private BirdUserInfo birdUserInfo;

    /**
     * 日付情報
     */
    private BirdDateInfo birdDateInfo;

    /**
     * 対象年月バリデータ
     */
    private DateVerifier lumpTakeInPlYmVerifier;

    /**
     * コードバリデータ
     */
    private CodeVerifier lumpTakeInCodeVerifier;

    /**
     * 作成年月日バリデータ
     */
    private DateVerifier lumpTakeInAuthDtVerifier;

    /**
     * 電話番号バリデータ
     */
    private PhoneVerifier lumpTakeInPhoneVerifier;

    /**
     * 日本語バリデータ
     * @return
     */
    private DefaultJapaneseVerifier lumpTakeInJapaneseVerifier;

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
     * メニューDTOを取得します。
     * @return メニューDTO
     */
    public PullDownMenuDto getPullDownMenuDto() {
        return pullDownMenuDto;
    }

    /**
     * メニューDTOを設定します。
     * @param pullDownMenuDto メニューDTO
     */
    public void setPullDownMenuDto(PullDownMenuDto pullDownMenuDto) {
        this.pullDownMenuDto = pullDownMenuDto;
    }

    /**
     * オーナー選択DTOを取得します。
     * @return オーナー選択DTO
     */
    public OwnerSearchDto getOwnerSearchDto() {
        return ownerSearchDto;
    }

    /**
     * オーナー選択DTOを設定します。
     * @param ownerSearchDto オーナー選択DTO
     */
    public void setOwnerSearchDto(OwnerSearchDto ownerSearchDto) {
        this.ownerSearchDto = ownerSearchDto;
    }

    /**
     * CSV一括取込ロジックを取得します。
     * @return CSV一括取込ロジック
     */
    public TakePlInfoCsvLogic getTakePlInfoCsvLogic() {
        return takePlInfoCsvLogic;
    }

    /**
     * CSV一括取込ロジックを設定します。
     * @param takePlInfoCsvLogic CSV一括取込ロジック
     */
    public void setTakePlInfoCsvLogic(TakePlInfoCsvLogic takePlInfoCsvLogic) {
        this.takePlInfoCsvLogic = takePlInfoCsvLogic;
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
     * P/Lデータ登録ロジックを取得します。
     * @return P/Lデータ登録ロジック
     */
    public RegistPLDataLogic getRegistPLDataLogic() {
        return registPLDataLogic;
    }

    /**
     * P/Lデータ登録ロジックを設定します。
     * @param registPLDataLogic P/Lデータ登録ロジック
     */
    public void setRegistPLDataLogic(RegistPLDataLogic registPLDataLogic) {
        this.registPLDataLogic = registPLDataLogic;
    }

    /**
     * P/L作成者情報取得ロジックを取得します。
     * @return P/L作成者情報取得ロジック
     */
    public GetPLAuthorLogic getGetPLAuthorLogic() {
        return getPLAuthorLogic;
    }

    /**
     * P/L作成者情報取得ロジックを設定します。
     * @param getPLAuthorLogic P/L作成者情報取得ロジック
     */
    public void setGetPLAuthorLogic(GetPLAuthorLogic getPLAuthorLogic) {
        this.getPLAuthorLogic = getPLAuthorLogic;
    }

    /**
     * P/Lデータ店名称取得ロジックを取得します。
     * @return P/Lデータ店名称取得ロジック
     */
    public GetPLMiseNameLogic getGetPLMiseNameLogic() {
        return getPLMiseNameLogic;
    }

    /**
     * P/Lデータ店名称取得ロジックを設定します。
     * @param getPLMiseNameLogic P/Lデータ店名称取得ロジック
     */
    public void setGetPLMiseNameLogic(GetPLMiseNameLogic getPLMiseNameLogic) {
        this.getPLMiseNameLogic = getPLMiseNameLogic;
    }

    /**
     * P/Lデータオーナー名称取得ロジックを取得します。
     * @return P/Lデータオーナー名称取得ロジック
     */
    public GetPLOwnerNameLogic getGetPLOwnerNameLogic() {
        return getPLOwnerNameLogic;
    }

    /**
     * P/Lデータオーナー名称取得ロジックを設定します。
     * @param getPLOwnerNameLogic P/Lデータオーナー名称取得ロジック
     */
    public void setGetPLOwnerNameLogic(GetPLOwnerNameLogic getPLOwnerNameLogic) {
        this.getPLOwnerNameLogic = getPLOwnerNameLogic;
    }

    /**
     * オーナーコード存在判定ロジックを取得します。
     * @return オーナーコード存在判定ロジック
     */
    public ExistOwnerCodeLogic getExistOwnerCodeLogic() {
        return existOwnerCodeLogic;
    }

    /**
     * オーナーコード存在判定ロジックを設定します。
     * @param existOwnerCodeLogic オーナーコード存在判定ロジック
     */
    public void setExistOwnerCodeLogic(ExistOwnerCodeLogic existOwnerCodeLogic) {
        this.existOwnerCodeLogic = existOwnerCodeLogic;
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
     * ユーザ関連情報を取得します。
     * @return ユーザ関連情報
     */
    public BirdUserInfo getBirdUserInfo() {
        return birdUserInfo;
    }

    /**
     * ユーザ関連情報を設定します。
     * @param birdUserInfo ユーザ関連情報
     */
    public void setBirdUserInfo(BirdUserInfo birdUserInfo) {
        this.birdUserInfo = birdUserInfo;
    }

    /**
     * 日付情報を取得します。
     * @return 日付情報
     */
    public BirdDateInfo getBirdDateInfo() {
        return birdDateInfo;
    }

    /**
     * 日付情報を設定します。
     * @param birdDateInfo 日付情報
     */
    public void setBirdDateInfo(BirdDateInfo birdDateInfo) {
        this.birdDateInfo = birdDateInfo;
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
     * @param lumpTakeInPlYmVerifier 対象年月バリデータ
     */
    public void setLumpTakeInPlYmVerifier(DateVerifier lumpTakeInPlYmVerifier) {
        this.lumpTakeInPlYmVerifier = lumpTakeInPlYmVerifier;
    }

    /**
     * コードバリデータを取得します。
     * @return コードバリデータ
     */
    public CodeVerifier getLumpTakeInCodeVerifier() {
        return lumpTakeInCodeVerifier;
    }

    /**
     * コードバリデータを設定します。
     * @param lumpTakeInCodeVerifier コードバリデータ
     */
    public void setLumpTakeInCodeVerifier(CodeVerifier lumpTakeInCodeVerifier) {
        this.lumpTakeInCodeVerifier = lumpTakeInCodeVerifier;
    }

    /**
     * 作成年月日バリデータを取得します。
     * @return 作成年月日バリデータ
     */
    public DateVerifier getLumpTakeInAuthDtVerifier() {
        return lumpTakeInAuthDtVerifier;
    }

    /**
     * 作成年月日バリデータを設定します。
     * @param lumpTakeInAuthDtVerifier 作成年月日バリデータ
     */
    public void setLumpTakeInAuthDtVerifier(
            DateVerifier lumpTakeInAuthDtVerifier) {
        this.lumpTakeInAuthDtVerifier = lumpTakeInAuthDtVerifier;
    }

    /**
     * 電話番号バリデータを取得します。
     * @return 電話番号バリデータ
     */
    public PhoneVerifier getLumpTakeInPhoneVerifier() {
        return lumpTakeInPhoneVerifier;
    }

    /**
     * 電話番号バリデータを設定します。
     * @param lumpTakeInPhoneVerifier 電話番号バリデータ
     */
    public void setLumpTakeInPhoneVerifier(PhoneVerifier lumpTakeInPhoneVerifier) {
        this.lumpTakeInPhoneVerifier = lumpTakeInPhoneVerifier;
    }

    /**
     * 日本語バリデータを取得します。
     * @return 日本語バリデータ
     */
    public DefaultJapaneseVerifier getLumpTakeInJapaneseVerifier() {
        return lumpTakeInJapaneseVerifier;
    }

    /**
     * 日本語バリデータを設定します。
     * @param lumpTakeInPhoneVerifier 日本語バリデータ
     */
    public void setLumpTakeInJapaneseVerifier(
            DefaultJapaneseVerifier lumpTakeInJapaneseVerifier) {
        this.lumpTakeInJapaneseVerifier = lumpTakeInJapaneseVerifier;
    }

    /**
     * 初期処理
     * 
     * @return 画面遷移情報
     */
    public String initialize() {

        // コンポーネント取得
        S2Container container = SingletonS2ContainerFactory.getContainer();
        PullDownMenuDto pullDownMenuDto = (PullDownMenuDto) container.getComponent(PullDownMenuDto.class);
        BirdUserInfo birdUserInfo = (BirdUserInfo) container.getComponent(BirdUserInfo.class);
        BirdDateInfo birdDateInfo = (BirdDateInfo) container.getComponent(BirdDateInfo.class);
        LumpTakeInDto lumpTakeInDto = (LumpTakeInDto) container.getComponent("lumpTakeInDto");
        OwnerSearchDto ownerSearchDto = (OwnerSearchDto) container.getComponent("ownerSearchDto");
        GetPLAuthorLogic getPLAuthorLogic = (GetPLAuthorLogic) container.getComponent("lumpTakeIn.getPLAuthorLogic");
        GetPLOwnerNameLogic getPLOwnerNameLogic = (GetPLOwnerNameLogic) container.getComponent("lumpTakeIn.getPLOwnerNameLogic");

        // 初期処理
        if (pullDownMenuDto.isClearFlg()) {
            // ユーザタイプ判定
            String ownerCd = null;
            int viewMode = 0;
            String userTypeCd = birdUserInfo.getMstUser().getUserTypeCd();
            if (userTypeCd.equals("01")) {
                // 表示モードをオーナー選択に設定
                viewMode = VIEWMODE_OWNER_SELECT;
                // オーナーコード初期化
                ownerCd = null;
            } else if (userTypeCd.equals("02")) {
                // 表示モードを条件画面に設定
                viewMode = VIEWMODE_CONDITION;
                // 自オーナーコード取得
                List ownerList = birdUserInfo.getUserOner();
                for (Iterator it = ownerList.iterator(); it.hasNext();) {
                    UIUserOner uIUserOner = (UIUserOner) it.next();
                    if (uIUserOner.getCompanyCd().equals("00")) {
                        ownerCd = uIUserOner.getOnerCd();
                        break;
                    }
                }
                // オーナー名称取得
                lumpTakeInDto.setOwnerName(getPLOwnerNameLogic.execute(ownerCd));
            } else {
                throw new CannotAccessException();
            }
            // 表示モード設定
            lumpTakeInDto.setSelectViewMode(viewMode);
            // オーナーコード設定
            lumpTakeInDto.setOwnerCd(ownerCd);
            // 項目初期化
            lumpTakeInDto.setPlYm(null);
            lumpTakeInDto.setLastPlYm(null);
            lumpTakeInDto.setLastOwnerCd(null);

            // P/L作成者情報取得
            getPlAuthorInfo(lumpTakeInDto, getPLAuthorLogic, ownerCd);
            lumpTakeInDto.setAuthDt(birdDateInfo.getSysDate());

            // 対象年月選択肢リスト生成
            lumpTakeInDto.setPlYmList(createTargetDateList(birdDateInfo.getSysDate(), MONTHS_PLYM, -1));

            pullDownMenuDto.setClearFlg(false);
        }

        // オーナー選択
        if (ownerSearchDto.isActionFlag()) {
            // オーナーコード取得
            lumpTakeInDto.setOwnerCd(ownerSearchDto.getOnerCd());

            ownerSearchDto.setActionFlag(false);
        }

        // 自画面へ遷移
        return null;
    }

    /**
     * CSV一括取込
     * 
     * @return 画面遷移情報
     */
    public String takeCsvFile() {

        // 入力値チェック
        validate();

        // CSV読込
        List plInfoList = getTakePlInfoCsvLogic().execute(getLumpTakeInDto());

        // オーナー/店コード重複チェック
        checkDuplicate(plInfoList);

        List errorList = new ArrayList();
        for (Iterator it = plInfoList.iterator(); it.hasNext();) {
            TrnPLInfo trnPLInfo = (TrnPLInfo) it.next();

            // オーナーコード一致チェック
            checkOwnerCode(trnPLInfo);

            // P/Lデータチェック
            getCheckPLDataLogic().execute(trnPLInfo);   

            // 店コードチェックOKのみ登録
            if (!trnPLInfo.getPlDataErrorInfo().isExistErrorMiseCd()
                    && !trnPLInfo.getPlDataErrorInfo().isExistErrorMiseCdDuplicate()) {
                // P/Lデータ登録
                getRegistPLDataLogic().execute(
                        getBirdUserInfo().getUserID(), getBirdDateInfo().getSysDate(), getLumpTakeInDto(), trnPLInfo);
            } else {
                // 店名称取得
                trnPLInfo.setMiseNameKj(getGetPLMiseNameLogic().execute(trnPLInfo.getPlType(), trnPLInfo.getMiseCd()));
                // エラーレコードとする
                trnPLInfo.setErrFlg("1");
                errorList.add(trnPLInfo);
            }
        }

        // 登録状況取得
        List plInfoStatusList = getGetPLInfoStatusLogic().execute(
                getLumpTakeInDto().getPlYm(), getLumpTakeInDto().getOwnerCd());
        // P/Lデータ再チェック
        for (Iterator it = plInfoStatusList.iterator(); it.hasNext();) {
            getCheckPLDataLogic().execute((TrnPLInfo) it.next());   
        }
        // 店コードエラーレコードを追加
        plInfoStatusList.addAll(errorList);

        getLumpTakeInDto().setPlDataList(plInfoStatusList);
        getLumpTakeInDto().setPlDataErrorList(errorList);

        // 取込時キー項目を保持
        getLumpTakeInDto().setLastOwnerCd(getLumpTakeInDto().getOwnerCd());
        getLumpTakeInDto().setLastPlYm(getLumpTakeInDto().getPlYm());

        // 結果画面へ遷移
        return VIEWID_RESULT;
    }

    /**
     * エラー状況確認
     * 
     * @return 画面遷移情報
     */
    public String referError() {

        // 入力値チェック
        validate();

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

        // 結果画面へ遷移
        return VIEWID_RESULT;
    }

    /**
     * オーナー選択画面呼出
     * 
     * @return 画面遷移情報
     */
    public String callOwnerSearch() {

        // パラメータ設定
        getOwnerSearchDto().clear();
        getOwnerSearchDto().setNavigationCase(VIEWID_CONDITION);
        getOwnerSearchDto().setInitFlag(true);

        // オーナー選択画面へ遷移
        return VIEWID_OWNER_SEARCH;
    }

    /**
     * オーナー選択
     * 
     * @return 画面遷移情報
     */
    public String selectOwnerCode() {

        // オーナーコードチェック
        validateOwnerCode();
        // オーナー名称取得
        getLumpTakeInDto().setOwnerName(getGetPLOwnerNameLogic().execute(getLumpTakeInDto().getOwnerCd()));
        // P/L作成者情報取得
        getPlAuthorInfo(getLumpTakeInDto(), getGetPLAuthorLogic(), getLumpTakeInDto().getOwnerCd());
        lumpTakeInDto.setAuthDt(getBirdDateInfo().getSysDate());

        // 表示モード変更
        getLumpTakeInDto().setSelectViewMode(VIEWMODE_CONDITION);
        // 対象年月初期化
        lumpTakeInDto.setPlYm(null);

        // 自画面へ遷移
        return null;
    }

    /**
     * 戻る
     * 
     * @return 画面遷移情報
     */
    public String back() {

        // 表示モード変更
        getLumpTakeInDto().setSelectViewMode(VIEWMODE_OWNER_SELECT);
        // オーナーコード初期化
        getLumpTakeInDto().setOwnerCd(null);

        // 自画面へ遷移
        return null;
    }

    /**
     * P/L作成者情報取得
     * @param onerCd オーナーコード
     */
    private void getPlAuthorInfo(LumpTakeInDto lumpTakeInDto, GetPLAuthorLogic getPLAuthorLogic, String onerCd) {

        // P/L作成者情報取得
        TrnPLAuthorInfo trnPLAuthorInfo = getPLAuthorLogic.execute(onerCd);

        // 作成者情報が存在すれば設定、存在しなければ初期化
        lumpTakeInDto.setAuthorName((trnPLAuthorInfo == null) ? null : trnPLAuthorInfo.getAuthorName());
//        lumpTakeInDto.setAuthDt((trnPLAuthorInfo == null) ? null : trnPLAuthorInfo.getAuthDt());
        lumpTakeInDto.setAuthPhoneNum((trnPLAuthorInfo == null) ? null : trnPLAuthorInfo.getAuthPhoneNum());
        lumpTakeInDto.setAuthOther((trnPLAuthorInfo == null) ? null : trnPLAuthorInfo.getAuthOther());
        lumpTakeInDto.setKessanDt((trnPLAuthorInfo == null) ? null : trnPLAuthorInfo.getKessanDt());
    }

    /**
     * 入力値チェック
     *
     */
    private void validate() {

        // 対象年月
        String plYm = getLumpTakeInDto().getPlYm();
        if (plYm == null || plYm.trim().length() == 0) {
            throw new NotNullException("対象年月", "plYm", null);
        }
        if (!getLumpTakeInPlYmVerifier().validate(plYm)) {
            throw new InvalidInputException("対象年月", "plYm", null);
        }

        // 作成年月日
        String authDt = getLumpTakeInDto().getAuthDt();
        if (authDt == null || authDt.trim().length() == 0) {
            throw new NotNullException("作成年月日", "authDt", null);
        }
        if (!getLumpTakeInAuthDtVerifier().validate(authDt)) {
            throw new InvalidInputException("作成年月日", "authDt", null);
        }

        // 作成者電話番号
        String authPhoneNum = getLumpTakeInDto().getAuthPhoneNum();
        if (authPhoneNum == null || authPhoneNum.trim().length() == 0) {
            throw new NotNullException("作成者電話番号", "authPhoneNum", null);
        }
        if (!getLumpTakeInPhoneVerifier().validate(authPhoneNum)) {
            throw new InvalidInputException("作成者電話番号", "authPhoneNum", null);
        }

        // 作成者会計事務所等
        String authOther = getLumpTakeInDto().getAuthOther();
        if (authOther == null) {
            // 未入力を許可
            authOther = "";
            getLumpTakeInDto().setAuthOther(authOther);
        }
        if (authOther.getBytes().length > 40
                || !getLumpTakeInJapaneseVerifier().validate(authOther)) {
            throw new InvalidInputException("作成者会計事務所等", "authOther", null);
        }

        // 作成者
        String authorName = getLumpTakeInDto().getAuthorName();
        if (authorName == null || authorName.trim().length() == 0) {
            throw new NotNullException("作成者", "authorName", null);
        }
        if (authorName.getBytes().length > 40
                || !getLumpTakeInJapaneseVerifier().validate(authorName)) {
            throw new InvalidInputException("作成者", "authorName", null);
        }

        // 決算月
        String kessanDt = getLumpTakeInDto().getKessanDt();
        if (kessanDt == null || kessanDt.trim().length() == 0) {
            throw new NotNullException("決算月", "kessanDt", null);
        }
        if (kessanDt.getBytes().length > 2 || !validateMonth(kessanDt)) {
            throw new InvalidInputException("決算月", "kessanDt", null);
        }
    }

    /**
     * オーナーコードチェック
     *
     */
    private void validateOwnerCode() {

        // オーナーコード
        String ownerCd = getLumpTakeInDto().getOwnerCd();
        if (ownerCd == null || ownerCd.trim().length() == 0) {
            throw new NotNullException("オーナーコード", "ownerCd", null);
        }
        if (!getLumpTakeInCodeVerifier().validate(ownerCd)) {
            throw new InvalidInputException("オーナーコード", "ownerCd", null);
        }
        // 存在チェック
        if (!getExistOwnerCodeLogic().execute(ownerCd)) {
            throw new NotExistException("オーナーコード", "ownerCd", null);
        }
    }

    /**
     * オーナーコード一致チェック
     *
     */
    private void checkOwnerCode(TrnPLInfo trnPLInfo) {

        // 本社P/Lのみチェックを行う
        if (trnPLInfo.getPlType().equals("0")) {
            // 入力値とCSV上のオーナーコードが一致するか
            if (!getLumpTakeInDto().getOwnerCd().equals(trnPLInfo.getMiseCd())) {
                // エラー設定
                trnPLInfo.getPlDataErrorInfo().add(
                        TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_INVALID);
            }
        }
    }

    /**
     * オーナー/店コード重複チェック
     * @param plInfoList P/Lデータリスト
     */
    private void checkDuplicate(List plInfoList) {

        // 重複店コードセット作成
        Set miseCodeSet = new HashSet();
        Set duplicateSet = new HashSet();
        for (Iterator it = plInfoList.iterator(); it.hasNext();) {
            TrnPLInfo trnPLInfo = (TrnPLInfo) it.next();
            // 本社P/Lの場合、入力値とCSV上のオーナーコードが一致するか
            if (trnPLInfo.getPlType().equals("0")
                    && !getLumpTakeInDto().getOwnerCd().equals(trnPLInfo.getMiseCd())) {
                // エラー設定
                trnPLInfo.getPlDataErrorInfo().add(
                        TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_INVALID);
            } else {
                // 既に設定済みの店コードか？
                if (miseCodeSet.contains(trnPLInfo.getMiseCd())) {
                    // 既に存在する場合、重複とする
                    duplicateSet.add(trnPLInfo.getMiseCd());
                } else {
                    miseCodeSet.add(trnPLInfo.getMiseCd());
                }
            }
        }

        // 重複する店コードを保持するP/Lデータにエラーを設定する
        for (Iterator it = plInfoList.iterator(); it.hasNext();) {
            TrnPLInfo trnPLInfo = (TrnPLInfo) it.next();
            if (duplicateSet.contains(trnPLInfo.getMiseCd())) {
                // エラー設定
                trnPLInfo.getPlDataErrorInfo().add(
                        TrnPLInfo.miseCd_COLUMN, PlDataErrorInfo.ERRORCODE_DUPLICATE);
            }
        }
    }

    /**
     * 月妥当性判定
     * @param month 判定対象月文字列
     * @return　判定結果
     */
    private boolean validateMonth(String month) {
        return validateDate(month, "MM");
    }

    /**
     * 日付妥当性判定
     * @param date 判定対象日付
     * @param pattern 日付パターン
     * @return　判定結果
     */
    private boolean validateDate(String date, String pattern) {
        DateFormat parser = new SimpleDateFormat(pattern);
        // 厳密な解析を行う
        parser.setLenient(false);
        boolean result = false;
        try {
            // 日付解析
            result = parser.parse(date) != null;
        } catch (ParseException e) {
            // 無処理
        }
        return result;
    }

    /**
     * 対象年月リスト作成
     * 
     * @param baseDate 基準日付
     * @param months 月数
     * @param offset オフセット
     * @return 対象年月リスト
     */
    private List createTargetDateList(String baseDate, int months, int offset) {

        // YYYYMMDD形式の文字列からカレンダー作成
        Calendar calendar = Calendar.getInstance();
        try {
            calendar.setTime((new SimpleDateFormat("yyyyMMdd")).parse(baseDate));
        } catch (ParseException e) {
            throw new FtlSystemException("連絡登録");
        }

        // オフセット反映
        calendar.add(Calendar.MONTH, offset);

        // YYYYMM形式で指定範囲の年月リストを作成
        List targetDateList = new ArrayList();
        DateFormat formatter = new SimpleDateFormat("yyyyMM");
        DateFormatter commonFormatter
                = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
        for (int i = 0; i < months; i++) {
            targetDateList.add(new SelectItem(
                    commonFormatter.format(formatter.format(calendar.getTime()), false),
                    commonFormatter.format(formatter.format(calendar.getTime()), true)));
            calendar.add(Calendar.MONTH, -1);
        }

        return targetDateList;
    }
}
