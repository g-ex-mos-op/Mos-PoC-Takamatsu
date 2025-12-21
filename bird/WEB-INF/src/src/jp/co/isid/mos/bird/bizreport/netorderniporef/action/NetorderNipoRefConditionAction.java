package jp.co.isid.mos.bird.bizreport.netorderniporef.action;

/**
 * ネット注文日報の条件部画面アクション・インターフェース
 * 
 */
public interface NetorderNipoRefConditionAction {
    /**
     * アクション【初期化処理】
     * @return String 遷移先ビューID
     */   
    public String initialize();
    /**
     * アクション【SV検索ボタン】処理
     * @return String 遷移先ビューID
     */   
    public String callSvForm();
    /**
     * アクション【実行】処理
     * @return String 遷移先ビューID
     */   
    public String search();

}