package jp.co.isid.mos.bird.portal.login.logic.impl;

import java.util.List;

import jp.co.isid.mos.bird.framework.dto.LoginDto;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.exception.GenericErrorException;
import jp.co.isid.mos.bird.portal.login.dto.MatrixDto;
import jp.co.isid.mos.bird.portal.login.entity.MatrixEntity;
import jp.co.isid.mos.bird.portal.login.entity.UIUserMatrixInfo;
import jp.co.isid.mos.bird.portal.login.logic.CheckInputMatrixInfoLogic;
import jp.co.isid.mos.bird.portal.login.logic.GetMatrixInfoLogic;


/**
 * @author xnkusama
 */
public class CheckInputMatrixInfoLogicImpl implements CheckInputMatrixInfoLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BPO000L07";
    
    private static final String ERR_MSG_TITLE = "認証";
    private static final String ERR_MSG_DETAIL = "正しい位置を選択して下さい。";

    private GetMatrixInfoLogic getMatrixInfoLogicImpl;
    
    /**
     *  マトリクス認証入力情報チェック
     * @param MatrixDto
     * @param LoginDto
     * @exception ApplicationException 
     */
    public void check(final MatrixDto dto, final LoginDto loginDto) throws ApplicationException {
        UIUserMatrixInfo uiUserMatrixInfo = getGetMatrixInfoLogicImpl().getMatrixInfoLogic(loginDto);

        // 認証画面に表示されたマトリクス情報
        List matrixList = dto.getMatrixList();
        // 入力値
//--- 2008/11/14 update        
//        String inpVal1 = dto.getKey1();
//        String inpVal2 = dto.getKey2();
//        String inpVal3 = dto.getKey3();
        String matrixValue = dto.getMatrixValue();
        String inpVal1 = "";
        String inpVal2 = "";
        String inpVal3 = "";
        if (matrixValue != null) {
            if (matrixValue.length() >= 2) {
                inpVal1 = matrixValue.substring(0, 2);
            }
            if (matrixValue.length() >= 4) {
                inpVal2 = matrixValue.substring(2, 4);
            }
            if (matrixValue.length() >= 6) {
                inpVal3 = matrixValue.substring(4, 6);
            }
        }
//---2008/11/14 update end
        
        // 入力チェック
        checkMatrix(uiUserMatrixInfo, matrixList, inpVal1, inpVal2, inpVal3);
    }

    /**
     * マトリクス認証入力チェック
     * @param entity ユーザーのキー情報(br16mtrx)
     * @param dispValues 画面に表示した値
     * @param inpVal1 入力した値１
     * @param inpVal2 入力した値２
     * @param inpVal3 入力した値３
     * @return
     */
    private boolean checkMatrix(UIUserMatrixInfo entity, 
                                  List dispValues, 
                                  String inpVal1, 
                                  String inpVal2, 
                                  String inpVal3) 
    {
        String col = "";
        int row = 0;
        
        // 第１キー
        String key1 = entity.getMatrixKey1();
        col = key1.substring(0, 1);
        row = Integer.valueOf(key1.substring(1)).intValue() - 1;
        String key1Val = ((MatrixEntity) dispValues.get(row)).getValue(col);
        if (!key1Val.equals(convUpperCase(inpVal1))) {
            throw new GenericErrorException(ERR_MSG_TITLE, ERR_MSG_DETAIL);
        }
        
        // 第２キー
        String key2 = entity.getMatrixKey2();
        col = key2.substring(0, 1);
        row = Integer.valueOf(key2.substring(1)).intValue() - 1;
        String key2Val = ((MatrixEntity) dispValues.get(row)).getValue(col);
        if (!key2Val.equals(convUpperCase(inpVal2))) {
            throw new GenericErrorException(ERR_MSG_TITLE, ERR_MSG_DETAIL);
        }
        
        // 第３キー
        String key3 = entity.getMatrixKey3();
        col = key3.substring(0, 1);
        row = Integer.valueOf(key3.substring(1)).intValue() - 1;
        String key3Val = ((MatrixEntity) dispValues.get(row)).getValue(col);
        if (!key3Val.equals(convUpperCase(inpVal3))) {
            throw new GenericErrorException(ERR_MSG_TITLE, ERR_MSG_DETAIL);
        }
        
        return true;
    }
    
    private String convUpperCase(String val) {
        String ret = "";
        if (val != null) {
            ret = val.toUpperCase();
        }
        return ret.trim();
    }

    public GetMatrixInfoLogic getGetMatrixInfoLogicImpl() {
        return getMatrixInfoLogicImpl;
    }

    public void setGetMatrixInfoLogicImpl(GetMatrixInfoLogic getMatrixInfoLogicImpl) {
        this.getMatrixInfoLogicImpl = getMatrixInfoLogicImpl;
    }
    
}