package jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.impl;

import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.action.EditAction;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenMstRegistDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenShanaiMenuDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.dto.MosChickenShokuhouzaiDto;
import jp.co.isid.mos.bird.bizsupport.moschickenmastregist.util.MosChickenMstRegistUtil;

/**
 * モスチキン管理マスタ登録
 * 編集画面用アクション実行クラス
 * 
 * @author xkinu
 *
 */
public class EditActionImpl implements EditAction {
    /* アクションID：初期処理 */
    public static final String initialize_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A11";
    /* アクションID：戻る */
    public static final String back_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A12";
    /* アクションID：メニューグループ追加 */
    public static final String addMenuGroup_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A13";
    /* アクションID：メニュー追加 */
    public static final String addMenu_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A14";
    /* アクションID：一括編集 */
    public static final String lumpChoise_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A15";
    /* アクションID：編集 */
    public static final String choise_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A16";
    /* アクションID：削除(メニューグループ) */
    public static final String deleteMenuGroup_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A17";
    /* アクションID：削除(メニュー) */
    public static final String deleteMenu_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A18";
    /* アクションID：削除(食包材) */
    public static final String deleteShokuhouzai_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A19";
    /* アクションID：確認 */
    public static final String confirm_ACTION_ID = MosChickenMstRegistUtil.SCREEN_ID+"A20";

    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenMstRegistDto mosChickenMstRegistDto = null;
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenShanaiMenuDto mosChickenShanaiMenuDto = null;
    /* DTO【モスチキン管理マスタ登録】*/
    private MosChickenShokuhouzaiDto mosChickenShokuhouzaiDto = null;
    /**
     * @return mosChickenMstRegistDto を戻します。
     */
    public MosChickenMstRegistDto getMosChickenMstRegistDto() {
        return mosChickenMstRegistDto;
    }
    /**
     * @param mosChickenMstRegistDto 設定する mosChickenMstRegistDto。
     */
    public void setMosChickenMstRegistDto(
            MosChickenMstRegistDto mosChickenMstRegistDto) {
        this.mosChickenMstRegistDto = mosChickenMstRegistDto;
    }
    /**
     * 初期処理 アクション
     * 
     * @return null 画面ID
     */
    public String initialize(){
        if(getMosChickenShanaiMenuDto().getDuplicateEx() != null){
            throw getMosChickenShanaiMenuDto().getDuplicateEx();
        }
        if(getMosChickenShokuhouzaiDto().getDuplicateEx() != null){
            throw getMosChickenShokuhouzaiDto().getDuplicateEx();
        }
        return null;
    }
    /**
     * 戻る アクション
     * 
     * @return 初期画面ID
     */
    public String back(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        return MosChickenMstRegistUtil.VIEW_ID_CONDITION;
    }
    /**
     * メニューグループ追加 アクション
     * 
     * メニューグループは上限１０個まで。
     * 
     * @return null 画面ID
     */
    public String addMenuGroup(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.メニューグループ追加処理を実行する。
        MosChickenMstRegistUtil.addMenuGroup(getMosChickenMstRegistDto());
        return null;
    }
    /**
     * メニュー追加 アクション
     * 
     * １．ユーティル【static 処理保持クラス】.メニュー件数チェック処理 を実行する。
     * ２．ユーティル【static 処理保持クラス】.処理対象メニューコードインデックスを設定する。
     * 
     * @return メニュー追加画面ID
     */
    public String addMenu(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.メニュー件数チェック処理 を実行する。
        MosChickenMstRegistUtil.checkMenuCnt(getMosChickenMstRegistDto());
        //２．ユーティル【static 処理保持クラス】.処理対象メニューコードインデックスを設定する。
        MosChickenMstRegistUtil.settingTargetMGroupIndex(getMosChickenMstRegistDto());
        //DTO【メニュー追加】クリア処理
        getMosChickenShanaiMenuDto().clear();
        return MosChickenMstRegistUtil.VIEW_ID_ADDMENU;
    }
    /**
     * 一括編集 アクション
     * 
     * 食包材を一括で選択を行うためのアクションです。
     * 
     * @return 食包材選択画面ID
     */
    public String lumpChoise(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.処理対象メニューコードインデックスを設定する。
        MosChickenMstRegistUtil.settingTargetMGroupIndex(getMosChickenMstRegistDto());
        //２．ユーティル【static 処理保持クラス】.メニュー存在チェックを実行する。
        MosChickenMstRegistUtil.checkingExisMenu(getMosChickenMstRegistDto());
        //DTO【食包材選択】クリア処理
        getMosChickenShokuhouzaiDto().clear();
        return MosChickenMstRegistUtil.VIEW_ID_CHOISESHOKU;
    }
    /**
     * 編集 アクション
     * 
     * 食包材の選択を行うためのアクションです。
     * 
     * @return 食包材選択画面ID
     */
    public String choise(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.処理対象メニューコードインデックスを設定する。
        MosChickenMstRegistUtil.settingTargetMGroupIndex(getMosChickenMstRegistDto());
        //DTO【食包材選択】クリア処理
        getMosChickenShokuhouzaiDto().clear();
        return MosChickenMstRegistUtil.VIEW_ID_CHOISESHOKU;
    }
    /**
     * 削除(メニューグループ) アクション
     * 
     * @return null 画面ID
     */
    public String deleteMenuGroup(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.メニューグループ削除処理を実行する。
        MosChickenMstRegistUtil.removeMenuGroup(getMosChickenMstRegistDto());
        return null;
    }

