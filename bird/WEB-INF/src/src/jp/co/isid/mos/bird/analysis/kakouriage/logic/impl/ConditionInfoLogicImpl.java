package jp.co.isid.mos.bird.analysis.kakouriage.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.kakouriage.code.KakoUriageConst;
import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageDto;
import jp.co.isid.mos.bird.analysis.kakouriage.dto.KakoUriageReqDto;
import jp.co.isid.mos.bird.analysis.kakouriage.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.dao.CodHyojiTaishoDao;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 条件項目の取得 ロジック
 * @author xnkusama
 *
 */
public class ConditionInfoLogicImpl implements ConditionInfoLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT005L01";

    /**DAO*/
    private CodHyojiTaishoDao codHyojiTaishoDao;
    
    
    public void execute(KakoUriageDto dto, KakoUriageReqDto dtoReq) {
        //１．初期処理
        validate(dto, dtoReq);
        
        //ユーザータイプ別処理
        if (UserType.ONER.equals(dto.getUserTypeCd())) {
            //２．オーナーユーザーの場合、保有店舗一覧を取得する
            List listHyojiTaisho = getCodHyojiTaishoDao().selectMise(dtoReq.getCompanyCd(), 
                                                                     dto.getUserId(), 
                                                                     dto.getUserTypeCd(), 
                                                                     dto.getBirdUserInfo().isLimit(), 
                                                                     dto.getBirdDateInfo().getAppDate());
            dto.setListHyojiTaisho(listHyojiTaisho);
        }
        
        //３．対象条件プルダウン用リスト作成
        dto.setListTaishoJoken(makeTaishoJokenList(dto, dtoReq));
        
        //４．期間期間プルダウン用リストを作成
        dto.setListTaishoKikan(makeKikanList(dto));
        
    }
    
    /**
     * 対象条件プルダウン作成
     * @return
     */
    private List makeTaishoJokenList(KakoUriageDto dto, KakoUriageReqDto dtoReq) {
        String[] codes = new String[]{TaishoJoken.CODE_ALL, TaishoJoken.CODE_MISE};
        
        return TaishoJoken.getPullDownList(dto.getUserTypeCd(), dtoReq.getCompanyCd(), dto.getBirdUserInfo().isLimit(), codes);
    }
    
    /**
     * 期間指定プルダウン作成
     * @return
     */
    private List makeKikanList(KakoUriageDto dto) {
        List listKikan = new ArrayList();
        String currentNendo = DateManager.getCurrentYear(dto.getBirdDateInfo().getAppDate());
        for (int i = 0; i < KakoUriageConst.KIKAN_PULLDOWN_LISTSU; i++) {
            try {
                String fromNendo = DateManager.getPrevYear(currentNendo, (KakoUriageConst.KIKAN_PULLDOWN_NENDOSU + i));
                String toNendo   = DateManager.getPrevYear(currentNendo, i);
                SelectItem sItem = new SelectItem(toNendo, fromNendo + "年度から" + toNendo + "年度");
                listKikan.add(sItem);
            }
            catch (Exception ex) {
                throw new FtlSystemException("対象期間プルダウン作成", null, ex);
            }
        }
        return listKikan;
    }
    
    /**
     * 初期処理
     * @param dto
     */
    private void validate(KakoUriageDto dto, KakoUriageReqDto dtoReq) {
        if (dto == null) {
            throw new NotNullException("画面情報");
        }
        if (dtoReq.getCompanyCd() == null || dtoReq.getCompanyCd().trim().equals("")) {
            throw new NotNullException("会社");
        }
    }
    
    public CodHyojiTaishoDao getCodHyojiTaishoDao() {
        return codHyojiTaishoDao;
    }

    public void setCodHyojiTaishoDao(CodHyojiTaishoDao codHyojiTaishoDao) {
        this.codHyojiTaishoDao = codHyojiTaishoDao;
    }
}