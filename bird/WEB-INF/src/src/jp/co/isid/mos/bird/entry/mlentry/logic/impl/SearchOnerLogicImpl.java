package jp.co.isid.mos.bird.entry.mlentry.logic.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import jp.co.isid.mos.bird.entry.mlentry.common.MlEntryCommon;
import jp.co.isid.mos.bird.entry.mlentry.dao.MstMiseDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.MstOnerDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryOnerDao;
import jp.co.isid.mos.bird.entry.mlentry.dao.UIEntryOnerStatusDao;
import jp.co.isid.mos.bird.entry.mlentry.dto.MlEntryDto;
import jp.co.isid.mos.bird.entry.mlentry.entity.MstMise;
import jp.co.isid.mos.bird.entry.mlentry.entity.MstOner;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOner;
import jp.co.isid.mos.bird.entry.mlentry.entity.UIEntryOnerStatus;
import jp.co.isid.mos.bird.entry.mlentry.logic.SearchOnerLogic;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.exception.NotSelectedException;

import org.seasar.framework.container.S2Container;
import org.seasar.framework.container.factory.SingletonS2ContainerFactory;

/**
 * エントリーマスタ管理の検索ロジック
 * オーナー情報を検索・設定する
 * @author Aspac
 */
public class SearchOnerLogicImpl implements SearchOnerLogic {

    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BEN008L06";

    /**
     * 店統合マスタ
     */
    private MstMiseDao mstMiseMlEntryDao;
    /**
     * オーナー別エントリー状況
     */
    private UIEntryOnerStatusDao uiEntryOnerStatusMlEntryDao;
    /**
     * エントリーオーナー宛先情報
     */
    private UIEntryOnerDao uiEntryOnerMlEntryDao;
    /**
     * オーナー情報
     */
    private MstOnerDao mstOnerMlEntryDao;
    
    
    
    /**
     * エントリーオーナー宛先情報(結果報告先・担当者)を検索・設定する。(BT21ENOJ)
     * @param MlEntryDto
     */
    public void getEntrySofuOnerList(MlEntryDto dto){
        
        // エントリーオーナー宛先情報の検索 (BT21ENOJ)
        List listEntryOwner = getUiEntryOnerMlEntryDao().getEntryOner(
                dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(),dto.getCondOnerCd());
        dto.setListEntryOner(listEntryOwner);
        
        // エントリーオーナー宛先情報の不足分レコード作成
        makeEntryOwner(dto);
        
        // 結果をDTOへセット
        setEntryOwnerKekkaHokoku(dto);//結果報告先
        setEntryOwnerTanto(dto);//担当者
    }
    
    
    
    
    
    /**
     * オーナー別エントリー状況を検索・設定する (BT20ENON)
     * @param MlEntryDto
     */
    public void getEntryOnerList(MlEntryDto dto) {
        
        // オーナー別エントリー状況の検索 (BT20ENON)
        List listEntryOwnerStatus = getUiEntryOnerStatusMlEntryDao().getEntryOner(
                dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(), dto.getCondOnerCd());
        
        // 不足レコードの作成
        UIEntryOnerStatus uiEntryOnerStatus = null;
        if (listEntryOwnerStatus != null && listEntryOwnerStatus.size() != 0) {
            uiEntryOnerStatus = (UIEntryOnerStatus) listEntryOwnerStatus.get(0);
        }
        else {
            // 存在しない場合は、新規レコードをセット
            uiEntryOnerStatus = new UIEntryOnerStatus();
            uiEntryOnerStatus.setEntryCd(dto.getEntryCd());
            uiEntryOnerStatus.setEntryYear(dto.getEntryYear());
            uiEntryOnerStatus.setEntryKai(dto.getEntryKai());
            uiEntryOnerStatus.setCompanyCd(dto.getCondCompanyCd());
            uiEntryOnerStatus.setOnerCd(dto.getCondOnerCd());
            uiEntryOnerStatus.setInsertFlg(true);
        }
        
        // 結果をDTOへセット
        dto.setUiEntryOnerStatus(uiEntryOnerStatus);
    }
    
    
    /**
     * 選択されたマスターライセンスの情報を検索する
     * @param MlEntryDto     
     * */
    public void execute(MlEntryDto dto) throws ApplicationException {
        
    	// 妥当性チェック
        validate(dto);
        
        // オーナー情報設定
        setOnerInfo(dto);
    }
    
    
    /**
     * オーナー情報を検索、設定する。
     */
    private void setOnerInfo(MlEntryDto dto){
        
        // パラメータ
        String companyCd = dto.getCondCompanyCd();
        String miseCd    = dto.getCondMiseCd();
        
        // オーナーコード取得(本部ユーザーの場合のみ)
        if (MlEntryCommon.USER_TYPE_CD_HONBU.equals(dto.getUserTypeCd())) {
//            List listMiseInfo = getMstMiseMlEntryDao().getMiseInfo(companyCd, miseCd);
//            if (listMiseInfo == null || listMiseInfo.size() == 0) {
//                throw new NotExistException("対象店舗", "condMiseCd", 0);
//            }
//            MstMise mstMise = (MstMise) listMiseInfo.get(0);
            
            MstMise mstMise = getMstMiseMlEntryDao().getMiseInfo(companyCd, miseCd);
            if (mstMise == null) {
                throw new NotExistException("対象店舗", "condMiseCd", 0);
            }
            
            String onerCd = mstMise.getOnerCd();
            dto.setCondOnerCd(onerCd);
        }
        
        // オーナー情報取得
        String onerCd = dto.getCondOnerCd();
        List listOner = getMstOnerMlEntryDao().getOnerInfo(companyCd, onerCd);
        MstOner mstOner = (MstOner) listOner.get(0);
        dto.setMstOner(mstOner);
        dto.setCondOnerNameKj(mstOner.getOnerNameKj());
        
        
        // エントリーオーナー情報検索設定
        getEntryOnerList(dto);
        
        // エントリー担当・結果報告先オーナー情報検索設定
        getEntrySofuOnerList(dto);
        
    }
    

