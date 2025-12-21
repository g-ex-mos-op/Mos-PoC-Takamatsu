package jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.impl;

import java.util.ArrayList;
import java.util.List;

import javax.faces.model.SelectItem;

import jp.co.isid.mos.bird.bizsupport.tempstorereplace.logic.ListTargetSearchLogic;
import jp.co.isid.mos.bird.framework.exception.ApplicationException;

/**
 * 対象条件プルダウンリストを生成する
 * @author Aspac
 */
public class ListTargetSearchLogicImpl implements ListTargetSearchLogic {
    
    /* ロジックID */
    public static final String LOGIC_ID = "BBS025L04";
    

    /**
     * 対象条件プルダウンリストを生成する
     * 
     * @param companyCd
     * @return List
     * @exception ApplicationException
     */
    public List execute(String companyCd) throws ApplicationException {
        
        List listTarget = new ArrayList();
        
        //会社コードが『モスフードサービス』
        if(companyCd.equals("00")){
            listTarget.add(new SelectItem("0","全社"));
            listTarget.add(new SelectItem("1","事業本部"));
            listTarget.add(new SelectItem("2","支部"));
        }
        else {
            listTarget.add(new SelectItem("0","全社"));
            listTarget.add(new SelectItem("2","支部"));
        }

        return listTarget;
    }
    
    
}
