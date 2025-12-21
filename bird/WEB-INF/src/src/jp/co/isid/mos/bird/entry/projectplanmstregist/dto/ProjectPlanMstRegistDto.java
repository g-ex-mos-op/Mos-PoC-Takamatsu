package jp.co.isid.mos.bird.entry.projectplanmstregist.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jp.co.isid.mos.bird.entry.common.dto.CommonDto;
import jp.co.isid.mos.bird.entry.projectplanmstregist.entity.UICourseInfo;
import jp.co.isid.mos.bird.entry.projectplanmstregist.util.ProjectPlanMstRegistUtil;
import jp.co.isid.mos.bird.framework.util.DateManager;

/**
 * 事業方針説明会マスタ登録DTO
 * 
 * @author xkinu
 *
 */
public class ProjectPlanMstRegistDto extends CommonDto {
    /** 処理対象：処理タイプ */
    private String modeType = ProjectPlanMstRegistUtil.MODETYPE_INSERT;
    /** 条件項目：対象事業方針説明会情報*/
    private Map listCourseInfo = new HashMap();
    /** 処理対象：選択済み対象コースインデックス*/
    private Map targetCourseIndex = new HashMap();
    /** 登録対象：編集対象事業方針説明会情報エンティティー */
    private Map entityMstData = new HashMap();
    /** 登録対象：事業方針説明会マスタ */
    private Map listMst = new HashMap();
    /** 登録対象：事業方針説明会日付マスタ */
    private Map listMstDate = new HashMap();
    /** 登録対象：事業方針説明会文言マスタ */
    private Map listMstNotice = new HashMap();
    /** 編集・削除対象データ存在フラグ */
    private boolean registDataExit = false;
    /**
     * @return modeType を戻します。
     */
    public String getModeType() {
        return modeType;
    }

    /**
     * @param modeType 設定する modeType。
     */
    public void setModeType(String modeType) {
        this.modeType = modeType;
    }
    /**
     * クリア処理
     * 
     * 1.処理タイプをINSERTにデフォルト設定。
     * 2.編集対象事業方針説明会情報エンティティーのnull設定。
     * 3.対象事業方針説明会情報のnull設定。
     * 4.登録対象：事業方針説明会マスタのnull設定。
     * 5.登録対象：事業方針説明会日付マスタのnull設定。
     * 6.登録対象：事業方針説明会文言マスタのnull設定。
     * 7.選択済み対象コースインデックスへ「"-1"」にデフォルト設定。
     * 
     */
    public void clear() {
        //1.処理タイプをINSERTにデフォルト設定。
        setModeType(ProjectPlanMstRegistUtil.MODETYPE_INSERT);
        //2.編集対象事業方針説明会情報エンティティーのnull設定。
        setEntityMstData(null);
        //3.対象事業方針説明会情報のnull設定。
        setListCourseInfo(null);
        //4.登録対象：事業方針説明会マスタのnull設定。
        setListMst(null);
        //5.登録対象：事業方針説明会日付マスタのnull設定。
        setListMstDate(null);
        //6.登録対象：事業方針説明会文言マスタのnull設定。
        setListMstNotice(null);
        //7.選択済み対象コースインデックスへ「"-1"」にデフォルト設定。
        setTargetCourseIndex("-1");
        //8.編集・削除対象データ存在フラグへfalse設定。
        setRegistDataExit(false);
    }


    /**
     * 編集対象事業方針説明会情報エンティティー取得処理
     * 
     * @return entityMstData を戻します。
     */
    public UICourseInfo getEntityMstData() {
        return (UICourseInfo)entityMstData.get(new Integer(getWindowId()));
    }

    /**
     * 編集対象事業方針説明会情報エンティティー設定処理
     * 
     * @param entityMstData 設定する entityMstData。
     */
    public void setEntityMstData(UICourseInfo entityMstData) {
        this.entityMstData.put(new Integer(getWindowId()), entityMstData);
    }

