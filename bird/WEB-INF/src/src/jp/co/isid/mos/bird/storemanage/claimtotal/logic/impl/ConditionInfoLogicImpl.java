package jp.co.isid.mos.bird.storemanage.claimtotal.logic.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetHyojiTaishoLogicImpl;
import jp.co.isid.mos.bird.framework.exception.FtlSystemException;
import jp.co.isid.mos.bird.framework.exception.MissingDataException;
import jp.co.isid.mos.bird.framework.util.DateManager;
import jp.co.isid.mos.bird.framework.util.formatter.DateFormatter;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalRequestDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.dto.ClaimTotalSessionDto;
import jp.co.isid.mos.bird.storemanage.claimtotal.logic.ConditionInfoLogic;

public class ConditionInfoLogicImpl implements ConditionInfoLogic {

    /** ロジックID */
    public static final String LOGIC_ID = "BSM017L01";
    
    /* 対象年月 表示月数（１４ヶ月表示）*/
    private static final int TAISHO_NENGETU_NUM = 14;
    /* 対象条件配列：本部（標準パターン） */
    private static final String[] CODE_TABLE_PATTERN
        = new String []{TaishoJoken.CODE_ALL, TaishoJoken.CODE_JIGYOU, TaishoJoken.CODE_SLAREA, TaishoJoken.CODE_SIBU, TaishoJoken.CODE_ONER, TaishoJoken.CODE_MISE};

    
    /* 年月フォーマッター(yyyy/MM) */
    private static final DateFormatter dateFormatter = new DateFormatter(DateFormatter.DATE_TYPE_YM, DateFormatter.PATTERN_MONTH_SLASH);
    
    private GetHyojiTaishoLogic getHyojiTaishoLogic;
    
    public void execute(ClaimTotalSessionDto sessionDto, ClaimTotalRequestDto requestDto) {
        //事前条件
        validate(sessionDto, requestDto);
        
        // 対象年月リスト作成
        String sysDate = sessionDto.getBirdDateInfo().getSysDate();
        List listTaisjoNengetu = new ArrayList();
        for (int i = 0; i < TAISHO_NENGETU_NUM; i++) {
            String month = "";
            try {
                month = DateManager.getPrevMonth(sysDate.substring(0, 6), i);
            }
            catch (Exception ex) {
                throw new FtlSystemException("対象年月プルダウンリスト作成", null, ex);
            }
            listTaisjoNengetu.add(new SelectItem(month, dateFormatter.format(month, true)));
        }
        sessionDto.setListTaishoNengetu(listTaisjoNengetu);
        
        // 対象条件リスト作成
        List listTaisjoJoken = TaishoJoken.getPullDownList(
                                    sessionDto.getUserTypeCd()
                                    , requestDto.getCompanyCd()
                                    , sessionDto.getBirdUserInfo().isLimit()
                                    , CODE_TABLE_PATTERN);
        sessionDto.setListTaishoJoken(listTaisjoJoken);
        
        // 表示対象リスト作成
        for (Iterator ite = sessionDto.getListTaishoJoken().iterator(); ite.hasNext();) {
            SelectItem selectItem = (SelectItem)ite.next();
            String taishoJoken = (String) selectItem.getValue();
            if (!TaishoJoken.CODE_ALL.equals(taishoJoken)
                    && !TaishoJoken.CODE_ONER.equals(taishoJoken)
                    && !TaishoJoken.CODE_MISE.equals(taishoJoken)) 
            {
                List listHyojiTaisho;
                Map mapParam = new HashMap();
                mapParam.put(GetHyojiTaishoLogicImpl.PM_COMPANY_CD, requestDto.getCompanyCd());
                mapParam.put(GetHyojiTaishoLogicImpl.PM_TAISHOJOKEN, taishoJoken);
                mapParam.put(GetHyojiTaishoLogicImpl.PM_APP_DATE, sessionDto.getBirdDateInfo().getAppDate());
                Map mapRet = getGetHyojiTaishoLogic().execute(mapParam);
                listHyojiTaisho = (List) mapRet.get(GetHyojiTaishoLogicImpl.RK_LIST);
                
                if (TaishoJoken.CODE_JIGYOU.equals(taishoJoken)) {
                    sessionDto.setListJigyohonbu(listHyojiTaisho);
                }
                else if (TaishoJoken.CODE_SLAREA.equals(taishoJoken)) {
                    sessionDto.setListSlArea(listHyojiTaisho);
                }
                else if (TaishoJoken.CODE_SIBU.equals(taishoJoken)) {
                    sessionDto.setListSibu(listHyojiTaisho);
                }
            }
        }
        
    }
    
    /**
     * 事前条件
     * @param sessionDto
     * @param requestDto
     */
    private void validate(ClaimTotalSessionDto sessionDto, ClaimTotalRequestDto requestDto) {
        if (sessionDto == null) {
            throw new MissingDataException("セッション情報");
        }
        if (requestDto == null) {
            throw new MissingDataException("リクエスト情報");
        }
    }

    public GetHyojiTaishoLogic getGetHyojiTaishoLogic() {
        return getHyojiTaishoLogic;
    }

    public void setGetHyojiTaishoLogic(GetHyojiTaishoLogic getHyojiTaishoLogic) {
        this.getHyojiTaishoLogic = getHyojiTaishoLogic;
    }

}
