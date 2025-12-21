package jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.impl;

import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dao.TrnMosChickenInfoDao;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.dto.MosChickenRevInfoDto;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.MstCmenuInfo;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.entity.TrnMosChikenDet;
import jp.co.isid.mos.bird.bizsupport.moschickenreservation.logic.CheckMosChickenLogic;
import jp.co.isid.mos.bird.framework.exception.CannotExecuteException;
import jp.co.isid.mos.bird.framework.exception.CannotInputException;
import jp.co.isid.mos.bird.framework.exception.GenericMessageException;
import jp.co.isid.mos.bird.framework.exception.InvalidInputException;
import jp.co.isid.mos.bird.framework.exception.NoInputException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.verifier.CodeVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.DefaultJapaneseVerifier;
import jp.co.isid.mos.bird.framework.util.verifier.NumericVerifier;
/**
 * モスチキン予約エラーチェックロジック
 * @author inazawa
 * 2006/09/19
 */
public class CheckMosChickenLogicImpl implements CheckMosChickenLogic {
    /** コードバリデータ */
    private CodeVerifier mosChickenRevCodeVerifier;
    private DefaultJapaneseVerifier mosChickenRevJapaneseVerifier;
    private NumericVerifier mosChickenRevDefaultNumericVerifier;
    /*マックスSEQNO取得Dao*/
    private TrnMosChickenInfoDao trnMosChickenInfoDao;
    /*新規／修正0：新規、2：修正*/
    public static final String sinki = "1";
    public static final String shusei  = "0";
    /** ロジックID 定義 */
    public static final String LOGIC_ID = "BBS016L01";