    /**
     * エントリーオーナー宛先情報の不足分レコード作成
     * ※エントリーオーナー情報から宛先区分別にデータをチェックし、
     * ※各宛先情報が存在しない場合には、各宛先情報にオーナー情報を保持させる。
     * ※マスターライセンス受験申込編集画面の宛先情報には、初期表示としてオーナー情報が入力されている。
     * @param listEntryOwner オーナー情報を保持
     * @param entryCd
     * @param entryYear
     * @param entryKai
     * @param companyCd
     * @param onerCd
     */
    private void makeEntryOwner(MlEntryDto dto) {
        
        List listEntryOwner = dto.getListEntryOner();
        
        if (listEntryOwner == null) {
            listEntryOwner = new ArrayList();
        }
        
        //結果報告先
        boolean flgExist = false;
        for (Iterator ite = listEntryOwner.iterator(); ite.hasNext();) {
            UIEntryOner entity = (UIEntryOner) ite.next();
            if (MlEntryCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI.equals(entity.getAtesakiKbn())) {
                flgExist = true;
                break;
            }
        }
        if (!flgExist) {
            UIEntryOner uiEntryOwnerKekkaHokoku = new UIEntryOner();
            uiEntryOwnerKekkaHokoku.setEntryCd(dto.getEntryCd());
            uiEntryOwnerKekkaHokoku.setEntryYear(dto.getEntryYear());
            uiEntryOwnerKekkaHokoku.setEntryKai(dto.getEntryKai());
            uiEntryOwnerKekkaHokoku.setCompanyCd(dto.getCondCompanyCd());
            uiEntryOwnerKekkaHokoku.setOnerCd(dto.getCondOnerCd());
            uiEntryOwnerKekkaHokoku.setAtesakiKbn(MlEntryCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI);
            uiEntryOwnerKekkaHokoku.setOnerNameKj(dto.getCondOnerNameKj());
            uiEntryOwnerKekkaHokoku.setName(dto.getMstOner().getOnerNameKj());
            uiEntryOwnerKekkaHokoku.setZip(dto.getMstOner().getSeikyuPostNo());
            uiEntryOwnerKekkaHokoku.setAddress1(dto.getMstOner().getSeikyuAdrs1());
            uiEntryOwnerKekkaHokoku.setAddress2(dto.getMstOner().getSeikyuAdrs2());
            uiEntryOwnerKekkaHokoku.setAddress3(dto.getMstOner().getSeikyuAdrs3());
// add start xkhata 20060904 デフォルトTEL対応
            uiEntryOwnerKekkaHokoku.setTel(dto.getMstOner().getOnerTel());
// add end
            uiEntryOwnerKekkaHokoku.setJobType("");
            uiEntryOwnerKekkaHokoku.setInsertFlg(true);
            listEntryOwner.add(uiEntryOwnerKekkaHokoku);
        }
        
        //担当者
        flgExist = false;
        for (Iterator ite = listEntryOwner.iterator(); ite.hasNext();) {
            UIEntryOner entity = (UIEntryOner) ite.next();
            if (MlEntryCommon.ATESAKI_KBN_TANTOU.equals(entity.getAtesakiKbn())) {
                flgExist = true;
                break;
            }
        }
        if (!flgExist) {
            UIEntryOner uiEntryOwnerTanto = new UIEntryOner();
            uiEntryOwnerTanto.setEntryCd(dto.getEntryCd());
            uiEntryOwnerTanto.setEntryYear(dto.getEntryYear());
            uiEntryOwnerTanto.setEntryKai(dto.getEntryKai());
            uiEntryOwnerTanto.setCompanyCd(dto.getCondCompanyCd());
            uiEntryOwnerTanto.setOnerCd(dto.getCondOnerCd());
            uiEntryOwnerTanto.setAtesakiKbn(MlEntryCommon.ATESAKI_KBN_TANTOU);
            uiEntryOwnerTanto.setOnerNameKj(dto.getCondOnerNameKj());
            uiEntryOwnerTanto.setName(dto.getMstOner().getOnerNameKj());
            uiEntryOwnerTanto.setZip(dto.getMstOner().getSeikyuPostNo());
            uiEntryOwnerTanto.setAddress1(dto.getMstOner().getSeikyuAdrs1());
            uiEntryOwnerTanto.setAddress2(dto.getMstOner().getSeikyuAdrs2());
            uiEntryOwnerTanto.setAddress3(dto.getMstOner().getSeikyuAdrs3());
// add start xkhata 20060904 デフォルトTEL対応
            uiEntryOwnerTanto.setTel(dto.getMstOner().getOnerTel());
// add end
            uiEntryOwnerTanto.setJobType("");
            uiEntryOwnerTanto.setInsertFlg(true);
            listEntryOwner.add(uiEntryOwnerTanto);

        }
        dto.setListEntryOner(listEntryOwner);
    }
    