    /**
     * 削除(メニュー) アクション
     * 
     * @return null 画面ID
     */
    public String deleteMenu(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.メニュー削除処理を実行する。
        MosChickenMstRegistUtil.removeMenu(getMosChickenMstRegistDto());
        //２．ユーティル【static 処理保持クラス】.処理対象メニューコードインデックスを設定する。
        MosChickenMstRegistUtil.settingTargetMGroupIndex(getMosChickenMstRegistDto());
        
        return null;
    }

    /**
     * 削除(食包材) アクション
     * 
     * @return null 画面ID
     */
    public String deleteShokuhouzai(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.食包材削除処理を実行する。
        MosChickenMstRegistUtil.clearShokuhouzai(getMosChickenMstRegistDto());
        //２．ユーティル【static 処理保持クラス】.処理対象メニューコードインデックスを設定する。
        MosChickenMstRegistUtil.settingTargetMGroupIndex(getMosChickenMstRegistDto());
        
        return null;
    }
    /**
     * 確認 アクション
     * 
     * @return 確認画面ID
     */
    public String confirm(){
        getMosChickenShanaiMenuDto().clear();
        getMosChickenShokuhouzaiDto().clear();
        //１．ユーティル【static 処理保持クラス】.入力値チェック処理を実行する。
        MosChickenMstRegistUtil.checkingInputParam(getMosChickenMstRegistDto());
        //２．ユーティル【static 処理保持クラス】.ソート処理を実行する。
        MosChickenMstRegistUtil.sort(getMosChickenMstRegistDto());
        return MosChickenMstRegistUtil.VIEW_ID_CONFIRM;
    }
    /**
     * @return mosChickenShanaiMenuDto を戻します。
     */
    public MosChickenShanaiMenuDto getMosChickenShanaiMenuDto() {
        return mosChickenShanaiMenuDto;
    }
    /**
     * @param mosChickenShanaiMenuDto 設定する mosChickenShanaiMenuDto。
     */
    public void setMosChickenShanaiMenuDto(
            MosChickenShanaiMenuDto mosChickenShanaiMenuDto) {
        this.mosChickenShanaiMenuDto = mosChickenShanaiMenuDto;
    }
    /**
     * @return mosChickenShokuhouzaiDto を戻します。
     */
    public MosChickenShokuhouzaiDto getMosChickenShokuhouzaiDto() {
        return mosChickenShokuhouzaiDto;
    }
    /**
     * @param mosChickenShokuhouzaiDto 設定する mosChickenShokuhouzaiDto。
     */
    public void setMosChickenShokuhouzaiDto(
            MosChickenShokuhouzaiDto mosChickenShokuhouzaiDto) {
        this.mosChickenShokuhouzaiDto = mosChickenShokuhouzaiDto;
    }

}
