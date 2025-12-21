package jp.co.isid.mos.bird.inforegist.documentregist.logic.impl;

import java.sql.Timestamp;
import java.util.ArrayList;

import jp.co.isid.mos.bird.common.dto.PublicTargetDto;
import jp.co.isid.mos.bird.common.logic.DeleteKanrenSakiBunshoLogic;
import jp.co.isid.mos.bird.common.logic.RegKanrenBunshoLogic;
import jp.co.isid.mos.bird.common.logic.RegKoukaiTaishoLogic;
import jp.co.isid.mos.bird.framework.entity.MstUser;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.CodeFormatter;
import jp.co.isid.mos.bird.inforegist.documentregist.dao.UIBunshoInfoDao;
import jp.co.isid.mos.bird.inforegist.documentregist.dto.DocumentRegistRegistFormDto;
import jp.co.isid.mos.bird.inforegist.documentregist.entity.UIBunshoInfo;
import jp.co.isid.mos.bird.inforegist.documentregist.logic.RegBunshoLogic;

public class RegBunshoLogicImpl implements RegBunshoLogic {
    
    private static final String VIEW_ID = "BIF003";
    /* ロジックID */
    public static final String LOGIC_ID = "BIF003L03";
    
    private static final int REG_MODE_INSERT = 1;
    private static final int REG_MODE_UPDATE = 2;
    private static final int REG_MODE_DELETE = 3;
    // 情報種別
    private static final String INFO_SHU = "03";
    
    // LOGIC
    private RegKoukaiTaishoLogic regKoukaiTaishoLogic;
    //【関連文書登録】
    private RegKanrenBunshoLogic regKanrenBunshoLogic;
    //【関連先文書削除】
    private DeleteKanrenSakiBunshoLogic deleteKanrenSakiBunshoLogic;
    
    // DAO
    private UIBunshoInfoDao uiBunshoInfoDao;
    
    /**
     * 文書情報の登録
     * @param RegistFormDto 編集画面DTO
     * @param PublicTargetDto 公開対象 共通DTO
     * @param MstUser ユーザー情報
     * @exception ApplicationException
     */
    public void registBunsho(DocumentRegistRegistFormDto dto, 
                               PublicTargetDto publicTargetDto,
                               MstUser user) 
                    throws ApplicationException 
    {
        // Entity
        UIBunshoInfo uiBunshoInfo = dto.getEditEntity();
        
        UIBunshoInfo entity = dto.getEditEntity();
        
// 2006/03/22 xkhata 排他制御対応
        Timestamp lastTmsp = entity.getLastTmsp();
// end
        if (dto.getRegMode() == REG_MODE_INSERT) {
            /* 新規 */
            // 現在のMAX SEQ番号
            int maxSeq = getUiBunshoInfoDao().getNumber(uiBunshoInfo.getRegDate());
            // SEQをセットしてから登録
            CodeFormatter codeFomatter = new CodeFormatter(4);
            codeFomatter.setFormatPattern("0000");
            Timestamp timestamp = DateManager.getCurrentTimestamp();
            entity.setSeq(codeFomatter.format(String.valueOf(maxSeq),true));
            entity.setFirstTmsp(timestamp);
            entity.setLastTmsp(timestamp);
            entity.setFirstPgm(VIEW_ID);
            entity.setLastPgm(VIEW_ID);
            getUiBunshoInfoDao().insertBunsho(entity);
            publicTargetDto.setRegDate(entity.getRegDate());
            publicTargetDto.setSeq(entity.getSeq());
            // 公開対象情報登録
            getRegKoukaiTaishoLogic().execute(
                    publicTargetDto, entity.getLastUser(), entity.getLastPgm());
            // ロジック【関連文書の登録】実行
            getRegKanrenBunshoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq(), dto.getListKanrenBunsho(),
                    entity.getLastUser(), entity.getLastPgm());

        }
        else if (dto.getRegMode() == REG_MODE_UPDATE) {
            /* 更新 */
            entity.setLastPgm(VIEW_ID);
            // 登録者、登録元、登録元名称、企業コードを更新者の情報で置き換える
            entity.setPubUser(user.getUser_id());
            entity.setPubOrg(user.getBumonCd());
            entity.setPubOrgName(user.getBumonName());
            entity.setRCompanyCd(user.getRCompanyCd());
            Timestamp lastTmspBk = entity.getLastTmsp();
            getUiBunshoInfoDao().updateBunsho(entity);
            entity.setLastTmsp(lastTmspBk);
            // 公開対象情報登録
            getRegKoukaiTaishoLogic().execute(
                    publicTargetDto, entity.getLastUser(), entity.getLastPgm());
            // ロジック【関連文書の登録】実行
            getRegKanrenBunshoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq(), dto.getListKanrenBunsho(),
                    entity.getLastUser(), entity.getLastPgm());

        }
        else if (dto.getRegMode() == REG_MODE_DELETE) {
            /* 削除 */
            // 削除フラグ＝'1'にしてから更新
            entity.setSakujoFlg("1");
            entity.setLastUser(user.getUser_id());
            //entity.setLastTmsp(DateManager.getCurrentTimestamp());
            entity.setLastPgm(VIEW_ID);
            getUiBunshoInfoDao().updateBunsho(entity);
            // 公開対象情報削除
            publicTargetDto.setListTrnControlCompany(new ArrayList());
            publicTargetDto.setListTrnControlGyotai(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiKobetu(new ArrayList());
            publicTargetDto.setListTrnControlGyotaiTenpo(new ArrayList());
            publicTargetDto.setListTrnControlShozoku(new ArrayList());
            getRegKoukaiTaishoLogic().execute(
                    publicTargetDto, entity.getLastUser(), entity.getLastPgm());

            //関連文書の削除
            dto.setListKanrenBunsho(new ArrayList());
            getRegKanrenBunshoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq(), dto.getListKanrenBunsho(),
                    entity.getLastUser(), entity.getLastPgm());
            //関連先から削除
            getDeleteKanrenSakiBunshoLogic().execute(INFO_SHU, entity.getRegDate(), entity.getSeq());
            
        }
