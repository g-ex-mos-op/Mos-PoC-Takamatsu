package jp.co.isid.mos.bird.storemanage.anshinreport.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.common.code.TaishoJoken;
import jp.co.isid.mos.bird.common.code.UserType;
import jp.co.isid.mos.bird.common.entity.CodHyojiTaisho;
import jp.co.isid.mos.bird.common.logic.GetHyojiTaishoLogic;
import jp.co.isid.mos.bird.common.logic.impl.GetHyojiTaishoLogicImpl;
import jp.co.isid.mos.bird.framework.control.BirdDateInfo;
import jp.co.isid.mos.bird.framework.control.BirdUserInfo;
import jp.co.isid.mos.bird.framework.entity.UIUserOner;
import jp.co.isid.mos.bird.framework.exception.NotExistException;
import jp.co.isid.mos.bird.framework.exception.NotNullException;
import jp.co.isid.mos.bird.storemanage.anshinreport.dto.AnshinReportDto;
import jp.co.isid.mos.bird.storemanage.anshinreport.logic.ChangeCompanyLogic;

/**
 * あんしん点検結果報告
 * 会社コード変更時ロジック
 * 
 * @author kawashima
 */
public class ChangeCompanyLogicImpl implements ChangeCompanyLogic {
    private GetHyojiTaishoLogic getHyojiTaishoLogic;
    /**
     * 事前条件処理
     * 
     * @param dto
     * @throws Exception
     */
    private void validate(AnshinReportDto dto) throws Exception {
        // 画面DTO
        if (dto == null) {
            throw new NotNullException("あんしん点検結果報告 画面DTO");
        }
        if (dto.getCompanyCd() == null) {
            throw new NotNullException("会社", "companyCd", 0);
        }
    }
    
    /**
     * 条件画面出力データ検索と検索結果のDTOへの設定を行う
     * 
     * 条件画面に必要な全てのデータを戻します。
     * 
     * @param BizReportRefDto dto 画面データ保持クラス
     * @param MstUser ユーザ情報
     * @param BirdDateInfoe-mossles日付情報
     * @exception Exception
     */
    public String execute(AnshinReportDto dto, BirdUserInfo userInfo, BirdDateInfo dateInfo) throws Exception {
        //事前条件判断処理実行
        validate(dto);
        miseSerch(dto, userInfo,dateInfo);
        //検索データを戻す。
        return null;
    }
    /**
     * 店検索
     * @param dto
     * @return
     */
    public String miseSerch(AnshinReportDto dto, BirdUserInfo userInfo, BirdDateInfo dateInfo){

        // 表示対象データ検索ロジックを実行する
        Map params = new HashMap();
        params.put(GetHyojiTaishoLogicImpl.PM_APP_DATE, dateInfo.getAppDate());
        params.put(GetHyojiTaishoLogicImpl.PM_COMPANY_CD, dto.getCompanyCd());
        params.put(GetHyojiTaishoLogicImpl.PM_TAISHOJOKEN, TaishoJoken.CODE_MISE);
        Map resultMap = getGetHyojiTaishoLogic().execute(params);
        List list = (List)resultMap.get(GetHyojiTaishoLogicImpl.RK_LIST);
        
        if (list == null || list.size() == 0) {
            throw new NotExistException("店舗情報");
        }
        
        // オーナーユーザの場合、"全店"を追加する
        if (UserType.isOner(dto.getUsertypeCd())) {
            CodHyojiTaisho hyojiTaisho = new CodHyojiTaisho();
            String onerCd = ((UIUserOner)((List)userInfo.getUserOner()).get(0)).getOnerCd();
            hyojiTaisho.setHyojitaishoCd(onerCd);
            hyojiTaisho.setHyojitaishoName("全店");
            list.add(0,hyojiTaisho);
        }
        
        // 表示情報を設定する
        dto.setMiseCd(((CodHyojiTaisho)(list.get(0))).getHyojitaishoCd());
        dto.setMiseName(((CodHyojiTaisho)(list.get(0))).getHyojitaishoNameAddClose());
        
        dto.setMiseList(list);
        
        return null;

    }

    public GetHyojiTaishoLogic getGetHyojiTaishoLogic() {
        return getHyojiTaishoLogic;
    }

    public void setGetHyojiTaishoLogic(GetHyojiTaishoLogic getHyojiTaishoLogic) {
        this.getHyojiTaishoLogic = getHyojiTaishoLogic;
    }
    
}