    /**
     * エントリーオーナー情報の有無をチェックする
     * @param dto
     */
    public void isEntryOnerInfo(MlEntryDto dto) {
        
        //エントリーオーナー宛先情報の有無をチェックする (BT21ENOJ)
        isEntryOnerSofuInfo(dto);
        
        //エントリーオーナー状況情報の有無をチェックする (BT20ENON)
        isEntryOnerStateInfo(dto);

    }
    
    /**
     * エントリーオーナー宛先情報の有無をチェックする (BT21ENOJ)
     * @param MlEntryDto
     */
    private void isEntryOnerSofuInfo(MlEntryDto dto) {
        
        // エントリーオーナー宛先情報の検索 (BT21ENOJ)
        List listEntryOner = getUiEntryOnerMlEntryDao().getEntryOner(
                dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(),dto.getCondOnerCd());
        
        if (listEntryOner == null) {
            listEntryOner = new ArrayList();
        }
        
        //結果報告先
        boolean flgExist = false;
        for (Iterator ite = listEntryOner.iterator(); ite.hasNext();) {
            UIEntryOner entity = (UIEntryOner) ite.next();
            if (MlEntryCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI.equals(entity.getAtesakiKbn())) {
                flgExist = true;
                break;
            }
        }
        if (!flgExist) {
            // 存在しない場合は、新規レコードをセット
            dto.getUiEntryOnerKekkaHokoku().setInsertFlg(true);
        }
        else {
            dto.getUiEntryOnerKekkaHokoku().setInsertFlg(false);
        }
        
        //担当者
        flgExist = false;
        for (Iterator ite = listEntryOner.iterator(); ite.hasNext();) {
            UIEntryOner entity = (UIEntryOner) ite.next();
            if (MlEntryCommon.ATESAKI_KBN_TANTOU.equals(entity.getAtesakiKbn())) {
                flgExist = true;
                break;
            }
        }
        if (!flgExist) {
            // 存在しない場合は、新規レコードをセット
            dto.getUiEntryOnerTanto().setInsertFlg(true);
        }
        else {
            dto.getUiEntryOnerTanto().setInsertFlg(false);
        }
    }
    
    
    /**
     * エントリーオーナー状況情報の有無をチェックする (BT20ENON)
     * @param MlEntryDto
     */
    private void isEntryOnerStateInfo(MlEntryDto dto) {
        
        // オーナー別エントリー状況の検索 (BT20ENON)
        List listEntryOnerStatus = getUiEntryOnerStatusMlEntryDao().getEntryOner(
                dto.getEntryCd(), dto.getEntryYear(), dto.getEntryKai(), dto.getCondCompanyCd(), dto.getCondOnerCd());
        
        // 不足レコードの作成
        if (listEntryOnerStatus != null && listEntryOnerStatus.size() != 0) {
            
            dto.getUiEntryOnerStatus().setInsertFlg(false);
        }
        else {
            // 存在しない場合は、新規レコードをセット
            dto.getUiEntryOnerStatus().setInsertFlg(true);
        }
    }
    
    
    /**
     * エントリーオーナー宛先情報：担当者
     */
    public void setEntryOwnerTanto(MlEntryDto dto) {
        UIEntryOner uiEntryOner = null;
        if (dto.getListEntryOner() != null) {
            for (Iterator ite = dto.getListEntryOner().iterator(); ite.hasNext();) {
                uiEntryOner = (UIEntryOner) ite.next();
                if (MlEntryCommon.ATESAKI_KBN_TANTOU.equals(uiEntryOner.getAtesakiKbn())) {
                    dto.setUiEntryOnerTanto(uiEntryOner);
                    return;
                }
            }
        }
    }
    
