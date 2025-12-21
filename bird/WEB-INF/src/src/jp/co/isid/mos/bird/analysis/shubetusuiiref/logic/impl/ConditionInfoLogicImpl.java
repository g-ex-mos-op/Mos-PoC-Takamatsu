package jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.dto.ShubetuSuiiRefReqDto;
import jp.co.isid.mos.bird.analysis.shubetusuiiref.logic.ConditionInfoLogic;
import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.GetSibuHoyuTenpoLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetHyojiTaishoLogicImpl;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;

/**
 * 条件項目の取得 ロジック
 * @author xnkusama
 *
 */
public class ConditionInfoLogicImpl implements ConditionInfoLogic {
    /** ロジックID */
    public static final String LOGIC_ID = "BDT003L01";

    /**LOGIC*/
    private GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic;
    private GetHyojiTaishoLogic getHyojiTaishoLogic;
    
    /**定数*/
    //期間指定プルダウン年月数
    private static final int KIKAN_PULLDOWN_MONTH = 13;
    
    public void execute(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq) {
        //初期処理
        validate(dto, dtoReq);
        
        //ユーザータイプ別処理
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            //２．本部ユーザーの場合、【共通ロジック】支部一覧の取得を実行
            List listSibu = getSibuHoyuTenpoLogic().execute(dtoReq.getCompanyCd(), dto.getUserId(), dto.getBirdUserInfo().isLimit());
            dto.setListHyojiTaisho(listSibu);
        }
        else if (UserType.ONER.equals(dto.getUserTypeCd())) {
            //３．オーナーユーザーの場合、保有店舗一覧を取得する
            Map mapParam = new HashMap();
            mapParam.put(GetHyojiTaishoLogicImpl.PM_COMPANY_CD, dtoReq.getCompanyCd());
            mapParam.put(GetHyojiTaishoLogicImpl.PM_TAISHOJOKEN, TaishoJoken.CODE_MISE);
            mapParam.put(GetHyojiTaishoLogicImpl.PM_APP_DATE, dto.getBirdDateInfo().getAppDate());
            Map retMap = getGetHyojiTaishoLogic().execute(mapParam);
            dto.setListHyojiTaisho((List) retMap.get(GetHyojiTaishoLogicImpl.RK_LIST));
        }
        
        //４－１．店舗種別プルダウン用リスト作成
        dto.setListTenpoShu(makeTenpoShuList());
        //４－２．店舗種別（個店用）プルダウン用リスト作成
        dto.setListTenpoShuKoten(makeTenpoShuListKoten());

        //５．対象条件プルダウン用リスト作成
        dto.setListTaishoJoken(makeTaishoJokenList(dto, dtoReq));
        
        //６．期間指定プルダウン用リストを作成
        dto.setListKikan(makeKikanList(dto));
        
        //７．前年データ種別用リストを作成
        dto.setListZenDataShu(makeZenDataShuList());
        
    }
    
    /**
     * 店舗種別プルダウン作成
     * @return
     */
    private List makeTenpoShuList() {
        List listTenpoShu = new ArrayList();
        listTenpoShu.add(new SelectItem("ALL", "全店"));
        listTenpoShu.add(new SelectItem("1", "前年対象店"));
        
        return listTenpoShu;
    }
    
    /**
     * 店舗種別プルダウン作成（個店用）
     * @return
     */
    private List makeTenpoShuListKoten() {
        List listTenpoShu = new ArrayList();
        listTenpoShu.add(new SelectItem("ALL", "全店"));
        
        return listTenpoShu;
    }
    
    /**
     * 対象条件プルダウン作成
     * @return
     */
    private List makeTaishoJokenList(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq) {
        String[] codes;
        if (UserType.HONBU.equals(dto.getUserTypeCd())) {
            codes = new String[]{TaishoJoken.CODE_ALL, TaishoJoken.CODE_SIBU, TaishoJoken.CODE_MISE};
        }
        else {
            codes = new String[]{TaishoJoken.CODE_ALL, TaishoJoken.CODE_MISE};
        }
        
        return TaishoJoken.getPullDownList(dto.getUserTypeCd(), dtoReq.getCompanyCd(), dto.getBirdUserInfo().isLimit(), codes);
    }
    
    /**
     * 期間指定プルダウン作成
     * @return
     */
    private List makeKikanList(ShubetuSuiiRefDto dto) {
        List listKikan = new ArrayList();
        DateFormatter dtFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
        for (int i = 0; i < KIKAN_PULLDOWN_MONTH; i++) {
            try {
                String month = DateManager.getPrevMonth(dto.getBirdDateInfo().getAppDate().substring(0, 6), i);
                SelectItem sItem = new SelectItem(month, dtFormatter.format(month, true));
                listKikan.add(sItem);
            }
            catch (Exception ex) {
                throw new FtlSystemException("期間指定プルダウン作成", null, ex);
            }
        }
        return listKikan;
    }

    /**
     * 前年データ種別プルダウン作成
     * @return
     */
    private List makeZenDataShuList() {
        List listZenDataShu = new ArrayList();
        listZenDataShu.add(new SelectItem("DOYO", "前年同曜日"));
        listZenDataShu.add(new SelectItem("DOJITU", "前年同日"));
        
        return listZenDataShu;
    }
    
    /**
     * 初期処理
     * @param dto
     */
    private void validate(ShubetuSuiiRefDto dto, ShubetuSuiiRefReqDto dtoReq) {
        if (dto == null) {
            throw new NotNullException("画面情報");
        }
        if (dtoReq.getCompanyCd() == null || dtoReq.getCompanyCd().trim().equals("")) {
            throw new NotNullException("会社");
        }
    }
    
    public GetHyojiTaishoLogic getGetHyojiTaishoLogic() {
        return getHyojiTaishoLogic;
    }

    public void setGetHyojiTaishoLogic(GetHyojiTaishoLogic getHyojiTaishoLogic) {
        this.getHyojiTaishoLogic = getHyojiTaishoLogic;
    }

    public GetSibuHoyuTenpoLogic getSibuHoyuTenpoLogic() {
        return sibuHoyuTenpoLogic;
    }

    public void setSibuHoyuTenpoLogic(GetSibuHoyuTenpoLogic sibuHoyuTenpoLogic) {
        this.sibuHoyuTenpoLogic = sibuHoyuTenpoLogic;
    }

}