    /**
     * 対象事業方針説明会情報 取得処理
     * 
     * 条件画面の説明会リストデータです。
     * 
     * @return listCourseInfo を戻します。
     */
    public List getListCourseInfo() {
        return (List)listCourseInfo.get(new Integer(getWindowId()));
    }

    /**
     * 対象事業方針説明会情報 設定処理
     * 
     * 条件画面の説明会リストデータです。
     * 
     * @param listCourseInfo 設定する listCourseInfo。
     */
    public void setListCourseInfo(List listCourseInfo) {
        this.listCourseInfo.put(new Integer(getWindowId()), listCourseInfo);
    }
    /**
     * 対象コース情報有無判断処理
     * 
     * @return
     */
    public boolean isEmptyListCourseInfo(){
        if(getListCourseInfo() == null || getListCourseInfo().size() == 0){
            return true;
        }
        return false;
    }
    /**
     * 対象処理名称取得処理
     * 
     * @return
     */
    public String getModeTypeKj(){
        String modeType = getModeType();
        if(modeType == null){
            
        }
        else if(ProjectPlanMstRegistUtil.MODETYPE_INSERT.equals(modeType)){
            return "新規登録";
        }
        else if(ProjectPlanMstRegistUtil.MODETYPE_UPDATE.equals(modeType)){
            return "修正";
        }
        else if(ProjectPlanMstRegistUtil.MODETYPE_DELETE.equals(modeType)){
            return "削除";
        }
        return null;
    }
    
    /**
     * システム日付の年度を取得処理
     * 
     * @return sysNendo を戻します。
     */
    public String getSysNendo() {
        String sysDate = getSysDate();
        if(sysDate != null){
            String sysNendo = null;
            try{
                sysNendo = DateManager.getCurrentYear(sysDate);
            }catch(Exception e){
                //何もしない
            }
            return sysNendo;
        }
        return null;
    }
    /**
     * 修正箇所制御判断処理
     * 
     * true:編集可　false:編集不可
     * @return
     */
    public boolean isReadOnly(){
        if(ProjectPlanMstRegistUtil.MODETYPE_UPDATE.equals(getModeType())){
            if(getEntityMstData().getEntryFrom().compareTo(getSysDate()) <=0){
                //本部：申込開始日 <= システム日付の場合
                return true;
            }
        }
        return false;
    }

    /**
     * @return listMst を戻します。
     */
    public List getListMst() {
        return (List)listMst.get(new Integer(getWindowId()));
    }

    /**
     * @param listMst 設定する listMst。
     */
    public void setListMst(List listMst) {
        this.listMst.put(new Integer(getWindowId()), listMst);
    }

    /**
     * @return listMstDate を戻します。
     */
    public List getListMstDate() {
        return (List)listMstDate.get(new Integer(getWindowId()));
    }

    /**
     * @param listMstDate 設定する listMstDate。
     */
    public void setListMstDate(List listMstDate) {
        this.listMstDate.put(new Integer(getWindowId()), listMstDate);
    }

    /**
     * @return listMstNotice を戻します。
     */
    public List getListMstNotice() {
        return (List)listMstNotice.get(new Integer(getWindowId()));
    }

    /**
     * @param listMstNotice 設定する listMstNotice。
     */
    public void setListMstNotice(List listMstNotice) {
        this.listMstNotice.put(new Integer(getWindowId()), listMstNotice);
    }

    /**
     * @return targetCourseIndex を戻します。
     */
    public String getTargetCourseIndex() {
        return (String)targetCourseIndex.get(new Integer(getWindowId()));
    }

    /**
     * @param targetCourseIndex 設定する targetCourseIndex。
     */
    public void setTargetCourseIndex(String targetCourseIndex) {
        this.targetCourseIndex.put(new Integer(getWindowId()), targetCourseIndex);
    }

    /**
     * @param registDataExit 設定する registDataExit。
     */
    public void setRegistDataExit(boolean registDataExit) {
        this.registDataExit = registDataExit;
    }

    /**
     * @return registDataExit を戻します。
     */
    public boolean isRegistDataExit() {
        return registDataExit;
    }

}