    /**
     * エントリーオーナー宛先情報：結果報告先
     */
    public void setEntryOwnerKekkaHokoku(MlEntryDto dto) {
        UIEntryOner uiEntryOner = null;
        if (dto.getListEntryOner() != null) {
            for (Iterator ite = dto.getListEntryOner().iterator(); ite.hasNext();) {
                uiEntryOner = (UIEntryOner) ite.next();
                if (MlEntryCommon.ATESAKI_KBN_KEKKA_HOKOKUSAKI.equals(uiEntryOner.getAtesakiKbn())) {
                    dto.setUiEntryOnerKekkaHokoku(uiEntryOner);
                    return;
                }
            }
        }
    }
    
    /**
     * 必須、妥当性チェック
     * @param MlEntryDto
     */
    private void validate(MlEntryDto dto) throws ApplicationException {
        String companyCd = dto.getCondCompanyCd();
        String miseCd    = dto.getCondMiseCd();
        
        if (dto.getListEntryMst() == null) {
            throw new NotSelectedException("対象のコース", "indexFlg", 0);
        }
        if (isNull(companyCd)) {
            throw new NotNullException("会社コード", "condCompanyCd", 0);
        }
        
        if (MlEntryCommon.USER_TYPE_CD_HONBU.equals(dto.getUserTypeCd()) && isNull(miseCd)) {
            throw new NotNullException("対象店舗", "condMiseCd", 0);
        }
    }
    
    
    /**
     * nullチェック
     */
    private boolean isNull(String val) {
        return (val == null || val.trim().equals("")) ? true : false;
    }
    
    public UIEntryOnerStatusDao getUiEntryOnerStatusMlEntryDao() {
        return uiEntryOnerStatusMlEntryDao;
    }

    public void setUiEntryOnerStatusMlEntryDao(
            UIEntryOnerStatusDao uiEntryOnerStatusMlEntryDao) {
        this.uiEntryOnerStatusMlEntryDao = uiEntryOnerStatusMlEntryDao;
    }

    public MstMiseDao getMstMiseMlEntryDao() {
        return mstMiseMlEntryDao;
    }

    public void setMstMiseMlEntryDao(MstMiseDao mstMiseMlEntryDao) {
        this.mstMiseMlEntryDao = mstMiseMlEntryDao;
    }

    public UIEntryOnerDao getUiEntryOnerMlEntryDao() {
        return uiEntryOnerMlEntryDao;
    }

    public void setUiEntryOnerMlEntryDao(
            UIEntryOnerDao uiEntryOwnerDaoMlEntryDao) {
        this.uiEntryOnerMlEntryDao = uiEntryOwnerDaoMlEntryDao;
    }

    public MstOnerDao getMstOnerMlEntryDao() {
        return mstOnerMlEntryDao;
    }

    public void setMstOnerMlEntryDao(MstOnerDao mstOnerMlEntryDao) {
        this.mstOnerMlEntryDao = mstOnerMlEntryDao;
    }
    
    private BirdDateInfo getBirdDateInfo() {
        return (BirdDateInfo) getS2Container().getComponent(BirdDateInfo.class);
    }
    private S2Container getS2Container() {
        return SingletonS2ContainerFactory.getContainer(); 
    }
        
    
}