    public void validater(MosChickenRevInfoDto dto) {
    	//add start xlee 必須チェック お渡し時
    	
    	if(isNull(dto.getReservDate()) || isNull(dto.getReservDate())) {
    		throw new NotNullException("お渡し日 ");
    	}
    	if(isNull(dto.getReservHour()) || isNull(dto.getReservMin())) {
    		throw new NotNullException("お渡し時間 ");
    	}
    	// add end
        if(isNull(dto.getBikou())){
            throw new NotNullException("備考","bikou",null);
        }else{
        	if(dto.getBikou().replaceAll("　", "").length() == 0) {
        		//全角を入力した場合、全角スペースはエラー、全角スペース＋文字の場合は正常
        		throw new NotNullException("備考","bikou",null);
        	}
            if(dto.getBikou().getBytes().length > 100){
                throw new CannotInputException("備考は全角50文字以上半角100文字以上","bikou",null);
            }
            if(!getMosChickenRevJapaneseVerifier().validate(dto.getBikou())){
                throw new GenericMessageException("備考に不正な文字が含まれています。入力可能な文字についてはヘルプをご確認下さい。","bikou",null);
            }
        }
        //add start 2007/11/02 プレミアム、代金の必須チェック
        if (isNull(dto.getPremiumFlg())) {
            throw new NotNullException("プレミアム", "premiumFlg", null);
        }
        if (isNull(dto.getPaymentFlg())) {
            throw new NotNullException("代金", "paymentFlg", null);
        }
        //add end
        
        
        boolean amtZeroCheck = false;
        for(int i=0;dto.getListMosChiCkenDatailInfo().size()>i;i++){
            if(dto.getNewFlg().equals(shusei)){
                TrnMosChikenDet trnMosChikenDet = (TrnMosChikenDet)dto.getListMosChiCkenDatailInfo().get(i);
                if(isNull(trnMosChikenDet.getStrReserveAmt())){
                    throw new NoInputException(trnMosChikenDet.getMenuNameKj() + "の予約数","reserveAmt",i);
                }
                if(!getMosChickenRevDefaultNumericVerifier().validate(String.valueOf(trnMosChikenDet.getStrReserveAmt()))){
                    throw new InvalidInputException(trnMosChikenDet.getMenuNameKj()+"の予約数","reserveAmt",i);
                }
                if(trnMosChikenDet.getStrReserveAmt().length() > 3){
                   throw new InvalidInputException(trnMosChikenDet.getMenuNameKj()+"の予約数","reserveAmt",i);
                }
                if(trnMosChikenDet.getStrReserveAmt().indexOf("-") != -1){
                    throw new InvalidInputException(trnMosChikenDet.getMenuNameKj()+"の予約数","reserveAmt",i);
                }
                trnMosChikenDet.setReserveAmt(Integer.parseInt(trnMosChikenDet.getStrReserveAmt()));
                if(trnMosChikenDet.getReserveAmt() > 0){
                    amtZeroCheck = true;
                }
            }else if(dto.getNewFlg().equals(sinki)){
                MstCmenuInfo mstCmenuInfo = (MstCmenuInfo)dto.getListMosChiCkenDatailInfo().get(i);
                if(isNull(mstCmenuInfo.getStrReserveAmt())){
                    throw new NoInputException(mstCmenuInfo.getMenuNameKj() + "の予約数","reserveAmt",i);
                }
                if(!getMosChickenRevDefaultNumericVerifier().validate(String.valueOf(mstCmenuInfo.getStrReserveAmt()))){
                    throw new InvalidInputException(mstCmenuInfo.getMenuNameKj() + "の予約数","reserveAmt",i);
                }
                if(mstCmenuInfo.getStrReserveAmt().getBytes().length > 3){
                    throw new InvalidInputException(mstCmenuInfo.getMenuNameKj() + "の予約数","reserveAmt",i);
                }
                if(mstCmenuInfo.getStrReserveAmt().indexOf("-") != -1){
                    throw new InvalidInputException(mstCmenuInfo.getMenuNameKj()+"の予約数","reserveAmt",i);
                }
                mstCmenuInfo.setReserveAmt(Integer.parseInt(mstCmenuInfo.getStrReserveAmt()));
                if(mstCmenuInfo.getReserveAmt() > 0){
                    amtZeroCheck = true;
                }
            }
        }
        if(!amtZeroCheck){
            if(dto.getNewFlg().equals(sinki)){
                throw new GenericMessageException("予約数がすべて０になっています。");
            }else if(dto.getNewFlg().equals(shusei)){
                throw new GenericMessageException("予約数がすべて０になっています。予約をキャンセルする場合は予約キャンセルを実行して下さい。");
            }
        }
        if(!isNull(dto.getMemo())){
            if(dto.getMemo().getBytes().length > 200){
                throw new CannotInputException("メモは全角100文字以上半角200文字以上","memo",null);
            }
            if(!getMosChickenRevJapaneseVerifier().validate(dto.getMemo())){
                throw new GenericMessageException("メモに不正な文字が含まれています。入力可能な文字についてはヘルプをご確認下さい。","memo",null);
            }
        }

        int maxSwqNo = getTrnMosChickenInfoDao().getMaxSeqNo(dto.getCkanriNo(),dto.getMiseCd(),dto.getCompanyCd());
        if(maxSwqNo >= 99999){
            throw new CannotExecuteException("登録");
        }
        dto.setMaxSeqNo(maxSwqNo);
    }
    /**
     * nullチェック
     */ 
    private boolean isNull(String val) {
        if (val == null) {
            return true;
        }
        if ("".equals(val.trim())) {
            return true;
        }
        return false;
    }
    public CodeVerifier getMosChickenRevCodeVerifier() {
        return mosChickenRevCodeVerifier;
    }
    /**
     * mosChickenRevCodeVerifierを設定
     * @param mosChickenRevCodeVerifier
     */
    public void setMosChickenRevCodeVerifier(CodeVerifier mosChickenRevCodeVerifier) {
        this.mosChickenRevCodeVerifier = mosChickenRevCodeVerifier;
    }
    /**
     * trnMosChickenInfoDaoを返す
     * @return trnMosChickenInfoDao
     */
    public TrnMosChickenInfoDao getTrnMosChickenInfoDao() {
        return trnMosChickenInfoDao;
    }
    /**
     * trnMosChickenInfoDaoを設定
     * @param trnMosChickenInfoDao
     */
    public void setTrnMosChickenInfoDao(TrnMosChickenInfoDao trnMosChickenInfoDao) {
        this.trnMosChickenInfoDao = trnMosChickenInfoDao;
    }
    /**
     * mosChickenRevJapaneseVerifierを戻す
     * @return mosChickenRevJapaneseVerifier
     */
    public DefaultJapaneseVerifier getMosChickenRevJapaneseVerifier() {
        return mosChickenRevJapaneseVerifier;
    }
    /**
     * mosChickenRevJapaneseVerifierを返す
     * @param mosChickenRevJapaneseVerifier
     */
    public void setMosChickenRevJapaneseVerifier(
            DefaultJapaneseVerifier mosChickenRevJapaneseVerifier) {
        this.mosChickenRevJapaneseVerifier = mosChickenRevJapaneseVerifier;
    }
    /**
     * mosChickenRevDefaultNumericVerifierを返す
     * @return mosChickenRevDefaultNumericVerifier
     */
    public NumericVerifier getMosChickenRevDefaultNumericVerifier() {
        return mosChickenRevDefaultNumericVerifier;
    }
    /**
     * mosChickenRevDefaultNumericVerifierを設定
     * @param mosChickenRevDefaultNumericVerifier
     */
    public void setMosChickenRevDefaultNumericVerifier(
            NumericVerifier mosChickenRevDefaultNumericVerifier) {
        this.mosChickenRevDefaultNumericVerifier = mosChickenRevDefaultNumericVerifier;
    }

}