// 2006/03/22 xkhata 排他制御対応
            entity.setLastTmsp( lastTmsp );
// end
    }
    
    /**
     * @param uiBunshoInfoDao uiBunshoInfoDao を設定。
     */
    public void setUiBunshoInfoDao(UIBunshoInfoDao uiBunshoInfoDao) {
        this.uiBunshoInfoDao = uiBunshoInfoDao;
    }

    /**
     * @return uiBunshoInfoDao を戻します。
     */
    public UIBunshoInfoDao getUiBunshoInfoDao() {
        return uiBunshoInfoDao;
    }
    
    /**
     * @return regKoukaiTaishoLogic を戻します。
     */
    public RegKoukaiTaishoLogic getRegKoukaiTaishoLogic() {
        return regKoukaiTaishoLogic;
    }
    /**
     * @param regKoukaiTaishoLogic regKoukaiTaishoLogic を設定。
     */
    public void setRegKoukaiTaishoLogic(
            RegKoukaiTaishoLogic regKoukaiTaishoLogic) {
        this.regKoukaiTaishoLogic = regKoukaiTaishoLogic;
    }
    /**
     * @return regKanrenBunshoLogic を戻します。
     */
    public RegKanrenBunshoLogic getRegKanrenBunshoLogic() {
        return regKanrenBunshoLogic;
    }
    /**
     * @param regKanrenBunshoLogic regKanrenBunshoLogic を設定。
     */
    public void setRegKanrenBunshoLogic(
            RegKanrenBunshoLogic regKanrenBunshoLogic) {
        this.regKanrenBunshoLogic = regKanrenBunshoLogic;
    }

    /**
     * 関連先文書削除ロジックを取得します。
     * @return 関連先文書削除ロジック 
     */
    public DeleteKanrenSakiBunshoLogic getDeleteKanrenSakiBunshoLogic() {
        return deleteKanrenSakiBunshoLogic;
    }

    /**
     * 関連先文書削除ロジックを設定します。
     * @param 関連先文書削除ロジック
     */
    public void setDeleteKanrenSakiBunshoLogic(
            DeleteKanrenSakiBunshoLogic deleteKanrenSakiBunshoLogic) {
        this.deleteKanrenSakiBunshoLogic = deleteKanrenSakiBunshoLogic;
    }
    
    
}
